<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
    <link rel="stylesheet" href="${base}/css/bootstrap-table.min.css" />
    <link rel="stylesheet" href="${base}/js/laydate/theme/default/laydate.css" />
</head>
<style>
    .e-table {
        padding: 20px;
    }

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
                <li><a href="#">首页</a></li>
                <li><a href="#">个人中心</a></li>
                <li class="active">基本信息</li>
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
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 基本信息
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 职位信息发布
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 宣讲会申请
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 招聘会申请
                        </li>
                    </ul>
                </div>
            </div>


            <#--<div class="content-list" style="height: auto;display: none">-->
                <#--<form class="form-horizontal" style="padding-top: 20px;" id="registerForm" method="" action="" target="rfFrame">-->
                    <#--<div class="form-group">-->
                        <#--<label for="qymc" class="col-sm-2 control-label">公司名称*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<input type="text" class="form-control" id="qymc" disabled="disabled" value="${cInfo.qymc}" placeholder="" autocomplete="off">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyTysh" class="col-sm-2 control-label">统一信用代码*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<input type="text" class="form-control" id="qyTysh" disabled="disabled" value="${cInfo.qyTysh}" placeholder="" autocomplete="off">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyFrsfz" class="col-sm-2 control-label">法人身份证号*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<input type="text" class="form-control" id="qyFrsfz" disabled="disabled" value="${cInfo.qyFrsfz}" placeholder="" autocomplete="off">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyProv" class="col-sm-2 control-label">所在省份*：</label>-->
                        <#--<div class="col-sm-3">-->
                            <#--<select class="form-control" onchange="getCity()" name="qyProv" id="qyProv" data-val="${cInfo.qyProv}">-->
                                <#--<option value="">请选择</option>-->

                            <#--</select>-->
                        <#--</div>-->
                        <#--<label for="qyCity" class="col-sm-2 control-label">所在市*：</label>-->
                        <#--<div class="col-sm-3">-->
                            <#--<select class="form-control" onchange="getArea()" name="qyCity" id="qyCity" data-val="${cInfo.qyCity}">-->
                                <#--<option value="">请选择</option>-->

                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyArea" class="col-sm-2 control-label">所在区*：</label>-->
                        <#--<div class="col-sm-3">-->
                            <#--<select class="form-control" name="qyArea" id="qyArea" data-val="${cInfo.qyArea}">-->
                                <#--<option value="">请选择</option>-->

                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qydz" class="col-sm-2 control-label">公司地址*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<input type="text" class="form-control" id="qydz" name="qydz" placeholder="" autocomplete="off" value="${cInfo.qydz}">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyLxr" class="col-sm-2 control-label">联系人*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<input type="text" class="form-control" id="qyLxr" name="qyLxr" placeholder="" autocomplete="off" value="${cInfo.qyLxr}">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyLxrdh" class="col-sm-2 control-label">手机*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<input type="text" class="form-control" id="qyLxrdh" name="qyLxrdh" placeholder="" autocomplete="off" value="${cInfo.qyLxrdh}">-->
                        <#--</div>-->
                    <#--</div>-->

                    <#--<div class="form-group">-->
                        <#--<label for="qyYx" class="col-sm-2 control-label">邮箱*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<input type="text" class="form-control" id="qyYx" name="qyYx" placeholder="" autocomplete="off" value="${cInfo.qyYx}">-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyGsxz" class="col-sm-2 control-label">公司性质*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<select class="form-control" id="qyGsxz" name="qyGsxz" data-val="${cInfo.qyGsxz}">-->
                                <#--<option value="">请选择</option>-->
                            <#--<#list qyGsxz as obj>-->
                                <#--<option value="${obj.dicVal1}">${obj.dicVal2}</option>-->
                            <#--</#list>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyHylb" class="col-sm-2 control-label">行业类别*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<select class="form-control" id="qyHylb" name="qyHylb" data-val="${cInfo.qyHylb}">-->
                                <#--<option value="">请选择</option>-->
                            <#--<#list qyHylb as obj>-->
                                <#--<option value="${obj.dicVal1}">${obj.dicVal2}</option>-->
                            <#--</#list>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label for="qyGsgm" class="col-sm-2 control-label">公司规模*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<select class="form-control" id="qyGsgm" name="qyGsgm" data-val="${cInfo.qyGsgm}">-->
                                <#--<option value="">请选择</option>-->
                            <#--<#list qyGsgm as obj>-->
                                <#--<option value="${obj.dicVal1}">${obj.dicVal2}</option>-->
                            <#--</#list>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->

                    <#--<div class="form-group">-->
                        <#--<label for="qyGsjs" class="col-sm-2 control-label">公司介绍*：</label>-->
                        <#--<div class="col-sm-8">-->
                            <#--<textarea class="form-control" id="qyGsjs" name="qyGsjs" rows="10" data-val="${cInfo.qyGsjs}"></textarea>-->
                        <#--</div>-->
                    <#--</div>-->

                    <#--<input type="hidden" id="qyYyzzzp" name="qyYyzzzp" />-->

                    <#--<div class="form-group">-->
                        <#--<div class="col-sm-12 text-center">-->
                            <#--<button type="submit" onclick="fixCompany()" class="btn btn-default btn-common">修改</button>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</form>-->

                <#--<iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>-->
            <#--</div>-->
            <div class="content-list" style="">

            </div>
            <div class="content-list" style="display: none">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" id="zwbt-zw" class="form-control" placeholder="输入职位进行查询">
                        <div class="input-group-btn">
                            <button type="button" onclick="searchZw()" class="btn btn-default green"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                    <button class="btn pull-right green" onclick='window.location.href="newJob"'>新增</button>
                </div>
                <div class="news-list">
                    <div class="e-table">
                        <table class="table table-hover" data-locale="zh-CN" id="table-job" style="table-layout: fixed;
                          word-break:break-all; word-wrap:break-all;">
                        </table>

                    </div>
                </div>
            </div>
            <div class="content-list" style="display: none">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" id="zwbt-xjh" class="form-control" placeholder="输入宣讲会进行查询">
                        <div class="input-group-btn">
                            <button type="button" onclick="searchXjh()" class="btn btn-default green"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                    <button class="btn pull-right green" onclick='applyXjh()'>申请</button>
                </div>
                <div class="news-list">
                    <div class="e-table">
                        <table class="table table-hover" data-locale="zh-CN" id="table-xjh" style="table-layout: fixed;
                          word-break:break-all; word-wrap:break-all;">
                        </table>

                    </div>
                </div>
            </div>
            <div class="content-list" style="display: none">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" id="zwbt-zph" class="form-control" placeholder="输入招聘会进行查询">
                        <div class="input-group-btn">
                            <button type="button" onclick="searchZph()" class="btn btn-default green"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                    <button class="btn pull-right green" onclick='window.location.href="jobFair/0"'>申请</button>
                </div>
                <div class="news-list">
                    <div class="e-table">
                        <table class="table table-hover" data-locale="zh-CN" id="table-zph" style="table-layout: fixed;
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
<script src="${base}/js/enterpriseService.js" type="text/javascript"></script>

</body>

</html>