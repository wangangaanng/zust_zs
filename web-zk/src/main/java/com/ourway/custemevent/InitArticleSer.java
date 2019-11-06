package com.ourway.custemevent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zul.ListModelList;

import java.util.*;

/**
 * Created by jack on 2017/5/28.
 */
public class InitArticleSer implements PageInitSer {
    private static final String SINGLE_DETAIL_URL = "web/zustcommon/bckjBizArticle/getByEjLmbh";
    private static final String INIT_CHOSE_URL = "web/zustcommon/bckjDicMenu/getArticleType";

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
            BaseListbox baseListbox = (BaseListbox) window.getFellowIfAny("mainTableGrid_nf");
            BaseLabel baseNfLabel = (BaseLabel) window.getFellowIfAny("mainTableGrid_nf_label");
            if (null != _rowMap && !TextUtils.isEmpty(_rowMap.get("owid"))) {
                responseMessage = JsonPostUtils.executeAPI(params, _params.get("apiUrl").toString());
            } else {
                if (null != params.get("#lmbh")) {
                    window.getPpt().put("lmbh", params.get("#lmbh"));
                    params.put("lmbh", params.get("#lmbh"));
                    params.remove("#lmbh");
                    responseMessage = JsonPostUtils.executeAPI(params, SINGLE_DETAIL_URL);
                }
                if (!params.get("lmbh").toString().equals("66")&&!params.get("lmbh").toString().equals("131")) {
                    baseListbox.setVisible(false);
                    baseNfLabel.setVisible(false);
                }
            }
            if (null == responseMessage || responseMessage.getBackCode() != 0) {
//                AlterDialog.alert("不存在详细信息列表");
                BaseDatebox baseDatebox = (BaseDatebox) window.getFellowIfAny("mainTableGrid_fbsj");
                baseDatebox.setValue(new Date());
                BaseIntbox intbox = (BaseIntbox) window.getFellowIfAny("mainTableGrid_sxh");
                intbox.setValue(1);
                BaseTextbox textbox = (BaseTextbox) window.getFellowIfAny("mainTableGrid_fbr");
                textbox.setValue(ZKSessionUtils.getZkUser().getEmpName());
                return;
            } else {
                if (!TextUtils.isEmpty(responseMessage.getBean())) {
                    if (!TextUtils.isEmpty(responseMessage.getBean())) {
                        Map result = (Map<String, Object>) responseMessage.getBean();
                        List<Object> filesList = Lists.newArrayList();
                        if (!TextUtils.isEmpty(result.get("qtbhname"))) {
                            String value = result.get("qtbhname").toString();
                            String qtbh = result.get("qtbh").toString();
                            String[] files = value.split("\\,");
                            String[] qtbhs = qtbh.split("\\,");
                            Map data = Maps.newHashMap();
                            for (int i = 0; i < files.length; i++) {
                                data.put("label", files[i]);
                                data.put("value", qtbhs[i]);
                                filesList.add(data);
                                data = Maps.newHashMap();
                            }
                            result.put("qtbh", filesList);
                        }
                        window.setPpt(result);
                    }
                }
                if (!(window.getPpt().get("lmbh").toString().equals("66"))&&!(window.getPpt().get("lmbh").toString().equals("131"))) {
                    baseListbox.setVisible(false);
                    baseNfLabel.setVisible(false);
                }
                //查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
                PageUtils.changeWindowTitle(window, args);

            }
        }

        }
    private void initCompBox(BaseWindow window) {
        BaseChosenbox combobox = (BaseChosenbox) window.getFellowIfAny("mainTableGrid_qtbh");
        ResponseMessage mess = JsonPostUtils.executeAPI("", INIT_CHOSE_URL);
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

