package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import org.zkoss.zk.ui.event.Event;

public class ListBoxChangeAction implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        BaseListbox secondPlanDetail = (BaseListbox) baseWindow.getFellow("mainTableGrid_lmbh2");
        if(null!=secondPlanDetail.getSelectedItem()&&null!=secondPlanDetail.getSelectedItem().getValue()){
        BaseListbox tzlj = (BaseListbox) baseWindow.getFellow("mainTableGrid_xzlj");
        BaseListbox url = (BaseListbox) baseWindow.getFellow("mainTableGrid_url");
            String value=secondPlanDetail.getSelectedItem().getValue();
            if(value.equals("1")){

            }
        }else{
            AlterDialog.alert("请选择类型");
        }

    }
}
