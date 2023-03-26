package com.thinkifu.origin.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkifu.origin.commons.entity.SlideshowEntity;
import com.thinkifu.origin.manage.form.SaveSlideshowForm;

/**
 * @author xieDan
 * @Classname SlideshowService
 * @Description 轮播图
 * @Date 2022/11/6 18:18
 * @Created by mr_xie
 */
public interface SlideshowService extends IService<SlideshowEntity> {
    /**
     * 保存轮播图
     * @param form
     */
    void updateOrSave(SaveSlideshowForm form);

    /**
     * 删除轮播
     * @param id
     */
    void delete(Long id);
}
