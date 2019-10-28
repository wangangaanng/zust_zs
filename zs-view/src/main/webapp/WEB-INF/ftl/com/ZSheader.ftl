<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="${base}/img/logo-zust.png">
                <div class="title">本科生招生网</div>
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