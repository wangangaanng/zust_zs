<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
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
                <li><a href="#">首页</a></li>
                <li><a href="#">个人中心</a></li>
                <li class="active">咨询列表</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav">
                <div class="menu-title">
                    <div class="title-chn">学生服务</div>
                    <div class="title-en">STUDENT SERVICE
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
                    </ul>
                </div>
            </div>

        <div class="content-list">
            <div class="ask-list">
                    <#if asklist??>
                        <#list asklist.records as obj>
                    <div class="al-item "><#--active2-->
                        <div class="al-question">
                            <i></i> 我的提问：${obj.wtnr}
                        </div>
                        <#if obj.danr??>
                        <div class="al-answer">
                            <i></i> ${obj.hfName}的回复：${obj.danr}
                        </div>
                        </#if>
                        <div class="al-btn">
                            <button onclick="removeHistoryConsult('${obj.owid}',this)" class="btn">删除</button>
                            <button class="btn">继续咨询</button>
                        </div>
                    <#--<span class="glyphicon glyphicon-menu-up "></span>-->
                    </div>
                        </#list>
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

            </div>
        </div>
        </div>
    </div>

</div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>

<script>

    $(document).ready(function () {
        historyConsult()
        $(".list-group-item").click(function(e) {
            var index=$(this).index()
            window.location.href="/stuCenter/"+index
        })
    })

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