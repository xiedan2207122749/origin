package com.thinkifu.origin.app.controller;

import com.thinkifu.origin.app.form.RegisterForm;
import com.thinkifu.origin.app.service.UserService;
import com.thinkifu.origin.commons.form.LoginForm;
import com.thinkifu.origin.commons.util.Result;
import com.thinkifu.origin.commons.vo.LoginSuccessVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 用户表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-26 15:24:18
 */
@Api(tags="用户表")
@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result<LoginSuccessVO> login(@RequestBody @Valid LoginForm loginForm) {
        return Result.ok(userService.login(loginForm));
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public Result register(@RequestBody @Valid RegisterForm form) {
        userService.register(form);
        return Result.ok();
    }
}
