package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 轮播图
 * 
 * @author 
 * @email 
 * @date 2022-11-06 20:35:52
 */
@Data
@ApiModel("轮播图")
@TableName("tb_slideshow")
public class SlideshowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *  
	 */
	@TableId
	@ApiModelProperty("")
    private Long id;
	/**
	 * 图片
	 */
	@ApiModelProperty("图片")
    private String image;
	/** 顺序 */
	@ApiModelProperty("顺序")
	private Integer orderNum;
	/** 游戏id */
	@ApiModelProperty("游戏id")
	private Long gameId;
	/**
	 *
	 */
	@ApiModelProperty("")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

}
