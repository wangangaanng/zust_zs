package com.ourway.manage.api;

import com.ourway.base.dao.BaseDao;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.manage.WebConstants;
import com.ourway.manage.service.CustomDicService;
import com.ourway.sys.dao.SysDicValueDao;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.service.DicService;
import com.ourway.sys.service.DicValueService;
import com.ourway.sys.utils.I18nUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dicValue")
public class DicValueController {
    private static final Logger log = LoggerFactory.getLogger(DicValueController.class);

    @Autowired
    DicService dicService;
    @Autowired
    DicValueService dicValue;
    @Autowired
    CustomDicService customDicService;
    @Autowired
    SysDicValueDao sysDicValueDao;
    @Autowired
    private BaseDao baseDao;


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
            return ResponseMessage.sendOK(customDicService.getByType(mapData));
        } catch (Exception e) {
            log.info("获取一级菜单失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = "getValueByDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getValueByDic(@RequestBody Map<String, Object> dataMap) {
        try {
            //判断owid是否为空
            String type = dataMap.get("dicType").toString();
            if (TextUtils.isEmpty(type)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "null");
            }
            String order = MapUtils.getString(dataMap, "orderBy");
            return ResponseMessage.sendOK(dicService.listDicByType(Integer.parseInt(dataMap.get("dicType").toString()), order));
        } catch (Exception e) {
            log.info("获取一级菜单失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = "saveDic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveDic(@RequestBody Map<String, Object> dataMap) {
        try {
            //判断owid是否为空
            String type = dataMap.get("dicType").toString();
            if (TextUtils.isEmpty(type)) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "null");
            }
            OurwaySysDic dic = dicService.getSingleDicByType(Integer.parseInt(type));
            List<OurwaySysDicValue> dicValList = dicValue.listDicValuesByRefOwid(dic);
            if (TextUtils.isEmpty(dicValList) || dicValList.size() == 0) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "查无");
            }
            OurwaySysDicValue dicVal = dicValList.get(0);
            if (!TextUtils.isEmpty(dataMap.get("dicVal1"))) {
                dicVal.setDicVal1(dataMap.get("dicVal1").toString());
            }
            if (!TextUtils.isEmpty(dataMap.get("dicVal2"))) {
                dicVal.setDicVal2(dataMap.get("dicVal2").toString());
            }
            if (!TextUtils.isEmpty(dataMap.get("dicVal3"))) {
                dicVal.setDicVal3(dataMap.get("dicVal3").toString());
            }
            if (!TextUtils.isEmpty(dataMap.get("dicVal4"))) {
                dicVal.setDicVal4(dataMap.get("dicVal4").toString());
            }
            if (!TextUtils.isEmpty(dataMap.get("dicVal5"))) {
                dicVal.setDicVal5(dataMap.get("dicVal5").toString());
            }
            if (!TextUtils.isEmpty(dataMap.get("dicVal6"))) {
                dicVal.setDicVal6(dataMap.get("dicVal6").toString());
            }
            if (!TextUtils.isEmpty(dataMap.get("dicVal7"))) {
                dicVal.setDicVal7(dataMap.get("dicVal7").toString());
            }
            if (!TextUtils.isEmpty(dataMap.get("dicVal8"))) {
                dicVal.setDicVal8(dataMap.get("dicVal8").toString());
            }

            if (!TextUtils.isEmpty(dataMap.get("memo"))) {
                dic.setMemo(dataMap.get("memo").toString());
                dicService.saveOrUpdate(dic);
                dicVal.setMemo(dataMap.get("memo").toString());
            }
            dicValue.saveOrUpdate(dicVal);
            CacheUtil.setVal(WebConstants.SWYT_SYSTEM_PARAM, dicVal);
            return ResponseMessage.sendOK(dicVal);
        } catch (Exception e) {
            log.info("保存字典表值失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = "getWebType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getWebType(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            mapData.put("dicType", 100001);
            List<Map<String, Object>> list = customDicService.getByType(mapData);
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (Map<String, Object> map : list) {
                String label = map.get("dicVal1").toString();
                String value = map.get("dicVal2").toString();
                map.clear();
                map.put("label", label);
                map.put("value", value);
                mapList.add(map);
            }
            return ResponseMessage.sendOK(mapList);
        } catch (Exception e) {
            log.info("获取字典数据失败：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    @RequestMapping(value = "getSeclm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getSeclm(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "dicVal4");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return customDicService.getSeclm(mapData);
        } catch (Exception e) {
            log.info("获取二级栏目：" + e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    @RequestMapping(value = "listModel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage qyAddPro(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return customDicService.listModel(mapData);
        } catch (Exception e) {
            log.error("{}：" + e.toString(), e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(value = "fileCenter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage fileCenterDsf(HttpServletRequest request, HttpServletResponse response,
                                         PublicDataVO data) {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", "文章附件");
        map.put("fileExt", "PDF,DOC,DOCX,JPG,PNG,JPEG,RAR,ZIP,XLS,XLSX");
        map.put("fileDesc", "只能上传.pdf文件和.doc文件和.docx文件和.jpg文件和.png文件和.jpeg文件和.rar文件和.zip文件和.xls文件和.xlsx文件");
        map.put("fileTemplate", null);
        map.put("fileTemplateExten", "");
        map.put("fileCode", "article");
        map.put("fileNum", "-1");
        datas.add(map);
        return ResponseMessage.sendOK(datas);
    }


    @RequestMapping(value = "fileJsfj", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage fileJsfj(HttpServletRequest request, HttpServletResponse response,
                                         PublicDataVO data) {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", "竞赛附件");
        map.put("fileExt", "PDF,DOC,DOCX,JPG,PNG,JPEG,RAR,ZIP,XLS,XLSX");
        map.put("fileDesc", "只能上传.pdf文件和.doc文件和.docx文件和.jpg文件和.png文件和.jpeg文件和.rar文件和.zip文件和.xls文件和.xlsx文件");
        map.put("fileTemplate", null);
        map.put("fileTemplateExten", "");
        map.put("fileCode", "BckjBizJbxx");
        map.put("fileNum", "-1");
        datas.add(map);
        return ResponseMessage.sendOK(datas);
    }



    @RequestMapping(value = "removeFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeFiles(PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            ValidateMsg validateMsg = ValidateUtils.isEmpty(mapData, "");
            if (!validateMsg.getSuccess()) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, validateMsg.toString());
            }
            return customDicService.removeFiles(mapData);
        } catch (Exception e) {
            log.error("{}：" + e.toString(), e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }

    /**
     * 处理控制开关
     *
     * @param type
     * @param dataVO
     * @return
     */
    @RequestMapping(value = "saveDic/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveDic(@PathVariable("type") Integer type, PublicDataVO dataVO) {
        try {
            Map<String, Object> mapData = JsonUtil.jsonToMap(dataVO.getData());
            //判断owid是否为空
            if (null == type) {
                return ResponseMessage.sendError(ResponseMessage.FAIL, "type为空");
            }
            return customDicService.saveDic(mapData, type);
        } catch (Exception e) {
            log.error("{}：" + e.toString(), e);
            return ResponseMessage.sendError(ResponseMessage.FAIL, "系统繁忙");
        }
    }


    @RequestMapping(
            value = {"listDic/{type}"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public ResponseMessage listDic(@PathVariable("type") Integer type, HttpServletRequest request, PublicDataVO data) {
        if (null == type) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent("public.common.noargs", data.getCurrLanguage()));
        } else {
            List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
            return customDicService.listEjlm(filters, type);
        }
    }
}
