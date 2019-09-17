package com.zghzbckj.manage.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * <p>方法:doPost Post 方式访问HTTP URL </p>
     * <ul>
     * <li> @param url 访问的路径地址</li>
     * <li> @param params 访问的参数</li>
     * <li> @param charset 编码方式</li>
     * <li> @param pretty 是否读一行换行</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/18 22:29  </li>
     * </ul>
     */
    public static String doPost(String url, Map<String, Object> headMap, Map<String, Object> bodyMap,
                                String charset, boolean pretty) throws IOException {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        /*普通的form post提交*/
        post.setRequestHeader(new Header("Content-type",
                "application/x-www-form-urlencoded;charset=UTF-8"));
        if (null != headMap && headMap.size() > 0) {
            for (Map.Entry<String, Object> entry : headMap.entrySet()) {
                post.setRequestHeader(new Header(entry.getKey(), entry.getValue().toString()));
            }
        }
        // 设置Http Post数据
        if (bodyMap != null && bodyMap.size() > 0) {
            for (Map.Entry<String, Object> entry : bodyMap.entrySet()) {
                post.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        try {
            client.executeMethod(post);
        } catch (IOException e) {
            logger.error("错误信息", e);
        }
        makeResponse(response, post, charset, pretty);
        return response.toString();
    }

    /**
     * <p>功能描述:Josn 方式post请求</p >
     * <ul>
     * <li>@param </li>
     * <li>@return </li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2018/5/18 14:51</li>
     * </ul>
     */
    public static String doPostJson(String url, String data, String charset, boolean pretty) throws IOException {

        StringBuffer response = new StringBuffer();
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        request.setHeader("Content-Type", "application/json;charset=utf-8");
        // 设置HttpPost数据
        StringEntity s = new StringEntity(data, "utf-8");
        request.setEntity(s);
        try {
            HttpResponse httpResponse = client.execute(request);
            makeResponse(response, httpResponse, charset, pretty);
        } catch (IOException e) {
            logger.error("错误信息", e);
        }
        return response.toString();
    }

    public static String doGet(String url, Map<String, Object> params,
                               String charset, boolean pretty) {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        if (sb.length() > 0) {
            url = url + "?" + sb.toString().substring(0, sb.toString().length() - 1);
        }
        GetMethod get = new GetMethod(url);
        get.setRequestHeader(new Header("Content-type",
                "application/x-www-form-urlencoded;charset=gbk"));
        get.setRequestHeader(new Header(
                "User-Agent",
                "Mozilla/5.0 /Windows; U; Windows NT 4.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.0"));

        try {
            client.executeMethod(get);
        } catch (IOException e) {
            logger.error("错误信息", e);
        }
        makeResponse(response, get, charset, pretty);
        return response.toString();
    }


    private static void makeResponse(StringBuffer response, HttpMethodBase method, String charset, boolean pretty) {
        if (method.getStatusCode() == HttpStatus.SC_OK) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(method.getResponseBodyAsStream(),
                                charset));

                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty) {
                        response.append(line).append(System.getProperty("line.separator"));
                    } else {
                        response.append(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("错误信息", e);
                }
                method.releaseConnection();
            }

        }
    }

    private static void makeResponse(StringBuffer response, HttpResponse httpResponse, String charset, boolean pretty) throws IOException {
        // 请求服务器成功
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), charset));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (pretty) {
                        response.append(line).append(System.getProperty("line.separator"));
                    } else {
                        response.append(line);
                    }
                }
            } catch (IOException e) {
                logger.error("错误信息", e);
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("错误信息", e);
                }
            }
        }
    }


}