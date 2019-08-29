package com.ourway.CommonEvent;

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

/**
 * <dl>
 * <dt>CommonApiPageInitAction</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/26</dd>
 * </dl>
 *
 * @author xby
 */
public class CommonApiPageInitAction implements PageInitSer {

    //[{"apiUrl":""}]

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        boolean flag = null != args;
        if (flag) {
            if (TextUtils.isEmpty(pageVO.getPageParams())) {
                AlterDialog.alert("未定义apiUrl调用接口");
                return;
            }

            List<Map> paramsList = JsonUtil.jsonToList(pageVO.getPageParams(), Map.class);
            Map<String, Object> _params = (Map)paramsList.get(0);
            Map<String, Object> _rowMap = (Map)args.get("ppt");
            Map<String, Object> params = new HashMap(1);
            Set<String> keys = args.keySet();
            Iterator var10 = keys.iterator();

            String key;
            while(var10.hasNext()) {
                key = (String)var10.next();
                if (!"ppt".equalsIgnoreCase(key)) {
                    params.put(key, args.get(key));
                }
            }

            keys = _params.keySet();
            var10 = keys.iterator();

            while(var10.hasNext()) {
                key = (String)var10.next();
                params.put(key, _params.get(key));
            }

            if (null != _rowMap && !TextUtils.isEmpty(_rowMap.get("owid"))) {
                keys = _rowMap.keySet();
                var10 = keys.iterator();

                while(var10.hasNext()) {
                    key = (String)var10.next();
                    params.put(key, _rowMap.get(key));
                }
            }
            ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, _params.get("apiUrl").toString());
            if (TextUtils.isEmpty(responseMessage)) {
                responseMessage = null;
            }
            if(null != responseMessage && responseMessage.getBackCode() == 0 && !TextUtils.isEmpty(responseMessage.getBean())){
                window.setPpt((Map)responseMessage.getBean());
            }else{
                if(TextUtils.isEmpty(responseMessage)){
                    return;
                }
                if(!TextUtils.isEmpty(responseMessage.getErrorMess())){
                    AlterDialog.alert(responseMessage.getErrorMess());
                    return;
                }
            }
            PageUtils.changeWindowTitle(window, args);
        }
    }
}