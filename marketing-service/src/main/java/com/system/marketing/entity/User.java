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
@TableName("mk_user")
public class User {

    @TableId
    private Long id;

    private String username;

    private String mobile;

    private String pwd;

    private Integer role;

    private Long creator;

    private Long updator;

    private Date createTime;

    @TableField(update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer delFlag;

}
