package com.ourway.custemevent;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenChao on 2018/5/18.
 */
public class TagRejectAction implements ComponentListinerSer {
    public TagRejectAction() {
    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();

        try {
            Map e = window.bindAll2Ppt(true);
            List paramsList = JsonUtil.jsonStr2List(windowParams.toString());
            Map _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert("请定义dataList");
            } else {
                BaseGrid baseGrid = window.getBaseGrid();
                if (null == baseGrid) {
                    AlterDialog.alert("未获取baseGrid");

                } else {
                    List selectRows = baseGrid.getSelectRows();
                    if (null == selectRows || selectRows.size() <= 0) {
                        AlterDialog.alert("请选择需要操作的记录");
                        return;
                    }
                    Iterator i$1 = selectRows.iterator();

                    while (i$1.hasNext()) {
                        Row selectRow = (Row) i$1.next();
                        Map data = (Map) selectRow.getValue();
                        /**调用接口**/
                        PublicData publicData = PublicData.instantce();
                        publicData.setMethod("web/zustjy/bckjBizJybm/backRejectOne");
                        Map<String, Object> ppt = new HashMap<String, Object>(2);
                        ppt.put("owid", data.get("owid"));
                        ppt.put("bmdx", 0);
                        publicData.setData(JsonUtil.toJson(ppt));
                        String result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false);
                        ResponseMessage responseMessage = JsonUtil.getResponseMsg(result);
                        if (null != responseMessage && responseMessage.getBackCode() == 0) {
                            AlterDialog.alert("审核成功！");
                        } else {
                            AlterDialog.alert(responseMessage.getErrorMess());
                        }
                        window.reloadPpt(true);
                    }
                    window.getBaseGrid().refreshGrid();
                    window.getBaseGrid().display();
                    window.detach();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void saveTag(String label) {
        try {
            String url = "projectLabelController/saveLabel";
            PublicData publicData = PublicData.instantce();
            publicData.setMethod(url);
            Map<String, Object> ppt = new HashMap<String, Object>(2);
            ppt.put("label", label);
            publicData.setData(JsonUtil.toJson(ppt));
            HttpUtils.doPost(publicData, BaseConstants.UTF8, false);
        } catch (Exception e) {

        }
    }


}

