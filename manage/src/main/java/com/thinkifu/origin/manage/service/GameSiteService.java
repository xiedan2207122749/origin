package com.thinkifu.origin.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkifu.origin.commons.entity.GameSiteEntity;
import com.thinkifu.origin.manage.form.SaveGameForm;

import java.util.List;

/**
 * 游戏下载地址
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */
public interface GameSiteService extends IService<GameSiteEntity> {
    /**
     * 保存游戏下载地址
     * @param gameSiteList
     * @param gameId
     */
    void add(List<SaveGameForm.GameSiteForm> gameSiteList, Long gameId);
}

