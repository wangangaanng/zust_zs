<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<title>企业信息</title>
	<link rel="stylesheet" href="../css/mui.min.css" />
	<link rel="stylesheet" href="../css/weui.min.css" />
	<script src="../js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="../js/mui.min.js" type="text/javascript"></script>
	<script src="../js/layer/layer.js" type="text/javascript"></script>
	<script src="../js/common.js" type="text/javascript"></script>
    <script>
        var openId=getCookie("openId");
    </script>
	<script src="../js/qyxx.js" type="text/javascript"></script>
</head>
<style>
	*{font-family: "微软雅黑";font-size: 16px;}
	.weui-input{padding: 0 !important;border: none !important;height: auto !important;margin-bottom: 0 !important;}
	.sczpDiv{padding: 10px 15px;background: #ffffff;}
	.sczpDiv p{color: #000000;}
	.sczpDiv div{width: 100%;text-align: center;}
	.sczpDiv img{width: 180px;height: 120px;}
</style>
<body>
<%--<div class="sczpDiv">
	<p>请上传企业执照</p>
	<div>
		<img src="../img/zpsc.png" id="xszzm" />
	</div>
</div>--%>
<div class="weui-cells weui-cells_form" style="margin-top: 10px;">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label" style="width: 150px;text-align: left;">统一社会信用码<br />组织机构代码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" id="compTaxno" placeholder="请输入机构代码" />
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label" style="width: 150px;">企业名称</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" id="compName" placeholder="请输入公司名称" />
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label" style="width: 150px;">企业地址</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" id="compAddr" placeholder="请输入公司地址" />
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label" style="width: 150px;">企业联系电话</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" id="compTel1" placeholder="请输入企业联系电话" />
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label" style="width: 150px;">企业法人</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" id="compLegalPerson" placeholder="请输入企业法人" />
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label" style="width: 150px;">企业法人联系电话</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" id="compLegalPersonTel" placeholder="请输入企业法人联系电话" />
        </div>
    </div>
</div>
<div class="page__bd page__bd_spacing" style="margin-top: 50px;padding: 15px;">
    <a href="javascript:;" class="weui-btn weui-btn_primary confirm">确认注册</a>
</div>
<div class="js_dialog" id="iosDialog2" style="opacity: 1;display:none;">
    <div class="weui-mask"></div>
    <div class="weui-dialog">
        <div class="weui-dialog__bd">绑定成功，请再次扫码进行系统登录</div>
        <div class="weui-dialog__ft">
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary qd">确定</a>
        </div>
    </div>
</div>
</body>
</html>
