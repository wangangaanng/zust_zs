<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>注册成功</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<link href="../css/mui.min.css" rel="stylesheet" />
	<link href="../css/weui.min.css" rel="stylesheet" />
	<script src="../js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="../js/mui.min.js" type="text/javascript"></script>
	<script src="../js/layer/layer.js" type="text/javascript"></script>
	<script src="../js/common.js" type="text/javascript"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.3.2.js"></script>
	<script>
        wx.miniProgram.getEnv(function(res) {
            console.log(res.miniprogram) // true
            setTimeout(function(){
                wx.miniProgram.switchTab({url: '/pages/index/index'})
            },1500)

        })
	</script>
	<style>
		.tbdiv{padding:35px 0 20px 0;text-align: center;margin-top: 80px;}
		.ddwc{width: 80px;height: 80px;}
	</style>
</head>
<body style="background: white;">
<div style="background: #fff;">
	<div class="tbdiv">
		<img src="../img/ddwc.png" class="ddwc" />
		<p class="wcts" style="margin-top: 20px;">绑定成功，请直接扫码进行系统登录</p>
	</div>
</div>
</body>
</html>




