package com.ourway.utils;

import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by jackson on 2018/5/19.
 */
public class DicUtils {

    public static final String DICURL = "/dicTreeApi/listDicByParams";
    public static final String DICLIST = "/dicTreeApi/listDicByParams";

    public static Map<String, Object> getDicMapByCode(Map<String, Object> ppt){
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(ppt, DICURL);
        if (null == responseMessage || responseMessage.getBackCode() == -1) {
            return null;
        }else{
            List<Map<String, Object>> result = (List<Map<String, Object>>) responseMessage.getBean();
            if (null == result || result.size() <= 0) {
                return null;
            }else{
                for (Map<String, Object> map : result) {
                    if (map.get("dicVal1").equals(ppt.get("code"))) {
                        return map;
                    }
                }
                return result.get(0);
            }
        }
    }
}
