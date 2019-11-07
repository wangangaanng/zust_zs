<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="${base}/img/logo-zust.png">
                <div class="title">本科生招生网</div>
                <div class="search-bar" style="float: right;width: 300px;margin-top: 50px;margin-right:12px;border-bottom: none;">
                    <div class="input-group" style="position: relative;width: 100%;">
                        <input type="text" class="form-control" onkeydown="keySearch()" style="height: 40px;border-radius: 25px;" id="gjz22" placeholder="请输入">
                    <#--<span class="input-group-btn">-->
                    <#--<button class="btn btn-default header-search" type="button" onclick="searchAll()">搜索</button>-->
                    <#--</span>-->
                        <span class="glyphicon glyphicon-search" onclick="searchAll()" style="background-color: #fff;color: #008784;position: absolute;right: 15px;top: 10px;font-size: 22px;z-index: 99;cursor: pointer;"></span>
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
                    <#elseif obj_index!=0>
                        <div class="subnav-cont" rel="${obj_index}">
                            <div class="subnav-left fl"><img alt="${imagePath}${obj.GGT!'defaultImg.png'}" src="${imagePath}${obj.GGT!'defaultImg.png'}">
                                <div class="subnav-motto">
                                    <p>${obj.JSY!''}</p>
                                <#--<p class="tr">——张德江</p>-->
                                </div></div>
                            <ul class="clearfix subnav fr">
                                <#list headerY as sub>
                                    <li onclick='openUrl("wzOrTpOrSqnd/${obj.CODE!''}/${sub_index}")'><a>${sub.dicVal2}</a></li>
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
    function isopenUrl(url,userType) {
        if(userType==0){

        }else{
            userType=1;//默认学生
        }
        if(userType==0){//企业
            console.log(getCookie('qyOwid'))
            if(getCookie('qyOwid')){
                window.location.href="${base}/"+url
            }else {
                login(url,0)
            }
        }else if(userType==1){//学生
            if(getCookie('stuOwid')){
                window.location.href="${base}/"+url
            }else {
                login(url,1)
            }
        }
    }
    function linkUrl(url) {
        if(url){
            window.open(url)
        }
    }
</script>
