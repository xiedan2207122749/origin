package com.thinkifu.origin.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.SlideshowEntity;
import com.thinkifu.origin.manage.dao.SlideshowDao;
import com.thinkifu.origin.manage.form.SaveSlideshowForm;
import com.thinkifu.origin.manage.service.SlideshowService;
import com.thinkifu.origin.manage.service.SysImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author xieDan
 * @Classname SlideshowServiceImpl
 * @Description
 * @Date 2022/11/6 18:19
 * @Created by mr_xie
 */
@Service("slideshowService")
public class SlideshowServiceImpl extends ServiceImpl<SlideshowDao, SlideshowEntity> implements SlideshowService {

    @Autowired
    private SysImageUploadService sysImageUploadService;

    /**
     * 保存轮播图
     *
     * @param form
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrSave(SaveSlideshowForm form) {
        SlideshowEntity entity = BeanUtil.copyProperties(form, SlideshowEntity.class);
        if (entity.getId() == null) {
            sysImageUploadService.updateUsedByLink(form.getImage());
            this.save(entity);
        } else {
            // 如果介绍图片不为空 说明这个人换了介绍图片 需要把之前的图片删掉
            String beforeImage = this.getObj(new QueryWrapper<SlideshowEntity>().select("image").eq("id", entity.getId()).last("limit 1"), Objects::toString);
            if (!beforeImage.equals(form.getImage())) {
                sysImageUploadService.updateUsedByLink(form.getImage());
                sysImageUploadService.removeByLink(beforeImage);
            }
            this.update()
                .set("image", entity.getImage())
                .set("order_num", entity.getOrderNum())
                .set("game_id", entity.getGameId())
                .eq("id", entity.getId())
                .last("limit 1")
                .update();
        }
    }

    /**
     * 删除轮播
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        // 如果介绍图片不为空 说明这个人换了介绍图片 需要把之前的图片删掉
        String beforeImage = this.getObj(new QueryWrapper<SlideshowEntity>().select("image").eq("id", id).last("limit 1"), Objects::toString);
        if (StrUtil.isNotBlank(beforeImage)) {
            sysImageUploadService.removeByLink(beforeImage);
        }
        this.removeById(id);
    }
}
