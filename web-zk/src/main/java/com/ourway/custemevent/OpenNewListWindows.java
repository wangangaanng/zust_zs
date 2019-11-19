package com.ourway.custemevent;

import com.ourway.apply.ManageMainAction;
import com.ourway.base.utils.*;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.Event;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
public class OpenNewListWindows implements ComponentListinerSer {
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
            _params.put("#owid", MapUtils.getString(mapParam,"owid"));
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
                String tabId="";
                if(vo.getPageCa().indexOf("xsgz")!=-1){
                    tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"zwbt")+"---学生关注");
                }else if(vo.getPageCa().indexOf("xsbm")!=-1){
                    tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"zwbt")+"---学生报名");
                }else if(vo.getPageCa().indexOf("xsqd")!=-1){
                    tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"zwbt")+"---学生签到");
                }else if(vo.getPageCa().indexOf("xuanchuan")!=-1){
                    tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"dicVal1")+"---参加人员详情");
                }
                else if (vo.getPageCa().indexOf("xjhqiaodaotongji")!=-1){
                    tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"xm")+"---宣讲会签到详情");
                }else if (vo.getPageCa().indexOf("zlzwqiaodaotongji")!=-1){
                    tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"xm")+"---职来职往签到详情");
                }else if (vo.getPageCa().indexOf("zjxjhqiaodaotongji")!=-1){
                    tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"xm")+"---讲座签到详情");
                } else{
                    tabId = root.openNewTab(_win,MapUtils.getString(mapParam,"zwbt"));
                }
                //根据指定的pageCa打开关注，报名，签到标题

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
