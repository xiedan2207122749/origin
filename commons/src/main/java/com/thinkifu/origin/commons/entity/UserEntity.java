package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 * 
 * @author mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-26 15:47:21
 */
@Data
@ApiModel("用户表")
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty("")
    private Long id;
	/**
	 * 账号
	 */
	@ApiModelProperty("账号")
    private String account;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
    private String password;
	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
    private String username;
	/**
	 * 1: 男 2:女 3:未知
	 */
	@ApiModelProperty("1: 男 2:女 3:未知")
    private Integer sex;
	/**
	 * 电话
	 */
	@ApiModelProperty("电话")
    private Long phone;
	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
    private String email;
	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
    private String remark;
	/**
	 * 0.不是vip 1.是vip
	 */
	@ApiModelProperty("0.不是vip 1.是vip")
    private Integer vipFlag;
	/**
	 * 最后登录时间
	 */
	@ApiModelProperty("最后登录时间")
    private LocalDateTime lastActiveDate;
	/**
	 * 1:可用 0:不可用
	 */
	@ApiModelProperty("1:可用 0:不可用")
    private Integer availableFlag;
	/**
	 * 用户来源 1:未知 2: 抖音..
	 */
	@ApiModelProperty("用户来源 1:未知 2: 抖音..")
    private Integer userSource;
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
