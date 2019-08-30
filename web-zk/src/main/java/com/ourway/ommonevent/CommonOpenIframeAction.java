package com.ourway.ommonevent;

import com.google.common.collect.Maps;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <dl>
 * <dt>CommonOpenIframeAction</dt>
 * <dd>Description: 打开iframe页面</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/7/2</dd>
 * </dl>
 *
 * @author xby
 */
public class CommonOpenIframeAction implements ComponentListinerSer {

    //pageCa : 页面
    //gridId
    //title : 名称
    //customParam：格式为:A-B,C-D A,C表示从grid或者ppt取值的参数名，B-D表示，需要带入下一个页面的参数名
    // [{"gridId":"","pageCa":"","title":"","customParam":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            List<Map> paramsList = JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if(TextUtils.isEmpty(_params.get("pageCa")) || TextUtils.isEmpty(_params.get("gridId")) || TextUtils.isEmpty("title")){
                AlterDialog.alert("请定义页面相关的pageCa 或者gridId 或者 title");
                return;
            }
            BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            Row row = null;
            List<Row> selRows = baseGrid.getSelectRows();
            if (null == selRows || selRows.size() != 1) {
                AlterDialog.alert("请选择列表中的一条操作记录进行操作");
                return;
            }
            Map<String, Object> rowData = selRows.get(0).getValue();
            ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
            if (null == root) {
                AlterDialog.alert("无tab页面，不能打开");
                return;
            }
            String  url = _params.get("pageCa").toString();
            if(!TextUtils.isEmpty(_params.get("customParam"))){
                StringBuilder sb = new StringBuilder();
                StringTokenizer stringTokenizer = new StringTokenizer(_params.get("customParam").toString(),",");
                while(stringTokenizer.hasMoreTokens()) {
                    String[] temStr = stringTokenizer.nextToken().split("-");
                    sb.append(temStr[1]+"="+rowData.get(temStr[0])+"&");
                }
                String temStr = sb.toString();
                if (url.indexOf("?") > 0) {
                    url = url + "&" + temStr.substring(0,temStr.length()-1);
                } else {
                    url = url + "?" + temStr.substring(0,temStr.length()-1);
                }
            }
            Map<String, Object> params = Maps.newHashMap();
            params.put("url", url);
            Component winEdit = Executions.createComponents("/sys/iframe/iframe.do", null, params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                _win.setBaseGrid(baseGrid);
                //打开到tab页面中的id
                String tabId = root.openNewTab(_win, _params.get("title").toString());
                _win.setTabId(tabId + "_window");
                _win.setTopWindow(window);//自己的上级窗体
                _win.setParentPpt(_params);
                if (null != row) {
                    _win.setSelRowId(row.getId());
                }
                if (_win.isClosePage()) {
                    root.closeTabWin(_win);
                }
                if (_win.isDetach()) {
                    root.closeTabWin(_win);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}