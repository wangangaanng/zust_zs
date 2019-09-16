package com.ourway.custemevent;

import com.google.common.collect.Maps;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.Map;

public class CodePageInitAction implements PageInitSer {
    private final static String CODENO_URL="web/zustcommon/bckjDicMenu/countMenu";
    public CodePageInitAction() {
    }

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        BaseTextbox textbox = (BaseTextbox) window.getFellowIfAny("leftTreeDiv_code");
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(Maps.newHashMap(),CODENO_URL);
        if (TextUtils.isEmpty(responseMessage)) {
            responseMessage = null;
        }

        if (null == responseMessage || responseMessage.getBackCode() != 0) {
            return;
        }
        textbox.setValue(responseMessage.getBean().toString());
    }
}
