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
<div class="main">
    <div class="container">
        <#include "com/subMenu.ftl">
        <div class="content">
            <#include "com/route.ftl">
            <div class="swiper-container gallery-top">
                <div class="swiper-wrapper" id="top-wrapper">
                    <div class="swiper-slide">
                        <img src="http://zsb.zust.edu.cn/files/imgs/F4C8C2EA34C24ACC8FE4A5AB5DEBD928.jpg" alt="">
                    </div>
                    <div class="swiper-slide">
                        <img src="http://zsb.zust.edu.cn/files/imgs/20170117193721.JPG" alt="">
                    </div>
                    <div class="swiper-slide">
                        <img src="http://zsb.zust.edu.cn/files/imgs/20170117193134.jpg" alt="">
                    </div>
                    <div class="swiper-slide">
                        <img src="http://zsb.zust.edu.cn/files/imgs/20170117194038.JPG" alt="">
                    </div>
                </div>
                <div class="swiper-button-prev swiper-button-white"></div>
                <div class="swiper-button-next swiper-button-white"></div>
            </div>
            <div class="swiper-container gallery-thumbs" style="margin: 0 2%">
                <div class="swiper-wrapper" id="bottom-wrapper">
                    <div class="swiper-slide">
                        <img src="http://zsb.zust.edu.cn/files/imgs/F4C8C2EA34C24ACC8FE4A5AB5DEBD928.jpg" alt="">
                    </div>
                    <div class="swiper-slide">
                        <img src="http://zsb.zust.edu.cn/files/imgs/20170117193721.JPG" alt="">
                    </div>
                    <div class="swiper-slide">
                        <img src="http://zsb.zust.edu.cn/files/imgs/20170117193134.jpg" alt="">
                    </div>
                    <div class="swiper-slide">
                        <img src="http://zsb.zust.edu.cn/files/imgs/20170117194038.JPG" alt="">
                    </div>
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
        loopedSlides: 5, //looped slides should be the same
        watchSlidesVisibility: true,
        watchSlidesProgress: true
    });
    var galleryTop = new Swiper('.gallery-top', {
        autoplay: true,
        spaceBetween: 10,
        loop:true,
        loopedSlides: 5, //looped slides should be the same
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
