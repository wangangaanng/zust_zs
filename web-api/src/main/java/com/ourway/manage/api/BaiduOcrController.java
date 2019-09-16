package com.ourway.manage.api;

import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.baiduapi.dto.InfoDTO;
import com.ourway.baiduapi.utils.Base64ImageUtils;
import com.ourway.baiduapi.utils.HttpClientUtils;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("ocrController")
public class BaiduOcrController {
    private static final Logger log = LoggerFactory.getLogger(BaiduOcrController.class);


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
                String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                path = CommonConstant.picPath + File.separator + trueFileName;
                try {
                    Image srcFile = ImageIO.read(file.getInputStream());
                    file.transferTo(new File(path));
                    Map resultMap = new HashMap();
                    resultMap = ocrPic(path, trueFileName, Integer.parseInt(dataMap.get("type").toString()));
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
        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + ApiInfo.API_KEY + "&client_secret=" + ApiInfo.SECRET_KEY;
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
                vat.put("fileName", fileName);
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
                vat.put("fileName", fileName);
            }
        }
        return vat;
    }

}
