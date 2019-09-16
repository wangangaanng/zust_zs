package com.zghzbckj.wechat.model;

import com.ourway.base.utils.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法:按钮菜单的基类 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2018/2/13 23:08  </li>
 * </ul>
 */
public class Button implements Serializable {
    /*view：跳转URL用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。*/
    public static final String VIEW = "view";
    /*click：点击推事件用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；*/
    public static final String CLICK = "click";
    /*scancode_push：扫码推事件用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。*/
    public static final String SCANCODE_PUSH = "scancode_push";
    /*scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息。*/
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
    /*pic_sysphoto：弹出系统拍照发图用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息*/
    public static final String PIC_SYSPHOTO = "pic_sysphoto";
    /*pic_photo_or_album：弹出拍照或者相册发图用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。*/
    public static final String PIC_SYSPHOTO_OR_ALBUM = "pic_photo_or_album";
    /*pic_weixin：弹出微信相册发图器用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。*/
    public static final String PIC_WEIXIN = "pic_weixin";
    /*location_select：弹出地理位置选择器用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。*/
    public static final String LOCATION_SELECT = "location_select";
    /*按钮名称*/
    private String name;
    /*菜单类型*/
    private String type;
    /*click事件的时候的key*/
    private String key;
    /*小程序url或者view的时候的url*/
    private String url;
    /*媒体文件id*/
    private String media_id;
    /*小程序ID*/
    private String appid;
    /*小程序页面路径*/
    private String pagepath;
    /*子菜单*/
    private List<Button> sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }

    public static Button instanceCommButton(Object type, Object name, Object key, Object url, Object media_id, Object appId, Object pageIndex) {
        Button btn = new Button();
        if (!TextUtils.isEmpty(type)&&!"-1".equalsIgnoreCase(type.toString())) {
            btn.setType(type.toString());
        }
        if (!TextUtils.isEmpty(name)) {
            btn.setName(name.toString());
        }
        if (!TextUtils.isEmpty(key) && "click".equalsIgnoreCase(type.toString())) {
            btn.setKey(key.toString());
        }
        if (!TextUtils.isEmpty(url) && "view".equalsIgnoreCase(type.toString())) {
            btn.setUrl(url.toString());
        }
        if (!TextUtils.isEmpty(url) && "miniprogram".equalsIgnoreCase(type.toString())) {
            btn.setUrl(url.toString());
        }
        if (!TextUtils.isEmpty(media_id)) {
            btn.setMedia_id(media_id.toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            btn.setAppid(appId.toString());
        }
        if (!TextUtils.isEmpty(pageIndex)) {
            btn.setPagepath(pageIndex.toString());
        }
        return btn;
    }

    public static Map<String,Object> button2Map(Button btn){
        Map<String,Object> map = new HashMap<String,Object>();
        if(!TextUtils.isEmpty(btn.getName())) {
            map.put("name", btn.getName());
        }
        if(!TextUtils.isEmpty(btn.getType())) {
            map.put("type", btn.getType());
        }

        if(!TextUtils.isEmpty(btn.getKey())) {
            map.put("key", btn.getKey());
        }
        if(!TextUtils.isEmpty(btn.getUrl())) {
            map.put("url", btn.getUrl());
        }

        if(!TextUtils.isEmpty(btn.getMedia_id())) {
            map.put("media_id", btn.getMedia_id());
        }

        if(!TextUtils.isEmpty(btn.getAppid())) {
            map.put("appid", btn.getAppid());
        }
        if(!TextUtils.isEmpty(btn.getPagepath())) {
            map.put("pagepath", btn.getPagepath());
        }
        if(null!=btn.getSub_button()&&btn.getSub_button().size()>0){
            List<Map<String,Object>> sub_button = new ArrayList<Map<String,Object>>(btn.getSub_button().size());
            for(Button button:btn.getSub_button()){
                Map<String,Object> _map =  Button.button2Map(button);
                sub_button.add(_map);
            }
            map.put("sub_button",sub_button);
        }
        return map;

    }

}