package com.system.marketing.util;


import com.system.marketing.exception.BusinessException;
import com.system.marketing.vo.ResultCode;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 18:09
 * @description:
 */
public class FileVerifyUtil {

    //支持文件类型
    private static final String[] suffixWhiteList = {"PPTX", "PDF", "DOCX", "DOC"};

    /**
     * 文件后缀名校验
     *
     * @param file
     * @return
     */
    public static void suffixCheck(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            throw new BusinessException(ResultCode.POLICY_FILE_NAME_NOT_NULL);
        }
        //从最后一个点之后截取字符串
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //白名单匹配
        if (!Arrays.stream(suffixWhiteList).anyMatch(x -> x.equalsIgnoreCase(suffix))) {
            throw new BusinessException(ResultCode.POLICY_FILE_TYPE_ERROR);
        }
    }

}
