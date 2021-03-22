package com.system.marketing.controller;

import com.system.marketing.dto.req.UserLoginReq;
import com.system.marketing.dto.req.UserRegistrationReq;
import com.system.marketing.dto.resp.LoginSuccessResp;
import com.system.marketing.service.UserService;
import com.system.marketing.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 13:51
 * @description:
 */
@Api(tags = "用户登录")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("login")
    public Result<LoginSuccessResp> signIn(@RequestBody UserLoginReq req) {
        return userService.userLogin(req);
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("register")
    public Result<Boolean> signUp(@RequestBody UserRegistrationReq req) {
        return Result.ok(userService.userSignUp(req));
    }

}
