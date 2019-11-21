<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <style>
        .content-list{
            width:100%;}
    </style>
</head>
<style>
    .xInfo{margin: 10px 32px;}
    .xInfo li{color: #333;}
    .article-detail-text p{font-size: 16px;line-height: 1.5em;text-indent: 32px;}
    .ewmsc{margin: 20px auto;width:150px;height: 150px;}
</style>

<body>
    <#include "com/ZSheader.ftl">
    <img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
    <div class="clear"></div>
        <div class="main">
            <div class="container">
                <div class="content">
                    <div class="content-list">

                    <#if result??>
                        <div class="fuye_list_infor">
                            <h1 style="color: #008784;">${result.dicVal1!''}</h1>
                            <div class="article-detail-text">
                                <#--<p>本次开放日主要面向有意了解以及以后报考浙江科技学院的高中学生及家长(不限年级)，为学生与家长提供一个与校区亲密接触和提前了解大学生活的机会。</p>-->
                                <ul class="xInfo">
                                    <li>活动时间：${result.dicVal2!''} - ${result.dicVal7!''}</li>
                                    <li>活动地点：${result.dicVal3!''}</li>
                                </ul>
                                <p>本次校园开放日主要内容：</p>
                                <p>${result.dicVal5!''}</p>
                                <p>报名注意事项：报名限额${result.dicVal4!''}人，报满即止。</p>
                                <p>咨询电话：0571-85070165</p>
                                <p>报名方式：微信扫描下方小程序码</p>
                                <p style="text-align: center;"><img src="${imagePath}${result.dicVal6!''}" class="ewmsc"></p>
                            </div>
                        </div>
                    </#if>
                    </div>
                </div>

            </div>
        </div>

    <#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
