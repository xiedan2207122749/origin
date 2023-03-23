package com.thinkifu.origin.commons.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 提供静态缓存接口
 */
@Component
public class RedisTools {

    private final static long NOT_EXPIRE = -1;

    private static RedisUtils REDIS_UTILS;

    @Autowired
    private RedisUtils redisUtils;

    public static boolean setAdd(String key, Object o) {
        return REDIS_UTILS.setAdd(key, String.valueOf(o));
    }

    public static long setCount(String key) {
        return REDIS_UTILS.setCount(key);
    }

    public static boolean setExist(String key, Object o) {
        return REDIS_UTILS.setExist(key, String.valueOf(o));
    }

    @PostConstruct
    private void init() {
        REDIS_UTILS = this.redisUtils;
    }

    /**
     * 获取redis值 支持默认返回实现
     *
     * @param key    key
     * @param clazz  返回class 用于json反序列化
     * @param mapper 如果获取为空 ， 默认获取方式 查询数据库
     * @param <T>    返回类型
     */
    public static <T> T get(String key, Class<T> clazz, Supplier<? extends T> mapper) {
        return get(key, clazz, mapper, NOT_EXPIRE, false);
    }

    /**
     * 获取redis值 支持默认返回实现
     *
     * @param key    key
     * @param clazz  返回class 用于json反序列化
     * @param mapper 如果获取为空 ， 默认获取方式 查询数据库
     * @param <T>    返回类型
     * @param expire 设置有效期 / 刷新有效期 (秒)
     */
    public static <T> T get(String key, Class<T> clazz, Supplier<? extends T> mapper, long expire) {
        return get(key, clazz, mapper, expire, false);
    }

    /**
     * @param key         key
     * @param clazz       返回class 用于json反序列化
     * @param mapper      如果获取为空 ， 默认获取方式 查询数据库
     * @param <T>         返回类型
     * @param expire      设置有效期 / 刷新有效期
     * @param autoRefresh 是否自动刷新过期时间
     */
    public static <T> T get(String key, Class<T> clazz, Supplier<? extends T> mapper, long expire, boolean autoRefresh) {
        String s = REDIS_UTILS.get(key, autoRefresh ? expire : NOT_EXPIRE);
        if (StrUtil.isNotEmpty(s)) {
            if (clazz.getSimpleName().equals("String")) {
                return (T) s;
            }
            return REDIS_UTILS.fromJson(s, clazz);
        }
        T result = mapper.get();
        //支持默认加载方式，如果查询不到及时投入redis
        if (Objects.nonNull(result)) {
            REDIS_UTILS.set(key, result, expire);
        }
        return result;
    }
    /**
     * 根据key移除缓存
     */
    public static void delete(String key) {
        REDIS_UTILS.delete(key);
    }

    /**
     * 添加缓存值
     */
    public static void set(String key, Object value) {
        REDIS_UTILS.set(key, value, NOT_EXPIRE);
    }

    /**
     * 添加缓存值
     */
    public static void set(String key, Object value, long expire) {
        REDIS_UTILS.set(key, value, expire);
    }

    /**
     * 增加缓存中的值
     *
     * @param key  key
     * @param hKey hKey
     * @param val  增加的值
     * @return 返回增加后的结果
     */
    public static long increase(String key, String hKey, long val, Supplier<Long> applyFun) {
        Long increase = REDIS_UTILS.increase(key, hKey, val);
        if (increase == null) {
            increase = applyFun.get();
        }
        REDIS_UTILS.hashSet(key, hKey, String.valueOf(val));
        return increase;
    }

    public static long increase(String key, String hKey, long val) {
        return REDIS_UTILS.increase(key, hKey, val);
    }


    public static <T> T hashGet(String key, String hKey, Class<T> clazz, Supplier<T> applyFun) {
        Object o = REDIS_UTILS.hashGet(key, hKey);
        if (o != null) {
            return REDIS_UTILS.fromJson(REDIS_UTILS.toJson(o), clazz);
        }
        T apply = applyFun.get();
        if (apply != null) {
            REDIS_UTILS.hashSet(key, hKey, String.valueOf(apply));
        }
        return apply;
    }

    public static void hashSet(String key, String hKey, Object value) {
        REDIS_UTILS.hashSet(key,hKey,value);
    }

}
