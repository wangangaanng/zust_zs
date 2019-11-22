var provice=[];
var city=[];
var pindex=0;
var cindex=0;
var _cityData=[];

function getCity() {
    $("#zwCity").html("<option value=''>请选择</option>")
    $("#zwArea").html("<option value=''>请选择</option>")
    pindex=parseInt($("#zwPro option:selected").index())-1;
    if(pindex>-1){
        _cityData=city[pindex]
        _cityData.forEach(function(e) {
            $("#zwCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
        });

    }

}

function getArea() {
    $("#zwArea").html("<option value=''>请选择</option>")
    cindex=parseInt($("#zwCity option:selected").index())-1;
    if(cindex>-1){
        var _areaData=_cityData[cindex].children
        _areaData.forEach(function(e) {
            $("#zwArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
        });

    }
}
$(document).ready(function () {

    if($("#sel1").val()){
        $("#zwSxsj").val($("#sel1").val().substring(0,10))
    }
    laydate.render({
        elem: "#zwSxsj" //指定元素
        ,min: 1
    });

    cityData3.forEach(function(e) {
        provice.push(e.text)
        city.push(e.children)
        if($("#zwPro").attr("data-val")==e.text) {
            $("#zwPro").append("<option value='" + e.text + "' selected>" + e.text + "</option>")
        }else{
            $("#zwPro").append("<option value='" + e.text + "' >" + e.text + "</option>")
        }
    });

    pindex=parseInt($("#zwPro option:selected").index())-1;
    if(pindex>-1){
        _cityData=city[pindex]
        _cityData.forEach(function(e) {
            if($("#zwCity").attr("data-val")==e.text){
                $("#zwCity").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#zwCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }

        });

    }
    cindex=parseInt($("#zwCity option:selected").index())-1;
    if(cindex>-1){
        var _areaData=_cityData[cindex].children
        _areaData.forEach(function(e) {
            if($("#zwArea").attr("data-val")==e.text){
                $("#zwArea").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#zwArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }
        });

    }

    $("#zwXs").val(parseInt(clearComma($("#zwXs").attr("data-val"))))
    $("#zwGzzn").val($("#zwGzzn").attr("data-val"))
    $("#zwGzxz").val($("#zwGzxz").attr("data-val"))
    $("#zwNlyq").val($("#zwNlyq").attr("data-val"))
    $("#zwXlyq").val($("#zwXlyq").attr("data-val"))
    $("#zwGznx").val($("#zwGznx").attr("data-val"))
    $("#zwYyyq").val($("#zwYyyq").attr("data-val"))
    $("#zwGwzz").val($("#zwGwzz").attr("data-val"))

    jQuery.validator.addMethod("isNum", function(value, element) {
        return this.optional(element) || !isNaN(value) && !testFloat(value);
    }, "请填写整数");

    $("#registerForm").validate({
        rules: {
            zwbt:"required",
            zwPro:"required",
            zwCity:"required",
            zwArea:"required",
            zwGzzn:"required",
            zwGzxz:"required",
            zwLxyx:{
                required: true,
                email: true
            },
            zwXs:{
                required: true,
                isNum: true
            },
            zwZprs:"required",
            zwNlyq:"required",
            zwXlyq:"required",
            zwGznx:"required",
            zwYyyq:"required",
            zwGwzz:"required",
        },
        messages: {
            zwbt: "请填写",
            zwPro: "请选择",
            zwCity: "请选择",
            zwArea: "请选择",
            zwGzzn: "请选择",
            zwGzxz: "请选择",
            zwLxyx: {
                required: "请填写",
                email: "请填写正确电子邮箱"
            },
            zwZprs: "请填写",
            zwXs: {
                required: "请填写",
                isNum: "请填写整数"
            },
            zwNlyq: "请选择",
            zwXlyq: "请选择",
            zwGznx: "请选择",
            zwYyyq: "请选择",
            zwGwzz: "请填写",
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).parent().addClass( "has-error" ).removeClass( "has-success" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).parent().removeClass( "has-error" );
        }
    });
})

$.validator.setDefaults({
    submitHandler: function () {
        fixJob()
    }
});

function fixJob() {
    if(!isTimeOut()) {
        var jsonObj = $("#registerForm").serializeObject()
        jsonObj.qyxxRefOwid = getCookie("qyOwid")
        jsonObj.zwlx = 0
        jsonObj.owid = $("#zwOwid").val();
        // console.log(jsonObj)
        ajax("zustjy/bckjBizJob/fixJob", jsonObj, function (data) {
            if (data.backCode == 0) {
                layer.open({
                    title: '提示',
                    content: '职位发布成功',
                    yes: function (index, layero) {
                        layer.close(index);
                    }
                });
            }
        })
    }
}

function zwSubcribeList() {
    if(!isTimeOut()) {
        $('#table-job').bootstrapTable('destroy');
        $('#table-job').bootstrapTable({
            ajax: function (request) {
                ajax("zustjy/bckjBizXsgz/zwSubcribeList", {
                    "jobRefOwid": $("#zwOwid").val(),
                    "pageSize": $('#table-job').bootstrapTable('getOptions').pageSize || 20,
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
            pageSize: 20, //每页的记录行数（*）
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
                title: '关注时间',
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
            // exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据
            // showExport: true,  //是否显示导出按钮
            // buttonsAlign:"right",  //按钮位置
            // exportTypes:['excel'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
        });
    }
}

function myBmList() {
    if(!isTimeOut()) {
        $('#table-job1').bootstrapTable('destroy');
        $('#table-job1').bootstrapTable({
            ajax: function (request) {
                ajax("zustjy/bckjBizJybm/myBmList", {
                    "jobRefOwid": $("#zwOwid").val(),
                    "bmlx":1,
                    "bmdx":2,
                    "pageSize": $('#table-job1').bootstrapTable('getOptions').pageSize || 20,
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
            pageSize: 20, //每页的记录行数（*）
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
        zwSubcribeList()
        $("#nav-tabs-job li").removeClass("active")
        $("#nav-tabs-job li").eq(1).addClass("active")
        $("#jobInfo").hide();
        $("#stuList1").hide();
        $("#stuList").show();

    }else if(a==2){
        myBmList()
        $("#nav-tabs-job li").removeClass("active")
        $("#nav-tabs-job li").eq(2).addClass("active")
        $("#jobInfo").hide();
        $("#stuList").hide();
        $("#stuList1").show();
    }
}