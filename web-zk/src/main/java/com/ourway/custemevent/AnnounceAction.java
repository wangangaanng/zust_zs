package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *<p>功能描述:发布调查问卷 AnnounceAction</p >
 *<ul>
 *<li>@param </li>
 *<li>@return </li>
 *<li>@throws </li>
 *<li>@author xuyux</li>
 *<li>@date 2019/10/23 19:07</li>
 *</ul>
 */
public class AnnounceAction implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();

        try {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = paramsList.get(0);
            Map<String, Object> params = paramsList.get(1);
            Map<String, Object> stateParam = paramsList.get(2);

            BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
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

            if ("0".equals(stateParam.get("state")) && !AlterDialog.corfirm("您确定发布该问卷吗？")) {
                return;
            }
            //调用接口
            String apiURL = params.get("apiUrl").toString();
            PublicData publicData = PublicData.instantce();
            publicData.setMethod(apiURL);
            Map<String, Object> ppt = new HashMap<String, Object>(1);
            ppt.put("owid", rowData.get("owid"));
            ppt.put("state", stateParam.get("state"));
            publicData.setData(JsonUtil.toJson(ppt));
            ResponseMessage e = JsonPostUtils.executeAPI(ppt, apiURL);
            if (null != e && e.getBackCode() == 0) {
                AlterDialog.alert("操作成功");
            } else if (null != e && e.getBackCode() != 0) {
                AlterDialog.alert(e.getErrorMess());
            }
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            Object models = new ArrayList();
            grid.filter((List) models);
            grid.display();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
