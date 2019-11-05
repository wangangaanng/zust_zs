<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/style.css"/>
</head>
<style>
    .fuye_search{border-width:1px 0; padding: 18px 0 0 10px;; background-color:#f9f9f9; overflow:hidden;margin-right: 22px;}
    .fuye_search ul li{ float:left; padding:0 15px; margin-bottom:5px;}
    .fuye_search ul li p.title{ line-height:36px; float:left;}
    .fuye_search ul li a.fuye_search_btn, a.fuye_search_btn{ display: inline-block; background-color:rgb(85,167,153); height:34px; width:120px; text-align:center; line-height:34px; color:#fff; font-size:14px;}
    .form-control{width: 150px;display: inline-block}
    .table-bordered{width: 78%}
    .table-bordered th{text-align: center}
    .pagination-detail{margin-left: 21%}
    .pagination{margin:0 6px}
</style>
<body>
<#include "com/ZSheader.ftl">
<img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
<div class="main">
    <div class="container">
        <div class="content">
            <#include "com/subMenu.ftl">
            <div class="nav-bar">
            <#include "com/route.ftl">
            </div>
            <div class="content-form">
                <form class="form-horizontal" id="queryForm"  action="" target="queryFrame">
                    <div class="fuye_search">
                        <div class="form-group">
                            <label for="nf" class="col-sm-1">年份：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="nf" id="nf" onchange="getChanges(this)">
                                    <option value="">---请选择---</option>
                                    <#if (result??)&&(result.nfList??)&&(result.nfList?size>0)>
                                        <#assign flag=1>
                                        <#list result.nfList as obj>
                                            <option value="${obj.nf}">${obj.nf}</option>
                                        </#list>
                                    <#else >
                                        <#assign flag=0>
                                    </#if>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a class="nf" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                            <label for="sf" class="col-sm-1">省份：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="sf" id="sf" onchange="getChanges(this)">
                                    <option value="">---请选择---</option>
                                    <#if (result??)&&(result.sfList??)&&(result.sfList?size>0)>
                                        <#assign flag=1>
                                        <#list result.sfList as obj>
                                            <option value="${obj.sf}">${obj.sf}</option>
                                        </#list>
                                    <#else >
                                        <#assign flag=0>
                                    </#if>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a class="sf" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                            <label for="kl" class="col-sm-1">科类：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="sf" id="kl" onchange="getChanges(this)">
                                    <option value="">---请选择---</option>
                                    <#if (result??)&&(result.klList??)&&(result.klList?size>0)>
                                        <#assign flag=1>
                                        <#list result.klList as obj>
                                            <option value="${obj.kl}">${obj.kl}</option>
                                        </#list>
                                    <#else >
                                        <#assign flag=0>
                                    </#if>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a class="kl" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pc" class="col-sm-1">批次：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="pc" id="pc" onchange="getChanges(this)">
                                    <option value="">---请选择---</option>
                                    <#if (result??)&&(result.pcList??)&&(result.pcList?size>0)>
                                        <#assign flag=1>
                                        <#list result.pcList as obj>
                                            <option value="${obj.pc}">${obj.pc}</option>
                                        </#list>
                                    <#else >
                                        <#assign flag=0>
                                    </#if>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a class="pc" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                            <label for="zy" class="col-sm-1">专业：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="zy" id="zy" onchange="getChanges(this)">
                                    <option value="">---请选择---</option>
                                    <#if (result??)&&(result.zyList??)&&(result.zyList?size>0)>
                                        <#assign flag=1>
                                        <#list result.zyList as obj>
                                            <option value="${obj.zy}">${obj.zy}</option>
                                        </#list>
                                    <#else >
                                        <#assign flag=0>
                                    </#if>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a class="zy" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                            <button type="button" class="btn btn-default" onclick="exportExcel()"
                                    style="background-color: rgb(85,167,153);color: #ffffff;width: 150px;height: 34px;margin-left: 90px">导出</button>
                        </div>
                    </div>
                </form>
                <div class="fuye_search_result" style="margin-top: 20px">
                    <div class="p10" style="padding: 10px">
                        <table class="table table-bordered" data-locale="zh-CN" id="table-zsjh">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/bootstrap-table.min.js"></script>
<script src="${base}/js/bootstrap-table-zh-CN.min.js"></script>
<script src="${base}/js/zs/zsjh.js"></script>
<script>

</script>
</body>
</html>
