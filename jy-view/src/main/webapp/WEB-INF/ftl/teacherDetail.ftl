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
    .zx-textarea{
        padding: 15px 20px;
        width: 100%;
        height: 100%;
    }
    .zx-textarea textarea{
        width: 100%;
        height: 190px;
        resize:none;
        border-radius: 5px;
    }
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
                <li><a href="#">学生服务</a></li>
                <li class="active">导师简介</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav" style="border: none;">
                <div class="td-item">
                    <div class="t-bg" style="background: url("${detail.zjtx}")"></div>
                    <div class="td-detail">
                        <div class="t-name">${detail.zjxm}</div>
                        <div class="t-xhx"><span></span></div>
                        <div class="t-sm">${detail.zjxx}</div>

                    </div>
                </div>
            </div>

            <div class="content-list" style="min-height: 707px;height:auto;">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">导师简介</div>
                    </div>
                    <div class="teacher-list">
                        <p class="td-sm">
                            ${detail.zjxx}
                        </p>
                        <div class="td-ask">
                            <button class="btn" onclick="question()">我要咨询</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<input type="hidden" id="teacherOwid" value="${towid}" />
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script>
    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        $(".content-list").eq($(this).index()).show();
    })

    $(document).ready(function () {


    })

    function question() {
        layer.open({
            type: 1,
            title:'填写咨询内容',
            btn:["提交","取消"],
            skin: 'layui-layer-rim', //加上边框
            area: ['450px', '320px'], //宽高
            content: '<div class="zx-textarea"><textarea id="wtnr"></textarea></div>',
            yes:function(index){
                ask(index);
            }
        })
    }

    function ask(index) {
        var jsonObj={
            "wtnr":$("#wtnr").val(),
            "owid": $("#teacherOwid").val(),
            "zxlx": 2,
            "studentOwid": getCookie("stuOwid")
        }
        ajax("zustcommon/bckjBizZxzx/consult", jsonObj, function (data) {
            if(data.backCode==0){
                layer.close(index)
            }
        })
    }

</script>
</body>

</html>