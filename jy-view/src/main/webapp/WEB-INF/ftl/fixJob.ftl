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
                <li><a href="/">首页</a></li>
                <li><a href="/enterpriseService/1">企业服务</a></li>
                <li class="active">职位信息</li>
            </ol>
        </div>
        <ul class="nav nav-tabs" id="nav-tabs-job">
            <li role="presentation" class="active"><a href="#" onclick="tabs(0)">职位信息</a></li>
            <li role="presentation"><a href="#" onclick="tabs(1)">关注学生</a></li>
            <li role="presentation"><a href="#" onclick="tabs(2)">报名学生</a></li>
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
        <div class="content-form" style="border-top:none;" id="jobInfo">
            <form class="form-horizontal" id="registerForm" method="" action="" target="rfFrame">
                <div class="form-group">
                    <label for="zwbt" class="col-sm-2 control-label">职位名称<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="zwbt" disabled name="zwbt" value="${(jobDetail.zwbt)!''}" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwPro" class="col-sm-2 control-label">所在省份<span class="red">*</span>：</label>
                    <div class="col-sm-2">
                        <select class="form-control" onchange="getCity()" disabled data-val="${(jobDetail.zwPro)!''}" name="zwPro" id="zwPro">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="zwCity" class="col-sm-1 control-label">所在市<span class="red">*</span>：</label>
                    <div class="col-sm-2">
                        <select class="form-control" onchange="getArea()" disabled data-val="${(jobDetail.zwCity)!''}" name="zwCity" id="zwCity">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="zwArea" class="col-sm-1 control-label">所在区<span class="red">*</span>：</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="zwArea" disabled id="zwArea" data-val="${(jobDetail.zwArea)!''}">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGzzn" class="col-sm-2 control-label">职能类别<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGzzn" disabled name="zwGzzn" data-val="${(jobDetail.zwGzzn)!''}">
                            <option value="">请选择</option>
                            <#if zwGzzn??>
                            <#list zwGzzn as obj>
                            <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGzxz" class="col-sm-2 control-label">工作性质<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGzxz" disabled name="zwGzxz" data-val="${(jobDetail.zwGzxz)!''}">
                            <option value="">请选择</option>
                            <#if zwGzxz??>
                            <#list zwGzxz as obj>
                            <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwXs" class="col-sm-2 control-label">薪水：</label>
                    <div class="col-sm-8">
                        <#if (jobDetail.zwXs)??>
                        <input type="number" class="form-control" disabled id="zwXs" name="zwXs" data-val="${(jobDetail.zwXs)!''}"placeholder="面议" value="${(jobDetail.zwXs)!''}" placeholder="" autocomplete="off">
                        <#else>
                        <input type="number" class="form-control" disabled id="zwXs" name="zwXs" data-val="" placeholder="面议" placeholder="" autocomplete="off">
                        </#if>
                        <span style="position: absolute;right: 25px;top: 7px;">元</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwLxyx" class="col-sm-2 control-label">邮箱<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <#if (jobDetail.zwLxyx)??>
                        <input type="text" class="form-control" disabled id="zwLxyx" name="zwLxyx" value="${(jobDetail.zwLxyx)!''}" placeholder="" autocomplete="off">
                        <#else>
                        <input type="text" class="form-control" disabled id="zwLxyx" name="zwLxyx" value="${(jobDetail.zwLxyx)!''}" placeholder="" autocomplete="off">
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwZprs" class="col-sm-2 control-label">招聘人数<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <#if (jobDetail.zwZprs)??>
                        <input type="number" class="form-control" disabled id="zwZprs" name="zwZprs" value="${(jobDetail.zwZprs)!''}" placeholder="" autocomplete="off">
                        <#else>
                        <input type="number" class="form-control" disabled id="zwZprs" name="zwZprs" value="" placeholder="" autocomplete="off">
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwNlyq" class="col-sm-2 control-label">年龄要求<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwNlyq" disabled name="zwNlyq" data-val="${(jobDetail.zwNlyq)!''}">
                            <option value="">请选择</option>
                            <#if zwNlyq??>
                            <#list zwNlyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwXlyq" class="col-sm-2 control-label">学历要求<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwXlyq" disabled name="zwXlyq" data-val="${(jobDetail.zwXlyq)!''}">
                            <option value="">请选择</option>
                            <#if zwXlyq??>
                            <#list zwXlyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGznx" class="col-sm-2 control-label">工作年限<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGznx" disabled name="zwGznx" data-val="${(jobDetail.zwGznx)!''}">
                            <option value="">请选择</option>
                            <#if zwGznx??>
                            <#list zwGznx as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwYyyq" class="col-sm-2 control-label">语言要求：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwYyyq" disabled name="zwYyyq" data-val="${(jobDetail.zwYyyq)!''}">
                            <option value="">不限</option>
                            <#if zwYyyq??>
                            <#list zwYyyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGwzz" class="col-sm-2 control-label">职位详情<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <#if (jobDetail.zwZprs)??>
                        <textarea class="form-control" disabled id="zwGwzz" name="zwGwzz" rows="10" data-val="${(jobDetail.zwGwzz)!''}"></textarea>
                        <#else>
                        <textarea class="form-control" disabled id="zwGwzz" name="zwGwzz" rows="10" data-val=""></textarea>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwSxsj" class="col-sm-2 control-label">失效时间：</label>
                    <div class="col-sm-3">
                        <#if (jobDetail.zwSxsj)??>
                        <input type="text" class="form-control" disabled id="zwSxsj" name="zwSxsj" value="${(jobDetail.zwSxsj)!''}" placeholder="" autocomplete="off">
                        <#else>
                        <input type="text" class="form-control" disabled id="zwSxsj" name="zwSxsj" value="" placeholder="" autocomplete="off">
                        </#if>
                    </div>
                </div>

                <#--<div class="form-group">
                    <div class="col-sm-12 text-center">
                        <button type="submit" class="btn btn-default btn-common green">提交</button>
                    </div>
                </div>-->
                <input type="hidden" id="sel1" value="${(jobDetail.zwSxsj)!''}">
            </form>
            <input type="hidden" id="zwOwid" value="${(jobDetail.owid)!''}">
            <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>
        </div>
    </div>

<#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/bootstrap-table.min.js" type="text/javascript"></script>
    <script src="${base}/js/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
    <#--<script src="${base}/js/bootstrap-table-export.min.js" type="text/javascript"></script>-->
    <#--<script src="${base}/js/tableExport.js" type="text/javascript"></script>-->
    <script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${base}/js/messages_zh.min.js" type="text/javascript"></script>
    <script src="${base}/js/laydate/laydate.js" type="text/javascript"></script>
    <script src="${base}/js/city1.js" type="text/javascript"></script>
    <script src="${base}/js/fixJob.js" type="text/javascript"></script>
</body>

</html>