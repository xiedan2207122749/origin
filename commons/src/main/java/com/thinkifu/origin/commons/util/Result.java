package com.thinkifu.origin.commons.util;

import lombok.Data;

/**
 * @Classname Result
 * @Description
 * @Date 2020/10/11 16:15
 * @Created by Mr_xie
 */
@Data
public class Result<T> {

    private String msg;

    private int code;

    private T data;

    private Result(T data) {
        msg = "success";
        code = 200;
        this.data = data;
    }

    private Result(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    private Result() {
        msg = "success";
        code = 200;
    }
    
    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }
    
    public static Result ok() {
        return new Result();
    }

    public static Result error(String msg) {
        return new Result(msg, 500);
    }
    
    public static Result error(int code, String msg) {
        return new Result(msg, code);
    }
    
}
