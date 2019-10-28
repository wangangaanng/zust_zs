package com.zghzbckj.manage.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;

import java.util.*;

/**
 * Created by jackson on 17-3-14.
 */
public class MessageUtil {
    //请求地址
    private static final String requesturl = "http://api.hspaas.cn:8080/sms/send";
    //查询余额地址
    private static final String requestBalanceurl = "http://sms.hspaas.com:8080/msgHttp/json/balance";
    //账户
    private static final String account = "hsw7upeW1Oyc1N";
    //密码
    private static final String password = "407ecc8d2535bb9fc92ad09227201260";

    /**
     * <p>功能描述：sendMessageCode 发送验证码</p>
     * <ul>
     * <li>@param [mobile]</li>
     * <li>@return java.lang.String</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 17-3-15 下午12:30</li>
     * </ul>
     */
    public static String sendMessageCode(String mobile, String code) throws Exception {
        if (TextUtils.isEmpty(mobile)) {
            throw new Exception("手机号为空");
        }
        long timestamp = System.currentTimeMillis();    //时间戳
        String pass = MD5Util.MD5Encode(password + mobile + timestamp, "utf-8");
        String content = "【浙江科技学院】您的验证码是" + code + "，如非本人发送请忽略该信息。";
        Map map = new HashMap();
        map.put("appkey", account);
        map.put("appsecret", pass);
        map.put("mobile", mobile);
        map.put("timestamp", timestamp + "");
        map.put("content", content);
        return doPost(requesturl, map, "utf-8");
    }


    public static String sendMessage(String mobile, String contenet) throws Exception {
        if (TextUtils.isEmpty(mobile)) {
            throw new Exception("手机号为空");
        }
        long timestamp = System.currentTimeMillis();    //时间戳
        String pass = MD5Util.MD5Encode(password + mobile + timestamp, "utf-8");
        String content = "【浙江科技学院】"+contenet;
        Map map = new HashMap();
        map.put("appkey", account);
        map.put("appsecret", pass);
        map.put("mobile", mobile);
        map.put("timestamp", timestamp + "");
        map.put("content", content);
        return doPost(requesturl, map, "utf-8");
    }
    /**
     * <p>功能描述：doPost 发送post请求</p>
     * <ul>
     * <li>@param [url, map, charset]</li>
     * <li>@return java.lang.String</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 17-3-14 下午7:10</li>
     * </ul>
     */
    public static String doPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            String sjh = "13588279280";
            String temp = "1234";
            System.out.println(sendMessageCode(sjh, temp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
