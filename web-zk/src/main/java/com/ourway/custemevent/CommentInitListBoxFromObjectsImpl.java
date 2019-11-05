package com.ourway.custemevent;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2018/1/7.
 */
public class CommentInitListBoxFromObjectsImpl implements ComponentInitSer {
    public static final String YES = "yes";

    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
            List<Map> paramsList = JsonUtil.jsonStr2List(pageControlVO.getKjInitData().toString());
            if (null == paramsList || paramsList.size() <= 0) {
                AlterDialog.alert("请传入本接口所需的JSON参数");
                return;
            }

            Map<String, Object> param = paramsList.get(0);
            if (TextUtils.isEmpty(param.get("apiUrl"))) {
                AlterDialog.alert("请传入接口参数！");
                return;
            }

            if (null != param.get("default") && YES.equalsIgnoreCase(param.get("default").toString())) {
                Listitem item = new Listitem("请选择", "");
                item.setParent(component);
                item.setSelected(true);
            }
            String label = "";
            String value = "";

            try {

                String apiURL = param.get("apiUrl").toString();
                PublicData publicData = PublicData.instantce();
                publicData.setMethod(apiURL);
                Map<String, Object> ppt = new HashMap<String, Object>(2);
                publicData.setData(JsonUtil.toJson(ppt));
                String result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false);

                ResponseMessage responseMessage = JsonUtil.getResponseMsg(result);

                if (null != responseMessage && responseMessage.getBackCode() == 0 && null != responseMessage.getBean()) {

                    List<Map<String, Object>> datas = (List<Map<String, Object>>) responseMessage.getBean();
                    if (null != datas && datas.size() > 0) {
                        for (Map<String, Object> data : datas) {
                            label = data.get("label").toString();
                            value = data.get("value").toString();
                            Listitem item = new Listitem(label, value);
                            item.setParent(component);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
