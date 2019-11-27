package com.ourway.custemevent;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *<p>功能描述:保存至历年榜单 SaveListAction</p >
 *<ul>
 *<li>@param </li>
 *<li>@return </li>
 *<li>@throws </li>
 *<li>@author xuyux</li>
 *<li>@date 2019/11/27 10:38</li>
 *</ul>
 */
public class SaveListAction implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入配置参数");
        } else {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("未配置表格"));
            } else {
                BaseGrid grid = (BaseGrid) baseWindow.getFellowIfAny(_params.get("gridId").toString());
                List<Map<String, Object>> datas = grid.getResult();
                if (null == datas || datas.size() <= 0) {
                    AlterDialog.alert("数据为空");
                    return;
                } else {
                    PublicData publicData = PublicData.instantce();
                    publicData.setMethod("web/zustjy/bckjBizJypm/saveLn");
                    Map<String, Object> ppt = new HashMap<>();
                    ppt.put("isNull", "true");
                    publicData.setData(JsonUtil.toJson(ppt));
                    try {
                        String result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false);
                        ResponseMessage responseMessage = com.ourway.base.zk.utils.JsonUtil.getResponseMsg(result);
                        if (null != responseMessage && responseMessage.getBackCode() == 0) {
                            AlterDialog.alert("操作成功");
                        } else {
                            AlterDialog.alert(responseMessage.getErrorMess());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
