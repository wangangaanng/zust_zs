package com.ourway.custemevent;

import com.google.common.collect.Maps;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listitem;
import sun.awt.SunHints;

import java.util.*;


public class ListBoxLinkage implements ComponentListinerSer {
    public final String ZSJH_URL_Linkage = "web/zustzs/bckjBizZsjh/getListBoxLinkage";
    public final String LNTJ_URL_Linkage = "web/zustzs/bckjBizLntj/getListBoxLinkage";

    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Map<String, Object> sendMap = Maps.newHashMap();
        ResponseMessage responseMessage = null;
        String pageCA = baseWindow.getPageCA();
        BaseListbox nfCode = (BaseListbox) baseWindow.getFellow("conditionGrid_nf");
        BaseListbox sfCode = (BaseListbox) baseWindow.getFellow("conditionGrid_sf");
        BaseListbox klCode = (BaseListbox) baseWindow.getFellow("conditionGrid_kl");
        BaseListbox pcCode = (BaseListbox) baseWindow.getFellow("conditionGrid_pc");
        BaseListbox zyCode = (BaseListbox) baseWindow.getFellow("conditionGrid_zy");
        if (!TextUtils.isEmpty(nfCode) && !TextUtils.isEmpty(nfCode.getSelectedItem().getValue().toString())) {
            sendMap.put("nf", nfCode.getSelectedItem().getValue().toString());
        }
        if (!TextUtils.isEmpty(sfCode) && !TextUtils.isEmpty(sfCode.getSelectedItem().getValue().toString())) {
            sendMap.put("sf", sfCode.getSelectedItem().getValue().toString());
        }
        if (!TextUtils.isEmpty(klCode) && !TextUtils.isEmpty(klCode.getSelectedItem().getValue().toString())) {
            sendMap.put("kl", klCode.getSelectedItem().getValue().toString());
        }
        if (!TextUtils.isEmpty(pcCode) && !TextUtils.isEmpty(pcCode.getSelectedItem().getValue().toString())) {
            sendMap.put("pc", pcCode.getSelectedItem().getValue().toString());
        }
        if (!TextUtils.isEmpty(zyCode) && !TextUtils.isEmpty(zyCode.getSelectedItem().getValue().toString())) {
            sendMap.put("zy", zyCode.getSelectedItem().getValue().toString());
        }
        if (pageCA.indexOf("zhaoshengjihua") != -1) {
            responseMessage = JsonPostUtils.executeAPI(sendMap, ZSJH_URL_Linkage);
        }
        if (pageCA.indexOf("linianzhaosheng") != -1) {
            responseMessage = JsonPostUtils.executeAPI(sendMap, LNTJ_URL_Linkage);
        }
        List<String> lists = null;
        if (!com.ourway.base.zk.utils.TextUtils.isEmpty(responseMessage) && responseMessage.getBackCode() == 0 && !com.ourway.base.zk.utils.TextUtils.isEmpty(responseMessage.getBean())) {
            lists = (List<String>) responseMessage.getBean();
            for (Object o : lists) {
                if (!TextUtils.isEmpty(((Map) o).get("nf"))) {
                    loadingList(nfCode, (Map) o, "nf");
                } else {
                    loadingListNull(nfCode);
                }
                if (!TextUtils.isEmpty(((Map) o).get("sf"))) {
                    loadingList(sfCode, (Map) o, "sf");
                } else {
                    loadingListNull(sfCode);
                }
                if (!TextUtils.isEmpty(((Map) o).get("pc"))) {
                    loadingList(pcCode, (Map) o, "pc");
                } else {
                    loadingListNull(pcCode);
                }
                if (!TextUtils.isEmpty(((Map) o).get("kl"))) {
                    loadingList(klCode, (Map) o, "kl");
                } else {
                    loadingListNull(klCode);
                }
                if (!TextUtils.isEmpty(((Map) o).get("zy"))) {
                    loadingList(zyCode, (Map) o, "zy");
                } else {
                    loadingListNull(zyCode);
                }
            }
        }
    }

    private void loadingList(BaseListbox Code, Map o, String keyName) {
        Listitem item11 = new Listitem("请选择", "");
        Code.getChildren().clear();
        item11.setParent(Code);
        Code.setDisabled(false);
        Object o1 = o.get(keyName);
        List o11 = (List) o1;
        for (Object o111 : o11) {
            Listitem item = null;
            Iterator iterator = ((Map) o111).keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                if (key.equals(keyName)) {
                    item = new Listitem(((Map) o111).get(key).toString(), ((Map) o111).get(key));
                    item.setParent(Code);
                }

            }
        }
    }

    private void loadingListNull(BaseListbox Code) {
        Code.getChildren().clear();
        Listitem item11 = new Listitem("请选择", "");
        item11.setParent(Code);
        Code.setDisabled(false);
    }

}
