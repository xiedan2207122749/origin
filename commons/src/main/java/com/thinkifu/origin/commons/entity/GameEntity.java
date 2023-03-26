package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 游戏表
 * 
 * @author mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 10:40:05
 */
@Data
@ApiModel("游戏表")
@TableName("tb_game")
public class GameEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
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
	 * 是否可用 1:可用0:不可用
	 */
	@ApiModelProperty("是否可用 1:可用0:不可用")
    private Integer availableFlag;
	/**
	 * 评分
	 */
	@ApiModelProperty("评分")
    private Float score;
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
	 * 1.是被推荐 0:不是被推荐
	 */
	@ApiModelProperty("1.是被推荐 0:不是被推荐")
    private Integer recommendFlag;
	/**
	 * 
	 */
	@ApiModelProperty("")
    private LocalDateTime createTime;
	/**
	 * 
	 */
	@ApiModelProperty("")
    private LocalDateTime updateTime;
	/**
	 * 
	 */
	@ApiModelProperty("")
	@TableLogic
    private Integer deleted;

	@TableField(exist = false)
	@ApiModelProperty("字典值")
	private List<String> dictValueList;

	/**
	 * 简介
	 */
	@TableField(exist = false)
	@ApiModelProperty("简介")
	private String introduction;
	/**
	 * 内容描述
	 */
	@TableField(exist = false)
	@ApiModelProperty("内容描述")
	private String contentDescription;

}
