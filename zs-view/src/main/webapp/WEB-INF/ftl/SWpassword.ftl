<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/SWconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/style.css"/>
    <link rel="stylesheet" href="${base}/css/swytFonts.css"/>
    <link rel="stylesheet" href="${base}/css/swyt.css"/>
    <style>
        .top{position: relative;height: 500px!important;}
        .top:before{    background-image: url("${base}/img/loginbackgrouind2.jpg");    content: "";    background-repeat: no-repeat;    background-size: 100% 100%;    position: absolute;    left: 0;    opacity: 0.3;    right: 0;    top: 0;    bottom: 0;}
    </style>
</head>
<body>
<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="${base}/img/logo-zust.png">
                <div class="title">三位一体招生网</div>
            </div>
        </div>
    </div>
</header>
<div class="w1200">
    <div class="login">
        <div class="login_left">
            <div class="login_title">忘记密码</div>
            <div class="login_input">
                <span class="iconfont icon-shouji"></span>
                <#--<img src="${base}/img/phone.png">-->
                <input type="number" oninput="if(value.length>11)value=value.slice(0,11)" placeholder="请输入手机号码" id="swZh">
            </div>
            <div class="login_input">
                <span class="iconfont icon-yanzhengma1"></span>
                <#--<img src="${base}/img/yzm.png">-->
                <input style="padding-right: 100px;" type="text" placeholder="请输入验证码" id="yzm">
                <div class="getymz" onclick="sendCode()">获取验证码</div>
            </div>
            <div class="login_input">
                <span class="iconfont icon-denglu-mima"></span>
                <#--<img src="${base}/img/password.png">-->
                <input style="padding-right: 40px;" type="password" placeholder="请输入密码(6-16位)" id="swMm">
                <i class="glyphicon glyphicon-eye-close" onclick="eye(this)" value="0"></i>
            </div>
            <div class="login_input">
                <span class="iconfont icon-denglu-mima"></span>
                <#--<img src="${base}/img/password.png">-->
                <input style="padding-right: 40px;" type="password" placeholder="确认密码" id="swMm2">
                <i class="glyphicon glyphicon-eye-close" onclick="eye(this)" value="0"></i>
            </div>
            <div class="login_bun" onclick="forgetPwd()">确认</div>
        </div>
        <div class="login_right">
            <div class="login_right_border">
                <div>已有账号</div>
                <div class="registered" onclick="window.location.href='trinitylogin'">登录<span class="glyphicon glyphicon-arrow-right" style="margin-left: 5px;"></span></div>
                <div>手机小程序登录</div>
                <div><img src="${base}/img/xcx-ewm.jpg" class="login-icon"></div>
            </div>

        </div>
    </div>
</div>

<#include "com/SWloginReFooter.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/swyt/SWpassword.js"></script>
</body>
</html>
