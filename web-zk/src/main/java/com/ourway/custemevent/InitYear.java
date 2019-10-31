package com.ourway.custemevent;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentInitSer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

/**
 * Created by CC on 2018/1/7.
 */
public class InitYear implements ComponentInitSer {
    public static final String YES = "yes";

    @Override
    public void doAction(BaseWindow window, Component component, PageControlVO pageControlVO) {
        String label = "";
        String value = "";
        Listitem newItem = new Listitem("--请选择--", "");
        newItem.setParent(component);
        newItem.setSelected(true);

        try {
            String year = DateUtil.getDateStr("yyyy");
            Integer years = Integer.parseInt(year);
            for (int i = years ; i >= 2018; i--) {
                label = String.valueOf(i);
                value = String.valueOf(i);
                Listitem item = new Listitem(label, value);
                item.setParent(component);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
