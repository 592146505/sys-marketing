package com.system.marketing.dto.req;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 13:55
 * @description:
 */
@Data
@ApiModel(value="用户登录请求", description="用户登录请求")
public class UserLoginReq {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String pwd;

}
