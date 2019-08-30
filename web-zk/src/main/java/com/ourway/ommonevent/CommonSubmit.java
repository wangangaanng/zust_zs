package com.ourway.ommonevent;

import com.google.common.collect.Lists;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/***
 *<p>功能描述：多条选中批量提交，并回刷列表页面</p>
 *<ul>  参数 ：[{"apiUrl":"/multiUserRelation/relateAccount","success":"关联成功！","fail":"关联失败！", "gridId": "dataList","confirm":"是否确定关联该账号，关联完之后不可修改！", "closed":1}]
 *<li>@param </li>
 *<li>@return </li>
 *<li>@throws </li>
 *<li>@author jackson</li>
 *<li>@date 2019/5/15 23:23</li>
 *</ul>
 */
public class CommonSubmit implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入配置参数");
        } else {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("apiUrl"))) {
                AlterDialog.alert(BackMsg.LACKAPIURL.toString());
            } else if (TextUtils.isEmpty(_params.get("success"))) {
                AlterDialog.alert(BackMsg.LACKMES.toString());
            } else if (TextUtils.isEmpty(_params.get("fail"))) {
                AlterDialog.alert(BackMsg.LACKMES.toString());
            } else if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert(BackMsg.LACKGRIDID.toString());
            } else if (TextUtils.isEmpty(_params.get("confirm"))) {
                AlterDialog.alert(BackMsg.LACKMES.toString());
            } else {
                BaseGrid grid = (BaseGrid) baseWindow.getFellowIfAny(_params.get("gridId").toString());
                //拿到查询条件
                List<FilterModel> models = Lists.newArrayList();
                if (!TextUtils.isEmpty(_params.get("conditionGrid"))){
                    models = baseWindow.bind2Filter(_params.get("conditionGrid").toString());
                }
                if (null == grid) {
                    AlterDialog.alert(BackMsg.LACKGRID.toString());
                    return;
                } else {
                    List<Map<String, Object>> selectedRow = grid.getSelectRowsData();
                    //一定要先选择一条记录
                    if (TextUtils.isEmpty(selectedRow) || selectedRow.size() <= 0) {
                        AlterDialog.alert(BackMsg.LACKDATA.toString());
                        return;
                    }

                    if (AlterDialog.corfirm(_params.get("confirm").toString())) {
                        ResponseMessage responseMessage = JsonPostUtils.executeObjAPI(selectedRow, _params.get("apiUrl").toString());
                        //接口调用失败
                        if (TextUtils.isEmpty(responseMessage)) {
                            AlterDialog.alert(BackMsg.INVOKEFAIL.toString());
                            return;
                        }

                        if (responseMessage.getBackCode() == 0) {
                            AlterDialog.alert(_params.get("success").toString());
                            this.tabClose(baseWindow, _params);
                            this.windowClose(baseWindow, _params);
                            grid.filter(models);
                            grid.display();
                        } else {
                            if (!TextUtils.isEmpty(responseMessage.getErrorMess())) {
                                AlterDialog.alert(_params.get("fail").toString() + ":" + responseMessage.getErrorMess());
                            } else {
                                AlterDialog.alert(_params.get("fail").toString());
                            }
                        }
                    }
                }
            }
        }
    }

    private void tabClose(BaseWindow window, Map<String, Object> _params) {
        if (!TextUtils.isEmpty(_params.get("closed")) && "1".equalsIgnoreCase(_params.get("closed").toString())) {
            try {
                ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
                if (!TextUtils.isEmpty(root)) {
                    root.closeTab(window.getParent().getId().replaceAll("_panel", ""));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void windowClose(BaseWindow window, Map<String, Object> _params) {
        if (!TextUtils.isEmpty(_params.get("closed")) && "2".equalsIgnoreCase(_params.get("closed").toString())) {
            try {
                window.detach();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
