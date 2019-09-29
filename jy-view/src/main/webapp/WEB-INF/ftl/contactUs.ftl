<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>
<style>

</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <#include "com/route.ftl">
        <div class="content">
            <#include "com/subMenu.ftl">
            <div class="content-list">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">${thirdDirName!''}</div>
                    </div>
                    <div class="message">
                        <div class="message-content">
                            <div class="message-input">
                                <label>
                                    <i class="icon ic-name"></i>
                                </label>
                                <input class="" type="text" id="xm" placeholder="姓名" />
                            </div>
                            <div class="message-input">
                                <label>
                                    <i class="icon ic-tel"></i>
                                </label>
                                <input class="" type="text" id="sjh" placeholder="手机号" />
                            </div>
                        </div>
                        <div class="message-content1">
                            <div class="message-textarea">
                                <label>
                                    <i class="icon ic-message"></i>
                                </label>
                                <textarea class="" type="text" id="wtnr" placeholder="留言" /></textarea>
                            </div>
                        </div>
                        <div class="message-btn">
                            <button class="btn" onclick="consult()">发送</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>

<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script>

    function consult() {
        if(!$("#sjh").val().trim()){
            walert("请填写手机号");
            return
        }else{

            var length = $("#sjh").val().trim().length;
            var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
            if(length!=11||!mobile.test($("#sjh").val().trim())){
                walert("请填写11位手机号");
                return
            }
        }
        if(!$("#xm").val().trim()){
            walert("请填写姓名");
            return
        }
        if(!$("#wtnr").val().trim()){
            walert("请填写留言内容");
            return
        }
        var jsonObj={
            "wtnr":$("#wtnr").val(),
            "zxlx": 5,
            "sjh": $("#sjh").val().trim(),
            "xm": $("#xm").val().trim(),
            "studentOwid": getCookie("stuOwid")
        }
        ajax("zustcommon/bckjBizZxzx/consult", jsonObj, function (data) {
            if(data.backCode==0){
               walert("留言成功")
            }
        })
    }
</script>
</body>

</html>