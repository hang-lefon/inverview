
package com.example.interview.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.example.interview.dao.UserDao;
import com.example.interview.exception.BizException;
import com.example.interview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Project: interview - UserService</p>
 * <p>Powered by lanfeng On 2024-07-17 </p>
 * <p>描述：<p>
 *
 * @author lanfeng [hlefon@qq.com]
 * @version 1.0
 * @since 17
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Value("${jwt.key}")
    private String key;
    public Map<String, String> login(User user) {
        List<User> Tel = userDao.selectEmail(user.getEmail());
        if (Tel == null) {
            throw new BizException(609, "账号未注册");
        }
        User userall = userDao.select(user);
        if (ObjectUtil.isEmpty(userall)) {
            throw new BizException(610, "账号或密码错误");
        }
        //设置双Token
        //带参数的设置短时间
        Map<String, Object> map1 = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("id", userall.getId());
                put("username", userall.getUsername());
                put("exp", System.currentTimeMillis() / 1000 + 10 * 6 * 60 * 2);
            }
        };
        //不带参数的设置长时间
        Map<String, Object> map2 = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("id", userall.getId());
                put("exp", System.currentTimeMillis() / 1000 + 1000 * 60 * 60 * 24 * 30 * 4);
            }
        };
        String token = JWTUtil.createToken(map1, key.getBytes());
        //将Token存入map返回出去
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    public Integer register(User user) {
        return userDao.register(user);
    }

    public User me(String id) {
        return userDao.selectById(id);
    }
}
