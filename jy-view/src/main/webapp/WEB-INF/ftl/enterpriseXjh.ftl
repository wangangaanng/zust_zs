<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/bootstrap-table.min.css" />
    <link rel="stylesheet" href="${base}/js/laydate/theme/default/laydate.css" />
</head>
<style>
    .layui-layer-content{overflow-y:auto !important;}
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
                <li><a href="${base}/">首页</a></li>
                <li><a href="#">企业服务</a></li>
                <li class="active">宣讲会申请</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav">
                <div class="menu-title">
                    <div class="title-chn">企业服务</div>
                    <div class="title-en">ENTERPRISE SERVICE
                        <div class="menu-nav-icon"></div>
                    </div>

                </div>
                <div class="menu-list">
                    <ul class="list-group">

                        <li class="list-group-item">
                            <span class="ic-menu"></span> 基本信息
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 职位信息发布
                        </li>
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 宣讲会申请
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 招聘会申请
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 招聘公告发布
                        </li>
                    </ul>
                </div>
            </div>

            <div class="content-list">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" id="zwbt-xjh" onkeydown="keyLogin()" class="form-control" placeholder="输入宣讲会进行查询">
                        <div class="input-group-btn">
                            <button type="button" onclick="searchXjh()" class="btn btn-default green"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                    <button class="btn pull-right green" onclick='applyXjh()'>点击申请宣讲会</button>
                </div>
                <div class="news-list">
                    <div class="question"></div>
                    <div class="e-table" style="padding: 0 20px;">
                        <table class="table table-hover" data-locale="zh-CN" id="table-xjh" style="table-layout: fixed;
                          word-break:break-all; word-wrap:break-all;">
                        </table>

                    </div>
                </div>
            </div>

        </div>

    </div>
</div>

<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<script src="${base}/js/city1.js" type="text/javascript"></script>
<script src="${base}/js/laydate/laydate.js" type="text/javascript"></script>
<script src="${base}/js/enterpriseXjh.js" type="text/javascript"></script>

</body>

</html>