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
</head>
<style>
    .step2-title{
        text-align: center;
        font-size: 24px;
        color: #292929;
        margin-top: 30px;
    }
    .zph-d{
        margin-top: 15px;
    }
    .zph-d .row{
        padding: 5px 0;
    }
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
                <li><a href="${base}/stuCenter/2">个人中心</a></li>
                <li class="active">我要报名</li>
            </ol>
        </div>
        <div class="content">
            <div class="jf-content">
                <div>
                    <div class="search-bar">
                        <div class="input-group search-input">
                            <input type="text" id="zwbt-zph" onkeydown="keyLogin()" class="form-control" placeholder="输入名称进行查询">
                            <div class="input-group-btn">
                                <button type="button" onclick="searchXjh()" class="btn btn-default green"><span class="glyphicon glyphicon-search"></span></button>
                            </div>
                        </div>
                        <select style="width: 200px;float: right;" onchange="searchXjh()" class="form-control" id="zwlx" name="zwlx">
                            <option value="">请选择职位类型</option>
                            <option value="3">职来职往</option>
                            <option value="4">宣讲会</option>
                        </select>
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

</div>
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<script>

    var pageNo=1;
    var pageSize=10;
    $(document).ready(function () {

        myJobList()
    })

    function myJobList() {

        initTable1()
    }


    function keyLogin(){
        if (event.keyCode==13){
            searchXjh()
        }
    }

    function searchXjh() {
        var key=$("#zwbt-zph").val().trim()
        if(testSql(key,$("#zwbt-zph"))){
            if(!isTimeOut()) {
                $("#table-zph").bootstrapTable('refresh', {pageNumber: 1});
            }
        }
    }

    function initTable1(){
        $('#table-zph').bootstrapTable('destroy');
        $('#table-zph').bootstrapTable({
            ajax:function(request) {
                ajax("zustjy/bckjBizJob/myJobList", {
                    "zwlx": $("#zwlx").val(),
                    "zwbt": $("#zwbt-zph").val().trim(),
                    "zphSfbm":1,
                    "yhRefOwid":getCookie("yhOwid"),
                    "pageSize":$('#table-zph').bootstrapTable('getOptions').pageSize || 10,
                    "pageNo":$('#table-zph').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : convertStr(data.bean.records,[]),
                            total: data.bean.totalCount
                        });

                    }
                })
            },
            responseHandler:function(res){
                $('#table-zph').bootstrapTable('load', res.row);
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
            pageSize: 10, //每页的记录行数（*）
            pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
            smartDisplay: false,
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false, //是否显示所有的列
            showRefresh: false, //是否显示刷新按钮
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            // height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "owid", //每一行的唯一标识，一般为主键列
            showToggle: false, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            theadClasses: "thead1",
            queryParamsType:"limit",
            columns: [{
                align : 'center',
                field: 'zwbt',
                title: '名称',
                events: window.operateEvents,
                formatter: operateFormatterZph2
            }, {
                align : 'center',
                field: 'zwlx',
                title: '类型',
                formatter:function(value,row,index){
                    var value=''
                    if(row.zwlx==0){
                        value='职位'
                    }else if(row.zwlx==1){
                        value='社会招聘会'
                    }else if(row.zwlx==2){
                        value='企业招聘公告'
                    }else if(row.zwlx==3){
                        value='职来职往'
                    }else if(row.zwlx==4){
                        value='宣讲会'
                    }
                    return value;
                }
            }, {
                align : 'center',
                field: 'zphJbdd',
                title: '举办地址',
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
                field: 'owid',
                title: '状态',
                events: window.operateEvents,
                formatter: operateFormatterZph
            }], //列设置

        });

    }


    function operateFormatterZph(value, row, index) {
        var str='';

        if(row.state==2){
            if(row.sfbm==1){
                str= '<span style="color: #888;">未报名</span>';
            }else if(row.sfbm==2){
                str= '<span style="color: #008784;">已报名</span>';
            }

        }else{
            str= '';
        }

        if(row.zphBmjzsj && compareToday(row.zphBmjzsj)){
            str= '<span style="color: #ccc;">已截止报名</span> ';
        }
        if(row.zphKsrq && compareToday(row.zphKsrq)){
            str= '<span style="color:red;">已结束</span> ';
        }

        return str;
    }

    function operateFormatterZph2(value, row, index) {
        var c = '<a class="green-color zphxq" href="#">'+row.zwbt+'</a> ';
        return c;
    }

    window.operateEvents = {
        'click .zphxq': function (e, value, row, index) {
            if(!isTimeOut()) {
                window.open("${base}/positionDetail/" + row.owid)
            }
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