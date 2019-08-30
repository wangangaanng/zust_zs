package com.ourway.custemevent;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;
import org.zkoss.zul.Treeitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddParamPageAction implements ComponentListinerSer {
    //[{"pageCa":"打开的路径","pageType":"窗体类型","gridId":"回掉的grid","tree":"treeid，表示必选","addKey":"多个key,中间用逗号分隔"}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        String addKey = "";
        try {
//            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(),Map.class);
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("pageCa"))) {
                AlterDialog.alert("请定义pageCa");
                return;
            }
            if (TextUtils.isEmpty(_params.get("pageType"))) {
                AlterDialog.alert("请定义页面类型");
                return;
            }
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert("请定义gridId");
                return;
            }
            if (!TextUtils.isEmpty(_params.get("addKey"))) {
                addKey = _params.get("addKey").toString();
            }
            if (!TextUtils.isEmpty(_params.get("tree"))) {
                BaseTree tree = (BaseTree) window.getFellowIfAny(_params.get("tree").toString());
                if (null != tree.getSelectedItem()) {
                    Treeitem treeitem = tree.getSelectedItem();
                    _params.put("tree", treeitem.getValue());
                } else {
                    if (!TextUtils.isEmpty(_params.get("treeNotNecessary")) && Boolean.parseBoolean(_params.get("treeNotNecessary").toString())) {
                        _params.remove("tree");
                    } else {
                        AlterDialog.alert(I18nUtil.getLabelContent(ERCode.TREE_NO_VALUE));
                        return;
                    }
                }
            }
            BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());

            //根据pageca拿到页面的相关信息
            PageVO vo = PageDataUtil.detailPageByCa(_params.get("pageCa").toString());
            if (null == vo) {
                AlterDialog.alert("请在模板中定义指定ca的页面");
                return;
            }
            if (vo.getPageCustomer() == 0) {
                url = vo.getPageTemplatePath();
            } else {
                url = vo.getPageCa();
            }

            //处理url中带？的参数问题
            Map<String, Object> urlParams = GridUtils.getParamsFromUrl(url);
            if (null != urlParams) {
                Set<String> keys = urlParams.keySet();
                for (String key : keys) {
                    _params.put(key, urlParams.get(key));
                }
                url = url.split("\\?")[0];
            }
            Row row = null;
            if ("2".equalsIgnoreCase(_params.get("pageType").toString()) || "3".equalsIgnoreCase(_params.get("pageType").toString())) {
                row = PageUtils.getSelRow(event.getTarget(), 1);
                if (null == row || null == row.getValue()) {
                    AlterDialog.alert("无选中的行，不能进行修改或查看跳转");
                    return;
                }
                Map<String, Object> rowData = (Map<String, Object>) row.getValue();
                if (null == rowData.get("owid")) {
                    AlterDialog.alert("不包含OWID,不能获取指定关键字值");
                    return;
                }
                _params.put("owid", rowData.get("owid"));
            }
            ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
            if (null == root) {
                AlterDialog.alert("无tab页面，不能打开");
                return;
            }
            doComposeDefaultCondition(addKey, window, _params);
            Component winEdit = Executions.createComponents(url, null, _params);
            if (winEdit instanceof BaseWindow) {
                BaseWindow _win = (BaseWindow) winEdit;
                if (!TextUtils.isEmpty(_params.get("windowCss"))) {
                    _win.setStyle(_params.get("windowCss").toString());
                }
                _win.setBaseGrid(baseGrid);
                //打开到tab页面中的id
                String tabId = root.openNewTab(_win, _params.get("title").toString());
                //组合关闭链路
//                List<String> tabList = ZkUtils.doHandleTabid(window,tabId);
//                _win.setTabId(tabList);
                _win.setTabId(tabId + "_window");
                _win.setTopWindow(window);//自己的上级窗体
                _win.setParentPpt(_params);
                if (null != row) {
                    _win.setSelRowId(row.getId());
                }
//                _win.setClosable(true);
//                _win.doModal();
                if (_win.isClosePage()) {
                    if ("1".equalsIgnoreCase(_params.get("pageType").toString())) {
//                        GridUtils.instance().addNewRow(_win.getPpt(), baseGrid, window);
                        window.getGridUtils().addNewRow(_win.getPpt(), baseGrid, window);
                    } else {
//                        GridUtils.instance().refreshRow(_win.getPpt(), baseGrid, row);
                        window.getGridUtils().refreshRow(_win.getPpt(), baseGrid, row);
                    }
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

    void doComposeDefaultCondition(String keys, BaseWindow window, Map<String, Object> params) {
        if (null != window.getCommonModels() && window.getCommonModels().size() > 0) {
            for (FilterModel filterModel : window.getCommonModels()) {
                params.put(filterModel.getKey(), filterModel.getDatas().get(0));
            }
        }
        if (!TextUtils.isEmpty(keys) && null != window.getParentPpt()) {
            String[] _keys = keys.split("\\,");
            for (String str : _keys) {
                if (!TextUtils.isEmpty(str)) {
                    if (null != window.getParentPpt().get("#"+str)) {
                        params.put(str, window.getParentPpt().get("#"+str));
                    }
                }
            }
        }
    }

}
