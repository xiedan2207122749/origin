package com.thinkifu.origin.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thinkifu.origin.commons.entity.SysUserEntity;
import com.thinkifu.origin.commons.form.LoginForm;
import com.thinkifu.origin.commons.vo.LoginSuccessVO;

/**
 * 后台用户表
 *
 * @author Mr_xie
 * @email e15973488008@163.com
 * @date 2022-08-22 16:24:37
 */
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 登录
     * @param form
     * @return
     */
    LoginSuccessVO login(LoginForm form);
}

