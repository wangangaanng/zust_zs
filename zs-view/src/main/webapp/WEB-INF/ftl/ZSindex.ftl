<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/swiper.min.css" />
    <link rel="stylesheet" href="${base}/css/style.css" />
</head>
<body>
<#include "com/ZSheader.ftl">
<#include "com/ZStopSlider.ftl">
<!-- S frame-content -->
<div class="frame-wrap">
    <!-- S  a-->
    <div class="shouYe_nav2_back">
        <div class="shouYe_nav2">
            <div class="nav2_1">
                <img class="nav2_img" id="nav2_1_img" src="${IMAGEPATH}zsdt.png" />
                <label class="nav2_lab zsdt" name="zsdt" onclick="changeNews(this)">招生动态</label>
            </div>
            <div class="nav2_2">
                <img class="nav2_img" src="${IMAGEPATH}2017zszy.png" />
                <label class="nav2_lab 2017zszy" id="zszylab" val="按专业招生" name="2017zszy" onclick="changeNews(this)">2019招生专业</label>
            </div>
            <div class="nav2_3">
                <img class="nav2_img" src="${IMAGEPATH}zydh.png" />
                <a class="nav2_a" href="IndexPage!zszy.htm">专业导航</a>
            </div>
            <div class="nav2_4">
                <img class="nav2_img" src="${IMAGEPATH}swytbmxt.png" />
                <a class="nav2_a" href="http://zustzsb.zghzbckj.com/zust/IndexPage!login.htm">三位一体</a>
            </div>
            <div class="nav2_5">
                <img class="nav2_img" src="${IMAGEPATH}xsybdxt.png" />
                <a class="nav2_a" href="http://ybd.zust.edu.cn">预报到</a>
            </div>
            <div class="nav2_6">
                <img class="nav2_img" id="nav2_6_img" src="${IMAGEPATH}zsjd.png" />
                <label class="nav2_lab zsjd" name="zsjd" onclick="changeNews(this)">生源基地</label>
            </div>
        </div>
    </div>

    <!-- E a -->
    <!-- S b -->

    <!-- E b -->

    <!-- S c -->

    <!-- E c -->

</div>
<!-- E frame-content -->
<#include "com/footer.ftl">
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/swiper.min.js"></script>

</body>
</html>
