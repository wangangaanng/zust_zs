<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>就业网</title>
    <#include "com/config.ftl">
    <link rel="stylesheet" href="${base}/css/swiper.min.css" />
    <link rel="stylesheet" href="${base}/css/jui.css" />
    <link rel="stylesheet" href="${base}/css/style.css" />
</head>
<body>
<#include "com/header.ftl">
<!-- S frame-content -->
<div class="frame-wrap">
    <!-- S  a-->
    <div class="frame-a">
        <!-- S  a_left-->
        <div class="frame-a_left box">
            <div class="tabbar">
                <ul>
                    <#list nav1 as obj>
                        <#if obj_index==0>
                            <li onclick="openUrl('${obj.TZLJ!}')" class="active"><a>${obj.NAME}</a></li>
                        <#elseif obj_index<5>
                            <li onclick="openUrl('${obj.TZLJ!}')"><a>${obj.NAME}</a></li>
                        </#if>
                    </#list>
                </ul><a>MORE+</a></div>
            <div class="frame-body">
                <!-- Swiper -->
                <div class="swiper-container news-swiper">
                    <div class="swiper-wrapper">
                    <#if (first1??)&&(first1?size>0)>
                        <#list first1 as obj>
                        <#if obj.tpjj??>
                            <div class="swiper-slide"><img alt="${obj.wzbt}" src="${imagePath+obj.tpjj}" /></div>
                        </#if>
                        </#list>
                    </#if>
                    </div>
                    <!-- Add Pagination -->
                    <div class="swiper-pagination"></div>
                </div>
                <div class="index-news_list">
                    <div class="tabbar-frame_content">
                    <#list first as objl>
                        <#if objl_index==0>
                            <ul class="news-ul">
                        <#else >
                            <ul class="news-ul" style="display: none">
                        </#if>
                            <#if (objl??)&&(objl?size>0)>
                                <#list objl as obj>
                                    <#if obj_index<4>
                                        <li data-owid="${obj.owid}" onclick="openUrl('newsDetail/${obj.owid!''}')">
                                            <div class="news-date">
                                                <#if obj.fbsj?exists>
                                                    <span>${obj.fbsj?substring(5,7)}.${obj.fbsj?substring(8,10)}</span>
                                                    <em>${obj.fbsj?substring(0,4)}</em>
                                                </#if>
                                            </div>
                                            <div class="news-content">
                                            ${obj.wzbt!''}
                                            </div>
                                        </li>
                                    </#if>
                                </#list>
                            </#if>
                        </ul>
                    </#list>
                    </div>

                </div>
            </div>
        </div>
        <!-- E  a_left-->
        <!-- S  a_right-->
        <div class="frame-a_right box">
            <div class="login-tabbar">
                <a class="active">学生登录</a><a>企业通道</a>
            </div>
            <div>
                <ul class="login-inform">
                    <li>
                        <label>
                            <i class="icon bg-login_user"></i>
                        </label>
                        <input type="text" id="yhDlzh" placeholder="请输入用户名" class="login-act">
                    </li>
                    <li>
                        <label>
                            <i class="icon bg-login_password"></i>
                        </label>
                        <input type="password" id="yhDlmm" placeholder="请输入密码" class="login-pswd">
                    </li>
                    <li>
                        <input type="button" onclick="stuLogin()" value="登录" class="login-btn">
                    </li>
                </ul>
                <#--<ul class="company-form" style="display:none">
                    <li><p>专门为企业开通的绿色通道！</p></li>
                    <li>
                        <i class="icon bg-qy_fb"></i><em>发布职位信息</em>
                    </li>
                    <li>
                        <i class="icon bg-qy_sq"></i><em>申请招聘会</em>
                    </li>
                    <li>
                        <i class="icon bg-qy_xc"></i><em>申请宣讲会</em>
                    </li>
                </ul>-->
                <ul class="login-inform-qy" style="display:none">
                    <li>
                        <label>
                            <i class="icon bg-login_user"></i>
                        </label>
                        <input type="text" id="qyTysh" placeholder="请社会统一信用码" class="login-act">
                    </li>
                    <li>
                        <label>
                            <i class="icon bg-login_password"></i>
                        </label>
                        <input type="text" id="qyFrsfz" placeholder="请输入法人身份证后六位" class="login-pswd">
                    </li>
                    <li>
                        还没有账号？<a href="/enterpriseReg">注册</a>
                    </li>
                    <li>
                        <input type="button" value="登录" onclick="qyLogin()" class="login-btn">
                    </li>
                </ul>
            </div>
        </div>
        <div class="frame-a_right box" id="qy_pipe" style="display: none;">
            <ul class="company-form">
                <li><p>专门为企业开通的绿色通道！</p></li>
                <li class="link2" onclick="window.location.href='/enterpriseService/1'">
                    <i class="icon bg-qy_fb"></i><em>发布职位信息</em>
                </li>
                <li class="link2" onclick="window.location.href='/enterpriseService/3'">
                    <i class="icon bg-qy_sq"></i><em>申请招聘会</em>
                </li>
                <li class="link2" onclick="window.location.href='/enterpriseService/2'">
                    <i class="icon bg-qy_xc"></i><em>申请宣讲会</em>
                </li>
            </ul>
        </div>
        <div class="frame-a_right box" id="stu_pipe" style="display: none;">
            <div class="stu_info">
                <img src="${base}/img/menu1.png" />
                <p>欢迎您，<span id="stu_tel"></span></p>
            </div>
        </div>
        <!-- E a_right-->
    </div>
    <!-- E a -->
    <!-- S b -->
    <div class="frame-b">
        <div class="frame-b_left box">
            <div class="tabbar"><ul>
            <#list nav2 as obj>
                <#if obj_index==0>
                    <li onclick="openUrl('${obj.TZLJ!}')" class="active"><a>${obj.NAME}</a></li>
                <#elseif obj_index<5>
                    <li onclick="openUrl('${obj.TZLJ!}')"><a>${obj.NAME}</a></li>
                </#if>
            </#list>
            </ul><a>MORE+</a></div>
            <div class="frame-body tabbar-frame_content">
                <#list second as objl>
                    <#if objl_index==0>
                    <ul class="frame-list">
                    <#else >
                    <ul class="frame-list" style="display: none">
                    </#if>
                    <#if (objl??)&&(objl?size>0)>
                        <#list objl as obj>
                            <#if obj_index<8>
                                <li data-owid="${obj.owid}" onclick="openUrl('positionDetail/${obj.owid!''}')">
                                    <ul class="job">
                                        <li>${obj.zwbt}</li>
                                        <li><i class="icon bg-icon_dz"></i>${obj.zwPro!''} · ${obj.zwCity!''} · ${obj.zwArea}</li>
                                        <li>
                                            <#if obj.zphKsrq?exists>
                                               ${obj.zphKsrq?substring(0,10)}
                                            </#if>
                                        </li>
                                    </ul>
                                </li>
                            </#if>
                        </#list>
                    </#if>
                </ul>
                </#list>
                <#list secondwz as objl>
                    <ul class="frame-list" style="display: none">
                    <#if (objl??)&&(objl?size>0)>
                        <#list objl as obj>
                            <#if obj_index<8>
                                <li data-owid="${obj.owid}" onclick="openUrl('newsDetail/${obj.owid!''}')">
                                    <ul class="job">
                                        <li>${obj.wzbt!''}</li>
                                        <li>
                                            <#if obj.fbsj?exists>
                                               ${obj.fbsj?substring(0,10)}
                                            </#if>
                                        </li>
                                    </ul>
                                </li>
                            </#if>
                        </#list>
                    </#if>
                </ul>
                </#list>
            </div>

        </div>
        <div class="frame-b_right box">
            <div class="calendar">
                <div class="calendar-bar">
                    <i class="icon bg-icon_rl"></i>招聘日历
                </div>
                <div class="calendar-content" id="datepicker">
                </div>
            </div>
        </div>
    </div>
    <!-- E b -->
    <div>
        <ul class="adv">
            <li><img src="${base}/img/pic1.png" /></li>
            <li><img src="${base}/img/pic2.png" /></li>
            <li><img src="${base}/img/pic3.png" /></li>
        </ul>
    </div>
    <!-- S c -->
    <div class="frame-c">
        <div class="frame-c_left box">
            <div class="tabbar"><ul>
            <#list nav3 as obj>
                <#if obj_index==0>
                    <li onclick="openUrl('${obj.TZLJ!}')" class="active"><a>${obj.NAME}</a></li>
                <#elseif obj_index<5>
                    <li onclick="openUrl('${obj.TZLJ!}')"><a>${obj.NAME}</a></li>
                </#if>
            </#list>
            </ul><a>MORE+</a></div>
            <div class="frame-body tabbar-frame_content">
                <#list third as objl>
                    <#if objl_index==0>
                    <ul class="frame-list">
                    <#else >
                    <ul class="frame-list" style="display: none">
                    </#if>
                    <#if (objl??)&&(objl?size>0)>
                        <#list objl as obj>
                            <#if obj_index<6>
                                <li data-owid="${obj.owid}">
                                    <ul class="job">
                                        <li>${obj.wzbt!''}</li>
                                        <li>
                                            <#if obj.fbsj?exists>
                                               ${obj.fbsj?substring(0,10)}
                                            </#if>
                                        </li>
                                    </ul>
                                </li>
                            </#if>
                        </#list>
                    </#if>
                </ul>
                </#list>
            </div>
        </div>
        <div class="frame-c_right box">
            <div class="menue-container" >
                <div class="menue-parent">
                    <div class="menue-item">
                        <div>
                            <img src="${base}/img/menu1.png" />
                            <div>
                                <strong>全国大学生</strong><br><span>一站式服务系统</span>
                            </div>
                        </div>
                    </div>
                    <div class="menue-item">
                        <div>
                            <img src="${base}/img/menu2.png" />
                            <div>
                                <br><strong>职业规划测评</strong>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="menue-parent">
                    <div class="menue-item">
                        <div>
                            <img src="${base}/img/menu3.png" />
                            <div>
                                <br><strong>职业咨询预约</strong>
                            </div>
                        </div>
                    </div>
                    <div class="menue-item">
                        <div>
                            <img src="${base}/img/menu4.png" />
                            <div>
                                <br><strong>就业调查</strong>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- E c -->
    <div>
        <ul class="link">
            <li><img src="${base}/img/link1.png" /></li>
            <li><img src="${base}/img/link2.png" /></li>
            <li><img src="${base}/img/link3.png" /></li>
            <li><img src="${base}/img/link4.png" /></li>
            <li><img src="${base}/img/link5.png" /></li>
        </ul>
    </div>
