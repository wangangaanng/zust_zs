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
            if (TextUtils.isEmpty(_params.get("gridId"))) {
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
                    String pageCa = _params.get("pageCa").toString();
                    _params.put("#owid",data.get("owid"));
                    Component comp = Path.getComponent("/mainWin");
                    PageVO vo = PageDataUtil.detailPageByCa(pageCa);
                    String url="";
                    if (vo.getPageCustomer().intValue() == 0) {
                        url = vo.getPageTemplatePath();
                    } else {
                        url = vo.getPageCa();
                    }
                    com.ourway.base.zk.main.MainAction root = null;
                    if (comp instanceof ManageMainAction) {
                        root = (com.ourway.base.zk.main.MainAction) comp;
                    }
                    try {
                        JsonPostUtils.executeAPI(data, _params.get("apiUrl").toString());
                        Component winEdit1 = Executions.createComponents(url, (Component) null, _params);
                        if (winEdit1 instanceof BaseWindow) {
                            BaseWindow _win1 = (BaseWindow) winEdit1;
                            _win1.setParentPpt(_params);
                            String tabId = root.openNewTab(_win1, _params.get("title") + ":" + wjmc);
                            _win1.setTabId(tabId + "_window");
                            _win1.setTopWindow(window);
                            if (_win1.isClosePage()) {
                                root.closeTabWin(_win1);
                            }
                        }
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
