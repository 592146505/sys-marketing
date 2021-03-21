package com.system.marketing.util;

import org.springframework.util.DigestUtils;

/**
 * @author: Liu.B.J
 * @date: 2021/3/21 14:41
 * @description:
 */
public class MD5Util {

    private static final String slat = "&AJ*392*$#85@";

    /**
     * 生成md5
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
