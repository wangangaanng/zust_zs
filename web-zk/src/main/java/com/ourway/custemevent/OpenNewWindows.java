package com.ourway.custemevent;

import com.ourway.apply.ManageMainAction;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.MapUtils;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

public class OpenNewWindows implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        String url = "";

        try {
            List e = JsonUtil.jsonToList(windowParams.toString());
            Map _params = (Map) e.get(0);
            if (TextUtils.isEmpty(_params.get("pageCa"))) {
                AlterDialog.alert("请定义pageCa");
                return;
            }
            if (TextUtils.isEmpty(_params.get("pageType"))) {
                AlterDialog.alert("请定义页面类型");
                return;
            }
            PageVO vo = PageDataUtil.detailPageByCa(_params.get("pageCa").toString());
            if (null == vo) {
                AlterDialog.alert("请在模板中定义指定ca的页面");
                return;
            }
            if (vo.getPageCustomer().intValue() == 0) {
                url = vo.getPageTemplatePath();
            } else {
                url = vo.getPageCa();
            }
            Map<String, Object> ppt = baseWindow.getPpt();
            String tabId ="";
            _params.put("#owid",ppt.get("owid"));
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
                tabId = root.openNewTab(_win, ppt.get("dicVal1")+"---宣讲会详情");
                //根据指定的pageCa打开关注，报名，签到标题
                _win.setTabId(tabId + "_window");
                _win.setTopWindow(baseWindow);
                if (_win.isClosePage()) {
                    root.closeTabWin(_win);
                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }

}
