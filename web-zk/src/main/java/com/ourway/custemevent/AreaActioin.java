package com.ourway.custemevent;

import com.google.common.collect.Maps;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listitem;

import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>AreaActioin</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2018</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/1/23</dd>
 * </dl>
 *
 * @author xby
 */
public class AreaActioin implements ComponentListinerSer {
    private static final String AREA_URL = "web/zustswyt/bckjBizBkzy/listByBk";

    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {

        //学科类别
        BaseListbox provinceCode = (BaseListbox) baseWindow.getFellowIfAny("conditionGrid_xklb");
        //报考类别
        BaseListbox cityCode = (BaseListbox) baseWindow.getFellow("conditionGrid_bklb");
        String label = "";
        String value = "";
        /**初始化数据*/
        try {
            PublicData publicData = PublicData.instantce();
            Map<String, Object> ppt = Maps.newHashMap();
            if (TextUtils.isEmpty(provinceCode) || TextUtils.isEmpty(provinceCode.getSelectedItem().getValue())) {
                AlterDialog.alert("请先选择学科类别！");
                return;
            }
            cityCode.setDisabled(false);
            ppt.put("dicName", provinceCode.getSelectedItem().getValue().toString());
            ppt.put("cc", 2);
            publicData.setMethod(AREA_URL);
            /**/
            publicData.setData(JsonUtil.toJson(ppt));
            ResponseMessage responseMessage = JsonPostUtils.executeAPI(ppt, AREA_URL);
//            String result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false);
//            ResponseMessage responseMessage = JsonUtil.getResponseMsg(result);
            cityCode.getChildren().clear();

            label = "请选择";
            value = "";
            Listitem item = new Listitem(label, value);
            item.setParent(cityCode);

            if (null != responseMessage && responseMessage.getBackCode() == 0 && null != responseMessage.getBean()) {
                List<Map<String, Object>> list = (List<Map<String, Object>>) responseMessage.getBean();
                if (TextUtils.isEmpty(list) || list.size() <= 0) {
                    return;
                }
                for (Map<String, Object> data : list) {
                    if (TextUtils.isEmpty(data)) {
                        continue;
                    }
                    label = TextUtils.isEmpty(data.get("label")) ? "" : data.get("label").toString();
                    value = TextUtils.isEmpty(data.get("value")) ? "" : data.get("value").toString();
                    item = new Listitem(label, value);
                    item.setParent(cityCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}