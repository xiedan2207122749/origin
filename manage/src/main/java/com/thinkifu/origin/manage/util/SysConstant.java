package com.thinkifu.origin.manage.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xieDan
 * @Classname SysConstant
 * @Description
 * @Date 2022/8/29 11:04
 * @Created by mr_xie
 */
@Component
public class SysConstant {

    
    @PostConstruct
    public void init() {

    }
    
    /**
     * 后台 token有效时间 (秒为单位) 30分钟有效期
     */
    public static final long TOKEN_EXPIRE_TIME = 3600 * 24 * 15;
    
    /**
     * 验证码图片有效时间
     */
    public static final long CAPTCHA_EXPIRE_TIME = 300;
}
