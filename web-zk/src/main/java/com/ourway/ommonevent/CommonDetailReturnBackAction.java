package com.ourway.ommonevent;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerWithReasonSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>CommonDetailReturnBackAction</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019-06-22</dd>
 * </dl>
 *
 * @author xby
 */
public class CommonDetailReturnBackAction implements ComponentListinerWithReasonSer {
    // projectFlow ： 0:项目信息 1:项目手续 2: 项目投资 3：招标采购 4:合同备案 5：项目进度 6：竣工验收 7：绩效评价  必传
    // operateType：  （0：新增 1：提交 2：审核 4 审核退回 5评审 6上报 7 管理 8:单项退回 9:上报打回 10:全退 11:审批通过 12:审批不通过  必传
    // projectOwidParam : 列表中存放 项目主键的参数名称  必传
    // bizRefOwidParam ： 列表中存放其他流程主键的参数名称,例如合同备案列表数据，则是合同单条记录的主键参数名    必传
    // refresh : 0:关闭tab 1：不关闭，重新加载
    // gridFresh ：关闭页面后 1：刷新  0：不刷新
    // parentArgs : 上级参数
    //apiUrl ： 业务接口      必传
    //pageType
    //windowClose 1: 关闭
    //freshTree 1: 刷新树
    //reasonParam  需要把原因传回 业务接口的参数名
    //[{"apiUrl":"","refresh":"","gridFresh":"","projectFlow":"","operateType":"","projectOwidParam":"","bizRefOwidParam":"","parentArgs":"","pageType":"","reasonParam":""}]
    private static final String LOGS_API_URL ="/projectLogsApi/saveProjectLogs";


    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo, Map<String, Object> map) {

        ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        Map<String, Object> ppt = window.bindAll2Ppt(true);
        List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
        Map<String, Object> _params = (Map) paramsList.get(0);
        if (TextUtils.isEmpty(_params.get("apiUrl"))) {
            AlterDialog.alert("请定义需要提交保存的API接口");
        }else if (TextUtils.isEmpty(_params.get("projectFlow"))) {
            AlterDialog.alert("请定义projectFlow");
        }else if(TextUtils.isEmpty(_params.get("operateType"))){
            AlterDialog.alert("请定义operateType");
        }else if(TextUtils.isEmpty(_params.get("projectOwidParam"))){
            AlterDialog.alert("请定义projectOwidParam");
        }else if(TextUtils.isEmpty(_params.get("bizRefOwidParam"))){
            AlterDialog.alert("请定义bizRefOwidParam");
        } else {
            String[] argss;
            if (!TextUtils.isEmpty(_params.get("gridId"))) {
                String[] _ids = _params.get("gridId").toString().split("\\,");
                argss = _ids;
                int length = _ids.length;
                for (int i = 0; i < length; i++) {
                    String s = argss[i];
                    BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(s);
                    if (null != baseGrid) {
                        ppt.put(s, window.getGridUtils().getAllDatas(baseGrid, true));
                    }
                }
            }
            if (!TextUtils.isEmpty(_params.get("parentArgs")) && null != window.getParentArgs()) {
                String args = _params.get("parentArgs").toString();
                argss = args.split("\\,");
                String[] var17 = argss;
                int length = argss.length;
                for (int i = 0; i < length; i++) {
                    String s = var17[i];
                    ppt.put(s, window.getParentArgs().get(s));
                }
            }

            //把退回的原因传到业务接口
            if(!TextUtils.isEmpty(_params.get("reasonParam"))){
                ppt.put(_params.get("reasonParam").toString(),map.get("reason"));
            }

            ResponseMessage message = JsonPostUtils.executeAPI(ppt, _params.get("apiUrl").toString());
            if (null == message) {
                AlterDialog.alert("操作失败");
            } else if (message.getBackCode() == 0) {

                //新增日志
                List<Map<String,Object>> mapList = Lists.newArrayList();
                mapList.add(ppt);
                createProjectLogs(mapList,Integer.valueOf(_params.get("projectFlow").toString()),
                    Integer.valueOf(_params.get("operateType").toString()),_params.get("projectOwidParam").toString(),
                    _params.get("bizRefOwidParam").toString(),map.get("reason").toString());


                if (message.getBean() instanceof Map) {
                    window.setPpt((Map) message.getBean());
                }
                if (!TextUtils.isEmpty(_params.get("freshTree")) && "1".equalsIgnoreCase(_params.get("freshTree").toString())) {
                    window.loadTreeData();
                }

                if (!TextUtils.isEmpty(window.getBaseGrid())) {
                    if (TextUtils.isEmpty(_params.get("gridFresh"))) {
                        if (!TextUtils.isEmpty(window.getSelRowId())) {
                            window.getGridUtils().refreshRow(window.getPpt(), window.getBaseGrid(), window.getSelRowId());
                        } else {
                            window.getGridUtils().addNewRow(window.getPpt(), window.getBaseGrid(), window);
                        }
                    } else {
                        if("1".equalsIgnoreCase(_params.get("gridFresh").toString())){
                            window.getBaseGrid().refreshGrid();
                            window.getBaseGrid().display();
                        }
                    }
                }

                if (TextUtils.isEmpty(_params.get("refresh"))) {
                    root.closeTabWin(window);
                } else {
                    if ("1".equalsIgnoreCase(_params.get("refresh").toString())) {
                        window.reloadPpt(true);
                    } else if ("0".equalsIgnoreCase(_params.get("refresh").toString())) {
                        root.closeTabWin(window);
                    }
                }

                if (!TextUtils.isEmpty(_params.get("pageType"))) {
                    window.setWindowType(Integer.parseInt(_params.get("pageType").toString()));
                    window.reloadButton();
                }

                if (!TextUtils.isEmpty(_params.get("windowClose")) && "1".equalsIgnoreCase(_params.get("windowClose").toString())) {
                    window.setClosePage(true);
                    window.detach();
                }

            } else {
                AlterDialog.alert(!TextUtils.isEmpty(message.getErrorMess()) ? message.getErrorMess() :"操作失败！");
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
}