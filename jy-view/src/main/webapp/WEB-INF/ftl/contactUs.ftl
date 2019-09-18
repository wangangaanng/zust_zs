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
                <li><a href="#">联系我们</a></li>
                <li class="active">联系我们</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav">
                <div class="menu-title">
                    <div class="title-chn">联系我们</div>
                    <div class="title-en">CONTACT US
                        <div class="menu-nav-icon"></div>
                    </div>

                </div>
                <div class="menu-list">
                    <ul class="list-group">
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 联系我们
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 留言咨询
                        </li>
                    </ul>
                </div>
            </div>

            <div class="content-list" style="height: auto;">
                <div class="article-detail" style="border: none;">
                    <div class="article-detail-title">
                        <div class="h3">联系我们</div>
                        <div><span class="label1">作者：</span>浙江科技学院 &nbsp;&nbsp;&nbsp;<span class="label1">提交时间：</span>2019/4/28 20:04:08 &nbsp;&nbsp;&nbsp;<span class="label1">浏览：</span>21 次 </div>
                    </div>
                    <div class="article-detail-text">
                        <p>
                            电话：
                        </p>

                    </div>
                </div>

            </div>

            <div class="content-list" style="display: none;">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">留言咨询</div>
                    </div>
                    <div class="message">
                        <div class="message-content">
                            <div class="message-input">
                                <label>
                                    <i class="icon ic-name"></i>
                                </label>
                                <input class="" type="text" placeholder="姓名" />
                            </div>
                            <div class="message-input">
                                <label>
                                    <i class="icon ic-tel"></i>
                                </label>
                                <input class="" type="text" placeholder="联系方式" />
                            </div>
                        </div>
                        <div class="message-content1">
                            <div class="message-textarea">
                                <label>
                                    <i class="icon ic-message"></i>
                                </label>
                                <textarea class="" type="text" placeholder="留言" /></textarea>
                            </div>
                        </div>
                        <div class="message-btn">
                            <button class="btn">发送</button>
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
    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        $(".content-list").eq($(this).index()).show();
    })
</script>
</body>

</html>