package com.thinkifu.origin.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkifu.origin.app.form.RegisterForm;
import com.thinkifu.origin.commons.entity.UserEntity;
import com.thinkifu.origin.commons.form.LoginForm;
import com.thinkifu.origin.commons.vo.LoginSuccessVO;

/**
 * 用户表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-26 15:24:18
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 登录
     * @param loginForm
     * @return
     */
    LoginSuccessVO login(LoginForm loginForm);

    /**
     * 注册
     * @param form
     */
    void register(RegisterForm form);
}

