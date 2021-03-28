package com.system.marketing.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 16:56
 * @description:
 */
@Data
@ApiModel(value="查询政策列表请求", description="查询政策列表请求")
public class QueryPolicyReq {

    @NotNull(message = "page不能为空")
    private Integer page;

    @NotNull(message = "size不能为空")
    private Integer size;

    @ApiModelProperty(notes = "政策名称")
    private String name;

    @ApiModelProperty(notes = "政策文号")
    private String no;

}
