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
                <li class="active">导师咨询</li>
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
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 导师咨询
                        </li>
                        <li class="list-group-item">
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
            <div class="content-list" style="min-height: 707px;height:auto;">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">指导老师</div>
                    </div>
                <div class="teacher-list">
                <#if tlist??>
                    <#list tlist.records as obj>
                        <div class="teacher-item">
                            <div class="t-bg" style="background: url("${obj.zjtx}")"></div>
                        <div class="teacher-detail">
                            <div class="t-name">${obj.zjxm}</div>
                            <div class="t-xhx"><span></span></div>
                            <div class="t-sm">${obj.zjxx}</div>
                            <div class="t-btn">
                                <button class="btn t-zx green" onclick="question('${obj.owid}')">我要咨询</button>
                                <button class="btn t-xq" onclick="window.location.href='/teacherDetail/${obj.owid}'">查看详情</button>
                            </div>
                        </div>
                    </div>
                    </#list>
                </#if>

                </div>
            </div>
        </div>

        </div>

    </div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>

<script>

    $(document).ready(function () {
        $(".list-group-item").click(function(e) {
            var index=$(this).index()
            window.location.href="/stuCenter/"+index
        })
    })


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
        if(!$("#wtnr").val()){
            walert("请填写咨询内容");
            return;
        }
        var jsonObj={
            "wtnr":$("#wtnr").val(),
            "owid": o,
            "zxlx": 2,
            "studentOwid": getCookie("stuOwid")
        }
        ajax("zustcommon/bckjBizZxzx/consult", jsonObj, function (data) {
            if(data.backCode==0){
                layer.close(index)
                layer.open({
                    title:'提示',
                    content: '咨询已提交，请等待回复。',
                    yes: function(index, layero){
                        layer.close(index);
                    }
                });
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