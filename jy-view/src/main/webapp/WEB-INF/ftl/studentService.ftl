<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
</head>
<style>
    .teacher-list {
        padding: 20px 20px 0;
        overflow: hidden;
    }

    .teacher-item {
        width: calc((100% - 80px)/3);
        margin-right: 40px;
        /* margin-bottom:  */
        float: left;
        margin-bottom: 30px;
    }

    .teacher-item:nth-child(3n) {
        margin-right: 0;
    }

    .teacher-item img {
        width: 100%;
        z-index: 1;
    }

    .teacher-item .t-bg {
        width: 100%;
        height: 260px;
        background: url("${base}/img/teacher.png") 100% no-repeat;
    }

    .teacher-detail {
        margin-top: -60px;
        width: 80%;
        background-color: #fff;
        margin-left: 10%;
        box-shadow: 0px 0px 2px 0px rgba(199, 199, 199, 0.75);
        z-index: 9;
    }

    .teacher-detail .t-name {
        line-height: 38px;
        text-align: center;
        font-size: 18px;
        color: #000;
    }

    .teacher-detail .t-xhx {
        text-align: center;
        height: 1px;
        line-height: 1px;
    }

    .teacher-detail .t-xhx span {
        width: 31px;
        height: 1px;
        border: solid 1px #008784;
        display: inline-block;
    }

    .teacher-detail .t-sm {
        padding: 12px;
        text-indent: 2em
    }

    .teacher-detail .t-btn {
        overflow: hidden;
    }

    .teacher-detail .t-btn .btn {
        width: 50%;
        float: left;
        border-radius: 0 !important;
    }

    .t-btn .t-zx {
        background-color: #008784;
        color: #fff;
    }

    .t-btn .t-xq {
        background-color: #f0f0f0;
        color: #3d3d3d;
    }
</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i> 当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">学生服务</a></li>
                <li class="active">办事流程</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav">
                <div class="menu-title">
                    <div class="title-chn">学生服务</div>
                    <div class="title-en">STUDENT SERVICE
                        <div class="menu-nav-icon"></div>
                    </div>

                </div>
                <div class="menu-list">
                    <ul class="list-group">
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 办事流程
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 常用下载
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 档案查询
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 导师咨询
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 报名预约
                        </li>
                    </ul>
                </div>
            </div>


            <div class="content-list" style="display: none">
                <div class="search-bar">
                    <div class="search-label">搜素关键字：</div>
                    <div class="input-group search-input">
                        <input type="text" class="form-control" placeholder="输入内容">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>

                        </div>
                    </div>
                </div>
                <div class="news-list">
                    <ul>
                        <li>
                            <div class="article">毕业生就业流程图</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                        <li>
                            <div class="article">毕业生就业流程图</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                        <li>
                            <div class="article">毕业生就业流程图</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                    </ul>
                    <div class="text-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li>
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="content-list" style="display: none;">
                <div class="search-bar">
                    <div class="search-label">搜素关键字：</div>
                    <div class="input-group search-input">
                        <input type="text" class="form-control" placeholder="输入内容">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>

                        </div>
                    </div>
                </div>
                <div class="news-list">
                    <ul>
                        <li>
                            <div class="article">2016浙江科技学院毕业生离校手续单</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                        <li>
                            <div class="article">2016浙江科技学院毕业生离校手续单</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                        <li>
                            <div class="article">2016浙江科技学院毕业生离校手续单</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                    </ul>
                    <div class="text-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li>
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>

            <div class="content-list" style="display: none;">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">档案去向查询</div>
                    </div>
                    <div class="article-detail-text text-center">
                        <div class="archives">
                            <div class="archives-title">按姓名和身份证查询</div>
                            <div class="archives-content">
                                <div class="archives-input">
                                    <label>
                                        <i class="icon ic-name"></i>
                                    </label>
                                    <input class="" type="text" placeholder="请输入姓名" />
                                </div>
                                <div class="archives-input">
                                    <label>
                                        <i class="icon ic-sfz"></i>
                                    </label>
                                    <input class="" type="text" placeholder="请输入身份证号码" />
                                </div>
                                <div class="archives-input">
                                    <button class="btn">查询</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
            <div class="content-list" style="min-height: 707px;height:auto;display: none;">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">指导老师</div>
                    </div>
                    <div class="teacher-list">
                        <div class="teacher-item">
                            <div class="t-bg"></div>
                            <div class="teacher-detail">
                                <div class="t-name">张立庆</div>
                                <div class="t-xhx"><span></span></div>
                                <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>
                                <div class="t-btn">
                                    <button class="btn t-zx">我要咨询</button>
                                    <button class="btn t-xq">查看详情</button>
                                </div>
                            </div>
                        </div>
                        <div class="teacher-item">
                            <div class="t-bg"></div>
                            <div class="teacher-detail">
                                <div class="t-name">张立庆</div>
                                <div class="t-xhx"><span></span></div>
                                <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>
                                <div class="t-btn">
                                    <button class="btn t-zx">我要咨询</button>
                                    <button class="btn t-xq">查看详情</button>
                                </div>
                            </div>
                        </div>
                        <div class="teacher-item">
                            <div class="t-bg"></div>
                            <div class="teacher-detail">
                                <div class="t-name">张立庆</div>
                                <div class="t-xhx"><span></span></div>
                                <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>
                                <div class="t-btn">
                                    <button class="btn t-zx">我要咨询</button>
                                    <button class="btn t-xq">查看详情</button>
                                </div>
                            </div>
                        </div>
                        <div class="teacher-item">
                            <div class="t-bg"></div>
                            <div class="teacher-detail">
                                <div class="t-name">张立庆</div>
                                <div class="t-xhx"><span></span></div>
                                <div class="t-sm">男，1962年11月出生，教授，浙江科技学院教学督导组组长，教育部全国大学化学课程。</div>
                                <div class="t-btn">
                                    <button class="btn t-zx">我要咨询</button>
                                    <button class="btn t-xq">查看详情</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script>
    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        $(".content-list").eq($(this).index()).show();
    })
</script>
</body>

</html>