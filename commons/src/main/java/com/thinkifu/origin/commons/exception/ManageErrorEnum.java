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
public enum ManageErrorEnum {
    
    OTA_FILE_NOT_NULL(0, "OTA_FILE_NOT_NULL"),
    VERSION_NUMBER_EXIST(1, "VERSION_NUMBER_EXIST"),

    
    UNKNOWN_ERROR(999, "UNKNOWN_ERROR");
    
    private int code;
    private String errorKey;
    
    
    ManageErrorEnum(int code, String message) {
        this.code = code;
        this.errorKey = message;
    }
}
