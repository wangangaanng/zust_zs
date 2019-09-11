package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

public class MeunRelatedSaveAction implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        Map ppt = window.bindAll2Ppt(true);
        List paramsList = JsonUtil.jsonStr2List(windowParams.toString());
        Object policy = window.getPpt();
        Map _params = (Map) paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("url"))) {
            AlterDialog.alert("请定义需要提交保存的API接口");
            return;
        } else {
            if (!TextUtils.isEmpty(_params.get("gridId"))) {
                String[] message = _params.get("gridId").toString().split("\\,");
                String[] messArr = message;
                int length = message.length;

                for (int i$ = 0; i$ < length; ++i$) {
                    String s = messArr[i$];
                    BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(s);
                    if (null != baseGrid) {
                        ppt.put(s, GridUtils.instance().getAllDatas(baseGrid, true));
//                        ppt.put(s, window.getGridUtils().getAllDatas(baseGrid, true));
                    }
                }
            }

            ResponseMessage var15 = JsonPostUtils.executeAPI(ppt, _params.get("url").toString());
            if (null == var15) {
                AlterDialog.alert("操作失败");
            } else if (var15.getBackCode() == 0) {
                if (var15.getBean() instanceof Map) {
                    window.setPpt((Map) var15.getBean());
                }

                if (TextUtils.isEmpty(_params.get("gridFresh")) && null != window.getBaseGrid()) {
                    if (!TextUtils.isEmpty(window.getSelRowId())) {
                        GridUtils.instance().refreshRow(window.getPpt(), window.getBaseGrid(), window.getSelRowId());
//                        window.getGridUtils().refreshRow(window.getPpt(), window.getBaseGrid(), window.getSelRowId());
                    } else {
                        GridUtils.instance().addNewRow(window.getPpt(), window.getBaseGrid(), window);
//                        window.getGridUtils().addNewRow(window.getPpt(), window.getBaseGrid(), window);
                    }
                }

                if (!TextUtils.isEmpty(_params.get("freshTree")) && "1".equalsIgnoreCase(_params.get("freshTree").toString())) {
                    window.loadTreeData();
                }

                if (!TextUtils.isEmpty(_params.get("gridFresh")) && "1".equalsIgnoreCase(_params.get("gridFresh").toString()) && null != window.getBaseGrid()) {
                    window.getBaseGrid().refreshGrid();
                    window.getBaseGrid().display();
                }

                if (!TextUtils.isEmpty(_params.get("refresh")) && "1".equalsIgnoreCase(_params.get("refresh").toString())) {
                    window.reloadPpt(true);
                }

                if (!TextUtils.isEmpty(_params.get("pageType"))) {
                    window.setWindowType(Integer.parseInt(_params.get("pageType").toString()));
                    window.reloadButton();
                }

                if (!TextUtils.isEmpty(_params.get("tips"))) {
                    AlterDialog.alert(I18nUtil.getLabelContent(_params.get("tips").toString()));
                }

                if (TextUtils.isEmpty(_params.get("refresh")) || "0".equalsIgnoreCase(_params.get("refresh").toString())) {
                    root.closeTabWin(window);
                }
            } else {
                AlterDialog.alert(var15.getErrorMess());
            }
        }
    }
}
