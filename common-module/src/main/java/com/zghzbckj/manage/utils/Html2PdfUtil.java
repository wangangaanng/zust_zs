package com.zghzbckj.manage.utils;

import com.google.common.collect.Maps;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreak;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;
import com.zghzbckj.base.config.Global;
import com.zghzbckj.common.CommonModuleContant;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * by 明明如月 github :https://github.com/chujianyun
 */

public class Html2PdfUtil {

    private static final  String PROMISE_NAME="承诺书.pdf";

    public static String doPromise(String content) throws IOException {
        Map mapData= Maps.newHashMap();
        mapData.put("wznr",content);
        String htmlData= TemplateUtils.freeMarkerContent(mapData,"ad");
        String fileName= CommonModuleContant.SWTYFILEPATH+File.separator+PROMISE_NAME;
        createPdf(htmlData,Global.getConfig(CommonModuleContant.SWTYFILEPATH)+fileName);
        return fileName;
    }

    /**
     * @param htmlContent html文本
     * @param dest        目的文件路径，如 /xxx/xxx.pdf
     * @throws IOException IO异常
     */
    public static void createPdf(String htmlContent, String dest) throws IOException {
        File file=new File(dest);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        ConverterProperties props = new ConverterProperties();
         props.setCharset("UFT-8"); //编码
        FontProvider fp = new FontProvider();
        fp.addStandardPdfFonts();
        // .ttf 字体所在目录
        String resources = Global.getConfig(CommonModuleContant.SWTYFILEPATH)+CommonModuleContant.FONTS_TTF;//Html2PdfUtil.class.getResource(FONT_RESOURCE_DIR).getPath();
        fp.addDirectory(resources);
        props.setFontProvider(fp);
        // html中使用的图片等资源目录（图片也可以直接用url或者base64格式而不放到资源里）
        // props.setBaseUri(resources);

        List<IElement> elements = HtmlConverter.convertToElements(htmlContent, props);
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf, PageSize.A4, false);
        for (IElement element : elements) {
            // 分页符
            if (element instanceof HtmlPageBreak) {
                document.add((HtmlPageBreak) element);

                //普通块级元素
            } else {
                document.add((IBlockElement) element);
            }
        }
        document.close();
    }
}
