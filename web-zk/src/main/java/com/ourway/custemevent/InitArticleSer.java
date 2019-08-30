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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jack on 2017/5/28.
 */
public class InitArticleSer implements PageInitSer {
    private static final String SINGLE_DETAIL_URL = "qcc/web/appBizArticle/getByEjLmbh";

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
            ResponseMessage responseMessage=null;
            if (null != _rowMap && !TextUtils.isEmpty(_rowMap.get("owid"))) {
                responseMessage = JsonPostUtils.executeAPI(params, _params.get("apiUrl").toString());
            }else{

                if(null!=params.get("#lmbh")){
                    params.put("lmbh",params.get("#lmbh"));
                    params.remove("#lmbh");
                    params.put("lmbh2",params.get("#lmbh2"));
                    params.remove("#lmbh2");
                    responseMessage = JsonPostUtils.executeAPI(params, SINGLE_DETAIL_URL);
                    window.getPpt().put("lmbh",params.get("lmbh"));
                    window.getPpt().put("lmbh2",params.get("lmbh2"));
                }
            }
            if (TextUtils.isEmpty(responseMessage)) {
                responseMessage = null;
            }
            if (null == responseMessage || responseMessage.getBackCode() != 0) {
//                AlterDialog.alert("不存在详细信息列表");
                return;
            } else {
                if (!TextUtils.isEmpty(responseMessage.getBean())) {
                    window.setPpt((Map<String, Object>) responseMessage.getBean());
                }
            }
            //查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
            PageUtils.changeWindowTitle(window, args);
//            Map<String,Object> ppt=window.getPpt();
//            String value=ppt.get("lmbh").toString();
//            BaseListbox secondPlanDetail = (BaseListbox) window.getFellow("mainTableGrid_lmbh2");
//            String label = "---请选择---";
//            String valuebox = ppt.get("lmbh2").toString();
//            Map<String, Object> param = Maps.newHashMap();
//            param.put("dicType", Integer.parseInt(value));
//            PublicData publicData = PublicData.instantce();
//            publicData.setMethod(MID_PLAN_DETAIL_URL);
//            publicData.setData(JsonUtil.toJson(param));
//            String result = null;
//            try {
//                result = HttpUtils.doPost(publicData, BaseConstants.UTF8, false);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (TextUtils.isEmpty(result)) {
//                return;
//            }
//            responseMessage = JsonUtil.getResponseMsg(result);
//            secondPlanDetail.getChildren().clear();
//            if (null != responseMessage && responseMessage.getBackCode() == 0 && null != responseMessage.getBean()) {
//                List<Map<String, Object>> list = (List<Map<String, Object>>) responseMessage.getBean();
//                if (TextUtils.isEmpty(list) || list.size() <= 0) {
//                    return;
//                }
//                Listitem items = new Listitem(label, null);
//                items.setParent(secondPlanDetail);
//                for (Map<String, Object> data : list) {
//                    if (TextUtils.isEmpty(data)) {
//                        continue;
//                    }
//                    label = TextUtils.isEmpty(data.get("dicVal2")) ? "" : data.get("dicVal2").toString();
//                    value = TextUtils.isEmpty(data.get("dicVal1")) ? "" : data.get("dicVal1").toString();
//                    Listitem item = new Listitem(label, value);
//                    item.setParent(secondPlanDetail);
//                    if(valuebox.equals(data.get("dicVal2").toString())){
//                        item.setSelected(true);
//                    }
//                }
            }
        }
    }

