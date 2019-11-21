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
    public final String Xch_BaoMing_URL = "web/zustzs/bckjBizZsjh/getCustomDicList";
    public final String LQXS_URL="web/zustzs/bckjBizLqxs/getCustomList";
    public final String QDLIST_URL="web/bckjBizYhxx/getCustomList";
    @Override
    public void doAction(BaseWindow baseWindow, Component component, PageControlVO pageControlVO) {
        Map<String, Object> parentArgs = baseWindow.getParentArgs();
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
        if(pageCA.indexOf("luquzhaosheng")!=-1){
            if (pageControlVO.getKjAttribute().equals("lqzy")) {
                sendMap.put("key", "lqzy");
                responseMessage= JsonPostUtils.executeAPI(sendMap, LQXS_URL);
            }
        }
        //宣传报名list
        if(pageCA.indexOf("zhaoshengxuanchuanbaomingxiangqing")!=-1){
            if (pageControlVO.getKjAttribute().equals("xszy")) {
                sendMap.put("key", "xszy");
                sendMap.put("owid", parentArgs.get("#owid"));
                responseMessage= JsonPostUtils.executeAPI(sendMap,Xch_BaoMing_URL);
            }
            if (pageControlVO.getKjAttribute().equals("xsxy")) {
                sendMap.put("key", "xsxy");
                sendMap.put("owid", parentArgs.get("#owid"));
                responseMessage= JsonPostUtils.executeAPI(sendMap,Xch_BaoMing_URL);
            }
            if (pageControlVO.getKjAttribute().equals("xsbj")) {
                sendMap.put("key", "xsbj");
                sendMap.put("owid", parentArgs.get("#owid"));
                responseMessage= JsonPostUtils.executeAPI(sendMap,Xch_BaoMing_URL);
            }
        }
        //统计签到专业下拉框
        if(pageCA.indexOf("xjhqiaodaotongji")!=-1) {
            if (pageControlVO.getKjAttribute().equals("xszy")) {
                sendMap.put("key", "xszy");
                sendMap.put("zwlx",8);
                responseMessage = JsonPostUtils.executeAPI(sendMap, QDLIST_URL);
            }
        }
        if (pageCA.indexOf("zlzwqiaodaotongji")!=-1) {
            if (pageControlVO.getKjAttribute().equals("xsxy")) {
                sendMap.put("key", "xsxy");
                sendMap.put("zwlx",8);
                responseMessage = JsonPostUtils.executeAPI(sendMap, QDLIST_URL);
            }
        }
        if(pageCA.indexOf("zjxjhqiaodaotongji")!=-1){
            if(pageControlVO.getKjAttribute().equals("xsbj")){
                sendMap.put("key", "xsbj");
                sendMap.put("zwlx",8);
                responseMessage= JsonPostUtils.executeAPI(sendMap,QDLIST_URL);
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

