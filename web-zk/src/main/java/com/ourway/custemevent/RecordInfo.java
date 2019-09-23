package com.ourway.custemevent;

import com.ourway.base.CommonConstants;
import com.ourway.base.utils.TextUtils;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;

import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentFileSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordInfo implements ComponentFileSer {
    public  final String URL="web/zustcommon/bckjBizYhxx/recordInfo";
    public  final String FolderPath="F:\\img\\";
    @Override
    public void doAction(BaseWindow window, Map<String, Object> map, String s) {
        String result="";
        String path = map.get("filePath").toString();
        String filePath=FolderPath+path;
        //添加后缀
        copyFile(filePath);
        Map<String, Object> params = new HashMap<String,Object>();
        if (!TextUtils.isEmpty(filePath)) {
            params.put("path", filePath + ".xls");
        }
        try {
             result=JsonPostUtils.executeAPIAsString(params,URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(TextUtils.isEmpty(result)){
            AlterDialog.alert("导入失败");
        }
        try{
            if(result.indexOf("操作成功")!=-1){
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
        }catch (Exception e){
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
