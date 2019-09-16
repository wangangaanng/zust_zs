package com.zghzbckj.wechat.model;


import java.io.Serializable;

/**
 * <p>方法: 支付微信推送反馈 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2018/2/13 22:55  </li>
 * </ul>
 */
public class PayBuyPost implements Serializable {

    private String OpenId;            // 支付该笔订单的用户 ID
    private String AppId;            // 公众号 id
    private int IsSubscribe;        // 用户是否关注了公众号。1 为关注，0 为未关注
    private long TimeStamp;            // 时间戳
    private String NonceStr;        // 随机字符串；字段来源：商户生成的随机字符
    private String AppSignature;    // 字段名称：签名
    private String SignMethod;        // SHA1

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String openId) {
        OpenId = openId;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public int getIsSubscribe() {
        return IsSubscribe;
    }

    public void setIsSubscribe(int isSubscribe) {
        IsSubscribe = isSubscribe;
    }

    public long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getNonceStr() {
        return NonceStr;
    }

    public void setNonceStr(String nonceStr) {
        NonceStr = nonceStr;
    }

    public String getAppSignature() {
        return AppSignature;
    }

    public void setAppSignature(String appSignature) {
        AppSignature = appSignature;
    }

    public String getSignMethod() {
        return SignMethod;
    }

    public void setSignMethod(String signMethod) {
        SignMethod = signMethod;
    }

    @Override
    public String toString() {
        return "WeChatBuyPost [OpenId=" + OpenId + ", AppId=" + AppId
                + ", IsSubscribe=" + IsSubscribe + ", TimeStamp=" + TimeStamp
                + ", NonceStr=" + NonceStr + ", AppSignature=" + AppSignature
                + ", SignMethod=" + SignMethod + "]";
    }
}