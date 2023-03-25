package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;

/**
 * 游戏图片
 * 
 * @author mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */
@Data
@ApiModel("游戏图片")
@TableName("tb_game_image")
public class GameImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long id;
	/**
	 * 游戏id
	 */
	@ApiModelProperty("游戏id")
    private Long gameId;
	/**
	 * 图片
	 */
	@ApiModelProperty("图片")
    private String image;
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
