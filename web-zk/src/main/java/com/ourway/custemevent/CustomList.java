package com.ourway.custemevent;
import com.google.common.collect.Maps;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;

import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import java.util.*;

public class CustomList implements ComponentInitSer {

    public final String ZSJH_URL_CUSTOM = "web/zustzs/bckjBizZsjh/getCustomList";
    public final String LNTJ_URL_CUSTOM = "web/zustzs/bckjBizLntj/getCustomList";


    @Override
    public void doAction(BaseWindow baseWindow, Component component, PageControlVO pageControlVO) {
        String pageCA = baseWindow.getPageCA();
        Listitem item = new Listitem(I18nUtil.getLabelContent("public.sys.select"), "");
        item.setParent(component);
        item.setSelected(true);
        HashMap<String, Object> sendMap = Maps.newHashMap();
        ResponseMessage responseMessage=null;

        //招生计划nf
        if (pageCA.indexOf("zhaoshengjihua") != -1) {
            if (pageControlVO.getKjAttribute().equals("nf")) {
                sendMap.put("key", "nf");
                responseMessage= JsonPostUtils.executeAPI(sendMap, ZSJH_URL_CUSTOM);
            }
        }
        //招生计划sf
        if (pageCA.indexOf("zhaoshengjihua") != -1) {
            if (pageControlVO.getKjAttribute().equals("sf")) {
                sendMap.put("key", "sf");
                responseMessage= JsonPostUtils.executeAPI(sendMap, ZSJH_URL_CUSTOM);
            }
        }
        //招生计划kl
        if (pageCA.indexOf("zhaoshengjihua") != -1) {
            if (pageControlVO.getKjAttribute().equals("kl")) {
                sendMap.put("key", "kl");
                responseMessage= JsonPostUtils.executeAPI(sendMap, ZSJH_URL_CUSTOM);
            }
        }
        //招生计划pc
        if (pageCA.indexOf("zhaoshengjihua") != -1) {
            if (pageControlVO.getKjAttribute().equals("pc")) {
                sendMap.put("key", "pc");
                responseMessage= JsonPostUtils.executeAPI(sendMap, ZSJH_URL_CUSTOM);
            }
        }
        //招生计划zy
        if (pageCA.indexOf("zhaoshengjihua") != -1) {
            if (pageControlVO.getKjAttribute().equals("zy")) {
                sendMap.put("key", "zy");
                responseMessage= JsonPostUtils.executeAPI(sendMap, ZSJH_URL_CUSTOM);
            }
        }
        //历年招生pc
        if (pageCA.indexOf("linianzhaosheng") != -1) {
            if (pageControlVO.getKjAttribute().equals("pc")) {
                sendMap.put("key", "pc");
                responseMessage= JsonPostUtils.executeAPI(sendMap, LNTJ_URL_CUSTOM);
            }
        }
        //历年招生nf
        if (pageCA.indexOf("linianzhaosheng") != -1) {
            if (pageControlVO.getKjAttribute().equals("nf")) {
                sendMap.put("key", "nf");
                responseMessage= JsonPostUtils.executeAPI(sendMap, LNTJ_URL_CUSTOM);
            }
        }
        //历年招生sf
        if (pageCA.indexOf("linianzhaosheng") != -1) {
            if (pageControlVO.getKjAttribute().equals("sf")) {
                sendMap.put("key", "sf");
                responseMessage= JsonPostUtils.executeAPI(sendMap, LNTJ_URL_CUSTOM);
            }
        }
        //历年招生kl
        if (pageCA.indexOf("linianzhaosheng") != -1) {
            if (pageControlVO.getKjAttribute().equals("kl")) {
                sendMap.put("key", "kl");
                responseMessage= JsonPostUtils.executeAPI(sendMap, LNTJ_URL_CUSTOM);
            }
        }
        //历年招生zy
        if (pageCA.indexOf("linianzhaosheng") != -1) {
            if (pageControlVO.getKjAttribute().equals("zy")) {
                sendMap.put("key", "zy");
                responseMessage= JsonPostUtils.executeAPI(sendMap, LNTJ_URL_CUSTOM);
            }
        }
        List<String> lists=null;
        if(!TextUtils.isEmpty(responseMessage)&&responseMessage.getBackCode()==0&&!TextUtils.isEmpty(responseMessage.getBean())){
            lists=(List<String>) responseMessage.getBean();
        }
        if (!TextUtils.isEmpty(lists)) {
            Iterator var9 = lists.iterator();
            while (var9.hasNext()) {
                String data = (String) var9.next();
                Listitem item1 = new Listitem(data,data);
                item1.setParent(component);
            }
        }
    }
}

