<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="top hidden-xs">
    <div class="container">
        <div class="pull-left ptinfo">专业的融资信息服务平台</div>
        <div class="pull-right ptinfo">客服热线：<span>0471-8581241</span></div>
    </div>
</div>
<div class="sub_body">
    <div class="container visible-xs-block">
        <nav class="navbar navbar-default navbar-fixed-top">
        <div class="" style="">
            <div class="compy">
                <img src="image/logo22.png" />
                <div class="compy_name">
                    <p>鄂尔多斯市转型发展</p>
                    <p class="ls3">投资有限责任公司</p>
                </div>
                <div class="web_name hidden-xs">
                    官方网站
                </div>
            </div>
        </div>
        <div style="position: absolute;top: 17px;right: 0px;">

            <%--<div class="container">--%>
                <div class="navbar-header">
                    <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".collapse2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse navbar-center collapse1 collapse2" role="navigation">
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active"><a href="indexPage.htm">首页</a></li>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100001">新闻资讯</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100002">党建园地</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100003">客户服务</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100004">关于我们</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100006&dicVal1=F01">关于我们</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100005">热门推荐</a></li>--%>
                    </ul>
                </div>
            <%--</div>--%>
        </div>
        </nav>
    </div>
    <div class="container hidden-xs">
            <div class="col-md-5 col-sm-5" style="padding-left: 0;">
                <div class="compy">
                    <img src="image/logo22.png" />
                    <div class="compy_name">
                        <p>鄂尔多斯市转型发展</p>
                        <p class="ls3">投资有限责任公司</p>
                    </div>
                    <div class="web_name hidden-xs">
                        官方网站
                    </div>
                </div>
            </div>
            <div class="col-md-7 col-sm-7" style="padding-right: 0;">

                <div class="navbar-collapse collapse navbar-center" role="navigation">
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active"><a href="indexPage.htm">首页</a></li>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100001">新闻资讯</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100002">党建园地</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100003">客户服务</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100004">关于我们</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100006&dicVal1=F01">关于我们</a></li>--%>
                        <%--<li role="presentation"><a href="aboutUs.htm?dicType=100005">热门推荐</a></li>--%>
                    </ul>
                </div>
            </div>
    </div>
</div>
<div class="banner">
    <div class="swiper-container" id="mySwiper" style="height: 400px;">
        <div class="swiper-wrapper" id="top-swiper">
            <%--<div class="swiper-slide"><img src="image/banner.png" /></div>
            <div class="swiper-slide"><img src="image/banner1.png" /></div>--%>
        </div>
        <div class="swiper-pagination"></div>
    </div>
</div>
<div class="mask" ></div>
<script type="text/javascript">
    $(document).ready(function() {
        getShowList()//轮播
    });

    var　topDatalist;
    function getByType() {
        var params = {
            'dicType':'100000'
        }
        postReq("/web/dic/getByType", "", "", params, function (data) {
            var data = JSON.parse(data)
            if (data.backCode == 0) {
                topDatalist = data.bean;
                if(data.bean.length>0){
                    $.each(data.bean,function (k,p) {
                        var str = '<li role="presentation"><a href="aboutUs.htm?dicType='+p.dicVal1+'">'+p.dicVal2+'</a></li>'
                        $('.nav.nav-tabs').append(str)
                    })
                }
            } else {

            }
        })
    }
    getByType()

    function getShowList() {
        var params = {
        }
        postReq("/web/appBizPicvid/getShowList", "", "", params, function (data) {
            var data = JSON.parse(data)
            if (data.backCode == 0) {
                if(data.bean && data.bean.length>0){
                    $.each(data.bean,function (k,p) {
                        var url='#';
                        if(p.url){
                            url=p.url;
                        }
                        var str = '<div class="swiper-slide" onclick="href2('+"'"+url+"'"+')"><img src="'+comUrl+p.imagePath+'" /></div>'
                        $('#top-swiper').append(str)
                    })
                    var mySwiper = new Swiper('#mySwiper', {
                        loop: true,
                        autoplay: {
                            delay: 5000, //1秒切换一次
                        },
                        pagination: {
                            el: '.swiper-pagination',
                        }

                    })
                }
            } else {

            }
        })
    }

    function href2(a) {
        window.location.href=a;
    }
</script>