package com.thinkifu.origin.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.GameImageEntity;
import com.thinkifu.origin.manage.dao.GameImageDao;
import com.thinkifu.origin.manage.service.GameImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 游戏图片
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */

@Service("gameImageService")
public class GameImageServiceImpl extends ServiceImpl<GameImageDao, GameImageEntity> implements GameImageService {

    /**
     * 保存游戏图片
     *
     * @param imageList
     * @param gameId
     */
    @Override
    public void add(List<String> imageList, Long gameId) {
        List<GameImageEntity> gameImageEntityList = imageList.parallelStream().map(item -> {
            GameImageEntity gameImageEntity = new GameImageEntity();
            gameImageEntity.setGameId(gameId);
            gameImageEntity.setImage(item);
            return gameImageEntity;
        }).collect(Collectors.toList());
        this.saveBatch(gameImageEntityList);
    }
}
