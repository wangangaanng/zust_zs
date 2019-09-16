package com.zghzbckj.util;


import java.text.SimpleDateFormat;
import java.util.Date;


public class JudgeInTimeIntervalUtils {
    public  static boolean  judgeInTimeIntervalUtils(String startTime , String endTime){
        String currentTime=getTimeShort();
        int start=(Integer.parseInt((startTime.replace(":","")).replace(" ","")));
        int end=Integer.parseInt((endTime.replace(":","")).replace(" ",""));
        int current=Integer.parseInt((currentTime.replace(":","")).replace(" ",""));
        if(start<=current&&end>=current){
            return true;
        }
        return false;
    }
    public static String getTimeShort(){
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        Date currentTime=new Date();
        String dateString = format.format(currentTime);
        return dateString;
    }
    public static boolean judgeSameDay(Date dateOne , Date dateTwo){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(format.format(dateOne).equals(format.format(dateTwo))){
            return true;
        }
        return false;
    }
}
