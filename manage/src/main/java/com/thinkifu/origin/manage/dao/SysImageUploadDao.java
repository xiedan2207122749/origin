package com.thinkifu.origin.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thinkifu.origin.commons.entity.SysImageUploadEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片上传记录表
 * 
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 14:08:46
 */
@Mapper
public interface SysImageUploadDao extends BaseMapper<SysImageUploadEntity> {
}
