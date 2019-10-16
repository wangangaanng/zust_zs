
$(document).ready(function () {

    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href="/enterpriseService/"+index
    })
    if(!isTimeOut()) {
        myJobList2()
    }

    xjhtjList()
})

function keyLogin(){
    if (event.keyCode==13){
        searchXjh()
    }
}

function searchXjh() {
    var key=$("#zwbt-xjh").val().trim()
    if(testSql(key,$("#zwbt-xjh"))){
        if(!isTimeOut()){
            $("#table-xjh").bootstrapTable('refresh',{pageNumber:1});
        }
    }
}

var zdytjObj={};
var zdytjStr='';
var zdytjLength=0;
function xjhtjList() {
    var jsonObj ={
    }
    ajax("zustjy/bckjBizJob/xjhtjList", jsonObj, function (data) {
        if(data.backCode==0){
            zdytjLength=data.bean.length;
            for(var i=1;i<data.bean.length+1;i++){
                for(var a in data.bean[i-1]){
                    zdytjObj['zdytj'+i]=a
                    zdytjObj['tjsd'+i]=data.bean[i-1][a]
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
                '     <label for="zdytj'+i+'" class="col-sm-3 control-label text-right" style="line-height: 34px;">'+zdytjObj['zdytj'+i]+'<span class="red">*</span>：</label>\n' +
                '     <div class="col-sm-4">\n' +zdytjObj['str'+i]+
                // '          <select class="form-control" id="tjsd'+i+'" name="tjsd'+i+'" >'+'</select>\n' +
                '     </div>\n' +
                '     </div>\n' +
                '     </div>\n'
            }
        }else{
            walert(data.errorMess)
        }
    })
}

// str+='<div class="row">\n' +
//     '     <div class="form-group">\n' +
//     '     <label for="zdytj'+i+'" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">'+a+'<span class="red">*</span>：</label>\n' +
//     '     <div class="col-sm-3">\n' +
//     '          <input type="text" class="form-control" id="zdytj'+i+'" name="zdytj'+i+'" placeholder="" autocomplete="off">\n' +
//     '     </div>\n' +
//     '     <label for="lxdh" class="col-sm-2 control-label text-right" style="line-height: 34px;">联系人手机<span class="red">*</span>：</label>\n' +
//     '     <div class="col-sm-3">\n' +
//     '     <input type="text" class="form-control" id="lxdh" name="lxdh" placeholder="" autocomplete="off">\n' +
//     '     </div>\n' +
//     '     </div>\n' +
//     '     </div>\n'

var layer1;
function applyXjh(){
    if(!isTimeOut()){
        layer1=layer.open({
            type: 1,
            title:'申请信息',
            skin: 'layui-layer-rim', //加上边框
            area: ['800px', '600px'], //宽高
            content: '<div class="lxr-modal"><div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="lxr" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">联系人<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-3">\n' +
            '                                    <input type="text" class="form-control" id="lxr" name="lxr" value="'+JSON.parse(getCookie("qyInfo")).qyLxr+'" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                                <label for="lxdh" class="col-sm-2 control-label text-right" style="line-height: 34px;">联系人手机<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-3">\n' +
            '                                    <input type="text" class="form-control" id="lxdh" name="lxdh" value="'+JSON.parse(getCookie("qyInfo")).qyLxrdh+'" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="jkr" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">讲课人<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-3">\n' +
            '                                    <input type="text" class="form-control" id="jkr" name="jkr" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                                <label for="xjsj" class="col-sm-2 control-label text-right" style="line-height: 34px;">宣讲时间<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-3">\n' +
            '                                    <input type="text" class="form-control" id="xjsj" name="xjsj" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                            </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="xjhsqly" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">申请理由<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-8">\n' +
            '                                    <textarea  class="form-control" id="xjhsqly" style="resize: none;" rows="4" name="xjhsqly" placeholder="" autocomplete="off"></textarea>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                            </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="jkrjs" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">讲课人介绍<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-8">\n' +
            '                                    <textarea  class="form-control" id="jkrjs" style="resize: none;" rows="4" name="jkrjs" placeholder="" autocomplete="off"></textarea>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                            </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="memo" class="col-sm-2 col-sm-offset-1 control-label text-right" style="line-height: 34px;">备注<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-8">\n' +
            '                                    <textarea  class="form-control" id="memo" style="resize: none;" rows="4" name="memo" placeholder="填写相关要求" autocomplete="off"></textarea>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                            </div>\n' +zdytjStr+
            '                        <div class="row btn-yd">\n' +
            '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
            '                                <button class="btn green" style="width: 120px;" onclick="confirmQd()">提交</button>\n' +
            '                            </div>\n' +
            '                        </div></div>'
        });
        laydate.render({
            elem: '#xjsj', //指定元素
            type: 'datetime',
            min: 0,
            format:'yyyy-MM-dd HH:mm'
        });
    }
}

function confirmQd() {
    if(!isTimeOut()){
        if(!$("#lxr").val().trim()){
            walert("请填写联系人")
            return
        }
        if(!$("#lxdh").val().trim()){
            walert("请填写联系人手机")
            return
        }else {
            if(!testSjh($("#lxdh").val().trim())){
                walert("请填写正确手机号码")
                return
            }
        }
        if(!$("#jkr").val().trim()){
            walert("请填写讲课人")
            return
        }
        if(!$("#xjsj").val().trim()){
            walert("请填写宣讲时间")
            return
        }
        if(!$("#xjhsqly").val().trim()){
            walert("请填写申请理由")
            return
        }else{
            if($("#xjhsqly").val().trim().length>200){
                walert("申请理由不得超过200字")
                return
            }
        }
        if(!$("#jkrjs").val().trim()){
            walert("请填写讲课人介绍")
            return
        }else{
            if($("#jkrjs").val().trim().length>200){
                walert("讲课人介绍不得超过200字")
                return
            }
        }
        if(!$("#memo").val().trim()){
            walert("请填写备注")
            return
        }else{
            if($("#memo").val().trim().length>200){
                walert("备注不得超过200字")
                return
            }
        }
        for(var i=1;i<zdytjLength+1;i++){
            if(!$("#tjsd"+i).val()){
                walert('请填选'+zdytjObj['zdytj'+i])
                return;
            }
        }
        var jsonObj ={
            "jobRefOwid":$("#zphOwid").val(),
            "bmlx":0,
            "bmdx":1,
            "qyxxRefOwid":getCookie("qyOwid"),
            "lxr":$("#lxr").val().trim(),
            "lxdh":$("#lxdh").val().trim(),
            "xjsj":$("#xjsj").val(),
            "jkr":$("#jkr").val(),
            "jkrjs":$("#jkrjs").val(),
            "xjhsqly":$("#xjhsqly").val(),
            "memo":$("#memo").val(),

        }
        for(var i=1;i<zdytjLength+1;i++){
            jsonObj['zdytj'+i]=zdytjObj['zdytj'+i]
            jsonObj['tjsd'+i]=$("#tjsd"+i).val()
        }

        console.log(jsonObj)
        ajax("zustjy/bckjBizJybm/applyJob", jsonObj, function (data) {
            if(data.backCode==0){
                layer.close(layer1)
                layer1=null;
                layer.open({
                    title:'提示',
                    content: '宣讲会申请成功，请等待审核。',
                    yes: function(index, layero){
                        layer.close(index);
                        searchXjh()
                    }
                });
            }else{
                walert(data.errorMess)
            }
        })
    }
}

function myJobList2() {
    if(!isTimeOut()) {
        AntiSqlValidAll(["#zwbt-xjh"], function () {
            $('#table-xjh').bootstrapTable('destroy');
            $('#table-xjh').bootstrapTable({
                pageNumber: 1, //初始化加载第一页，默认第一页
                pageSize: 10, //每页的记录行数（*）
                ajax: function (request) {
                    ajax("zustjy/bckjBizJybm/myBmList", {
                        "zwbt": $("#zwbt-xjh").val().trim(),
                        "qyxxRefOwid": getCookie("qyOwid"),
                        "bmlx": 0,
                        "bmdx": 1,
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
                    field: 'bt',
                    title: '标题',
                    formatter: function (value, row, index) {
                        if (!row.zwbt) {
                            return "暂无";
                        }else {
                            return row.zwbt;
                        }

                    }
                }, {
                    field: 'xjsj',
                    title: '举办日期',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (row.xjsj) {
                            var value = row.xjsj.substring(0, 10);
                            return value;
                        } else {
                            return "-";
                        }

                    }
                }, {
                    field: 'zphJtsj',
                    title: '具体时间',
                    align: 'center',
                },  {
                    field: 'xxlxr',
                    title: '学校联系人',
                    align: 'center',
                },  {
                    field: 'xxlxrdh',
                    title: '学校联系人</br>电话',
                    align: 'center',
                },  {
                    field: 'zphJbdd',
                    title: '举办地点',
                    align: 'center',
                }, {
                    align: 'center',
                    field: 'state',
                    title: '审核状态',
                    formatter: function (value, row, index) {
                        var value = ""
                        if (row.state == 0) {
                            value = "<span>待审核</span>"
                        } else if (row.state == 1) {
                            value = "<span style='color: #008784;'>审核通过</span>"
                        } else if (row.state == 2) {
                            value = "<span style='color: red;'>审核拒绝</span>"
                        }
                        return value;
                    }
                }, {
                    align: 'center',
                    events: 'operateEvents',
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

function close1() {
    layer.close(layer2);
    layer2=null;
}

var layer2;
window.operateEvents = {
    'click .detail': function (e, value, row, index) {
        if(!isTimeOut()) {
            window.open(base + '/xjhXq/' + row.owid + '/' + row.jobRefOwid)
            // var jsonObj = {
            //     "owid": row.owid,
            // }
            // ajax("zustjy/bckjBizJybm/getOne", jsonObj, function (data) {
            //     if (data.backCode == 0) {
            //         if (data.bean) {
            //             var xjsj = "";
            //             if (data.bean.xjsj) {
            //                 xjsj = data.bean.xjsj
            //             } else {
            //                 xjsj = "暂无"
            //             }
            //             var xqStr='';
            //             for(var i=1;i<6;i++){
            //                 if(data.bean['zdytj'+i]){
            //                     xqStr+='<div class="row">\n' +
            //                         '   <div class="form-group">\n' +
            //                         '   <label for="jkrjs" class="col-sm-3 control-label text-right">'+data.bean['zdytj'+i]+'：</label>\n' +
            //                         '   <div class="col-sm-8">\n' + convertStr(data.bean['tjsd'+i],'') +
            //                         '   </div>\n' +
            //                         '   </div>\n' +
            //                         '   </div>\n'
            //                 }
            //             }
            //             layer2 = layer.open({
            //                 type: 1,
            //                 title: '详情',
            //                 skin: 'layui-layer-rim', //加上边框
            //                 area: ['800px', '540px'], //宽高
            //                 content: '<div class="lxr-modal"><div class="row">\n' +
            //                 '                            <div class="form-group">\n' +
            //                 '                                <label for="lxr" class="col-sm-2 col-sm-offset-1 control-label text-right">联系人：</label>\n' +
            //                 '                                <div class="col-sm-3">\n' + data.bean.lxr +
            //                 '                                </div>\n' +
            //                 '                                <label for="lxdh" class="col-sm-2 control-label text-right">联系人手机：</label>\n' +
            //                 '                                <div class="col-sm-3">\n' + data.bean.lxdh +
            //                 '                                </div>\n' +
            //                 '                            </div>\n' +
            //                 '                        </div>\n' +
            //                 '                        <div class="row">\n' +
            //                 '                            <div class="form-group">\n' +
            //                 '                                <label for="jkr" class="col-sm-2 col-sm-offset-1 control-label text-right">讲课人：</label>\n' +
            //                 '                                <div class="col-sm-3">\n' + data.bean.jkr +
            //                 '                                </div>\n' +
            //                 '                                <label for="xjsj" class="col-sm-2 control-label text-right">宣讲时间：</label>\n' +
            //                 '                                <div class="col-sm-3">\n' + xjsj +
            //                 '                                </div>\n' +
            //                 '                            </div>\n' +
            //                 '                            </div>\n' +
            //                 '                        <div class="row">\n' +
            //                 '                            <div class="form-group">\n' +
            //                 '                                <label for="xjhsqly" class="col-sm-2 col-sm-offset-1 control-label text-right">申请理由：</label>\n' +
            //                 '                                <div class="col-sm-8">\n' + data.bean.xjhsqly +
            //                 '                                </div>\n' +
            //                 '                            </div>\n' +
            //                 '                            </div>\n' +
            //                 '                        <div class="row">\n' +
            //                 '                            <div class="form-group">\n' +
            //                 '                                <label for="jkrjs" class="col-sm-2 col-sm-offset-1 control-label text-right">讲课人介绍：</label>\n' +
            //                 '                                <div class="col-sm-8">\n' + data.bean.jkrjs +
            //                 '                                </div>\n' +
            //                 '                            </div>\n' +
            //                 '                            </div>\n' +
            //                 '                        <div class="row">\n' +
            //                 '                            <div class="form-group">\n' +
            //                 '                                <label for="jkrjs" class="col-sm-2 col-sm-offset-1 control-label text-right">备注：</label>\n' +
            //                 '                                <div class="col-sm-8">\n' + convertStr(data.bean.memo,'') +
            //                 '                                </div>\n' +
            //                 '                            </div>\n' +
            //                 '                            </div>\n' +xqStr+
            //                 '                        <div class="row btn-yd">\n' +
            //                 '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
            //                 '                                <button class="btn green" onclick="close1()">关闭</button>\n' +
            //                 '                            </div>\n' +
            //                 '                        </div></div>'
            //             });
            //         }
            //     }
            // })
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