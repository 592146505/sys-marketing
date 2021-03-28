package com.system.marketing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 19:25
 * @description:
 */
@Data
@TableName("mk_sys_user")
public class SysUser {

    @TableId
    private Long id;

    private String name;

    private String namePinyin;

    private String username;

    private String password;

    private String mobile;

    private Long creator;

    private Long editor;

    private Date createTime;

    @TableField(update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Version
    private Integer version;

    private Integer delFlag;

}
