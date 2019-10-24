<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/jui.css"/>
    <link rel="stylesheet" href="${base}/css/style.css"/>
    <style>
        .icon {
            /*display: inline-block;*/
            background: url('${base}/img/icon1.png') center center no-repeat;
        }
    </style>
</head>
<body>
<#include "com/header.ftl">
<div class="w1200 clearfix news-content">
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <img src="https://www.zust.edu.cn/images/20190917163010659znW.png" alt="">
            </div>
            <div class="swiper-slide">
                <img src="https://www.zust.edu.cn/images/20190916094148933Y6E.png" alt="">
            </div>
            <div class="swiper-slide">
                <img src="https://www.zust.edu.cn/images/20190123.png" alt="">
            </div>
            <div class="swiper-slide">
                <img src="https://www.zust.edu.cn/images/20191023105624358DzA.png" alt="">
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
    <div class="place">
        <i class="icon homeicon"></i>
        <em>
            <a href="../index.htm">首页</a>
            &gt;
            <a href="javascript:void(0)">师资</a>
            &gt;
            <a href="szjj.htm">师资简介</a>
        </em>
    </div>
    <div class="leftmenu fl">
        <dl class="sidemenu">
            <dt>师资简介</dt>
            <dd><a href="szjj.htm" id="sonchannel1"><i class="icon sideicon1"></i>师资简介</a><i class="icon sideicon2"></i></dd>
            <dd><a href="jsfc.htm" id="sonchannel2"><i class="icon sideicon1"></i>教师风采</a><i class="icon sideicon2"></i></dd>
            <dd><a href="http://ez.zust.edu.cn/login?url=http://fdc.zust.edu.cn/" id="sonchannel3"><i class="icon sideicon1"></i>教师发展</a><i class="icon sideicon2"></i></dd>
            <dd><a href="javascript:;"><i class="icon sideicon1"></i>人才招聘</a><i class="icon sideicon2"></i></dd>
        </dl>
    </div>
    <div class="rigCont fr">
        <div class="detailsRig pb30">
            <h4 class="detailsTitle">师资简介</h4>
            <div class="detailsCont" style="margin-top: 20px;">
                <div id="vsb_content_1014_u101"><div id="vsb_content"><p style="line-height: 2em; text-indent: 2em; margin-top: 10px; margin-bottom: 10px;"><span style="color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 16px;">学校坚持“人才强校”主战略，<span style="font-size: 16px;">大力实施人才发展“三三战略”行动计划，</span>建设一支具有国际化视野、学术水平一流、师德师风高尚、梯队结构合理的优秀人才队伍。现有教职工1300余名，专任教师970多名，其中高级职称470余名，博士比例占30%以上，具有企业实践经历占40%以上，享受国务院特殊津贴5人，国家级知名专家1人，全国优秀教师1人，全国教育系统职业道德建设标兵1人，教育部“新世纪优秀人才支持计划”入选者1人，省级知名专家2人，省“万人计划”1人，“钱江学者”特聘教授1人，省突出贡献中青年专家1人，省“151人才工程”培养人员78人，省高校中青年学科带头人28人，省优秀教师5人，省高校优秀教师3人，省高校教学名师7人，省级教学团队3个。</span></p></div></div>
            </div>
        </div>
    </div>
</div>

<#--    style="background-image: url(https://www.zust.edu.cn/images/20190917163010659znW.png); display: block;"></div>-->
<#--    style="background-image: url(https://www.zust.edu.cn/images/20190916094148933Y6E.jpg); display: block;"></div>-->
<#--    style="background-image: url(https://www.zust.edu.cn/images/20190123.jpg); display: block;"></div>-->
<#--    style="background-image: url(https://www.zust.edu.cn/images/20191023105624358DzA.jpg); display: block;"></div>-->
<#--    <div class="ban-item" style="background-image: url(https://www.zust.edu.cn/images/5.jpg); display: block;"></div>-->
<#--    style="background-image: url(https://www.zust.edu.cn/images/22.jpg); display: block;"></div>-->
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/jui.min.js"></script>

<script src="${base}/js/artdialog/jquery.artDialog.js?skin=blue"></script>
<script src="${base}/js/jquery.jdate.js"></script>
<script>
    var loginType = 0;
    var mySwiper = new Swiper ('.swiper-container', {
        autoplay:{
          stopOnlastSlide:true
        },
        loop: true, // 循环模式选项

        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination',
        },

    })
    $(".login-tabbar a").click(function () {
        if ($(this).index() == 0) {
            loginType = 0;
            $(".login-inform").show();
            $(".login-inform-qy").hide();
        } else {
            loginType = 1;
            $(".login-inform").hide();
            $(".login-inform-qy").show();
        }
        $(this).addClass('active').siblings().removeClass('active');
    })
    $(".tabbar ul li").mouseover(function () {
        $(this).addClass('active').siblings().removeClass('active');
        $(this).parents('.box').find(".tabbar-frame_content>ul").eq($(this).index()).show().siblings().hide();
    })

    function keyLogin() {
        if (event.keyCode == 13) {
            if (loginType == 0) {
                stuLogin();
            } else if (loginType == 1) {
                qyLogin();
            }
        }
    }

    function stuLogin() {
        beginLoad()
        if (!$("#yhDlzh").val().trim()) {
            walert("请填写账号");
            return;
        }
        if (!$("#yhDlmm").val().trim()) {
            walert("请填写密码");
            return;
        }
        var jsonObj = {
            "yhDlzh": $("#yhDlzh").val().trim(),
            "yhDlmm": $("#yhDlmm").val().trim().MD5()
        }
        ajax("zustcommon/bckjBizYhxx/logIn", jsonObj, function (data) {
            finishLoad()
            if (data.backCode == 0) {
                addCookie("stuOwid", data.bean.owid)
                addCookie("stuSjh", data.bean.sjh)
                addCookie("userType", "1") //1学生 0企业
                addCookie("stuXm", data.bean.xm)
                addCookie("yhOwid", data.bean.owid)
                location.reload();
            } else {
                walert(data.errorMess)
            }
        })
    }

    function qyLogin() {
        beginLoad()
        if (!$("#qyTysh").val().trim()) {
            walert("请填写社会统一信用码");
            return;
        }
        if (!$("#qyFrsfz").val().trim()) {
            walert("请填写法人身份证后六位");
            return;
        }
        var jsonObj = {
            "qyFrsfz": $("#qyFrsfz").val().trim(),
            "qyTysh": $("#qyTysh").val().trim()
        }
        ajax("zustjy/bckjBizQyxx/login", jsonObj, function (data) {
            finishLoad()
            if (data.backCode == 0) {
                addCookie("qyOwid", data.bean.owid)
                addCookie("qyInfo", JSON.stringify(data.bean))
                addCookie("userType", "0") //1学生 0企业
                addCookie("yhOwid", data.bean.owid)
                location.reload();
            } else {
                walert(data.errorMess)
            }
        })
    }

    $(document).ready(function () {

        if (getCookie("userType")) {
            if (getCookie("userType") == 0) {
                $(".frame-a_right").hide();
                $("#qy_pipe").show();
            } else if (getCookie("userType") == 1) {
                $(".frame-a_right").hide();
                $("#stu_tel").html(getCookie("stuXm"))
                $("#stu_pipe").show();
            }
        }
    })
</script>
</body>
</html>
