package com.ourway.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getYeanAndMonth() {
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR));//当前年
        Integer month = now.get(Calendar.MONTH) + 1;
        String monthStr = month.toString();
        if (month < 10) {
            monthStr = "0" + month;
        }
        return year + "年" + monthStr + "月";
    }

    public static Integer getYean(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.YEAR);
    }

    /***
     *<p>功能描述：isNumeric 判断是否为数字</p>
     *<ul>
     *<li>@param [str]</li>
     *<li>@return boolean</li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 2019/3/29 17:04</li>
     *</ul>
     */
    public static boolean isNumeric(String str) {
         try {
             Double data = Double.parseDouble(str);
         } catch (Exception e) {
             return false;
         }
         return true;
    }

    public static void main(String[] args) {
        String val = getYeanAndMonth();
//        System.out.println(val);
    }
}
