
$(document).ready(function () {
    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href=base+"/stuCenter/"+index
    })
    if(!isTimeOut()) {
        myBmList()
    }
})

function wybm(){
    window.open(base+"/wybm")
}

function keyLogin(){
    if (event.keyCode==13){
        searchXjh()
    }
}

function searchXjh() {
    var key=$("#zwbt-xjh").val().trim()
    if(testSql(key,$("#zwbt-xjh"))){
        if(!isTimeOut()) {
            $("#table-xjh").bootstrapTable('refresh', {pageNumber: 1});
        }
    }
}

function myBmList() {
    if(!isTimeOut()) {
        AntiSqlValidAll(["#zwbt-xjh"], function () {
            $('#table-xjh').bootstrapTable('destroy');
            $('#table-xjh').bootstrapTable({
                pageNumber: 1, //初始化加载第一页，默认第一页
                pageSize: 10, //每页的记录行数（*）
                ajax: function (request) {
                    ajax("zustjy/bckjBizJybm/myBmList", {
                        "zwbt": $("#zwbt-xjh").val().trim(),
                        "yhRefOwid": getCookie("stuOwid"),
                        "bmlx": 1,
                        "pageSize": $('#table-xjh').bootstrapTable('getOptions').pageSize || 10,
                        "pageNo": $('#table-xjh').bootstrapTable('getOptions').pageNumber || 1
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
                    $('#table-xjh').bootstrapTable('load', res.row);
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
                columns: [{
                    align: 'center',
                    field: 'owid',
                    title: '序号',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                }, {
                    align: 'center',
                    field: 'zwbt',
                    title: '名称',
                    events: window.operateEvents,
                    formatter: operateFormatter
                }, {
                    field: 'bmsj',
                    title: '报名时间',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var value = row.bmsj.substring(0, 10);
                        return value;
                    }
                }, {
                    align: 'center',
                    field: 'bmdx',
                    title: '类型',
                    formatter: function (value, row, index) {
                        var value = ""
                        if (row.bmdx == 0) {
                            value = "招聘会"
                        } else if (row.bmdx == 1) {
                            value = "宣讲会"
                        } else if (row.bmdx == 2) {
                            value = "职位"
                        }
                        return value;
                    }
                }, {
                    align: 'center',
                    field: 'state',
                    title: '状态',
                    formatter: function (value, row, index) {
                        return "已报名";
                    }
                }
                ], //列设置

            });
        });
    }
}

function operateFormatter(value, row, index) {
    var c = '<a class="green-color xq" href="#">'+convertStr(row.zwbt,"-")+'</a> ';
    return c;
}

window.operateEvents = {
    'click .xq': function (e, value, row, index) {
        if(!isTimeOut()) {
            window.open(base + "/positionDetail/" + row.jobRefOwid)
        }
    }
}