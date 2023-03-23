package com.thinkifu.origin.commons.util;


import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.GlobalErrorEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xieDan
 * @Classname EnumUtil
 * @Description 获取枚举工具类
 * @Date 2022/8/26 10:26
 * @Created by mr_xie
 */
public class EnumUtil {
    
    /**
     * 前提是枚举必须有getValue方法
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T getEnum(int value, Class<T> clazz) {
        try {
            T[] values = clazz.getEnumConstants();
            Method getValue = clazz.getMethod("getValue");
            for (int i = 0; i < values.length; i++) {
                if ((int) getValue.invoke(values[i]) == value) {
                    return values[i];
                }
            }
            throw new BaseException(GlobalErrorEnum.ILLEGAL_DATA);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new BaseException(GlobalErrorEnum.ILLEGAL_DATA);
        }
    }
}
