
$(document).ready(function () {
    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href=base+"/enterpriseService/"+index
    })
    if(!isTimeOut()) {
        myJobList1()
    }
})

function applyZpj() {
    if(!isTimeOut()) {
        window.open(base + "/jobFair/0")
    }
}

function keyLogin(){
    if (event.keyCode==13){
        searchZph()
    }
}

function searchZph() {
    var key=$("#zwbt-zph").val().trim()
    if(testSql(key,$("#zwbt-zph"))){
        if(!isTimeOut()) {
            $("#table-zph").bootstrapTable('refresh', {pageNumber: 1});
        }
    }
}

function myJobList1() {
    if(!isTimeOut()) {
        AntiSqlValidAll(["#zwbt-zph"], function () {
            $('#table-zph').bootstrapTable('destroy');
            $('#table-zph').bootstrapTable({
                pageNumber: 1, //初始化加载第一页，默认第一页
                pageSize: 10, //每页的记录行数（*）
                ajax: function (request) {
                    ajax("zustjy/bckjBizJybm/myBmList", {
                        "zwbt": $("#zwbt-zph").val().trim(),
                        "qyxxRefOwid": getCookie("qyOwid"),
                        "bmlx": 0,
                        "bmdx": 0,
                        "pageSize": $('#table-zph').bootstrapTable('getOptions').pageSize || 10,
                        "pageNo": $('#table-zph').bootstrapTable('getOptions').pageNumber || 1
                    }, function (data) {
                        if (data.backCode == 0) {
                            request.success({
                                row: data.bean.records || [],
                                total: data.bean.totalCount,
                            });

                        }
                    })
                },

                responseHandler: function (res) {
                    $('#table-zph').bootstrapTable('load', res.row);
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
                // queryParams: jsonObj,
                columns: [{
                    align: 'center',
                    field: 'zwbt',
                    title: '招聘会名称',
                }, {
                    align: 'center',
                    field: 'zwCity',
                    title: '城市'
                }, {
                    align: 'center',
                    field: 'zphJbdd',
                    title: '举办地址',
                }, {
                    align: 'center',
                    field: 'zwlx',
                    title: '招聘会类型',
                    formatter: function (value, row, index) {
                        return "企业招聘会";
                    }
                }, {
                    field: 'xjsj',
                    title: '举办时间',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (row.xjsj) {
                            var value = row.xjsj.substring(0, 10);
                            return value;
                        } else {
                            return "-"
                        }
                    }
                }, {
                    field: 'zphJtsj',
                    title: '举办时长',
                    align: 'center',
                }, {
                    align: 'center',
                    field: 'zwbh',
                    title: '展位',
                }, {
                    align: 'center',
                    field: 'state',
                    title: '状态',
                    formatter: function (value, row, index) {
                        if (row.state==0) {
                            if(row.zphKsrq && compareToday(row.zphKsrq)){
                                return '<span>已失效</span>';
                            }else{
                                return '<span>待审核</span>';
                            }
                        } else if (row.state==1) {
                            return '<span style="color:#008784;">通过</span>';
                        } else if (row.state==2) {
                            return '<span style="color:red;">拒绝</span>';
                        }

                    }
                }, {
                    align: 'center',
                    field: 'owid',
                    title: '操作',
                    events: window.operateEvents,
                    formatter: operateFormatterZph
                }], //列设置

            });
        });
    }

}

function operateFormatterZph(value, row, index) {
    var c = '<a class="green-color detail" href="#" >查看</a> ';
    return c;
}

window.operateEvents = {
    'click .detail': function (e, value, row, index) {
        if(!isTimeOut()) {
            var zwbh = "";
            console.log(row.zwbh)
            if (emptyCheck(row.zwbh)) {
                zwbh = row.zwbh;
            } else {
                zwbh = "暂无"
            }
            // window.open(base + "/positionDetail/qy/" + zwbh + "/" + row.jobRefOwid)
            window.open(base + "/zphXq/" + row.owid + "/" + row.jobRefOwid)
        }
    },
    'click .remove': function (e, value, row, index) {
        if(!isTimeOut()) {
            layer.confirm('确定删除该条记录？', {
                btn: ['确定'] //按钮
            }, function () {
                var jsonObj = {
                    "owid": row.owid,
                }
                ajax("zustjy/bckjBizJob/deleteOneJob", jsonObj, function (data) {
                    if (data.backCode == 0) {
                        $('#table-job').bootstrapTable('removeByUniqueId', row.owid);
                        layer.msg('删除成功', {icon: 1});
                    }
                })

            });
        }
    }
}