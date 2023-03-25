package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 游戏下载地址
 * 
 * @author mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */
@Data
@ApiModel("游戏下载地址")
@TableName("tb_game_site")
public class GameSiteEntity implements Serializable {
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
	 * 游戏平台
	 */
	@ApiModelProperty("游戏平台")
    private String platform;
	/**
	 * 下载地址
	 */
	@ApiModelProperty("下载地址")
    private String link;
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
