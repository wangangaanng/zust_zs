<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>
<style>
    .xyzy{color: #008784;padding-right: 20px;padding-top: 0px;}
    .xyzy li a{color: #292929;font-size: 16px;}
    .xyzy li{height: 60px;line-height: 60px;
        border-bottom:1px dashed #008784;}
</style>

<body>
    <#include "com/ZSheader.ftl">
    <img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
    <div class="clear"></div>
        <div class="main">
            <div class="container">
                <div class="content">
                    <div class="menu-nav">
                        <div class="menu-title">
                            <div class="title-chn">校园开放日<div class="menu-nav-icon"></div></div>
                        </div>
                        <div class="menu-list">
                            <ul class="list-group">
                                <li class="list-group-item active1" onclick='openUrl("public")'>预约校园开放日</li>
                            </ul>
                        </div>
                    </div>
                    <div class="content-list">
                        <div class="articleTpl-detail">
                            <div class="articleTpl-title">
                                预约校园开放日
                            </div>
                            <div class="article-detail-text">
                                <ul class="xyzy">
                                <#if (result??)&&(result?size>0)>
                                    <#assign flag=1>
                                    <#list result as obj>
                                        <li><a href="${base}/public/${obj.owid?c!''}" target="_blank">${obj.val1!''}</a></li>
                                    </#list>
                                <#else >
                                    <#assign flag=0>
                                </#if>
                                </ul>


                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    <#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            if ("${flag}" == 0) {
                $(".xyzy").append(nulltip)
            }
        })
    </script>
</body>
</script>
</html>
