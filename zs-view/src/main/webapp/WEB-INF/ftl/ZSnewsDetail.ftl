<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <style>
        .content-list{
            width:100%;}
        .routes{
            font-size:14px;}
        .nav-bar{
            margin: 0 20px;
            padding: 15px 0;
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<style>

</style>

<body>
    <#include "com/ZSheader.ftl">
    <#--<img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">-->
    <div class="clear"></div>
        <div class="main">
            <div class="container">
                <div class="content">
                   <#--<#include "com/subMenu.ftl">-->
                    <div class="content-list">
                        <div class="nav-bar">
                        <#include "com/route.ftl">
                            <div style="clear:both;"></div>
                        </div>

                    <#if result??>
                        <div class="fuye_list_infor">
                            <h1 id="article_title">${result.wzbt!''}</h1>
                            <h6><span>${result.fbsj!''}</span> | <span>来源：${result.wzly!'暂无'}</span> | <span class="liulan"><i class="icon"></i>关注度：${result.ydcs!'0'}</span></h6>
                        <#--<div class="article-detail-title">-->
                                <#--<div class="h3">${result.wzbt!''}</div>-->
                                <#--<div><span class="label1">发布人：</span>${result.fbr!''} &nbsp;&nbsp;&nbsp;<span class="label1">发布时间：</span>${result.fbsj!''} &nbsp;&nbsp;&nbsp;<span class="label1">浏览：</span>${result.ydcs!'0'} 次 </div>-->
                            <#--</div>-->
                            <div class="article-detail-text">
                                <p>${result.wznr!''}</p>
                                <#if (result.fileList??)&&(result.fileList?size>0)>
                                    <div class="file">
                                        <div style="font-weight: bold;font-size: 16px;">附件</div>
                                        <ul>
                                            <#list result.fileList as obj>
                                                <li>${obj.FILE_LABEL!''}<a href="${imagePath+obj.FILE_PATH}">${obj.FILE_PATH!''}</a></li>
                                            </#list>
                                        </ul>
                                    </div>
                                </#if>
                            </div>
                            <div class="article-pager">
                                <#if (result.upArticle??)&&!(result.upArticle?is_number)>
                                    <div>上一篇：<a href="${base}/wzxq/${result.upArticle.owid!''}">${result.upArticle.wzbt!''} </a></div>
                                <#else >
                                    <div>上一篇：<a href="#">无 </a></div>
                                </#if>
                                <#if (result.downArticle??)&&!(result.downArticle?is_number)>
                                    <div>下一篇：<a href="${base}/wzxq/${result.downArticle.owid!''}">${result.downArticle.wzbt!''} </a></div>
                                <#else >
                                    <div>下一篇：<a href="#">无 </a></div>
                                </#if>
                            </div>
                        </div>
                    </#if>
                    </div>
                </div>

            </div>
        </div>

    <#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
