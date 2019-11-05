package com.ourway.custemevent;

import com.google.common.collect.Maps;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import org.zkoss.zk.ui.event.Event;

import java.util.Map;

public class FzszInitAction implements ComponentListinerSer {
    private static final String QUERY_DL_URL = "web/zustswyt/bckjBizBkzy/getNumber";

    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        //选择大类
        BaseListbox dlListBox = (BaseListbox) baseWindow.getFellowIfAny("mainTableGrid_dl");
        //剩余人数
        BaseTextbox baseDatebox = (BaseTextbox) baseWindow.getFellowIfAny("mainTableGrid_exp1");
        try {
            if (TextUtils.isEmpty(dlListBox.getSelectedItem().getValue())) {
                return;
            }
            PublicData publicData = PublicData.instantce();
            Map<String, Object> ppt = Maps.newHashMap();
            ppt.put("dl", dlListBox.getSelectedItem().getValue().toString());
            publicData.setMethod(QUERY_DL_URL);
            /**/
            publicData.setData(JsonUtil.toJson(ppt));
            String result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false);
            ResponseMessage responseMessage = JsonUtil.getResponseMsg(result);
            if (null != responseMessage && responseMessage.getBackCode() == 0 && null != responseMessage.getBean()) {
                Integer number = (Integer) responseMessage.getBean();
                baseDatebox.setValue(number.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
