package com.example.interview.model;

import lombok.Data;

@Data
public class ResponseDTO {
    private int code;  //  0,非0 错误码
    private String msg; // 如果非0的时候，显示的是错误的信息
    private Object data;

    public static  ResponseDTO success(Object data)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(0);
        responseDTO.setData(data);
        return responseDTO;
    }
    public static ResponseDTO error(int code,String msg)
    {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(code);
        responseDTO.setMsg(msg);
        return responseDTO;
    }
}
