package com.thinkifu.origin.manage.controller;

import com.thinkifu.origin.commons.form.LoginForm;
import com.thinkifu.origin.commons.util.Result;
import com.thinkifu.origin.manage.service.SysUserService;
import com.thinkifu.origin.manage.vo.LoginSuccessVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieDan
 * @Classname SysUserController
 * @Description
 * @Date 2022/8/29 9:56
 * @Created by mr_xie
 */
@RestController
@Validated
@RequestMapping("/user")
@Api(tags = "系统用户接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result<LoginSuccessVO> login(@RequestBody LoginForm form) {
        return Result.ok(sysUserService.login(form));
    }


}
