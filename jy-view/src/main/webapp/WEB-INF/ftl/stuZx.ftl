<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>
<style>

    /*.teacher-item .t-bg {
        width: 100%;
        height: 260px;
        background: url("${base}/img/teacher.png") 100% no-repeat;
    }*/

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
                <li><a href="#">个人中心</a></li>
                <li class="active">咨询列表</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav">
                <div class="menu-title">
                    <div class="title-chn">个人中心</div>
                    <div class="title-en">INFORMATION
                        <div class="menu-nav-icon"></div>
                    </div>

                </div>
                <div class="menu-list">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 导师咨询
                        </li>
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 咨询列表
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 报名预约
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 我的收藏
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 生源信息
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 就业方案
                        </li>
                    </ul>
                </div>
            </div>

        <div class="content-list">
            <div class="ask-list">
                    <#if (asklist??)&&(asklist.records??)&&(asklist.records?size>0)>
                            <#assign flag=1>
                        <#list asklist.records as obj>
                    <div class="al-item "><#--active2-->
                        <div class="al-question">
                            <i></i> 我的提问：${obj.wtnr}
                            <div class="pull-right twrq">${obj.twrq}</div>
                        </div>
                        <#--<#if obj.danr??>-->
                        <div class="al-answer">
                            <i></i> ${obj.zjxm}的回复：${obj.danr!"暂无回复"}
                        </div>
                        <#--</#if>-->
                       <#-- <div class="al-btn">
                            <button onclick="removeHistoryConsult('${obj.owid}',this)" class="btn">删除</button>
                            <#if obj.hfOwid??>
                            <button class="btn" onclick="question('${obj.hfOwid}')">继续咨询</button>
                            </#if>
                        </div>-->
                    <#--<span class="glyphicon glyphicon-menu-up "></span>-->
                    </div>
                        </#list>
                    <#else >
                        <#assign flag=0>
                    </#if>

            <#--<div class="al-item">-->
            <#--<div class="al-question">-->
            <#--<i></i> 我的提问：张老师，就业需要准备什么材料?-->
            <#--</div>-->
            <#--<div class="al-answer">-->
            <#--<i></i> 张老师的回复：简历，毕业证书等等。。-->
            <#--</div>-->
            <#--<span class="glyphicon glyphicon-menu-down "></span>-->
            <#--</div>-->
                <div class="text-center">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">

                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        </div>
    </div>

</div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-paginator.min.js" type="text/javascript"></script>
<script>
    var currentPage="${asklist.currentPage!'1'}"
    $(document).ready(function () {
        if("${flag}"==0){
            $(".content-list").append(nulltip)
        }else {
            setPage(currentPage, "${asklist.totalPage!'1'}", function () {
                openUrl('stuCenter/1/' + currentPage)
            })
        }
        historyConsult()
        $(".list-group-item").click(function(e) {
            var index=$(this).index()
            window.location.href=base+"/stuCenter/"+index
        })
    })


    function setPage(pageCurrent, pageSum, callback) {
        $(".pagination").bootstrapPaginator({
            //设置版本号
            bootstrapMajorVersion: 3,
            // 显示第几页
            currentPage: pageCurrent,
            // 总页数
            totalPages: pageSum,
            //当单击操作按钮的时候, 执行该函数, 调用ajax渲染页面
            onPageClicked: function (event,originalEvent,type,page) {
                // 把当前点击的页码赋值给currentPage, 调用ajax,渲染页面
                currentPage = page
                callback && callback()
            }
        })
    }

    function question(o) {
        layer.open({
            type: 1,
            title:'填写咨询内容',
            btn:["提交","取消"],
            skin: 'layui-layer-rim', //加上边框
            area: ['450px', '320px'], //宽高
            content: '<div class="zx-textarea"><textarea id="wtnr"></textarea></div>',
            yes:function(index){
                ask(index,o);
            }
        })
    }

    function ask(index) {
        if(!isTimeOut()) {
            if (!$("#wtnr").val()) {
                walert("请填写咨询内容");
                return;
            }
            var jsonObj = {
                "wtnr": $("#wtnr").val(),
                "owid": o,
                "zxlx": 2,
                "studentOwid": getCookie("stuOwid")
            }
            ajax("zustcommon/bckjBizZxzx/consult", jsonObj, function (data) {
                if (data.backCode == 0) {
                    layer.close(index)
                    layer.open({
                        title: '提示',
                        content: '咨询已提交，请等待回复。',
                        yes: function (index, layero) {
                            layer.close(index);
                        }
                    });
                }
            })
        }
    }

    function removeHistoryConsult(a,obj){
        layer.open({
            title:'提示',
            content: '确定要删除该条记录？',
            yes: function(index, layero){
                var jsonObj ={
                    "owid":a
                }
                ajax("zustcommon/bckjBizZxzx/removeHistoryConsult", jsonObj, function (data) {
                    if(data.backCode==0){
                        walert("删除成功");
                        $(obj).parent().parent().remove();
                        layer.close(index);
                    }else{
                        walert(data.errorMess)
                    }
                })

            }
        });

    }

    function historyConsult(){
        var jsonObj ={
            "pageSize":10,
            "pageNo":1,
            "zxlx":2,
            "twOwid":getCookie("stuOwid")
        }
        ajax("zustcommon/bckjBizZxzx/historyConsult", jsonObj, function (data) {
            if(data.backCode==0){
            <#--window.location.href="${base}/"+url-->
            <#--window.location.href="${base}/jobFair/2"-->
            }else{
                walert(data.errorMess)
            }
        })
    }
    function supervisorList(){
        var jsonObj ={
            "pageSize":10,
            "pageNo":1
        }
        ajax("zustjy/bckjBizZjzx/supervisorList", jsonObj, function (data) {
            if(data.backCode==0){
            <#--window.location.href="${base}/"+url-->
            <#--window.location.href="${base}/jobFair/2"-->
            }else{
                walert(data.errorMess)
            }
        })
    }
</script>
</body>

</html>