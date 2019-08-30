package com.zghzbckj.util;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <dl>
 * <dt>ToolUtils</dt>
 * <dd>Description: 基本工具类</dd>
 * <dd>Copyright: Copyright (C) 2018</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2018/10/25</dd>
 * </dl>
 *
 * @author xby
 */
public class ToolUtils {

    private static final char[] CHINES_NUM = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
    private static final char[] CHINESE_UNIT = {'里', '分', '角', '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟'};

    /**
     * 返回关于钱的中文式大写数字,支仅持到亿
     */
    public static String arabNumToChineseRMB(int moneyNum) {
        String res = "";
        int i = 3;
        if (moneyNum == 0) {
            return "零元";
        }
        while (moneyNum > 0) {
            res = CHINESE_UNIT[i++] + res;
            res = CHINES_NUM[moneyNum % 10] + res;
            moneyNum /= 10;
        }
        return res.replaceAll("零[拾佰仟]", "零")
                .replaceAll("零+亿", "亿").replaceAll("零+万", "万")
                .replaceAll("零+元", "元").replaceAll("零+", "零");

    }

    /**
     * 返回关于钱的中文式大写数字,支仅持到亿
     *
     * @throws Exception
     */
    private static String arabNumToChineseRMB(String moneyNum) throws Exception {
        String res = "";
        int i = 3;
        int len = moneyNum.length();
        if (len > 12) {
            throw new Exception("Number too large!");
        }
        if ("0".equals(moneyNum)) {
            return "零元";
        }
        for (len--; len >= 0; len--) {
            res = CHINESE_UNIT[i++] + res;
            int num = Integer.parseInt(moneyNum.charAt(len) + "");
            res = CHINES_NUM[num] + res;
        }
        return res.replaceAll("零[拾佰仟]", "零")
                .replaceAll("零+亿", "亿").replaceAll("零+万", "万")
                .replaceAll("零+元", "元").replaceAll("零+", "零");

    }


    /**
     * 整数位支持12位,到仟亿
     * 支持到小数点后3位,如果大于3位,那么会四舍五入到3位
     *
     * @throws Exception
     */
    public static String arabNumToChineseRMB(double moneyNum) throws Exception {
        String res = "";
        String money = String.format("%.3f", moneyNum);
        int i = 0;
        if (moneyNum == 0.0) {
            return "零元";
        }
        String inte = money.split("\\.")[0];
        int deci = Integer.parseInt(money.split("\\.")[1].substring(0, 3));
        while (deci > 0) {
            res = CHINESE_UNIT[i++] + res;
            res = CHINES_NUM[deci % 10] + res;
            deci /= 10;
        }
        res = res.replaceAll("零[里分角]", "零");
        if (i < 3) {
            res = "零" + res;
        }
        res = res.replaceAll("零+", "零");
        if (res.endsWith("零")) {
            res = res.substring(0, res.length() - 1);
        }
        return arabNumToChineseRMB(inte) + res;
    }


    /**
     * 生成（'1','2'）类型的数据
     */
    public static String createStr(String str, String prex) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, prex);
        StringBuffer buffer = new StringBuffer("('");
        while (stringTokenizer.hasMoreElements()) {
            buffer.append(stringTokenizer.nextToken() + "','");
        }
        String result = buffer.toString();
        return result.substring(0, result.length() - 3) + "')";
    }


    /**
     * 分割是字符串，返回List
     */
    public static String createInStr(List<String> sList){
        if(null != sList && sList.size() >0){
            StringBuffer buffer = new StringBuffer("('");
            for(String tem : sList) {
                buffer.append(tem + "','");
            }
            String result = buffer.toString();
            return result.substring(0, result.length() - 3) + "')";
        }
        return null;
    }

    /** 分割是字符串，返回List*/
    public static List<String> strToList(String str,String prex){
        StringTokenizer stringTokenizer = new StringTokenizer(str,prex);
        List<String> sList = Lists.newArrayList();
        while (stringTokenizer.hasMoreElements()) {
            sList.add(stringTokenizer.nextToken());
        }
        return sList;
    }

//    public static void saveRequestMap(String reslt, String fileName, String path) {
//        FileOutputStream fos = null;
//        try {
//            // 准备文件666.txt其中的内容是空的
//            File f1 = new File(path + fileName);
//            if (f1.exists() == false) {
//                f1.getParentFile().mkdirs();
//            } else {
//                //已经存在就return
//                return;
//            }
//            //
//            byte[] data = reslt.getBytes();
//            // 创建基于文件的输出流
//            fos = new FileOutputStream(f1);
//            // 把数据写入到输出流
//            fos.write(data);
//            // 关闭输出流
//            fos.close();
//        } catch (IOException e) {
//            LOGGER.error(CommonConstant.ERROR_MESSAGE, e);
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    LOGGER.error(CommonConstant.ERROR_MESSAGE, e);
//                }
//            }
//        }
//    }
    
    /**
     *<p>功能描述:去除多余的0</p >
     *<ul>
     *<li>@param </li>
     *<li>@return java.lang.String</li>
     *<li>@throws </li>
     *<li>@author xubiyu</li>
     *<li>@date 2019-02-11 16:09</li>
     *</ul>
    */
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }


    /**
     *<p>功能描述:去除bigDecimal后面的0</p >
     *<ul>
     *<li>@param [money]</li>
     *<li>@return java.math.BigDecimal</li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2018/12/4 10:29</li>
     *</ul>
     */
    public static BigDecimal removeAmtLastZero(BigDecimal money) {
        String strMoney = money.toString();
        if (strMoney.indexOf('.') != -1) {
            String[] arr = strMoney.split("\\.");
            String strDecimals = arr[1];
            List<String> list = new ArrayList<String>();
            boolean isCanAdd = false;
            for (int i = strDecimals.length() - 1; i > -1; i--) {
                String ss = String.valueOf(strDecimals.charAt(i));
                if (!"0".equals(ss)) {
                    //从最后的字符开始算起，遇到第一个不是0的字符开始都是需要保留的字符
                    isCanAdd = true;
                }
                if (!"0".equals(ss) || isCanAdd) {
                    list.add(ss);
                }
            }
            StringBuffer strZero = new StringBuffer();
            for (int i = list.size() - 1; i > -1; i--) {
                strZero.append(list.get(i));
            }
            strMoney = String.format("%s.%s", arr[0], strZero.toString());
        }
        return new BigDecimal(strMoney);
    }
    

    /**
    *<p>功能描述: 置换某个字符</p >
    *<ul>
    *<li>@param [orgin, index, ss]</li>
    *<li>@return java.lang.String</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2019/2/18 16:08</li>
    *</ul>
    */
    public static String replaceChar(String orgin,int index, char ss){
        char[] cs = orgin.toCharArray();
        cs[index] = ss;
        return new String(cs);
    }

}