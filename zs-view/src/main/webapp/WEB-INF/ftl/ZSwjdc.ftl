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

<body>
<#include "com/ZSheader.ftl">
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i> 当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a href="${base}/">首页</a></li>
                <li class="active">就业调查</li>
            </ol>
        </div>
        <div class="content">
            <div>
                <div>
                    <div class="search-bar">
                        <div class="search-label">搜索关键字：</div>
                        <div class="input-group search-input">
                            <input type="text" id="key" class="form-control" placeholder="输入内容">
                            <div class="input-group-btn">
                                <button onclick="initTable1()" type="button" class="btn btn-default green"><span class="glyphicon glyphicon-search"></span></button>

                            </div>
                        </div>
                    </div>
                    <div class="news-list">
                        <div class="e-table">
                            <table class="table table-hover" data-locale="zh-CN" id="table-zph" style="table-layout: fixed;
                          word-break:break-all; ">
                            </table>

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
        initTable1();
    });
    document.onkeydown = function(e){
        if(e.keyCode===13)
        {
            initTable1();
        }
    };
    function initTable1(){
        $('#table-zph').bootstrapTable('destroy');
        $('#table-zph').bootstrapTable({
            ajax:function(request) {
                ajax("zustcommon/bckjBizDcwj/dcwjList", {
                    "wzbh":1,
                    "wjmc":$("#key").val(),
                    "pageSize":$('#table-zph').bootstrapTable('getOptions').pageSize || pageSize,
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
                field: '',
                title: '序号',
                formatter:function(value,row,index){
                    return index+1;
                }
            }, {
                align : 'center',
                field: 'wjmc',
                title: '调查主题',
                formatter: operateFormatterZph
            }, {
                align : 'center',
                field: 'kssj',
                title: '调查开始时间',
                formatter:function(value,row,index){
                    var value=row.kssj.substring(0,10);
                    return value;
                }
            }, {
                align : 'center',
                field: 'zphJbdd',
                title: '调查结束时间',
                formatter:function(value,row,index){
                    var value=row.jssj.substring(0,10);
                    return value;
                }
            }, {
                align : 'center',
                field: 'mxdx',
                title: '面向对象',
                formatter:operateFormatterdx
            }, {
                align : 'center',
                field: 'sfyx',
                title: '状态',
                formatter:operateFormatterZt
            }] //列设置

        });

    }
    function operateFormatterdx(value, row, index) {
        if(row.mxdx==0){
            return '企业'
        }else if(row.mxdx==1){
            return '学生'
        }
    }
    function operateFormatterZph(value, row, index) {
        if(row.mxdx==0){////1学生 0企业
            if(row.sfdl==0){
                if(row.sfyx==1){
                    var c = '<a class="green-color" style="cursor: pointer;" href="${base}/inquiryDetail/'+row.owid+'">'+row.wjmc+'</a> ';
                }else{
                    var c = '<a class="green-color" style="color:#000;">'+row.wjmc+'</a> ';
                }
            }else if(row.sfdl==1){
                if(row.sfyx==1) {
                    var c = '<a class="green-color" style="cursor: pointer;" onclick="isopenUrl(\'inquiryDetail/' + row.owid + '\',0)">' + row.wjmc + '</a> ';
                }else{
                    var c = '<a class="green-color" style="color:#000;">' + row.wjmc + '</a> ';
                }
            }

        }else if(row.mxdx==1){
            if(row.sfdl==0){
                if(row.sfyx==1){
                    var c = '<a class="green-color" style="cursor: pointer;" href="${base}/inquiryDetail/'+row.owid+'">'+row.wjmc+'</a> ';
                }else{
                    var c = '<a class="green-color" style="color:#000;">'+row.wjmc+'</a> ';
                }
            }else if(row.sfdl==1){
                if(row.sfyx==1) {
                    var c = '<a class="green-color" style="cursor: pointer;" onclick="isopenUrl(\'inquiryDetail/' + row.owid + '\',1)">' + row.wjmc + '</a> ';
                }else{
                    var c = '<a class="green-color" style="color:#000;">' + row.wjmc + '</a> ';
                }
            }
        }

        return c;
    }

    function operateFormatterZt(value, row, index) {
        if(row.sfyx==1){
            var c = '<span style="color: green;">进行中</span>';
            return c;
        }else if(row.sfyx==0){
            var d = '<span style="color: red;">已过期</span> ';
            return d;
        }

    }

</script>
</body>

</html>