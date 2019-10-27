package com.zghzbckj.manage.utils;

import freemarker.template.Configuration;

import java.io.IOException;

/**
 * Created by lujianing on 2017/5/7.
 */
public class JavaToPdfHtmlFreeMarker {

    private static final String FONT = "simhei.ttf";

    private static Configuration freemarkerCfg = null;


    public static void main(String[] args) throws IOException {
        Html2PdfUtil.createPdf("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                        "<head>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                        "    <meta http-equiv=\"Content-Style-Type\" content=\"text/css\"/>\n" +
                        "    <title></title>\n" +
                        "    <style mce_bogus=\"1\" type=\"text/css\">\n" +
                        "        body {\n" +
                        "\t\t font-family: SimHei;\n" +
                        "\t\t font-style:italic;\n" +
                        "\t\t font-weight:bold;\n" +
                        "\t\t background:none;\n" +
                        "\t\t margin: 0 auto;\n" +
                        "\t\t margin-top:15px;\n" +
                        "\t\t color: #000000;\n" +
                        "\t\t font-size:12px;\n" +
                        "\t\t}\n" +
                        "        .bt{\n" +
                        "\t\t   color: #121212;\n" +
                        "\t\t   font-size: 26px; \n" +
                        "\t\t   font-weight:bold; \n" +
                        "\t\t   line-height:80px;\n" +
                        "\t\t   text-align: center;\n" +
                        "\t\t}\n" +
                        "        .A4 {\n" +
                        "\t\t    margin: 0 auto; \n" +
                        "\t\t}\n" +
                        "        .f_12{ \n" +
                        "\t\t    font-size:12px;\n" +
                        "\t\t}\n" +
                        "        .f_red{ \n" +
                        "\t\t    color:#F00;\n" +
                        "\t\t}\n" +
                        "        .page {\n" +
                        "            padding: 1cm;\n" +
                        "            margin: 1cm auto;\n" +
                        "            border: 1px #D3D3D3 solid;\n" +
                        "            border-radius: 5px;\n" +
                        "            background: white;\n" +
                        "            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);\n" +
                        "            position: relative;\n" +
                        "        }\n" +
                        "        .subpage {\n" +
                        "            padding: 1cm;\n" +
                        "            border: 5px red solid;\n" +
                        "            height: 256mm;\n" +
                        "            outline: 2cm #FFEAEA solid;\n" +
                        "        }\n" +
                        "        .water-print{\n" +
                        "            background-color:#FFFFFF;\n" +
                        "            background-image:url(img/img_logo2.png)  ;\n" +
                        "            background-repeat:repeat;\n" +
                        "        }\n" +
                        "        .noprint h6{\n" +
                        "\t\t   margin: 0px;\n" +
                        "\t\t   font-style: normal;\n" +
                        "\t\t   color: #333;\n" +
                        "\t\t   text-align: center;\n" +
                        "\t\t}\n" +
                        "        .noprint{\n" +
                        "\t\t   position: fixed;\n" +
                        "\t\t   bottom: 50px;\n" +
                        "\t\t   right: 100px;\n" +
                        "\t\t   text-decoration: none;\n" +
                        "\t\t   z-index: 99999;\n" +
                        "\t\t   width: 70px;\n" +
                        "\t\t   height: 70px;\n" +
                        "\t\t   border-radius: 50%;\n" +
                        "\t\t   background: rgba(0,0,0,0.1);\n" +
                        "\t\t }\n" +
                        "        .noprint img{\n" +
                        "\t\t   width: 39px;\n" +
                        "\t\t   margin: 0 auto;\n" +
                        "\t\t   display: block;\n" +
                        "\t\t   margin-top: 13px;\n" +
                        "\t\t}\n" +
                        "\t\t\n" +
                        "\t\ttable { \n" +
                        "\t\t  page-break-inside:auto;\n" +
                        "\t\t  -fs-table-paginate:paginate;\n" +
                        "\t\t  border-spacing: 0;\n" +
                        "\t\t  table-layout:fixed;\n" +
                        "\t\t  word-break:break-strict;\n" +
                        "\t\t  cellspacing:0;\n" +
                        "\t\t  cellpadding:0;\n" +
                        "\t\t  /*border: solid 1px #ccc;*/\n" +
                        "\t\t  padding: 2px 2px;\n" +
                        "\t\t}\n" +
                        "\t\ttr    { \n" +
                        "\t\t   page-break-inside:avoid;\n" +
                        "\t\t   page-break-after:auto;\n" +
                        "\t\t}\n" +
                        "\t\tthead { \n" +
                        "\t\t   display:table-header-group;\n" +
                        "\t\t}\n" +
                        "\t\ttfoot { \n" +
                        "\t\t   display:table-footer-group;\n" +
                        "\t\t}\n" +
                        "        @page {\n" +
                        "            margin: 0;\n" +
                        "\t\t\tmargin-top:25px;\n" +
                        "        }  \n" +
                        "        @media print {\n" +
                        "\t\t    .page {\n" +
                        "                border: initial;\n" +
                        "                border-radius: initial;\n" +
                        "                width: initial;\n" +
                        "                min-height: initial;\n" +
                        "                box-shadow: initial;\n" +
                        "                background: initial;\n" +
                        "                page-break-after: always;\n" +
                        "            }\n" +
                        "            .noprint{display:none;}\n" +
                        "\t\t\t\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "    <script type=\"text/javascript\">\n" +
                        "       \n" +
                        "        var prnhtml=\"\";\n" +
                        "        function doPrint() {\n" +
                        "            bdhtml=window.document.body.innerHTML;\n" +
                        "            sprnstr=\"<!--startprint-->\";\n" +
                        "            eprnstr=\"<!--endprint-->\";\n" +
                        "\t\t\tif(prnhtml!=''){\n" +
                        "\t\t\t   bdhtml = sprnstr+bdhtml+eprnstr;\n" +
                        "\t\t\t}\n" +
                        "\t\t\tprnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);\n" +
                        "            //console.log(prnhtml);\n" +
                        "            prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));\n" +
                        "            //console.log(prnhtml);\n" +
                        "\t\t\twindow.document.body.innerHTML=prnhtml;\n" +
                        "            window.print();\n" +
                        "        }\n" +
                        "      \n" +
                        "\n" +
                        "    </script>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<!--startprint-->\n" +
                        "<!--第一页开始-->\n" +
                        "<div class=\"page\" >\n" +
                        "    <a href=\"javascript:doPrint()\" class=\"noprint\">\n" +
                        "        <img src=\"img/print.png\"/>\n" +
                        "        <h6>打印</h6>\n" +
                        "    </a>\n" +
                        "\t\n" +
                        "    <table width=\"100%\" align=\"center\" style=\"margin-top: 1px;border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"left\">\n" +
                        "                <img src=\"img/logo.png\" width=\"80px\" />\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "\n" +
                        "    </table>\n" +
                        "    <table width=\"100%\" align=\"center\" style=\"margin-top: 60px;border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"center\"  valign=\"middle\" style=\"font-weight:bold;font-size:30px;color:#000000;font-style: normal;\">\n" +
                        "               内蒙古 . 包头市项目投资报告\n" +
                        "            \n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <table width=\"80%\" align=\"center\" style=\"margin-top: 100px;border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"center\" colspan=\"2\"  valign=\"middle\" style=\"text-align: center;\">\n" +
                        "                <img src=\"E:\\workplace\\idea\\myproject2019\\zustWebSite\\img\\logo.png\" style=\"width: 200px;height: 200px;\" />\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <table width=\"80%\" align=\"center\" style=\"margin-top:120px;bottom:15px;border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td style=\"font-size:24px;color:#000000;font-style: normal;text-align: center;align-content: center;\">\n" +
                        "               ${fromSystem}投资项目管理平台\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "</div>\n" +
                        "<div class=\"page water-print\">\n" +
                        "    <table width=\"100%\" align=\"center\"  style=\"border-collapse:collapse\">\n" +
                        "        <tr>\n" +
                        "            <td align=\"center\"  valign=\"middle\" style=\"font-weight:bold;font-size:35px;color:#000000;font-style: normal;\">\n" +
                        "                项目总览\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#121212;margin-top: 25px;border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td height=\"30\" align=\"left\" valign=\"center\" style=\"color:#FFFFFF;font-size: 20px;font-style: normal;\">投资总览</td>\n" +
                        "            <td align=\"right\" style=\"color:#FFFFFF;font-size: 20px;font-style: normal;\">单位：万元</td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <table width=\"100%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"  style=\"border-collapse:collapse\">\n" +
                        "        <tr style=\"height: 40px;\" >\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">项目内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">计划投资</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">比重%</td>\n" +
                        "            <#--<td align=\"center\" style=\"font-size: 20px;font-style: normal;\">前期投资</td>-->\n" +
                        "            <#--<td align=\"center\" style=\"font-size: 20px;font-style: normal;\">当期完成</td>-->\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">累计完成</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">进度%</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">备注</td>\n" +
                        "        </tr>\n" +
                        "        <#list tzzl as p>\n" +
                        "            <tr style=\"height: 30px;\">\n" +
                        "                <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">${p.name!''}</td>\n" +
                        "                <td align=\"center\" style=\"font-size: 18px;font-style: normal;\"><#if p.jhtz==''|| p.jhtz==0 || null==p.jhtz>/<#else> ${p.jhtz?string(',###')} </#if></td>\n" +
                        "                <td align=\"center\" style=\"font-size: 18px;font-style: normal;\"><#if p.rate==''|| p.rate==0 || null==p.rate >/<#elseif p.rate=='*' || p.rate=='-'>${p.jd}<#else> ${p.rate} </#if></td>\n" +
                        "                <#--<td align=\"center\" style=\"font-size: 18px;font-style: normal;\"><#if p.qqtz==''|| p.qqtz==0 || null==p.qqtz >/<#else> ${p.qqtz?string(',###')} </#if></td>-->\n" +
                        "                <#--<td align=\"center\" style=\"font-size: 18px;font-style: normal;\"><#if p.currFinish==''|| p.currFinish==0 || null==p.currFinish >/<#else> ${p.currFinish?string(',###')} </#if></td>-->\n" +
                        "                <td align=\"center\" style=\"font-size: 18px;font-style: normal;\"><#if p.totalFinish==''|| p.totalFinish==0 || null==p.totalFinish >/<#else> ${p.totalFinish?string(',###')} </#if></td>\n" +
                        "                <td align=\"center\" style=\"font-size: 18px;font-style: normal;\"><#if p.jd==''|| p.jd==0 || null==p.jd >/<#elseif p.jd=='*' || p.jd=='-'>${p.jd}<#else> ${p.jd} </#if></td>\n" +
                        "                <td></td>\n" +
                        "            </tr>\n" +
                        "        </#list>\n" +
                        "\n" +
                        "    </table>\n" +
                        "    <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#121212;margin-top: 25px;border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td height=\"30\" align=\"left\" valign=\"center\" style=\"color:#FFFFFF;font-size: 20px;font-style: normal;\">政府投资</td>\n" +
                        "            <td align=\"right\" style=\"color:#FFFFFF;font-size: 20px;font-style: normal;\">单位：万元</td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <table width=\"100%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"  style=\"border-collapse:collapse\">\n" +
                        "        <tr style=\"height: 40px;\" >\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">项目内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">计划投资</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">比重%</td>\n" +
                        "            <#--<td align=\"center\" style=\"font-size: 20px;font-style: normal;\">前期投资</td>-->\n" +
                        "            <#--<td align=\"center\" style=\"font-size: 20px;font-style: normal;\">当期完成</td>-->\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">累计完成</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">进度%</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">备注</td>\n" +
                        "        </tr>\n" +
                        "       \n" +
                        "           <tr style=\"height: 30px;\">\n" +
                        "               <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">1</td>\n" +
                        "               <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">21</td>\n" +
                        "               <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">234234</td>\n" +
                        "               <#--<td align=\"center\" style=\"font-size: 18px;font-style: normal;\">232323</td>-->\n" +
                        "               <#--<td align=\"center\" style=\"font-size: 18px;font-style: normal;\">444444</td>-->\n" +
                        "               <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">877623</td>\n" +
                        "               <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">1212</td>\n" +
                        "               <td></td>\n" +
                        "           </tr>\n" +
                        "     \n" +
                        "\n" +
                        "    </table>\n" +
                        "\n" +
                        "    <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#121212;margin-top: 25px;border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td height=\"30\" align=\"left\" valign=\"center\" style=\"color:#FFFFFF;font-size: 20px;font-style: normal;\">\n" +
                        "                项目流程\n" +
                        "            </td>\n" +
                        "            <td align=\"right\" style=\"color:#FFFFFF;font-size: 20px;font-style: normal;\">单位：个数</td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <table width=\"100%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"  style=\"border-collapse:collapse;\">\n" +
                        "      <thead>\n" +
                        "\t      <tr>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">流程内容1</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">合计</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">已完成</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">未完成</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">已评审</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">完成度</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 20px;font-style: normal;\">备注</td>\n" +
                        "        </tr>\n" +
                        "\t </thead>\n" +
                        "        <tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">2流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">3流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">4流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">5流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">6流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">7流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">8流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">9流程内容流程内容流流程内容流流程内容流流程内容流流程内容流流程<BR/>内容流流程内容流流程内容流流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">10流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">11流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">12流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">13流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">14流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">15流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">16流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">17流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">18流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">19流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "\t\t<tr >\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">20流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容流程内容流程内容流程内容</td>\n" +
                        "            <td align=\"center\" style=\"font-size: 18px;font-style: normal;\">流程内容</td>\n" +
                        "        </tr>\n" +
                        "        \n" +
                        "\n" +
                        "\n" +
                        "    </table>\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"page\" >\n" +
                        "   <table width=\"100%\" style=\"border-collapse:collapse\">\n" +
                        "       <tr>\n" +
                        "\t      <td>\n" +
                        "\t\t     <table width=\"100%\" align=\"center\" style=\"border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "\t\t  </td>\n" +
                        "\t   </tr>\n" +
                        "\t   <tr>\n" +
                        "\t      <td>\n" +
                        "\t\t     <table width=\"100%\" align=\"center\" style=\"border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "\t\t  </td>\n" +
                        "\t   </tr>\n" +
                        "\t   <tr>\n" +
                        "\t      <td>\n" +
                        "\t\t     <table width=\"100%\" align=\"center\" style=\"border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "\t\t  </td>\n" +
                        "\t   </tr>\n" +
                        "\t   <tr>\n" +
                        "\t      <td>\n" +
                        "\t\t     <table width=\"100%\" align=\"center\" style=\"border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "\t\t  </td>\n" +
                        "\t   </tr>\n" +
                        "\t   <tr>\n" +
                        "\t      <td>\n" +
                        "\t\t     <table width=\"100%\" align=\"center\" style=\"border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "\t\t  </td>\n" +
                        "\t   </tr>\n" +
                        "\t   <tr>\n" +
                        "\t      <td>\n" +
                        "\t\t     <table width=\"100%\" align=\"center\" style=\"border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "\t\t  </td>\n" +
                        "\t   </tr>\n" +
                        "\t   <tr>\n" +
                        "\t      <td>\n" +
                        "\t\t     <table width=\"100%\" align=\"center\" style=\"border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "\t\t  </td>\n" +
                        "\t   </tr>\n" +
                        "\t   <tr>\n" +
                        "\t      <td>\n" +
                        "\t\t     <table width=\"100%\" align=\"center\" style=\"border-collapse:collapse\" >\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目编号：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCode}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;width: 200px;\">\n" +
                        "                项目名称：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTitle}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.compName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                主管单位：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectTopDeptName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                申报地区：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectReginCodeName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目政策：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "               ${projectInfo.projectPolicyName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                项目类型：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "                ${projectInfo.projectCatogoryName}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                建设期限：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.projectYears}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\"  valign=\"middle\" style=\"font-size:26px;color:#000000;font-style: normal;\">\n" +
                        "                报告日期：\n" +
                        "            </td>\n" +
                        "            <td align=\"left\" valign=\"middle\" style=\"font-size: 20px;color: #000000;font-style: normal;\">\n" +
                        "            ${projectInfo.currDate}\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "\t\t  </td>\n" +
                        "\t   </tr>\n" +
                        "   </table>\n" +
                        "   \n" +
                        "\t\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "<!--endprint-->\n" +
                        "</body>\n" +
                        "</html>"
                ,"D://TTT.pdf");
    }

 

}