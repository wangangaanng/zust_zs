<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/style.css"/>
    <link rel="stylesheet" href="${base}/css/swyt.css"/>
    <style>
        .top:before{    background-image: url("${base}/img/loginbackgrouind.jpg");    content: "";    background-repeat: no-repeat;    background-size: 100% 100%;    position: absolute;    left: 0;    opacity: 0.3;    right: 0;    top: 0;    bottom: 0;}
    </style>
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
        <div class="login_left">
            <div class="login_title">登录</div>
            <div class="login_input">
                <img src="${base}/img/phone.png">
                <input type="number" placeholder="请输入手机号码" id="swZh" oninput="if(value.length>11)value=value.slice(0,11)">
            </div>
            <div class="login_input">
                <img src="${base}/img/password.png">
                <input type="password" placeholder="请输入密码" id="swMm">
            </div>
            <div class="forget"><a href="SWYTpassword">忘记密码？</a></div>
            <div class="login_bun" onclick="swYtLogin()">登录</div>
        </div>
        <div class="login_right">
            <div class="login_right_border">
                <div>还没有账号</div>
                <div class="registered" onclick="window.location.href='SWregistered'">立即注册<span class="glyphicon glyphicon-arrow-right" style="margin-left: 5px;"></span></div>
                <div>手机小程序登录</div>
                <div><img src="${base}/img/xcx-ewm.png" class="login-icon"></div>
            </div>

        </div>
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

<script src="${base}/js/swyt/SWlogin.js"></script>
</body>
</html>
