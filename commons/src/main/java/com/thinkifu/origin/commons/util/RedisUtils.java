package com.thinkifu.origin.commons.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public boolean setAdd(String key, Object o) {
        return Long.valueOf(1L).equals(setOperations.add(key, o));
    }

    public Long setCount(String key) {
        return setOperations.size(key);
    }

    public boolean setExist(String key, Object value) {
        return Boolean.TRUE.equals(setOperations.isMember(key, value));
    }

    /**
     * Object转成JSON数据
     */
    public String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JsonUtil.objectToJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        return JsonUtil.jsonToObject(json, clazz);
    }

    public Long increase(String key, String hKey, long val) {
        return hashOperations.increment(key, hKey, val);
    }

    public void hashSet(String key, String hKey, Object val) {
        hashOperations.put(key, hKey, val);
    }

    public Object hashGet(String key, String hKey) {
        return hashOperations.get(key, hKey);
    }
}
