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
                        <#else >
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
                        <ul class="news-ul">
                            <#if (first1??)&&(first1?size>0)>
                                <#list first1 as obj>
                                    <#if obj_index<4>
                                        <li>
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
                        <ul class="news-ul" style="display: none">
                        <#if (first2??)&&(first2?size>0)>
                            <#list first2 as obj>
                                <#if obj_index<4>
                                    <li>
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
                        <ul class="news-ul" style="display: none">
                        <#if (first3??)&&(first3?size>0)>
                            <#list first3 as obj>
                                <#if obj_index<4>
                                    <li>
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
                        <input type="text" placeholder="请输入用户名" class="login-act">
                    </li>
                    <li>
                        <label>
                            <i class="icon bg-login_password"></i>
                        </label>
                        <input type="password" placeholder="请输入密码" class="login-pswd">
                    </li>
                    <li>
                        <input type="button" value="登录" class="login-btn">
                    </li>
                </ul>
                <ul class="company-form" style="display:none">
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
                </ul>
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
                <#else >
                    <li onclick="openUrl('${obj.TZLJ!}')"><a>${obj.NAME}</a></li>
                </#if>
            </#list>
            </ul><a>MORE+</a></div>
            <div class="frame-body tabbar-frame_content">
                <ul class="frame-list">
                <#if (second1??)&&(second1?size>0)>
                    <#list second1 as obj>
                        <#if obj_index<8>
                            <li>
                                <ul class="job">
                                    <li>悉地（苏州）勘察设计顾问有限公司杭州分院招聘启示</li>
                                    <li><i class="icon bg-icon_dz"></i>浙江 · 杭州</li>
                                    <li>2019-04-08</li>
                                </ul>
                            </li>
                        </#if>
                    </#list>
                </#if>

                </ul>
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
        <div class="frame-b_left box">
            <div class="tabbar"><ul>
            <#list nav3 as obj>
                <#if obj_index==0>
                    <li onclick="openUrl('${obj.TZLJ!}')" class="active"><a>${obj.NAME}</a></li>
                <#else >
                    <li onclick="openUrl('${obj.TZLJ!}')"><a>${obj.NAME}</a></li>
                </#if>
            </#list>
            </ul><a>MORE+</a></div>
            <div class="frame-body tabbar-frame_content">
                <ul class="frame-list">
                    <li>
                        <ul class="job">
                            <li>浙江省人民政府办公厅关于加快发展众创空间促进创业创新得实施意见</li>
                            <li>2019-04-08</li>
                        </ul>
                    </li>
                    <li>
                        <ul class="job">
                            <li>浙江省人民政府办公厅关于加快发展众创空间促进创业创新得实施意见</li>
                            <li>2019-04-08</li>
                        </ul>
                    </li>
                    <li>
                        <ul class="job">
                            <li>浙江省人民政府办公厅关于加快发展众创空间促进创业创新得实施意见</li>
                            <li>2019-04-08</li>
                        </ul>
                    </li>
                    <li>
                        <ul class="job">
                            <li>浙江省人民政府办公厅关于加快发展众创空间促进创业创新得实施意见</li>
                            <li>2019-04-08</li>
                        </ul>
                    </li>
                    <li>
                        <ul class="job">
                            <li>浙江省人民政府办公厅关于加快发展众创空间促进创业创新得实施意见</li>
                            <li>2019-04-08</li>
                        </ul>
                    </li>
                    <li>
                        <ul class="job">
                            <li>浙江省人民政府办公厅关于加快发展众创空间促进创业创新得实施意见</li>
                            <li>2019-04-08</li>
                        </ul>
                    </li>

                </ul>
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
<script src="${base}/js/jui.min.js"></script>
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
            $(".company-form").hide();
        }else{
            $(".login-inform").hide();
            $(".company-form").show();
        }
        $(this).addClass('active').siblings().removeClass('active');
    })
    $(".tabbar ul li").mouseover(function(){
        // console.log($(this).index())
        $(this).addClass('active').siblings().removeClass('active');
        $(".tabbar-frame_content").find("ul").eq($(this).index()).show().siblings().hide();
    })
</script>
</body>
</html>
