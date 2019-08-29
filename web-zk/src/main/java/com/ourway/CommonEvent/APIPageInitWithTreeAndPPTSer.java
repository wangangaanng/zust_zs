package com.ourway.CommonEvent;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIPageInitWithTreeAndPPTSer implements PageInitSer {

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        boolean flag = null != args && !TextUtils.isEmpty(args.get("ppt"));
        if (TextUtils.isEmpty(args.get("tree"))) {
            AlterDialog.alert("未定义选中树节点");
        } else {
            if (null != args.get("tree")) {
                TreeVO treeVO = (TreeVO)args.get("tree");
                Map<String, Object> pptMap = new HashMap();
                pptMap.put("tree", treeVO);
                window.setPpt(pptMap);
            }

            for(Object key : args.keySet()){
                window.getPpt().put(key.toString(), args.get(key));
            }

            if (flag) {
                if (TextUtils.isEmpty(pageVO.getPageParams())) {
                    AlterDialog.alert("未定义apiUrl调用接口");
                } else {
                    List<Map> paramsList = JsonUtil.jsonToList(pageVO.getPageParams(), Map.class);
                    Map<String, Object> _params = (Map)paramsList.get(0);
                    Map<String, Object> _rowMap = (Map)args.get("ppt");
                    Map<String, Object> params = new HashMap(1);
                    if (null != _rowMap && !TextUtils.isEmpty(_rowMap.get("owid"))) {
                        params.put("owid", _rowMap.get("owid"));
                    } else {
                        params.put("owid", "");
                    }

                    ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, _params.get("apiUrl").toString());
                    if (TextUtils.isEmpty(responseMessage)) {
                        responseMessage = null;
                    }

                    if (null != responseMessage && responseMessage.getBackCode() == 0) {
                        Map<String, Object> _ppt = (Map)responseMessage.getBean();
                        _ppt.put("tree", _rowMap.get("tree"));
                        window.setPpt(_ppt);
                        PageUtils.changeWindowTitle(window, args);
                    } else {
                        AlterDialog.alert("不存在详细信息列表");
                    }
                }
            }
        }
    }
}
