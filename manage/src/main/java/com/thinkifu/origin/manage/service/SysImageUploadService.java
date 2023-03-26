package com.thinkifu.origin.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkifu.origin.commons.entity.SysImageUploadEntity;

import java.util.List;

/**
 * 图片上传记录表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 14:08:46
 */
public interface SysImageUploadService extends IService<SysImageUploadEntity> {
    /**
     * 批量修改为已使用 通过图片链接
     * @param linkList
     */
    void updateBatchUsedByLink(List<String> linkList);

    /**
     * 修改为已使用 通过图片链接
     * @param link
     */
    void updateUsedByLink(String link);

    /**
     * 删除原先的图片
     * @param link
     */
    void removeByLink(String link);
}

