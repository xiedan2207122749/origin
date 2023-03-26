package com.thinkifu.origin.manage.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.SysUserEntity;
import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.GlobalErrorEnum;
import com.thinkifu.origin.commons.form.LoginForm;
import com.thinkifu.origin.commons.util.LoginSecurityCheckUtil;
import com.thinkifu.origin.manage.dao.SysUserDao;
import com.thinkifu.origin.manage.service.SysUserService;
import com.thinkifu.origin.manage.util.SysConstant;
import com.thinkifu.origin.commons.vo.LoginSuccessVO;
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
    public LoginSuccessVO login(LoginForm form) {
        SysUserEntity entity = this.getOne(new QueryWrapper<SysUserEntity>().select("id, username").eq("account", form.getAccount())
            .eq("password", DigestUtil.md5Hex(form.getPassword())).last("limit 1"));
        if (entity == null) {
            throw new BaseException(GlobalErrorEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        String token = LoginSecurityCheckUtil.check(entity.getId(), SysConstant.TOKEN_EXPIRE_TIME, redisTemplate);
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setToken(token);
        loginSuccessVO.setUsername(entity.getUsername());
        return loginSuccessVO;
    }
}
