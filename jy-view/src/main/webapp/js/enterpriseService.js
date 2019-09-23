
function searchXjh() {
    $("#table-xjh").bootstrapTable('refresh',{pageNumber:1});
}

function searchZph() {
    $("#table-zph").bootstrapTable('refresh',{pageNumber:1});
}

function searchZw() {
    $("#table-zph").bootstrapTable('refresh',{pageNumber:1});
}
var layer1;
function applyXjh(){
    layer1=layer.open({
        type: 1,
        title:'申请信息',
        skin: 'layui-layer-rim', //加上边框
        area: ['750px', '480px'], //宽高
        content: '<div class="lxr-modal"><div class="row">\n' +
        '                            <div class="form-group">\n' +
        '                                <label for="lxr" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">联系人：</label>\n' +
        '                                <div class="col-sm-3">\n' +
        '                                    <input type="text" class="form-control" id="lxr" name="lxr" placeholder="" autocomplete="off">\n' +
        '                                </div>\n' +
        '                                <label for="lxdh" class="col-sm-2 control-label text-right" style="line-height: 34px;">联系电话：</label>\n' +
        '                                <div class="col-sm-3">\n' +
        '                                    <input type="text" class="form-control" id="lxdh" name="lxdh" placeholder="" autocomplete="off">\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                        <div class="row">\n' +
        '                            <div class="form-group">\n' +
        '                                <label for="jkr" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">讲课人：</label>\n' +
        '                                <div class="col-sm-3">\n' +
        '                                    <input type="text" class="form-control" id="jkr" name="jkr" placeholder="" autocomplete="off">\n' +
        '                                </div>\n' +
        '                                <label for="xjsj" class="col-sm-2 control-label text-right" style="line-height: 34px;">宣讲时间：</label>\n' +
        '                                <div class="col-sm-3">\n' +
        '                                    <input type="text" class="form-control" id="xjsj" name="xjsj" placeholder="" autocomplete="off">\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                            </div>\n' +
        '                        <div class="row">\n' +
        '                            <div class="form-group">\n' +
        '                                <label for="xjhsqly" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">申请理由：</label>\n' +
        '                                <div class="col-sm-8">\n' +
        '                                    <textarea  class="form-control" id="xjhsqly" rows="2" name="xjhsqly" placeholder="" autocomplete="off"></textarea>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                            </div>\n' +
        '                        <div class="row">\n' +
        '                            <div class="form-group">\n' +
        '                                <label for="jkrjs" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">讲课人介绍：</label>\n' +
        '                                <div class="col-sm-8">\n' +
        '                                    <textarea  class="form-control" id="jkrjs" rows="4" name="jkrjs" placeholder="" autocomplete="off"></textarea>\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                            </div>\n' +
        '                        <div class="row btn-yd">\n' +
        '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
        '                                <button class="btn green" onclick="confirmQd()">提交</button>\n' +
        '                            </div>\n' +
        '                        </div></div>'
    });
    laydate.render({
        elem: '#xjsj' //指定元素
    });
}

