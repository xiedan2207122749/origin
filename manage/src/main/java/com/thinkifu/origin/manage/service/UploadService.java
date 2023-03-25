package com.thinkifu.origin.manage.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xieDan
 * @Classname UploadService
 * @Description 文件上传
 * @Date 2023/3/25 14:14
 * @Created by mr_xie
 */
public interface UploadService {

    /**
     * 上传图片
     * @param multipartFile 需要上传的图片
     * @param finalFileName 最终文件名称
     * @param folderSite 文件夹位置
     * @return
     */
    String uploadImage(MultipartFile multipartFile, String finalFileName, String folderSite);

    /**
     * 删除文件
     * @param filePosition 需要删除的文件位置
     * @return
     */
    boolean removeFile(String filePosition);
}
