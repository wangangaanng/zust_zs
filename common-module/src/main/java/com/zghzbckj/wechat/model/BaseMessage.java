package com.zghzbckj.wechat.model;

import java.io.Serializable;

/**
 * <p>
 * 消息基类（普通用户 -> 公众帐号）
 * </p>
 *
 * @author Jack Zhou
 * @version $Id: BaseMessage.java,v 0.1 2014-4-7 下午9:39:54 Jack Exp $
 */
public class BaseMessage implements Serializable {
    // 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/image/location/link）
    private String MsgType;
    // 消息id，64位整型
    private Long MsgId;

    private Integer FuncFlag;

    public Integer getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(Integer funcFlag) {
        this.FuncFlag = funcFlag;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        this.ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        this.CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        this.MsgType = msgType;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        this.MsgId = msgId;
    }
}
