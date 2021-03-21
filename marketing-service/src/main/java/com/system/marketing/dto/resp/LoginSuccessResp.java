package com.system.marketing.dto.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 14:13
 * @description:
 */
@Data
@ApiModel(value="用户登录成功响应", description="用户登录成功响应")
public class LoginSuccessResp {

    private Integer role;

    private String username;

    private String token;

}
