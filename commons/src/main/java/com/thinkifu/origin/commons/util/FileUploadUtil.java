package com.thinkifu.origin.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * @author dell
 * @Classname FileUploadUtil
 * @Description
 * @Date 2020/12/2 10:09
 * @Created by mr_xie
 */
@Slf4j
public class FileUploadUtil {
    
    private final static HashSet<String> SET = new HashSet<String>(32) {
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
     * 不是图片会抛异常
     * @param multipartFile
     */
    public static void onlyPicture(MultipartFile multipartFile) {
        String suffix = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!FileUploadUtil.SET.contains(suffix)) {
            // throw new BaseException(AppErrorEnum.MUST_IS_PICTURE);
        }
    }
    
    /**
     * 判断文件是否为空
     * @param multipartFile
     * @return true 为空 false 不为空
     */
    public static boolean determineWhetherTheFileIsEmpty(MultipartFile multipartFile) {
        return multipartFile == null || multipartFile.getSize() == 0;
    }
    
    public static String fileUploadImage(MultipartFile multipartFile, String filePath) {
        // 如果文件为空 直接返回空
        if (determineWhetherTheFileIsEmpty(multipartFile)) {
            return null;
        }
        onlyPicture(multipartFile);
        return fileUpload(multipartFile, filePath);
    }
    
    /**
     * 客户上传文件
     * @param multipartFile 客户上传的位置
     * @param filePath 文件存放位置
     * @return 文件下载位置
     */
    public static String fileUpload(MultipartFile multipartFile, String filePath) {
        LocalDate now = LocalDate.now();
        StringBuilder builder = new StringBuilder(Constant.FILE_PREFIX);
        builder.append(filePath).append("/").append(now.getYear()).append("/").append(now.getMonthValue()).append("/").append(now.getDayOfMonth());
        builder.append("/").append(TokenGenerator.generateValue());
        builder.append(multipartFile.getOriginalFilename(), multipartFile.getOriginalFilename().lastIndexOf("."), multipartFile.getOriginalFilename().length());
        return fileUploadImpl(multipartFile, builder.toString());
    }

    private static String fileUploadImpl(MultipartFile multipartFile, String filename) {
        File targetFile = new File(filename);
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
            filename = filename.replace(Constant.FILE_PREFIX, Constant.REPLACE_UP_LOAD);
            filename = Constant.SERVER_IP + filename + "?originalFileName=" + URLEncoder.encode(multipartFile.getOriginalFilename(), "utf-8").replace("+","%20");
            return filename;
        } catch (IllegalStateException e) {
            log.error("multipartFile.transferTo, IllegalStateException", e);
        } catch (IOException e) {
            log.error("multipartFile.transferTo, IOException", e);
        }
        return null;
    }
    
    public static void deleteFile(String filePosition) {
        filePosition = filePosition.substring(Constant.SERVER_IP.length(), filePosition
            .indexOf("?originalFileName="))
            .replace(Constant.REPLACE_UP_LOAD, Constant.FILE_PREFIX);
        File file = new File(filePosition);
        if (file.exists() && file.isFile()) {
            file.delete();
            log.info("文件删除成功{}", filePosition);
        }
    }
}
