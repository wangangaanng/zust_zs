package com.ourway.utils;

import com.itextpdf.text.pdf.BaseFont;
import com.ourway.base.zk.ZKConstants;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;


/**
 *<p>功能描述: ftl转pdf</p>
 *<ul>
 *<li>@param null </li>
 *<li>@return </li>
 *<li>@throws </li>
 *<li>@author </li>
 *<li>@date 2019/8/5 14:21</li>
 *</ul>
*/
public class HtmlTopdf {
    private static final String FONT = "\\fonts\\simsun.ttc";

    private static Configuration freemarkerCfg = null;
    private static String fontPath = "";
    static {
        freemarkerCfg = new Configuration();
        try {
            //freemarker的模板目录
            String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
            //去掉返回路径中各种不需要的东西
            path = path.replace('/', '\\'); // 将/换成\
            path = path.replace("file:", ""); // 去掉file:
            path = path.substring(1); // 去掉第一个\
            String ftlpath = path.substring(0, path.indexOf("WEB-INF") + 7) + "\\template\\";
            fontPath = path.substring(0, path.indexOf("WEB-INF"))+"";
            freemarkerCfg.setDirectoryForTemplateLoading(new File(ftlpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws IOException, DocumentException, com.lowagie.text.DocumentException {
//        String path = "file:/E:/workspace/projectManage/out/artifacts/web_zk_war_exploded/WEB-INF/classes/";
//        path = path.replace('/', '\\'); // 将/换成\
//        path = path.replace("file:", ""); // 去掉file:
//        // path = path.replace("classes\\", ""); // 去掉class\
//        path = path.substring(1); // 去掉第一个\,如 \D:\JavaWeb...
//        System.out.println(path);
//    }

    /**
     *<p>功能描述:genteratPdf 生成pdf</p>
     *<ul>
     *<li>@param newFileName 生成pdf的文件名</li>
     *<li>@param resultMap 动态填充的数据集合</li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2019/8/5 14:10</li>
     *</ul>
    */
    public static void genteratPdf(String newFileName,Map<String,Object> resultMap) throws IOException, com.lowagie.text.DocumentException {
        String content = freeMarkerRender(resultMap,"projectReport.ftl");
        createPdf(content, ZKConstants.SYSTEM_FILE_PATH+"mirror/"+newFileName);
    }

    public static void genteratTargetPdf(String filePath,Map<String,Object> resultMap) throws IOException, com.lowagie.text.DocumentException {
        String content = freeMarkerRender(resultMap,"targetReport.ftl");
        createPdf(content, ZKConstants.SYSTEM_FILE_PATH+filePath);
    }



    /**
     * freemarker渲染html
     */
    private static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            template.process(data, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static void createPdf(String content, String dest) throws IOException, com.lowagie.text.DocumentException {
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver fontResolver = render.getFontResolver();
        fontResolver.addFont(fontPath + FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解析html生成pdf
        render.setDocumentFromString(content);
        //解决图片相对路径的问题
//        render.getSharedContext().setBaseURL(LOGO_PATH);
        render.layout();
        render.createPDF(new FileOutputStream(dest));
    }
}
