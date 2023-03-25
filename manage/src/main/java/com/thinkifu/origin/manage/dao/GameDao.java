package com.thinkifu.origin.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.thinkifu.origin.commons.entity.GameEntity;
import com.thinkifu.origin.manage.form.SearchGameForm;
import org.apache.ibatis.annotations.Mapper;

/**
 * 游戏表
 * 
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 10:40:05
 */
@Mapper
public interface GameDao extends BaseMapper<GameEntity> {
    /**
     * 查询游戏表列表
     * @param page
     * @param form
     * @return
     */
    IPage<GameEntity> page(IPage<GameEntity> page, SearchGameForm form);
}
