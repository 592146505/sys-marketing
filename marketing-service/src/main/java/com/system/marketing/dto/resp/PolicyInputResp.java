package com.system.marketing.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 14:09
 * @description:
 */
@Data
@ApiModel(value="政策查看返回结构体", description="政策查看返回结构体")
public class PolicyInputResp {

    @ApiModelProperty(notes = "政策名称")
    private String name;

    @ApiModelProperty(notes = "分类")
    private Integer type;

    @ApiModelProperty(notes = "录入人")
    private Long creator;

    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

    @ApiModelProperty(notes = "文件名称")
    private String fileName;

    @ApiModelProperty(notes = "附件路径")
    private String path;

}
