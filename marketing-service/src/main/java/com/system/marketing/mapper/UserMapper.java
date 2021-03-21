package com.system.marketing.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.marketing.dto.req.UserLoginReq;
import com.system.marketing.entity.User;
import com.system.marketing.enums.DeleteEnum;
import com.system.marketing.util.MD5Util;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 14:09
 * @description:
 */
public interface UserMapper extends BaseMapper<User> {

    default User getUserByPwd(UserLoginReq req){
        return this.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, req.getUsername())
                .eq(User::getPwd, MD5Util.getMD5(req.getPwd()))
                .eq(User::getDelFlag, DeleteEnum.No_Deleted.getCode())
                .last("limit 1"));
    }

}
