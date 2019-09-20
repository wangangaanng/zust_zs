<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
    <link rel="stylesheet" href="${base}/css/bootstrap-table.min.css" />
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
                <li><a href="#">企业服务</a></li>
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
            <#--<div class="content-list">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" class="form-control" placeholder="输入职位进行查询">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                    <button class="btn pull-right green">新增</button>
                </div>
                <div class="news-list">
                    <div class="e-table">
                        <table class="table table-hover" data-locale="zh-CN" id="table-job" style="table-layout: fixed;
                          word-break:break-all; word-wrap:break-all;">
                        </table>

                    </div>
                </div>
            </div>-->
            <div class="content-list" style="display: none">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" class="form-control" placeholder="输入宣讲会进行查询">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
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
            <div class="content-list">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" class="form-control" placeholder="输入招聘会进行查询">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
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
<script>

    var layer1;
    function applyXjh(){
        layer1=layer.open({
            type: 1,
            title:'联系人信息',
            skin: 'layui-layer-rim', //加上边框
            area: ['420px', '240px'], //宽高
            content: '<div class="lxr-modal"><div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="lxr" class="col-sm-3 col-sm-offset-1 control-label text-right" style="line-height: 34px;">联系人：</label>\n' +
            '                                <div class="col-sm-6">\n' +
            '                                    <input type="text" class="form-control" id="lxr" name="lxr" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="lxdh" class="col-sm-3 col-sm-offset-1 control-label text-right" style="line-height: 34px;">联系电话：</label>\n' +
            '                                <div class="col-sm-6">\n' +
            '                                    <input type="text" class="form-control" id="lxdh" name="lxdh" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div><div class="row btn-yd">\n' +
            '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
            '                                <button class="btn green" onclick="confirmQd()">确定</button>\n' +
            '                            </div>\n' +
            '                        </div></div>'
        });
    }

    function confirmQd() {
        if(!$("#lxr").val().trim()){
            walert("请填写联系人")
            return
        }else if(!$("#lxdh").val().trim()){
            walert("请填写联系人电话")
            return
        }
        var jsonObj ={
            "jobRefOwid":$("#zphOwid").val(),
            "bmlx":0,
            "bmdx":1,
            "qyxxRefOwid":getCookie("qyOwid"),
            "lxr":$("#lxr").val().trim(),
            "lxdh":$("#lxdh").val().trim()
        }
        ajax("zustjy/bckjBizJybm/applyJob", jsonObj, function (data) {
            if(data.backCode==0){
                layer.close(layer1)
                layer1=null;

            }else{
                walert(data.errorMess)
            }
        })

    }
    $(document).ready(function () {
        var provice=[];
        var city=[];
        var pindex=0;
        var cindex=0;
        var _cityData=[];
        cityData3.forEach(function(e) {
            provice.push(e.text)
            city.push(e.children)
            if($("#qyProv").attr("data-val")==e.text) {
                $("#qyProv").append("<option value='" + e.text + "' selected>" + e.text + "</option>")
            }else{
                $("#qyProv").append("<option value='" + e.text + "' >" + e.text + "</option>")
            }
        });

        pindex=parseInt($("#qyProv option:selected").index())-1;
        if(pindex>-1){
            _cityData=city[pindex]
            _cityData.forEach(function(e) {
                if($("#qyCity").attr("data-val")==e.text){
                    $("#qyCity").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
                }else{
                    $("#qyCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
                }

            });

        }
        cindex=parseInt($("#qyCity option:selected").index())-1;
        if(cindex>-1){
            var _areaData=_cityData[cindex].children
            _areaData.forEach(function(e) {
                if($("#qyArea").attr("data-val")==e.text){
                    $("#qyArea").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
                }else{
                    $("#qyArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
                }
            });

        }

        $("#qyGsxz").val($("#qyGsxz").attr("data-val"))
        $("#qyHylb").val($("#qyHylb").attr("data-val"))
        $("#qyGsgm").val($("#qyGsgm").attr("data-val"))
        $("#qyGsjs").val($("#qyGsjs").attr("data-val"))


        // myJobList()
        myJobList1()
        // myJobList2()
    })

    function myJobList2() {
        $('#table-xjh').bootstrapTable('destroy');
        $('#table-xjh').bootstrapTable({
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: 10, //每页的记录行数（*）
            ajax:function(request) {
                ajax("zustjy/bckjBizJybm/myBmList", {
                    "qyxxRefOwid":getCookie("qyOwid"),
                    "bmlx": 0,
                    "bmdx": 1,
                    "pageSize":$('#table-xjh').bootstrapTable('getOptions').pageSize || 10,
                    "pageNo":$('#table-xjh').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : data.bean.records ||[],
                            total: data.bean.totalCount,
                            // pageNumber:data.bean.currentPage,
                            // pageSize:data.bean.pageSize
                        });

                        // $('#table-job').bootstrapTable('load', data.bean.records);
                    }
                })
            },

            responseHandler:function(res){
                console.log("res",res)
                // return res
                $('#table-xjh').bootstrapTable('load', res.row);
                return {
                    "total":res.total
                    // ,
                    // "pageNumber":res.pageNumber,
                    // "pageSize":res.pageSize
                }
            },
            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: true, //是否启用排序
            sortOrder: "asc", //排序方式
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
            smartDisplay: false,
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false, //是否显示所有的列
            showRefresh: false, //是否显示刷新按钮
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "owid", //每一行的唯一标识，一般为主键列
            showToggle: false, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            theadClasses: "thead1",
            queryParamsType:"limit",
            // queryParams: jsonObj,
            columns: [{
                align : 'center',
                field: 'zwbt',
                title: '标题',
            }, {
                field: 'zphKsrq',
                title: '举办日期',
                align : 'center',
                formatter:function(value,row,index){
                    var value=row.zphKsrq.substring(0,10);
                    return value;
                }
            }, {
                align : 'center',
                field: 'zphJbdd',
                title: '审核状态',
            },   {
                align : 'center',
                events:'operateEvents',
                field: 'owid',
                title: '操作',
                events: window.operateEvents,
                formatter: operateFormatterZph
            }], //列设置

        });
    }

    function myJobList1() {
        $('#table-zph').bootstrapTable('destroy');
        $('#table-zph').bootstrapTable({
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: 10, //每页的记录行数（*）
            ajax:function(request) {

                ajax("zustjy/bckjBizJybm/myBmList", {
                    "qyxxRefOwid":getCookie("qyOwid"),
                    "bmlx": 0,
                    "bmdx": 0,
                    "pageSize":$('#table-zph').bootstrapTable('getOptions').pageSize || 10,
                    "pageNo":$('#table-zph').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : data.bean.records,
                            total: data.bean.totalCount,
                            // pageNumber:data.bean.currentPage,
                            // pageSize:data.bean.pageSize
                        });

                        // $('#table-job').bootstrapTable('load', data.bean.records);
                    }
                })
            },

            responseHandler:function(res){
                console.log("res",res)
                // return res
                $('#table-zph').bootstrapTable('load', res.row);
                return {
                    "total":res.total
                    // ,
                    // "pageNumber":res.pageNumber,
                    // "pageSize":res.pageSize
                }
            },
            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: true, //是否启用排序
            sortOrder: "asc", //排序方式
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
            smartDisplay: false,
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false, //是否显示所有的列
            showRefresh: false, //是否显示刷新按钮
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "owid", //每一行的唯一标识，一般为主键列
            showToggle: false, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            theadClasses: "thead1",
            queryParamsType:"limit",
            // queryParams: jsonObj,
            columns: [{
                align : 'center',
                field: 'zwbt',
                title: '招聘会名称',
            }, {
                align : 'center',
                field: 'zwArea',
                title: '城市'
            }, {
                align : 'center',
                field: 'zphJbdd',
                title: '举办地址',
            }, {
                align : 'center',
                field: 'zwlx',
                title: '招聘会类型',
                formatter:function(value,row,index){
                    return "企业招聘会";
                }
            }, {
                field: 'zphKsrq',
                title: '举办时间',
                align : 'center',
                // formatter:function(value,row,index){
                //     var value=row.zphKsrq.substring(0,10);
                //     return value;
                // }
            }, {
                align : 'center',
                events:'operateEvents',
                field: 'owid',
                title: '操作',
                events: window.operateEvents,
                formatter: operateFormatterZph
            }], //列设置

        });

    }
    function fixCompany() {

        var jsonObj = $("#registerForm").serializeObject()
        jsonObj.owid=getCookie("qyOwid")
        console.log(jsonObj)
        ajax("zustjy/bckjBizQyxx/fixCompany", jsonObj, function (data) {
            if(data.backCode==0){

            }
        })
    }

    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        $(".content-list").eq($(this).index()).show();
    })
    
    function myJobList() {
        // var jsonObj ={
        //     "qyxxRefOwid":getCookie("qyOwid"),
        //     "zwlx":0,
        //     "pageSize":10,
        //     "pageNo":1
        // }
        // ajax("zustjy/bckjBizJob/myJobList", jsonObj, function (data) {
        //     if(data.backCode==0){
        //
        //     }
        // })
        initTable()
    }

    function initTable(){
        $('#table-job').bootstrapTable('destroy');
        $('#table-job').bootstrapTable({
            ajax:function(request) {
                ajax("zustjy/bckjBizJob/myJobList", {
                    "qyxxRefOwid":getCookie("qyOwid"),
                    "zwlx":0,
                    "pageSize":$('#table-job').bootstrapTable('getOptions').pageSize || 2,
                    "pageNo":$('#table-job').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : data.bean.records,
                            total: data.bean.totalCount
                        });

                        // $('#table-job').bootstrapTable('load', data.bean.records);
                    }
                })
            },
            responseHandler:function(res){
                console.log("res",res)
                // return res
                $('#table-job').bootstrapTable('load', res.row);
                return {
                    "total":res.total
                }
            },
            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: true, //是否启用排序
            sortOrder: "asc", //排序方式
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: 2, //每页的记录行数（*）
            pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
            smartDisplay: false,
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false, //是否显示所有的列
            showRefresh: false, //是否显示刷新按钮
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "owid", //每一行的唯一标识，一般为主键列
            showToggle: false, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            theadClasses: "thead1",
            queryParamsType:"limit",
            columns: [{
                align : 'center',
                field: 'zwbt',
                title: '职位名称',
            }, {
                align : 'center',
                field: 'zwGzznStr',
                title: '职能类别'
            }, {
                align : 'center',
                field: 'zwGzxzStr',
                title: '工作性质',
            }, {
                align : 'center',
                field: 'zwArea',
                title: '工作地点',
            }, {
                field: 'createtime',
                title: '发布时间',
                align : 'center',
                formatter:function(value,row,index){
                    var value=row.createtime.substring(0,10);
                    return value;
                }
            }, {
                align : 'center',
                field: 'state',
                title: '审核状态',
            }, {
                align : 'center',
                events:'operateEvents',
                field: 'owid',
                title: '操作',
                events: window.operateEvents,
                formatter: operateFormatterZw
            }], //列设置

        });

    }

    function operateFormatterZw(value, row, index) {
        var c = '<a class="green-color detail" href="#"  οnclick="info(\''
                + row.owid
                + '\')">查看</a> ';
        var d = '<a class="green-color remove" href="#"  οnclick="info(\''
                + row.owid
                + '\')">删除</a> ';

        return c + d;
    }

    function operateFormatterZph(value, row, index) {
        var c = '<a class="green-color detail" href="#"  οnclick="info(\''
                + row.owid
                + '\')">查看</a> ';

        return c;
    }

    window.operateEvents = {
        'click .detail': function (e, value, row, index) {
            alert(row.owid)
        },
        'click .remove': function (e, value, row, index) {
            layer.confirm('确定删除该条记录？', {
                btn: ['确定'] //按钮
            }, function(){
                var jsonObj={
                    "owid":row.owid,
                }
                ajax("zustjy/bckjBizJob/deleteOneJob", jsonObj, function (data) {
                    if(data.backCode==0){
                        $('#table-job').bootstrapTable('removeByUniqueId', row.owid);
                        layer.msg('删除成功', {icon: 1});
                    }
                })

            });
        }
    }

</script>

</body>

</html>