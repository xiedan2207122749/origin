package com.thinkifu.origin.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkifu.origin.commons.entity.GameImageEntity;

import java.util.List;

/**
 * 游戏图片
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */
public interface GameImageService extends IService<GameImageEntity> {
    /**
     * 保存游戏图片
     * @param imageList
     * @param gameId
     */
    void add(List<String> imageList, Long gameId);
}

