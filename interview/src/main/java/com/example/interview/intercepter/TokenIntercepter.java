/*
 * Copyright (c) 2021, 2024, lanfeng.cn All rights reserved.
 *
 */
package com.example.interview.intercepter;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.example.interview.exception.BizException;
import com.example.interview.model.LocalUser;
import com.example.interview.threadlocal.LocalUserUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * <p>Project: lefon-root - TokenIntercepter</p>
 * <p>Powered by lanfeng On 2024-03-04 </p>
 * <p>描述：<p>
 *
 * @author lanfeng [hlefon@qq.com]
 * @version 1.0
 * @since 17
 */
@Component
public class TokenIntercepter implements HandlerInterceptor {
    private String key="123456";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (ObjectUtil.isEmpty(token)) {
            throw new BizException(456, "token必传");
        }
        boolean b = false;
        //验证算法，JWTvalidator包含过期的验证，验证比较全面
        try {
            JWTValidator.of(token).validateAlgorithm(JWTSignerUtil.hs256(key.getBytes())).validateDate();
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!b) {
            throw new BizException(457, "token不正确");
        }
        
            //ThreadLocal
        LocalUser localUser  = JSONUtil.toBean(JWTUtil.parseToken(token).getPayload().toString(),LocalUser.class);
        //在当前线程脑门上贴了一个标签 对象-》 localUser
        LocalUserUtil.setLocalUser(localUser);
        MDC.put("userId",localUser.getId());
        return true;
    }
}
