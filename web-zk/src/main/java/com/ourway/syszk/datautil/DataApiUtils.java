package com.ourway.syszk.datautil;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>方法 DataApiUtils : 通用的前端调用接口类，为springmvc服务 <p>
 * <p>说明:TODO</p>
 * <pre>
 * @author JackZhou
 * @date 2019/3/5 21:57
 * </pre>
 */
public class DataApiUtils {

    /**
     * <p>方法:getProjectSchedular 获取项目的时间进度 </p>
     * <ul>
     * <li> @param owid TODO</li>
     * <li> @param year TODO</li>
     * <li> @param fjlb TODO</li>
     * <li> @param state TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2019/3/5 21:54  </li>
     * </ul>
     */
    public  static Map<String, Object> getProjectSchedular(String projectOwid, String flowId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", projectOwid);
            params.put("flowId", flowId);
            PublicData data = PublicData.instantce();
            data.setMethod("/projectProcessApi/detailProjectSchedue");
            data.setData(JsonUtil.toJson(params));
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == 0 && !TextUtils.isEmpty(mess.getBean())) {
                return (Map<String, Object>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
