package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 游戏类型表
 * 
 * @author mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 13:22:31
 */
@Data
@ApiModel("游戏类型表")
@TableName("tb_game_dict")
public class GameDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 游戏id
	 */
	@TableId
	@ApiModelProperty("游戏id")
    private Long gameId;
	/**
	 * 字典id
	 */
	@ApiModelProperty("字典id")
    private Long dictId;
	/**
	 * 
	 */
	@ApiModelProperty("")
    private LocalDateTime createTime;

}
