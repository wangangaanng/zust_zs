package com.ourway.custemevent;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.FileUploadHanderSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportRankExcel implements FileUploadHanderSer {

    private final static String RANK_URL = "web/zustjy/bckjBizJypm/importRankFromExcel";

    @Override
    public void doAction(BaseWindow baseWindow, Map<String, Object> map, PageControlVO pageControlVO) {
        System.out.println(map);
        String path = map.get("filePath").toString();
//        String system_file_path = "F:\\img\\";
        String system_file_path = "/mnt/files/zjcFiles/";
        Map<String, Object> params = new HashMap<>();
        if (!TextUtils.isEmpty(path)) {
            params.put("excelUrl", system_file_path + path);
        }
        ResponseMessage responseMessage = JsonPostUtils.executeAPI(params, RANK_URL);
        if (null != responseMessage && null != responseMessage.getBean()) {
            AlterDialog.alert("导入成功");
            BaseGrid grid = (BaseGrid) baseWindow.getFellowIfAny("dataList");
            Object models = new ArrayList<>();
            grid.filter((List) models);
            grid.display();
        }
    }
}
