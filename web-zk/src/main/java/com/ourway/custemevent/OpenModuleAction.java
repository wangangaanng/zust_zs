package com.ourway.custemevent;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by D.chen.g on 2018/6/7.
 */
public class OpenModuleAction implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
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

            Map urlParams = GridUtils.getParamsFromUrl(url);
            Set row;
            if (null != urlParams) {
                row = urlParams.keySet();
                Iterator winEdit = row.iterator();
                while (winEdit.hasNext()) {
                    String _win = (String) winEdit.next();
                    _params.put(_win, urlParams.get(_win));
                }
                url = url.split("\\?")[0];
            }

            row = null;
            Component winEdit1 = Executions.createComponents(url, (Component) null, _params);
            if (winEdit1 instanceof BaseWindow) {
                BaseWindow _win1 = (BaseWindow) winEdit1;
                if (!com.ourway.base.zk.utils.TextUtils.isEmpty(_params.get("windowCss"))) {
                    _win1.setStyle(_params.get("windowCss").toString());
                }
                if (!TextUtils.isEmpty(_params.get("gridId"))) {
                    BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
                    if (null != grid) {
                        _win1.setBaseGrid(grid);
                    }
                }
                _win1.setTopWindow(window);
                _win1.setParentPpt(_params);
                _win1.doModal();
                if (_win1.isClosePage()) {

                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }
}
