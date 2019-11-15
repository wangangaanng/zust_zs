package com.zghzbckj.manage.web;

import com.google.common.collect.Maps;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.zghzbckj.CommonConstants;
import com.zghzbckj.base.model.PublicDataVO;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.manage.service.CommonService;
import com.zghzbckj.wechat.WechatConstants;
import com.zghzbckj.wechat.model.AccessToken;
import com.zghzbckj.wechat.service.AccessTokenInit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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


    @RequestMapping(value = "getXkkm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getXkkm(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "dicType", "dicVal5");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return ResponseMessage.sendOK(commonService.getXkkm(mapData));
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("获取选课成绩失败：" + e);
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
        String fileName = file.getOriginalFilename();// 文件原名称
        type = fileName.indexOf(CommonModuleContant.SPILT_POINT) != -1 ? fileName.substring(fileName.lastIndexOf(CommonModuleContant.SPILT_POINT) + 1, fileName.length()) : null;
        if (type != null) {// 判断文件类型是否为空
            if (CommonModuleContant.PIC_PNG.equals(type.toUpperCase()) || CommonModuleContant.PIC_JPG.equals(type.toUpperCase()) || CommonModuleContant.PIC_JPEG.equals(type.toUpperCase())) {
                // 项目在容器中实际发布运行的根路径
                byte[] picbyte = file.getBytes();
                float picsize = (float) picbyte.length / 1024 / 1024;
                if (picsize > 5) {
                    return ResponseMessage.sendError(ResponseMessage.FAIL, "图片不大于5MB");
                }
                String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                path = CommonModuleContant.picPath + File.separator + trueFileName;
                try {
                    file.transferTo(new File(path));
                    Map resultMap  = commonService.ocrPic(path, trueFileName, Integer.parseInt(dataMap.get("type").toString()));
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

    @RequestMapping(value = "getOneByType/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getOneByType(@PathVariable String type, HttpServletResponse response, PublicDataVO dataVO) {
        try {
            Map mapData = Maps.newHashMap();
            mapData.put("dicType", type);
            return ResponseMessage.sendOK(commonService.getByType(mapData));
        } catch (Exception e) {
            log.error(e + "初始BckjBizMidcompNews\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }


    @RequestMapping(value = "saveInfo/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveInfo(@PathVariable String type, PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            mapData.put("dicType", type);
            //判断id是否为
            return commonService.saveBckjDic(mapData);
        } catch (Exception e) {
            log.error(e + "保存信息失败\r\n" + e.getStackTrace()[0], e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstants.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 返回字典表 按val2排序
     *
     * @param dataVO
     * @return
     */
    @PostMapping("getByTypeSort")
    @ResponseBody
    public ResponseMessage getByTypeSort(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "dicType");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(commonService.getByTypeSort(dataMap));
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    /**
     * 小程序下拉框显示20字典表内容
     *
     * @param dataVO
     * @return ResponseMessage
     */
    @PostMapping("getSmallRoutine")
    @ResponseBody
    public ResponseMessage getSmallRoutine(PublicDataVO dataVO) {
        try {
            Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "text", "dicType");
            if (!msg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
            }
            return ResponseMessage.sendOK(commonService.getSmallRoutine(dataMap));
        } catch (Exception e) {
            log.error(CommonConstant.ERROR_MESSAGE, e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
    }

    @RequestMapping(value = "sendCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage sendCode(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "swZh", "type");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            int type = MapUtils.getInt(mapData, "type");
            if (type == 1) {
                commonService.sendCodeForget(mapData);
            } else {
                commonService.sendCode(mapData, type);
            }
            return ResponseMessage.sendOK(Boolean.TRUE);
        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("发送验证码失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /**
     * <p>方法:pictureUp TODO上传到文件中心 </p>
     * <ul>
     * <li> @param dataVO TODO</li>
     * <li>@return com.zghzbckj.base.model.ResponseMessage  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/24 17:03  </li>
     * </ul>
     */
    @CrossOrigin("*")
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage fileUpload(PublicDataVO dataVO, @RequestParam(required = false) MultipartFile file) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "type");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            if (null == file || file.isEmpty()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "文件数据空");
            }
            byte[] picbyte = file.getBytes();
            float picsize = (float) picbyte.length / 1024 / 1024;
            if (picsize > 5) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "文件不大于5MB");
            }


                return ResponseMessage.sendOK(commonService.saveFile(file, mapData));

        } catch (CustomerException e) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, e.getMsgDes());
        } catch (Exception e) {
            log.info("上传到文件中心失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = "getToken", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getToken(PublicDataVO publicDataVO) {
        AccessTokenInit.init();
        AccessToken accessToken = com.zghzbckj.base.util.CacheUtil.getVal(WechatConstants.WECHAT_REDIS_PREX + "wx618803b392a8c474", AccessToken.class);
        return ResponseMessage.sendOK(accessToken.getToken());
    }


}