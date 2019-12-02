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

var curProv='';
var curCity='';

$(document).ready(function () {
    curProv=$("#curProv").val();
    curCity=$("#curCity").val();

    $(".list-group-item").click(function(e) {
        var index=$(this).index()
        window.location.href=base+"/enterpriseService/"+index
    })
    if(!isTimeOut()) {
        myJobList()
    }

})

var E, editor;
function addZpgg() {
    if(!isTimeOut()) {

        layer1=layer.open({
            type: 1,
            title:'招聘公告信息',
            scrollbar: false,
            skin: 'layui-layer-rim', //加上边框
            area: ['1000px', '750px'], //宽高
            content: '<div class="lxr-modal">' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="zwbt" class="col-sm-2 control-label text-right" style="line-height: 34px;">标题<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-10">\n' +
            '                                    <input type="text" class="form-control" id="zwbt" name="zwbt" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="zwPro" class="col-sm-2 control-label text-right" style="line-height: 34px;">所在省<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-4">\n' +
            '                                    <select class="form-control" onchange="getCity()" name="zwPro" id="zwPro"><option value="">请选择</option></select>\n' +
            '                                </div>\n' +
            '                                <label for="zwCity" class="col-sm-2 control-label text-right" style="line-height: 34px;">所在市<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-4" style="position: relative;">\n' +
            '                                    <select class="form-control"  name="zwCity" id="zwCity"><option value="">请选择</option></select>\n' +
            '                            </div>\n' +
            '                            </div>\n' +
            '                            </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="zwMxxy" class="col-sm-2 control-label text-right" style="line-height: 34px;">面向学院：</label>\n' +
            '                                <div class="col-sm-4">\n' +
            '                                    <select  class="form-control" id="zwMxxy"  name="zwMxxy" onchange="getZyList1()"><option value="">请选择</option></select>\n' +
            '                                </div>\n' +
            '                                <label for="zwMxzy" class="col-sm-2 control-label text-right" style="line-height: 34px;">面向专业：</label>\n' +
            '                                <div class="col-sm-4">\n' +
            '                                    <select  class="form-control" id="zwMxzy"  name="zwMxzy" ><option value="">请选择</option></select>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                            </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="zwgjz" class="col-sm-2 control-label text-right" style="line-height: 34px;">关键字：</label>\n' +
            '                                <div class="col-sm-10">\n' +
            '                                    <input type="text" class="form-control" id="zwgjz" name="zwgjz" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n' +
            '                            <div class="form-group">\n' +
            '                                <label for="memo" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-left: 0;">详细介绍<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-10">\n' +
            '<div id="div1" style="max-height: 330px;">\n' +
            '</div><p style="color: red;margin-top: 5px;">注：请控制简介内容宽度在输入框范围之内，否则会影响展示</p>\n' +
            // '                                    <textarea  class="form-control" id="memo" style="resize: none;" rows="6" name="memo" placeholder="" autocomplete="off"></textarea>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                            </div>\n' +
            '                        <div class="row btn-yd">\n' +
            '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
            '                                <button class="btn green" style="width: 120px;" onclick="confirmQd()">提交</button>\n' +
            '                            </div>\n' +
            '                        </div></div>'
        });

        E = window.wangEditor
        editor = new E('#div1')
        // 忽略粘贴内容中的图片
        editor.customConfig.pasteIgnoreImg = true
        editor.customConfig.menus = [
            'head',  // 标题
            'bold',  // 粗体
            'fontSize',  // 字号
            // 'fontName',  // 字体
            // 'italic',  // 斜体
            // 'underline',  // 下划线
            // 'strikeThrough',  // 删除线
            // 'foreColor',  // 文字颜色
            // 'backColor',  // 背景颜色
            // 'link',  // 插入链接
            // 'list',  // 列表
            'justify',  // 对齐方式
            // 'quote',  // 引用
            // 'emoticon',  // 表情
            // 'image',  // 插入图片
            // 'table',  // 表格
            // 'video',  // 插入视频
            // 'code',  // 插入代码
            'undo',  // 撤销
            // 'redo'  // 重复
        ]
        editor.create()

    }

    cityData3.forEach(function(e) {
        provice.push(e.text)
        city.push(e.children)
        if(curProv==e.text) {
            $("#zwPro").append("<option value='" + e.text + "' selected>" + e.text + "</option>")
        }else{
            $("#zwPro").append("<option value='" + e.text + "' >" + e.text + "</option>")
        }
    });

    pindex=parseInt($("#zwPro option:selected").index())-1;
    if(pindex>-1){
        _cityData=city[pindex]
        _cityData.forEach(function(e) {
            if(curCity==e.text){
                $("#zwCity").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
            }else{
                $("#zwCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
            }

        });

    }
    // cindex=parseInt($("#zwCity option:selected").index())-1;
    // if(cindex>-1){
    //     var _areaData=_cityData[cindex].children
    //     _areaData.forEach(function(e) {
    //         if($("#zwArea").attr("data-val")==e.text){
    //             $("#zwArea").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
    //         }else{
    //             $("#zwArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
    //         }
    //     });
    //
    // }

    getZyList()
}


function confirmQd() {
    if(!isTimeOut()){
        if(!$("#zwbt").val().trim()){
            walert("请填写标题")
            return
        }
        if(!$("#zwPro").val()){
            walert("请选择所在省")
            return
        }
        if(!$("#zwCity").val().trim()){
            walert("请选择所在市")
            return
        }
        if(!editor.txt.text().trim()){
            walert("请填写详细介绍")
            return
        }else{
            if(editor.txt.text().trim().length<50){
                walert("详细介绍不得少于50个字")
                return
            }
        }

        var jsonObj ={
            "zwlx":2,
            "qyxxRefOwid":getCookie("qyOwid"),
            "zwbt":$("#zwbt").val().trim(),
            "zwPro":$("#zwPro").val(),
            "zwCity":$("#zwCity").val(),
            "zwgjz":$("#zwgjz").val(),
            "zwMxxy":$("#zwMxxy option:selected").attr("data-mz"),
            "zwMxzy":$("#zwMxzy option:selected").attr("data-mz"),
            "memo":editor.txt.html()//$("#memo").val(),

        }

        ajax("zustjy/bckjBizJob/addOneJob", jsonObj, function (data) {
            if(data.backCode==0){
                layer.close(layer1)
                layer1=null;
                layer.open({
                    title:'提示',
                    closeBtn: 0,
                    scrollbar: false,
                    content: '发布成功',
                    yes: function(index, layero){
                        layer.close(index);
                        searchZw()
                    }
                });
            }else{
                walert(data.errorMess)
            }
        })
    }
}

function getZyList() {
    if(!isTimeOut()){
        var jsonObj = {
            "parentId":'-1'
        }
        ajax("zustcommon/bckjBizXyzy/getZyList", jsonObj, function (data) {
            if(data.backCode==0){
                if(data.bean && data.bean.length>0){
                    var str='';
                    for(var i=0;i<data.bean.length;i++){
                        str+='<option data-mz="'+data.bean[i].mz+'" value="'+data.bean[i].owid+'">'+data.bean[i].mz+'</option>'
                    }
                    $("#zwMxxy").append(str)
                }
            }else{
                walert(data.errorMess)
            }
        })
    }
}

function getZyList1() {
    if(!isTimeOut()){
        if(!$("#zwMxxy").val()){
            $("#zwMxzy").html('<option value="">请选择</option>')
            return
        }
        var jsonObj = {
            "parentId":$("#zwMxxy").val()
        }
        ajax("zustcommon/bckjBizXyzy/getZyList", jsonObj, function (data) {
            if(data.backCode==0){
                if(data.bean && data.bean.length>0){
                    var str='';
                    for(var i=0;i<data.bean.length;i++){
                        str+='<option data-mz="'+data.bean[i].mz+'" value="'+data.bean[i].owid+'">'+data.bean[i].mz+'</option>'
                    }
                    $("#zwMxzy").append(str)
                }
            }else{
                walert(data.errorMess)
            }
        })
    }
}

function keyLogin(){
    if (event.keyCode==13){
        searchZw()
    }
}

function searchZw() {
    var key=$("#zwbt-zw").val().trim()
    if(testSql(key,$("#zwbt-zw"))){
        if(!isTimeOut()) {
            $("#table-job").bootstrapTable('refresh', {pageNumber: 1});
        }
    }
}

function myJobList() {
    if(!isTimeOut()) {
        AntiSqlValidAll(["#zwbt-zw"], function () {
            $('#table-job').bootstrapTable('destroy');
            $('#table-job').bootstrapTable({
                ajax: function (request) {
                    ajax("zustjy/bckjBizJob/myJobList", {
                        "zwbt": $("#zwbt-zw").val().trim(),
                        "qyxxRefOwid": getCookie("qyOwid"),
                        "zwlx": 2,
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
                    field: 'zwbt',
                    title: '名称',
                }, {
                    field: 'createtime',
                    title: '发布时间',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var value = row.createtime.substring(0, 10);
                        return value;
                    }
                }, {
                    align: 'center',
                    events: 'operateEvents',
                    field: 'owid',
                    title: '操作',
                    events: window.operateEvents,
                    formatter: operateFormatterZw
                }], //列设置

            });
        });
    }
}

function operateFormatterZw(value, row, index) {
    var c = '<a class="green-color detail" href="#"  οnclick="info(\''
        + row.owid
        + '\')">查看</a> ';
    var d = '<a class="green-color remove" style="color: red;" href="#"  οnclick="info(\''
        + row.owid
        + '\')">删除</a> ';

    return c+d;
}

function closeZpgg() {
    layer.close(layer2)
}

var layer2;
window.operateEvents = {
    'click .detail': function (e, value, row, index) {
        if(!isTimeOut()) {
            var jsonObj = {
                "yhOwid": getCookie("qyOwid"),
                "owid": row.owid
            }
            ajax("zustjy/bckjBizJob/getOneJob", jsonObj, function (data) {
                if (data.backCode == 0) {
                    if (data.bean) {
                        var str='';
                        if(data.bean.zwMxxy && data.bean.zwMxzy){
                            str+='                        <div class="row">\n' +
                                '                            <div class="form-group">\n' +
                                '                                <label for="zwMxxy" class="col-sm-2 control-label text-right" style="line-height: 34px;">面向学院：</label>\n' +
                                '                                <div class="col-sm-4" style="line-height: 34px;">\n'  +data.bean.zwMxxy+
                                '                                </div>\n' +
                                '                                <label for="zwMxzy" class="col-sm-2 control-label text-right" style="line-height: 34px;">面向专业：</label>\n' +
                                '                                <div class="col-sm-4" style="line-height: 34px;">\n'  +data.bean.zwMxzy+
                                '                                </div>\n' +
                                '                            </div>\n' +
                                '                            </div>\n'
                        }
                        if(data.bean.zwMxxy && !data.bean.zwMxzy){
                            str+='                        <div class="row">\n' +
                                '                            <div class="form-group">\n' +
                                '                                <label for="zwMxxy" class="col-sm-2 control-label text-right" style="line-height: 34px;">面向学院：</label>\n' +
                                '                                <div class="col-sm-4" style="line-height: 34px;">\n'  +data.bean.zwMxxy+
                                '                                </div>\n' +
                                '                            </div>\n' +
                                '                            </div>\n'
                        }
                        if(data.bean.zwgjz){
                            str+='                        <div class="row">\n' +
                                '                            <div class="form-group">\n' +
                                '                                <label for="zwgjz" class="col-sm-2 control-label text-right" style="line-height: 34px;">关键字：</label>\n' +
                                '                                <div class="col-sm-10" style="line-height: 34px;">\n'  +data.bean.zwgjz+
                                '                                </div>\n' +
                                '                            </div>\n' +
                                '                        </div>\n'
                        }

                        layer2=layer.open({
                            type: 1,
                            title:'招聘公告信息',
                            scrollbar: false,
                            skin: 'layui-layer-rim', //加上边框
                            area: ['1000px', '720px'], //宽高
                            content: '<div class="lxr-modal">' +
                            '                        <div class="row">\n' +
                            '                            <div class="form-group">\n' +
                            '                                <label for="zwbt" class="col-sm-2 control-label text-right" style="line-height: 34px;">标题：</label>\n' +
                            '                                <div class="col-sm-10" style="line-height: 34px;">\n' +data.bean.zwbt+
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                        <div class="row">\n' +
                            '                            <div class="form-group">\n' +
                            '                                <label for="zwPro" class="col-sm-2 control-label text-right" style="line-height: 34px;">所在省：</label>\n' +
                            '                                <div class="col-sm-4" style="line-height: 34px;">\n' +data.bean.zwPro+
                            '                                </div>\n' +
                            '                                <label for="zwCity" class="col-sm-2 control-label text-right" style="line-height: 34px;">所在市：</label>\n' +
                            '                                <div class="col-sm-4" style="position: relative;line-height: 34px;">\n'  +data.bean.zwCity+
                            '                            </div>\n' +
                            '                            </div>\n' +
                            '                            </div>\n' +str+
                            '                        <div class="row">\n' +
                            '                            <div class="form-group">\n' +
                            '                                <label for="memo" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-left: 0;">详细介绍：</label>\n' +
                            '                                <div class="col-sm-10" style="line-height: 34px;">\n'  +data.bean.memo+
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                            </div>\n' +
                            '                        <div class="row btn-yd">\n' +
                            '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
                            '                                <button class="btn green" style="width: 120px;" onclick="closeZpgg()">关闭</button>\n' +
                            '                            </div>\n' +
                            '                        </div></div>'
                        });

                    }
                } else {
                    walert(data.errorMess)
                }
            })
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
