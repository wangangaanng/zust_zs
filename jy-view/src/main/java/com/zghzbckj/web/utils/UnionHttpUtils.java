package com.zghzbckj.web.utils;

import com.ourway.base.utils.JackSonJsonUtils;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.http.HttpClientUtils;
import com.zghzbckj.web.constant.Constant;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.model.ResponseMessage;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 UnionHttpUtils : <p>
 * <p>说明:前端统一调用类</p>
 * <pre>
 * @author JackZhou
 * @date 2018/7/24 17:55
 * </pre>
 */
public class UnionHttpUtils {
    private static final Logger log = LoggerFactory.getLogger(UnionHttpUtils.class);


    public static ResponseMessage invoke(PublicData vo) {
        try {
            String result = doPost(vo);
            return JackSonJsonUtils.fromJson(result, ResponseMessage.class);
//            return (ResponseMessage) JsonMapper.fromJsonString(result, ResponseMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //带cookie的调用
    public static String doPost(PublicData data) throws IOException {
        String baseUrl = "";
        if (!TextUtils.isEmpty(data.getBaseUrl())) {
            baseUrl = data.getBaseUrl();
        } else {
            baseUrl = Constant.URL_HOST;
        }
        String url = baseUrl + data.getMethod();
        String result = doPost(url, (Map<String, Object>) JsonUtil.objectToMap(data), Constant.UTF8);
        return result;
    }

    public static String doPost(String url, Map<String, Object> dataMap, String charset) {
        HttpPost httpPost = new HttpPost(url);
        try {
            if (dataMap != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                    if(null!=entry.getValue()) {
                        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                    }
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, charset);
                formEntity.setContentEncoding(charset);
                httpPost.setEntity(formEntity);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return HttpClientUtils.executeRequest(httpPost, charset);
    }
}
