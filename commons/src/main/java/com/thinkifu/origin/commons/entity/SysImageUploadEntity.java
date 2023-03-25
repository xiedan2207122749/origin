package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图片上传记录表
 * 
 * @author mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 14:08:46
 */
@Data
@ApiModel("图片上传记录表")
@TableName("sys_image_upload")
public class SysImageUploadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long id;
	/**
	 * 文件前缀
	 */
	@ApiModelProperty("文件前缀")
    private Long preFileName;
	/**
	 * 文件地址
	 */
	@ApiModelProperty("文件地址")
    private String link;
	/**
	 * 0:未被使用 1:被使用
	 */
	@ApiModelProperty("0:未被使用 1:被使用")
    private Integer useFlag;
	/**
	 * 1: 电脑游戏图片 2:手机游戏图片 3:学习视频
	 */
	@ApiModelProperty("1: 电脑游戏图片 2:手机游戏图片 3:学习视频")
    private Integer type;
	/**
	 * 创建者id
	 */
	@ApiModelProperty("创建者id")
    private Long creatorUserId;
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
    private Integer deleted;

}
