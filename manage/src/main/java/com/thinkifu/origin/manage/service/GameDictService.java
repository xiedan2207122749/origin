package com.thinkifu.origin.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkifu.origin.commons.entity.GameDictEntity;

import java.util.List;

/**
 * 游戏类型表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */
public interface GameDictService extends IService<GameDictEntity> {
    /**
     * 保存游戏字典信息
     * @param dictIdList
     * @param gameId
     */
    void add(List<Long> dictIdList, Long gameId);
}

