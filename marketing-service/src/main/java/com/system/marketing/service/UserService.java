package com.system.marketing.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.marketing.consts.KeyConsts;
import com.system.marketing.dto.req.UserLoginReq;
import com.system.marketing.dto.req.UserRegistrationReq;
import com.system.marketing.dto.resp.LoginSuccessResp;
import com.system.marketing.entity.User;
import com.system.marketing.mapper.UserMapper;
import com.system.marketing.util.RedisUtil;
import com.system.marketing.vo.Result;
import com.system.marketing.vo.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 14:07
 * @description:
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Resource
    private RedisUtil redisUtil;

    public Result<?> userLogin(UserLoginReq req) {
        User user = this.baseMapper.getUserByPwd(req);
        if (user == null) {
            return Result.error(ResultCode.LOGIN_FAIL);
        }
        // 校验重复登录
        Object loginIn = redisUtil.get(KeyConsts.IS_LOGIN_IN + user.getId());
        if (loginIn != null) {
            return Result.error(ResultCode.REPEAT_LOGIN);
        }

        user.setPwd("");
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisUtil.set(KeyConsts.USER_KEY + token, user, 24, TimeUnit.HOURS);
        redisUtil.set(KeyConsts.IS_LOGIN_IN + user.getId(), 1, 24, TimeUnit.HOURS);
        LoginSuccessResp resp = new LoginSuccessResp();
        resp.setToken(token);
        resp.setRole(user.getRole());
        resp.setUsername(user.getUsername());
        return Result.ok(resp);
    }

    public boolean userSignUp(UserRegistrationReq req) {
        User user = new User();
        BeanUtils.copyProperties(req, user);
        user.setCreator(0L);
        user.setUpdator(0L);
        System.out.println("新注册用户>>>>>>>>>>>>>>>>>>>>>>>"+user);
        return this.baseMapper.insert(user) > 0;
    }

}
