package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.*;

public class MenuAPIPageInitSer implements PageInitSer {

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        boolean flag = null != args;
        if(flag) {
            if(TextUtils.isEmpty(pageVO.getPageParams())) {
                AlterDialog.alert("未定义apiUrl调用接口");
                return;
            }

            List paramsList = JsonUtil.jsonStr2List(pageVO.getPageParams());
            Map _params = (Map)paramsList.get(0);
            Map _rowMap = (Map)args.get("ppt");
            HashMap params = new HashMap(1);
            Set keys = args.keySet();
            Iterator responseMessage = keys.iterator();

            String key;
            while(responseMessage.hasNext()) {
                key = (String)responseMessage.next();
                if(!"ppt".equalsIgnoreCase(key)) {
                    params.put(key, args.get(key));
                }
            }

            keys = _params.keySet();
            responseMessage = keys.iterator();

            while(responseMessage.hasNext()) {
                key = (String)responseMessage.next();
                params.put(key, _params.get(key));
            }

            if(null != _rowMap && !TextUtils.isEmpty(_rowMap.get("owid"))) {
                keys = _rowMap.keySet();
                responseMessage = keys.iterator();

                while(responseMessage.hasNext()) {
                    key = (String)responseMessage.next();
                    params.put(key, _rowMap.get(key));
                }
            }

            ResponseMessage responseMessage1 = JsonPostUtils.executeAPI(params, _params.get("apiUrl").toString());
            if(TextUtils.isEmpty(responseMessage1)) {
                responseMessage1 = null;
            }

            if(null == responseMessage1 || responseMessage1.getBackCode() != 0) {
                return;
            }

            if(!TextUtils.isEmpty(responseMessage1.getBean())) {
                window.setPpt((Map)responseMessage1.getBean());
            }

            PageUtils.changeWindowTitle(window, args);
        }

    }
}
