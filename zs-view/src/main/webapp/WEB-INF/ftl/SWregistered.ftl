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
    <link rel="stylesheet" href="${base}/css/swytFonts.css"/>
    <link rel="stylesheet" href="${base}/css/swyt.css"/>
    <style>
        .top{position: relative;height: 500px!important;}
        .top:before{    background-image: url("${base}/img/loginbackgrouind2.jpg");    content: "";    background-repeat: no-repeat;    background-size: 100% 100%;    position: absolute;    left: 0;    opacity: 0.3;    right: 0;    top: 0;    bottom: 0;}
        .dropdown-menu{width: 100%;}
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
            <div class="login_title">注册</div>
            <div class="login_input">
                <span class="iconfont icon-shouji"></span>
                <#--<img src="${base}/img/phone.png">-->
                <input type="number" placeholder="请输入手机号码" id="swZh" oninput="if(value.length>11)value=value.slice(0,11)">
            </div>
            <div class="login_input">
                <span class="iconfont icon-yanzhengma1"></span>
                <#--<img src="${base}/img/yzm.png">-->
                <input style="padding-right: 100px;" type="text" placeholder="请输入验证码" id="yzm">
                <div class="getymz" onclick="sendCode()">获取验证码</div>
            </div>
            <div class="login_input">
                <#--<img src="${base}/img/yh.png">-->
                <span class="iconfont icon-xingming"></span>
                <input type="text" placeholder="请输入真实姓名" id="xm">
            </div>
            <div class="login_input">
                <#--<img src="${base}/img/password.png">-->
                <span class="iconfont icon-denglu-mima"></span>
                <input style="padding-right: 40px;" type="password" placeholder="请输入密码(6-16位)" id="swMm">
                <i class="glyphicon glyphicon-eye-close" onclick="eye(this)" value="0"></i>
<#--                <i class="glyphicon glyphicon-eye-open"></i>-->
            </div>
            <div class="login_input">
                <#--<img src="${base}/img/password.png">-->
                <span class="iconfont icon-denglu-mima"></span>
                <input style="padding-right: 40px;" type="password" placeholder="确认密码" id="swMm2">
                <i class="glyphicon glyphicon-eye-close" onclick="eye(this)" value="0"></i>
            </div>
            <div class="login_input">
                <span class="iconfont icon-xingbie"></span>
                <#--<img src="${base}/img/sex.png">-->
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span id="sex">请选择性别</span>  <span class="glyphicon glyphicon-chevron-down"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a>男</a></li>
                        <li><a>女</a></li>
                    </ul>
                </div>
            </div>
            <div class="login_input2">
                <select name="prov" id="prov" onchange="showCity(this)">
                    <option disabled selected>学籍省</option>
                </select>
                <select id="city" name="city" onchange="showCountry(this)">
                    <option  disabled selected>学籍市</option>
                </select>
                <select id="country" name="country" onchange="selecCountry(this)" style="margin-right: 0;">
                    <option  disabled selected>学籍区</option>
                </select>
            </div>
            <div class="login_input">
                <#--<img src="${base}/img/class.png">-->
                <span class="iconfont icon-shuji"></span>
                <input type="text" placeholder="专业倾向" id="qxzy">
            </div>
            <div class="login_bun met1" onclick="swYtzc()">注册</div>
        </div>
        <div class="login_right">
            <div class="login_right_border">
                <div>已有账号</div>
                <div class="registered" onclick="window.location.href='${base}/trinitylogin'">登录<span class="glyphicon glyphicon-arrow-right" style="margin-left: 5px;"></span></div>
                <div>手机小程序登录</div>
                <div><img src="${base}/img/xcx-ewm.png" class="login-icon"></div>
            </div>

        </div>
    </div>
</div>
<#include "com/SWloginReFooter.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/city.js"></script>
<script src="${base}/js/method.js"></script>
<script src="${base}/js/swyt/SWregistered.js"></script>
</body>
</html>
