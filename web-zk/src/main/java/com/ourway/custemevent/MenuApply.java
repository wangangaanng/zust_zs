package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuApply implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        List<Map> paramsList = JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("pageCa"))) {
            AlterDialog.alert("请定义pageCa");
            return;
        }
        if (TextUtils.isEmpty(_params.get("pageName"))) {
            AlterDialog.alert("请定义tab名称");
            return;
        }

        Component comp = Path.getComponent("/mainWin");
        MainAction root = null;
        if (comp instanceof MainAction) {
            root = (MainAction) comp;
        }
        if (null == root) {
            AlterDialog.alert("无tab页面，不能打开");
            return;
        }
        if (TextUtils.isEmpty(window.getPpt().get("owid"))) {
            AlterDialog.alert("请选择一个菜单");
            return;
        }
        _params.put("owid", window.getPpt().get("owid"));
        _params.put("ppt", window.getPpt());
        root.openFunByPageCa(I18nUtil.getLabelContent(_params.get("pageName").toString()), _params.get("pageCa").toString() + "?owid=" + window.getPpt().get("owid"));


    }

}
