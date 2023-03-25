package com.thinkifu.origin.manage.config;

import com.thinkifu.origin.commons.interceptor.AuthorizationInterceptor;
import com.thinkifu.origin.manage.util.SysConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Classname WebMvcConfig
 * @Description
 * @Date 2020/10/14 13:39
 * @Created mr_xie
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @PostConstruct
    public void init () {
        authorizationInterceptor.tokenExpireTime = SysConstant.TOKEN_EXPIRE_TIME;
    }
    
    @Resource
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/webjars/**", "/druid/**"
                    , "/v2/**", "/swagger-ui.html/**", "/swagger-resources/**"
                );
    }

}
