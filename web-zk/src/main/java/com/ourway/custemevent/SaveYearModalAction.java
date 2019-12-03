package com.ourway.custemevent;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import org.zkoss.zk.ui.event.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaveYearModalAction implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Map<String, Object> ppt = baseWindow.bindAll2Ppt(true);
        PublicData publicData = PublicData.instantce();
        publicData.setMethod("web/zustjy/bckjBizJypm/saveLn");
        publicData.setData(JsonUtil.toJson(ppt));
        try {
            String result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false);
            ResponseMessage responseMessage = JsonUtil.getResponseMsg(result);
            if (null != responseMessage && responseMessage.getBackCode() == 0) {
                AlterDialog.alert("操作成功");
            } else {
                AlterDialog.alert(responseMessage.getErrorMess());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseWindow.detach();
    }
}
