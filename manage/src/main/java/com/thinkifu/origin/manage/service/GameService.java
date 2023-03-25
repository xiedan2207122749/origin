package com.thinkifu.origin.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkifu.origin.commons.entity.GameEntity;
import com.thinkifu.origin.manage.form.SaveGameForm;
import com.thinkifu.origin.manage.form.SearchGameForm;

/**
 * 游戏表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 10:40:05
 */
public interface GameService extends IService<GameEntity> {

    /**
     * 查询游戏表列表
     * @param form
     * @return
     */
    IPage<GameEntity> list(SearchGameForm form);

    /**
     * 添加游戏
     * @param form
     */
    void add(SaveGameForm form);
}

