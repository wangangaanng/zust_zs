package com.zghzbckj.wechat.model;




import com.zghzbckj.wechat.utils.MessageUtil;

import java.util.Map;

public class TextMessage extends BaseMessage {
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    /**
    *<p>方法:instance 产生text文本 </p>
    *<ul>
     *<li> @param fromUser TODO</li>
     *<li> @param toUser TODO</li>
     *<li> @param msgType TODO</li>
     *<li> @param funFlag TODO</li>
     *<li> @param Content TODO</li>
    *<li>@return TextMessage  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2018/2/10 16:58  </li>
    *</ul>
    */
    public static TextMessage instance(Map<String,String> msgMap, int funFlag, String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(msgMap.get("FromUserName"));
        textMessage.setFromUserName(msgMap.get("ToUserName"));
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(funFlag);
        textMessage.setContent(content);
        return textMessage;
    }
}
