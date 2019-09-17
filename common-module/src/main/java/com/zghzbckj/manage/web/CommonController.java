package com.zghzbckj.manage.web;

import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.baiduapi.dto.InfoDTO;
import com.ourway.baiduapi.utils.Base64ImageUtils;
import com.ourway.baiduapi.utils.HttpClientUtils;
import com.ourway.base.utils.*;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.manage.service.CommonService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * <dl>
 * <dt>CommonController</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@RestController
@RequestMapping(value = "common")
public class CommonController {
    private static final Logger log = Logger.getLogger(CommonController.class);

    @Autowired
    CommonService commonService;


    @PostMapping(value = "test")
    public ResponseMessage test() {
        return ResponseMessage.sendOK("OK");
    }


    /**
     * <p>方法:getSecond TODO获取二级菜单 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/9/5 14:40  </li>
     * </ul>
     */
    @RequestMapping(value = "getSecondMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getSecondMenu(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "dicVal4");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return commonService.getSecondMenu(mapData);
        } catch (Exception e) {
            log.info("获取字典数据失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    @RequestMapping(value = "getFirtstMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getFirtstMenu(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "dicType", "webType");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return commonService.getFirtstMenu(mapData);
        } catch (Exception e) {
            log.info("获取字典数据失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


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
        type = fileName.indexOf(CommonModuleContant.SPILT_POINT) != -1 ? fileName.substring(fileName.lastIndexOf(CommonModuleContant.SPILT_POINT) + 1, fileName.length()) : null;
        if (type != null) {// 判断文件类型是否为空
            if (CommonModuleContant.PIC_PNG.equals(type.toUpperCase()) || CommonModuleContant.PIC_JPG.equals(type.toUpperCase()) || CommonModuleContant.PIC_JPEG.equals(type.toUpperCase())) {
                // 项目在容器中实际发布运行的根路径
                String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                path = CommonModuleContant.picPath + File.separator + trueFileName;
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
        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + CommonModuleContant.API_KEY + "&client_secret=" + CommonModuleContant.SECRET_KEY;
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
            CloseableHttpResponse backResponse = HttpClientUtils.doHttpsPost(CommonModuleContant.LICENSE_URL + baidToken, headers, bodys);
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
            CloseableHttpResponse backResponse = HttpClientUtils.doHttpsPost(CommonModuleContant.ID_URL + baidToken, headers, bodys);
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


    @RequestMapping(value = "getByType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getByType(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "dicType");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return commonService.getByType(mapData);
        } catch (Exception e) {
            log.info("获取字典数据失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

}