package com.system.marketing.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 14:09
 * @description:
 */
@Data
@ApiModel(value="政策录入请求", description="政策录入请求")
public class PolicyInputReq {

    @ApiModelProperty(notes = "政策名称")
    @NotBlank(message = "政策名称不能为空")
    private String name;

    @ApiModelProperty(notes = "分类")
    @NotNull(message = "分类不能为空")
    private Integer type;

    @ApiModelProperty(notes = "文号")
    @NotBlank(message = "文号不能为空")
    private String no;

    @ApiModelProperty(notes = "文件名称")
    @NotBlank(message = "上传的文件名称不能为空")
    private String fileName;

}
