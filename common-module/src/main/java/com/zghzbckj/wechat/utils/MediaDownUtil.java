package com.zghzbckj.wechat.utils;

import com.ourway.base.utils.TextUtils;
import com.zghzbckj.base.util.CacheUtil;



import com.zghzbckj.wechat.WechatConstants;
import com.zghzbckj.wechat.model.AccessToken;
import com.zghzbckj.common.CommonConstant;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 媒体下载接口
 * </p>
 *
 * @author Jack Zhou
 * @version $Id: MediaDownUtil.java,v 0.1 2015-11-1 下午9:07:00 Jack Exp $
 */
public class MediaDownUtil {


    public static InputStream getInputStream(String accessToken, String mediaId) {
        InputStream is = null;
        String url = WechatConstants.WechatApiURL.MEDIA_GET_URL.replaceAll("ACCESSTOKEN", accessToken).replaceAll("MEDIAID", mediaId);
//		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + mediaId;
        return HttpsRequest.doDownLoad(url);
    }

    /**
     * <p>方法:saveImageToDisk 把数据保存到硬盘中 </p>
     * <ul>
     * <li> @param wxid 微信的编号</li>
     * <li> @param mediaId 文件id</li>
     * <li> @param picName 附件名称</li>
     * <li> @param picPath 附件存储路径</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/2/11 15:23  </li>
     * </ul>
     */
    public static boolean saveImageToDisk(String wxid, String mediaId, String picName) {
        try {
            AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxid, AccessToken.class);
            if (null == accessToken || TextUtils.isEmpty(accessToken.getToken())) {
                return false;
            }
            InputStream inputStream = getInputStream(accessToken.getToken(), mediaId);
            if (null == inputStream) {
                return false;
            }
            byte[] data = new byte[102400];
            int len = 0;
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(CommonConstant.SYSTEM_FILE_PATH + picName + ".jpg");
                while ((len = inputStream.read(data)) != -1) {
                    fileOutputStream.write(data, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * <p>方法:saveVoiceToDisk 把声音保存到硬盘 </p>
     * <ul>
     * <li> @param accessToken TODO</li>
     * <li> @param mediaId TODO</li>
     * <li> @param picName TODO</li>
     * <li> @param picPath TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2018/2/11 15:24  </li>
     * </ul>
     */
    public static void saveVoiceToDisk(String wxid, String mediaId, String picName, String format) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            AccessToken accessToken = CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + wxid, AccessToken.class);
            if (null == accessToken || TextUtils.isEmpty(accessToken.getToken())) {
                return;
            }
            inputStream = getInputStream(accessToken.getToken(), mediaId);
            if (null == inputStream) {
                return;
            }
            byte[] data = new byte[102400];
            int len = 0;
            fileOutputStream = new FileOutputStream(CommonConstant.SYSTEM_FILE_PATH + picName + "." + format);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
//		JSSDKCONS.accessToken = WeixinUtil.getAccessToken(WxConstants.appID, WxConstants.appSecrect);
        String mediaId = "XaFAd6m1VmWbQPlZQbJxS0XWGJ43670-V7F2sNEKBR-VrAZh-43LztnRhONHYLvI";
        try {
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // SaveImage.getPic(JSSDKCONS.accessToken.getToken(),
        // "XaFAd6m1VmWbQPlZQbJxS0XWGJ43670-V7F2sNEKBR-VrAZh-43LztnRhONHYLvI");

    }

}
