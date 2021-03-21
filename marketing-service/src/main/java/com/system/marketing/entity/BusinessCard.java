package com.system.marketing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: Liu.B.J
 * @date: 2021/3/20 19:20
 * @description:
 */
@Data
@TableName("mk_business_card")
public class BusinessCard {

    @TableId
    private Long id;

    private String name;

    private String job;

    private String telephone;

    private String mobile;

    private Long uploadUser;

    private Long creator;

    private Long updator;

    private Date createTime;

    @TableField(update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer delFlag;

}
