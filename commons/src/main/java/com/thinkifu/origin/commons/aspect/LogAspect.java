package com.thinkifu.origin.commons.aspect;


import com.thinkifu.origin.commons.annontation.CurrentLimiting;
import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.GlobalErrorEnum;
import com.thinkifu.origin.commons.util.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面类
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * ..表示包及子包 该方法代表controller层的所有方法
     */
    @Pointcut("execution(public * com.thinkifu.origin.*.controller..*.*(..))")
    public void controllerMethod() {
    }

    /**
     * 方法执行前
     *
     * @param joinPoint
     * @throws Exception
     */
    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        StringBuilder requestLog = new StringBuilder();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CurrentLimiting currentLimiting = signature.getMethod().getAnnotation(CurrentLimiting.class);
        // 如果限流注解不为空
        if (currentLimiting != null) {
            currentLimiting(request.getRequestURI(), currentLimiting, HttpContextUtils.getIpAddress(request));
        }
        ApiOperation annotation = signature.getMethod().getAnnotation(ApiOperation.class);
        if (annotation != null) {
            requestLog.append(annotation.value()).append("\t");
        }
        requestLog.append("请求信息：").append("URL = {").append(request.getRequestURI()).append("},\t")
                .append("请求方式 = {").append(request.getMethod()).append("},\t")
                .append("请求IP = {").append(HttpContextUtils.getIpAddress(request)).append("},\t")
                .append("类方法 = {").append(signature.getDeclaringTypeName()).append(".")
                .append(signature.getName()).append("},\t");
        // 处理请求参数
        String[] paramNames = signature.getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        int paramLength = null == paramNames ? 0 : paramNames.length;
        if (paramLength == 0) {
            requestLog.append("请求参数 = {} ");
        } else {
            requestLog.append("请求参数 = [");
            for (int i = 0; i < paramLength; i++) {
                if (paramValues[i] instanceof ServletRequest || paramValues[i] instanceof ServletResponse || paramValues[i] instanceof MultipartFile) {
                    continue;
                }
                requestLog.append(paramNames[i]).append("=").append(JsonUtil.objectToJson(paramValues[i])).append(",");
            }
            requestLog.append("]");
        }
        log.info(requestLog.toString());
        request.setAttribute("beginTime", System.currentTimeMillis());
    }
    /**
     * 方法执行之后删除threadLocal里面的userId
     */
    @After("controllerMethod()")
    public void after() {
        UserContext.remove();
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("{}方法耗时:{}", request.getRequestURI(), System.currentTimeMillis() - (long) request.getAttribute("beginTime"));
    }
    
    /**
     * 方法执行返回值后
     *
     * @param resultVO
     */
    @AfterReturning(returning = "resultVO", pointcut = "controllerMethod()")
    public void logResultVOInfo(Result resultVO) {
        log.info("请求结果：" + resultVO.getCode() + "\t" + resultVO.getMsg());
    }

    /**
     * 限流方法
     *
     * @param methodName
     * @param currentLimiting
     */
    private void currentLimiting(String methodName, CurrentLimiting currentLimiting, String ip) {
        String key = ip + methodName;
        int times = RedisTools.get(key, int.class, () -> 0, currentLimiting.second());
        // 如果大于了准许访问的次数就不能让他访问了
        if (times >= currentLimiting.times()) {
            throw new BaseException(GlobalErrorEnum.PLEASE_TRY_AGAIN_LATER);
        }
        RedisTools.set(key, ++times, currentLimiting.second());
    }
}
