package com.zghzbckj.web.utils;

import com.ourway.base.utils.TextUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 10033 on 2017/3/23.
 */
@Component
public class PropertiesUtil {
        private static  final String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        private static  final String regExIp="^(127\\.0\\.0\\.1)|(localhost)|(10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(172\\.((1[6-9])|(2\\d)|(3[01]))\\.\\d{1,3}\\.\\d{1,3})|(192\\.168\\.\\d{1,3}\\.\\d{1,3})$";


    /**
     *<p>方法:判断是否内网IP innerIp TODO </p>
     *<ul>
     *<li> @param ip TODO</li>
     *<li>@return boolean  </li>
     *<li>@author xuyux </li>
     *<li>@date 2019/9/26 16:18  </li>
     *</ul>
     */
    public static boolean  innerIp(String ip) {
        Pattern reg = Pattern.compile(regExIp);
        Matcher match = reg.matcher(ip);
        return match.find();
    }
    public Properties loadProperties(String fileName){
        Properties pro = new Properties();
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            System.out.println(Properties.class);
            is = this.getClass().getClassLoader().getResourceAsStream(fileName);
            isr = new InputStreamReader(is);
            pro.load(isr);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return pro;
    }

    public static String readProperty(Properties p,String key){
        if(TextUtils.isEmpty(p.getProperty(key)))
            return "";
        else
            return p.getProperty(key);
    }

    public static String filterChar(    String str) {
        if(null==str){
            return str;
        }
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
