package com.thinkifu.origin.commons.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xieDan
 * @Classname SysUserEntity
 * @Description 管理后台用户
 * @Date 2023/3/23 17:19
 * @Created by mr_xie
 */
@Data
@ApiModel("管理后台用户")
@TableName("sys_user")
public class SysUserEntity implements Serializable {
    public static final long serialVersionUID = 1L;
    
    @ApiModelProperty
    private Long id;
    /** 账号 */
    @ApiModelProperty("账号")
    private String account;
    /** 密码 */
    @ApiModelProperty("密码")
    private String password;
    /** 用户名 */
    @ApiModelProperty("用户名")
    private String username;
    /** 1: 男 2:女 3:未知 */
    @ApiModelProperty("1: 男 2:女 3:未知")
    private Integer sex;
    /** 电话 */
    @ApiModelProperty("电话")
    private Long phone;
    /** 邮箱 */
    @ApiModelProperty("邮箱")
    private String email;
    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;
    
    @ApiModelProperty
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
    
    @ApiModelProperty
    private LocalDateTime updateTime;
    
    @ApiModelProperty
    @TableLogic
    private Integer deleted;
    
}
