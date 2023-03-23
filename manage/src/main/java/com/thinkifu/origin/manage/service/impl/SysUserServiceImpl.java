package com.thinkifu.origin.manage.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.SysUserEntity;
import com.thinkifu.origin.commons.exception.AppErrorEnum;
import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.GlobalErrorEnum;
import com.thinkifu.origin.commons.form.LoginForm;
import com.thinkifu.origin.commons.util.LoginSecurityCheckUtil;
import com.thinkifu.origin.manage.dao.SysUserDao;
import com.thinkifu.origin.manage.service.SysUserService;
import com.thinkifu.origin.manage.util.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 后台用户表
 *
 * @author Mr_xie
 * @email e15973488008@163.com
 * @date 2022-08-22 16:24:37
 */

@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 登录
     *
     * @param form
     * @return
     */
    @Override
    public String login(LoginForm form) {
        Long userId = this.getObj(new QueryWrapper<SysUserEntity>().select("id").eq("account", form.getAccount())
            .eq("password", DigestUtil.md5Hex(form.getPassword())).last("limit 1"), item -> Long.parseLong(item.toString()));
        if (userId == null) {
            throw new BaseException(GlobalErrorEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        return LoginSecurityCheckUtil.check(userId, SysConstant.TOKEN_EXPIRE_TIME, redisTemplate);
    }
}
