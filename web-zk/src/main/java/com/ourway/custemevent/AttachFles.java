package com.ourway.custemevent;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseMultipFileUpload;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.sys.attachfile.AttachFileNewAction;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2019/11/11.
 */
public class AttachFles implements ComponentListinerSer {
    public AttachFles() {
    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();

        try {
            String e = "";
            Map ppt = window.getPpt();
            if (!TextUtils.isEmpty(ppt.get("userRefOwid"))) {
//                String userRefOwid = ppt.get("userRefOwid").toString();
//                /**调用接口**/
//                String apiURL = "web/zustswyt/bckjBizJbxx/queryOwidByYh";
//                PublicData publicData = PublicData.instantce();
//                publicData.setMethod(apiURL);
//                Map<String, Object> params = new HashMap<String, Object>(1);
//                ppt.put("yhRefROwid", userRefOwid);
//                publicData.setData(com.ourway.base.zk.utils.JsonUtil.toJson(ppt));
//                ResponseMessage responseMessage = JsonPostUtils.executeAPI(ppt, apiURL);
//                String owid = (String) responseMessage.getBean();
                e = ppt.get("userRefOwid").toString();
            } else if (!TextUtils.isEmpty(ppt.get("fileExtId"))) {
                e = ppt.get("fileExtId").toString();
            } else {
                e = "tmp" + TextUtils.getUUID();
                window.getPpt().put("fileExtId", e);
            }

            List paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("fileCode"))) {
                AlterDialog.alert("请定义文件类别");
                return;
            }

            if (TextUtils.isEmpty(_params.get("apiUrl"))) {
                AlterDialog.alert("请定义获取文件类别的API接口");
                return;
            }

            _params.put("ppt", e);
            if (!TextUtils.isEmpty(_params.get("keys"))) {
                String winEdit = _params.get("keys").toString();
                String[] baseMultipFileUpload = winEdit.split("\\,");
                String[] var11 = baseMultipFileUpload;
                int var12 = baseMultipFileUpload.length;

                for (int var13 = 0; var13 < var12; ++var13) {
                    String s = var11[var13];
                    if (!TextUtils.isEmpty(s)) {
                        _params.put(s, ppt.get(s));
                    }
                }
            }

            AttachFileNewAction var17 = (AttachFileNewAction) Executions.createComponents("/sys/attachfile/attachFileNew.do", (Component) null, _params);
            var17.setClosable(false);
            var17.setMinimizable(true);
            var17.setMinimized(true);
            var17.setParent(window);
            var17.doModal();
            if (var17.isClosePage() || var17.isDetach()) {
                try {
                    BaseMultipFileUpload var18 = (BaseMultipFileUpload) event.getTarget().getParent().getParent();
                    var18.setFileNum(var17.getFilesNum() + I18nUtil.getLabelContent(ERCode.FILE_DESC));
                } catch (Exception var15) {
                    ;
                }

                var17.detach();
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        }

    }
}

