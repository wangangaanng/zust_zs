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
//        ComponentWindowSer root = PageUtils.getBaseMainWindow(baseWindow);
//        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
//        Map<String, Object> ppt = baseWindow.bindAll2Ppt(true);
//        List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
//        Map<String, Object> _params = (Map)paramsList.get(0);
//        if (TextUtils.isEmpty(_params.get("url"))) {
//            AlterDialog.alert("请定义需要提交保存的API接口");
//        } else {
//            ResponseMessage message = JsonPostUtils.executeAPI(ppt, _params.get("url").toString());
//            if (null == message) {
//                AlterDialog.alert("操作失败");
//            } else if (message.getBackCode() == 0) {
//                baseWindow.setClosePage(true);
//                baseWindow.detach();
//            } else {
//                AlterDialog.alert(message.getErrorMess());
//            }
//        }
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        BaseWindow parentWindow = baseWindow.getTopWindow();
        //当前页面
        Map<String, Object> ppt = baseWindow.bindAll2Ppt(true);
        //父级页面
        Map<String, Object> parentPpt = parentWindow.getParentPpt();
        BaseGrid parentGrid = (BaseGrid) parentWindow.getFellowIfAny("dataList1");
        List<Map<String, Object>> list = parentGrid.getAllDatas();
        if (TextUtils.isEmpty(list)) {
            list = new ArrayList<>();
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
            ppt.put("owid", "tmp"+TextUtils.getUUID());
        }
        parentGrid.clearRows();
        //处理父子关系，如果是父节点，则在前面空格
//        List<Map<String, Object>> result = dealWithFatherAndSonMap(usefulList);
//        for (int i = usefulList.size()-1; i >= 0; i--) {
//            Map<String, Object> map = result.get(i);
//            parentWindow.getGridUtils().addNewRow(map, parentGrid, parentWindow);
//        }
//        parentGrid.setAllDatas(result);
        for (int i = usefulList.size()-1; i >= 0; i--) {
            Map<String, Object> map = usefulList.get(i);
            parentWindow.getGridUtils().addNewRow(map, parentGrid, parentWindow);
        }
        parentGrid.setAllDatas(usefulList);
        //关闭页面
        baseWindow.detach();
    }

    private List<Map<String, Object>> dealWithFatherAndSonMap(List<Map<String, Object>> list) {
        List<Map<String, Object>> result = new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            //exp10为空表示是父进度
            if (TextUtils.isEmpty(map.get("exp8"))) {
                map.put("state", -1);
                result.add(map);
            }
        }
        return result;
    }

}
