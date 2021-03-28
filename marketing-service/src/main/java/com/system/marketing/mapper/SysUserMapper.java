package com.system.marketing.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.marketing.dto.req.UserLoginReq;
import com.system.marketing.entity.SysUser;
import com.system.marketing.enums.DeleteEnum;
import com.system.marketing.util.MD5Util;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 14:09
 * @description:
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    default SysUser getUserByPwd(UserLoginReq req){
        return this.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, req.getUsername())
                .eq(SysUser::getPassword, MD5Util.getMD5(req.getPassword()))
                .eq(SysUser::getDelFlag, DeleteEnum.No_Deleted.getCode())
                .last("limit 1"));
    }

}
