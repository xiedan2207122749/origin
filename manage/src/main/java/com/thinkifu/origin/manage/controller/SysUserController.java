package com.thinkifu.origin.manage.controller;

import com.google.code.kaptcha.Producer;
import com.thinkifu.origin.commons.form.LoginForm;
import com.thinkifu.origin.commons.util.Result;
import com.thinkifu.origin.commons.validator.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    public Result<String> login(@RequestBody LoginForm form) {
        return Result.ok(sysUserService.login(form));
    }
}
