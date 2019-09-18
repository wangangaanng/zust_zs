
package com.zghzbckj.util;











import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class WeChatLogin {

    private static final long serialVersionUID=1L;
    private static final String APPID="wx2568460f7adbd6bd";
    private static final String SECRET="e3d3b8c72799b5eccaa1532922f57b66";
    private String code;
    public static String openid_url="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Map<String,Object> (){
        String url =openid_url.replace("APPID",APPID).replace("SECRET",SECRET).replace("JSCODE",code);
        Map<String,Object> param=new HashMap<>();
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject=httpRequest(url,"GET",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(jsonObject.containsKey("openid")){
            param.put("openid",jsonObject.get("openid").toString());
        }
        if(jsonObject.containsKey("session_key")){
            param.put("session_key",jsonObject.get("session_key").toString());
        }
        if(jsonObject.containsKey("unionid")){
            param.put("unionid",jsonObject.get("unionid").toString());
        }
        if(jsonObject.containsKey("errcode")){
            param.put("errcode",jsonObject.get("errcode").toString());
        }
        if(jsonObject.containsKey("errmsg")){
            param.put("errmsg",jsonObject.get("errmsg").toString());
        }
        return param;
    }

    public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr) throws Exception{
        JSONObject jsonObject=new JSONObject();
        StringBuffer buffer=new StringBuffer();
        try {
            URL restUrl=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)restUrl.openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            if("GET".equalsIgnoreCase(requestMethod))
                conn.connect();
            if(outputStr!=null){
                OutputStream outputStream=conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            InputStream inputStream=conn.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String str=null;
            while((str=bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            jsonObject=JSONObject.parseObject(buffer.toString());
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }



}
