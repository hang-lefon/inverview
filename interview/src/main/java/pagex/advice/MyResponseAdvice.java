package pagex.advice;


import cn.hutool.json.JSONUtil;
import com.example.interview.exception.BizException;
import com.example.interview.model.ResponseDTO;
import com.github.pagehelper.Page;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class MyResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @ExceptionHandler(Exception.class) //处理异常
    public Object exceptionHandler(Exception ex) {
        ex.printStackTrace();
        if(ex instanceof BizException){
            BizException bizException = (BizException) ex;

            return ResponseDTO.error(bizException.getCode(), bizException.getMsg());
        }
        return ResponseDTO.error(500, ex.getMessage());
    }


    @Override // 统一格式  {code:x,data:xxx} 处理正常
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseDTO){
           return body;
        }
        ResponseDTO dto = ResponseDTO.success(body);
        if (body instanceof Page) {
            Page page = (Page) body;
            long total = page.getTotal();
            HashMap<String, Object> map = new HashMap<>();
            map.put("total", total);
            map.put("items", body);
            dto = ResponseDTO.success(map);
        }

        if (selectedConverterType == StringHttpMessageConverter.class) {
            return JSONUtil.toJsonStr(dto);
        }
        return dto;
    }
}
