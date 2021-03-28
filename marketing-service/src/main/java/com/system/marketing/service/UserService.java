package com.system.marketing.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.marketing.consts.KeyConsts;
import com.system.marketing.dto.req.UserLoginReq;
import com.system.marketing.dto.req.UserRegistrationReq;
import com.system.marketing.dto.resp.LoginSuccessResp;
import com.system.marketing.entity.SysUser;
import com.system.marketing.exception.BusinessException;
import com.system.marketing.mapper.SysUserMapper;
import com.system.marketing.util.MD5Util;
import com.system.marketing.util.PinyinUtil;
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
@Service("userService")
public class UserService extends ServiceImpl<SysUserMapper, SysUser> {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 登录
     *
     * @param req
     * @return
     */
    public Result<LoginSuccessResp> userLogin(UserLoginReq req) {
        LoginSuccessResp resp = new LoginSuccessResp();
        SysUser user = this.baseMapper.getUserByPwd(req);
        if (user == null) {
            throw new BusinessException(ResultCode.LOGIN_FAIL);
        }
        // 校验重复登录
        Object loginIn = redisUtil.get(KeyConsts.IS_LOGIN_IN + user.getId());
        if (loginIn != null) {
            throw new BusinessException(ResultCode.REPEAT_LOGIN);
        }

        user.setPassword("");
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisUtil.set(KeyConsts.USER_KEY + token, user, 24, TimeUnit.HOURS);
        redisUtil.set(KeyConsts.IS_LOGIN_IN + user.getId(), 1, 24, TimeUnit.HOURS);
        resp.setToken(token);
//        resp.setRole(user.getRole());
        resp.setUsername(user.getUsername());
        return Result.ok(resp);
    }

    /**
     * 注册
     *
     * @param req
     * @return
     */
    public boolean userSignUp(UserRegistrationReq req) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(req, user);
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        user.setNamePinyin(PinyinUtil.toPinyin(user.getName()));
        user.setCreator(0L);
        user.setEditor(0L);
        return this.baseMapper.insert(user) > 0;
    }

}
