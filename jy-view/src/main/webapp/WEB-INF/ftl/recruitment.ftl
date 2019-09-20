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
                            <tr><th>关键词</th><th>范围</th><th>举办城市</th><th>发布时间</th><th></th></tr>
                            <tr>
                                <td>
                                    <div class="input-group search-input">
                                        <input type="text" class="form-control" placeholder="输入内容">
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
            
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <select class="form-control">
                                        <option>1</option>
                                    </select> 
                                </td>
                                <td>
                                    <select class="form-control">
                                        <option>1</option>
                                    </select> 
                                </td>
                                <td>
                                    <select class="form-control">
                                        <option>1</option>
                                    </select> 
                                </td>
                                <td>
                                    <button class="search-button">搜索</button>
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
                        <#--<table>-->
                            <#--<tr><th>公司名称</th><th>学校</th><th>城市</th><th>举办地点</th><th>具体时间</th></tr>-->
                            <#--<tr><td>9届职来职往--11月29日宣讲会浙江华数广电网络股</td><td>浙江科技学院</td><td>杭州市</td><td>创业广场会议室</td><td>2018-11-29 13:30</td></tr>-->
                        <#--</table>-->
                    </div>


                    <#--<div class="search-group">-->
                        <#--<table>-->
                            <#--<tr><th><a class="active">职位名</a><a>公司名</a></th><th>行业类别</th><th>职能类别</th><th>选择地区</th><th></th></tr>-->
                            <#--<tr>-->
                                <#--<td>-->
                                    <#--<div class="input-group search-input">-->
                                        <#--<input type="text" class="form-control" placeholder="输入内容">-->
                                        <#--<div class="input-group-btn">-->
                                            <#--<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>-->
            <#---->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<select class="form-control">-->
                                        <#--<option>1</option>-->
                                    <#--</select> -->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<select class="form-control">-->
                                        <#--<option>1</option>-->
                                    <#--</select> -->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<select class="form-control">-->
                                        <#--<option>1</option>-->
                                    <#--</select> -->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<button class="search-button">搜索</button>-->
                                <#--</td>-->
                            <#--</tr>-->
                        <#--</table>-->
                    <#--</div>-->
                    <#--<div class="search-table">-->
                        <#--<div class="search-menu_left">-->
                            <#--<div class="search-menu_title">精细筛选<a>清空</a></div>-->
                            <#--<div>-->
                                <#--<dl>-->
                                    <#--<dt>薪资<i class="arrowdown"></i></dt>-->
                                    <#--<dd>-->
                                        <#--<ul>-->
                                            <#--<li><label class="radio"><input type="radio" /><span>面议</span></label></li>-->
                                            <#--<li><label class="radio"><input type="radio" /><span>1500以下</span></label></li>-->
                                        <#--</ul>-->
                                        <#--<p>更多</p>-->
                                    <#--</dd>-->
                                    <#--<dt>学历要求<i class="arrowdown"></i></dt>-->
                                    <#--<dd>-->
                                        <#--<ul>-->
                                            <#--<li><label class="radio"><input type="radio" /><span>不限</span></label></li>-->
                                        <#--</ul>-->
                                    <#--</dd>-->
                                    <#--<dt>发布时间<i class="arrowdown"></i></dt>-->
                                    <#--<dd>-->
                                        <#--<ul>-->
                                            <#--<li><label class="radio"><input type="radio" /><span>不限</span></label></li>-->
                                        <#--</ul>-->
                                    <#--</dd>-->
                                <#--</dl>-->
                            <#--</div>-->
                        <#--</div>-->
                        <#--<div class="search-menu_right">-->
                            <#--<table>-->
                                <#--<tr><th>职位名称</th><th>公司名称</th><th>工作地点</th><th>发布日期</th></tr>-->
                                <#--<tr><td>平面设计</td><td>9届职来职往--11月29日宣讲会浙江华数广电网络股</td><td>杭州市</td><td>2018-11-29</td></tr>-->
                            <#--</table>-->
                        <#--</div>-->
                    <#--</div>-->
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
    var param="";
    $(document).ready(function () {

        myJobList()
    })

    function myJobList() {
        param={
            "zwlx":"${zwlx?number!0}",
            "pageSize":pageSize,
            "pageNo":pageNo,
        }
        initTable1()//职来职往
    }
    function initTable1(){
        $('#table-zph').bootstrapTable('destroy');
        $('#table-zph').bootstrapTable({
            ajax:function(request) {
                ajax("zustjy/bckjBizJob/myJobList", param, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : data.bean.records,
                            total: data.bean.totalCount,
                            pageNumber:data.bean.currentPage,
                            pageSize:data.bean.pageSize
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
            height: 600, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "owid", //每一行的唯一标识，一般为主键列
            showToggle: false, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            theadClasses: "thead1",
            queryParamsType:"limit",//<tr><th>公司名称</th><th>学校</th><th>城市</th><th>举办地点</th><th>具体时间</th></tr>
            columns: [{
                align : 'center',
                field: 'zwbt',
                title: '公司名称',
            }, {
                align : 'center',
                field: 'zphCbf',
                title: '承办方'
            }, {
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
            }
//            , {
//                align : 'center',
//                events:'operateEvents',
//                field: 'owid',
//                title: '操作',
//                events: window.operateEvents,
//                formatter: operateFormatterZph
//            }
            ], //列设置

        });

    }
</script>
</body>

</html>