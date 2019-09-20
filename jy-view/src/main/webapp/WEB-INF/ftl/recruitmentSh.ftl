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

<body>
<#include "com/header.ftl">
    <div class="main">
        <div class="container">
        <#include "com/route.ftl">
            <div class="content">
                <#include "com/subMenu.ftl">
                <div class="content-list">
                    <div class="search-group">
                        <table>
                            <tr><th>关键词</th><th>所在城市</th><th>开始时间</th><th></th></tr>
                            <tr>
                                <td>
                                    <div class="input-group search-input">
                                        <input type="text" class="form-control" id="zwgjz" placeholder="输入内容">
                                        <div class="input-group-btn">
                                            <button onclick="initTable()" type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
            
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <input class="form-control" type="text" id="zwCity">
                                </td>
                                <td>
                                    <input class="form-control" type="date" id="zphKsrq1" >
                                </td>
                                <td>
                                    <input class="form-control" type="date" id="zphKsrq2" >
                                </td>
                                <td>
                                    <button class="search-button" onclick="initTable()">搜索</button>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="search-table">
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
<script>
    var pageNo=1;
    var pageSize=10;
    var zwlx="${zwlx?number!0}";
    $(document).ready(function () {
        myJobList()
    })

    function myJobList() {
        initTable()//职来职往
    }
    document.onkeydown = function(e){
        if(e.keyCode==13)
        {
            initTable();
        }
    }
    function initTable(){
        $('#table-zph').bootstrapTable('destroy');
        $('#table-zph').bootstrapTable({
            ajax:function(request) {
                ajax("zustjy/bckjBizJob/myJobList", {
                    "zwgjz":$("#zwgjz").val(),//关键字
//                    "zwPro":$("#zwPro").val(),//省
                    "zwCity":$("#zwCity").val(),//市
                    "startTime":$("#zphKsrq1").val(),//招聘会开始时间
                    "endTime":$("#zphKsrq2").val(),//结束时间
                    "zwlx":zwlx,
                    "pageSize":$('#table-zph').bootstrapTable('getOptions').pageSize || 10,
                    "pageNo":$('#table-zph').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : convertStr(data.bean.records,[]),
                            total: data.bean.totalCount,
                            pageNumber:data.bean.currentPage,
                            pageSize:data.bean.pageSize
                        });
                    }else{
                        request.success({
                            row : [],
                        });
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
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: pageSize, //每页的记录行数（*）
            pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
            smartDisplay: false,
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false, //是否显示所有的列
            showRefresh: false, //是否显示刷新按钮
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            height: 600, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
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
            },{
                align : 'center',
                field: 'zwCity',
                title: '城市',
            }, {
                align : 'center',
                field: 'zphJbdd',
                title: '举办地点',
            }, {
                field: 'zphKsrq',
                title: '具体时间',
                align : 'center',
                formatter:function(value,row,index){
                    var value=row.zphKsrq.substring(0,10);
                    return value;
                }
            }], //列设置

        });

    }
</script>
</body>

</html>