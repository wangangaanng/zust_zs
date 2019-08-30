package com.ourway.ommonevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.*;

/**
 * <dl>
 * <dt>CommonOpenTabAction</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/7/1</dd>
 * </dl>
 *
 * @author xby
 */
public class CommonOpenTabAction implements ComponentListinerSer {

    //pageCa : 页面
    //gridId
    //title : 页面名称
    //type : 1: 选择一条记录  其他为 双击一条数据
    //pageType
    //customParam：格式为:A-B,C-D A,C表示从grid或者ppt取值的参数名，B-D表示，需要带入下一个页面的参数名
    // [{"gridId":"","pageCa":"","title":"","customParam":"","pageType":"","type":""}]

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        String url = "";
        try {
            List datas = JsonUtil.jsonStr2List(windowParams.toString());
            Map _params = (Map) datas.get(0);

            if(TextUtils.isEmpty(_params.get("pageCa")) || TextUtils.isEmpty(_params.get("gridId")) || TextUtils.isEmpty("title") ||
                 TextUtils.isEmpty(_params.get("pageType")) || TextUtils.isEmpty("type")){
                AlterDialog.alert("请定义页面相关的pageCa 或者gridId 或者 title或者pageType或者type");
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
            Set row = null;
            if (null != urlParams) {
                row = urlParams.keySet();
                Iterator winEdit = row.iterator();
                while (winEdit.hasNext()) {
                    String _win = (String) winEdit.next();
                    _params.put(_win, urlParams.get(_win));
                }
                url = url.split("\\?")[0];
            }
            ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
            if (null == root) {
                AlterDialog.alert("无tab页面，不能打开");
                return;
            }

            BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            Map<String,Object> ppt;
            int type = Integer.parseInt(_params.get("type").toString());
            if(type == 1){
                //选中一条处理
                List<Row> selRows = baseGrid.getSelectRows();
                if (null == selRows || selRows.size() != 1) {
                    AlterDialog.alert("请选择列表中的一条操作记录进行操作");
                    return;
                }
                ppt = selRows.get(0).getValue();
            }else{
                //双击一条处理
                ppt = baseGrid.getDbData();
                if(null == ppt){
                    AlterDialog.alert("请双击一条操作记录进行操作");
                    return;
                }
            }
            if(!TextUtils.isEmpty(_params.get("customParam"))){
                StringTokenizer stringTokenizer = new StringTokenizer(_params.get("customParam").toString(),",");
                while(stringTokenizer.hasMoreTokens()) {
                    String[] temStr = stringTokenizer.nextToken().split("-");
                    _params.put(temStr[1],ppt.get(temStr[0]));
                }
            }
            Component winEdit = Executions.createComponents(url, (Component)null, _params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow)winEdit;
                if (!TextUtils.isEmpty(_params.get("windowCss"))) {
                    _win.setStyle(_params.get("windowCss").toString());
                }
                _win.setBaseGrid(baseGrid);
                String tabId = root.openNewTab(_win, _params.get("title").toString());
                _win.setTabId(tabId + "_window");
                _win.setTopWindow(window);
                _win.setParentPpt(_params);
                if (_win.isDetach()) {
                    root.closeTabWin(_win);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}