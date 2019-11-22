package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentWindowSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

public class SaveActionCust implements ComponentListinerSer{

        public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
             BaseGrid baseGrid1 = window.getBaseGrid();

            final List<Map<String, Object>> mapList = baseGrid1.exportFilter();
            ComponentWindowSer root = PageUtils.getBaseMainWindow(window);
            Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
            Map<String, Object> ppt = window.bindAll2Ppt(true);
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = (Map)paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("url"))) {
                AlterDialog.alert("请定义需要提交保存的API接口");
            } else {
                String[] argss;
                int var12;
                if (!TextUtils.isEmpty(_params.get("gridId"))) {
                    String[] _ids = _params.get("gridId").toString().split("\\,");
                    argss = _ids;
                    int var11 = _ids.length;

                    for(var12 = 0; var12 < var11; ++var12) {
                        String s = argss[var12];
                        BaseGrid baseGrid = (BaseGrid)window.getFellowIfAny(s);
                        if (null != baseGrid) {
                            ppt.put(s, window.getGridUtils().getAllDatas(baseGrid, true));
                        }
                    }
                }

                if (!TextUtils.isEmpty(_params.get("parentArgs")) && null != window.getParentArgs()) {
                    String args = _params.get("parentArgs").toString();
                    argss = args.split("\\,");
                    String[] var17 = argss;
                    var12 = argss.length;

                    for(int var18 = 0; var18 < var12; ++var18) {
                        String s = var17[var18];
                        ppt.put(s, window.getParentArgs().get(s));
                    }
                }
                Map<String, Object> map = mapList.get(0);

                ppt.put("keyJob",map.get("xb").toString().substring(map.get("xb").toString().indexOf(":")+1));
                ResponseMessage message = JsonPostUtils.executeAPI(ppt, _params.get("url").toString());
                if (null == message) {
                    AlterDialog.alert("操作失败");
                } else if (message.getBackCode() == 0) {
                    if (message.getBean() instanceof Map) {
                        window.setPpt((Map)message.getBean());
                    }

                    if (TextUtils.isEmpty(_params.get("gridFresh")) && null != window.getBaseGrid()) {
                        if (!TextUtils.isEmpty(window.getSelRowId())) {
                            window.getGridUtils().refreshRow(window.getPpt(), window.getBaseGrid(), window.getSelRowId());
                        } else {
                            window.getGridUtils().addNewRow(window.getPpt(), window.getBaseGrid(), window);
                        }
                    }

                    if (!TextUtils.isEmpty(_params.get("freshTree")) && "1".equalsIgnoreCase(_params.get("freshTree").toString())) {
                        window.loadTreeData();
                    }

                    if (!TextUtils.isEmpty(_params.get("gridFresh")) && "1".equalsIgnoreCase(_params.get("gridFresh").toString()) && null != window.getBaseGrid()) {
                        window.getBaseGrid().refreshGrid();
                        window.getBaseGrid().display();
                    }

                    if (!TextUtils.isEmpty(_params.get("refresh")) && "1".equalsIgnoreCase(_params.get("refresh").toString())) {
                        window.reloadPpt(true);
                    }

                    if (!TextUtils.isEmpty(_params.get("pageType"))) {
                        window.setWindowType(Integer.parseInt(_params.get("pageType").toString()));
                        window.reloadButton();
                    }

                    if (!TextUtils.isEmpty(_params.get("tips"))) {
                        AlterDialog.alert(I18nUtil.getLabelContent(_params.get("tips").toString()));
                    }

                    if (TextUtils.isEmpty(_params.get("refresh")) || "0".equalsIgnoreCase(_params.get("refresh").toString())) {
                        root.closeTabWin(window);
                    }

                    if (!TextUtils.isEmpty(_params.get("windowClose")) && "1".equalsIgnoreCase(_params.get("windowClose").toString())) {
                        window.setClosePage(true);
                        window.detach();
                    }

                } else {
                    AlterDialog.alert(message.getErrorMess());
                }
            }
        }
    }


