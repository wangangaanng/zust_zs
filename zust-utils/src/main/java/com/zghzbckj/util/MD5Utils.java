package com.zghzbckj.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <dl>
 * <dt>MD5Utils</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2018</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2018/9/14</dd>
 * </dl>
 *
 * @author xby
 */
public class MD5Utils {

    private static final String[] HEXDIGITS;
    private static MessageDigest messagedigest = null;

    /** 静态代码块*/
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var1) {
            var1.printStackTrace();
        }
        HEXDIGITS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    }


    /**
     *<p>功能描述: MD5 加密</p >
     *<ul>
     *<li>@param [str：传入的字符串, start：字符串截取起始, end：字符串截取末尾]</li>
     *<li>@return java.lang.String</li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2019/3/22 14:09</li>
     *</ul>
    */
    public static String MD5(String str, int start, int end) {
        String resultString = "";
        if (end > 32) {
            end = 32;
        }
        resultString = byteArrayToHexString(messagedigest.digest(str.getBytes())).substring(start, end);
        return resultString;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = b + 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEXDIGITS[d1] + HEXDIGITS[d2];
    }

}