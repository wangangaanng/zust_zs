package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseCombobox;
import com.ourway.base.zk.component.BaseComboitem;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CombobxCustome implements ComponentInitSer {
    public static final String YES = "yes";
   public CombobxCustome(){

   }

    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {

        if (component instanceof BaseCombobox) {
            BaseCombobox combobox = (BaseCombobox)component;
            List<Object> paramsList = JsonUtil.jsonToList(pageControlVO.getKjInitData().toString());
            if (null == paramsList || paramsList.size() <= 0) {
                AlterDialog.alert("请传入本接口所需的JSON参数");
                return;
            }

            Map<String, Object> param = (Map)paramsList.get(0);
            if (TextUtils.isEmpty(param.get("type"))) {
                AlterDialog.alert("请传入字典表类型");
                return;
            }

            if (TextUtils.isEmpty(param.get("returnType"))) {
                if ("1".equals(param.get("returnType").toString())) {
                    combobox.setRequired(true);
                }

                return;
            }

            if (TextUtils.isEmpty(param.get("value"))) {
                AlterDialog.alert("请传入combobox的Value对应属性");
                return;
            }

            if (TextUtils.isEmpty(param.get("label"))) {
                AlterDialog.alert("请传入combobox的Label对应属性");
                return;
            }

            if (TextUtils.isEmpty(param.get("sortStr"))) {
                AlterDialog.alert("请传入combobox的排序字段");
                return;
            }

            String label = "";
            String value = "";
            Map<String, Object> dataMap = new HashMap();
            List<Map<String, Object>> datas = DicUtil.listDic(Integer.parseInt(param.get("type").toString()), param.get("sortStr").toString());
            if (null != datas && datas.size() > 0) {
                Iterator var11 = datas.iterator();

                while(var11.hasNext()) {
                    Map<String, Object> data = (Map)var11.next();
                    label = TextUtils.isEmpty(data.get(param.get("label").toString())) ? "" : data.get(param.get("label").toString()).toString();
                    label = I18nUtil.getLabelContent(label);
                    value = TextUtils.isEmpty(data.get(param.get("value").toString())) ? "" : data.get(param.get("value").toString()).toString();
                    BaseComboitem item = BaseComboitem.instance(value, label);
                    item.setParent(component);
                    dataMap.put(item.getLabel(), item.getItemValue());
                }

                ((BaseCombobox)component).setDataMap(dataMap);
            }
        } else {
            AlterDialog.alert("必须是combobox才能调用");
        }

    }
}