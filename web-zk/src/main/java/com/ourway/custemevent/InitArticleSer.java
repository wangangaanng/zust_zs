package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseDatebox;
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
 * Created by jack on 2017/5/28.
 */
public class InitArticleSer implements PageInitSer {
    private static final String SINGLE_DETAIL_URL = "web/zustcommon/bckjBizArticle/getByEjLmbh";

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        boolean flag = null != args;
//        && !TextUtils.isEmpty(args.get("ppt"))
        if (flag) {
            if (TextUtils.isEmpty(pageVO.getPageParams())) {
                AlterDialog.alert("未定义apiUrl调用接口");
                return;
            }
            List<Map> paramsList = JsonUtil.jsonToList(pageVO.getPageParams(), Map.class);
            Map<String, Object> _params = paramsList.get(0);
            Map<String, Object> _rowMap = (Map<String, Object>) args.get("ppt");
            Map<String, Object> params = new HashMap<>(1);
            Set<String> keys = args.keySet();
            for (String key : keys) {
                if (!"ppt".equalsIgnoreCase(key)) {
                    params.put(key, args.get(key));
                }
            }
            keys = _params.keySet();
            for (String key : keys) {
                params.put(key, _params.get(key));
            }
            ResponseMessage responseMessage = null;
            if (null != _rowMap && !TextUtils.isEmpty(_rowMap.get("owid"))) {
                responseMessage = JsonPostUtils.executeAPI(params, _params.get("apiUrl").toString());
            } else {
                if (null != params.get("#lmbh")) {
                    window.getPpt().put("lmbh", params.get("#lmbh"));
                    params.put("lmbh", params.get("#lmbh"));
                    params.remove("#lmbh");
                    responseMessage = JsonPostUtils.executeAPI(params, SINGLE_DETAIL_URL);
                }

            }
            if (null == responseMessage || responseMessage.getBackCode() != 0) {
//                AlterDialog.alert("不存在详细信息列表");
                    BaseDatebox baseDatebox= (BaseDatebox) window.getFellowIfAny("mainTableGrid_fbsj");
                    baseDatebox.setValue(new Date());
                return;
            } else {
                if (!TextUtils.isEmpty(responseMessage.getBean())) {
                    window.setPpt((Map<String, Object>) responseMessage.getBean());
                }
            }

            //查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
            PageUtils.changeWindowTitle(window, args);

        }
    }
}

