package com.zghzbckj.wechat.utils;


import com.ourway.base.utils.TextUtils;
import com.zghzbckj.messageapi.wechat.model.SysWxtip;

import java.util.Date;
import java.util.Map;


/**
 * Created by jack on 2018/2/18.
 */
public class WechatTipUtils {

    //组织文本
    public static SysWxtip doTxtTip(Map<String, String> msgMap){
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(0);
        tip.setMsgCat(0);
        tip.setMsgContent(msgMap.get("Content"));
        tip.setState(-1);
        return tip;
    }

    public static SysWxtip doPicTip(Map<String, String> msgMap){
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(1);
        tip.setMsgCat(0);
        tip.setState(-1);
        if(!TextUtils.isEmpty(msgMap.get("MediaId"))){
            //保存图片
            String tpmc = tip.getOpenid()+System.currentTimeMillis();
            MediaDownUtil.saveImageToDisk(tip.getAppid(),msgMap.get("MediaId"),tpmc);
            tip.setTpId(msgMap.get("MediaId"));
            tip.setTpFile(tpmc+".jpg");
            tip.setTpLink(msgMap.get("PicUrl"));
        }
        return tip;
    }
    public static SysWxtip doGpsTip(Map<String, String> msgMap){
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(2);
        tip.setMsgCat(0);
        tip.setState(-1);
        tip.setGpsJd(msgMap.get("Location_Y"));
        tip.setGpsWd(msgMap.get("Location_X"));
        tip.setGpsSf(msgMap.get("Scale"));
        tip.setGpsMsg(msgMap.get("Label"));

        return tip;
    }

    public static SysWxtip doLinkTip(Map<String, String> msgMap) {
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(3);
        tip.setMsgCat(0);
        tip.setState(-1);

        tip.setLink(msgMap.get("Url"));
        tip.setLinkTitle(msgMap.get("Title"));
        tip.setLinkDesc(msgMap.get("Description"));


        return tip;
    }

    public static SysWxtip doVoiceTip(Map<String, String> msgMap) {
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(4);
        tip.setMsgCat(0);
        tip.setState(-1);
        if(!TextUtils.isEmpty(msgMap.get("MediaId"))){
            //保存图片
            String tpmc = tip.getOpenid()+System.currentTimeMillis();
            MediaDownUtil.saveVoiceToDisk(tip.getAppid(),msgMap.get("MediaId"),tpmc,msgMap.get("Format"));
            tip.setTpId(msgMap.get("MediaId"));
            tip.setVoiceFile(tpmc+"."+msgMap.get("Format"));
        }

        return tip;
    }

    public static SysWxtip doVideoTip(Map<String, String> msgMap) {
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(5);
        tip.setMsgCat(0);
        tip.setState(-1);
        if(!TextUtils.isEmpty(msgMap.get("MediaId"))){
            //保存图片
            String tpmc = tip.getOpenid()+System.currentTimeMillis();
            MediaDownUtil.saveVoiceToDisk(tip.getAppid(),msgMap.get("MediaId"),tpmc,msgMap.get("Format"));
            tip.setTpId(msgMap.get("MediaId"));
            tip.setVoiceFile(tpmc+"."+msgMap.get("Format"));
        }

        return tip;
    }


    public static SysWxtip doSubscribeTip(Map<String, String> msgMap) {
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(6);
        tip.setMsgCat(1);
        tip.setState(-1);
        if(!TextUtils.isEmpty(msgMap.get("EventKey"))){
            if("qrscene".startsWith(msgMap.get("EventKey"))){
                tip.setMsgContent(msgMap.get("EventKey").replaceAll("qrscene_",""));
            }else{
                tip.setMsgContent(msgMap.get("EventKey"));
                tip.setMsgType(7);
            }
        }
        return tip;
    }

    public static SysWxtip doUnSubscribeTip(Map<String, String> msgMap) {
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(8);
        tip.setMsgCat(1);
        tip.setState(-1);
        return tip;
    }
    public static SysWxtip doLinkClickTip(Map<String, String> msgMap) {
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(9);
        tip.setMsgCat(1);
        tip.setState(-1);
        tip.setMsgContent(msgMap.get("EventKey"));
        return tip;
    }
    public static SysWxtip doScanTip(Map<String, String> msgMap) {
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(10);
        tip.setMsgCat(1);
        tip.setState(-1);
        tip.setMsgContent(msgMap.get("EventKey"));
        return tip;
    }
    public static SysWxtip doLocationTip(Map<String, String> msgMap) {
        SysWxtip tip = new SysWxtip();
        tip.setMsgid(msgMap.get("MsgId"));
        tip.setOpenid(msgMap.get("FromUserName"));
        tip.setAppid(msgMap.get("ToUserName"));
        tip.setMsgDt(new Date());
        tip.setMsgType(10);
        tip.setMsgCat(1);
        tip.setState(-1);
        tip.setMsgContent(msgMap.get("EventKey"));
        tip.setGpsJd(msgMap.get("Longitude"));
        tip.setGpsWd(msgMap.get("Latitude"));
        tip.setGpsSf(msgMap.get("Precision"));
        return tip;
    }
}
