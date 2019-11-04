<!--
    作者：2515421994@qq.com
    时间：2019-10-25
    描述：三位一体container
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/SWconfig.ftl">
    <title>${swyt_title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/swyt.css"/>
</head>

<body>
<#include "com/SWheader.ftl">
<div class="frame-wrap">
    <#--menu constant-->
   <#assign menuList=["我的报名表","报名表打印","拍照上传","初审结果/缴费","分组信息","面试通知单打印","成绩查询","在线提问"] />

    <#--swiper start-->
    <div class="swiper-container index-swiper">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <img src="https://www.zust.edu.cn/images/20190123.jpg" alt="">
            </div>
            <div class="swiper-slide">
                <img src="https://www.zust.edu.cn/images/5.jpg" alt="">
            </div>
            <div class="swiper-slide">
                <img src="https://www.zust.edu.cn/images/22.jpg" alt="">
            </div>
        </div>
        <!-- 如果需要分页器 -->
        <div class="swiper-pagination"></div>
    </div>
   <#--swiper end-->

     <#--content start -->
    <div class="content mt20">

    <#--leftMenu start -->
    <#include "com/SWsubMenu.ftl">
    <#--leftMenu start -->

        <div class="content-list">
            <div class="articleTpl-detail">
                <div class="articleTpl-title">
                    <#list menuList as data>
                        ${((data_index?number)==(page?number))?string(data,'')}
                    </#list>
                </div>
                <div class="article-detail-text">
                    <#switch page>
                        <#case "0">
                             <#--报名表容器：1：基本信息 2：联系人 3：学考等第 4：招考信息-->
                            <#include "SWInfoBasic.ftl">
                            <#break>
                        <#case "2">
                            <#--拍照上传-->
                            <#include "SWphotoUpload.ftl">
                            <#break>
                        <#case "3">
                            <#--初审结果/缴费-->
                            <#include "SWpayOnline.ftl">
                            <#break>
                        <#case "4">
                            <#--分组信息-->
                            <#include "SWgroupInfo.ftl">
                            <#break>
                        <#case "5">
                            <#--面试通知单-->
                            <#include "SWofferNotice.ftl">
                            <#break>
                        <#case "6">
                            <#--成绩查询-->
                            <#include "SWquerySearch.ftl">
                            <#break>
                    </#switch>
                </div>
            </div>
        </div>

    </div>
<#--content start -->
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script>
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: {stopOnlastSlide: true},
        loop: true, // 循环模式选项
        // 如果需要分页器
        pagination: {el: '.swiper-pagination',}
    })
</script>
</body>
</html>
