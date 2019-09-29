<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>
<style>

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
                <li><a href="#">企业指南</a></li>
                <li class="active">招聘指南</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav">
                <div class="menu-title">
                    <div class="title-chn">企业指南</div>
                    <div class="title-en">ENTERPRISE GUIDE
                        <div class="menu-nav-icon"></div>
                    </div>

                </div>
                <div class="menu-list">
                    <ul class="list-group">
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 招聘指南
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 生源速览
                        </li>
                    </ul>
                </div>
            </div>
            <div class="content-list" style="height: auto">
                <div class="article-detail" style="border: none;">
                    <div class="article-detail-title">
                        <div class="h3">招聘指南</div>
                        <div><span class="label1">发布人：</span>浙江科技学院 &nbsp;&nbsp;&nbsp;<span class="label1">发布时间：</span>2019/4/28 20:04:08 &nbsp;&nbsp;&nbsp;<span class="label1">浏览：</span>21 次 </div>
                    </div>
                    <div class="article-detail-text">
                        <p>
                            用人单位招聘流程:<br>一、注册<br>登录浙江科技学院就业信息网(job.zust.edu.cn)→点击企业发布系统“企业登录”&nbsp;→点击“立即注册”&nbsp;→填写单位名称、邮箱和密码→认证邮箱→填写认证资料并提交→电话联系快速审核或等待一个工作日审核→注册成功。<br>注：如单位名称或邮箱已被注册，不知道登录密码等情况，可致电0571-85124573，由就业指导中心配合重置邮箱及登录密码。<br><br>二、登录<br>登录浙科院就业信息网→点击“企业登录”&nbsp;→输入用户名和密码→登录成功，可操作“职位发布”、“宣讲会场次申请”、&nbsp;“专场招聘会申请”、&nbsp;“招聘会展位预订”，可修改单位基本情况和密码。<br><br>三、职位发布<br>点击“新增职位”→按要求填写内容并提交→成功发布。<br><br>四、宣讲会场次申请<br>点击“新增”&nbsp;→按要求填写内容，可以选择要发送的专业学生，提交→学校审核（电话联系确定时间和地点）→成功发布。<br><br>五、专场招聘会申请<br>点击“新增”&nbsp;→按要求填写内容，可以选择要发送的专业学生提交→学校审核（电话联系确定时间和地点）→成功发布。<br><br>&nbsp;六、招聘会展位预订<br>点击“展位预订”→选择要参加的招聘会→点击“预订展位”→选择可用的预订编号，点击预位展位→填写“展位预订报名表”，提交→学校审核→成功预订。<br><br>&nbsp;
                        </p>

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
                            <div class="article">2016届毕业生生源信息</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                        <li>
                            <div class="article">2016届毕业生生源信息</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                        <li>
                            <div class="article">2016届毕业生生源信息</div>
                            <div class="article-time">2019-04-29</div>

                        </li>
                        <li>
                            <div class="article">2016届毕业生生源信息</div>
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