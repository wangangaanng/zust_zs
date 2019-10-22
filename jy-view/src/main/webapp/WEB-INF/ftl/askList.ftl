<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>
<style>

</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i> 当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a>首页</a></li>
                <li><a >学生服务</a></li>
                <li>咨询列表</li>
            </ol>
        </div>
        <div class="content">
            <div class="ask-list">
                <div class="al-item active2">
                    <div class="al-question">
                        <i></i> 我的提问：张老师，就业需要准备什么材料?
                    </div>
                    <div class="al-answer">
                        <i></i> 张老师的回复：简历，毕业证书等等。。
                    </div>
                    <div class="al-btn">
                        <button class="btn">删除</button>
                        <button class="btn">继续咨询</button>
                    </div>
                    <span class="glyphicon glyphicon-menu-up "></span>
                </div>
                <div class="al-item">
                    <div class="al-question">
                        <i></i> 我的提问：张老师，就业需要准备什么材料?
                    </div>
                    <div class="al-answer">
                        <i></i> 张老师的回复：简历，毕业证书等等。。
                    </div>
                    <span class="glyphicon glyphicon-menu-down "></span>
                </div>

            </div>

        </div>

    </div>
</div>

<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>