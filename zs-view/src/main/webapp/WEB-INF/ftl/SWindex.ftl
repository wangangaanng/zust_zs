<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/swyt.css"/>
</head>
<body>
<#include "com/SWheader.ftl">
<div class="main">
    <div class="container">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <img src="https://www.zust.edu.cn/images/20190917163010659znW.png" alt="">
                </div>
                <div class="swiper-slide">
                    <img src="https://www.zust.edu.cn/images/20190916094148933Y6E.jpg" alt="">
                </div>
                <div class="swiper-slide">
                    <img src="https://www.zust.edu.cn/images/20190123.jpg" alt="">
                </div>
                <div class="swiper-slide">
                    <img src="https://www.zust.edu.cn/images/20191023105624358DzA.jpg" alt="">
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
        <div class="menu-nav">
            <div class="menu-title">
                <div class="title-chn">三位一体</div>
                <div class="title-en">TRINITY RROCESS
                    <div class="menu-nav-icon"></div>
                </div>

            </div>
            <div class="menu-list">
                <ul class="list-group">
                    <#--                        <li class="list-group-item active1" onclick='openUrl("${obj.TZLJ!''}")'>-->
                    <li class="list-group-item" onclick='openUrl("")'>
                        <span class="ic-menu"></span>我的报名表
                    </li>
                </ul>
            </div>

        </div>
        <div class="content-list">
            <div class="article-detail" style="border: none;">
                <div class="article-column-title">
                    <div class="h3">档案查询</div>
                </div>
                <div class="article-detail-text text-center">
                    <div class="archives">
                        <div class="archives-title">按姓名和身份证查询</div>
                        <div class="archives-content">
                            <div class="archives-input">
                                <label>
                                    <i class="icon ic-name"></i>
                                </label>
                                <input class="" id="xsxm" type="text" placeholder="请输入姓名">
                            </div>
                            <div class="archives-input">
                                <label>
                                    <i class="icon ic-sfz"></i>
                                </label>
                                <input class="" id="sfzh" type="text" placeholder="请输入身份证号码">
                            </div>
                            <div class="archives-input">
                                <button class="btn green" onclick="inquiryArchives()">查询</button>
                            </div>
                        </div>
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
    var loginType = 0;
    var mySwiper = new Swiper('.swiper-container', {
        autoplay: {
            stopOnlastSlide: true
        },
        loop: true, // 循环模式选项

        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination',
        },

    })
</script>
</body>
</html>
