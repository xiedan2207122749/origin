package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 游戏详情表
 * 
 * @author mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-26 19:18:10
 */
@Data
@ApiModel("游戏详情表")
@TableName("tb_game_detail")
public class GameDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 游戏id
	 */
	@TableId(type = IdType.INPUT)
	@ApiModelProperty("游戏id")
    private Long id;
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

}
