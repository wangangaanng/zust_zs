package com.ourway.custemevent;

import com.ourway.base.utils.MapUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ExcelUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.utils.ExcelUtil;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Filedownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 *<p>功能描述:导出就业排行榜表格。根据学院合并 ExportRankExcel</p >
 *<ul>
 *<li>@param </li>
 *<li>@return </li>
 *<li>@throws </li>
 *<li>@author xuyux</li>
 *<li>@date 2019/10/30 15:28</li>
 *</ul>
 */
public class ExportRankExcel implements ComponentListinerSer {
    public ExportRankExcel() {

    }

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        FileOutputStream out = null;

        try {
            List<Map> paramsList = JsonUtil.jsonToList(windowParams.toString(), Map.class);
            Map<String, Object> _params = (Map) paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("title"))) {
                AlterDialog.alert("请输入导出标题");
                return;
            }
            if (TextUtils.isEmpty(_params.get("label"))) {
                AlterDialog.alert("请输入导出文件名");
                return;
            }
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            if (null == grid) {
                AlterDialog.alert("对象不存在");
                return;
            }
            ResponseMessage responseMessage = JsonPostUtils.executeAPI("web/zustjy/bckjBizJypm/exportRankExcel");
            String result =  (String)responseMessage.getBean();
            URL url = new URL("http://127.0.0.1:8080/zjcFiles/export/" + result);
            Filedownload.save( url, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
