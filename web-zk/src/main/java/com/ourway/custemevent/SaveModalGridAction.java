package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaveModalGridAction implements ComponentListinerSer {
    public SaveModalGridAction() {

    }

    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        BaseWindow parentWindow = baseWindow.getTopWindow();
        //当前页面，模态框
        Map<String, Object> ppt = baseWindow.bindAll2Ppt(true);
        //父级页面，多tab
        Map<String, Object> parentPpt = baseWindow.getParentPpt();
        BaseGrid parentGrid = (BaseGrid) parentWindow.getFellowIfAny("dataList1");
        List<Map<String, Object>> list = parentGrid.getAllDatas();
        if (TextUtils.isEmpty(list)) {
            list = new ArrayList<>();
        }
        for (Map<String, Object> objectMap : list) {
            if (!TextUtils.isEmpty(objectMap.get("tmsx")) && objectMap.get("tmsx").equals(ppt.get("tmsx"))) {
                AlterDialog.alert("该题目序号已存在，请直接修改");
                return;
            }
            if (!TextUtils.isEmpty(objectMap.get("tmmc")) && objectMap.get("tmmc").equals(ppt.get("tmmc"))) {
                AlterDialog.alert("该题目名称已存在，请直接修改");
                return;
            }
        }
        list.add(ppt);
        //去掉删除的记录
        List<Map<String, Object>> usefulList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            if (!TextUtils.isEmpty(map.get("updateFlag")) && map.get("updateFlag").equals(2)) {
                continue;
            }
            usefulList.add(map);
        }
        if (TextUtils.isEmpty(ppt.get("owid"))) {
            ppt.put("owid", TextUtils.getUUID());
        }
        parentGrid.clearRows();
        for (int i = usefulList.size()-1; i >= 0; i--) {
            Map<String, Object> map = usefulList.get(i);
            parentWindow.getGridUtils().addNewRow(map, parentGrid, parentWindow);
        }
        parentGrid.setAllDatas(usefulList);
        //关闭页面
        baseWindow.detach();
    }

}
