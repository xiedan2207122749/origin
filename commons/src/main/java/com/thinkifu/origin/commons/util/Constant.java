package com.thinkifu.origin.commons.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author dell
 * @Classname Constant
 * @Description
 * @Date 2022/08/16 10:43
 * @Created mr_xie
 */
@Component
public class Constant {
    /**
     * 用户token
     */
    public static final String USER_TOKEN = "userToken";
    
    /**
     * 如果没有这个key或者key的时间过期了
     */
    public static final long KEY_NOT_HAVE_EXPIRE = -2L;
    /**
     * 替换掉真实的路径
     */
    public static final String REPLACE_UP_LOAD = "download";
    
    /**
     * ip地址
     */
    public static String SERVER_IP;
    @Value("${file.upload.serverIp}")
    public String serverIp;
    /**
     * 文件前缀
     */
    public static String FILE_PREFIX;
    @Value("${file.upload.prefix}")
    public String filePrefix;
    @PostConstruct
    public void init() {
        SERVER_IP = serverIp;
        FILE_PREFIX = filePrefix;
    }
    
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
