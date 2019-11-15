package com.ourway.custemevent;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 点击弹出一个iframe 页面</p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class OpenNoZKPageAction implements ComponentListinerSer {
    //[{"pageCa":"打开的路径","gridId":"回掉的grid","title":""}]



    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        try {
            List<Map> paramsList = JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("pageCa"))) {
                AlterDialog.alert("请定义pageCa");
                return;
            }
            url = _params.get("pageCa").toString();
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert("请定义gridId");
                return;
            }
            BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            Row row = null;
            List<Row> selRows = baseGrid.getSelectRows();
            if (null == selRows || selRows.size() <= 0) {
                AlterDialog.alert("请选择需要操作的记录");
                return;
            }
            if (selRows.size() > 1) {
                AlterDialog.alert("请选择列表中的一条操作记录进行操作");
                return;
            }
            row = selRows.get(0);


            Map<String, Object> rowData = (Map<String, Object>) row.getValue();


            if (null == rowData.get("owid")) {
                AlterDialog.alert("未包含主键值,不能获取指定关键字值");
                return;
            }
//
            String zphKsrq = null;
            if (!TextUtils.isEmpty(rowData.get("zphKsrq"))) {
                zphKsrq = DateUtil.getDateString(rowData.get("zphKsrq").toString(), "yyyy-MM-dd");
            }
            String qdsj1 = null;
            if (!TextUtils.isEmpty(rowData.get("qdsj1"))) {
                qdsj1 = DateUtil.getDateString(rowData.get("qdsj1").toString(), "HH:mm:ss");
            }
            String qdsj2 = null;
            if (!TextUtils.isEmpty(rowData.get("qdsj2"))) {
                qdsj2 = DateUtil.getDateString(rowData.get("qdsj2").toString(), "HH:mm:ss");
            }

            String apiURL = "web/zustcommon/common/getToken";
            PublicData publicData = PublicData.instantce();
            publicData.setMethod(apiURL);
            Map ppt = new HashMap<>();
            publicData.setData(JsonUtil.toJson(ppt));
            ResponseMessage e = JsonPostUtils.executeAPI(ppt, apiURL);
            String token = (String) e.getBean();


//            url += "?name=" + rowData.get("zwbt") + "&zphJbdd=" + rowData.get("zphJbdd") + "&zphKsrq=" + zphKsrq + "&zphJtsj=" + rowData.get("zphJtsj") + "&owid=" + rowData.get("owid");
            url += "?name=" + rowData.get("zwbt") + "&zphJbdd=" + rowData.get("zphJbdd") + "&zphKsrq=" + zphKsrq + "&zphJtsj=" + rowData.get("zphJtsj") + "&owid=" + rowData.get("owid") + "&token=" + token + "&qdsj1=" + qdsj1 + "&qdsj2=" + qdsj2;
            ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
            if (null == root) {
                AlterDialog.alert("无tab页面，不能打开");
                return;
            }
            Map<String, Object> params = new HashMap<String, Object>(1);
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