function confirmQd() {
    if(!$("#lxr").val().trim()){
        walert("请填写联系人")
        return
    }else if(!$("#lxdh").val().trim()){
        walert("请填写联系人电话")
        return
    }else if(!$("#jkr").val().trim()){
        walert("请填写讲课人")
        return
    }else if(!$("#xjsj").val().trim()){
        walert("请填写宣讲时间")
        return
    }else if(!$("#xjhsqly").val().trim()){
        walert("请填写申请理由")
        return
    }
    else if(!$("#jkrjs").val().trim()){
        walert("请填写讲课人介绍")
        return
    }
    var jsonObj ={
        "jobRefOwid":$("#zphOwid").val(),
        "bmlx":0,
        "bmdx":1,
        "qyxxRefOwid":getCookie("qyOwid"),
        "lxr":$("#lxr").val().trim(),
        "lxdh":$("#lxdh").val().trim()
    }
    ajax("zustjy/bckjBizJybm/applyJob", jsonObj, function (data) {
        if(data.backCode==0){
            layer.close(layer1)
            layer1=null;
            layer.open({
                title:'提示',
                content: '宣讲会申请成功，请等待审核。',
                yes: function(index, layero){
                    layer.close(index);
                }
            });
        }else{
            walert(data.errorMess)
        }
    })

}
$(document).ready(function () {
    var provice=[];
    var city=[];
    var pindex=0;
    var cindex=0;
    var _cityData=[];
    cityData3.forEach(function(e) {
        provice.push(e.text)
        city.push(e.children)
        if($("#qyProv").attr("data-val")==e.text) {
            $("#qyProv").append("<option value='" + e.text + "' selected>" + e.text + "</option>")
        }else{
            $("#qyProv").append("<option value='" + e.text + "' >" + e.text + "</option>")
        }
    });

    pindex=parseInt($("#qyProv option:selected").index())-1;
    if(pindex>-1){
        _cityData=city[pindex]
        _cityData.forEach(function(e) {
            if($("#qyCity").attr("data-val")==e.text){
                $("#qyCity").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#qyCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }

        });

    }
    cindex=parseInt($("#qyCity option:selected").index())-1;
    if(cindex>-1){
        var _areaData=_cityData[cindex].children
        _areaData.forEach(function(e) {
            if($("#qyArea").attr("data-val")==e.text){
                $("#qyArea").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#qyArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }
        });

    }

    $("#qyGsxz").val($("#qyGsxz").attr("data-val"))
    $("#qyHylb").val($("#qyHylb").attr("data-val"))
    $("#qyGsgm").val($("#qyGsgm").attr("data-val"))
    $("#qyGsjs").val($("#qyGsjs").attr("data-val"))



    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        var index=$(this).index()
        $(".content-list").eq(index).show();
        if(index==1){
            myJobList()
        }else if(index==2){
            myJobList2()
        }else if(index==3){
            myJobList1()
        }
    })

    $("#registerForm").validate({
        rules: {
            qymc:"required",
            qyProv:"required",
            qyCity:"required",
            qyArea:"required",
            qydz:"required",
            qyLxr:"required",
            qyLxrdh:{
                required: true,
                isMobile: true
            },
            qyYx:{
                required: true,
                email: true
            },
            qyGsxz:"required",
            qyHylb:"required",
            qyGsgm:"required",
            qyGsjs:"required",
        },
        messages: {
            qymc: "请填写",
            qyProv: "请选择",
            qyCity: "请选择",
            qyArea: "请选择",
            qydz: "请填写",
            qyLxr: "请填写",
            qyLxrdh:  {
                required: "请填写",
                email: "请填写正确的11位手机号码"
            },
            qyYx: {
                required: "请填写",
                email: "请填写正确电子邮箱"
            },
            qyGsxz: "请选择",
            qyHylb: "请选择",
            qyGsgm: "请选择",
            qyGsjs: "请填写",
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

function myJobList2() {
    $('#table-xjh').bootstrapTable('destroy');
    $('#table-xjh').bootstrapTable({
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        ajax:function(request) {
            ajax("zustjy/bckjBizJybm/myBmList", {
                "zwbt":$("#zwbt-xjh").val().trim(),
                "qyxxRefOwid":getCookie("qyOwid"),
                "bmlx": 0,
                "bmdx": 1,
                "pageSize":$('#table-xjh').bootstrapTable('getOptions').pageSize || 10,
                "pageNo":$('#table-xjh').bootstrapTable('getOptions').pageNumber || 1
            }, function (data) {
                if(data.backCode==0){
                    request.success({
                        row : data.bean.records ||[],
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
        height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "owid", //每一行的唯一标识，一般为主键列
        showToggle: false, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        theadClasses: "thead1",
        queryParamsType:"limit",
        // queryParams: jsonObj,
        columns: [{
            align : 'center',
            field: 'zwbt',
            title: '标题',
            formatter:function(value,row,index){
                var value=row.xjsj.substring(0,10);
                return value+"宣讲会";
            }
        }, {
            field: 'xjsj',
            title: '举办日期',
            align : 'center',
            formatter:function(value,row,index){
                var value=row.xjsj.substring(0,10);
                return value;
            }
        }, {
            align : 'center',
            field: 'state',
            title: '审核状态',
            formatter:function(value,row,index){
                var value=""
                if(row.state==0){
                    value="待审核"
                }else if(row.state==1){
                    value="审核通过"
                }else if(row.state==2){
                    value="审核拒绝"
                }
                return value;
            }
        },   {
            align : 'center',
            events:'operateEvents',
            field: 'owid',
            title: '操作',
            events: window.operateEvents,
            formatter: operateFormatterZph
        }], //列设置

    });
}

function myJobList1() {
    $('#table-zph').bootstrapTable('destroy');
    $('#table-zph').bootstrapTable({
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        ajax:function(request) {
            ajax("zustjy/bckjBizJybm/myBmList", {
                "zwbt":$("#zwbt-xjh").val().trim(),
                "qyxxRefOwid":getCookie("qyOwid"),
                "bmlx": 0,
                "bmdx": 0,
                "pageSize":$('#table-zph').bootstrapTable('getOptions').pageSize || 10,
                "pageNo":$('#table-zph').bootstrapTable('getOptions').pageNumber || 1
            }, function (data) {
                if(data.backCode==0){
                    request.success({
                        row : data.bean.records,
                        total: data.bean.totalCount,
                        // pageNumber:data.bean.currentPage,
                        // pageSize:data.bean.pageSize
                    });

                    // $('#table-job').bootstrapTable('load', data.bean.records);
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
        // queryParams: jsonObj,
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
            align : 'center',
            field: 'zwlx',
            title: '招聘会类型',
            formatter:function(value,row,index){
                return "企业招聘会";
            }
        }, {
            field: 'zphKsrq',
            title: '举办时间',
            align : 'center',
            // formatter:function(value,row,index){
            //     var value=row.zphKsrq.substring(0,10);
            //     return value;
            // }
        }, {
            align : 'center',
            field: 'zwbh',
            title: '展位',
        },{
            align : 'center',
            events:'operateEvents',
            field: 'owid',
            title: '操作',
            events: window.operateEvents,
            formatter: operateFormatterZph
        }], //列设置

    });

}


$.validator.setDefaults({
    submitHandler: function () {
        fixCompany()
    }
});

function fixCompany() {

    var jsonObj = $("#registerForm").serializeObject()
    jsonObj.owid=getCookie("qyOwid")
    ajax("zustjy/bckjBizQyxx/fixCompany", jsonObj, function (data) {
        if(data.backCode==0){
            walert("修改成功")
        }else{
            walert(data.errorMess)
        }
    })
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

