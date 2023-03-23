package com.thinkifu.origin.commons.util;

/**
 * @author dell
 * @Classname UserContext
 * @Description
 * @Date 2022/08/23 9:43
 * @Created mr_xie
 */
public class UserContext {
    private static ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        currentUserId.set(userId);
    }

    public static Long getUserId() {
        return currentUserId.get();
    }

    public static void remove() {
        currentUserId.remove();
    }
}
