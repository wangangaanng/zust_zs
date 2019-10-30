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
    /*.nav-tabs li a{color: #008784;}*/
    /*.bootstrap-table .fixed-table-container.fixed-height:not(.has-footer) {*/
    /*border-bottom: none !important;*/
    /*}*/
    /*.fixed-table-border{*/
    /*border: none !important;*/
    /*height: 0 !important;*/
    /*}*/
    .xInfo li{min-height: 30px; height: auto;padding: 5px;}
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
                <li><a href="${base}/enterpriseService/2">企业服务</a></li>
                <li class="active">宣讲会详情</li>
            </ol>
        </div>
        <ul class="nav nav-tabs" id="nav-tabs-job">
            <li role="presentation" class="active"><a onclick="tabs(0)">宣讲会信息</a></li>
            <#if result?? && (result.state)?? && result.state==1>
            <li role="presentation"><a onclick="tabs(1)">关注学生</a></li>
                <#if (result.zphSfbm)?? && result.zphSfbm==1>
                <li role="presentation"><a onclick="tabs(2)">报名学生</a></li>
                </#if>
            </#if>
        </ul>
        <div class="content-form" style="border-top:none;display: none;padding: 0;min-height: 400px;" id="stuList">
            <div class="e-table" style="padding: 0 20px;">
                <table class="table table-hover" data-locale="zh-CN" id="table-job" style="table-layout: fixed;
                          word-break:break-all; word-wrap:break-all;">
                </table>

            </div>
        </div>
        <div class="content-form" style="border-top:none;display: none;padding: 0;min-height: 400px;" id="stuList1">
            <div class="e-table" style="padding: 0 20px;">
                <table class="table table-hover" data-locale="zh-CN" id="table-job1" style="table-layout: fixed;
                          word-break:break-all; word-wrap:break-all;">
                </table>

            </div>
        </div>
        <div class="content-form" style="border-top:none;padding: 0;" id="jobInfo">
            <div class="position-detail" style="overflow: hidden;">
                <#if result??>
                <div class="position-head">
                    <#if (result.zwSxsj?exists)&&((result.zwSxsj)?date("yyyy-MM-dd HH:mm:ss") lt (.now)?date)>
                        <h1><span class="tag-grey">过期</span>${result.zwbt!''}</h1>
                    <#else >
                        <h1>${result.zwbt!''}</h1>
                    </#if>
                    <dl class="info">
                        <dt><a>宣讲会</a></dt>
                    </dl>
                </div>
                <ul class="xInfo" style="width: 50%;float: left;">
                    <#if result.xjsj?exists>
                        <li>宣讲日期：<span> ${result.xjsj?substring(0,10)}</span></li>
                    </#if>
                    <#if result.zphJtsj?exists>
                        <li>具体时间：<span> ${result.zphJtsj!''}</span></li>
                    </#if>
                    <#if result.zphJbdd?exists>
                    <li>举办地点：<span>${result.zphJbdd!''}</span></li>
                    </#if>
                    <#if result.zphBmjzsj?exists>
                        <li>报名截止时间：<span>${result.zphBmjzsj?substring(0,10)}</span></li>
                    </#if>
                    <#if result.xxlxr?exists>
                        <li>学校联系人：<span> ${result.xxlxr!''}</span></li>
                    </#if>
                    <#if result.xxlxrdh?exists>
                        <li>学校联系电话：<span> ${result.xxlxrdh!''}</span></li>
                    </#if>
                    <#if result.state?exists>
                        <li>审核状态：
                        <#if result.state==0>
                            <span>待审核</span>
                            <#elseif result.state==1>
                            <span style='color: #008784;'>审核通过</span>
                            <#elseif result.state==2>
                            <span style='color: red;'>审核拒绝</span>
                            <#else >
                        </#if>
                        </li>
                    </#if>
                    <#if (result.memo?exists) && (result.state==2)>
                        <li>拒绝原因：<span> ${result.memo!''}</span></li>
                    </#if>
                    <#if result.jkr?exists>
                        <li>讲课人：<span> ${result.jkr!''}</span></li>
                    </#if>
                    <#if result.jkrjs?exists>
                        <li>讲课人介绍：<span> ${result.jkrjs!''}</span></li>
                    </#if>
                    <#if result.xjhsqly?exists>
                        <li>申请理由：<span> ${result.xjhsqly!''}</span></li>
                    </#if>
                    <#if result.memo?exists>
                        <li>备注：<span> ${result.memo!''}</span></li>
                    </#if>
                </ul>
                <ul class="xInfo" style="width: 50%;float: left;">
                    <#if result.zdytj1?exists>
                        <li><span> ${result.zdytj1!''}</span></li>
                    </#if>
                    <#if result.zdytj2?exists>
                        <li><span> ${result.zdytj2!''}</span></li>
                    </#if>
                    <#if result.zdytj3?exists>
                        <li><span> ${result.zdytj3!''}</span></li>
                    </#if>
                    <#if result.zdytj4?exists>
                        <li><span> ${result.zdytj4!''}</span></li>
                    </#if>
                    <#if result.zdytj5?exists>
                        <li><span> ${result.zdytj5!''}</span></li>
                    </#if>
                </ul>
            </div>
                </#if>
        </div>
    </div>
    <#if result??>
    <input type="hidden" id="jobRefOwid" value="${result.jobRefOwid!''}">
    <input type="hidden" id="owid" value="${result.owid!''}">
    </#if>

<#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/bootstrap-table.min.js" type="text/javascript"></script>
    <script src="${base}/js/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
    <script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${base}/js/messages_zh.min.js" type="text/javascript"></script>
    <script src="${base}/js/laydate/laydate.js" type="text/javascript"></script>
    <script src="${base}/js/city1.js" type="text/javascript"></script>
    <script src="${base}/js/xjhXq.js" type="text/javascript"></script>
</body>

</html>