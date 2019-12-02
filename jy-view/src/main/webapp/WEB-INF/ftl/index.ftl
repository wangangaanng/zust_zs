<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
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
                </ul><a href="${base}/newsList/2/0" target="_blank">MORE+</a></div>
            <div class="frame-body">
                <!-- Swiper -->
                <div class="swiper-container news-swiper">
                    <div class="swiper-wrapper">
                        <#if (newsImg??)&&(newsImg?size>0)>
                            <#assign count=0>
                            <#list newsImg as obj>
                                <#if (obj.tpjj??)&&(count<5)>
                                    <div class="swiper-slide"><img alt="${obj.wzbt}" title="${obj.wzbt}" onclick="openUrl('newsDetail/${obj.owid!''}')" src="${imagePath+obj.tpjj}" /></div>
                                    <#assign count+=1>
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
                                            <div class="news-content" title="${obj.wzbt!''}">
                                            ${obj.wzbt!''}
                                            </div>
                                        </li>
                                    </#if>
                                </#list>
                            <#else >
                                <p style="text-align: center;margin: 125px auto;">暂无数据</p>
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
                        <input type="text" id="yhDlzh" onkeydown="keyLogin()" placeholder="请输入身份证号" class="login-act">
                    </li>
                    <li>
                        <label>
                            <i class="icon bg-login_password"></i>
                        </label>
                        <input type="password" id="yhDlmm" onkeydown="keyLogin()" placeholder="请输入身份证后六位" class="login-pswd">
                    </li>
                    <li>
                        <a href="https://authserver.zust.edu.cn/authserver/login?service=https://job.zust.edu.cn/proxyLogin">校内统一身份认证登录</a>
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
                        <input type="text" id="qyTysh" onkeydown="keyLogin()" placeholder="请输入社会统一信用码" class="login-act">
                    </li>
                    <li>
                        <label>
                            <i class="icon bg-login_password"></i>
                        </label>
                        <input type="password" id="qyFrsfz" onkeydown="keyLogin()" placeholder="法人身份证后六位或统一信用码后六位" class="login-pswd">
                    </li>
                    <li>
                        还没有账号？<a href="${base}/enterpriseReg">注册</a>
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
                <li class="link2" onclick="window.location.href='${base}/enterpriseService/1'">
                    <i class="icon bg-qy_fb"></i><em>发布职位信息</em>
                </li>
                <li class="link2" onclick="window.location.href='${base}/enterpriseService/2'">
                    <i class="icon bg-qy_xc"></i><em>申请宣讲会</em>
                </li>
                <li class="link2" onclick="window.location.href='${base}/enterpriseService/3'">
                    <i class="icon bg-qy_sq"></i><em>申请招聘会</em>
                </li>
            </ul>
        </div>
        <div class="frame-a_right box" id="stu_pipe" style="display: none;">
            <#--<div class="stu_info">
                <img src="${base}/img/menu1.png" />
                <p>欢迎您，<span id="stu_tel"></span></p>
            </div>-->
            <ul class="company-form company-form1">
                <li><p>欢迎您，<span id="stu_tel"></span></p></li>
                <li class="link2" onclick="window.location.href='${base}/stuCenter/4'">
                    <i class="icon bg-qy_sq"></i><em>个人信息</em>
                </li>
                <li class="link2" onclick="window.location.href='${base}/stuCenter/0'">
                    <i class="icon bg-qy_fb"></i><em>导师咨询</em>
                </li>
                <li class="link2" onclick="window.location.href='${base}/stuCenter/2'">
                    <i class="icon bg-qy_xc"></i><em>报名预约</em>
                </li>
                <li class="link2" onclick="window.location.href='${base}/stuCenter/5'">
                    <i class="icon bg-icon_fa"></i><em>就业方案</em>
                </li>
            </ul>
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
            </ul><a href="${base}/recruitment/3/0" target="_blank">MORE+</a></div>
            <div class="frame-body tabbar-frame_content">
                <#list second as objl>
                    <#if objl_index==0>
                    <ul class="frame-list">
                    <#else>
                    <ul class="frame-list" style="display: none">
                    </#if>
                    <#if (objl??)&&(objl?size>0)>
                        <#list objl as obj>
                        <#if obj.zwlx??>
                            <#if obj_index<8>
                                <li data-owid="${obj.owid}" onclick="openUrl('positionDetail/${obj.owid!''}')">
                                    <ul class="job">
                                        <li title="${obj.zwbt!''}">${obj.zwbt!''}</li>
                                        <#if obj.zwlx==1>
                                            <li title="${obj.zwCity!''}"><i class="icon bg-icon_dz"></i>${obj.zwCity!''}</li>
                                            <li>
                                                <#if obj.zphKsrq?exists>
                                               ${obj.zphKsrq?substring(0,10)}
                                            </#if>
                                            </li>
                                        <#elseif obj.zwlx==2>
                                            <li>
                                                <#if obj.createtime?exists>
                                               ${obj.createtime?substring(0,10)}
                                            </#if>
                                            </li>
                                        <#elseif obj.zwlx==3>
                                            <li title="${obj.zphJbdd!''}"><i class="icon bg-icon_dz"></i>${obj.zphJbdd!''}</li>
                                            <li>
                                                <#if obj.zphKsrq?exists>
                                               ${obj.zphKsrq?substring(0,10)}
                                            </#if>
                                            </li>
                                        <#elseif obj.zwlx==0>
                                            <li title="${obj.qymc!''}">${obj.qymc!''}</li>
                                            <li>
                                                <#if obj.createtime?exists>
                                               ${obj.createtime?substring(0,10)}
                                            </#if>
                                            </li>
                                        <#elseif objl_index==4>
                                            <li>
                                                <#if obj.createtime?exists>
                                               ${obj.createtime?substring(0,10)}
                                            </#if>
                                            </li>
                                        </#if>

                                    </ul>
                                </li>
                            </#if>
                        <#else >
                            <#if obj_index<8>
                                <li data-owid="${obj.owid}" onclick="openUrl('newsDetail/${obj.owid!''}')">
                                    <ul class="job">
                                        <li title="${obj.wzbt!''}">${obj.wzbt!''}</li>
                                        <li>
                                            <#if obj.fbsj?exists>
                                               ${obj.fbsj?substring(0,10)}
                                            </#if>
                                        </li>
                                    </ul>
                                </li>
                            </#if>
                        </#if>

                        </#list>
                    <#else >
                    <p style="text-align: center;margin: 140px auto;">暂无数据</p>
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
            <li onclick="linkUrl('http://gzdc.zjedu.gov.cn/')"><img src="${base}/img/pic1.png" /></li>
            <li onclick="linkUrl('http://www.ejobmart.cn/jyxt-v5/xtgl/index/initMenu.zf')"><img src="${base}/img/pic2.png" /></li>
            <li onclick="linkUrl('http://172.16.13.106/ranking')"><img src="${base}/img/pic3.png" /></li>
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
            </ul><a href="${base}/newsList/4/0" target="_blank">MORE+</a></div>
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
                                <li data-owid="${obj.owid}" onclick="openUrl('newsDetail/${obj.owid!''}')">
                                    <ul class="job">
                                        <li title="${obj.wzbt!''}">${obj.wzbt!''}</li>
                                        <li>
                                            <#if obj.fbsj?exists>
                                               ${obj.fbsj?substring(0,10)}
                                            </#if>
                                        </li>
                                    </ul>
                                </li>
                            </#if>
                        </#list>
                    <#else >
                        <p style="text-align: center;margin: 100px auto;">暂无数据</p>
                    </#if>
                </ul>
                </#list>
            </div>
        </div>
        <div class="frame-c_right box">
            <div class="menue-container" >
                <div class="menue-parent">
                    <div class="menue-item">
                        <div onclick="linkUrl('http://www.ncss.org.cn/')">
                            <img src="${base}/img/menu1.png" />
                            <div>
                                <strong>全国大学生</strong><br><span>一站式服务系统</span>
                            </div>
                        </div>
                    </div>
                    <div class="menue-item">
                        <div onclick="linkUrl('http://zust.careersky.cn/jixun/')">
                            <img src="${base}/img/menu2.png" />
                            <div>
                                <br><strong>职业规划测评</strong>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="menue-parent">
                    <div class="menue-item" onclick="isopenUrl('stuCenter/0')">
                        <div>
                            <img src="${base}/img/menu3.png" />
                            <div>
                                <br><strong>职业咨询预约</strong>
                            </div>
                        </div>
                    </div>
                    <div class="menue-item" onclick="openUrl('inquiry')">
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
            <#if (tplink??)&&(tplink?size>0)>
            <#list tplink as ob>
                <#if (ob_index==0)&&(ob.chirdMenu??)&&(ob.chirdMenu?size>0)>
                    <#list ob.chirdMenu as obj>
                        <li><img onclick="linkUrl('${obj.TZLJ!''}')" src="${imagePath}${obj.GGT!''}" /></li>
                    </#list>
                </#if>
            </#list>
            </#if>
        </ul>
    </div>
