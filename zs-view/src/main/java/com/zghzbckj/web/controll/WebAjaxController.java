package com.zghzbckj.web.controll;

import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.baiduapi.dto.InfoDTO;
import com.ourway.baiduapi.utils.Base64ImageUtils;
import com.ourway.baiduapi.utils.HttpClientUtils;
import com.ourway.base.utils.*;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.web.constant.CommonConstant;
import com.zghzbckj.web.constant.Constant;
import com.zghzbckj.web.model.PublicData;
import com.zghzbckj.web.utils.UnionHttpUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>方法 WebAjaxController : <p>
 * <p>说明:统一对外接口类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/21 22:30
 * </pre>
 */
@Controller
@RequestMapping(value = "/webAjax")
public class WebAjaxController {

    private static final Logger log = Logger.getLogger(WebAjaxController.class);

    private ResponseMessage responseMessage;

    /**
     * pc端文件上传
     *
     * @param dataVO
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "picUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage picUpload(PublicDataVO dataVO, @RequestParam(required = false) MultipartFile file, final HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "type");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }

        if (null == file || file.isEmpty()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "图片数据空");
        }

        String path = null;// 文件路径
        String type = null;// 文件类型
        String zippath = null;// 压缩文件路径
        String fileName = file.getOriginalFilename();// 文件原名称
        type = fileName.indexOf(CommonConstant.SPILT_POINT) != -1 ? fileName.substring(fileName.lastIndexOf(CommonConstant.SPILT_POINT) + 1, fileName.length()) : null;
        if (type != null) {// 判断文件类型是否为空
            if (CommonConstant.PIC_PNG.equals(type.toUpperCase()) || CommonConstant.PIC_JPG.equals(type.toUpperCase()) || CommonConstant.PIC_JPEG.equals(type.toUpperCase())) {
                // 项目在容器中实际发布运行的根路径
                byte[] picbyte = file.getBytes();
                float picsize = (float) picbyte.length / 1024 / 1024;
                System.out.println("图片大小：" + picsize + "MB");
                if (picsize > 5) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "图片不大于5MB");
                }
                String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                path = CommonConstant.picPath + File.separator + trueFileName;
                try {
                    Image srcFile = ImageIO.read(file.getInputStream());
                    file.transferTo(new File(path));
                    Map resultMap = new HashMap();
                    resultMap = ocrPic(path, trueFileName, Integer.parseInt(dataMap.get("type").toString()));
//                    System.out.println(JackSonJsonUtils.toJson(resultMap));
                    return ResponseMessage.sendOK(resultMap);
                } catch (IOException e) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "upload failed");
                }
            } else {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "not picturefile");
            }
        } else {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "file type is null");
        }
    }

    public static String getToKen() {
        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + CommonConstant.API_KEY + "&client_secret=" + CommonConstant.SECRET_KEY;
        CloseableHttpResponse response = HttpClientUtils.doHttpsGet(access_token_url, (String) null);
        String result = HttpClientUtils.toString(response);
        Map tokenMap = JsonUtil.jsonToMap(result);
        if (null != tokenMap && !TextUtils.isEmpty(tokenMap.get("access_token"))) {
            BaiDuApiInfo.TOKEN = tokenMap.get("access_token").toString();
            return BaiDuApiInfo.TOKEN;
        } else {
            return null;
        }
    }

    public Map ocrPic(String path, String fileName, Integer type) throws Exception {
        Map<String, String> vat = new HashMap<>();
        InfoDTO resultBack = Base64ImageUtils.getImageStrFromPath(path);
        String baidToken = getToKen();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", (String) resultBack.getValue());
        bodys.put("detect_direction", "false");
        if (1 == type) {
            bodys.put("accuracy", "high");//可选值：normal,high参数选normal或为空使用快速服务；选择high使用高精度服务，但是时延会根据具体图片有相应的增加
            CloseableHttpResponse backResponse = HttpClientUtils.doHttpsPost(CommonConstant.LICENSE_URL + baidToken, headers, bodys);
            String result = HttpClientUtils.toString(backResponse);
            if (!TextUtils.isEmpty(result)) {
                Map idcardDTO = JackSonJsonUtils.jsonToMap(result);
                vat = (Map<String, String>) idcardDTO.get("words_result");
                if (null == vat) {
                    vat = new HashMap(1);
                }
                vat.put("fileName", "pic/" + fileName);
            }
        } else if (2 == type) {
            bodys.put("id_card_side", "front");
            bodys.put("detect_risk", "true");
            CloseableHttpResponse backResponse = HttpClientUtils.doHttpsPost(CommonConstant.ID_URL + baidToken, headers, bodys);
            String result = HttpClientUtils.toString(backResponse);
            if (!TextUtils.isEmpty(result)) {
                Map idcardDTO = JackSonJsonUtils.jsonToMap(result);
                vat = (Map<String, String>) idcardDTO.get("words_result");
                if (null == vat) {
                    vat = new HashMap(1);
                }
                vat.put("image_status", idcardDTO.get("image_status").toString());
                vat.put("fileName", "pic/" + fileName);
            }
        }
        return vat;
    }


    @RequestMapping(value = "executeAPI", method = RequestMethod.POST)//原本是POST
    @ResponseBody
    @CrossOrigin("*")
    public String executeApi(HttpServletRequest request, PublicData data) {
        /*发起请求*/
        String result = "";
        ResponseMessage mess = null;
        try {
            result = UnionHttpUtils.doPost(data);
            if (TextUtils.isEmpty(result)) {
                return JsonUtil.toJson(ResponseMessage.sendError(Constant.FAIL, Constant.ERROR_FAIL));
            }
            log.info(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            mess = ResponseMessage.sendError(Constant.FAIL, e.getMessage());
        }
        return JsonUtil.toJson(mess);
    }



