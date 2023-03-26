package com.thinkifu.origin.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author xieDan
 * @Classname RegisterForm
 * @Description 注册form
 * @Date 2022/8/24 9:25
 * @Created by mr_xie
 */
@Data
@ApiModel("注册form")
public class RegisterForm {
    
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}", message = "账号的格式错误")
    private String account;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{32}$", message = "密码格式不对")
    private String password;
    
    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String username;
}
