package com.thinkifu.origin.commons.interceptor;

import cn.hutool.core.util.StrUtil;
import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.GlobalErrorEnum;
import com.thinkifu.origin.commons.util.Constant;
import com.thinkifu.origin.commons.util.HttpContextUtils;
import com.thinkifu.origin.commons.util.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author dell
 * @Classname AuthorizationInterceptor
 * @Description
 * @Date 2022/08/23 13:37
 * @Created mr_xie
 */
@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    
    @Value(value = "${spring.profiles.active}")
    private String active;
    @Value(value = "${server.servlet.context-path}")
    private String contextPath;
    
    /**
     * token持续时间
     */
    public Long tokenExpireTime;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //放行 OPTION 请求 options请求是浏览器自主发起的一个试探性请求  是浏览器对服务器的支持情况 以及一些资源的试探性请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.debug("放行OPTION请求");
            return true;
        }
        //获取请求中的token 令牌
        String ipAddress = HttpContextUtils.getIpAddress(request);
        judgeTokenValidity(request.getHeader("Authorization"), ipAddress, request);
        return true;
    }

    /**
     * 判断token是否还有效
     *
     * @param token 凭证
     */
    private void judgeTokenValidity(String token, String nowIp, HttpServletRequest request) {
        if (StrUtil.isBlank(token)) {
            if (active.equals("dev")) {
                if (request.getRequestURI().equals(contextPath + "/csrf") || request.getRequestURI().equals(contextPath + "/") || request.getRequestURI().equals(contextPath + "/error")) {
                    return;
                }
            }
            throw new BaseException(GlobalErrorEnum.TOKEN_EMPTY);
        }
        long userId;
        Object userIdObj = redisTemplate.opsForValue().get(token);
        if (userIdObj == null) {
            userIdObj = redisTemplate.opsForValue().get(Constant.GET_SQUEEZED_TOKEN(token));
            if (userIdObj != null) {
                userId = Long.parseLong(userIdObj.toString());
                Object beforeUserIpObj = redisTemplate.opsForValue().get(Constant.GET_USER_IP(userId));
                // 如果目前使用的ip和上一次访问的人的ip不一致 那可能存在多人登录账号 这样是不允许的
                if (beforeUserIpObj != null && !beforeUserIpObj.toString().equals(nowIp)) {
                    // 获取这个用户的token
                    Object obj = redisTemplate.opsForHash().get(Constant.USER_TOKEN, userIdObj.toString());
                    if (obj != null) {
                        // 把现在用的token也删掉 保证别人无法再使用
                        redisTemplate.delete(obj.toString());
                    }
                    // 删除被挤下去的token
                    redisTemplate.delete(Constant.GET_SQUEEZED_TOKEN(token));
                    // 删除用户登录的ip信息
                    redisTemplate.delete(Constant.GET_USER_IP(userId));
                    throw new BaseException(GlobalErrorEnum.TOKEN_NO_EXISTS);
                }
            }
            throw new BaseException(GlobalErrorEnum.TOKEN_NO_EXISTS);
        }
        userId = Long.parseLong(userIdObj.toString());
        redisTemplate.opsForValue().set(Constant.GET_USER_IP(userId), nowIp, tokenExpireTime, TimeUnit.SECONDS);
        redisTemplate.expire(token, tokenExpireTime, TimeUnit.SECONDS);
        UserContext.setUserId(userId);
    }
}
