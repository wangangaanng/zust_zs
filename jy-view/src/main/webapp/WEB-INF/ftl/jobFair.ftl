<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/bootstrap-table.min.css" />
</head>
<style>
    .step2-title{
        text-align: center;
        font-size: 24px;
        color: #292929;
        margin-top: 30px;
    }
    .zph-d{
        margin-top: 15px;
    }
    .zph-d .row{
        padding: 5px 0;
    }
    .layui-layer-content{overflow-y:auto !important;}

</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i> 当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a href="${base}/">首页</a></li>
                <li><a href="${base}/enterpriseService/3">企业服务</a></li>
                <li class="active">招聘会申请</li>
            </ol>
        </div>
        <div class="content">
            <div class="jf-content">
                <div class="jf-steps">
                     <#if step=='0'>
                    <div class="jf-items">
                        <div class="jf-item jf-active">1、招聘会列表
                            <div class="jf-box"></div>
                            <div class="jf-box1"></div>
                            <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item ">2、预定展位
                            <div class="jf-box"></div>
                            <div class="jf-box1"></div>
                            <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item ">3、完成</div>
                    </div>
                    <#elseif step=='1'>
                    <div class="jf-items">
                        <div class="jf-item jf-active">1、招聘会列表
                        <div class="jf-box"></div>
                        <div class="jf-box1"></div>
                        <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item jf-active">2、预定展位
                        <div class="jf-box"></div>
                        <div class="jf-box1"></div>
                        <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item ">3、完成</div>
                    </div>
                        <div class="step2-title">${(oneJob.zwbt)!''}招聘会火热报名中。。。</div>
                     <#elseif step=='2'>
                    <div class="jf-items">
                        <div class="jf-item jf-active">1、招聘会列表
                        <div class="jf-box"></div>
                        <div class="jf-box1"></div>
                        <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item jf-active">2、预定展位
                        <div class="jf-box"></div>
                        <div class="jf-box1"></div>
                        <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item jf-active">3、完成</div>
                    </div>
                    </#if>
                            <#--<#if (step==1)>-->
                                <#--<div class="jf-item jf-active">1、招聘会列表-->
                                    <#--<div class="jf-box"></div>-->
                                    <#--<div class="jf-box1"></div>-->
                                    <#--<div class="jf-box2"></div>-->
                                <#--</div>-->
                                <#--<div class="jf-item jf-active">2、预定展位-->
                                    <#--<div class="jf-box"></div>-->
                                    <#--<div class="jf-box1"></div>-->
                                    <#--<div class="jf-box2"></div>-->
                                <#--</div>-->
                                <#--<div class="jf-item ">3、完成</div>-->
                            <#--</#if>-->
                            <#--<#if (step==2)>-->
                                <#--<div class="jf-item jf-active">1、招聘会列表-->
                                    <#--<div class="jf-box"></div>-->
                                    <#--<div class="jf-box1"></div>-->
                                    <#--<div class="jf-box2"></div>-->
                                <#--</div>-->
                                <#--<div class="jf-item jf-active">2、预定展位-->
                                    <#--<div class="jf-box"></div>-->
                                    <#--<div class="jf-box1"></div>-->
                                    <#--<div class="jf-box2"></div>-->
                                <#--</div>-->
                                <#--<div class="jf-item jf-active">3、完成</div>-->
                        <#--</#if>-->
                    </div>

                <#if step=='0'>
                <div>
                    <div class="news-list">
                        <div class="e-table">
                            <table class="table table-hover" data-locale="zh-CN" id="table-zph" style="table-layout: fixed;
                          word-break:break-all; word-wrap:break-all;">
                            </table>

                        </div>
                    </div>
                </div>
                <#elseif step=='1'>
                    <div class="zph-d">
                        <div class="row">
                            <div class="col-md-4 col-md-offset-1">举办日期：${((oneJob.zphKsrq)?substring(0,10))!'暂无'}</div>
                            <div class="col-md-4 col-md-offset-1">举办城市：${(oneJob.zwPro)!""}${(oneJob.zwCity)!""}${(oneJob.zwArea)!""}</div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 col-md-offset-1">预定截止日期：${((oneJob.zphBmjzsj)?substring(0,10))!'暂无'}</div>
                            <div class="col-md-4 col-md-offset-1">举办地址：${(oneJob.zphJbdd)!"暂无"}</div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 col-md-offset-1">主办方：${(oneJob.zphJbf)!"暂无"}</div>
                            <div class="col-md-4 col-md-offset-1">承办方：${(oneJob.zphCbf)!"暂无"}</div>
                        </div>
                        <div class="row btn-yd">
                            <div class="col-md-8 col-md-offset-1 text-center">
                                <button class="btn green" style="width: 120px;" onclick="order()">预定展位</button>
                            </div>
                        </div>

                        <div>

                        </div>
                    </div>
                <#elseif step=='2'>
                <div class="jf-result" >
                    <div class="jf-tips">
                        <div class="jf-tip-icon">
                            <i></i>
                        </div>
                        <div class="jf-tip-text">
                            <div>申请资料提交成功，您的申请一般在24小时内审核完成，</div>
                            <div>将以电话的方式通知审核结果</div>
                            <div>申请遇到问题请咨询浙江科技学院学生处就业指导中心:0571-85121710</div>
                        </div>
                    </div>
                </div>

                </#if>
            </div>
            </div>

        </div>

    </div>
