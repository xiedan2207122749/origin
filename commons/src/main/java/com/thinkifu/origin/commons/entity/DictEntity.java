package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类型表
 * 
 * @author mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-24 22:52:31
 */
@Data
@ApiModel("类型表")
@TableName("tb_dict")
public class DictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("")
    private Long id;
	/**
	 * 值
	 */
	@ApiModelProperty("值")
    private String value;
	/**
	 * 1. 电脑游戏 2.安卓游戏 3. 免费学习
	 */
	@ApiModelProperty("1. 电脑游戏 2.安卓游戏 3. 免费学习")
    private Integer type;
	/**
	 * 
	 */
	@ApiModelProperty("")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
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
