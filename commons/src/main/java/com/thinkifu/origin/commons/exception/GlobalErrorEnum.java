package com.thinkifu.origin.commons.exception;


import lombok.Getter;

/**
 * 定义全局的错误code码与相关提示信息
 * @author dell
 */

@Getter
public enum GlobalErrorEnum {
    
    NO_HANDLER_FOUND(501, "NO_HANDLER_FOUND"),
    DUPLICATE_KEY(502, "DUPLICATE_KEY"),
    NO_HAVE_PERMISSION(509, "NO_HAVE_PERMISSION"),
    HTTP_MESSAGE_NOT_READABLE(503, "HTTP_MESSAGE_NOT_READABLE"),
    WAY_ERROR_OR_NO_LOGIN(510, "WAY_ERROR_OR_NO_LOGIN"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED(511, "HTTP_MEDIA_TYPE_NOT_SUPPORTED"),
    REQUIRED_FIELD_EMPTY(505, "REQUIRED_FIELD_EMPTY"),
    METHOD_ARGS_TYPE_ERROR(506, "METHOD_ARGS_TYPE_ERROR"),
    STRING_CONVERT_NUMBER_ERROR(507, "STRING_CONVERT_NUMBER_ERROR"),
    ILLEGAL_DATA(512, "参数非法"),
    OBJECT_TO_JSON_STR_ERROR(513, "OBJECT_TO_JSON_STR_ERROR"),
    JSON_STR_TO_OBJECT_ERROR(514, "JSON_STR_TO_OBJECT_ERROR"),
    JSON_STR_TO_LIST_ERROR(515, "JSON_STR_TO_LIST_ERROR"),
    PLEASE_TRY_AGAIN_LATER(516, "PLEASE_TRY_AGAIN_LATER"),
    TOKEN_EMPTY(517, "TOKEN_EMPTY"),
    ACCOUNT_HAS_BEEN_BANNED(518, "ACCOUNT_HAS_BEEN_BANNED"),
    TOKEN_NO_EXISTS(519, "TOKEN_NO_EXISTS"),
    ACCOUNT_OR_PASSWORD_ERROR(520, "ACCOUNT_OR_PASSWORD_ERROR"),

    
    UNKNOWN_ERROR(999, "UNKNOWN_ERROR");
    private int code;
    private String errorKey;


    GlobalErrorEnum(int code, String message) {
        this.code = code;
        this.errorKey = message;
    }
}