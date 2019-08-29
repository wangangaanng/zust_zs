package com.ourway.utils;


import com.ourway.base.zk.utils.TextUtils;

import java.math.BigDecimal;

/**
 * Created by jackson on 2018/5/19.
 */
public class DigitalUtil {

    //为空返回0
    public static double ifNullBackZero(Object object) {
        if (TextUtils.isEmpty(object)) {
            return 0.0;
        }
        if (object instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) object;
            return bigDecimal.doubleValue();
        }
        if (object instanceof String) {
            return Double.parseDouble(object.toString());
        }
        if (object instanceof Double) {
            return (double) object;
        }
        return 0.0;
    }

    public static Integer integerIfNullBackZero(Object object) {
        if (TextUtils.isEmpty(object)) {
            return 0;
        }
        if (object instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) object;
            return bigDecimal.intValue();
        }
        if (object instanceof String) {
            return Integer.parseInt(object.toString());
        }
        if (object instanceof Integer) {
            return (Integer) object;
        }
        return 0;
    }

    public static String ifIntNullStr(Object object) {
        if (TextUtils.isEmpty(object)) {
            return "";
        }
        try {
            Double i = new Double(object.toString());
            return i.intValue() + "";
        } catch (Exception e) {
            return object.toString();
        }
    }

    /**
     * <p>功能描述：ifNullBackEmpth 字符串如果为空则返回空</p>
     * <ul>
     * <li>@param [object]</li>
     * <li>@return java.lang.String</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 2018/7/5 15:13</li>
     * </ul>
     */
    public static String ifNullBackEmpth(Object object) {
        if (TextUtils.isEmpty(object)) {
            return "";
        }
        return object.toString();
    }

    //四舍五入返回两位小数
    public static double roundUp(Object obj) {
        if (TextUtils.isEmpty(obj)) {
            return 0.0;
        }
        BigDecimal bigDecimal = null;
        if (obj instanceof BigDecimal) {
            bigDecimal = (BigDecimal) obj;
        }
        if (obj instanceof String) {
            Double value = Double.parseDouble(obj.toString());
            bigDecimal = new BigDecimal(value);
        }
        if (obj instanceof Double) {
            bigDecimal = new BigDecimal((Double) obj);
        }
        if (TextUtils.isEmpty(bigDecimal)) {
            return 0.0;
        } else {
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    //自定义保留几位小数
    public static double roundUp(Object obj, int num) {
        try {
            if (TextUtils.isEmpty(obj)) {
                return 0.0;
            }
            BigDecimal bigDecimal = null;
            if (obj instanceof BigDecimal) {
                bigDecimal = (BigDecimal) obj;
            }
            if (obj instanceof String) {
                Double value = Double.parseDouble(obj.toString());
                bigDecimal = new BigDecimal(value);
            }
            if (obj instanceof Double) {
                bigDecimal = new BigDecimal((Double) obj);
            }
            if (TextUtils.isEmpty(bigDecimal)) {
                return 0.0;
            } else {
                return bigDecimal.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    //四舍五入保留整数
    public static int roundUpToInteger(Object object) {
        if (TextUtils.isEmpty(object)) {
            return 0;
        }
        BigDecimal bigDecimal = new BigDecimal(object.toString());
        return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }


    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
//            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(roundUpToInteger(8.0));
    }
}
