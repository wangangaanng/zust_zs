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

<#--<#include "com/header.ftl">-->
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
            <div class="content-list">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" class="form-control" placeholder="输入招聘会进行查询">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </div>
                    <button class="btn pull-right green">新增</button>
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
    })

    function myJobList1() {
        // var jsonObj ={
        //     // "qyxxRefOwid":getCookie("qyOwid"),
        //     "zwlx":3,
        //     "pageSize":10,
        //     "pageNo":1
        // }
        // ajax("zustjy/bckjBizJob/myJobList", jsonObj, function (data) {
        //     if(data.backCode==0){
        //
        //     }
        // })
        initTable1()
    }
    function initTable1(){
        var jsonObj ={
            "zwlx":3,
            "pageSize":pageSize,
            "pageNo":pageNo
        }
        $('#table-zph').bootstrapTable('destroy');
        $('#table-zph').bootstrapTable({
            ajax:function(request) {
                ajax("zustjy/bckjBizJob/myJobList", jsonObj, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : data.bean.records,
                            total: data.bean.totalCount,
                            pageNumber:data.bean.currentPage,
                            pageSize:data.bean.pageSize
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
                    "total":res.total,
                    "pageNumber":res.pageNumber,
                    "pageSize":res.pageSize
                }
            },
            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: true, //是否启用排序
            sortOrder: "asc", //排序方式
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: pageNo, //初始化加载第一页，默认第一页
            pageSize: pageSize, //每页的记录行数（*）
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
                formatter:function(value,row,index){
                    var value=row.zphKsrq.substring(0,10);
                    return value;
                }
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

    var pageNo=1;
    var pageSize=10
    function initTable(){
        var jsonObj ={
            "qyxxRefOwid":getCookie("qyOwid"),
            "zwlx":0,
            "pageSize":pageSize,
            "pageNo":pageNo
        }
        $('#table-job').bootstrapTable('destroy');
        $('#table-job').bootstrapTable({
            ajax:function(request) {
                ajax("zustjy/bckjBizJob/myJobList", jsonObj, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : data.bean.records,
                            total: data.bean.totalCount,
                            pageNumber:data.bean.currentPage,
                            pageSize:data.bean.pageSize
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
                    "total":res.total,
                    "pageNumber":res.pageNumber,
                    "pageSize":res.pageSize
                }
            },
            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: true, //是否启用排序
            sortOrder: "asc", //排序方式
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: pageNo, //初始化加载第一页，默认第一页
            pageSize: pageSize, //每页的记录行数（*）
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


    var txt={
        "backCode" : 0,
        "bean" : {
            "currentIndex" : 1,
            "totalCount" : 14,
            "currentPage" : 1,
            "totalPage" : 2,
            "pageSize" : 10,
            "records" : [ {
                "owid" : "059e0c4e964a45ba8beb1ce95814fb4d",
                "createtime" : "2019-09-18 10:31:35",
                "lastupdate" : "2019-09-18 10:31:35",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:35",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "1d57ebf5e8ef42beb697005e69647f61",
                "createtime" : "2019-09-18 10:31:35",
                "lastupdate" : "2019-09-18 10:31:35",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:35",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "9f840f07a68a4f1ea24f09afa7a67371",
                "createtime" : "2019-09-18 10:31:35",
                "lastupdate" : "2019-09-18 10:31:35",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:35",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "41b50c70dd9143d8b39a12d079413a0f",
                "createtime" : "2019-09-18 10:31:34",
                "lastupdate" : "2019-09-18 10:31:34",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:34",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "869e2b7f3aa04a07aa9420469ed8fda1",
                "createtime" : "2019-09-18 10:31:34",
                "lastupdate" : "2019-09-18 10:31:34",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:34",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "c49556d45f9f46279f618a564370c734",
                "createtime" : "2019-09-18 10:31:34",
                "lastupdate" : "2019-09-18 10:31:34",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:34",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "13437e9d36604e2895aadcfd2753f727",
                "createtime" : "2019-09-18 10:31:33",
                "lastupdate" : "2019-09-18 10:31:33",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:33",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "2ff06df6a731466c93d1f6363022c3bb",
                "createtime" : "2019-09-18 10:31:33",
                "lastupdate" : "2019-09-18 10:31:33",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:33",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "09327a19d3c943119a2f9e355fc0a110",
                "createtime" : "2019-09-18 10:31:32",
                "lastupdate" : "2019-09-18 10:31:32",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:32",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-09 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            }, {
                "owid" : "395dc8de9a004ab39b06cf43fd0bf832",
                "createtime" : "2019-09-18 10:31:32",
                "lastupdate" : "2019-09-18 10:31:32",
                "delflg" : 0,
                "state" : 2,
                "ver" : 1,
                "vertime" : "2019-09-18 10:31:32",
                "qyxxRefOwid" : "e64343778f514cef98a43314f0407854",
                "zwbt" : "标题1",
                "zwgjz" : "关键字",
                "zwlx" : 3,
                "lmlj" : "1",
                "lmbh" : 1,
                "zwPro" : "浙江省",
                "zwCity" : "杭州市",
                "zwArea" : "西湖区",
                "zwGzzn" : "A001",
                "zwGzxz" : "B001",
                "zwXs" : 500.00,
                "zwLxyx" : "51073844@qq.com",
                "zwZprs" : 2,
                "zwNlyq" : "C001",
                "zwXlyq" : "D001",
                "zwYyyq" : "E001",
                "zwGznx" : "F001",
                "zwGwzz" : "负责打扫",
                "zwSxsj" : "2018-08-08 00:00:00",
                "zwMxxy" : "学院",
                "zwMxzy" : "专业",
                "zphJbf" : "举办方",
                "zphCbf" : "成安防",
                "zphJbdd" : "A区域",
                "zphKsrq" : "2019-08-10 00:00:00",
                "zphJtsj" : "三个小时",
                "zphSfbm" : 0,
                "zphBmjzsj" : "2018-08-09 00:00:00",
                "zphSfqd" : 0,
                "number" : 0,
                "zwGzznStr" : "销售/零售/客户服务类",
                "zwGzxzStr" : "不限",
                "zwNlyqStr" : "18~25",
                "zwXlyqStr" : "大专",
                "zwYyyqStr" : "英语",
                "zwGznxStr" : "不限"
            } ]
        }
    }
</script>

</body>

</html>