package com.zhanhong.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    //状态码
    private Integer code;
    //提示信息
    private String msg;
    //消息体
    private T data;

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setCode(200);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(200);
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.setMsg(msg);
        return result;
    }

}
