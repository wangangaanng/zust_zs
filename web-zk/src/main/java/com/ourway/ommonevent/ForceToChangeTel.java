package com.ourway.ommonevent;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.utils.AlterDialog;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class ForceToChangeTel implements EventListener {
    public ForceToChangeTel() {
    }

    @Override
    public void onEvent(Event event) throws Exception {
        try {
            Component comp = Path.getComponent("/mainWin");
            MainAction root = null;
            if (comp instanceof MainAction) {
                root = (MainAction) comp;
            }
            if (null == root) {
                AlterDialog.alert("无tab页面，不能打开");
                return;
            }
            root.openFunByPageCa("绑定电话", "/sys/bdTel.do");
        } catch (Exception var16) {
            var16.printStackTrace();
        }
    }
}
