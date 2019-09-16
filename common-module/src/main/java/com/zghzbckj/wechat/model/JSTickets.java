package com.zghzbckj.wechat.model;

/**
 * 微信通用接口凭证
 *
 * @author 杨建冬
 * @date 2013-09-10
 */
public class JSTickets implements java.io.Serializable {
    // 获取到的凭证
    private String ticket;
    // 凭证有效时间，单位：秒
    private Integer expiresIn;
    private Long getTime;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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