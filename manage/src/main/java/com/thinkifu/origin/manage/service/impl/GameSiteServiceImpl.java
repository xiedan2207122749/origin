package com.thinkifu.origin.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.GameSiteEntity;
import com.thinkifu.origin.manage.dao.GameSiteDao;
import com.thinkifu.origin.manage.form.SaveGameForm;
import com.thinkifu.origin.manage.service.GameSiteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 游戏下载地址
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */

@Service("gameSiteService")
public class GameSiteServiceImpl extends ServiceImpl<GameSiteDao, GameSiteEntity> implements GameSiteService {

    /**
     * 保存游戏下载地址
     *
     * @param gameSiteList
     * @param gameId
     */
    @Override
    public void add(List<SaveGameForm.GameSiteForm> gameSiteList, Long gameId) {
        List<GameSiteEntity> gameSiteEntityList = gameSiteList.parallelStream().map(item -> {
            GameSiteEntity gameSiteEntity = new GameSiteEntity();
            gameSiteEntity.setGameId(gameId);
            gameSiteEntity.setLink(item.getLink());
            gameSiteEntity.setPlatform(item.getPlatform());
            return gameSiteEntity;
        }).collect(Collectors.toList());
        this.saveBatch(gameSiteEntityList);
    }
}
