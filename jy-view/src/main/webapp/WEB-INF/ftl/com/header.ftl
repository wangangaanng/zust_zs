<#assign nav=[{'url':'${base}','title':'首页'},
{'url':'','title':'学院概况','sub':[{'url':'${base}/articleTpl','title':'学校简介'},{'url':'${base}/articleTpl','title':'学院专业'},{'url':'${base}/articleTpl','title':'中心介绍'}]},
{'url':'','title':'新闻公告','sub':[{'url':'${base}/newsList','title':'通知公告'},{'url':'${base}/newsList','title':'新闻快递'},{'url':'${base}/newsList','title':'校内公示'}]},
{'url':'','title':'招聘信息','sub':[{'url':'${base}/recruitment','title':'浙科院·职来职往'},{'url':'${base}/recruitment','title':'社会招聘会'},{'url':'${base}/recruitment','title':'企业招聘信息'},{'url':'${base}/recruitment','title':'职位招聘信息'},{'url':'${base}/announcement','title':'招考公告'}]},
{'url':'','title':'职业指导','sub':[{'url':'${base}/newsList','title':'政策法规'},{'url':'${base}/newsList','title':'就业指导'},{'url':'${base}/newsList','title':'创业指导'},{'url':'${base}/newsList','title':'生涯规划'},{'url':'${base}/newsList','title':'技能培训'}]},
{'url':'','title':'企业指南','sub':[{'url':'${base}/enterpriseGuide','title':'招聘指南'},{'url':'${base}/enterpriseGuide','title':'生源速览'}]},
{'url':'','title':'学生服务','sub':[{'url':'${base}/studentService','title':'办事流程'},{'url':'${base}/studentService','title':'常用下载'},{'url':'${base}/studentService','title':'档案查询'}]},
{'url':'${base}/contactUs','title':'联系我们'}]>
<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="img/logo-zust.png">
                <div class="title">就业信息网</div>
            </div>
            <div class="nav">
                <ul>
                    <#list nav as obj>
                        <li class="nav-item" onclick='openUrl("${obj.url!''}")'>${obj.title}
                            <#--<#if obj.sub??>-->
                                <#--<ul class='subnav'>-->
                                    <#--<#list obj.sub as sub>-->
                                        <#--<li onclick='openUrl("${sub.url!''}")'><a><span>${sub.title}</span></a></li>-->
                                    <#--</#list>-->
                                <#--</ul>-->
                            <#--</#if>-->
                        </li>
                    </#list>

                </ul>
                <div class="subnav-wrap">
                <#list nav as obj>
                    <#if obj.sub??>
                        <div class="subnav-cont" rel="${obj_index}">
                            <div class="subnav-left fl"><img alt="" src="">
                                <div class="subnav-motto">
                                    <p>真山真水，真是读书好地方。</p>
                                    <p class="tr">——张德江</p>
                                </div></div>
                            <ul class="clearfix subnav fr">
                                <#list obj.sub as sub>
                                <li onclick='openUrl("${sub.url!''}")'><a>${sub.title}</a></li>
                                </#list>
                            </ul>
                        </div>
                    </#if>
                </#list>
                </div>
            </div>
        </div>
    </div>
</header>
<script>
    $('.nav li').each(function(i,v){
        $(this).hover(function(){
            if((i != 0)&&(i!=$('.nav>ul li').length-1)){
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
        if(url=="${base}"){
            window.location.href=url
        }else{
            if(url){
                window.location.href=url+'.htm'
                // window.open(url);
            }
        }

    }
</script>
