package com.thinkifu.origin.manage.controller;

import com.thinkifu.origin.commons.entity.SysImageUploadEntity;
import com.thinkifu.origin.commons.util.EnumUtil;
import com.thinkifu.origin.commons.util.FileUtil;
import com.thinkifu.origin.commons.util.Result;
import com.thinkifu.origin.commons.util.UserContext;
import com.thinkifu.origin.manage.enums.FileFolderEnum;
import com.thinkifu.origin.manage.service.SysImageUploadService;
import com.thinkifu.origin.manage.service.UploadService;
import com.thinkifu.origin.manage.util.SnowFlakeGenerateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * 图片上传记录表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 14:08:46
 */
@Api(tags="图片上传记录表")
@RestController
@Validated
@RequestMapping("/sysImageUpload")
public class SysImageUploadController {

    @Autowired
    private SysImageUploadService sysImageUploadService;

    @Resource(name = "serverUploadService")
    private UploadService uploadService;

    @ApiOperation("上传图片返回文件名称")
    @PostMapping(value = "/uploadImage")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "需要上传的图片", required = true, dataType = "multipartFile", dataTypeClass = MultipartFile.class),
        @ApiImplicitParam(name = "fileType", value = "文件类型 1.电脑游戏介绍图 2.电脑游戏详细图 3.手游介绍图 4.手游详细图", required = true, dataType = "int", dataTypeClass = Integer.class)
    })
    public Result<String> uploadImage(MultipartFile file, Integer fileType) {
        FileUtil.authenticationImageNotNullAndLegal(file);
        String fileName = SnowFlakeGenerateUtil.getSnowFlakeId();
        String fileSite = uploadService.uploadImage(file, fileName, EnumUtil.getEnum(fileType, FileFolderEnum.class).getStrValue());
        SysImageUploadEntity entity = new SysImageUploadEntity();
        entity.setLink(fileSite);
        entity.setCreatorUserId(UserContext.getUserId());
        entity.setType(fileType);
        sysImageUploadService.save(entity);
        return Result.ok(fileSite);
    }
}
