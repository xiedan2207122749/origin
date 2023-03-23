package com.thinkifu.origin.commons.exception;

import lombok.Data;

/**
 * @Classname BaseException
 * @Description
 * @Date 2020/11/20 11:26
 * @Created by mr_xie
 */
@Data
public class BaseException extends RuntimeException {

    private String errorKey;
    private int code;
    private Object customData;

    public BaseException(GlobalErrorEnum error) {
        super();
        this.code = error.getCode();
        this.errorKey = error.getErrorKey();
    }
    public BaseException(AppErrorEnum error, Object customData) {
        super();
        this.code = error.getCode();
        this.errorKey = error.getErrorKey();
        this.customData = customData;
    }
    
    public BaseException(AppErrorEnum error) {
        super();
        this.code = error.getCode();
        this.errorKey = error.getErrorKey();
    }
    
    public BaseException(ManageErrorEnum error) {
        super();
        this.code = error.getCode();
        this.errorKey = error.getErrorKey();
    }
    
    public BaseException(String errorKey) {
        super();
        this.code = 200;
        this.errorKey = errorKey;
    }
    
}
