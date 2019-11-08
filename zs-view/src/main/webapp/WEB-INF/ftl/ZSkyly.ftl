<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/style.css"/>
</head>
<style>
    .gallery-top {
        height: 600px;
        width: 890px;
    }
    .gallery-top img {
        height: 600px;
        width: 890px;
    }
    .gallery-thumbs img {
        height: 120px;
        width: 180px;
    }
    .gallery-thumbs {
        height: 120px;
        box-sizing: border-box;
        padding: 10px 0;
        width: 890px;
    }
    .gallery-thumbs .swiper-slide {
        height: 120px;
        opacity: 0.4;
    }
    .gallery-thumbs .swiper-slide-thumb-active {
        opacity: 1;
    }
</style>
<body>
<#include "com/ZSheader.ftl">
<img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
<div class="main">
    <div class="container">
        <div class="menu-nav">
            <div class="menu-title">
                <div class="title-chn">科院掠影</div>

            </div>
            <div class="menu-list">
                <ul class="list-group">
                    <#if thirdDir?? && thirdDir != "" && thirdDir == "0">
                        <li class="list-group-item active1" onclick='openUrl("kyly/1/0")'>
                            <span class="ic-menu"></span>安吉校园风光
                        </li>
                        <li class="list-group-item" onclick='openUrl("kyly/1/1")'>
                            <span class="ic-menu"></span>小和山校园风光
                        </li>
                    <#else>
                        <li class="list-group-item" onclick='openUrl("kyly/1/0")'>
                            <span class="ic-menu"></span>安吉校园风光
                        </li>
                        <li class="list-group-item active1" onclick='openUrl("kyly/1/1")'>
                            <span class="ic-menu"></span>小和山校园风光
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
        <div class="content">
            <div class="routes">
                <div class="location">
                    <i></i>
                    <#--当前位置：-->
                </div>

                <ol class="breadcrumb">
                    <li><a href="/">首页</a></li>
                    <li><a href="#">科院掠影</a></li>
                    <#if thirdDir?? && thirdDir != "" && thirdDir == "0">
                        <li><a href="#">安吉校园风光</a></li>
                    <#else >
                        <li><a href="#">小和山校园风光</a></li>
                    </#if>
                </ol>
            </div>
            <div class="swiper-container gallery-top">
                <div class="swiper-wrapper" id="top-wrapper">
                    <#if (result??)&&(result?size>0)>
                        <#assign flag=1>
                        <#list result as obj>
                            <div class="swiper-slide">
                                <img src="${imagePath}${obj.xsbt}" alt="">
                            </div>
                        </#list>
                    <#else >
                        <#assign flag=0>
                    </#if>
                </div>
                <div class="swiper-button-prev swiper-button-white"></div>
                <div class="swiper-button-next swiper-button-white"></div>
            </div>
            <div class="swiper-container gallery-thumbs" style="margin: 0 2%">
                <div class="swiper-wrapper" id="bottom-wrapper">
                    <#if (result??)&&(result?size>0)>
                        <#assign flag=1>
                        <#list result as obj>
                            <div class="swiper-slide">
                                <img src="${imagePath}${obj.xsbt}" alt="">
                            </div>
                        </#list>
                    <#else >
                        <#assign flag=0>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script>
    var galleryThumbs = new Swiper('.gallery-thumbs', {
        spaceBetween: 10,
        slidesPerView: 4,
        loop: true,
        freeMode: true,
        loopedSlides: 4, //looped slides should be the same
        watchSlidesVisibility: true,
        watchSlidesProgress: true
    });
    var galleryTop = new Swiper('.gallery-top', {
        autoplay: true,
        spaceBetween: 10,
        loop:true,
        loopedSlides: 4, //looped slides should be the same
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
        },
        thumbs: {
            swiper: galleryThumbs
        }
    });
</script>
</body>
</html>
