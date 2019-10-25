<#--<#assign header=[{"CODE": "14","TZLJ": "/","OWID": 14,"PX": 1,"BXLX": 1,"NAME": "首页"},-->
<#--{"JSY": "一日之计在于晨","CODE": "15","GGT": "20190927/1569571396885.png","OWID": 15,"PX": 2,"chirdMenu": [-->
<#--{"CODE": "22","SJHQDX": 5,"TZLJ": "articleTpl/1/0","OWID": 22,"BXLX": 0,"NAME": "学校简介"}],"BXLX": 1,"NAME": "查询服务"},-->
<#--{"JSY": "一日之计在于晨","CODE": "16","GGT": "20190927/1569571411984.png","OWID": 16,"PX": 3,"chirdMenu": [-->
<#--{"CODE": "26","SJHQDX": 5,"TZLJ": "newsList/2/0","OWID": 26,"PX": 1,"BXLX": 1,"NAME": "新闻快递"}],"BXLX": 1,"NAME": "新闻公告"},-->
<#--{"JSY": "一日之计在于晨","CODE": "17","SJHQDX": 0,"GGT": "20190927/1569571575462.png","OWID": 17,"PX": 4,"chirdMenu": [-->
<#--{"CODE": "28","SJHQDX": 1,"TZLJ": "recruitment/3/0","OWID": 28,"BXLX": 3,"NAME": "社会招聘会"}],"BXLX": 1,"NAME": "招聘信息"},-->
<#--{"JSY": "一日之计在于晨","CODE": "18","GGT": "20190927/1569571674347.png","OWID": 18,"PX": 5,"chirdMenu": [-->
<#--{"CODE": "33","SJHQDX": 5,"TZLJ": "newsList/4/0","OWID": 33,"BXLX": 1,"NAME": "政策法规"}],"BXLX": 1,"NAME": "职业指导"},-->
<#--{"JSY": "一日之计在于晨","CODE": "19","GGT": "20190927/1569571696580.png","OWID": 19,"PX": 6,"chirdMenu": [-->
<#--{"CODE": "38","SJHQDX": 5,"TZLJ": "articleTpl/5/0","OWID": 38,"BXLX": 0,"NAME": "招聘指南"}],"BXLX": 1,"NAME": "企业指南"},-->
<#--{"JSY": "一日之计在于晨","CODE": "20","GGT": "20190927/1569571786395.png","OWID": 20,"PX": 7,"chirdMenu": [-->
<#--{"CODE": "40","SJHQDX": 5,"TZLJ": "newsList/6/0","OWID": 40,"BXLX": 1,"NAME": "办事流程"}],"BXLX": 1,"NAME": "学生服务"},-->
<#--{"JSY": "一日之计在于晨","CODE": "21","GGT": "20190927/1569571810884.png","OWID": 21,"PX": 8,"chirdMenu": [-->
<#--{"CODE": "43","SJHQDX": 5,"TZLJ": "articleTpl/7/0","OWID": 43,"BXLX": 0,"NAME": "联系我们"}],"BXLX": 1,"NAME": "联系我们"}-->
<#--]-->
<#-->-->
<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="${base}/img/logo-zust.png">
                <div class="title">就业信息网</div>
                <div class="user-info" id="qyInfo" style="display: none;">
                    <a href="${base}/enterpriseService/0">欢迎您<span id="qyName"></span></a>,<a href="#" onclick="loginout()">退出</a>
                </div>
                <div class="user-info" id="stuInfo" style="display: none;">
                    <a href="${base}/stuCenter/0">欢迎您<span id="stuName"></span></a>,<a href="#" onclick="loginout()">退出</a>
                </div>
                <div class="search-bar" style="float: right;width: 350px;margin-top: 50px;margin-right:12px;border-bottom: none;">
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
                window.location.href="${base}/"
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
</script>
