package com.zghzbckj.wechat.utils.response;

/**
 * <dl>
 * <dt>KFMessage</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2018</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2018/6/13</dd>
 * </dl>
 *
 * @author xby
 */
public class KFMessage {

    private String ToUserName;

    private String FromUserName;

    private long CreateTime;

    private String MsgType;


    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}