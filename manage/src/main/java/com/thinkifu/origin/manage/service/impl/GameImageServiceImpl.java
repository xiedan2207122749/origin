package com.thinkifu.origin.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.GameImageEntity;
import com.thinkifu.origin.manage.dao.GameImageDao;
import com.thinkifu.origin.manage.form.SaveGameImageForm;
import com.thinkifu.origin.manage.service.GameImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

    /**
     * 保存游戏图片
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SaveGameImageForm form) {
        List<GameImageEntity> beforeImageEntityList = this.list(new QueryWrapper<GameImageEntity>().select("id, image").eq("game_id", form.getGameId()));
        Map<String, Long> map = beforeImageEntityList.parallelStream().collect(Collectors.toMap(GameImageEntity::getImage, GameImageEntity::getId, (item, item2) -> item2));

        List<String> imageList = new ArrayList<>(10);
        for (int i = 0; i < form.getImageList().size(); i++) {
            // 存在的就把map里面删掉 最后剩下来没被删除的
            if (map.containsKey(form.getImageList().get(i))) {
                map.remove(form.getImageList().get(i));
            } else {
                imageList.add(form.getImageList().get(i));
            }
        }
        if (imageList.size() >= 0) {
            add(imageList, form.getGameId());
        }
        Collection<Long> values = map.values();
        if (values.size() >= 0) {
            this.removeByIds(values);
        }
    }
}
