package com.thinkifu.origin.manage.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author xieDan
 * @Classname SaveGameForm
 * @Description 保存游戏form
 * @Date 2023/3/25 13:17
 * @Created by mr_xie
 */
@Data
@ApiModel("保存游戏form")
public class SaveGameForm {
    /**
     *
     */
    @ApiModelProperty("")
    private Long id;
    /**
     * 中文名称
     */
    @ApiModelProperty("中文名称")
    private String name;
    /**
     * 英文名称
     */
    @ApiModelProperty("英文名称")
    private String englishName;
    /**
     * 发售时间
     */
    @ApiModelProperty("发售时间")
    private LocalDate releaseDate;
    /**
     * 发行厂商
     */
    @ApiModelProperty("发行厂商")
    private String releaseCompany;
    /**
     * 语言
     */
    @ApiModelProperty("语言")
    private String language;
    /**
     * 年龄分段
     */
    @ApiModelProperty("年龄分段")
    private String ageDivision;
    /**
     * 别名
     */
    @ApiModelProperty("别名")
    private String alias;
    /**
     * 游戏平台
     */
    @ApiModelProperty("游戏平台")
    private String platform;
    /**
     * 介绍图
     */
    @ApiModelProperty("介绍图")
    private String introduceImage;
    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String introduction;
    /**
     * 内容描述
     */
    @ApiModelProperty("内容描述")
    private String contentDescription;
    /**
     * 文件大小
     */
    @ApiModelProperty("文件大小")
    private Float fileSize;
    /**
     * 1.电脑游戏 2.手机游戏
     */
    @ApiModelProperty("1.电脑游戏 2.手机游戏")
    private Integer type;
    /**
     * 是否可用 1:可用 0: 不可用
     */
    @ApiModelProperty("是否可用 1:可用 0: 不可用")
    private Integer availableFlag;
    /**
     * 1.是被推荐 0:不是被推荐
     */
    @ApiModelProperty("1.是被推荐 0:不是被推荐")
    private Integer recommendFlag;

    @ApiModelProperty("字典id")
    private List<Long> dictIdList;

    @ApiModelProperty("游戏详细图")
    private List<String> imageList;

    @ApiModelProperty("游戏下载地址")
    private List<GameSiteForm> gameSiteList;

    @Data
    @ApiModel("保存游戏下载地址form")
    public static class GameSiteForm {
        /**
         * 游戏平台
         */
        @ApiModelProperty("游戏平台")
        private String platform;
        /**
         * 下载地址
         */
        @ApiModelProperty("下载地址")
        private String link;
    }
}
