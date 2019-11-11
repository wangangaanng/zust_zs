$(document).ready(function () {
    searchLnfsmc();
});

var pageNo=1;
var pageSize=10;
var lnobj;

function getChanges(obj) {
    lnobj = $(obj).attr("id");
    var nf = $('#nf').val();
    var sf = $('#sf').val();
    var kl = $('#kl').val();
    var pc = $('#pc').val();
    var zy = $('#zy').val();
    var data = {
        nf: nf,
        sf: sf,
        kl: kl,
        pc: pc,
        zy: zy
    };
    ajax("zustzs/bckjBizLntj/getChanges", data, function (res) {
        if (res.backCode === 0) {
            var str = '';
            if (res.bean) {
                clearOption();
                if(lnobj!=='nf')
                $.each(res.bean.nfList, function (k, p) {
                    if (nf === p.nf) {
                        str = "<option selected='selected' value='"+ p.nf +"'>"+ p.nf +"</option>"
                    } else {
                        str = "<option  value='"+p.nf+"'>"+p.nf+"</option>";
                    }
                    $('#nf').append(str);
                });
                if(lnobj!=='sf')
                $.each(res.bean.sfList, function (k, p) {
                    if (sf === p.sf) {
                        str = "<option selected='selected' value='"+ p.sf +"'>"+ p.sf +"</option>"
                    } else {
                        str = "<option  value='"+p.sf+"'>"+p.sf+"</option>";
                    }
                    $('#sf').append(str);
                });
                if(lnobj!=='kl')
                $.each(res.bean.klList, function (k, p) {
                    if (kl === p.kl) {
                        str = "<option selected='selected' value='"+ p.kl +"'>"+ p.kl +"</option>"
                    } else {
                        str = "<option value='"+p.kl+"'>"+p.kl+"</option>";
                    }
                    $('#kl').append(str);
                });
                if(lnobj!=='pc')
                $.each(res.bean.pcList, function (k, p) {
                    if (pc === p.pc) {
                        str = "<option selected='selected' value='"+ p.pc +"'>"+ p.pc +"</option>"
                    } else {
                        str = "<option  value='"+p.pc+"'>"+p.pc+"</option>";
                    }
                    $('#pc').append(str);
                });
                if(lnobj!=='zy')
                $.each(res.bean.zyList, function (k, p) {
                    if (zy === p.zy) {
                        str = "<option selected='selected' value='"+ p.zy +"'>"+ p.zy +"</option>"
                    } else {
                        str = "<option  value='"+p.zy+"'>"+p.zy+"</option>";
                    }
                    $('#zy').append(str);
                });
                searchLnfsmc(nf, sf, kl, pc, zy);
            } else {
                layer.open({
                    title: '提示',
                    content: '暂无数据'
                });
            }
        } else {
            walert(res.errorMess);
        }
    })
}

function searchLnfsmc(nf, sf, kl, pc, zy) {
    if ((nf == "") || (nf == null) || (nf = "null")) {
        nf = $("#nf").val();
    }
    if ((sf == "") || (sf == null) || (sf == "null")) {
        sf = $("#sf").val();
    }
    if ((kl == "") || (kl == null) || (kl == "null")) {
        kl = $("#kl").val();
    }
    if ((pc == "") || (pc == null) || (pc == "null")) {
        pc = $("#pc").val();
    }
    if ((zy == "") || (zy == null) || (zy == "null")) {
        zy = $("#zy").val();
    }
    $('#table-lnfsmc').bootstrapTable('destroy');
    $('#table-lnfsmc').bootstrapTable({
        ajax: function (request) {
            ajax("zustzs/bckjBizLntj/getResult", {
                nf: nf,
                sf: sf,
                kl: kl,
                pc: pc,
                zy: zy,
                pageNo: $('#table-lnfsmc').bootstrapTable('getOptions').pageNumber || 1,
                pageSize: $('#table-lnfsmc').bootstrapTable('getOptions').pageSize || pageSize
            }, function (data) {
                if (data.backCode === 0) {
                    request.success({
                        row: convertStr(data.bean.records, []),
                        total: data.bean.totalCount
                    })
                }else{
                    request.success({
                        row : [],
                    });
                }
            })
        },
        responseHandler:function(res){
            $('#table-lnfsmc').bootstrapTable('load', res.row);
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
        uniqueId: "owid", //每一行的唯一标识，一般为主键列
        showToggle: false, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        theadClasses: "thead1",
        queryParamsType:"limit",
        columns: [
            {
                align: 'center',
                field: 'nf',
                title: '年份'
            },
            {
                align: 'center',
                field: 'sf',
                title: '省份'
            },
            {
                align: 'center',
                field: 'kl',
                title: '科类'
            },
            {
                align: 'center',
                field: 'pc',
                title: '批次'
            },
            {
                align: 'center',
                field: 'zy',
                title: '专业'
            },
            {
                align: 'center',
                field: 'xz',
                title: '学制'
            },
            {
                align: 'center',
                field: 'lqs',
                title: '录取数'
            },
            {
                align: 'center',
                field: 'zgf',
                title: '最高分'
            },
            {
                align: 'center',
                field: 'zdf',
                title: '最低分'
            },
            {
                align: 'center',
                field: 'pjf',
                title: '平均分'
            }
        ]
    });
}

function clearOption() {
    if (lnobj!=='nf')
        $("#nf").empty();
    if (lnobj!=='sf')
        $("#sf").empty();
    if (lnobj!=='kl')
        $("#kl").empty();
    if (lnobj!=='pc')
        $("#pc").empty();
    if (lnobj!=='zy')
        $("#zy").empty();
    var nfoption="<option value=''>---请选择---</option>";
    var sfoption="<option value=''>---请选择---</option>";
    var kloption="<option value=''>---请选择---</option>";
    var pcoption="<option value=''>---请选择---</option>";
    var zyoption="<option value=''>---请选择---</option>";
    if(lnobj!=='nf')
        $("#nf").append(nfoption);
    if(lnobj!=='sf')
        $("#sf").append(sfoption);
    if(lnobj!=='pc')
        $("#pc").append(pcoption);
    if(lnobj!=='kl')
        $("#kl").append(kloption);
    if(lnobj!=='zy')
        $("#zy").append(zyoption);
}

var clearObj;
function clearVal(obj) {
    clearObj = $(obj).attr("class");
    $("#"+clearObj).empty();
    var nfoption="<option selected='true' value=''>---请选择---</option>";
    $("#"+clearObj).append(nfoption);

    getChanges(obj);
}

function exportExcel() {
    var nf = $('#nf').val();
    var sf = $('#sf').val();
    var kl = $('#kl').val();
    var pc = $('#pc').val();
    var zy = $('#zy').val();
    var data = {
        nf: nf,
        sf: sf,
        kl: kl,
        pc: pc,
        zy: zy
    };
    ajax("zustzs/bckjBizLntj/exportExcel", data, function (res) {
        if (res.backCode === 0) {
            //本地
            // window.open("http://127.0.0.1:8081/files/" + res.bean);
            //测试服务器
            window.open("http://192.168.3.222/zjcFiles/" + res.bean);
            //正式
            // window.open("https://job.zust.edu.cn/zjcFiles/" + res.bean);
        } else {
            walert("导出失败")
        }
    })
}