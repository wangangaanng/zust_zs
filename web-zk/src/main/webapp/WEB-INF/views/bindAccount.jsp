<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>绑定账号</title>
    <link rel="stylesheet" href="../css/mui.min.css"/>
    <link rel="stylesheet" href="../css/weui.min.css"/>
    <script src="../js/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script src="../js/mui.min.js" type="text/javascript"></script>
    <script src="../js/layer/layer.js" type="text/javascript"></script>
    <script src="../js/common.js" type="text/javascript"></script>
    <script>
        var openId = "${openId}";
        var avatarUrl="${avatarUrl}";
        var nickName="${nickName}";
        var unionId="${unionId}";
        addCookie("openId", openId);
    </script>
    <script src="../js/bindAccount.js" type="text/javascript"></script>
</head>
<style>
    * {
        font-family: "微软雅黑";
        font-size: 16px;
    }

    .weui-input {
        padding: 0 !important;
        border: none !important;
        height: auto !important;
        margin-bottom: 0 !important;
    }

    .mui-icon-eye {
        font-size: 20px;
        position: absolute;
        z-index: 1;
        top: 10px;
        right: 0;
        width: 38px;
        height: 38px;
        text-align: center;
        color: #999;
    }
    .confirm{
        background: #3474DD !important;
        font-size: 16px;
    }
    .confirm:active{
        background: #3474DD !important;
    }
</style>
<body>
<div style="width: 100%;height:150px;text-align: center;background: url('https://www.qzsjcloud.com/projectManageFiles/img-bg1.png') 100% 100%;">
    <img src="../img/img_logo.png" style="width: 75px;height:75px;margin-top: 35px;" />
</div>
<div class="weui-cells weui-cells_form" style="margin-top: 10px;">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">登录账号</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" id="empId" placeholder="请输入登录账号">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">登录密码</label></div>
        <div class="weui-cell__bd mui-password">
            <input class="weui-input mui-input-password" type="password" id="empPsw" placeholder="请输入登录密码">
            <span class="mui-icon mui-icon-eye"></span>
        </div>
    </div>
</div>
<div class="page__bd page__bd_spacing" style="margin-top: 50px;padding: 15px;">
    <a href="javascript:;" class="weui-btn weui-btn_primary confirm">确认绑定</a>
</div>
<%--<div style="width: 100%;text-align: right;padding-right: 15px;margin-top: 15px;">--%>
    <%--<a class="register" style="font-size: 14px;color: #E66B14;">新注册用户</a>--%>
<%--</div>--%>
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
