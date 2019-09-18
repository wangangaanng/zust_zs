<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
</head>
<style>

</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i> 当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">学生服务</a></li>
                <li class="active">导师简介</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav" style="border: none;">
                <div class="td-item">
                    <div class="t-bg"></div>
                    <div class="td-detail">
                        <div class="t-name">张立庆</div>
                        <div class="t-xhx"><span></span></div>
                        <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>

                    </div>
                </div>
            </div>

            <div class="content-list" style="min-height: 707px;height:auto;">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">导师简介</div>
                    </div>
                    <div class="teacher-list">
                        <p class="td-sm">
                            张立庆，男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程教学指导委员会委员，浙江省高等学校化学类与化工制药类教学指导委员会委员。 长期从事化学教学与科研工作。在教学工作中，主持全国教育科学“十一五规划重点项目等省部级项目12项，作为主持人“物理化学和无机及分析化学系列课程教学团队”被评为浙江省高等学校省级教学团队，“物理化学’和“无机及分析化学”被评为浙江省精品课程，主编出版教材2部。获浙江省教学成果二等奖2项，浙江省教育科学优秀研究成果三等奖1项，浙江省“十二五”优秀教材奖1项。在科研工作中，主要从事多相催化与化工智能优化研究，主持浙江省自然科学基金等省部级项目5项，在国内外学术刊物发表论文70多篇，其中被SCI或EI收录22篇
                            。获浙江省科技进步三等奖，浙江省高等学校科研成果二等奖， 浙江省高等学校科研成果三等奖2项。获“国务院政府特殊津贴”，“全国教育系统职业道德建设标兵”，“浙江省劳动模范”，“浙江省首届高等学校教学名师”，“浙江省优秀教师”，“浙江省师德标兵”，“浙江省中青年学科带头人”，浙江科技学院首届“卓越教学奖”-等奖等荣誉称号。 标志性项目和成果
                            <br> 1、全国教育科学“十- -五’规划重点项目:高等教育大众化与数字化环境下高校课堂教学的实效性研究。<br> 2、浙江省自然科学基金项目:基于先验知识的粒子群算法在酯化反应催化剂优化设计中的应用研究。
                            <br> 3、“ 基础化学课程体系与应用技术相结合之研究"获浙江省教学成果二等奖。<br> 4、“大众化与数字化环境下化学类基础系列课程课堂教学改革研究与实践”获浙江省教学成果二等奖。
                            <br> 5、“基于培养学生科研开发能力的教学改革研究"获浙江省第五届教育科学优秀研究成果三等奖。
                            <br> 6、“速效感冒液中对乙酰氨基酚等五组分含量的同时测定研究'获浙江省高等学校科研成果三等奖。
                            <br> 7、“低碳醇氧化羰基化合成产物的相平衡性质研究”获浙江省高等学校科研成果三等奖。
                            <br> 成长感悟
                            <br> 坚持!
                            <br> 寄语青年学子
                            <br> 坚持!
                            <br>
                        </p>
                        <div class="td-ask">
                            <button class="btn">我要咨询</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script>
    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        $(".content-list").eq($(this).index()).show();
    })
</script>
</body>

</html>