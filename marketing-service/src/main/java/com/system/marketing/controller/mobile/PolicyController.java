package com.system.marketing.controller.mobile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.marketing.dto.req.PolicyInputReq;
import com.system.marketing.dto.req.QueryPolicyReq;
import com.system.marketing.dto.resp.PolicyInputResp;
import com.system.marketing.service.PolicyService;
import com.system.marketing.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 14:03
 * @description:
 */
@Api(tags = "政策库管理")
@RestController
@RequestMapping("/mobile/policy")
public class PolicyController {

    @Resource
    private PolicyService policyService;

    @ApiOperation(value = "政策录入", notes = "政策录入")
    @PostMapping("input")
    public Result<Boolean> policyInput(@ApiIgnore HttpServletRequest httpRequest,
                                       @RequestBody PolicyInputReq req) {
        return Result.ok(policyService.policyInput(httpRequest, req));
    }

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping("upload")
    public Result<String> uploadPolicy(@NotNull @RequestParam("file") MultipartFile file) {
        return Result.ok(policyService.uploadFile(file));
    }

    @ApiOperation(value = "政策查看", notes = "政策查看")
    @GetMapping("/{policyId}")
    public Result<PolicyInputResp> getPolicyInfo(@PathVariable("policyId") Long policyId) {
        return Result.ok(policyService.getPolicy(policyId));
    }

    @ApiOperation(value = "政策查询列表", notes = "政策查询列表")
    @PostMapping("filter")
    public Result<Page<PolicyInputResp>> getPolicyList(@RequestBody QueryPolicyReq req) {
        return Result.ok(policyService.getPolicyList(req));
    }


}
