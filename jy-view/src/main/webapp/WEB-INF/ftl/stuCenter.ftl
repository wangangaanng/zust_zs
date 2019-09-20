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

    .teacher-item .t-bg {
        width: 100%;
        height: 260px;
        background: url("${base}/img/teacher.png") 100% no-repeat;
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
                            <span class="ic-menu"></span> 报名预约
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
                        <div class="teacher-item">
                            <div class="t-bg"></div>
                            <div class="teacher-detail">
                                <div class="t-name">张立庆</div>
                                <div class="t-xhx"><span></span></div>
                                <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>
                                <div class="t-btn">
                                    <button class="btn t-zx">我要咨询</button>
                                    <button class="btn t-xq">查看详情</button>
                                </div>
                            </div>
                        </div>
                        <div class="teacher-item">
                            <div class="t-bg"></div>
                            <div class="teacher-detail">
                                <div class="t-name">张立庆</div>
                                <div class="t-xhx"><span></span></div>
                                <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>
                                <div class="t-btn">
                                    <button class="btn t-zx">我要咨询</button>
                                    <button class="btn t-xq">查看详情</button>
                                </div>
                            </div>
                        </div>
                        <div class="teacher-item">
                            <div class="t-bg"></div>
                            <div class="teacher-detail">
                                <div class="t-name">张立庆</div>
                                <div class="t-xhx"><span></span></div>
                                <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>
                                <div class="t-btn">
                                    <button class="btn t-zx">我要咨询</button>
                                    <button class="btn t-xq">查看详情</button>
                                </div>
                            </div>
                        </div>
                        <div class="teacher-item">
                            <div class="t-bg"></div>
                            <div class="teacher-detail">
                                <div class="t-name">张立庆</div>
                                <div class="t-xhx"><span></span></div>
                                <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>
                                <div class="t-btn">
                                    <button class="btn t-zx">我要咨询</button>
                                    <button class="btn t-xq">查看详情</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>

<script>

    $(document).ready(function () {
        supervisorList()
    })

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
    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        $(".content-list").eq($(this).index()).show();
    })
</script>
</body>

</html>