package com.thinkifu.origin.commons.exception;


import com.thinkifu.origin.commons.util.JsonUtil;
import com.thinkifu.origin.commons.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Set;


/**
 * 异常处理器
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 具体异常处理类
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public Result handleException(BaseException e) {
        String errorKey = e.getErrorKey();
        logger.error(errorKey);
        if (e.getCustomData() == null) {
            return Result.error(e.getCode(), errorKey);
        } else {
            return Result.error(e.getCode(), MessageFormat.format(errorKey, e.getCustomData()));
        }
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.NO_HANDLER_FOUND));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.DUPLICATE_KEY));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.HTTP_MESSAGE_NOT_READABLE));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.WAY_ERROR_OR_NO_LOGIN));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.REQUIRED_FIELD_EMPTY));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.METHOD_ARGS_TYPE_ERROR));
    }

    @ExceptionHandler(NumberFormatException.class)
    public Result handleNumberFormatException(NumberFormatException e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.STRING_CONVERT_NUMBER_ERROR));
    }

    /**
     * get / post(没加requestBody注解)请求验证form如果不通过会抛这个异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public Result BindExceptionException(BindException e) {
        logger.error(e.getMessage(), e);
        return handleException(e.getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * post(加了requestBody注解才会进入 否则进入上面BingException) 请求验证form如果不通过会抛这个异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return handleException(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 控制层单个参数验证不通过会进这个异常  主要是针对get请求
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result ConstraintViolationException(ConstraintViolationException e) {
        logger.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if(!violations.isEmpty()) {
            // 设置验证结果状态码
            for (ConstraintViolation<?> item : violations) {
                return handleException(item.getMessage());
            }
        }
        return handleException(new BaseException(GlobalErrorEnum.UNKNOWN_ERROR));
    }
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write(JsonUtil.objectToJson(Result.error("the file is too lager")));
        writer.flush();
        writer.close();
    }

    private Result handleException(String key) {
        logger.error(key);
        return Result.error(key);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return handleException(new BaseException(GlobalErrorEnum.UNKNOWN_ERROR));
    }
}