</div>
<!-- E frame-content -->
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/common.js"></script>
<script src="${base}/js/jui.min.js"></script>
<script src="${base}/js/md5.min.js"></script>
<script src="${base}/js/artdialog/jquery.artDialog.js?skin=blue"></script>
<script src="${base}/js/jquery.jdate.js"></script>
<script>
    var swiper = new Swiper('.news-swiper', {
        pagination: {
            el: '.swiper-pagination',
            clickable :true,
        },
        autoplay:true,
    });
    $(".login-tabbar a").click(function(){
        if($(this).index()==0){
            $(".login-inform").show();
            $(".login-inform-qy").hide();
        }else{
            $(".login-inform").hide();
            $(".login-inform-qy").show();
        }
        $(this).addClass('active').siblings().removeClass('active');
    })
    $(".tabbar ul li").mouseover(function(){
        $(this).addClass('active').siblings().removeClass('active');
        $(this).parents('.box').find(".tabbar-frame_content>ul").eq($(this).index()).show().siblings().hide();
    })

    function stuLogin() {
        var jsonObj={
            "yhDlzh":$("#yhDlzh").val().trim(),
            "yhDlmm":$("#yhDlmm").val().trim().MD5()
        }
        ajax("zustcommon/bckjBizYhxx/logIn", jsonObj, function (data) {
            console.log(data)
            if(data.backCode==0){
                addCookie("stuOwid",data.bean.owid)
                addCookie("stuSjh",data.bean.sjh)
                location.reload();
            }else{
                walert(data.errorMess)
            }
        })
    }

    function qyLogin() {
        var jsonObj={
            "qyFrsfz":$("#qyFrsfz").val().trim(),
            "qyTysh":$("#qyTysh").val().trim()
        }
        ajax("zustjy/bckjBizQyxx/login", jsonObj, function (data) {
            console.log(data)
            if(data.backCode==0){
                addCookie("qyOwid",data.bean.owid)
                addCookie("qyInfo",JSON.stringify(data.bean))
                location.reload();
            }else{
                walert(data.errorMess)
            }
        })
    }

    $(document).ready(function () {
        if(getCookie("qyOwid")){
            $(".frame-a_right").hide();
            $("#qy_pipe").show();
        }
        if(getCookie("stuOwid")){
            $(".frame-a_right").hide();
            $("#stu_tel").html(getCookie("stuSjh").substring(0,3)+"****"+getCookie("stuSjh").substring(7,11))
            $("#stu_pipe").show();
        }

    })
</script>
</body>
</html>