</div>
<!-- E frame-content -->
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/jui.min.js"></script>

<script src="${base}/js/artdialog/jquery.artDialog.js?skin=blue"></script>
<script src="${base}/js/jquery.jdate.js"></script>
<script>
    var loginType=0;
    var swiper = new Swiper('.news-swiper', {
        pagination: {
            el: '.swiper-pagination',
            clickable :true,
        },
        autoplay:{
            delay: 5000,
        },
        loop:true,

    });
    $(".login-tabbar a").click(function(){
        if($(this).index()==0){
            loginType=0;
            $(".login-inform").show();
            $(".login-inform-qy").hide();
        }else{
            loginType=1;
            $(".login-inform").hide();
            $(".login-inform-qy").show();
        }
        $(this).addClass('active').siblings().removeClass('active');
    })
    $(".tabbar ul li").mouseover(function(){
        $(this).addClass('active').siblings().removeClass('active');
        $(this).parents('.box').find(".tabbar-frame_content>ul").eq($(this).index()).show().siblings().hide();
    })

    function keyLogin(){
        if (event.keyCode==13){
            if(loginType==0){
                stuLogin();
            }else if(loginType==1){
                qyLogin();
            }
        }
    }

    function stuLogin() {
        beginLoad()
        if(!$("#yhDlzh").val().trim()){
            walert("请填写账号");
            return;
        }
        if(!$("#yhDlmm").val().trim()){
            walert("请填写密码");
            return;
        }
        var jsonObj={
            "yhDlzh":$("#yhDlzh").val().trim(),
            "yhDlmm":$("#yhDlmm").val().trim().MD5()
        }
        ajax("zustcommon/bckjBizYhxx/logIn", jsonObj, function (data) {
            finishLoad()
            if(data.backCode==0){
                addCookie("stuOwid",data.bean.owid)
                addCookie("stuSjh",data.bean.sjh)
                addCookie("userType","1") //1学生 0企业
                addCookie("stuXm",data.bean.xm)
                addCookie("stuXh",data.bean.xsxh)
                addCookie("yhOwid",data.bean.owid)
                location.reload();
            }else{
                walert(data.errorMess)
            }
        })
    }

    function qyLogin() {
        beginLoad()
        if(!$("#qyTysh").val().trim()){
            walert("请填写社会统一信用码");
            return;
        }
        if(!$("#qyFrsfz").val().trim()){
            walert("请填写法人身份证后六位或统一信用码后六位");
            return;
        }
        var jsonObj={
            "qyFrsfz":$("#qyFrsfz").val().trim(),
            "qyTysh":$("#qyTysh").val().trim()
        }
        ajax("zustjy/bckjBizQyxx/login", jsonObj, function (data) {
            finishLoad()
            if(data.backCode==0){
//                console.log(JSON.stringify(data.bean));
                addCookie("qyOwid",data.bean.owid)
//                addCookie("qyInfo",data.bean.qymc)
                var obj={}
                obj.qymc=data.bean.qymc;
                obj.qyLxr=data.bean.qyLxr;
                obj.qyLxrdh=data.bean.qyLxrdh;
                obj.qyYx=data.bean.qyYx;
                addCookie("qyInfo",JSON.stringify(obj));
                addCookie("userType","0") //1学生 0企业
                addCookie("yhOwid",data.bean.owid)
                location.reload();
            }else{
                walert(data.errorMess)
            }
        })
    }

    $(document).ready(function () {
        if(getQueryString("mess")==1){
            layer.open({
                title:'提示',
                scrollbar: false,
                closeBtn: 0,
                content: '尚未获取到当前认证学号，请直接在首页登录',
                yes: function(index, layero){
                    window.location.replace(base+'/')
                    layer.close(index);
                }
            });
        }else if(getQueryString("mess")==2){
            layer.open({
                title:'提示',
                scrollbar: false,
                closeBtn: 0,
                content: '请在首页用身份证和身份证后六位登录后在生源信息完善学号',
                yes: function(index, layero){
                    window.location.replace(base+'/')
                    layer.close(index);
                }
            });
        }

        if(getCookie("userType")){
            if(getCookie("userType")==0){
                $(".frame-a_right").hide();
                $("#qy_pipe").show();
            }else if(getCookie("userType")==1){
                $(".frame-a_right").hide();
                $("#stu_tel").html(getCookie("stuXm"))
                $("#stu_pipe").show();
            }
        }
    })
</script>
</body>
</html>
