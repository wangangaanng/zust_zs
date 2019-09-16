package com.zghzbckj.wechat.model;


public class AccessToken implements java.io.Serializable {
    // 获取到的凭证
    private String token;
    // 凭证有效时间，单位：秒
    private Integer expiresIn;
    //获取的时间
    private Long getTime;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getGetTime() {
        return getTime;
    }

    public void setGetTime(Long getTime) {
        this.getTime = getTime;
    }
}