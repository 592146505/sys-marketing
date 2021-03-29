package com.system.marketing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 14:31
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("mk_policy")
public class Policy {

    @TableId
    private Long id;

    private String name;

    private Integer type;

    private String no;

    private String path;

    private String fileName;

    private Long creator;

    private Long updator;

    private Date createTime;

    @TableField(update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Version
    private Integer version;

    private Integer delFlag;

}
