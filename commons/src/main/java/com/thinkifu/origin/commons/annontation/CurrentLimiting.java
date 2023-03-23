package com.thinkifu.origin.commons.annontation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xieDan
 * @Classname CurrentLimitingAnnotation
 * @Description
 * @Date 2021/4/14 9:46
 * @Created by mr_xie
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentLimiting {
    /**
     * 规定时间内某个接口访问次数
     * @return
     */
    int times() default 4;

    /**
     * 规定多少秒
     * @return
     */
    int second() default 10;
}
