package com.system.marketing.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.marketing.consts.KeyConsts;
import com.system.marketing.dto.req.PolicyInputReq;
import com.system.marketing.dto.req.QueryPolicyReq;
import com.system.marketing.dto.resp.PolicyFileUploadResp;
import com.system.marketing.dto.resp.PolicyInputResp;
import com.system.marketing.entity.Policy;
import com.system.marketing.entity.SysUser;
import com.system.marketing.enums.DeleteEnum;
import com.system.marketing.exception.BusinessException;
import com.system.marketing.mapper.PolicyMapper;
import com.system.marketing.util.FileVerifyUtil;
import com.system.marketing.util.GsonUtil;
import com.system.marketing.util.RedisUtil;
import com.system.marketing.vo.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 14:27
 * @description:
 */
@Service("policyService")
public class PolicyService extends ServiceImpl<PolicyMapper, Policy> {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 政策录入
     *
     * @param request
     * @param req
     * @return
     */
    public Boolean policyInput(HttpServletRequest request, PolicyInputReq req) {
        Object objUser = redisUtil.get(KeyConsts.USER_KEY + request.getHeader(KeyConsts.TOKEN));
        SysUser user = GsonUtil.convert(objUser, SysUser.class);
//        String path = KeyConsts.UPLOAD_FILE_PATH + req.getFileName();
        return this.baseMapper.insert(Policy.builder()
                .name(req.getName())
                .type(req.getType())
                .no(req.getNo())
                .path(req.getPath())
                .fileName(req.getFileName())
                .creator(user.getId())
                .updator(user.getId())
                .build()) > 0;
    }

    /**
     * 上传政策文件
     *
     * @param file
     * @return
     */
    public PolicyFileUploadResp uploadFile(MultipartFile file) {
        FileVerifyUtil.suffixCheck(file);
        String fileName = file.getOriginalFilename() + "-" + UUID.randomUUID().toString().replaceAll("-", "");
        String filePath = KeyConsts.UPLOAD_FILE_PATH;
        File dir = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        PolicyFileUploadResp resp = new PolicyFileUploadResp();
        String path = filePath + fileName;
        File dest = new File(path);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new BusinessException(ResultCode.POLICY_FILE_UPLOAD_FAIL);
        }
        resp.setFileName(file.getOriginalFilename());
        resp.setPath(path);
        return resp;
    }

    /**
     * 政策查看
     *
     * @param policyId
     * @return
     */
    public PolicyInputResp getPolicy(Long policyId) {
        Policy policy = this.baseMapper.getPolicyById(policyId);
        if (policy == null) {
            throw new BusinessException(ResultCode.POLICY_NOT_EXIST);
        }
        PolicyInputResp resp = new PolicyInputResp();
        BeanUtils.copyProperties(policy, resp);
        return resp;
    }

    /**
     * 政策查询列表
     *
     * @param req
     * @return
     */
    public Page<PolicyInputResp> getPolicyList(QueryPolicyReq req) {
        LambdaQueryWrapper<Policy> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Policy::getDelFlag, DeleteEnum.No_Deleted.getCode());
        if (!StringUtils.isEmpty(req.getName())) {
            queryWrapper.like(Policy::getName, req.getName());
        }
        if (!StringUtils.isEmpty(req.getNo())) {
            queryWrapper.like(Policy::getNo, req.getNo());
        }
        queryWrapper.orderByDesc(Policy::getCreateTime);
        Page<Policy> page = new Page<>(req.getPage(), req.getSize());
        IPage<Policy> iPage = this.baseMapper.selectPage(page, queryWrapper);
        Page<PolicyInputResp> respList = new Page<>();
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return respList;
        }
        BeanUtils.copyProperties(iPage, respList);
        respList.setRecords(GsonUtil.convertList(iPage.getRecords(), PolicyInputResp.class));
        return respList;
    }

}
