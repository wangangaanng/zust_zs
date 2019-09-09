<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="commonProperty.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户绑定</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="../css/mui.min.css" />
		<link rel="stylesheet"href="../css/weui.min.css" />
		<link rel="stylesheet"href="../css/icons-extra.css" />
		<link rel="stylesheet" href="../css/style.css" />
		<script type="text/javascript" src="../js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="../js/layer/layer.js" ></script>
		<script type="text/javascript" src="../js/common.js" ></script>
		<script type="text/javascript" src="../js/bind.js"></script>
	</head>
	<!--
    	作者：2515421994@qq.com
    	时间：2017-05-08
    	描述：绑定页面
    -->
	<body style="height: 100%;background: url(images/bind-back.png) no-repeat 10px 5% #efeff4;background-size: 94% ">
		<div class="bind">
			<p>我的信息<span class="mui-icon mui-icon-closeempty" style="float: right;"></span></p>
			<input type="number" placeholder="请输入手机号" class="bind_phone"/>
			<div class="bind-input">
				<input type="number" value="绑定" placeholder="请输入验证码"/>
				<input type="button" value="获取验证码" id="getCode"/>
			</div>
			<input type="button" value="绑定" class="bind-btn"/>
		</div>
	</body>
</html>
