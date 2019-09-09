<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="commonProperty.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>鄂尔多斯市转型发展投资有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/swiper.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/layer/skin/layer.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/com.css" />
    <style>
        a:hover, a:focus {
            color: #fff;
            text-decoration: none;
        }
        .layui-layer.layer-anim.layui-layer-loading{top:50% !important;}

        .part_rmtj .part_content_item .tstz_item{
            display: none;
            z-index: 99;
            width: 276px;height: 245px;background-color: #fff;text-align: center;z-index: 2;cursor: auto;margin: 39px auto;
            /*position: absolute;*/
            /*margin-top: 324px;*/
            /*margin-left: 48px;*/
            /*opacity: 0;*/
        }
        .part_rmtj .part_content_item:hover .tstz_item{
            background-color: #fff;
            overflow: hidden;
            z-index: 99;
            /*margin-top: 39px;*/
            opacity: 1;
        }

        .tz_item{text-align: center;z-index: 1}
        .tz_item .ic_gd{width: 29px;height: 29px;margin-top: 29px;}
        .tz_item .tz_title{z-index: 1;font-size: 30px;color: #ff0000;font-family: NotoSansHans-Bold;font-weight: 600;height: 29px;line-height: 30px;margin-top: 20px;}
        .tz_item .tz_sm{height: 17px;font-family: NotoSansHans-Regular;font-size: 18px;line-height: 30px;letter-spacing: 0px;color: #ffffff;margin-top: 12px;}
        .left1{
            position: absolute;
            top: 50%;
            width: 27px;
            height: 44px;
            margin-top: -22px;
            z-index: 10;
            left: 10px;
            cursor: pointer;
            z-index: 99;
        }
        .right1{
            position: absolute;
            top: 50%;
            width: 27px;
            height: 44px;
            margin-top: -22px;
            z-index: 10;
            right: 10px;
            cursor: pointer;
            z-index: 99;
        }

    </style>
<body>
<%@ include file="top.jsp" %>

<!-- 热门推荐 -->
<div class="container part_rmtj">
    <div class="part_title">
        <span class="part_title_name">热门推荐</span>
        <%--<span class="part_title_right">查看更多</span>display: none;--%>
    </div>
    <div class="part_content">
        <a class="part_content_item tstz hidden-xs">
            <div class="tz_item" id="tstz1">
                <img src="image/ic_tstz.png" />
                <div class="tz_title">股权投资</div>
                <div class="tz_sm">
                    引入实力投资，助推企业发展
                </div>
                <img src="image/ic_gd.png" class="ic_gd" />
            </div>
            <div class="tstz_item" id="tstz">
                <div class="tz_name">股权投资</div>
                <div class="tz_fg"></div>
                <div class="tz_jj">引入实力投资，助推企业发展</div>
                <button type="button" class="btn btn-primary tzbtn" onclick="tdxm()">投递项目</button>
            </div>
        </a>
        <a class="part_content_item gqtz" onclick="go2('E01')">
            <div class="tz_item" id="gqtz1">
                <img src="image/ic_gqtz.png" />
                <div class="tz_title">投研分析</div>
                <div class="tz_sm">
                    专家精准分析，掌握行业动态
                </div>
                <img src="image/ic_gd.png" class="ic_gd hidden-xs" />
            </div>
            <%--<div class="tstz_item" id="gqtz">
                <div class="tz_name">投研分析</div>
                <div class="tz_fg"></div>
                <div class="tz_jj">引入实力投资，助推企业发展</div>
                <button type="button" class="btn btn-primary tzbtn" data-toggle="modal" data-target="#exampleModal" >投递项目</button>
            </div>--%>
        </a>
        <a class="part_content_item zqtz" onclick="go2('E02')">
            <div class="tz_item" id="zqtz1">
                <img src="image/ic_zqtz.png" />
                <div class="tz_title">资讯参阅</div>
                <div class="tz_sm">
                    行业法律法规，保障权益
                </div>
                <img src="image/ic_gd.png" class="ic_gd hidden-xs" />
            </div>
            <%--<div class="tstz_item" id="zqtz">--%>
                <%--<div class="tz_name">资讯参阅</div>--%>
                <%--<div class="tz_fg"></div>--%>
                <%--<div class="tz_jj">借款方式多样，融资就这么简单</div>--%>
                <%--<button type="button" class="btn btn-primary tzbtn" data-toggle="modal" data-target="#exampleModal" >投递项目</button>--%>
            <%--</div>--%>
        </a>
    </div>
</div>
<!-- 管理平台 -->
<div class="container yqlj hidden-xs">
    <a class="yqlj_item href1" onclick="href1()" >

    </a>
    <a class="yqlj_item href2">

    </a>
</div>

<div class="hidden-xs">
    <!-- 最新动态 -->
    <div class="container part_zxdt">
        <div class="overh">
            <div class="part_title1">
                <span class="part_title_name">最新动态</span>
                <img src="image/ic_more.png" class="ic_more" onclick="go1('A01')" />
            </div>
            <div class="part_title1">
                <span class="part_title_name">业务对接</span>
                <img src="image/ic_more.png" class="ic_more" onclick="go1('A05')" />
            </div>
        </div>
        <div class="overh part_content_news">
            <div class="part_left">
                <div id="zxdt1">
                    <div class="image_lb">
                        <div class="swiper-container" id="mySwiper1">
                            <div class="swiper-wrapper" id="swiperlist">
                                <%--<div class="swiper-slide"><img src="image/news01.png" /></div> onclick="prev1()" onclick="next1()"--%>
                            </div>
                            <div class="swiper-button-prev" style="z-index: 999;"></div>
                            <!--左箭头-->
                            <div class="swiper-button-next" style="z-index: 999;"></div>
                            <!--右箭头-->

                            <div class="left1"></div>
                            <div class="right1"></div>
                        </div>
                    </div>
                    <p class="dt_content" id="dt_content" style="min-height: 40px;">
                    </p>
                    <p class="dt_content" id="dt_content_time">
                    </p>
                    <div class="seemore">
                        <span onclick="go1('A01')">查看更多</span>
                    </div>
                </div>
                <div class="null_ts" id="null_ts1">
                    <img src="image/null_ts.jpg"  />
                    <p>暂无最新动态</p>
                </div>
            </div>
            <div class="part_right">
                <div class="tzgd_list" id="tzgd_list">
                    <%--<div class="tzgd_item">
                        <div class="tzgd_item_date">
                            <div class="date_day">12日</div>
                            <div class="date_month">04月</div>
                        </div>
                        <div class="tzgd_item_content">
                            <p class="tzgd_item_title">
                                与投资高手为伍
                            </p>

                            <p class="tzgd_item_nr">
                                2019年，金融圈风向骤变，盈利方向主导市场放箱，盈利结构影响
                                市场风格
                            </p>
                        </div>
                    </div>--%>
                    <div class="null_ts" id="null_ts2">
                        <img src="image/null_ts.jpg"  />
                        <p>暂无业务对接</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 公司公告 -->
    <div class="container part_gsgg">
        <div class="part_title">
            <span class="part_title_name">公司公告</span>
            <img src="image/ic_more.png" class="ic_more" onclick="go1('A02')" />
        </div>
        <div class="overh">
            <div class="part_left gg_left">
                <div class="gg_list" id="gg_list">
                    <%--<div class="gg_item">
                        <div class="gg_item_title">
                            <div class="gg_bt">
                                鄂尔多斯市转型发展投资有限责任公司鄂尔多斯市转型……
                            </div>
                            <div class="gg_time">
                                2019-04-12
                            </div>
                        </div>
                        <div class="gg_item_content">
                            作为唯一民营机构受邀参加国家发改委融资担保行业交流座谈会 近日，中共中央办公厅、国务院办公厅印发《关于加强金融服务民...
                        </div>
                    </div>--%>
                    <div class="null_ts1" id="null_ts3">
                        <img src="image/null_ts.jpg"  />
                        <p>暂无公司公告</p>
                    </div>
                </div>
                <div class="part_xxpl">
                    <div class="part_title">
                        <span class="part_title_name">学习动态</span>
                        <img src="image/ic_more.png" class="ic_more" onclick="go1('A04')" />
                    </div>
                </div>
                <div class="xxpl_list" id="xxpl_list">
                    <%--<div class="xxpl_item">
                        <span class="red_star"></span>
                        构建产业金融生态圈 提高中小微企生存力
                        <span class="xxpl_time">2019-04-12</span>
                    </div>
                    <div class="xxpl_item">
                        <span class="red_star"></span>
                        围观中国第五次金融创新期的“碰撞火花” 当华软……
                        <span class="xxpl_time">2019-04-12</span>
                    </div>
                    <div class="xxpl_item">
                        <span class="red_star"></span>
                        中新金融峰会硕果累累 多个项目签约落地
                        <span class="xxpl_time">2019-04-12</span>
                    </div>--%>
                    <div class="null_ts1" id="null_ts4">
                        <img src="image/null_ts.jpg"  />
                        <p>暂无学习报告</p>
                    </div>
                </div>
            </div>

            <div class="part_right">
                <img src="image/news02.png" class="news02" />
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="font-size: 26px;border: 1px solid #7a787c;width: 31px;height: 31px;text-align: center;line-height: 31px;border-radius: 50px;display: inline-block;">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel" style="text-align: center;">投递项目</h4>
                </div>
                <div class="modal-body">
                    <form id="signupForm" method="post" action="">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-2" style="text-align: right;height: 34px;line-height: 34px;">
                                    <label for="lxr" class="control-label"><span style="color: red;">*</span>联系人:</label>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="lxr" name="lxr"  />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-2" style="text-align: right;height: 34px;line-height: 34px;">
                                    <label for="mobile" class="control-label"><span style="color: red;">*</span>手机号码:</label>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="mobile" name="mobile" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-2" style="text-align: right;height: 34px;line-height: 34px;">
                                    <label for="itemIntro" class="control-label"><span style="color: red;">*</span>项目描述:</label>
                                </div>
                                <div class="col-sm-9">
                                    <textarea class="form-control" rows="4" id="itemIntro" name="itemIntro" placeholder="请简单介绍项目情况、项目优势及融资用途。字数20-500之间"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-9 col-sm-offset-2">
                                    <label class="control-label" for="fwxy" style="color:#333333;">
                                        <input type="checkbox" class="pull-left" id="fwxy" name="fwxy" value="fwxy" style="margin-right: 5px;" />
                                        我已阅读并同意《服务协议》</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" style="display: none;" id="error-div">
                            <div class="row">
                                <div class="col-sm-9 col-sm-offset-2">
                                    <span style="color: red;" id="error-msg"></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-9 col-sm-offset-2">
                                    <button type="submit" class="btn btn-primary tjxm">确定</button>
                                    <%--onclick="addItem()"--%>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <%--<div class="modal-footer" style="text-align: left;">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary">确定</button>
                </div>--%>
            </div>
        </div>
    </div>
</div>

<div class="visible-xs-block">
<!-- 最新动态 -->
<div class="container part_rmtj">
    <div class="part_title">
        <span class="part_title_name">最新动态</span>
        <img src="image/ic_more.png" class="ic_more" onclick="go1('A01')" />
    </div>
    <div class="xxpl_list dtlist">

    </div>
    <div class="null_ts" id="null_ts1">
        <img src="image/null_ts.jpg"  />
        <p>暂无最新动态</p>
    </div>
</div>
<div class="container part_rmtj">
    <div class="part_title">
        <span class="part_title_name">业务对接</span>
        <img src="image/ic_more.png" class="ic_more" onclick="go1('A05')" />
    </div>
    <div class="tzgd_list" id="tzgd_list">
        <%--<div class="tzgd_item">
            <div class="tzgd_item_date">
                <div class="date_day">12日</div>
                <div class="date_month">04月</div>
            </div>
            <div class="tzgd_item_content">
                <p class="tzgd_item_title">
                    与投资高手为伍
                </p>

                <p class="tzgd_item_nr">
                    2019年，金融圈风向骤变，盈利方向主导市场放箱，盈利结构影响
                    市场风格
                </p>
            </div>
        </div>--%>
        <div class="null_ts" id="null_ts2">
            <img src="image/null_ts.jpg"  />
            <p>暂无业务对接</p>
        </div>
    </div>
</div>
<!-- 公司公告 -->
<div class="container part_rmtj">
    <div class="part_title">
        <span class="part_title_name">公司公告</span>
        <img src="image/ic_more.png" class="ic_more" onclick="go1('A02')" />
    </div>
    <div class="gg_list" id="gg_list">
        <%--<div class="tzgd_item">
            <div class="tzgd_item_date">
                <div class="date_day">12日</div>
                <div class="date_month">04月</div>
            </div>
            <div class="tzgd_item_content">
                <p class="tzgd_item_title">
                    与投资高手为伍
                </p>

                <p class="tzgd_item_nr">
                    2019年，金融圈风向骤变，盈利方向主导市场放箱，盈利结构影响
                    市场风格
                </p>
            </div>
        </div>--%>
        <div class="null_ts" id="null_ts3">
            <img src="image/null_ts.jpg"  />
            <p>暂无公司公告</p>
        </div>
    </div>
</div>
<!-- 学习动态 -->
<div class="container part_rmtj">
    <div class="part_title">
        <span class="part_title_name">学习动态</span>
        <img src="image/ic_more.png" class="ic_more" onclick="go1('A04')" />
    </div>
    <div class="xxpl_list" id="xxpl_list">
        <%--<div class="tzgd_item">
            <div class="tzgd_item_date">
                <div class="date_day">12日</div>
                <div class="date_month">04月</div>
            </div>
            <div class="tzgd_item_content">
                <p class="tzgd_item_title">
                    与投资高手为伍
                </p>

                <p class="tzgd_item_nr">
                    2019年，金融圈风向骤变，盈利方向主导市场放箱，盈利结构影响
                    市场风格
                </p>
            </div>
        </div>--%>
        <div class="null_ts" id="null_ts4">
            <img src="image/null_ts.jpg"  />
            <p>暂无学习动态</p>
        </div>
    </div>
</div>

</div>
<div class="dtlist" style="display: none;"></div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/swiper.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>
<script>
    // $(document).ready(function() {
        // var mySwiper = new Swiper('#mySwiper', {
        //     loop: true,
        //     autoplay: {
        //         delay: 5000, //1秒切换一次
        //     },
        //     pagination: {
        //         el: '.swiper-pagination',
        //     }
        //
        // })
    // })
    function href1() {
        window.open('http://ordos.qzsjcloud.com')
    }
</script>
</body>
</html>
