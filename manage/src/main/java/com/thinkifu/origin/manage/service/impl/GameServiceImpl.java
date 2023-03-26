package com.thinkifu.origin.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.GameDetailEntity;
import com.thinkifu.origin.commons.entity.GameEntity;
import com.thinkifu.origin.manage.dao.GameDao;
import com.thinkifu.origin.manage.form.SaveGameForm;
import com.thinkifu.origin.manage.form.SearchGameForm;
import com.thinkifu.origin.manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;

/**
 * 游戏表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 10:40:05
 */
@Service("gameService")
public class GameServiceImpl extends ServiceImpl<GameDao, GameEntity> implements GameService {

    @Autowired
    private SysImageUploadService sysImageUploadService;
    @Autowired
    private GameDictService gameDictService;
    @Autowired
    private GameSiteService gameSiteService;
    @Autowired
    private GameImageService gameImageService;
    @Autowired
    private GameDetailService gameDetailService;
    /**
     * 查询游戏表列表
     *
     * @param form
     * @return
     */
    @Override
    public IPage<GameEntity> list(SearchGameForm form) {
        IPage<GameEntity> page = form.getPage();
        this.baseMapper.page(page, form);
        return page;
    }

    /**
     * 添加游戏
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SaveGameForm form) {
        GameEntity gameEntity = BeanUtil.copyProperties(form, GameEntity.class, "contentDescription", "introduction");
        // 1. 保存游戏本体
        this.save(gameEntity);
        // 保存游戏介绍 和游戏内容描述 (之所以分开是因为这两个字段太大 影响之后的效率)
        GameDetailEntity gameDetailEntity = new GameDetailEntity();
        gameDetailEntity.setId(gameEntity.getId());
        gameDetailEntity.setContentDescription(form.getContentDescription());
        gameDetailEntity.setIntroduction(form.getIntroduction());
        gameDetailService.save(gameDetailEntity);
        // 2. 保存游戏类型
        gameDictService.add(form.getDictIdList(), gameEntity.getId());
        // 3. 保存游戏下载地址
        gameSiteService.add(form.getGameSiteList(), gameEntity.getId());
        // 4. 保存游戏图片
        gameImageService.add(form.getImageList(), gameEntity.getId());
        // 5. 把之前标记的文件改成已使用
        form.getImageList().add(gameEntity.getIntroduceImage());
        sysImageUploadService.updateBatchUsedByLink(form.getImageList());
    }

    /**
     * 修改游戏
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SaveGameForm form) {
        GameEntity gameEntity = BeanUtil.copyProperties(form, GameEntity.class);
        String introduceImage = this.getObj(new QueryWrapper<GameEntity>().select("introduce_image").eq("id", gameEntity.getId()).last("limit 1"), Objects::toString);
        // 说明修改了介绍图 需要去把之前上传的照片标记成已删除的状态
        if (introduceImage != null && !introduceImage.equals(gameEntity.getIntroduceImage())) {
            // 删除原先的介绍图片
            sysImageUploadService.removeByLink(introduceImage);
            // 然后把现在的介绍图片标记成已使用
            sysImageUploadService.updateBatchUsedByLink(Collections.singletonList(gameEntity.getIntroduceImage()));
        }
        this.updateById(gameEntity);
        GameDetailEntity gameDetailEntity = new GameDetailEntity();
        gameDetailEntity.setId(gameEntity.getId());
        gameDetailEntity.setIntroduction(form.getIntroduction());
        gameDetailEntity.setContentDescription(form.getContentDescription());
        gameDetailService.updateById(gameDetailEntity);
    }
}
