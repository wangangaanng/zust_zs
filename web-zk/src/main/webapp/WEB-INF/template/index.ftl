<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <title>登录</title>
    <link rel="icon" href="charisma/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="html/logincss/common.css"/>
    <link rel="stylesheet" href="html/logincss/index.css"/>
    <link rel="stylesheet" href="html/js/layer/theme/default/layer.css"/>
    <link href="html/logincss/bootstrap.css" rel="stylesheet">
</head>
<body style="background: url('charisma/img/index_bkg.png') 100% 100% no-repeat">
<div style="width: 580px;position: relative; left: 23%; top: 30%">
    <div style="text-align: left;padding: 30px 0 0 0;">
        <img src="charisma/img/index_logo.png"/>
        <h2 style="font-size: 36px;letter-spacing: 0;color: #fffefe;">浙江科技学院招就处一体化管理平台</h2>
        <h2 style="font-size: 12px;letter-spacing: 0;color: #fffefe;">Zhejiang academy of science and technology website background management system</h2>
    </div>

</div>
<!--content-wrap begin-->
<div class="root" style="height: 700px;">

    <article>
        <section class="LoginForm" style="color: #fff;top: 40%;left: 77%">
           <div class="header" style="text-align: center;">
               <span class="title">登录 / LOGIN</span>
           </div>
           <form action="login.htm" method="post" >
               <div class="body">
                  <div class="form-group">
                      <label>登录名:</label>
                      <input type="text" name="empId" class="form-control" placeholder="请输入登录名" required value >
                  </div>
                  <div class="form-group">
                      <label>密 码:</label>
                      <input type="password" name="empPsw" class="form-control" placeholder="请输入密码" required value >
                  </div>
               </div>
               <div class="footer" style="text-align: center">
                   <button type="submit" class="btn btn-primary w80 mr10" style="background-color: #f4b901;width: 100%; height: 38px;border: 1px solid #f4b901">登 录</button>
                   <#--<a href="">密码找回</a>-->
               </div>
               <div id="error" style=" display:none;margin: 10px auto;padding: 3px;font-size: 12px;background-color: #C77016;color: #fff;border-radius: 4px;position: absolute;right: 25%;left: 25%;text-align: center;">账号密码错误请重试!</div>
           </form>
        </section>
    </article>

</div>
<div style="width: 100%;text-align: center;padding-top: 20px;font-size: 14px;color: #fffefe;">浙江科技学院版权所有<span style="margin-left:10px;margin-right:10px;"><span >•</span></span><span style="margin-left:10px;margin-right:10px;"></span>copyright © 2019 . All rights reserved</div>
<!--content-wrap end-->
<#--<script src="html/js/jquery.min.js"></script>-->
<script src="html/js/jquery-3.2.1.min.js"></script>
<script src="html/js/layer/layer.js"></script>
<script>
    console.log(window.location.search);
    console.log(window.location.search.indexOf('error'));
    (function () {
        if(window.location.search.indexOf('error')>0){
            $('#error').show();
            setTimeout(function () {
                $('#error').fadeOut();
            },2000)
        }
    })();
    function getRequest() {
        var url = window.location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                //就是这句的问题
                theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]);
                //之前用了unescape()
                //才会出现乱码
            }
        }
        return theRequest;
    }
</script>
</body>
</html>