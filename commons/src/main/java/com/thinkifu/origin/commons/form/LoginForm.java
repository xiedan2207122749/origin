package com.thinkifu.origin.commons.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Classname UserForm
 * @Description
 * @Date 2022/08/23 15:13
 * @Created mr_xie
 */
@ApiModel("登录form")
@Data
public class LoginForm {
    
    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}", message = "账号的格式错误")
    private String account;
    
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{32}$", message = "密码的格式错误")
    private String password;
}
