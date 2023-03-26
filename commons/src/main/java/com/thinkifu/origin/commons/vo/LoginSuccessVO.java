package com.thinkifu.origin.commons.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xieDan
 * @Classname LoginSuccessVO
 * @Description 登录成功vo
 * @Date 2023/3/25 10:43
 * @Created by mr_xie
 */
@Data
@ApiModel("登录成功vo")
public class LoginSuccessVO {

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("token")
    private String token;
}
