package com.zghzbckj.manage.utils;

import com.zghzbckj.CommonConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharUtil {
    private static  final String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    public static String filterChar(    String str) {
        if(null==str){
            return str;
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll(CommonConstants.EMPTY_STR).trim();
    }
}
