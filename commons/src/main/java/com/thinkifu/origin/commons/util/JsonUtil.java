package com.thinkifu.origin.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.GlobalErrorEnum;

import java.util.List;
import java.util.Map;

/**
 * @author xieDan
 * @Classname JsonUtil
 * @Description
 * @Date 2021/4/30 16:44
 * @Created by mr_xie
 */
public class JsonUtil {
    /**
     *  定义jackson对象
      */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new BaseException(GlobalErrorEnum.OBJECT_TO_JSON_STR_ERROR);
        }
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToObject(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            throw new BaseException(GlobalErrorEnum.JSON_STR_TO_OBJECT_ERROR);
        }
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            // new TypeReference<List<T>>() {
            // }
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            throw new BaseException(GlobalErrorEnum.JSON_STR_TO_LIST_ERROR);
        }
    }
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @return
     */
    public static Map<String, Object> jsonToMap(String jsonData) {
        try {
            return MAPPER.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new BaseException(GlobalErrorEnum.JSON_STR_TO_LIST_ERROR);
        }
    }
}
