<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<title>新注册用户</title>
	<link rel="stylesheet" href="../css/mui.min.css" />
	<link rel="stylesheet" href="../css/weui.min.css" />
	<script src="../js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="../js/mui.min.js" type="text/javascript"></script>
	<script src="../js/layer/layer.js" type="text/javascript"></script>
	<script src="../js/common.js" type="text/javascript"></script>
    <script>
        var openId=getCookie("openId");
    </script>
	<script src="../js/xzcyh.js" type="text/javascript"></script>
</head>
<style>
	*{font-family: "微软雅黑";font-size: 16px;}
	.weui-input{padding: 0 !important;border: none !important;height: auto !important;margin-bottom: 0 !important;}
</style>
<body>
<div class="weui-cells weui-cells_form" style="margin-top: 10px;">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">手机号<br/>(登录账号)</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" id="userMobile" placeholder="请输入手机号" />
        </div>
    </div>
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">动态码</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" id="userValidCode" placeholder="请输入动态码" />
        </div>
        <div class="weui-cell__ft">
            <button class="weui-vcode-btn yanzhengmabutton" style="width: 100px;padding: 0;text-align: center;font-size: 16px;" onclick="sendMessageCode()">获取动态码</button>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" id="userName" placeholder="请输入姓名" />
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">身份证</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" id="userCard" placeholder="请输入身份证号码" />
        </div>
    </div>
    <div class="weui-cell">
       <div class="weui-cell__hd"><label class="weui-label">登录密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="password" id="userPsd" placeholder="请输入登录密码">
        </div>
    </div>
</div>
<div class="page__bd page__bd_spacing" style="margin-top: 50px;padding: 15px;">
    <a class="weui-btn weui-btn_primary qyxx">下一步确认企业</a>
</div>
</body>
</html>
