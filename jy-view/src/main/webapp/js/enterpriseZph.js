
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
                    field: 'zphKsrq',
                    title: '举办时间',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (row.zphKsrq) {
                            var value = row.zphKsrq.substring(0, 10);
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
var zdytjObj={};
var zdytjStr='';
var zdytjLength=0;
function zphtjList(zphOwid,owid) {
    var jsonObj ={
        "owid":zphOwid
    }
    ajax("zustjy/bckjBizJob/zphtjList", jsonObj, function (data) {
        if(data.backCode==0){
            if(data.bean){
                if(data.bean.length){
                    zdytjLength=data.bean.length;
                }
                for(var i=1;i<data.bean.length+1;i++){
                    for(var a in data.bean[i-1]){
                        zdytjObj['zdytj'+i]=a
                        zdytjObj['tjsd'+i]=data.bean[i-1][a]
                        // zdytjObj['str'+i]='<option value="">请选择</option>'
                        // for(var x=0;x<data.bean[i-1][a].length;x++){
                        //     zdytjObj['str'+i]+='<option value="'+data.bean[i-1][a][x]+'">'+data.bean[i-1][a][x]+'</option>'
                        // }
                        zdytjObj['str'+i]=''//'<option value="">请选择</option>'
                        if(!data.bean[i-1][a] || data.bean[i-1][a].length==0){
                            zdytjObj['str'+i]= '<input type="text" class="form-control" id="tjsd'+i+'" name="tjsd'+i+'" placeholder="" autocomplete="off">';
                        }else{
                            zdytjObj['str'+i]='<select class="form-control" id="tjsd'+i+'" name="tjsd'+i+'" ><option value="">请选择</option>'
                            for(var x=0;x<data.bean[i-1][a].length;x++){
                                zdytjObj['str'+i]+='<option value="'+data.bean[i-1][a][x]+'">'+data.bean[i-1][a][x]+'</option>'
                            }
                            zdytjObj['str'+i]+='</select>';
                        }
                    }
                }
                for(var i=1;i<data.bean.length+1;i++){
                    zdytjStr+='<div class="row">\n' +
                        '     <div class="form-group">\n' +
                        '     <label for="zdytj'+i+'" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">'+zdytjObj['zdytj'+i]+'<span class="red">*</span>：</label>\n' +
                        '     <div class="col-sm-3" style="padding:0;">\n' +zdytjObj['str'+i]+
                        // '          <select class="form-control" id="tjsd'+i+'" name="tjsd'+i+'" >'+zdytjObj['str'+i]+'</select>\n' +
                        '     </div>\n' +
                        '     </div>\n' +
                        '     </div>\n'
                }
                getOne(owid)
            }
        }else{
            walert(data.errorMess)
        }
    })
}

var layer1;
function getOne(owid) {
    var jsonObj ={
        "owid":owid
    }
    ajax("zustjy/bckjBizJybm/getOne", jsonObj, function (data) {
        if(data.backCode==0){
            if(data.bean){

                var lxr=data.bean.lxr?data.bean.lxr:''
                var lxdh=data.bean.lxdh?data.bean.lxdh:''
                var zw1=data.bean.zw1?data.bean.zw1:''
                var zw2=data.bean.zw2?data.bean.zw2:''
                var zw3=data.bean.zw3?data.bean.zw3:''
                var zw4=data.bean.zw4?data.bean.zw4:''
                var zw5=data.bean.zw5?data.bean.zw5:''
                var rs1=data.bean.rs1?data.bean.rs1:''
                var rs2=data.bean.rs2?data.bean.rs2:''
                var rs3=data.bean.rs3?data.bean.rs3:''
                var rs4=data.bean.rs4?data.bean.rs4:''
                var rs5=data.bean.rs5?data.bean.rs5:''

                layer1=layer.open({
                    type: 1,
                    title:'招聘信息',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['800px', '700px'], //宽高
                    content: '<div class="lxr-modal">\n' +
                    '                        <div class="row">\n'+
                    '                           <div class="form-group">\n' +
                    '                                <label for="lxr" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">联系人<span class="red">*</span>：</label>\n' +
                    '                                <div class="col-sm-3" style="padding:0;">\n' +
                    '                                    <input type="text" class="form-control" id="lxr" name="lxr" value="'+lxr+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                                <label for="lxdh" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">联系人手机号<span class="red">*</span>：</label>\n' +
                    '                                <div class="col-sm-2" style="padding:0;">\n' +
                    '                                    <input type="text" class="form-control" id="lxdh" name="lxdh" value="'+lxdh+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +zdytjStr+
                    '                        <div class="row">\n'+
                    '                           <div class="form-group">\n' +
                    '                                <label for="zw1" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
                    '                                <div class="col-sm-3" style="padding:0;">\n' +
                    '                                    <input type="text" class="form-control" id="zw1" name="zw1" value="'+zw1+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                                <label for="rs1" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
                    '                                <div class="col-sm-2" style="padding:0;">\n' +
                    '                                    <input type="number" class="form-control" id="rs1" name="rs1" value="'+rs1+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <div class="row">\n'+
                    '                           <div class="form-group">\n' +
                    '                                <label for="zw2" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
                    '                                <div class="col-sm-3" style="padding:0;">\n' +
                    '                                    <input type="text" class="form-control" id="zw2" name="zw2" value="'+zw2+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                                <label for="rs2" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
                    '                                <div class="col-sm-2" style="padding:0;">\n' +
                    '                                    <input type="number" class="form-control" id="rs2" name="rs2" value="'+rs2+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <div class="row">\n'+
                    '                           <div class="form-group">\n' +
                    '                                <label for="zw3" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
                    '                                <div class="col-sm-3" style="padding:0;">\n' +
                    '                                    <input type="text" class="form-control" id="zw3" name="zw3" value="'+zw3+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                                <label for="rs3" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
                    '                                <div class="col-sm-2" style="padding:0;">\n' +
                    '                                    <input type="number" class="form-control" id="rs3" name="rs3" value="'+rs3+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <div class="row">\n'+
                    '                           <div class="form-group">\n' +
                    '                                <label for="zw4" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
                    '                                <div class="col-sm-3" style="padding:0;">\n' +
                    '                                    <input type="text" class="form-control" id="zw4" name="zw4" value="'+zw4+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                                <label for="rs4" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
                    '                                <div class="col-sm-2" style="padding:0;">\n' +
                    '                                    <input type="number" class="form-control" id="rs4" name="rs4" value="'+rs4+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <div class="row">\n'+
                    '                           <div class="form-group">\n' +
                    '                                <label for="zw5" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
                    '                                <div class="col-sm-3" style="padding:0;">\n' +
                    '                                    <input type="text" class="form-control" id="zw5" name="zw5" value="'+zw5+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                                <label for="rs5" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
                    '                                <div class="col-sm-2" style="padding:0;">\n' +
                    '                                    <input type="number" class="form-control" id="rs5" name="rs5" value="'+rs5+'" placeholder="" autocomplete="off">\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <div class="row btn-yd"><div class="col-md-9 col-sm-offset-1 text-center">\n' +
                    '                                <button class="btn green" style="width: 120px;" onclick="confirmQd('+"'"+owid+"'"+')">保存</button>\n' +
                    '                            </div>\n' +
                    '                        </div></div>'
                });
                if(data.bean.tjsd1){
                    $("#tjsd1").val(data.bean.tjsd1)
                }
                if(data.bean.tjsd2){
                    $("#tjsd2").val(data.bean.tjsd2)
                }
                if(data.bean.tjsd3){
                    $("#tjsd3").val(data.bean.tjsd3)
                }
                if(data.bean.tjsd4){
                    $("#tjsd4").val(data.bean.tjsd4)
                }
                if(data.bean.tjsd5){
                    $("#tjsd5").val(data.bean.tjsd5)
                }
            }
        }else{
            walert(data.errorMess)
        }
    })
}


function confirmQd(owid) {
    if(!isTimeOut()) {
        if (!$("#lxr").val().trim()) {
            walert("请填写联系人")
            return
        }
        if (!$("#lxdh").val().trim()) {
            walert("请填写联系人手机号")
            return
        } else {
            if (!testSjh($("#lxdh").val().trim())) {
                walert("请填写正确手机号码")
                return
            }
        }

        for(var i=1;i<zdytjLength+1;i++){
            if(!$("#tjsd"+i).val()){
                walert("请填选"+zdytjObj['zdytj'+i])
                return;
            }
        }

        for(var i=1;i<6;i++){
            if($("#zw"+i).val() && !$("#rs"+i).val()){
                walert("请填写岗位及相应招聘人数")
                return;
            }
            if(!$("#zw"+i).val() && $("#rs"+i).val()){
                walert("请填写岗位及相应招聘人数")
                return;
            }
        }

        var jsonObj = {
            "owid": owid,
            "lxr": $("#lxr").val().trim(),
            "lxdh": $("#lxdh").val().trim()
        }
        for(var i=1;i<zdytjLength+1;i++){
            // jsonObj['zdytj'+i]=zdytjObj['zdytj'+i]
            jsonObj['tjsd'+i]=$("#tjsd"+i).val()
        }
        for(var i=1;i<6;i++){
            jsonObj['zw'+i]=$("#zw"+i).val()
            jsonObj['rs'+i]=$("#rs"+i).val()
        }
        ajax("zustjy/bckjBizJybm/fixJybm", jsonObj, function (data) {
            if (data.backCode == 0) {
                layer.close(layer1)
                layer1 = null;
                walert("修改成功")
            } else {
                walert(data.errorMess)
            }
        })
    }
}

function operateFormatterZph(value, row, index) {
    if (row.state==0) {
        if(row.zphKsrq && compareToday(row.zphKsrq)){
            var c = '<a class="green-color detail" href="#" >查看</a> ';
            return c;
        }else{
            var c = '<a class="green-color detail" href="#" >查看</a> <a class="green-color modify" style="color: #008784;" href="#" >修改</a>';
            return c;
        }
    }

    // var c = '<a class="green-color detail" href="#" >查看</a> ';
    // return c;
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
    },
    'click .modify': function (e, value, row, index) {
        if(!isTimeOut()) {
            zphtjList(row.jobRefOwid,row.owid)
            // getOne(row.owid)

        }
    },
}