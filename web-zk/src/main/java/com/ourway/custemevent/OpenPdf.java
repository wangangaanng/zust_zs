package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.ZKSessionUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2019/11/1.
 */
public class OpenPdf implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Component comp = Path.getComponent("/mainWin");
        MainAction root = null;
        if (comp instanceof MainAction) {
            root = (MainAction) comp;
        }
        if (null == root) {
            AlterDialog.alert("无tab页面，不能打开");
            return;
        }


        BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny("dataList");
        List<Map<String, Object>> objs = baseGrid.getSelectRowsData();
        if (null == objs || objs.size() <= 0) {
            AlterDialog.alert("请选择需要操作的记录");
            return;
        }
        if (objs.size() > 1) {
            AlterDialog.alert("一次只能选择一条进行操作");
            return;
        }
        Map<String, Object> rowData = (Map<String, Object>) objs.get(0);
        if (TextUtils.isEmpty(rowData.get("owid"))) {
            AlterDialog.alert("不包含数据的主键，不能操作");
            return;
        }
        String owid = rowData.get("owid").toString();
        String pafPath = "https://job.zust.edu.cn/zjcFiles/test.pdf";

        ZKSessionUtils.setLastLink(pafPath);
        root.openFunByPageCa("报名表查看", "/swyt/openPdf.do");
//        root.openFunByPageCa("报名表查看", "/sys/config/configlist.do");

    }
}
