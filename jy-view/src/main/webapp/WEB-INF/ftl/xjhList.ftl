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
                <li class="active">宣讲会申请</li>
            </ol>
        </div>
        <div class="content">
            <div class="jf-content">
                <div>
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

    $(document).ready(function () {
            myJobList1()
    })

    var layer1;
    function order() {
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
            "bmdx":0,
            "qyxxRefOwid":getCookie("qyOwid"),
            "lxr":$("#lxr").val().trim(),
            "lxdh":$("#lxdh").val().trim()
        }
        ajax("zustjy/bckjBizJybm/applyJob", jsonObj, function (data) {
            if(data.backCode==0){
                layer.close(layer1)
                layer1=null;
            <#--window.location.href="${base}/"+url-->
                window.location.href="${base}/jobFair/2"
            }else{
                walert(data.errorMess)
            }
        })

    }
    function myJobList1() {

        initTable1()
    }
    function initTable1(){
        $('#table-zph').bootstrapTable('destroy');
        $('#table-zph').bootstrapTable({
            ajax:function(request) {
                ajax("zustjy/bckjBizJob/myJobList", {
                    "zwlx":3,
                    "pageSize":$('#table-zph').bootstrapTable('getOptions').pageSize || 10,
                    "pageNo":$('#table-zph').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : data.bean.records,
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
            if(row.zphSfbm==0){
                return '';
            }else if(row.zphSfbm==1){
                var c = '<a class="green-color order" href="#">预定展位</a> ';
                return c;
            }

        }else if(row.state==6){
            var d = '<span style="color: red;" href="#">已结束</span> ';
            return d;
        }else{
            return '';
        }

    }

    window.operateEvents = {
        'click .order': function (e, value, row, index) {
            window.location.href="${base}/jobFair/1/"+row.owid
        },
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