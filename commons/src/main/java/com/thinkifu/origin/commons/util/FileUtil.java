package com.thinkifu.origin.commons.util;

import com.thinkifu.origin.commons.exception.AppErrorEnum;
import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.ManageErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;

/**
 * @author dell
 * @Classname FileUtil
 * @Description 文件util
 * @Date 2020/12/2 10:09
 * @Created by mr_xie
 */
@Slf4j
public class FileUtil {
    
    private final static HashSet<String> IMAGE_SET = new HashSet<String>(32) {
        private static final long serialVersionUID = 1L;
        {
            add("bmp");
            add("dib");
            add("gif");
            add("jfif");
            add("jpe");
            add("jpeg");
            add("jpg");
            add("png");
            add("tif");
            add("tiff");
            add("ico");
            add("BMP");
            add("DIB");
            add("GIF");
            add("JFIF");
            add("JPE");
            add("JPEG");
            add("JPG");
            add("PNG");
            add("TIF");
            add("TIFF");
            add("ICO");
        }
    };

    /**
     * 验证图片不为空并且合法
     * @param multipartFile
     */
    public static void authenticationImageNotNullAndLegal(MultipartFile multipartFile) {
        // 如果文件为空 直接返回空
        if (multipartFile == null || multipartFile.getSize() == 0) {
            throw new BaseException(ManageErrorEnum.FILE_NOT_NULL);
        }
        String suffix = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!FileUtil.IMAGE_SET.contains(suffix)) {
            throw new BaseException(AppErrorEnum.MUST_IS_PICTURE);
        }
    }
}
