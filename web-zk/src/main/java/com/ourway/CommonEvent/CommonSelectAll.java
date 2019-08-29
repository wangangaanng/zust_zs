package com.ourway.CommonEvent;

import com.ourway.base.zk.component.BaseCheckbox;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import java.util.List;
import java.util.Map;

/**
 * <p>功能描述：全选按钮事件</p>
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author My</li>
 * <li>@date 2019/6/21 15:09</li>
 * </ul>
 */
public class CommonSelectAll implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入配置参数");
        } else {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("dataList"))) {
                AlterDialog.alert("请指定dataList!");
                return;
            }
            BaseGrid baseGrid = (BaseGrid) baseWindow.getFellowIfAny(_params.get("dataList").toString());
            if (TextUtils.isEmpty(baseGrid)) {
                AlterDialog.alert("grid配置有误！");
                return;
            }
            Rows rows = baseGrid.getRows();
            if (TextUtils.isEmpty(rows)) {
                return;
            }
            List<Row> rowList = rows.getChildren();
            if (TextUtils.isEmpty(rowList) || rowList.size() <= 0) {
                return;
            }
            for (Row row : rowList) {
                List<Component> components = row.getChildren();
                if (TextUtils.isEmpty(components) || components.size() <= 0) {
                    return;
                }

                Component component =  components.get(0);
                if (component instanceof BaseCheckbox) {
                    BaseCheckbox checkbox = (BaseCheckbox) component;
                    checkbox.setChecked(true);
                }
            }
        }
    }

}
