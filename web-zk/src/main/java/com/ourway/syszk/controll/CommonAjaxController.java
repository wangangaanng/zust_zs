package com.ourway.syszk.controll;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.CookieUtils;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.zpspring.SpringZkBaseControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


/**
 * <dl>
 * <dt>AjaxController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019-06-19</dd>
 * </dl>
 *
 * @author xby
 */
@Controller
public class CommonAjaxController extends SpringZkBaseControl {
    /**
     * <p>功能描述:executeAPI 前端统一调用接口</p >
     * <ul>
     * <li>@param data </li>
     * <li>@return com.ourway.base.zk.models.ResponseMessage</li>
     * <li>@throws </li>
     * <li>@author xubiyu</li>
     * <li>@date 2019-06-19 10:13</li>
     * </ul>
     */
    @RequestMapping(value = "executeAPI")
    @ResponseBody
    public ResponseMessage executeAPI(PublicData data, HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.setRequest(request);
        response.setHeader("Access-Control-Allow-Origin", "*");
        PublicData publicData = PublicData.instantce();
        publicData.setMethod(data.getMethod());
        publicData.setData(data.getData());
        String result = "";
        response.setContentType("application/json;charset=utf-8");
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            if(!TextUtils.isEmpty(result)){
                return getResponseMsg(result);
            }else{
                return ResponseMessage.sendError(-1, "调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.sendError(-1, "调用失败");
        }
    }

    private ResponseMessage getResponseMsg(String result){
        Map<String,Object> resMap =JsonUtil.jsonToMap(result);
        ResponseMessage responseMessage = JsonUtil.map2Bean(resMap,ResponseMessage.class);
        return responseMessage;
    }

    /**
     * <p>功能描述:uploadImg 上传文件返回文件名</p >
     * <ul>
     * <li>@param file </li>
     * <li>@return java.lang.String</li>
     * <li>@throws </li>
     * <li>@author xubiyu</li>
     * <li>@date 2019-06-24 16:38</li>
     * </ul>
     */
    @RequestMapping(value = "uploadImg")
    @ResponseBody
    public ResponseMessage uploadImg(@RequestParam("file") MultipartFile file) {
        try {
            //旧的名称
            String oldFileName = file.getOriginalFilename();
            String suffix = oldFileName.indexOf(".") != -1 ? oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length()) : null;
            //新的名称
            String fileName = "" + System.currentTimeMillis() + suffix;
            if (!uploadImg(file.getInputStream(), fileName, ZKConstants.SYSTEM_FILE_PATH)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "上传失败");
            }
            return ResponseMessage.sendOK(fileName);
        } catch (IOException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "上传失败");
        }
    }


    /**
     * <p>功能描述:uploadImgForBase64 上传base64</p >
     * <ul>
     * <li>@param data </li>
     * <li>@return java.lang.String</li>
     * <li>@throws </li>
     * <li>@author xubiyu</li>
     * <li>@date 2019-06-25 10:56</li>
     * </ul>
     */
    @RequestMapping(value = "uploadImgForBase64")
    @ResponseBody
    @CrossOrigin("*")
    public ResponseMessage uploadImgForBase64(PublicData data) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
            String fileName = uploadImageFromBase64(dataMap.get("base64Data").toString(), "signature" + System.currentTimeMillis(), ZKConstants.SYSTEM_FILE_PATH);
            if (TextUtils.isEmpty(fileName)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "上传失败");
            }
            return ResponseMessage.sendOK(fileName);
        } catch (IOException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "上传失败");
        }
    }

    private String uploadImageFromBase64(String base64Data, String fileName, String outPath) throws IOException {
        String dataPrix = "";
        String data = "";
        String[] d = base64Data.split("base64,");
        if (d != null && d.length == 2) {
            dataPrix = d[0];
            data = d[1];
        } else {
            return "";
        }

        String suffix = "";
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            suffix = ".png";
        } else {
            return "";
        }

        String tempFileName = fileName + suffix;
        BASE64Decoder decoder = new BASE64Decoder();
        String path = "";
        FileOutputStream out = null;
        try {
            byte[] b = decoder.decodeBuffer(data);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] = (byte) (b[i] + 256);
                }
            }
            path = outPath + tempFileName;
            out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return tempFileName;
        } catch (IOException var17) {
            return "";
        }
    }


    private boolean uploadImg(InputStream inputStream, String fileName, String outPath) throws IOException {
        byte[] data = new byte[10240];
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outPath + fileName);
            int len;
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
            return true;
        } catch (Exception var11) {
            return false;
        } finally {
            if (null != fileOutputStream) {
                fileOutputStream.close();
            }
            if (null != inputStream) {
                inputStream.close();
            }
        }
    }

}