package com.ourway.custemevent;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;

import java.util.Map;

/**
 * Created by CC on 2019/11/1.
 */
public class JtsjRender implements PageInitSer {
    public JtsjRender() {
    }

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        BaseTextbox text = (BaseTextbox) window.getFellowIfAny("mainTableGrid_zphJtsj");
        if (!TextUtils.isEmpty(text) && TextUtils.isEmpty(text.getValue())) {
            text.setText("下午1:30");
        }

    }
}
