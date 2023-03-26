package com.thinkifu.origin.manage.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xieDan
 * @Classname SaveSlideshowForm
 * @Description 保存轮播提form
 * @Date 2022/4/7 22:20
 * @Created by mr_xie
 */
@Data
@ApiModel("保存轮播提form")
public class SaveSlideshowForm {


    @ApiModelProperty("")
    private Long id;
    /** 顺序 */
    @ApiModelProperty("顺序")
    private Integer orderNum;
    /**
     * 介绍图片文件
     */
    @ApiModelProperty("介绍图片文件")
    private String image;
    /**
     * 游戏id
     */
    @ApiModelProperty("游戏id")
    private Long gameId;
}
