package com.ourway.ommonevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeClickQueryListAction implements TreeListinerSer {
    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
        Map<String, Object> _params = (Map) paramsList.get(0);

        if (TextUtils.isEmpty(_params.get("gridId"))) {
            AlterDialog.alert("请定义加载的dataList");
            return;
        }
        List<FilterModel> models = new ArrayList();
        BaseGrid grid = null;
        if (null != window.getFellowIfAny(_params.get("gridId").toString())) {
            grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            if (!TextUtils.isEmpty(_params.get("conditionGrid"))) {
                models = window.bind2Filter(_params.get("conditionGrid").toString());
            }

            //处理选中数据
            if (null != treeitem.getValue()) {
                TreeVO treeVO = (TreeVO) treeitem.getValue();
                Map dataMap = JsonUtil.objectToMap(treeVO);
                if (TextUtils.isEmpty(dataMap)) {
                    return;
                }
                window.setPpt(dataMap);
                List<Object> datas = new ArrayList(1);
                //将关联的字段往下传 [{"property":"code","gridId":"dataList","filterType":0,"conditionGrid":"conditionGrid","url":"center/projecttarget/common/jxModelList"}]
                datas.add(dataMap.get(_params.get("property").toString()));
                FilterModel model = FilterModel.instance(_params.get("property").toString(), Integer.parseInt(_params.get("filterType").toString()), datas);
                ((List) models).add(model);
            }

            //点击查询数据
            grid.filter((List) models);
            grid.display();
        }
    }
}
