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
    .tag-grey{
        position: relative;
        top: -2px;
    }
    a:hover{text-decoration: none;}
    table>tbody>tr>td{vertical-align: middle !important;}

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
                <li><a href="${base}/enterpriseService/3">企业服务</a></li>
                <li class="active">招聘会详情</li>
            </ol>
        </div>
        <ul class="nav nav-tabs" id="nav-tabs-job">
            <li role="presentation" class="active"><a href="#" onclick="tabs(0)">招聘会信息</a></li>
            <#if result?? && result.state?? && result.state==1>
            <li role="presentation"><a href="#" onclick="tabs(1)">关注学生</a></li>
                <#if result.zphSfbm?? && result.zphSfbm==1>
                <li role="presentation"><a href="#" onclick="tabs(2)">报名学生</a></li>
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
                            <dt><a href="">招聘会</a></dt>
                        </dl>
                    </div>
                    <ul class="xInfo" style="width: 50%;float: left;">
                    <#if result.zphKsrq?exists>
                        <li>举办日期：<span>${result.zphKsrq?substring(0,10)}</span></li>
                    </#if>
                    <#if result.zphJtsj?exists>
                        <li>具体时间：<span> ${result.zphJtsj!''}</span></li>
                    </#if>
                        <li>举办地点：<span>${result.zphJbdd!''}</span></li>
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
                    <#if result.zwbh?exists>
                        <li>展位：<span> ${result.zwbh!''}</span></li>
                    </#if>
                    <#if (result.memo?exists) && (result.state==2)>
                        <li>拒绝原因：<span> ${result.memo!''}</span></li>
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
                    <#if result.zw1?exists>
                    <li>招聘岗位：<span> ${result.zw1!''}</span><span>（${(result.rs1)!''}人）</span></li>
                    </#if>
                    <#if result.zw2?exists>
                    <li>招聘岗位：<span> ${result.zw2!''}</span><span>（${(result.rs2)!''}人）</span></li>
                    </#if>
                    <#if result.zw3?exists>
                    <li>招聘岗位：<span> ${result.zw3!''}</span><span>（${(result.rs3)!''}人）</span></li>
                    </#if>
                    <#if result.zw4?exists>
                    <li>招聘岗位：<span> ${result.zw4!''}</span><span>（${(result.rs4)!''}人）</span></li>
                    </#if>
                    <#if result.zw5?exists>
                    <li>招聘岗位：<span> ${result.zw5!''}</span><span>（${(result.rs5)!''}人）</span></li>
                    </#if>
                    </ul>
            </div>
               <div class="position-tabcontent">
                   <div class="position-tabbar"><ul><li class="active"><a>详情</a></li>
                                    <#if (result.bmList??)&&(result.bmList?size>0)>
                                        <li><a>参会企业</a></li>
                                    </#if>
                   </ul></div>
                   <div class="frame-body tabbar-frame_content">
                       <div class="tabcontent">
                           <div><p>${result.detail!''}</p></div>
                                        <#if (result.fileList??)&&(result.fileList?size>0)>
                                            <div class="file">
                                                <div style="font-weight: bold;font-size: 16px;">附件</div>
                                                <ul>
                                                    <#list result.fileList as obj>
                                                        <li><a href="${imagePath+obj.FILE_PATH}">${obj.FILE_LABEL!''}</a></li>
                                                    </#list>
                                                </ul>
                                            </div>
                                        </#if>
                       </div>
                       <div class="tabcontent" style="display: none;">
                           <table class="table table-bordered" style="text-align: center">
                               <tr>
                                   <td>序号</td><td>企业名称</td><td>招聘岗位</td><td>招聘人数</td>
                               </tr>
                                            <#if (result.bmList??)&&(result.bmList?size>0)>
                                                <#list result.bmList as qy>
                                                    <#if (qy.zwList??)&&(qy.zwList?size>0)>
                                                        <#list qy.zwList as gw>
                                                            <#if gw_index==0>
                                                            <tr>
                                                                <td rowspan="${qy.zwList?size}">${qy_index+1}</td><td rowspan="${qy.zwList?size}">${qy.qymc!''}</td><td>${gw.zw!''}</td><td>${gw.rs!''}</td>
                                                            </tr>
                                                            <#else >
                                                            <tr>
                                                                <td>${gw.zw!''}</td><td>${gw.rs!''}</td>
                                                            </tr>
                                                            </#if>
                                                        </#list>
                                                    <#else >
                                                    <tr>
                                                        <td>${qy_index+1}</td><td>${qy.qymc!''}</td><td>-</td><td>-</td>
                                                    </tr>
                                                    </#if>

                                                </#list>
                                            </#if>
                           </table>
                       </div>

                   </div>
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
    <script src="${base}/js/zphXq.js" type="text/javascript"></script>
</body>

</html>