package com.zghzbckj.wechat.utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JSSDKSign {

    /**
     * <p>
     *  微信jssdk签名
     * </p>
     * @author Jack Zhou
     * @version $Id: JSSDKSign.java,v 0.1 2015-10-1 上午12:11:36 Jack Exp $
     */
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * <p>
     * 产生随机字符串
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: JSSDKSign.java,v 0.1 2015-8-4 下午3:44:55 Jack Exp $
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * <p>
     * 产生随机字符串的时间
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: JSSDKSign.java,v 0.1 2015-8-4 下午3:45:33 Jack Exp $
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
