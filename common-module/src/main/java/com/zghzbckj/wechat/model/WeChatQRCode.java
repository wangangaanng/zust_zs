package com.zghzbckj.wechat.model;


public class WeChatQRCode implements java.io.Serializable {
    // 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
    private String ticket;
    // 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
    private Integer expireSeconds;
    //获取的时间
    private Long getTime;
    /*二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片*/
    private String url;
    private Integer scence;

    public Integer getScence() {
        return scence;
    }

    public void setScence(Integer scence) {
        this.scence = scence;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public Long getGetTime() {
        return getTime;
    }

    public void setGetTime(Long getTime) {
        this.getTime = getTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}