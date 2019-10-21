package com.ourway.custemevent;

import com.ourway.apply.ManageMainAction;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
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

public class OpenDcjgtjPageCASer extends com.ourway.base.zk.main.MainAction implements ComponentListinerSer{
    public OpenDcjgtjPageCASer(){

    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        String url = "";

        try {
            List e = JsonUtil.jsonToList(windowParams.toString());
            Map _params = (Map) e.get(0);
            if (com.ourway.base.utils.TextUtils.isEmpty(_params.get("pageCa"))) {
                AlterDialog.alert("请定义pageCa");
                return;
            }

            if (com.ourway.base.utils.TextUtils.isEmpty(_params.get("pageType"))) {
                AlterDialog.alert("请定义页面类型");
                return;
            }
            PageVO vo = PageDataUtil.detailPageByCa(_params.get("pageCa").toString());
            if (null == vo) {
                AlterDialog.alert("请在模板中定义指定ca的页面");
                return;
            }
            BaseGrid grid2 = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            List<Map<String, Object>> datas = grid2.getSelectRowsData();
            if (null != datas && datas.size() > 1) {
                AlterDialog.alert(I18nUtil.getLabelContent("public.sys.onlySelectOne"));
                return;
            }
            if (vo.getPageCustomer().intValue() == 0) {
                url = vo.getPageTemplatePath();
            } else {
                url = vo.getPageCa();
            }
            Map mapParam=datas.get(0);
            _params.put("#dcwjRefOwid", MapUtils.getString(mapParam,"owid"));
            Component comp = Path.getComponent("/mainWin");
            com.ourway.base.zk.main.MainAction root = null;
            if (comp instanceof ManageMainAction) {
                root = (com.ourway.base.zk.main.MainAction) comp;
            }
            Component winEdit = Executions.createComponents(url, (Component)null, _params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow)winEdit;
                if (!com.ourway.base.zk.utils.TextUtils.isEmpty(_params.get("windowCss"))) {
                    _win.setStyle(_params.get("windowCss").toString());
                }
                String tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"wjmc"));
                _win.setTabId(tabId + "_window");
                _win.setTopWindow(window);
                if (_win.isClosePage()) {
                    root.closeTabWin(_win);
                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }
}
