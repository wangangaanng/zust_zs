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

    .table .thead1 th {
        color: #6c9d9c;
        background-color: #f1f8f8;
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

            <div class="content-list" style="height: auto;">
                <form class="form-horizontal" style="padding-top: 20px;" id="registerForm" method="" action="" target="rfFrame">
                    <div class="form-group">
                        <label for="qymc" class="col-sm-2 control-label">公司名称*：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="qymc" disabled="disabled" value="${cInfo.qymc}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyTysh" class="col-sm-2 control-label">统一信用代码*：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="qyTysh" disabled="disabled" value="${cInfo.qyTysh}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyFrsfz" class="col-sm-2 control-label">法人身份证号*：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="qyFrsfz" disabled="disabled" value="${cInfo.qyFrsfz}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyProv" class="col-sm-2 control-label">所在省份*：</label>
                        <div class="col-sm-3">
                            <select class="form-control" onchange="getCity()" name="qyProv" id="qyProv" value="${cInfo.qyProv}">
                                <option value="">请选择</option>

                            </select>
                        </div>
                        <label for="qyCity" class="col-sm-2 control-label">所在市*：</label>
                        <div class="col-sm-3">
                            <select class="form-control" onchange="getArea()" name="qyCity" id="qyCity" value="${cInfo.qyCity}">
                                <option value="">请选择</option>

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyArea" class="col-sm-2 control-label">所在区*：</label>
                        <div class="col-sm-3">
                            <select class="form-control" name="qyArea" id="qyArea" value="${cInfo.qyArea}">
                                <option value="">请选择</option>

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qydz" class="col-sm-2 control-label">公司地址*：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="qydz" name="qydz" placeholder="" autocomplete="off" value="${cInfo.qydz}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyLxr" class="col-sm-2 control-label">联系人*：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="qyLxr" name="qyLxr" placeholder="" autocomplete="off" value="${cInfo.qyLxr}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyLxrdh" class="col-sm-2 control-label">手机*：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="qyLxrdh" name="qyLxrdh" placeholder="" autocomplete="off" value="${cInfo.qyLxrdh}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="qyYx" class="col-sm-2 control-label">邮箱*：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="qyYx" name="qyYx" placeholder="" autocomplete="off" value="${cInfo.qyYx}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyGsxz" class="col-sm-2 control-label">公司性质*：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="qyGsxz" name="qyGsxz" value="${cInfo.qyGsxz}">
                                <option value="">请选择</option>
                            <#list qyGsxz as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyHylb" class="col-sm-2 control-label">行业类别*：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="qyHylb" name="qyHylb" value="${cInfo.qyHylb}">
                                <option value="">请选择</option>
                            <#list qyHylb as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qyGsgm" class="col-sm-2 control-label">公司规模*：</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="qyGsgm" name="qyGsgm" value="${cInfo.qyGsgm}">
                                <option value="">请选择</option>
                            <#list qyGsgm as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="qyGsjs" class="col-sm-2 control-label">公司介绍*：</label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="qyGsjs" name="qyGsjs" rows="10" value="${cInfo.qyGsjs}"></textarea>
                        </div>
                    </div>

                    <input type="hidden" id="qyYyzzzp" name="qyYyzzzp" />

                    <div class="form-group">
                        <div class="col-sm-12 text-center">
                            <button type="submit" class="btn btn-default btn-common">修改</button>
                        </div>
                    </div>
                </form>

                <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>
            </div>
            <div class="content-list" style="display: none">
                <div class="search-bar">
                    <div class="input-group search-input">
                        <input type="text" class="form-control" placeholder="输入内容">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>

                        </div>
                    </div>
                </div>
                <div class="news-list">
                    <div class="e-table">
                        <table class="table table-hover" data-locale="zh-CN" id="table-request" style="table-layout: fixed;
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
    var provice=[];
    var city=[];
    var pindex=0;
    var cindex=0;
    var _cityData=[];
    cityData3.forEach(function(e) {
        provice.push(e.text)
        city.push(e.children)

        $("#qyProv").append("<option value='"+e.text+"'>"+e.text+"</option>")
    });
    $(document).ready(function () {

        // console.log(cInfo)
        $("#qyProv").val("浙江省")
        console.log($("#qydz").val())
        var provice=[];
        var city=[];
        var pindex=0;
        var cindex=0;
        var _cityData=[];

    })


    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        $(".content-list").eq($(this).index()).show();
    })

    $('#table-request').bootstrapTable({
        url: '/test', //请求后台的URL（*）
        method: 'get', //请求方式（*）
        toolbar: '#toolbar', //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: true, //是否启用排序
        sortOrder: "asc", //排序方式
        queryParams: 'queryParams', //传递参数（*）
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
        uniqueId: "id", //每一行的唯一标识，一般为主键列
        showToggle: false, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        // locale: 'zh-CN'
        theadClasses: "thead1",
        columns: [{
            field: 'name',
            title: '职位名称',
        }, {
            field: 'lb',
            title: '职能类别',
        }, {
            field: 'xz',
            title: '工作性质',
        }, {
            field: 'dd',
            title: '工作地点',
        }, {
            field: 'sj',
            title: '发布时间',
        }, {
            field: 'zt',
            title: '审核状态',
        }, {
            field: 'cz',
            title: '操作',
        }], //列设置
    });

    function rowStyle(row, index) {
        if (index == 0) {
            var style = {};
            style = {
                css: {
                    'color': '#6c9d9c',
                    'background-color': '#f1f8f8'
                }
            };
            return style;
        }

    }
</script>

</body>

</html>