package com.system.marketing.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Liu.B.J
 * @date: 2021/3/29 11:30
 * @description:
 */
@Data
@ApiModel(value="政策文件成功上传响应", description="政策文件成功上传响应")
public class PolicyFileUploadResp {

    @ApiModelProperty(notes = "文件名称")
    private String fileName;

    @ApiModelProperty(notes = "文件路径")
    private String path;

}
