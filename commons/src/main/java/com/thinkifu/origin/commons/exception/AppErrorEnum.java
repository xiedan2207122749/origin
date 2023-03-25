package com.thinkifu.origin.commons.exception;

import lombok.Getter;

/**
 * @author xieDan
 * @Classname SystemErrorEnum
 * @Description
 * @Date 2022/8/22 15:47
 * @Created by mr_xie
 */
@Getter
public enum AppErrorEnum {
    
    ACCOUNT_EXIST(0, "ACCOUNT_EXIST"),
    ACCOUNT_NOT_EXIST(1, "ACCOUNT_NOT_EXIST"),
    EXCEED_MAX_SEND_TIME(2, "EXCEED_MAX_SEND_TIME"),
    EMAIL_SEND_ERROR(3, "EMAIL_SEND_ERROR"),
    AUTH_CODE_ERROR(6, "AUTH_CODE_ERROR"),
    MUST_IS_PICTURE(7, "上传的文件不是图片, 请修正后重试"),
    MEMBER_NAME_EXIST(8, "MEMBER_NAME_EXIST"),
    EXCEED_MAX_MEMBER(10, "EXCEED_MAX_MEMBER"),
    YOU_ALREADY_BOUND_FACILITY(11, "YOU_ALREADY_BOUND_FACILITY"),
    THIS_FACILITY_ALREADY_BOUND(12, "THIS_FACILITY_ALREADY_BOUND"),
    
    UNKNOWN_ERROR(999, "UNKNOWN_ERROR");
    
    private int code;
    private String errorKey;
    
    
    AppErrorEnum(int code, String message) {
        this.code = code;
        this.errorKey = message;
    }
}
