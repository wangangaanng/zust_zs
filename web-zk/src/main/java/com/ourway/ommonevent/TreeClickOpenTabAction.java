package com.ourway.ommonevent;

import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.treeutils.NodeUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

import java.util.HashMap;
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
public class TreeClickOpenTabAction implements TreeListinerSer {

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

        try {
            window.setBaseTree(tree);
            window.setBaseTreeItem(treeitem);
            window.setBaseTreeType(pgvo.getTreeType());
            if (!TextUtils.isEmpty(treeVO.getOwid())) {
                Map<String, Object> params = JsonUtil.jsonToMap(JsonUtil.toJson(treeVO));
                if (!TextUtils.isEmpty(_params.get("url").toString())) {
                    ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, _params.get("url").toString());
                    if (null != responseMessage && responseMessage.getBackCode() == 0 && null != responseMessage.getBean()) {
                        Map<String, Object> _ppt = (Map)responseMessage.getBean();
                        _ppt.put("fid", treeVO.getFid());
                        _ppt.put("path", treeVO.getPath());
                        _ppt.put("px", treeVO.getPx());
                        _ppt.put("cc", treeVO.getCc());
                        window.setPpt(_ppt);
                    } else {
                        Map<String, Object> _ppt = new HashMap();
                        _ppt.put("fid", treeVO.getFid());
                        _ppt.put("path", treeVO.getPath());
                        _ppt.put("px", treeVO.getPx());
                        _ppt.put("cc", treeVO.getCc());
                        window.setPpt(_ppt);
                    }
                } else {
                    Map<String, Object> _ppt = new HashMap();
                    _ppt.put("fid", treeVO.getFid());
                    _ppt.put("path", treeVO.getPath());
                    _ppt.put("px", treeVO.getPx());
                    _ppt.put("cc", treeVO.getCc());
                    window.setPpt(_ppt);
                }
            } else {
                Map<String, Object> _ppt = new HashMap();
                _ppt.put("fid", treeVO.getFid());
                _ppt.put("path", treeVO.getPath());
                _ppt.put("px", treeVO.getPx());
                _ppt.put("cc", treeVO.getCc());
                window.setPpt(_ppt);
            }

            ComponentUtils.setEditable(window);
            window.setWindowType(1);
            ComponentUtils.doCheckButtonStatus(window);
            PageUtils.resetGridComponent(window);
            window.bind2Page();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
