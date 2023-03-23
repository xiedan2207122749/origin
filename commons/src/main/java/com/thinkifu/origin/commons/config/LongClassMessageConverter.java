package com.thinkifu.origin.commons.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * @author xieDan
 * @Classname LongClassMessageConverter
 * @Description
 * @Date 2021/8/2 17:44
 * @Created by mr_xie
 */
@Configuration
public class LongClassMessageConverter implements InitializingBean {
    
    @Resource
    ObjectMapper objectMapper;
    
    @Override
    public void afterPropertiesSet() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        /// 暂时放弃对小long的转换，约定与前端交互数据时，大Long全部转换成字符串
        // simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
    }
}