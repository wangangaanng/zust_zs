package com.zghzbckj.manage.service;

import com.beust.jcommander.internal.Maps;
import com.ourway.baiduapi.constants.BaiDuApiInfo;
import com.ourway.baiduapi.dto.InfoDTO;
import com.ourway.baiduapi.utils.Base64ImageUtils;
import com.ourway.baiduapi.utils.HttpClientUtils;
import com.ourway.base.utils.*;
import com.zghzbckj.base.config.Global;
import com.zghzbckj.base.model.ResponseMessage;
import com.zghzbckj.common.CommonConstant;
import com.zghzbckj.common.CommonModuleContant;
import com.zghzbckj.common.CustomerException;
import com.zghzbckj.manage.dao.CommonDao;
import com.zghzbckj.manage.entity.BckjBizYhxx;
import com.zghzbckj.manage.utils.Html2PdfUtil;
import com.zghzbckj.manage.utils.HttpUtil;
import com.zghzbckj.manage.utils.MessageUtil;
import com.zghzbckj.util.HttpBackUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <dl>
 * <dt>CommonService</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@Service("commonService")
public class CommonService {
    private static final Logger log = Logger.getLogger(CommonService.class);

    @Autowired
    BckjBizSybService bckjBizSybService;
    @Autowired
    BckjBizYhxxService bckjBizYhxxService;
    @Autowired
    CommonDao commonDao;

