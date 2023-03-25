package com.thinkifu.origin.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.SysImageUploadEntity;
import com.thinkifu.origin.manage.dao.SysImageUploadDao;
import com.thinkifu.origin.manage.service.SysImageUploadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 图片上传记录表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 14:08:46
 */

@Service("sysImageUploadService")
public class SysImageUploadServiceImpl extends ServiceImpl<SysImageUploadDao, SysImageUploadEntity> implements SysImageUploadService {

    /**
     * 批量修改为已使用 通过图片链接
     *
     * @param linkList
     */
    @Override
    public void updateBatchUsedByLink(List<String> linkList) {
        List<Long> preFileNameList = linkList.parallelStream().map(item -> Long.parseLong((String) item.subSequence(item.lastIndexOf("/") + 1, item.lastIndexOf(".")))).collect(Collectors.toList());
        this.baseMapper.updateBatchUsedByPreFileName(preFileNameList);
    }
}
