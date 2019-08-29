package com.ourway.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Created by hanna on 2019/7/31.
 */
@Component
public class TestUtil {

//    private static String ClientUrl="E:\\MainProject\\projectManageAPI\\web-zk\\src\\main\\webapp\\WEB-INF\\template\\";
//    private static String ClientUrl;
//    @Value("#{propertyConfigurer['client.localUrl']}")
//    @Value("#{propertyConfigurer['client.localUrl']}")
//    public void setDatabase(String db) {
//        ClientUrl = db;
//        System.out.println("==url ="+ db);
//    }

    private static final String DEST = "C:\\Users\\DoodleJump\\Desktop\\pic\\test.pdf";
    private static final String ftlPath = "/template/";
    private static final String HTML = "atest.ftl";
    private static final String FONT = "/fonts/simsun.ttc";
    private static final String LOGO_PATH = "http://localhost:8080//files/20190309/1552117565418.jpeg";
    private static Properties properties;

    private static String getProperty(String key){
        if (properties==null) {
            properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try {
                properties.load(loader.getResourceAsStream("config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }
    private static String url=getProperty("client.localUrl");
    private static Configuration freemarkerCfg = null;

    static {
        freemarkerCfg =new Configuration();
        //freemarker的模板目录
        try {
//            String ftlpath=url+ftlPath;
            String ftlpath="webapp/WEB-INF/template/";
            System.out.println("==ftlpath ="+ ftlpath);
            freemarkerCfg.setDirectoryForTemplateLoading(new File(ftlpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
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

    public static void createPdf(String content,String dest) throws IOException, DocumentException, com.lowagie.text.DocumentException {
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver fontResolver = render.getFontResolver();
        fontResolver.addFont(url+FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解析html生成pdf
        render.setDocumentFromString(content);
        //解决图片相对路径的问题
        render.getSharedContext().setBaseURL(LOGO_PATH);
        render.layout();
        render.createPDF(new FileOutputStream(dest));
    }
}
