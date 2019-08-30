package com.zghzbckj.manage.utils;

import com.zghzbckj.base.util.SpringContextHolder;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

public class FtlFileUtil {

    private final  static String temName="detail.ftl";
    public static boolean freeMarkerContent(Map<String,Object> root, String owid, String path){
        try {
            Configuration configuration= SpringContextHolder.getBean(Configuration.class);
            configuration.setDefaultEncoding("UTF-8");
            Template temp = configuration.getTemplate(temName);
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            temp.setEncoding("UTF-8");
            File fileParent=new File(path);
            if(!fileParent.exists()) {
                fileParent.mkdirs();
            }
            String fileName=path+ File.separator+owid+".html";
            File fileOld=new File(fileName);
            if(fileOld.exists()){
                fileOld.delete();
            }
            Writer file = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "UTF-8"));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
