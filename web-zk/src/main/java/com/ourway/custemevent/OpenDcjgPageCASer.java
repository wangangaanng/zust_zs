package com.ourway.custemevent;

import com.ourway.apply.ManageMainAction;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

public class OpenDcjgPageCASer extends com.ourway.base.zk.main.MainAction implements ComponentListinerSer{
    public OpenDcjgPageCASer(){

    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入查询配置参数");
        } else {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("attr"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("未配置属性"));

            } else if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("未设置表格"));
            } else if (TextUtils.isEmpty(_params.get("title"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("未设置标签"));
            } else if (TextUtils.isEmpty(_params.get("apiUrl"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("未设置接口"));
            } else {
                BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
                List<Map<String, Object>> datas = grid.getSelectRowsData();
                if (null != datas && datas.size() > 1) {
                    AlterDialog.alert(I18nUtil.getLabelContent("public.sys.onlySelectOne"));
                    return;
                } else if (null != datas && datas.size() > 0) {
                    Map<String, Object> data = (Map) datas.get(0);
                    String wjmc = data.get("wjmc").toString();
                    String pageCa = _params.get("attr").toString();
                    Component comp = Path.getComponent("/mainWin");
                    com.ourway.base.zk.main.MainAction root = null;
                    if (comp instanceof ManageMainAction) {
                        root = (com.ourway.base.zk.main.MainAction) comp;
                    }

                    try {
                        JsonPostUtils.executeAPI(data, _params.get("apiUrl").toString());
                        root.openFunByPageCa(_params.get("title") + ":" + wjmc, pageCa, 4);
                    } catch (Exception var15) {
                        AlterDialog.alert(I18nUtil.getLabelContent("public.sys.error.openError"));
                    }

                } else {
                    AlterDialog.alert(I18nUtil.getLabelContent("public.sys.onlySelectOne"));
                }
            }
        }
    }
}
