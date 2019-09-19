<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
</head>
<style>

</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
    <#include "com/route.ftl">
        <div class="content">
            <#if result??>
                <div class="article-detail">
                    <div class="article-detail-title">
                        <div class="h3">${result.wzbt!''}</div>
                        <div><span class="label1">作者：</span>${result.fbr!''} &nbsp;&nbsp;&nbsp;<span class="label1">提交时间：</span>${result.fbsj!''} &nbsp;&nbsp;&nbsp;<span class="label1">浏览：</span>${result.ydcs!'0'} 次 </div>
                    </div>
                    <div class="article-detail-text">
                        <p>${result.wznr!''}</p>
                        <#--<div class="article-pager">-->
                            <#--<div class="pull-left">上一篇：<a href="#">面试应战：面试的形式和种类 </a></div>-->
                            <#--<div class="pull-right">下一篇：<a href="#">没有英语证书的一则应聘面试故事</a></div>-->
                        <#--</div>-->
                    </div>
                </div>
            </#if>

        </div>

    </div>
</div>

<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>