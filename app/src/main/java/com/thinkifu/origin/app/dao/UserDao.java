package com.thinkifu.origin.app.dao;

import com.thinkifu.origin.commons.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 * 
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-26 15:24:18
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    /**
     * 统计是否存在
     * @param fieldValue
     * @param field
     * @return
     */
    int countByField(@Param("fieldValue") String fieldValue, @Param("field") String field);
}
