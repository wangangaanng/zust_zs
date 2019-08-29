package com.ourway.CommonEvent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.List;
import java.util.Map;

public class CommonRemoveItemGridAction implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pgvo) {
        Object params = pgvo.getLayoutComponent().getEventDataContent();
        if (null != params) {
            List<Object> paramList = JsonUtil.jsonToList(params.toString());
            Map<String, Object> param = (Map<String, Object>) paramList.get(0);
            BaseGrid baseGrid = (BaseGrid)baseWindow.getFellowIfAny(param.get("gridId").toString());
            List<Row> rows = baseGrid.getSelectRows();
            if (rows.size()<=0) {
                AlterDialog.alert("请选择要删除的记录！");
                return;
            }

            if (!AlterDialog.corfirm("确定需要删除选中的数据")) {
                return;
            }

            baseGrid.removeItems(param.get("url").toString());
            if (!TextUtils.isEmpty(param.get("freshTree")) && "1".equalsIgnoreCase(param.get("freshTree").toString())) {
                baseWindow.loadTreeData();
            }
        }
    }
}
