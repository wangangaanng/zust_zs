$(function () {

    //解决跳转到我的服务再回来不能正常返回首页
    var prePage = document.referrer;
    if(prePage.indexOf("selectMechanism")>0){
        localStorage.setItem("prePageDetail",prePage);
    }

    topSwiper();
    centerSwiper();
    pageHref();//跳转到详情页面
    //点击放大
    $(".small-licence").click(function () {
        bigLicense();
    });
    list();
});

function list() {
  var typeList = $(".txgg_nav_list_li");
  $.each(typeList,function (k,p) {
      //从第二个开始 第一个controller已经加载
      if(k>0){
          getNewsList($(p).attr("val"));
          sleep(500);
      }
  });
}

//获取列表信息
function getNewsList(type) {
    var paramThis = {
        "newsType":type,
        "midRefOwid":companyOwid,//中介主键
        "pageSize":4,
        "pageNo":1,//当前一定是第一页 入口展示几条
        timestamp : new Date().getTime()
    }
    window.parent.ajax("target/bckjBizMidcompNews/getNewsList", paramThis, function (data) {
        if (data.backCode == 0) {
           var str = '<div class="swiper-slide swiper-slide-prev"><ul>';
            if(data.bean.records.length>0){
                $.each(data.bean.records,function (k,p) {
                    str += '<li>';
                    if(p.newsImg){
                        str += '<div class="tzgg-img_wrap"><img src="'+p.newsImg+'" /></div>';
                    }
                   // str +='<p class="showellipsis">'+((!p.newsTitle)?"无标题":p.newsTitle)+'</p>';
                    str +='<div class="tzgg_box-content"><h5>'+((!p.newsContent)?"暂无":p.newsContent)+'</h5></div></li>';
                })
            }else{
                str +="暂无相关信息";
            }
            str +='</ul></div>';
            $("#newsList").append(str);
        } else {
            layer.msg(data.errorMess);
        }
    })
}
//页面跳转
function pageHref() {
    var comUrl = 'newsDetail.htm?projectId='+projectId+'&companyOwid='+companyOwid+'&pageNo=1&newsType=';

    //最新通知公告
    $("#leftMore").click(function () {
        window.location.href = comUrl+newsType;
    });
    $("#serviceMore").click(function () {
        window.location.href = comUrl+"002";
    });

    //最新通知公告
    $(".notice-wrap h6").click(function () {
        window.location.href = comUrl+"001";
    });

    //中间左边
    $(document).on("click",'.tzgg_content_box li',function(){
        window.location.href = comUrl+newsType;
    });
}

var mySwiper2 = "";
function centerSwiper() {
    mySwiper2 = new Swiper ('.tzgg_content_box .swiper-container', {
        observer:true,//修改swiper自己或子元素时，自动初始化swiper
        observeParents:false,//修改swiper的父元素时，自动初始化swiper
        watchSlidesProgress : true,
        autoplay:false
    });

    $(".tzgg_content_box").mouseenter(function () {
        mySwiper2.autoplay.stop();
    }).mouseleave(function(){
        //mySwiper2.autoplay.start();
    });

    mySwiper2.on('transitionStart',function () {
        var index1=this.activeIndex;
        $(".txgg_nav_list_li_a").removeClass("txgg_nav_list_li_a_hover");
        $(".txgg_nav_list_li_a").eq(index1).addClass("txgg_nav_list_li_a_hover");
    });

    //鼠标放置于tab上边 下边滚动到第几个 这时候开始调用接口 如果当前列表没有的话
    $(".txgg_nav_list_li").mouseover(function () {
        var index1=$(this).index();
        var tabLength = $(".tzgg_content_box .swiper-slide").length;
        newsType = $(this).attr("val")
        if(tabLength<index1+1){
           //getNewsList(newsType,index1);
        }else{
            mySwiper2.slideTo(index1);
        }
    });

    $(".swiper_time").mouseenter(function () {
        mySwiper1.autoplay.stop();
    }).mouseleave(function () {
        //mySwiper1.autoplay.start();
    });

}

//营业执照图片放大
function bigLicense() {
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '',
        skin: '#FFF', //没有背景色
        shadeClose: true,
        content: $('#imgForBig')
    });
}

function topSwiper() {
    var mySwiper = new Swiper('.me-top_left .swiper-container', {
        autoplay:true,
        pagination: {
            el: '.swiper-pagination',
            clickable :true,
            renderBullet: function (index, className) {
                return '<span class="swiper-pagination-bullet">' + (index + 1) + '</span>';
            },
        },

    })
}
//延迟第二个接口的调用
function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}