    @RequestMapping(value = "execItemApi", method = RequestMethod.POST)//原本是POST
    @ResponseBody
    @CrossOrigin("*")
    public String execItemApi(HttpServletRequest request, PublicData data) {
        /*发起请求*/
        String result = null;
        ResponseMessage mess = null;
        try {
            Map map=JsonUtil.jsonToMap(data.getData());
            result=UnionHttpUtils.doPost(Constant.URL_HOST+data.getMethod(),map,"utf-8");
//            result=HttpClientUtils.ajaxPostJson(Constant.URL_HOST+data.getMethod(),data.getData());
            if (TextUtils.isEmpty(result)) {
                return JsonUtil.toJson(ResponseMessage.sendError(Constant.FAIL, Constant.ERROR_FAIL));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            mess = ResponseMessage.sendError(Constant.FAIL, e.getMessage());
        }
        return JsonUtil.toJson(mess);
    }


//    @RequestMapping(value = "/getUserId", method = RequestMethod.POST)
//    @ResponseBody
//    public String newest(HttpServletRequest request) {
//        String code = request.getParameter("code");
//        String wxid = request.getParameter("wxid");
//        HttpSession session = request.getSession();
//        WxUserModel user = null;
//        user = (WxUserModel) session.getAttribute(CommonConstant.WX_COMP_USER_SESSION);
//        if (null != user) {
//            return JsonUtil.toJson(user);
//        }
//        log.info(code + "================================================" + wxid);
//        if (TextUtils.isEmpty(code) || TextUtils.isEmpty(wxid)) {
//            return "error";
//        }
//        user = WeixinUtils.getWxCompMemb(wxid, code);
//        if(null!=user){
//            session.setAttribute(CommonConstant.WX_COMP_USER_SESSION,user);
//            return JsonUtil.toJson(user);
//        }
//        return "error";
//
//    }


//    @ResponseBody
//    @RequestMapping(value = "signWx", method = RequestMethod.POST)//原本是POST
//    public String signWx(HttpServletRequest request) {
//        responseMessage = new ResponseMessage();
//        String url = request.getParameter("urlStr");
//        String wxid = request.getParameter("wxid");
//        log.info("wxid" + wxid);
//        JSTickets jsTickets = (JSTickets) CommonConstant.weixin_map.get(WechatConstants.WECHAT_JS_REDIS_PREX + wxid);
//        Map<String, String> map = JSSDKSign.sign(jsTickets.getTicket(), url);
//        SysWxconfig sysWxconfig = (SysWxconfig) CommonConstant.weixin_map.get(WechatConstants.WECHAT_REDIS_CONFIG_PREX + wxid);
//        map.put("appId", sysWxconfig.getWeAppid());
//        responseMessage.setBackCode(0);
//        responseMessage.setBean(map);
//        return JsonUtil.toJson(responseMessage);
//    }

}
