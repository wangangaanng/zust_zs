package com.zghzbckj.manage.utils;

import com.zghzbckj.base.config.Global;
import com.zghzbckj.base.util.SpringContextHolder;
import com.zghzbckj.common.SwytConstant;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

/**
 * <p>freemaker工具类 </p>
 * <ul>
 * <li>@author D.chen.g </li>
 * <li>@date 2019/10/25 20:04  </li>
 * </ul>
 */
public class TemplateUtils {
    public static String freeMarkerContent(Object root, String ftl) {
        try {
            Configuration configuration = SpringContextHolder.getBean(Configuration.class);
            configuration.setDefaultEncoding("UTF-8");
            Template temp = configuration.getTemplate(ftl + SwytConstant.FTLFILE_SUFFIX);
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            temp.setEncoding("UTF-8");
            File fileParent = new File(Global.getConfig(SwytConstant.SWTYFILEPATH));
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            Writer file = new StringWriter();
            temp.process(root, file);
            file.flush();
            file.close();
            return file.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
