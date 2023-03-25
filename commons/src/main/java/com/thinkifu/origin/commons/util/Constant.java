package com.thinkifu.origin.commons.util;

/**
 * @author dell
 * @Classname Constant
 * @Description
 * @Date 2022/08/16 10:43
 * @Created mr_xie
 */

public class Constant {
    /**
     * 用户token
     */
    public static final String USER_TOKEN = "userToken";
    /**
     * 替换掉真实的路径
     */
    public static final String REPLACE_UP_LOAD = "download";
    /**
     * 用户ip
     */
    public static String GET_USER_IP(Long userId) {
        return "userIp" + userId;
    }
    /**
     * 获取被挤下去的人的token
     * @param token
     * @return
     */
    public static String GET_SQUEEZED_TOKEN(String token) {
        return "squeezedToken" + token;
    }
}
