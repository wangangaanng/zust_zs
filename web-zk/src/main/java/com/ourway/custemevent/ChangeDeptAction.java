package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenChao on 2018/5/18.
 */
public class ChangeDeptAction implements ComponentListinerSer {
    public ChangeDeptAction() {
    }


    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();

        try {
            Map e = window.bindAll2Ppt(true);
            if (TextUtils.isEmpty(e.get("zphJbdd"))) {
                AlterDialog.alert("请选择举办地点");
                return;
            }
//            if (TextUtils.isEmpty(e.get("zphGpsjd"))) {
//                AlterDialog.alert("请填写GPS经度");
//                return;
//            }
//            if (TextUtils.isEmpty(e.get("zphGpswd"))) {
//                AlterDialog.alert("请填写GPS纬度");
//                return;
//            }
            if (TextUtils.isEmpty(e.get("zphGpsbj"))) {
                AlterDialog.alert("请填写GPS半径范围");
                return;
            }
            List paramsList = com.ourway.base.zk.utils.JsonUtil.jsonToList(windowParams.toString());
//            List paramsList = JsonUtil.jsonStr2List(windowParams.toString());
            Map _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert("请定义dataList");
            } else {
                BaseGrid baseGrid = window.getBaseGrid();
                if (null == baseGrid) {
                    AlterDialog.alert("未获取baseGrid");

                } else {
                    List selectRows = baseGrid.getSelectRows();
                    if (null == selectRows || selectRows.size() <= 0) {
                        AlterDialog.alert("请选择需要操作的记录");
                        return;
                    }
                    if (selectRows.size() > 1) {
                        AlterDialog.alert("一次只能选择一条进行操作");
                        return;
                    }
                    Iterator i$1 = selectRows.iterator();
                    Integer isFlag = 0;
                    while (i$1.hasNext()) {
                        Row selectRow = (Row) i$1.next();
                        Map data = (Map) selectRow.getValue();
                        /**调用接口**/
                        String apiURL = "web/zustjy/bckjBizJob/setJbdd";
                        PublicData publicData = PublicData.instantce();
                        publicData.setMethod(apiURL);
                        Map<String, Object> ppt = new HashMap<String, Object>(1);
                        ppt.put("owid", data.get("owid"));
                        ppt.put("zphJbdd", e.get("zphJbdd"));
                        ppt.put("zphGpsbj", e.get("zphGpsbj"));
                        publicData.setData(com.ourway.base.zk.utils.JsonUtil.toJson(ppt));
                        ResponseMessage responseMessage = JsonPostUtils.executeAPI(ppt, apiURL);
                        if (responseMessage.getBackCode() != 0) {
                            isFlag = 2;
                        }
                        window.reloadPpt(true);
                        window.getBaseGrid().refreshGrid();
                        window.getBaseGrid().display();
                        if (isFlag == 0) {
                            AlterDialog.alert("定位成功，请在小程序登录管理员账号进行采点");
                        } else if (isFlag == 2) {
                            AlterDialog.alert(responseMessage.getErrorMess());
                        }
                        window.detach();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

