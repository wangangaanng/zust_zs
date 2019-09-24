
$(document).ready(function () {
    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href="/enterpriseService/"+index
    })

    myJobList()
})

function searchZw() {
    $("#table-job").bootstrapTable('refresh',{pageNumber:1});
}

function myJobList() {
    $('#table-job').bootstrapTable('destroy');
    $('#table-job').bootstrapTable({
        ajax:function(request) {
            ajax("zustjy/bckjBizJob/myJobList", {
                "zwbt":$("#zwbt-zw").val().trim(),
                "qyxxRefOwid":getCookie("qyOwid"),
                "zwlx":0,
                "pageSize":$('#table-job').bootstrapTable('getOptions').pageSize || 2,
                "pageNo":$('#table-job').bootstrapTable('getOptions').pageNumber || 1
            }, function (data) {
                if(data.backCode==0){
                    request.success({
                        row : data.bean.records || [],
                        total: data.bean.totalCount
                    });

                }
            })
        },
        responseHandler:function(res){
            $('#table-job').bootstrapTable('load', res.row);
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
        pageSize: 2, //每页的记录行数（*）
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
