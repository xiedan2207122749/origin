package com.thinkifu.origin.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.GameDictEntity;
import com.thinkifu.origin.manage.dao.GameDictDao;
import com.thinkifu.origin.manage.form.SaveGameDictForm;
import com.thinkifu.origin.manage.service.GameDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 游戏类型表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */

@Service("gameDictService")
public class GameDictServiceImpl extends ServiceImpl<GameDictDao, GameDictEntity> implements GameDictService {

    /**
     * 保存游戏字典信息
     *
     * @param dictIdList
     * @param gameId
     */
    @Override
    public void add(List<Long> dictIdList, Long gameId) {
        List<GameDictEntity> gameDictEntityList = dictIdList.parallelStream().map(item -> {
            GameDictEntity gameDictEntity = new GameDictEntity();
            gameDictEntity.setGameId(gameId);
            gameDictEntity.setDictId(item);
            return gameDictEntity;
        }).collect(Collectors.toList());
        this.saveBatch(gameDictEntityList);
    }

    /**
     * 修改游戏字典
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SaveGameDictForm form) {
        this.remove(new QueryWrapper<GameDictEntity>().eq("game_id", form.getGameId()));
        add(form.getDictIdList(), form.getGameId());
    }
}
