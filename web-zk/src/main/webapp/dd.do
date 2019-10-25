<?xml version="1.0" encoding="UTF-8"?>
<?page title="浙江科技学院招就处一体化管理平台"?>
<?meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /?>
<?meta name="renderer" content="webkit" /?>
<?link rel="icon" href="/charisma/img/zust.ico" type="image/x-icon"/?>
<?link href="/charisma/css/common.css" rel="stylesheet" type="text/css"?>
<?script src="/charisma/js/jquery-3.2.1.min.js" ?>
<?script src="/assets/common/func.js" ?>
<window
        xmlns="http://www.zkoss.org/2005/zul"
        xmlns:h="http://www.w3.org/1999/xhtml"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        use="com.ourway.apply.Tess"
        id="sadfsdf"
        class="login-body">
    <style>
        .wait-tip_wrap{width: 500px;height: 220px;padding: 0px;
        background: rgba(255,255,255,1);position: absolute;top: 50%;left: 50%;margin-left: -250px;border-radius: 12px;box-shadow: 1px 5px 19px #e5e9ea;
        margin-top: -150px;}
        .wait-tip_title img{height: 28px;box-shadow: rgba(255, 255, 255, 0.2) 1px 1px 0px inset;
        border-width: 1px;border-style: solid;border-color: rgba(0, 0, 0, 0.1);margin-right: 12px;
        border-image: initial;padding: 4px;}
        .wait-loading{float: left;}
        .wait-tip,.wait-loading{display: inline-block;}
        .wait-content_wrap{padding: 25px 20px;margin-top:10px;}
        .wait-tip{text-align: center;}
        .wait-tip h4 {font-weight: bold;font-size: 16px;margin-top:10px;}
        .wait-tip h6 {font-weight: normal;font-size: 14px;margin: 10px;}
        .wait-loading{margin-left: 40px;margin-right: 20px;}
        .wait-tip_title{text-align:center;margin: 0px;font-size: 20px;font-weight: bold;display: block;padding: 15px;
        color: #FFF;background: url(charisma/img/login-banner_top.png) no-repeat !important;border-radius: 12px 12px 0px 0px;}
    </style>
    <custom-attributes caname="/logins.do" />
    <grid onClientInfo="sadfsdf.onClientInfo(event)" style="height:0px !important;"></grid>
    <!--content start-->
    <div id="ddddd" width="1000px" height="300px" visible="true" context="第四大"></div>


</window>