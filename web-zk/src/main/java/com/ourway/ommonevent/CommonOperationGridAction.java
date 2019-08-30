package com.ourway.ommonevent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>CommonOperationGridAction</dt>
 * <dd>Description: 对列表数据进行操作</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/19</dd>
 * </dl>
 *
 * @author xby
 */
public class CommonOperationGridAction implements ComponentListinerSer {

    //type 0: 选择单条  1：选择多条数据
    //success : 成功后的提示语句
    //tip : 操作之前的提示语句
    // conditionGrid: 查询条件
    // apiUrl : 接口路径
    //
    //[{"gridId":"","apiUrl":"","conditionGrid":"","type":"","tip":"","success":"","tip":""}]
    @Override
    public void doAction(BaseWindow baseWindow, Event event, PageControlVO pageControlVO) {
        Object windowParams = pageControlVO.getLayoutComponent().getEventDataContent();

        try{
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(),Map.class);
            Map<String, Object> _params = (Map<String,Object>)paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("gridId")) || TextUtils.isEmpty(_params.get("apiUrl")) || TextUtils.isEmpty(_params.get("type"))) {
                AlterDialog.alert("请定义参数");
                return;
            }
            BaseGrid baseGrid = (BaseGrid) baseWindow.getFellowIfAny(_params.get("gridId").toString());
            List<FilterModel> models = Lists.newArrayList();
            if (!TextUtils.isEmpty(_params.get("conditionGrid"))) {
                models = baseWindow.bind2Filter(_params.get("conditionGrid").toString());
            }
            List<Map<String,Object>> objs = baseGrid.getSelectRowsData();
            if (CollectionUtils.isEmpty(objs)) {
                AlterDialog.alert("请选择需要操作的记录");
                return;
            }
            Map<String,Object> ppt = Maps.newHashMap();
            int type = Integer.valueOf(_params.get("type").toString());
            if(type == 0){
                if (objs.size() > 1) {
                    AlterDialog.alert("一次只能选择一条进行操作");
                    return;
                }
                ppt = objs.get(0);
            }else{
               ppt.put("list",objs);
            }
            /*提示性语句*/
            if(!TextUtils.isEmpty(_params.get("tip"))) {
                if (!AlterDialog.corfirm(_params.get("tip").toString())) {
                    return;
                }
            }
            ResponseMessage responseMessage = JsonPostUtils.executeAPI(ppt,_params.get("apiUrl").toString());
            if(null != responseMessage ){
                if(responseMessage.getBackCode() ==0){
                    /*成功*/
                    AlterDialog.alert(!TextUtils.isEmpty(_params.get("success")) ? _params.get("success").toString() : "操作成功");
                    /*刷新页面*/
                    baseGrid.refreshGrid();
                    baseGrid.display();
                }else{
                    AlterDialog.alert(!TextUtils.isEmpty(responseMessage.getErrorMess()) ? responseMessage.getErrorMess() : "操作失败！");
                }
            }else{
                AlterDialog.alert("操作失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
            AlterDialog.alert("操作失败！");
        }
    }
}