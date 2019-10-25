package com.ourway.apply;

import com.ourway.base.zk.component.BaseLabel;
import com.ourway.base.zk.component.BaseWindow;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Div;

public class Tess extends BaseWindow {
    @Override
    public void onCreate(CreateEvent event) {
        //根据用户类型判断是否出现账号切换按钮

        Div div= (Div) getFellowIfAny("ddddd");
        Div dd2=new Div();
        dd2.setWidth("200px");
            dd2.setHeight("50px");
        dd2.setContext("你好是多少是多少");
        dd2.setParent(div);
        dd2.setVisible(true);
        BaseLabel base=new BaseLabel();
        base.setValue("nidaxiei");
        base.setVisible(true);

    }
}
