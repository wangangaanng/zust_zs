<#assign sliderList=[{"imgPath":'http://zsb.zust.edu.cn/files/imgs/20170531112701.JPG'},
{"imgPath":'http://zsb.zust.edu.cn/files/imgs/20170531112711.jpg'},
{"imgPath":'http://zsb.zust.edu.cn/files/imgs/20170116182253.jpg'}]>
<link rel="stylesheet" href="${base}/css/swiper.min.css" />
<style>
    .swiper-pagination-bullet {
        width: 50px;
        height: 4px;
        border-radius: 2px;
        position: relative;
        background: #fff;
        opacity: 1;
        cursor: pointer;
    }

    .swiper-pagination-bullet-active {
        background: black;
    }

    #topSlider ul.swiper-wrapper li div,
    #topSlider ul.swiper-wrapper li a {
        position: relative;
        overflow: hidden;
        width: 100%;
        height: 400px;
        display: block;
    }

    #topSlider ul.swiper-wrapper li a img {
        width: 1920px;
        position: absolute;
        left: 50%;
        margin-left: -960px;
    }
</style>
<div class="banner-box">
    <div class="bd swiper-container" id="topSlider" style="max-height: 400px;">
        <ul class="swiper-wrapper" style="height:400px;">
<#if sliderList??>
    <#list sliderList as sliderobj>
            <li class="swiper-slide" style="background-color:#fff">
                <div class="m-width">
                    <a>
                        <img src="${sliderobj.imgPath}" class="fadein" style="height: 400px;" /></a>
                </div>
            </li>
    </#list>
</#if>
        </ul>
        <div class="swiper-pagination"></div>
    </div>
</div>
<script type="text/javascript" src="${base}/js/swiper.min.js"></script>
<script>
    var mySwiper = new Swiper('#topSlider', {
        //progress: true,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        autoplay: {
            delay: 3000,
        },
        loop: true,
        speed: 3000,
        effect: 'fade',
    })

</script>