package com.thinkifu.origin.commons.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author xieDan
 * @Classname LoginSecurityCheckUtil
 * @Description 登录的时候做一个安全检测
 * @Date 2022/8/29 11:09
 * @Created by mr_xie
 */
public class LoginSecurityCheckUtil {

    /**
     * 关于一些ip的安全检测
     *
     * @param userId
     * @param tokenExpireTime
     * @return 返回token
     */
    public static String check(Long userId, Long tokenExpireTime, RedisTemplate<String, Object> redisTemplate) {
        String nowToken = TokenGenerator.generateValue();
        // 先去把之前用的token获取到
        Object obj = redisTemplate.opsForHash().get(Constant.USER_TOKEN, userId.toString());
        String userIpKey = Constant.GET_USER_IP(userId);
        String nowIp = HttpContextUtils.getIpAddress();
        if (obj != null) {
            String lastToken = obj.toString();
            // 如果删除之前的token成功了的话 说明上一个用的token还没过期
            if (redisTemplate.delete(lastToken)) {
                Object ipObj = redisTemplate.opsForValue().get(userIpKey);
                if (ipObj != null && !ipObj.toString().equals(nowIp)) {
                    // 于是有了一个被挤下去的操作
                    redisTemplate.opsForValue().set(Constant.GET_SQUEEZED_TOKEN(lastToken), userId, tokenExpireTime, TimeUnit.SECONDS);
                }
            }
        }
        // 把目前用户用的token放到 最后一次使用的token里面去
        redisTemplate.opsForHash().put(Constant.USER_TOKEN, userId.toString(), nowToken);
        redisTemplate.opsForValue().set(userIpKey, nowIp, tokenExpireTime, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(nowToken, userId, tokenExpireTime, TimeUnit.SECONDS);
        return nowToken;
    }
}