    public ResponseMessage getSecondMenu(Map<String, Object> mapData) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("data", JsonUtil.toJson(mapData));
        try {
            String resStr = HttpBackUtil.doPost(CommonModuleContant.BACKEJ_URL_HOST, param, "utf-8", false);
            if (!TextUtils.isEmpty(resStr)) {
                Map value = JsonUtil.jsonToMap(resStr);
                return JsonUtil.map2Bean(value, ResponseMessage.class);
            }
        } catch (IOException e) {
            log.error("获取二级栏目数据失败", e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
        return ResponseMessage.sendOK(null);
    }

    public ResponseMessage getFirtstMenu(Map<String, Object> mapData) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("data", JsonUtil.toJson(mapData));
        try {
            String resStr = HttpBackUtil.doPost(CommonModuleContant.BACK_URL_HOST, param, "utf-8", false);
            if (!TextUtils.isEmpty(resStr)) {
                Map value = JsonUtil.jsonToMap(resStr);
                return JsonUtil.map2Bean(value, ResponseMessage.class);
            }
        } catch (IOException e) {
            log.error("获取一级栏目数据失败", e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
        return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
    }

    public ResponseMessage getByType(Map<String, Object> mapData) {
        try {
//            String resStr = HttpBackUtil.doPost(CommonModuleContant.BACK_TYPE_URL_HOST, param, "utf-8", false);
            String resStr = HttpUtil.doPostJson(CommonModuleContant.BACK_TYPE_URL_HOST, JsonUtil.toJson(mapData), "UTF-8", true);
            if (!TextUtils.isEmpty(resStr)) {
                Map value = JsonUtil.jsonToMap(resStr);
                return JsonUtil.map2Bean(value, ResponseMessage.class);
            }
        } catch (IOException e) {
            log.error("获取字典表数据失败", e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, CommonConstant.ERROR_SYS_MESSAG);
        }
        return ResponseMessage.sendOK(null);
    }

    /**
     * 返回字典表 按val2排序
     *
     * @param dataMap
     * @return
     */
    public List<Map<String, Object>> getByTypeSort(Map<String, Object> dataMap) {
        return bckjBizSybService.getByTypeSort(dataMap);
    }

    @Transactional(readOnly = false)
    public void sendCode(Map<String, Object> mapData, int type) throws Exception {
        BckjBizYhxx yhxx;
        if (type == 0) {
            if (TextUtils.isEmpty(mapData.get("unionid"))) {
                throw new CustomerException("unionid必传");
            }
            yhxx = bckjBizYhxxService.getBySwZh(mapData, "unionid");
            if (null == yhxx) {
                throw new CustomerException("微信基本信息不存在，请重新进入小程序");
            } else {
                if (!TextUtils.isEmpty(yhxx.getState()) && yhxx.getState() == 1) {
                    throw new CustomerException("每个微信号只能注册一个手机，此微信已注册");
                }
            }
            BckjBizYhxx indata = bckjBizYhxxService.getBySwZh(mapData, "swZh");
            if (null != indata && yhxx != null) {
                if (!indata.getOwid().equals(yhxx.getOwid())) {
                    yhxx.setState(indata.getState());
                    bckjBizYhxxService.delete(yhxx);
                    BeanUtil.copyPropertiesIgnoreNull(yhxx, indata);
                    yhxx = indata;
                }
            }

        } else {
            //pc
            yhxx = bckjBizYhxxService.getBySwZh(mapData, "swZh");
            if (null == yhxx) {
                yhxx = new BckjBizYhxx();
                yhxx.setYhlx(3);
                yhxx.setState(0);
            }
        }
        if (null != yhxx.getState() && yhxx.getState() == 1) {
            throw new CustomerException("此手机号已经注册");
        }
        yhxx.setSwZh(MapUtils.getString(mapData, "swZh"));
        yhxx.setYzm(getRandom());
        MessageUtil.sendMessageCode(yhxx.getSwZh(), yhxx.getYzm());
        yhxx.setFssj(new Date());
        bckjBizYhxxService.saveOrUpdate(yhxx);
    }

    public static String getRandom() {
        Random rd = new Random();
        String tmp = "";
        for (int i = 0; i < 6; i++) {
            tmp += rd.nextInt(10);
        }
        return tmp;
    }

    @Transactional(readOnly = false)
    public void sendCodeForget(Map<String, Object> mapData) throws CustomerException {
        BckjBizYhxx yhxx = bckjBizYhxxService.getBySwZh(mapData, "swZh");
        if (null == yhxx) {
            throw new CustomerException("不存在此用户");
        }
        if (yhxx.getState() == 0) {
            throw new CustomerException("此手机号未注册");
        }
        yhxx.setYzm(getRandom());
        try {
            MessageUtil.sendMessageCode(yhxx.getSwZh(), yhxx.getYzm());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException("验证按发送失败");
        }
        yhxx.setFssj(new Date());
        bckjBizYhxxService.saveOrUpdate(yhxx);
    }

    /**
     * <p>方法:saveFile TODO保存到附件中心 </p>
     * <ul>
     * <li> @param file TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/24 17:09  </li>
     * </ul>
     */
    @Transactional(readOnly = false)
    public Map<String, Object> saveFile(MultipartFile file, Map mapData) throws Exception {
        String fileName = file.getOriginalFilename();
        String type = fileName.indexOf(CommonModuleContant.SPILT_POINT) != -1 ? fileName.substring(fileName.lastIndexOf(CommonModuleContant.SPILT_POINT) + 1, fileName.length()) : null;
        String realName = String.valueOf(System.currentTimeMillis());
        String trueFileName = CommonModuleContant.SWTYFILEPATH + File.separator + realName + CommonConstant.SPILT_POINT + type;
        String path = Global.getConfig(CommonModuleContant.SWTYFILEPATH);
        String allPathFile=path + trueFileName;
        File tarFile = new File(allPathFile);
        file.transferTo(tarFile);
        Map<String, Object> fileCenter = Maps.newHashMap();
        fileCenter.put("filePath", trueFileName);
        if (MapUtils.getInt(mapData, "type") == 1) {
            return fileCenter;
        }
        if (MapUtils.getInt(mapData, "type") == 3) {
           Map mapResult= ocrPic(allPathFile,trueFileName,2);
            mapResult.put("fileName", trueFileName);
            fileCenter.put("filePath", trueFileName);
           return mapResult;
        }
        String yhRefOwid = MapUtils.getString(mapData, "yhRefOwid")+CommonModuleContant.SWTYFILEPATH;
        fileCenter.put("owid", TextUtils.getUUID());
        fileCenter.put("fileClass", "BckjBizJbxx");
        fileCenter.put("fileClassId", yhRefOwid);
        fileCenter.put("fileName", realName);
        fileCenter.put("fileLabel", fileName);
        fileCenter.put("fileRandon", type);
        fileCenter.put("fileSize", file.getSize());
        fileCenter.put("fileExtion", type);
        fileCenter.put("createtime", new Date());
        commonDao.insertFile(fileCenter);
        return fileCenter;
    }

    /**
     * 小程序下拉框显示20字典表内容
     *
     * @param dataMap
     * @return List<Map>
     */
    public List<Map> getSmallRoutine(Map<String, Object> dataMap) {
        return bckjBizSybService.getSmallRoutine(dataMap);

    }

    /***
     * <p>方法:getXkkm TODO获取选课列表 </p>
     * <ul>
     * <li> @param mapData TODO</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author D.chen.g </li>
     * <li>@date 2019/10/28 11:43  </li>
     * </ul>
     */
    public Object getXkkm(Map<String, Object> mapData) {
        return this.commonDao.getXkcj(mapData);
    }

    @Transactional(readOnly = false)
    public ResponseMessage saveBckjDic(Map<String, Object> mapData) {
        try {
            Map params = Maps.newHashMap();
            params.put("xxbh", CommonConstant.XXBH);
            //开始时间 截止时间 是否报名
            if (!TextUtils.isEmpty(mapData.get("dicVal1"))) {
                params.put("kssj", mapData.get("dicVal1"));

            }
            if (!TextUtils.isEmpty(mapData.get("dicVal4"))) {
                params.put("jzsj", mapData.get("dicVal4"));
            }
            if (!TextUtils.isEmpty(mapData.get("dicVal8"))) {
                params.put("sfks", Integer.parseInt(mapData.get("dicVal8").toString()));
            }
            String url = "";
            if (!TextUtils.isEmpty(mapData.get("dicVal5"))) {
                Html2PdfUtil.doPromise(mapData.get("dicVal5").toString());
            }
            commonDao.updateXxpz(params);
            HttpUtil.doPostJson(CommonModuleContant.BACK_DIC_SAVE_HOST, JsonUtil.toJson(mapData), "UTF-8", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return ResponseMessage.sendOK(null);
    }

    public  String getToKen() {
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
        Map<String, String> headers =  Maps.newHashMap();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> bodys = Maps.newHashMap();
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
                    vat = Maps.newHashMap();
                }
                vat.put("fileName", "pic/" + fileName);
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
                vat.put("fileName", "pic/" + fileName);
            }
        } else if (3 == type) {
            bodys.put("id_card_side", "back");
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
                vat.put("fileName", "pic/" + fileName);
            }
        }

        return vat;
    }

}