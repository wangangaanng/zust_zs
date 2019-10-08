package com.ourway.custemevent;


import com.ourway.base.utils.TextUtils;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;


import com.ourway.base.zk.service.ComponentFileSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.apache.poi.ss.formula.functions.Index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordInfo implements ComponentFileSer {
    //师生录入url
    public final String xsURL = "web/zustcommon/bckjBizYhxx/recordInfo";
    //专家录入url
    public final String zxURL = "web/zustjy/bckjBizZjzx/recordInfo";
    //档案录入url
    public final String daURL = "web/zustjy/bckjBizDacx/recDanInfo";
    //就业排行榜url
    public final String rankURL = "web/zustjy/bckjBizJypm/importRankFromExcel";
    //上传excela保存的本地地址
    public final String FolderPath = "/mnt/files/zjcFiles/excel/";
//    public final String FolderPath = "F:\\img\\";
    @Override
    public void doAction(BaseWindow window, Map<String, Object> map, String s) {
        String url = "";
        String pageCA = window.getPageCA();
        if (pageCA.indexOf("student") != -1) {
            url = xsURL + "/0";
        }
        if (pageCA.indexOf("teatch") != -1) {
            url = xsURL + "/1";
        }
        if (pageCA.indexOf("consult") != -1) {
            url = zxURL;
        }
        if (pageCA.indexOf("archives") != -1) {
            url = daURL;
        }
        if (pageCA.indexOf("rankList") != -1) {
            url = rankURL;
        }
        String result = "";
        String path = map.get("filePath").toString();
        String filePath = FolderPath + path;
        //添加后缀
        copyFile(filePath);
        Map<String, Object> params = new HashMap<String, Object>();
        if (!TextUtils.isEmpty(filePath)) {
            params.put("path", filePath + ".xls");
        }
        try {
            result = JsonPostUtils.executeAPIAsString(params, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(result)) {
            AlterDialog.alert("导入失败");
        }
        try {
            /*if(result.indexOf("操作成功")!=-1){
                AlterDialog.alert("导入成功");
                BaseGrid grid = (BaseGrid) window.getFellowIfAny("dataList");
                Object models = new ArrayList();
                grid.filter((List) models);
                grid.display();
            }
            if(result.indexOf("重复学生学号")!=-1){
                AlterDialog.alert("导入失败,学号或工号存在重复");
                BaseGrid grid = (BaseGrid) window.getFellowIfAny("dataList");
                Object models = new ArrayList();
                grid.filter((List) models);
                grid.display();
            }
            if(result.indexOf("身份证号码格式错误")!=-1){
                AlterDialog.alert("身份证格式错误");
                BaseGrid grid = (BaseGrid) window.getFellowIfAny("dataList");
                Object models = new ArrayList();
                grid.filter((List) models);
                grid.display();
            }
            if(result.indexOf("手机号")!=-1){
                AlterDialog.alert("手机号格式错误");
                BaseGrid grid = (BaseGrid) window.getFellowIfAny("dataList");
                Object models = new ArrayList();
                grid.filter((List) models);
                grid.display();
            }*/
            AlterDialog.alert(result);
            BaseGrid grid = (BaseGrid) window.getFellowIfAny("dataList");
            Object models = new ArrayList();
            grid.filter((List) models);
            grid.display();
        } catch (Exception e) {
            e.printStackTrace();
            AlterDialog.alert("导入失败");
        }
    }

    public void copyFile(String filePath) {
        int len = 0;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File file = new File(filePath);
        try {
            fis = new FileInputStream(filePath);
            String newFileName = filePath + ".xls";
            fos = new FileOutputStream(new File(newFileName));

            byte[] bt = new byte[1024];
            while ((len = fis.read(bt)) != -1) {
                fos.write(bt, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
