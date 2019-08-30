package com.ourway.ommonevent;

import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.MessUtil;
import com.ourway.base.zk.utils.treeutils.NodeUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

import java.util.List;
import java.util.Map;

/**
*<p>功能描述：右键新增树下级或者同级节点弹出页面</p>
*<ul>
*<li>@param </li>
*<li>@return </li>
*<li>@throws </li>
*<li>@author jackson</li>
*<li>@date 2019/7/4 14:00</li>
*</ul>
*/
public class TreeClickOpenWindowsAction implements TreeListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
        Map<String, Object> _params = (Map) paramsList.get(0);
        TreeVO treeVO = new TreeVO();
        TreeVO _treeVO = new TreeVO();

        //1、选中树节点新增
        switch (pgvo.getTreeType()) {
            case 1:
                if (null != treeitem && null != treeitem.getValue()) {
                    _treeVO = (TreeVO) treeitem.getValue();
                    treeVO.setOwid(_treeVO.getOwid());
                    treeVO.setFid(_treeVO.getOwid());
                    treeVO.setPath(_treeVO.getPath());
                    treeVO.setPx(NodeUtils.getIndexInItem(treeitem));
                    treeVO.setCc(_treeVO.getCc());
                } else {
                    treeVO.setFid(-1);
                    treeVO.setPath("-1/");
                    treeVO.setPx(NodeUtils.getIndexInItem(treeitem));
                    treeVO.setCc(1);
                }
                break;
            case 2:
                if (null != treeitem && null != treeitem.getValue()) {
                    _treeVO = (TreeVO) treeitem.getValue();
                    treeVO.setOwid(_treeVO.getOwid());
                    treeVO.setFid(_treeVO.getFid());
                    //判断path结尾有没有/
                    if (_treeVO.getPath().endsWith("/")) {
                        treeVO.setPath(_treeVO.getPath().replaceAll(_treeVO.getOwid().toString()+"/", ""));
                    }else{
                        treeVO.setPath(_treeVO.getPath().replaceAll(_treeVO.getOwid().toString(), ""));
                    }
                    treeVO.setPx(NodeUtils.getIndexInItem(treeitem.getParentItem()));
                    treeVO.setCc(_treeVO.getCc());
                } else {
                    treeVO.setFid(-1);
                    treeVO.setPath("-1/");
                    treeVO.setPx(NodeUtils.getIndexInItem(treeitem));
                    treeVO.setCc(1);
                }
                break;
            case 3:
                _treeVO = (TreeVO) treeitem.getValue();
                treeVO = _treeVO;
        }

        //树操作类型
        _params.put("tree", treeVO);
        _params.put("treeType", pgvo.getTreeType());

        //2、打开对应页面
        if (TextUtils.isEmpty(_params.get("pageCa").toString())) {
            AlterDialog.alert("请定义pageCa!");
            return;
        }
        window.setBaseTree(tree);
        window.setBaseTreeItem(treeitem);
        window.setBaseTreeType(pgvo.getTreeType());
        //3、打开绩效目标类型维护页面
        openJxTargetTypeWindow(_params, window);


    }

    private void openJxTargetTypeWindow(Map<String, Object> dataMap, BaseWindow baseWindow) {
        dataMap.put("pageType", 1);
        String link = MessUtil.getLinkByPageCa(dataMap.get("pageCa").toString());
        Component winEdit1 = Executions.createComponents(link, (Component) null, dataMap);
        if (winEdit1 instanceof BaseWindow) {
            BaseWindow _win = (BaseWindow) winEdit1;
            _win.setStyle("width: 670px;height: 322px");
            _win.setTopWindow(baseWindow);
            _win.doModal();
            if (_win.isClosePage() || _win.isDetach()) {
                Map<String, Object> ppt = (Map<String, Object>) _win.getAttribute("rulePPt");
            }
        }
    }
}
