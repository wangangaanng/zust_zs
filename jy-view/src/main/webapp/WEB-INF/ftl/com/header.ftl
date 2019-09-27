<#--<#assign nav=-->
<#--[{'url':'','title':'首页'},-->
<#--{'url':'','title':'学院概况','sub':[{'url':'articleTpl/college/0','title':'学校简介'},{'url':'articleTpl/1','title':'学院专业'},{'url':'articleTpl/2','title':'中心介绍'}]},-->
<#--{'url':'','title':'新闻公告','sub':[{'url':'newsList','title':'通知公告'},{'url':'/newsList','title':'新闻快递'},{'url':'/newsList','title':'校内公示'}]},-->
<#--{'url':'','title':'招聘信息','sub':[{'url':'recruitment','title':'浙科院·职来职往'},{'url':'recruitment','title':'社会招聘会'},{'url':'recruitment','title':'企业招聘信息'},{'url':'recruitment','title':'职位招聘信息'},{'url':'announcement','title':'招考公告'}]},-->
<#--{'url':'','title':'职业指导','sub':[{'url':'newsList','title':'政策法规'},{'url':'newsList','title':'就业指导'},{'url':'newsList','title':'创业指导'},{'url':'newsList','title':'生涯规划'},{'url':'newsList','title':'技能培训'}]},-->
<#--{'url':'','title':'企业指南','sub':[{'url':'enterpriseGuide','title':'招聘指南'},{'url':'enterpriseGuide','title':'生源速览'}]},-->
<#--{'url':'','title':'学生服务','sub':[{'url':'studentService','title':'办事流程'},{'url':'studentService','title':'常用下载'},{'url':'studentService','title':'档案查询'}]},-->
<#--{'url':'contactUs','title':'联系我们'}]-->
<#-->-->
<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="${base}/img/logo-zust.png">
                <div class="title">就业信息网</div>
                <div class="user-info" id="qyInfo" style="display: none;">
                    <a href="/enterpriseService/0">欢迎您<span id="qyName"></span></a>,<a href="#" onclick="loginout()">退出</a>
                </div>
                <div class="user-info" id="stuInfo" style="display: none;">
                    <a href="/stuCenter/0">欢迎您<span id="stuName"></span></a>,<a href="#" onclick="loginout()">退出</a>
                </div>
                <div class="search-bar" style="float: right;width: 350px;margin-top: 50px;margin-right:12px;border-bottom: none;display: none;">
                    <div class="input-group">
                        <input type="text" class="form-control" onkeydown="keySearch()" id="gjz22" placeholder="请输入">
                        <span class="input-group-btn">
                            <button class="btn btn-default header-search" type="button" onclick="searchAll()">搜索</button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="nav">
                <ul>
                    <#if header??>
                    <#list header as obj>
                        <li class="nav-item" onclick='openUrl("${obj.TZLJ!''}")'>${obj.NAME}
                            <#--<#if obj.sub??>-->
                                <#--<ul class='subnav'>-->
                                    <#--<#list obj.sub as sub>-->
                                        <#--<li onclick='openUrl("${sub.url!''}")'><a><span>${sub.title}</span></a></li>-->
                                    <#--</#list>-->
                                <#--</ul>-->
                            <#--</#if>-->
                        </li>
                    </#list>
                    </#if>
                </ul>
                <div class="subnav-wrap">
                <#if header??>
                <#list header as obj>
                    <#if obj.chirdMenu??>
                        <div class="subnav-cont" rel="${obj_index}">
                            <div class="subnav-left fl"><img alt="${imagePath}${obj.GGT!'defaultImg.png'}" src="${imagePath}${obj.GGT!'defaultImg.png'}">
                                <div class="subnav-motto">
                                    <p>${obj.JSY!''}</p>
                                    <#--<p class="tr">——张德江</p>-->
                                </div></div>
                            <ul class="clearfix subnav fr">
                                <#list obj.chirdMenu as sub>
                                <li onclick='openUrl("${sub.TZLJ!''}")'><a>${sub.NAME}</a></li>
                                </#list>
                            </ul>
                        </div>
                    </#if>
                </#list>
                </#if>
                </div>
            </div>
        </div>
    </div>
</header>
<script src="${base}/js/md5.min.js"></script>
<script>
    $('.nav li').each(function(i,v){
        $(this).hover(function(){
            if(i != 0){
                $(this).addClass('current').siblings().removeClass('current');
                $('.subnav-wrap').show();
                $('.subnav-wrap .subnav-cont').eq(i-1).stop().fadeIn().siblings().hide();
            }else{
                $('.nav li').removeClass('current');
                $('.subnav-wrap').hide();
                $('.subnav-wrap .subnav-cont').hide();
            }
        })
    })
    $('.nav').mouseleave(function(){
        $('.nav li').removeClass('current');
        $('.subnav-wrap').hide();
        $('.subnav-wrap .subnav-cont').hide();
    })
    function openUrl(url) {
        if(url){
            if(url=='/'){
                window.location.href=url
            }else{
                window.location.href="${base}/"+url
            }
        }else{

        }

    }
    function linkUrl(url) {
        if(url){
            window.open(url)
        }
    }
    function isopenUrl(url) {
        if(getCookie('stuOwid')){
            window.location.href="${base}/"+url
        }else {
           login(url)
        }
    }
</script>
