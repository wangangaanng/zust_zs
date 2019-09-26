
$(document).ready(function () {
    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href="/stuCenter/"+index
    })
    myBmList()
})

function keyLogin(){
    if (event.keyCode==13){
        searchXjh()
    }
}

function searchXjh() {
    $("#table-xjh").bootstrapTable('refresh',{pageNumber:1});
}

function myBmList() {
    $('#table-xjh').bootstrapTable('destroy');
    $('#table-xjh').bootstrapTable({
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        ajax:function(request) {
            ajax("zustjy/bckjBizXsgz/studentSubcribeList", {
                "zwbt":$("#zwbt-xjh").val().trim(),
                "yhOwid":getCookie("stuOwid"),
                // "zwlx": 1,
                "pageSize":$('#table-xjh').bootstrapTable('getOptions').pageSize || 10,
                "pageNo":$('#table-xjh').bootstrapTable('getOptions').pageNumber || 1
            }, function (data) {
                if(data.backCode==0){
                    request.success({
                        row : data.bean.records || [],
                        total: data.bean.totalCount,
                        // pageNumber:data.bean.currentPage,
                        // pageSize:data.bean.pageSize
                    });

                    // $('#table-job').bootstrapTable('load', data.bean.records);
                }
            })
        },

        responseHandler:function(res){
            // return res
            $('#table-xjh').bootstrapTable('load', res.row);
            return {
                "total":res.total
                // ,
                // "pageNumber":res.pageNumber,
                // "pageSize":res.pageSize
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
        queryParamsType:"limit",
        columns: [{
            align : 'center',
            field: 'owid',
            title: '序号',
            formatter:function(value,row,index){
                return index+1;
            }
        }, {
            field: 'zwbt',
            title: '名称',
            events:'operateEvents',
            align : 'center',
            events: window.operateEvents1,
            formatter: operateFormatterSc
        },{
            field: 'gzsj',
            title: '收藏时间',
            align : 'center',
            formatter:function(value,row,index){
                var value=row.gzsj.substring(0,10);
                return value;
            }
        }, {
            align : 'center',
            field: 'zwlx',
            title: '类型',
            formatter:function(value,row,index){
                var value=""
                if(row.zwlx==0){
                    value="职位"
                }else if(row.zwlx==1){
                    value="职来职往"
                }else if(row.zwlx==2){
                    value="社会招聘会"
                }else if(row.zwlx==3){
                    value="企业招聘会"
                }else if(row.zwlx==4){
                    value="宣讲会"
                }
                return value;
            }
        }
        ], //列设置

    });
}

function operateFormatterSc(value, row, index) {
    var c = '<a class="green-color detail" href="#" >'+row.zwbt+'</a> ';
    return c;
}

window.operateEvents1 = {
    'click .detail': function (e, value, row, index) {
        window.open(base+"/positionDetail/"+row.jobRefOwid)
    }
}