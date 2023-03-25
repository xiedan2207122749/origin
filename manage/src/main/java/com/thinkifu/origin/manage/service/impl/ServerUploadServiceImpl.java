package com.thinkifu.origin.manage.service.impl;

import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.ManageErrorEnum;
import com.thinkifu.origin.commons.util.Constant;
import com.thinkifu.origin.manage.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;

/**
 * @author xieDan
 * @Classname ServerUploadServiceImpl
 * @Description 文件上传
 * @Date 2023/3/25 14:22
 * @Created by mr_xie
 */
@Service("serverUploadService")
@Slf4j
public class ServerUploadServiceImpl implements UploadService {

    @Value("${file.upload.server.serverIp}")
    public String serverIp;
    @Value("${file.upload.server.prefix}")
    public String filePrefix;
    /**
     * 上传图片
     * @param multipartFile 需要上传的图片
     * @param finalFileName 最终文件名称
     * @param folderSite 文件夹位置
     * @return
     */
    @Override
    public String uploadImage(MultipartFile multipartFile, String finalFileName, String folderSite) {
        LocalDate now = LocalDate.now();
        StringBuilder builder = new StringBuilder(filePrefix);
        builder.append("/origin/").append(now.getYear()).append("/").append(now.getMonthValue()).append("/").append(now.getDayOfMonth()).append("/");
        builder.append(folderSite).append("/").append(finalFileName);
        builder.append(multipartFile.getOriginalFilename(), multipartFile.getOriginalFilename().lastIndexOf("."), multipartFile.getOriginalFilename().length());

        String filePath = builder.toString();
        File targetFile = new File(filePath);
        //保存文件
        try {
            File parentFile = targetFile.getParentFile();
            if (!parentFile.exists()) {
                // 之所以不直接用mkdirs创建这个路径所有文件 是因为  mkdirs创建完 全是目录 只有执行完{targetFile.createNewFile();}
                // 之后才会把最后一个目录改成文件 但是上传了的如果是txt文件 而且txt文件是空的 这样执行{targetFile.createNewFile();}会抛异常 所以用这种创建方式先创建文件所有父级目录
                // 然后再用{targetFile.createNewFile()}创建对应的文件
                parentFile.mkdirs();
            }
            targetFile.createNewFile();
            multipartFile.transferTo(targetFile);
            filePath = filePath.replace(filePrefix, Constant.REPLACE_UP_LOAD);
            filePath = serverIp + filePath + "?originalFileName=" + URLEncoder.encode(multipartFile.getOriginalFilename(), "utf-8").replace("+","%20");
            return filePath;
        } catch (IllegalStateException e) {
            log.error("multipartFile.transferTo, IllegalStateException", e);
        } catch (IOException e) {
            log.error("multipartFile.transferTo, IOException", e);
        }
        throw new BaseException(ManageErrorEnum.FILE_UPLOAD_ERROR);
    }

    /**
     * 删除文件
     *
     * @param filePosition 需要删除的文件位置
     * @return
     */
    @Override
    public boolean removeFile(String filePosition) {
        filePosition = filePosition.substring(serverIp.length(), filePosition
            .indexOf("?originalFileName="))
            .replace(Constant.REPLACE_UP_LOAD, filePrefix);
        File file = new File(filePosition);
        if (file.exists() && file.isFile()) {
            return file.delete();
        }
        return true;
    }
}
