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
    .content-list{}
    .content-list ul li{ margin:20px; border:1px solid #ddd; padding:20px 10px; list-style-type: none }
    .content-list ul li .questions{ border-bottom:1px dashed #ddd; margin-bottom:15px;}
    .content-list ul li .questions h6{ color:#9fa0a0; padding:5px 0; font-size:12px; font-weight:300;}
    .content-list ul li .questions p{ padding:5px 0; line-height:20px; text-indent:2em;}
    .content-list ul li .answer{background-color:#f9f9f9; border:1px solid #959595; border-radius:5px; margin-left:10px;}
    .content-list ul li .answer-content {padding:0 10px;}
    .content-list ul li .answer .answer-content h6{color:#9fa0a0; padding:5px 0; font-size:12px; font-weight:300;}
    .content-list ul li .answer .answer-content p{ padding:5px 0; line-height:20px; color:#cc0000;}
    .content-list ul li .survey_answer{margin-left:20px;margin-top:5px ;}
    .content-list ul li .survey_answer ul{list-style:none;}
    .content-list ul li .survey_answer li{width:50%;float:left;border:none;margin:0; padding:0;}
    .content-list ul li .survey_answer span {cursor: hand;}
    .content-list .zxtw-form{ margin:20px; padding:0 0 20px 0; border-top:1px solid #ddd;}
    .content-list .zxtw-form h5{padding:8px 5px;}
    .content-list .zxtw-form p{ padding:5px;}
    .content-list .zxtw-form textarea{ width:827px; height:100px; margin-bottom:10px;}
    .path{ border-bottom:1px solid #d6d6d6; padding-bottom:10px;}
    .path a{ padding:0 5px;}
</style>
<body>
<#include "com/ZSheader.ftl">
<img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
<div class="main">
    <div class="container">
        <div class="content">
            <#include "com/subMenu.ftl">
                <div class="nav-bar">
                <#include "com/route.ftl">
                </div>
                <div class="content-list">
                    <ul id="wdListUi">
                        <li>
                            <div class="questions">
                                <h6><span class="fr">2019-10-14 03:19:21</span>1、来自58.101.36.84的提问</h6>
                                <p>科院授予的制药工程学士学位英文对应的是bachelor of science 还是bachelor of engineering？</p>
                            </div>
                            <div class="answer">
                                <div class="answer-content">
                                    <h6><span class="fr">2019-10-21 08:12:26</span><strong>管理员</strong>的回复：</h6>
                                    <p>详情咨询请拨打85070116</p>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="text-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">

                            </ul>
                        </nav>
                    </div>
                    <div class="zxtw-form">
                        <h5>我要留言</h5>
                        <p>
                            <textarea name="textarea" id="textarea" cols="40" rows="5"></textarea>
                        </p>
                        <p>
                            <button type="button" class="btn btn-default"
                                    style="background-color: rgb(85,167,153);color: #ffffff;width: 100px;height: 34px;">提交留言</button>
                        </p>
                    </div>
                </div>
        </div>
    </div>
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script>

</script>
</body>
</html>
