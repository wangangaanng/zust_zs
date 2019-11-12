<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/zszy.css" />
</head>
<style>

</style>

<body>
    <#include "com/ZSheader.ftl">
        <div class="main">
            <div class="container">
                <img class="ejgg" src="" />
                <div class="zkds">
                    <#if (xyList??)&&(xyList?size>0)>
                        <#list xyList as xy>
                        <#if xy_index==8>
                            <div class="xyzy" style="position:relative;top:99px;">
                        <#elseif xy_index==9>
                            <div class="xyzy" style="position:relative;top:59px;">
                        <#elseif xy_index==10>
                            <div class="xyzy" style="position:relative;top:140px;">
                        <#elseif xy_index==11>
                            <div class="xyzy" style="position:relative;top:70px;">
                        <#else >
                            <div class="xyzy">
                        </#if>


                                <div class="title title${xy_index+1}">
                                    <div>
                                        <a class="xymc" href="${base}/wzOrTpOrSq/6/${xy_index}" target="_blank">${xy.mz!''}</a>
                                    </div>
                                    <#--<div>-->
                                        <#--<a class="xymcE"></a>-->
                                    <#--</div>-->
                                    <div style="margin-top: 10px;">
                                        <a class="xyjj" href="${base}/wzOrTpOrSq/6/${xy_index}" target="_blank">学院简介</a>
                                        <a class="ztxy" href="${xy.articleUrl!''}" target="_blank">直通学院</a>
                                    </div>
                                    <#if xy_index%2==0>
                                        <div class="zydiv" style="margin-right: ${(((xy_index+1)/2)?ceiling)*9.6}px;">
                                    <#else >
                                        <div class="zydiv" style="margin-left: ${(((xy_index+1)/2)?ceiling)*9.6}px;">
                                    </#if>
                                        <#if (zyList??)&&(zyList?size>0)>
                                            <#list zyList as zy>
                                                <#if zy_index==xy_index>
                                                    <#list zy as obj>
                                                    <#if obj.articleUrl??>
                                                        <a href="${obj.articleUrl!''}" class="zy" target="_blank">${obj.mz!''}</a>
                                                    <#else >
                                                        <a onclick="javascript:walert('暂无介绍')" class="zy">${obj.mz!''}</a>
                                                    </#if>

                                                    </#list>
                                                </#if>
                                            </#list>

                                        </#if>
                                        <#--<a href="IndexPage!wzxq.htm?id=4" class="zy" style="top:120px;left:-20px;" target="_blank">机械设计制造及其自动化</a>-->
                                        <#--<a href="IndexPage!wzxq.htm?id=5" class="zy" style="top:70px;left:-55px;" target="_blank">材料成型及控制工程</a>-->
                                        <#--<a href="IndexPage!wzxq.htm?id=6" class="zy" style="top:40px;left:-48px;" target="_blank">车辆工程</a>-->
                                        <#--<a href="IndexPage!wzxq.htm?id=7" class="zy" style="top:130px;left:-45px;" target="_blank">汽车服务工程</a>-->
                                        <#--<a href="IndexPage!wzxq.htm?id=38" class="zy" style="top:90px;left:-40px;" target="_blank">能源与环境系统工程</a>-->
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
                <div style="width:1200px;text-align:center;font-family:'微软雅黑';font-size:45px;margin-left:auto;margin-right:auto;margin-top:-57px;font-weight:500;">浙江科技学院</div>

            </div>
        </div>

    <#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-paginator.min.js" type="text/javascript"></script>
</body>

</html>
