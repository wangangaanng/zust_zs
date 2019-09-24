package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;

import java.util.Map;

public class CodePageInitAction implements PageInitSer {
    private final static String CODENO_URL="web/zustcommon/bckjDicMenu/countMenu";
    public CodePageInitAction() {
    }

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
       if(null==args.get("#lmbh")){
           AlterDialog.alert("当前不存在栏目编号，请确认菜单配置正确");
       }
    }
}
