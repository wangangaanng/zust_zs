<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <title>登录</title>
    <link rel="stylesheet" href="html/logincss/common.css"/>
    <link rel="stylesheet" href="html/logincss/index.css"/>
    <link rel="stylesheet" href="html/js/layer/theme/default/layer.css"/>
    <link href="html/logincss/bootstrap.css" rel="stylesheet">
</head>
<body>
<div style="width: 100%;">
    <div style="text-align: left;padding: 40px 0 15px 0;">
        <img src="charisma/img/zust_logo.jpg" style="margin-left: 88px;" />
        <h2 style="display: inline-block;margin-left: 20px;letter-spacing: 5px;">浙江科技学院网站后台管理系统</h2>
    </div>

</div>
<!--content-wrap begin-->
<div class="root" style="height: 700px;background: url('charisma/img/logo/bg.png') 100% 100% no-repeat">
    <article>
        <section class="LoginForm" style="background: #fff;">
           <div class="header">
               <span class="title">登录</span>
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
               <div class="footer">
                   <button type="submit" class="btn btn-primary w80 mr10">登 录</button>
                   <#--<a href="">密码找回</a>-->
               </div>

           </form>
        </section>
    </article>

</div>
<div style="width: 100%;text-align: center;padding-top: 20px;font-size: 14px;color: #555555;">浙江科技学院版权所有<span style="margin-left:10px;margin-right:10px;"><span >•</span></span><span style="margin-left:10px;margin-right:10px;"></span>copyright © 2019 . All rights reserved</div>
<!--content-wrap end-->
<script src="html/js/jquery.min.js"></script>
<script src="html/js/layer/layer.js"></script>
<script>
    // (function () {
    //     if(getRequest().errorMess==1){
    //         layer.confirm('账号或者密码有误', {
    //             btn: ['确定'] //按钮
    //         }, function(){
    //             // window.location.href='http://localhost:8080/webZk/manage/index.htm';
    //             console.log(111);
    //             window.alert("账号或密码错误")
    //         })
    //     }
    //
    // })()
    console.log(window.location.search);
    console.log(window.location.search.indexOf('error'));
    if(window.location.search.indexOf('error')>0){
        $('#error').show();
        setTimeout(function () {
            $('#error').fadeOut();
        },2000)
    }
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