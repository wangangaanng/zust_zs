package com.ourway.custemevent;

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
 * Created by jackson on 2018/1/7.
 */
public class FinanceDetailOpen implements ComponentListinerSer {
    public FinanceDetailOpen() {
    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        String url = "";

        try {



            List<Map> e = com.ourway.base.zk.utils.JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map _params = (Map) e.get(0);


            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            List<Map<String, Object>> datas = grid.getSelectRowsData();
            if (TextUtils.isEmpty(datas) || datas.size() == 0) {
                AlterDialog.alert("请选择需要操作的记录");
                return;
            }
            if (TextUtils.isEmpty(_params.get("pageCa"))) {
                AlterDialog.alert("请定义pageCa");
                return;
            }

            if (TextUtils.isEmpty(_params.get("pageType"))) {
                AlterDialog.alert("请定义页面类型");
                return;
            }

            if (!TextUtils.isEmpty(window.getPpt().get("owid"))) {
                _params.put("owid", window.getPpt().get("owid"));
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
//                    BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
//                    List<Map<String, Object>> datas = grid.getSelectRowsData();
//                    if (TextUtils.isEmpty(datas) || datas.size() == 0) {
//                        AlterDialog.alert("请选择需要操作的记录");
//                        return;
//                    }
//                    if (null != datas && datas.size() > 1) {
//                        AlterDialog.alert(I18nUtil.getLabelContent("public.sys.onlySelectOne"));
//                        return;
//                    }
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
