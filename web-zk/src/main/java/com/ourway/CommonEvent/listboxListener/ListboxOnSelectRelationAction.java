package com.ourway.CommonEvent.listboxListener;

import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListboxOnSelectRelationAction implements ComponentListinerSer {
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        boolean flag = false;
        int index = 0;
        List<Map> paramsList = JsonUtil.jsonToList(pageControlVO.getLayoutComponent().getEventDataContent().toString(), Map.class);
        if (null == paramsList || paramsList.size() <= 0) {
            AlterDialog.alert("请传入本接口所需的JSON参数");
            return;
        }

        Map<String, Object> param = (Map) paramsList.get(0);
        if (TextUtils.isEmpty(param.get("apiUrl"))) {
            AlterDialog.alert("请传入获取的url");
            return;
        }

        if (TextUtils.isEmpty(param.get("targetComponentId"))) {
            AlterDialog.alert("请传入级联的控件ID");
            return;
        }

        //触发事件的listbox
        BaseListbox baseListbox = (BaseListbox) event.getTarget();

//        getCompontId(baseWindow);

        //目标修改值的listbox
        Component component1 = baseWindow.getFellowIfAny(param.get("targetComponentId").toString());
        if (component1 instanceof BaseListbox) {
            BaseListbox combobox = (BaseListbox) component1;
            combobox.getChildren().clear();
            if (!TextUtils.isEmpty(param.get("default")) && "yes".equalsIgnoreCase(param.get("default").toString())) {
                Listitem item = new Listitem();
                item.setValue("");
                item.setLabel(I18nUtil.getLabelContent("public.sys.select"));
                item.setSelected(true);
                item.setParent(combobox);
                flag = true;
            }

            //选中的数据
            Object object = baseListbox.getSelectedItem().getValue();
            Map<String, Object> params = new HashMap();
            params.put("owid", object);

            ResponseMessage mess = JsonPostUtils.executeAPI(JsonUtil.toJson(params), param.get("apiUrl").toString());
            if (null != mess && mess.getBackCode() == 0) {
                List<Map<String, Object>> list = (List) mess.getBean();
                for (Map<String, Object> map : list) {
                    if (!TextUtils.isEmpty(map.get("value"))) {
                        Listitem item = new Listitem();
                        if (!TextUtils.isEmpty(map.get("value"))) {
                            item.setValue(map.get("value"));
                        }

                        if (!TextUtils.isEmpty(map.get("label"))) {
                            item.setLabel(map.get("label").toString());
                        }

                        if (index == 0 && flag) {
                            item.setSelected(true);
                        }

                        index++;
                        item.setParent(combobox);
                    }
                }
            }
        }
    }

    private void getCompontId(Component baseWindow) {
        List<Component> list = baseWindow.getChildren();
        for (Component component : list) {
            System.out.println("===" + component.getId());
            getCompontId(component);
        }
    }

}
