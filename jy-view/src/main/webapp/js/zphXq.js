
$(document).ready(function () {


    getOne()
})

function getOne() {
    var jsonObj = {
        "owid":$("#owid").val()
    }
    ajax("zustjy/bckjBizJybm/getOne", jsonObj, function (data) {
        if (data.backCode == 0) {


        }
    })
}

function showStudentInfo2() {
    if(!isTimeOut()) {
        $('#table-job').bootstrapTable('destroy');
        $('#table-job').bootstrapTable({
            ajax: function (request) {
                ajax("zustjy/bckjBizQyxx/showStudentInfo", {
                    "type":1,
                    "jobOwid": $("#jobRefOwid").val(),
                    "owid": getCookie("yhOwid"),
                    "pageSize": $('#table-job').bootstrapTable('getOptions').pageSize || 10,
                    "pageNo": $('#table-job').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if (data.backCode == 0) {
                        request.success({
                            row: data.bean.records || [],
                            total: data.bean.totalCount
                        });

                    }
                })
            },
            responseHandler: function (res) {
                $('#table-job').bootstrapTable('load', res.row);
                return {
                    "total": res.total
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
            queryParamsType: "limit",
            columns: [{
                align: 'center',
                field: 'lxr',
                title: '联系人',
            }, {
                align: 'center',
                field: 'bmsj',
                title: '报名时间',
                formatter: function (value, row, index) {
                    var value = ""
                    if (row.bmsj) {
                        value = row.bmsj.substring(0, 10);
                    } else {
                        value = "-"
                    }
                    return value;
                }
            }, {
                align: 'center',
                field: 'lxdh',
                title: '联系电话',
            }], //列设置
            // exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据
            // showExport: true,  //是否显示导出按钮
            // buttonsAlign:"right",  //按钮位置
            // exportTypes:['excel'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
        });
    }
}

function showStudentInfo1() {
    if(!isTimeOut()) {
        $('#table-job1').bootstrapTable('destroy');
        $('#table-job1').bootstrapTable({
            ajax: function (request) {
                ajax("zustjy/bckjBizQyxx/showStudentInfo", {
                    "type":2,
                    "jobOwid": $("#jobRefOwid").val(),
                    "owid": getCookie("yhOwid"),
                    "pageSize": $('#table-job1').bootstrapTable('getOptions').pageSize || 10,
                    "pageNo": $('#table-job1').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if (data.backCode == 0) {
                        request.success({
                            row: data.bean.records || [],
                            total: data.bean.totalCount
                        });

                    }
                })
            },
            responseHandler: function (res) {
                $('#table-job1').bootstrapTable('load', res.row);
                return {
                    "total": res.total
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
            queryParamsType: "limit",
            columns: [{
                align: 'center',
                field: 'lxr',
                title: '联系人',
            }, {
                align: 'center',
                field: 'gzsj',
                title: '报名时间',
                formatter: function (value, row, index) {
                    var value = ""
                    if (row.gzsj) {
                        value = row.gzsj.substring(0, 10);
                    } else {
                        value = "-"
                    }
                    return value;
                }
            }, {
                align: 'center',
                field: 'lxdh',
                title: '联系电话',
            }], //列设置

        });
    }
}

function tabs(a) {
    if(a==0){
        $("#nav-tabs-job li").removeClass("active")
        $("#nav-tabs-job li").eq(0).addClass("active")
        $("#stuList").hide();
        $("#stuList1").hide();
        $("#jobInfo").show();
    }else if(a==1){
        showStudentInfo1()
        $("#nav-tabs-job li").removeClass("active")
        $("#nav-tabs-job li").eq(1).addClass("active")
        $("#jobInfo").hide();
        $("#stuList").hide();
        $("#stuList1").show();

    }else if(a==2){
        showStudentInfo2()
        $("#nav-tabs-job li").removeClass("active")
        $("#nav-tabs-job li").eq(2).addClass("active")
        $("#jobInfo").hide();
        $("#stuList1").hide();
        $("#stuList").show();
    }
}