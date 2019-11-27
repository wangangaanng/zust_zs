package com.ourway.custemevent;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *<p>功能描述:复制问卷 CopyDcwjAction</p >
 *<ul>
 *<li>@param </li>
 *<li>@return </li>
 *<li>@throws </li>
 *<li>@author xuyux</li>
 *<li>@date 2019/11/27 10:37</li>
 *</ul>
 */
public class CopyDcwjAction implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("未配置参数");
        } else {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("未配置表格"));
            } else {
                BaseGrid grid = (BaseGrid) baseWindow.getFellowIfAny(_params.get("gridId").toString());
                List<Map<String, Object>> datas = grid.getSelectRowsData();
                if (null != datas && datas.size() > 1) {
                    AlterDialog.alert(I18nUtil.getLabelContent("请选择一条数据"));
                    return;
                } else if (!TextUtils.isEmpty(datas) && datas.size() == 0) {
                    AlterDialog.alert(I18nUtil.getLabelContent("请选择一条数据"));
                    return;
                } else {
                    PublicData publicData = PublicData.instantce();
                    publicData.setMethod("web/zustcommon/bckjBizDcwj/copyDcwj");
                    Map<String, Object> ppt = new HashMap<>();
                    ppt.put("owid", datas.get(0).get("owid"));
                    publicData.setData(JsonUtil.toJson(ppt));
                    try {
                        String result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false);
                        ResponseMessage responseMessage = JsonUtil.getResponseMsg(result);
                        if (null != responseMessage && responseMessage.getBackCode() == 0) {
                            AlterDialog.alert("操作成功");
                            Object models = new ArrayList();
                            grid.filter((List) models);
                            grid.display();
                        } else {
                            AlterDialog.alert(responseMessage.getErrorMess());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
