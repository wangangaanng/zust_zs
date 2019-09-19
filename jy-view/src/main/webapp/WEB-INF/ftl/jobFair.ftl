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
                <li class="active">招聘会申请</li>
            </ol>
        </div>
        <div class="content">
            <div class="jf-content">
                <div class="jf-steps">
                    <div class="jf-items">
                        <div class="jf-item jf-active">1、招聘会列表

                            <div class="jf-box"></div>
                            <div class="jf-box1"></div>
                            <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item ">2、预定展位
                            <div class="jf-box"></div>
                            <div class="jf-box1"></div>
                            <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item ">3、完成</div>
                    </div>
                </div>
                <div>
                    <div class="news-list">
                        <div class="e-table">
                            <table class="table table-hover" data-locale="zh-CN" id="table-zph" style="table-layout: fixed;
                          word-break:break-all; word-wrap:break-all;">
                            </table>

                        </div>
                    </div>
                </div>
                <div class="jf-result" style="display: none">
                    <div class="jf-tips">
                        <div class="jf-tip-icon">
                            <i></i>
                        </div>
                        <div class="jf-tip-text">
                            <div>认证资料提交成功，您的注册认证一般在24小时内审核完成，</div>
                            <div>将以邮件的方式通知审核结果</div>
                            <div>注册遇到问题请咨询浙江科技学院学生处就业指导中心:0571-85121710</div>
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


    function operateFormatterZph(value, row, index) {
        if(row.state==2){
            var c = '<a class="green-color order" href="#">预定展位</a> ';
            return c;
        }else if(row.state==6){
            var d = '<span style="color: red;" href="#">已结束</span> ';
            return d;
        }else{
            return '';
        }

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