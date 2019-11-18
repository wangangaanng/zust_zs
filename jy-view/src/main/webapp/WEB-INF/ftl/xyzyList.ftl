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
    .xyzy{color: #008784;padding-right: 20px;padding-top: 0px;}
    .xyzy li a{color: #292929;font-size: 16px;}
    .xyzy li{height: 60px;line-height: 60px;
        border-bottom:1px dashed #008784;}
</style>

<body>
    <#include "com/header.ftl">
        <div class="main">
            <div class="container">
                <div class="routes">
                    <div class="location">
                        <i></i>
                        当前位置：
                    </div>

                    <ol class="breadcrumb">
                        <li><a href="${base}/">首页</a></li>
                    <#if secondDirName??>
                        <li><a href="${base}/articleTpl/1/0">${secondDirName!''}</a></li>
                    </#if>
                    <#if thirdDirName??>
                        <li><a>${thirdDirName!''}</a></li>
                    </#if>
                    </ol>
                </div>
                <div class="content">
                    <div class="menu-nav">
                        <div class="menu-title">
                            <div class="title-chn">学院专业</div>
                            <div class="title-en">INFORMATION
                                <div class="menu-nav-icon"></div>
                            </div>

                        </div>
                        <div class="menu-list">
                            <ul class="list-group">
                            <#if (menuList??)&&(menuList?size>0)>
                                <#list menuList as obj>
                                    <#if obj.NAME==thirdDirName>
                                    <li class="list-group-item active1" onclick='openUrl("xyzyList/${secondDir!''}/${obj_index}")'>
                                    <#else >
                                    <li class="list-group-item" onclick='openUrl("xyzyList/${secondDir!''}/${obj_index}")'>
                                    </#if>
                                    <span class="ic-menu"></span> ${obj.NAME!''}
                                </li>
                                </#list>
                            </#if>
                            </ul>
                        </div>
                    </div>
                    <div class="content-list">
                    <div class="articleTpl-detail">
                        <div class="articleTpl-title">
                        ${thirdDirName!''}
                        </div>
                        <div class="article-detail-text">
                            <ul class="xyzy">
                                <#if (result??)&&(result.records??)&&(result.records?size>0)>
                                    <#list result.records as obj>
                                        <li><a href="${base}/newsDetail/${obj.owid!''}" target="_blank">${obj.wzbt!''}</a></li>
                                    </#list>
                                </#if>
                            </ul>


                        </div>
                    </div>
                </div>
                </div>

            </div>
        </div>

    <#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
