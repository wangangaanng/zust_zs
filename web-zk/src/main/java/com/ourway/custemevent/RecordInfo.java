package com.ourway.custemevent;


import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.service.ComponentFileSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.JsonPostUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordInfo implements ComponentFileSer {
    //师生录入url
    public final String xsURL = "web/zustcommon/bckjBizSyb/recordInfo";
    //就业方案录入url
    public final String jsURL = "web/zustcommon/bckjBizJyscheme/recordInfo";
    //专家录入url
    public final String zxURL = "web/zustjy/bckjBizZjzx/recordInfo";
    //档案录入url
    public final String daURL = "web/zustjy/bckjBizDacx/recDanInfo";
    //就业排行榜url
    public final String rankURL = "web/zustjy/bckjBizJypm/importRankFromExcel";
    //企业信息录入url
    public final String qyxxURL = "web/zustjy/bckjBizQyxx/recordQyxxInfo";
    //企业招聘会录入url
    public final String zphURL = "web/zustjy/bckjBizJypm/recordJobInfo";
    //三位一体成绩
    public final String cjURL = "web/zustswyt/bckjBizBm/recordCjInfo";
    //招生计划
    public final String zsjhURL = "web/zustzs/bckjBizZsjh/recordInfo";
    //历年招生
    public final String lntjURL = "web/zustzs/bckjBizLntj/recordInfo";
    //录取招生
    public final String lqxsURL = "web/zustzs/bckjBizLqxs/recordInfo";
    //原始成绩
    public final String cjcxURL = "web/zustzs/bckjBizCjcx/recordInfo";
    //上传excel保存的本地地址
    public final String savePath = "/mnt/files/zjcFiles/excel/";
    public final String foundPath = "/mnt/files/zjcFiles/";
//    public final String FolderPath = "F:\\img\\";
    //地区典表 导入
    public final String dwszURL = "web/zustjy/bckjBizJyscheme/dqRecordInfo";


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
        if (pageCA.indexOf("location") != -1) {
            url = dwszURL;
        }
        if (pageCA.indexOf("jyscheme") != -1) {
            url = jsURL;
        }
        if (pageCA.indexOf("CompanyList") != -1) {
            url = qyxxURL;
        }
        if (pageCA.indexOf("zphbm") != -1) {
            url = zphURL;
        }
        if (pageCA.indexOf("jihua") != -1) {
            url = zsjhURL;
        }
        if (pageCA.indexOf("linian") != -1) {
            url = lntjURL;
        }
        if (pageCA.indexOf("luqu") != -1) {
            url = lqxsURL;
        }
        if (pageCA.indexOf("chengji") != -1) {
            url = cjcxURL;
        }
        if (pageCA.indexOf("cjdr") != -1) {
            url = cjURL;
        }

        String result = "";
        String path = map.get("filePath").toString();
        String foundfilePath = foundPath + path;  //线上路径
        String savefilePath = savePath + path;
        copyXsFile(foundfilePath, savefilePath);

//        String savefilePath = FolderPath + path;  //本地上传路径
//        copyFile(savefilePath); //本地上传路径*/

        Map<String, Object> params = new HashMap<String, Object>();

        if (!TextUtils.isEmpty(savefilePath)) {
            params.put("path", savefilePath + ".xls");
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

    public void copyXsFile(String foundfilePath, String savefilePath) {
        System.out.println(foundfilePath);
        System.out.println(savefilePath);
        int len = 0;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(foundfilePath);
            String newFileName = savefilePath + ".xls";
            File folder = new File(savefilePath.substring(0, savefilePath.lastIndexOf("/")));
            folder.mkdirs();
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
