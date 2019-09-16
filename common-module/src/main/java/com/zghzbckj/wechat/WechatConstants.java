package com.zghzbckj.wechat;


import java.util.HashMap;
import java.util.Map;

/**
 * <p>方法: 微信端通用管理 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2018/2/10 16:48  </li>
 * </ul>
 */
public final class WechatConstants {
    /*微信在redis中存储的前缀*/
    public static final String WECHAT_REDIS_PREX = "config.wechat.";
    public static final String WECHAT_REDIS_CONFIG_PREX = "config.wechat.config.";
    public static final String WECHAT_JS_REDIS_PREX = "config.wechat.js.";

    /*用来做不同微信号，采用开发者模式后，通过不同的接口来处理里面的消息，若未设置，通过系统默认接口*/
    public static Map<String, String> APP_MSG_MAP = new HashMap<String, String>();

    /*微信相关的URL*/
    public final class WechatApiURL {
        /*会话中的媒体文件下载*/
        public static final String MEDIA_GET_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESSTOKEN&media_id=MEDIAID";

        /*微信把长连接转成短链接*/
        public static final String URL_LONG2SHORT = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
        /*生成二维码*/
        public static final String URL_QRCODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
        public static final String URL_IMG_QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
        // 获取access_token的接口地址（GET） 限200（次/天）
        public final static String URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        /*获取jsticket的令牌*/
        public final static String URL_JSTICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESSTOKEN&type=jsapi";
        /*创建菜单接口*/
        public final static String URL_MENUCREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        /*微信发送客服消息*/
        public final static String URL_KF_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        /*通过code换取网页授权access_token*/
        public final static String URL_USER_CODE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        /*拉取用户信息(需scope为 snsapi_userinfo)*/
        public final static String URL_USERINFO_SNSAPIUSERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        public final static String URL_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        /*发送消息模板*/
        public final static String URL_TEMPMSG = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        /*发送消息模板*/
        public final static String URL_CUSTMSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        /*获取关注着*/
        public final static String URL_USERLIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        /*根据openid获取用户信息*/
        public final static String URL_USERLIST_ONE = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        /*微信统一下单接口*/
        public final static String URL_UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        /*批量获取用户信息*/
        public final static String URL_USERINFOBATCH = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
        /*公众号发送图文*/
        public final static String URL_SENDPIC = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        /*上传多媒体文件到服务器*/
        public final static String URL_UPLOADMEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        /*下载多媒体文件*/
        public final static String URL_DOWNLOADMEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        /*企业号获取token*/
        public final static String URL_COMPANY_ACCESSTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=APPID&corpsecret=APPSECRET";
        /*企业号，根据code获取用户信息*/
        public final static String URL_COMPANY_USERINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
        /*企业号，信息发送*/
        public final static String URL_COMPANY_MESSSEND = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
        /*用户id转openId*/
        public final static String URL_COMPANY_USER2OPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=ACCESS_TOKEN";
        /*企业号成员信息*/
        public final static String URL_COMPANY_USERDETAIL = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
        /*企业号的jsTicket*/
        public final static String URL_COMPANY_JSTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN";
        /*企业号下载文件*/
        public final static String URL_COMPANY_MEDIA = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

        /*获取小程序accessToken*/
        public final static String URL_MINIPROGRAMM_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        /*发送消息模板*/
        public final static String URL_MINIPROGRAMM_TEMPLATEMESSAGE = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
        public final static String URL_COMP_CREATEUSER = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
        public final static String URL_COMP_INVITEEUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/invite?access_token=ACCESS_TOKEN";

    }


}
