package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import org.zkoss.zk.ui.event.Event;

/**
 * Created by CC on 2018/11/12.
 */
public class CloseWinAction implements ComponentListinerSer {
    public CloseWinAction() {
    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        try {
            window.detach();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


