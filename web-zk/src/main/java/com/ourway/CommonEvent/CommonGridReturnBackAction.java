package com.ourway.CommonEvent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerWithReasonSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>CommonBackAction</dt>
 * <dd>Description: 列表打回公用方法</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/6/11</dd>
 * </dl>
 *
 * @author xby
 */
public class CommonGridReturnBackAction implements ComponentListinerWithReasonSer {


    private static final String LOGS_API_URL ="/projectLogsApi/saveProjectLogs";
//    createProjectLogs(mList,Integer.valueOf(_params.get("projectFlow").toString()),Integer.valueOf(_params.get("operateType").toString()),
//        _params.get("projectOwidParam").toString(),_params.get("bizRefOwidParam").toString(),
//    map.get("reason").toString());

    // projectFlow ： 0:项目信息 1:项目手续 2: 项目投资 3：招标采购 4:合同备案 5：项目进度 6：竣工验收 7：绩效评价  必传
    // operateType：  （0：新增 1：提交 2：审核 4 审核退回 5评审 6上报 7 管理 8:单项退回 9:上报打回 10:全退 11:审批通过 12:审批不通过 13取消  必传
    // projectOwidParam : 列表中存放 项目主键的参数名称  必传
    // bizRefOwidParam ： 列表中存放其他流程主键的参数名称,例如合同备案列表数据，则是合同单条记录的主键参数名    必传
    // closed : 1:关闭tab 2：关闭window
    // fail ： 失败是提示的信息 必传
    // confirm : 确认提示框
    //success ： 成功提示语   必传
    //apiUrl ： 业务接口      必传
    //gridId  必传
    //conditionGrid 不必传
    //reasonParam  需要把原因传回 业务接口的参数名
    //[{"apiUrl":"","success":"","fail":"","gridId":"","conditionGrid":"","confirm":"","closed":"","projectFlow":"","operateType":"","projectOwidParam":"","bizRefOwidParam":"","reasonParam":""}]

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo, Map<String, Object> map) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入配置参数");
        } else {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = (Map)paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("apiUrl"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("public.sys.noApi"));
            } else if (TextUtils.isEmpty(_params.get("success"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("public.sys.noSucessTips"));
            } else if (TextUtils.isEmpty(_params.get("fail"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("public.sys.noFailedTips"));
            } else if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert(I18nUtil.getLabelContent("public.sys.noFailedTips"));
            }else if (TextUtils.isEmpty(_params.get("projectFlow"))) {
                AlterDialog.alert("请定义projectFlow");
            }else if(TextUtils.isEmpty(_params.get("operateType"))){
                AlterDialog.alert("请定义operateType");
            }else if(TextUtils.isEmpty(_params.get("projectOwidParam"))){
                AlterDialog.alert("请定义projectOwidParam");
            }else if(TextUtils.isEmpty(_params.get("bizRefOwidParam"))){
                AlterDialog.alert("请定义bizRefOwidParam");
            }else {
                //获取查询条件
                List<FilterModel> models = Lists.newArrayList();
                if (!TextUtils.isEmpty(_params.get("conditionGrid"))) {
                    models = window.bind2Filter(_params.get("conditionGrid").toString());
                }

                BaseGrid grid = (BaseGrid)window.getFellowIfAny(_params.get("gridId").toString());
                if (null == grid) {
                    AlterDialog.alert(I18nUtil.getLabelContent("public.sys.noGrid"));
                } else if (TextUtils.isEmpty(_params.get("confirm")) || AlterDialog.corfirm(I18nUtil.getLabelContent(_params.get("confirm").toString()))) {
                    List<Map<String,Object>> mList = grid.getSelectRowsData();
                    if(null == mList || mList.size()< 1){
                        AlterDialog.alert("请至少选择一条记录");
                        return;
                    }
                    //把打回原因传回业务接口
                    if(!TextUtils.isEmpty(_params.get("reasonParam"))){
                        mList.forEach(param -> param.put(_params.get("reasonParam").toString(),map.get("reason").toString()));
                    }
                    ResponseMessage responseMessage = JsonPostUtils.executeObjAPI(mList, _params.get("apiUrl").toString());
                    if (TextUtils.isEmpty(responseMessage)) {
                        AlterDialog.alert("操作失败！");
                    }
                    if (responseMessage.getBackCode() == 0) {
                        AlterDialog.alert(I18nUtil.getLabelContent(_params.get("success").toString()));
                        this.tabClose(window, _params);
                        this.windowClose(window, _params);
                        this.refreshRow(window, grid, responseMessage,models);
                        //保存操作日志
                        createProjectLogs(mList,Integer.valueOf(_params.get("projectFlow").toString()),Integer.valueOf(_params.get("operateType").toString()),
                            _params.get("projectOwidParam").toString(),_params.get("bizRefOwidParam").toString(),
                            map.get("reason").toString());
                    } else {
                        if (null != responseMessage.getErrorMess()) {
                            AlterDialog.alert(I18nUtil.getLabelContent(_params.get("fail").toString()) + ":" + responseMessage.getErrorMess() );
                        } else {
                            AlterDialog.alert(I18nUtil.getLabelContent(_params.get("fail").toString()));
                        }
                    }
                }
            }
        }
    }

    /**
     *<p>功能描述:createProjectLogs 新增操作</p>
     *<ul>
     *<li>@param mList </li>
     *<li>@param projectFlow </li>
     *<li>@param operateType </li>
     *<li>@param projectOwidParam </li>
     *<li>@param bizRefOwidParam </li>
     *<li>@param desc </li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2019/6/11 11:51</li>
     *</ul>
    */
    private void createProjectLogs(List<Map<String,Object>> mList,Integer projectFlow, Integer operateType,
                                   String projectOwidParam,String bizRefOwidParam,String reason) {
        Map<String,Object> param = Maps.newHashMap();
        param.put("mList",mList);
        param.put("projectFlow",projectFlow);
        param.put("operateType",operateType);
        param.put("projectOwidParam",projectOwidParam);
        param.put("bizRefOwidParam",bizRefOwidParam);
        param.put("reason",reason);
        JsonPostUtils.executeAPIByMap(param,LOGS_API_URL);
    }


    /**
     *<p>功能描述:refreshRow 刷新列表</p>
     *<ul>
     *<li>@param window </li>
     *<li>@param grid </li>
     *<li>@param responseMessage </li>
     *<li>@param models </li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2019/6/11 14:01</li>
     *</ul>
    */
    private void refreshRow(BaseWindow window, BaseGrid grid, ResponseMessage responseMessage, List<FilterModel> models) {
        try {
            //刷新整个页面
            grid.filter(models);
            grid.display();
//            if (responseMessage.getBean() instanceof List) {
//                List<Map<String, Object>> afterDatas = (List)responseMessage.getBean();
//                Iterator var5 = afterDatas.iterator();
//
//                while(var5.hasNext()) {
//                    Map<String, Object> afterData = (Map)var5.next();
//                    if (!TextUtils.isEmpty(afterData.get("rowId"))) {
//                        window.getGridUtils().refreshRow(afterData, grid, afterData.get("rowId").toString());
//                    }
//                }
//            }else{
//                //刷新整个grid
//                grid.filter(models);
//                grid.display();
//            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    /**
     *<p>功能描述:tabClose 关闭tab</p>
     *<ul>
     *<li>@param window </li>
     *<li>@param _params </li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2019/6/11 14:01</li>
     *</ul>
    */
    private void tabClose(BaseWindow window, Map<String, Object> _params) {
        if (!TextUtils.isEmpty(_params.get("closed")) && "1".equalsIgnoreCase(_params.get("closed").toString())) {
            try {
                ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
                root.closeTab(window.getParent().getId().replaceAll("_panel", ""));
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }
    }

    /**
     *<p>功能描述:windowClose 关闭window</p>
     *<ul>
     *<li>@param window </li>
     *<li>@param _params </li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2019/6/11 14:02</li>
     *</ul>
    */
    private void windowClose(BaseWindow window, Map<String, Object> _params) {
        if (!TextUtils.isEmpty(_params.get("closed")) && "2".equalsIgnoreCase(_params.get("closed").toString())) {
            try {
                window.detach();
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }
    }
}