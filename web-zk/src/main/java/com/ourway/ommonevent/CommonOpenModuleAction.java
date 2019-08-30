package com.ourway.ommonevent;

import com.google.common.collect.Maps;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

import java.util.*;

/**
 * <dl>
 * <dt>CommonOpenModuleAction</dt>
 * <dd>Description: 打开模态框</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/7/1</dd>
 * </dl>
 *
 * @author xby
 */
public class CommonOpenModuleAction implements ComponentListinerSer {

    //pageCa:页面CA
    //pageType:页面类型
    //windowCss: 页面样式
    //gridId:
    //customParam: 格式为:A-B,C-D A,C表示从grid或者ppt取值的参数名，B-D表示，需要带入下一个页面的参数名
    //type: 取值类型，1：从列表数据取值 2：从详情页面取值  

    //[{"pageCa":"","pageType":"","windowCss":"","gridId":"","customParam":"","type":""}]
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();
        String url = "";
        try {
            List<Map> datas = JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = (Map<String, Object>) datas.get(0);
            if (TextUtils.isEmpty(_params.get("pageCa"))) {
                AlterDialog.alert("请定义pageCa");
                return;
            }
            if (TextUtils.isEmpty(_params.get("pageType"))) {
                AlterDialog.alert("请定义页面类型");
                return;
            }
            Map<String,Object> ppt = Maps.newHashMap();
            //取自定义参数
            if (!TextUtils.isEmpty(_params.get("type"))) {
                int type = Integer.parseInt(_params.get("type").toString());
                if (type == 1) {
                    if (TextUtils.isEmpty(_params.get("gridId"))) {
                        AlterDialog.alert("请定义grid");
                        return;
                    }
                    BaseGrid baseGrid = (BaseGrid) baseWindow.getFellowIfAny(_params.get("gridId").toString());
                    List<Map<String,Object>> objs = baseGrid.getSelectRowsData();
                    if (null == objs || objs.size() <= 0) {
                        AlterDialog.alert("请选择一条数据进行操作");
                        return;
                    }
                    ppt = objs.get(0);
                } else if (type == 2) {
                    ppt = baseWindow.bindAll2Ppt(true);
                }
            }
            if(!TextUtils.isEmpty(_params.get("customParam"))){
                StringTokenizer stringTokenizer = new StringTokenizer(_params.get("customParam").toString(),",");
                while(stringTokenizer.hasMoreTokens()) {
                    String[] temStr = stringTokenizer.nextToken().split("-");
                    _params.put(temStr[1],ppt.get(temStr[0]));
                }
            }


            PageVO vo = PageDataUtil.detailPageByCa(_params.get("pageCa").toString());
            if (null == vo) {
                AlterDialog.alert("请在模板中定义指定ca的页面");
                return;
            }
            //页面动态还是自定义
            if (vo.getPageCustomer().intValue() == 0) {
                url = vo.getPageTemplatePath();
            } else {
                url = vo.getPageCa();
            }

            //链接中组装参数
            Map<String, Object> urlParams = GridUtils.getParamsFromUrl(url);
            Set<String> row = null;
            if (null != urlParams) {
                row = urlParams.keySet();
                Iterator winEdit = row.iterator();
                while (winEdit.hasNext()) {
                    String _win = (String) winEdit.next();
                    _params.put(_win, urlParams.get(_win));
                }
                url = url.split("\\?")[0];
            }

            Component winEdit1 = Executions.createComponents(url, null, _params);
            if (winEdit1 instanceof BaseWindow) {
                BaseWindow _win1 = (BaseWindow) winEdit1;
                if (!com.ourway.base.zk.utils.TextUtils.isEmpty(_params.get("windowCss"))) {
                    _win1.setStyle(_params.get("windowCss").toString());
                }
                if (!TextUtils.isEmpty(_params.get("gridId"))) {
                    BaseGrid grid = (BaseGrid) baseWindow.getFellowIfAny(_params.get("gridId").toString());
                    if (null != grid) {
                        _win1.setBaseGrid(grid);
                    }
                }
                _win1.setTopWindow(baseWindow);
                _win1.setParentPpt(_params);
                _win1.doModal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}