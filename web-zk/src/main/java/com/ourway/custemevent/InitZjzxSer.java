package com.ourway.custemevent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.zk.component.BaseChosenbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zul.ListModelList;

import java.util.*;

public class InitZjzxSer implements PageInitSer {
    private static final String SINGLE_DETAIL_URL = "web/zustjy/bckjBizZjzx/getZjZxfx";

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        boolean flag = null != args;
        if (flag) {
            if (TextUtils.isEmpty(pageVO.getPageParams())) {
                AlterDialog.alert("未定义apiUrl调用接口");
                return;
            }
            initCompBox(window);
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

            if (null == responseMessage || responseMessage.getBackCode() != 0) {
                return;
            }


            {
                if (!TextUtils.isEmpty(responseMessage.getBean())) {
                    Map result = (Map<String, Object>) responseMessage.getBean();
                    List<Object> filesList = Lists.newArrayList();
                    if (!TextUtils.isEmpty(result.get("exp5"))) {
                        String value = result.get("exp5").toString();
                        String qtbh = result.get("exp4").toString();
                        String[] files = value.split("\\,");
                        String[] qtbhs = qtbh.split("\\,");
                        Map data = Maps.newHashMap();
                        for (int i = 0; i < files.length; i++) {
                            data.put("label", files[i]);
                            data.put("value", qtbhs[i]);
                            filesList.add(data);
                            data = Maps.newHashMap();
                        }
                        result.put("exp4", filesList);
                    }
                    window.setPpt(result);
                }
            }

            if (!TextUtils.isEmpty(responseMessage.getBean())) {
                window.setPpt((Map)responseMessage.getBean());
            }

            PageUtils.changeWindowTitle(window, args);
        }
    }


    private void initCompBox(BaseWindow window) {
        BaseChosenbox combobox = (BaseChosenbox) window.getFellowIfAny("mainTableGrid_exp4");
        Map param = Maps.newHashMap();
        ResponseMessage mess = JsonPostUtils.executeAPI(param,SINGLE_DETAIL_URL);
        if (null != mess && mess.getBackCode() == 0) {
            List<Map<String, Object>> list = (List) mess.getBean();
            ListModelList<String> values = new ListModelList(list.size());
            Map<String, Map<String, Object>> valuesMap = new HashMap();
            Iterator var14 = list.iterator();

            while (var14.hasNext()) {
                Map<String, Object> map = (Map) var14.next();
                if (!TextUtils.isEmpty(map.get("label"))) {
                    values.add(map.get("label").toString());
                    valuesMap.put(map.get("label").toString(), map);
                }
            }
            combobox.setValues(values);
            combobox.setValuesMap(valuesMap);
            combobox.setModel(values);
        }
    }
}
