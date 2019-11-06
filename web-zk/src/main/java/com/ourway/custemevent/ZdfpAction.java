package com.ourway.custemevent;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>功能描述:自动分配</p >
 * <ul>
 * <li>@param </li>
 * <li>@return </li>
 * <li>@throws </li>
 * <li>@author xuyux</li>
 * <li>@date 2019/10/30 11:38</li>
 * </ul>
 */
public class ZdfpAction implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();

        try {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> params = paramsList.get(0);
            if (!TextUtils.isEmpty(params.get("apiUrl")) && !AlterDialog.corfirm("您确定要自动分配吗")) {
                return;
            }
            //调用接口
            String apiUrl = params.get("apiUrl").toString();
            PublicData publicData = PublicData.instantce();
            publicData.setMethod(apiUrl);
            ResponseMessage responseMessage = JsonPostUtils.executeAPI(apiUrl);
            if (null != responseMessage && responseMessage.getBackCode() == 0) {
                AlterDialog.alert("操作成功");
            } else if (null != responseMessage && responseMessage.getBackCode() != 0) {
                AlterDialog.alert(responseMessage.getErrorMess());
            }
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(params.get("gridId").toString());
            Object models = new ArrayList<>();
            grid.filter((List<FilterModel>) models);
            grid.display();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