</div>
<#if zphOwid??>
<input type="hidden" value="${zphOwid!''}" id="zphOwid" />
</#if>
<input type="hidden" value="${step!''}" id="current-step" />
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<script>

var pageNo=1;
var pageSize=10;
    $(document).ready(function () {

        if($("#current-step").val()==0){
            myJobList1()
        }else if($("#current-step").val()==1){
            zphtjList()
        }

    })


    var zdytjObj={};
    var zdytjStr='';
    var zdytjLength=0;
    function zphtjList() {
        var jsonObj ={
            "owid":$("#zphOwid").val()
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
                }
            }else{
                walert(data.errorMess)
            }
        })
    }

    var layer1;
    function order() {
        var qyLxr=JSON.parse(getCookie("qyInfo")).qyLxr || '';
        var qyLxrdh=JSON.parse(getCookie("qyInfo")).qyLxrdh || '';
        layer1=layer.open({
            type: 1,
            title:'招聘信息',
            scrollbar: false,
            skin: 'layui-layer-rim', //加上边框
            area: ['800px', '700px'], //宽高
            content: '<div class="lxr-modal">\n' +
            '                        <div class="row">\n'+
            '                           <div class="form-group">\n' +
            '                                <label for="lxr" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">联系人<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-3" style="padding:0;">\n' +
            '                                    <input type="text" class="form-control" id="lxr" name="lxr" value="'+qyLxr+'" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                                <label for="lxdh" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">联系人手机号<span class="red">*</span>：</label>\n' +
            '                                <div class="col-sm-2" style="padding:0;">\n' +
            '                                    <input type="text" class="form-control" id="lxdh" name="lxdh" value="'+qyLxrdh+'" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +zdytjStr+
            '                        <div class="row">\n'+
            '                           <div class="form-group">\n' +
            '                                <label for="zw1" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
            '                                <div class="col-sm-3" style="padding:0;">\n' +
            '                                    <input type="text" class="form-control" id="zw1" name="zw1" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                                <label for="rs1" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
            '                                <div class="col-sm-2" style="padding:0;">\n' +
            '                                    <input type="number" class="form-control" id="rs1" name="rs1" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n'+
            '                           <div class="form-group">\n' +
            '                                <label for="zw2" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
            '                                <div class="col-sm-3" style="padding:0;">\n' +
            '                                    <input type="text" class="form-control" id="zw2" name="zw2" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                                <label for="rs2" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
            '                                <div class="col-sm-2" style="padding:0;">\n' +
            '                                    <input type="number" class="form-control" id="rs2" name="rs2" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n'+
            '                           <div class="form-group">\n' +
            '                                <label for="zw3" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
            '                                <div class="col-sm-3" style="padding:0;">\n' +
            '                                    <input type="text" class="form-control" id="zw3" name="zw3" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                                <label for="rs3" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
            '                                <div class="col-sm-2" style="padding:0;">\n' +
            '                                    <input type="number" class="form-control" id="rs3" name="rs3" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n'+
            '                           <div class="form-group">\n' +
            '                                <label for="zw4" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
            '                                <div class="col-sm-3" style="padding:0;">\n' +
            '                                    <input type="text" class="form-control" id="zw4" name="zw4" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                                <label for="rs4" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
            '                                <div class="col-sm-2" style="padding:0;">\n' +
            '                                    <input type="number" class="form-control" id="rs4" name="rs4" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row">\n'+
            '                           <div class="form-group">\n' +
            '                                <label for="zw5" class="col-sm-4 control-label text-right" style="line-height: 34px;padding-right: 0;">岗位：</label>\n' +
            '                                <div class="col-sm-3" style="padding:0;">\n' +
            '                                    <input type="text" class="form-control" id="zw5" name="zw5" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                                <label for="rs5" class="col-sm-2 control-label text-right" style="line-height: 34px;padding-right: 0;">招聘人数：</label>\n' +
            '                                <div class="col-sm-2" style="padding:0;">\n' +
            '                                    <input type="number" class="form-control" id="rs5" name="rs5" value="" placeholder="" autocomplete="off">\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="row btn-yd"><div class="col-md-9 col-sm-offset-1 text-center">\n' +
            '                                <button class="btn green" style="width: 120px;" onclick="confirmQd()">确定</button>\n' +
            '                            </div>\n' +
            '                        </div></div>'
        });


    }

    function confirmQd() {
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
                "jobRefOwid": $("#zphOwid").val(),
                "bmlx": 0,
                "bmdx": 0,
                "qyxxRefOwid": getCookie("qyOwid"),
                "lxr": $("#lxr").val().trim(),
                "lxdh": $("#lxdh").val().trim()
            }
            for(var i=1;i<zdytjLength+1;i++){
                jsonObj['zdytj'+i]=zdytjObj['zdytj'+i]
                jsonObj['tjsd'+i]=$("#tjsd"+i).val()
            }
            for(var i=1;i<6;i++){
                jsonObj['zw'+i]=$("#zw"+i).val()
                jsonObj['rs'+i]=$("#rs"+i).val()
            }
            ajax("zustjy/bckjBizJybm/applyJob", jsonObj, function (data) {
                if (data.backCode == 0) {
                    layer.close(layer1)
                    layer1 = null;
                <#--window.location.href="${base}/"+url-->
                    window.location.href = "${base}/jobFair/2"
                } else {
                    walert(data.errorMess)
                }
            })
        }
    }
    function myJobList1() {

        initTable1()
    }
    function initTable1(){
        $('#table-zph').bootstrapTable('destroy');
        $('#table-zph').bootstrapTable({
            ajax:function(request) {
                ajax("zustjy/bckjBizJob/myJobList", {
                    "zwlx":3,
                    "zphSfbm":1,
                    "pageSize":$('#table-zph').bootstrapTable('getOptions').pageSize || 10,
                    "pageNo":$('#table-zph').bootstrapTable('getOptions').pageNumber || 1
                }, function (data) {
                    if(data.backCode==0){
                        request.success({
                            row : convertStr(data.bean.records,[]),
                            total: data.bean.totalCount
                        });

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
            queryParamsType:"limit",
            columns: [{
                align : 'center',
                field: 'zwbt',
                title: '招聘会名称',
                events: window.operateEvents,
                formatter: operateFormatterZph2
            }, {
                align : 'center',
                field: 'zwArea',
                title: '城市',
                formatter:function(value,row,index){
                    var value=convertStr(row.zwCity,"-")
                    return value;
                }
            }, {
                align : 'center',
                field: 'zphJbdd',
                title: '举办地址',
            }, {
                field: 'zphKsrq',
                title: '举办时间',
                align : 'center',
                formatter:function(value,row,index){
                    var value=row.zphKsrq.substring(0,10);
                    return value;
                }
            }, {
                align : 'center',
                field: 'owid',
                title: '操作',
                events: window.operateEvents,
                formatter: operateFormatterZph
            }], //列设置

        });

    }


    function operateFormatterZph(value, row, index) {
        var str='';

        if(row.state==2){
            if(row.zphSfbm==0){
                str= '-';
            }else if(row.zphSfbm==1){
                str= '<a class="green-color order" href="#">预定展位</a> ';
            }

        }else{
            str= '';
        }

        if(row.zphBmjzsj && compareToday(row.zphBmjzsj)){
            str= '<span style="color: #ccc;">已截止报名</span> ';
        }
        if(row.zphKsrq && compareToday(row.zphKsrq)){
            str= '<span style="color:red;">已结束</span> ';
        }

        return str;
    }

function operateFormatterZph2(value, row, index) {
    var c = '<a class="green-color zphxq" href="#">'+row.zwbt+'</a> ';
    return c;
}

    window.operateEvents = {
        'click .order': function (e, value, row, index) {
            window.location.href="${base}/jobFair/1/"+row.owid
        },
        'click .zphxq': function (e, value, row, index) {
            if(!isTimeOut()) {
                window.open("${base}/positionDetail/" + row.owid)
            }
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

</script>
</body>

</html>