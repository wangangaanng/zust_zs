<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/jui.css"/>
    <link rel="stylesheet" href="${base}/css/style.css"/>
</head>
<body>
<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="${base}/img/logo-zust.png">
                <div class="title">三位一体招生网</div>
                <div class="user-info" id="qyInfo" style="display: none;">
                    <a href="/enterpriseService/0">欢迎您<span id="qyName"></span></a>,<a href="#" onclick="loginout()">退出</a>
                </div>
                <div class="user-info" id="stuInfo" style="display: none;">
                    <a href="/stuCenter/0">欢迎您<span id="stuName"></span></a>,<a href="#" onclick="loginout()">退出</a>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="w1200">
    <div class="login">

    </div>
</div>
<div class="bottom">
    <div class="contact">
        <div class="container text-center">
            <div class="contact-info text-center">
                <div>浙江科技学院招生就业处</div>
                <div>电话：0571-85124573、85121710 &nbsp;&nbsp;&nbsp;传真：0571-85124573 &nbsp;&nbsp;&nbsp;电子邮件：zustjob@zust.edu.cn</div>
                <div>Copyright © job.zust.edu.cn All Right reserved 沪ICP备10005749号-3</div>
                <div>技术支持：杭州步长科技有限公司 &nbsp;&nbsp;&nbsp;电话：0571-81109247</div>
            </div>
            <div class="ewm">
                <div class="ewmxq">
                    <div class="ewm-border">
                        <img src="${base}/img/ewm-jiuye.png">
                    </div>
                    <div class="ewmsm">
                        科院微就业
                    </div>
                </div>
                <div class="ewmxq">
                    <div class="ewm-border">
                        <img src="${base}/img/ewm-qq.png">
                    </div>
                    <div class="ewmsm">
                        企业QQ群
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--<#include "com/footer.ftl">-->
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/jui.min.js"></script>

<script src="${base}/js/artdialog/jquery.artDialog.js?skin=blue"></script>
<script src="${base}/js/jquery.jdate.js"></script>
<style>
    .login{

    }
</style>
</body>
</html>
