<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
</head>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <#include "com/route.ftl">
        <div class="content">
           <#include "com/subMenu.ftl">
            <div class="content-list">
                <div class="articleTpl-detail">
                    <div class="articleTpl-title">
                        ${thirdDirName!''}
                    </div>
                    <div class="article-detail-text">
                        <!--学校简介、中心介绍-->
                        <#if (bxlx??)&&(bxlx=="0")&&(result??)>
                            <p>${result.wznr!''}</p>
                        <#if (result.fileList??)&&(result.fileList?size>0)>
                            <div class="file">
                                <div>附件</div>
                                <ul>
                                    <#list result.fileList as obj>
                                        <li>${obj.FILE_LABEL!''}<a href="${imagePath+obj.FILE_PATH}">${obj.FILE_PATH!''}</a></li>
                                    </#list>
                                </ul>
                            </div>
                        </#if>

                        </#if>

                        <!--学院专业-->
                        <#if (bxlx??)&&(bxlx=="1")>
                        <style>
                            .xyzy{color: #008784;padding-right: 20px;padding-top: 15px;}
                            .xyzy li a{color: #292929;font-size: 16px;}
                            .xyzy li{height: 60px;line-height: 60px;
                                border-bottom:1px dashed #008784;}
                        </style>
                        <ul class="xyzy">
                            <#if (result??)&&(result?size>0)>
                                <#list result as obj>
                                    <li><a href="${base}/newsDetail/${obj.owid!''}">${obj.wzbt!''}</a></li>
                                </#list>
                            </#if>
                        </ul>
                        </#if>
                        <!--学院详情-->
                        <!--newsDetail.html类-->


                        <!--新闻公告、新闻快递、校内公示-->
                        <!--newsList.html类-->
                        <!--详情-->
                        <!--newsDetail.html类-->


                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<#include "com/footer.ftl">
</body>

</html>