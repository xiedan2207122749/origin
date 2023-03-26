package com.thinkifu.origin.app.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.app.dao.UserDao;
import com.thinkifu.origin.app.form.RegisterForm;
import com.thinkifu.origin.app.service.UserService;
import com.thinkifu.origin.app.util.AppConstant;
import com.thinkifu.origin.commons.entity.UserEntity;
import com.thinkifu.origin.commons.enums.WhetherEnum;
import com.thinkifu.origin.commons.exception.AppErrorEnum;
import com.thinkifu.origin.commons.exception.BaseException;
import com.thinkifu.origin.commons.exception.GlobalErrorEnum;
import com.thinkifu.origin.commons.form.LoginForm;
import com.thinkifu.origin.commons.util.LoginSecurityCheckUtil;
import com.thinkifu.origin.commons.vo.LoginSuccessVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 用户表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-26 15:24:18
 */

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 登录
     *
     * @param loginForm
     * @return
     */
    @Override
    public LoginSuccessVO login(LoginForm loginForm) {
        UserEntity userEntity = this.getOne(new QueryWrapper<UserEntity>().select("id, username, available_flag").eq("account", loginForm.getAccount()).eq("password", DigestUtil.md5Hex(loginForm.getPassword())).last("limit 1"));
        if (userEntity == null) {
            throw new BaseException(GlobalErrorEnum.ACCOUNT_OR_PASSWORD_ERROR);
        }
        if (userEntity.getAvailableFlag() != WhetherEnum.YES.getValue()) {
            throw new BaseException(GlobalErrorEnum.ACCOUNT_HAS_BEEN_BANNED);
        }
        // 更新最后一次登录时间
        this.update().setSql("last_active_date = curdate()").eq("id", userEntity.getId()).last("limit 1").update();
        String token = LoginSecurityCheckUtil.check(userEntity.getId(), AppConstant.TOKEN_EXPIRE_TIME, redisTemplate);
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setToken(token);
        loginSuccessVO.setUsername(userEntity.getUsername());
        return loginSuccessVO;
    }

    /**
     * 注册
     *
     * @param form
     */
    @Override
    public void register(RegisterForm form) {
        authenticationAccountAndUsernameUnique(form);
        UserEntity userEntity = new UserEntity();
        userEntity.setAccount(form.getAccount());
        userEntity.setPassword(DigestUtil.md5Hex(form.getPassword()));
        userEntity.setUsername(form.getUsername());
        this.save(userEntity);
    }

    private void authenticationAccountAndUsernameUnique(RegisterForm form) {
        int count = this.baseMapper.countByField(form.getAccount(), "account");
        if (count >= 0) {
            throw new BaseException(AppErrorEnum.ACCOUNT_EXIST);
        }
        count = this.baseMapper.countByField(form.getUsername(), "username");
        if (count >= 0) {
            throw new BaseException(AppErrorEnum.USERNAME_EXIST);
        }
    }
}
