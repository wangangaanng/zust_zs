package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseDatebox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;

import java.util.Date;
import java.util.Map;

public class AfterArtListInitAction implements PageInitSer {
    @Override
    public void initPage(BaseWindow baseWindow, Map map, PageVO pageVO) {
//        baseWindow.
        if (null == baseWindow.getPpt().get("fbsj")) {
           BaseDatebox baseDatebox= (BaseDatebox) baseWindow.getFellowIfAny("mainTableGrid_fbsj");
            baseDatebox.setValue(new Date());
        }
        if(null==map.get("lmbh")){
            AlterDialog.alert("当前不存在栏目编号，请确认菜单配置正确");
        }
    }
}
