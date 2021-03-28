package com.system.marketing.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.marketing.entity.Policy;
import com.system.marketing.enums.DeleteEnum;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 14:09
 * @description:
 */
public interface PolicyMapper extends BaseMapper<Policy> {

    default Policy getPolicyById(Long id){
        return this.selectOne(new LambdaQueryWrapper<Policy>()
                .eq(Policy::getId, id)
                .eq(Policy::getDelFlag, DeleteEnum.No_Deleted.getCode())
                .last("limit 1"));
    }

}
