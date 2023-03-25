package com.thinkifu.origin.manage.util;


import com.thinkifu.origin.manage.config.SnowflakeIdWorker;

/**
 * @Classname SnowFlakeGenerateUtil
 * @Description 雪花id生成器
 * @Date 2020/12/2 17:15
 * @Created by mr_xie
 */
public class SnowFlakeGenerateUtil {
    private static SnowflakeIdWorker snowflakeIdWorker;

    static {
        snowflakeIdWorker = new SnowflakeIdWorker(0L, 0L);
    }

    public static String getSnowFlakeId() {
        return snowflakeIdWorker.generateNextId();
    }
}
