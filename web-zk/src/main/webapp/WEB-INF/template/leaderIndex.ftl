<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="bootstrap/v3/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="html/css/common.css" />
    <link rel="stylesheet" href="html/css/style.css" />
    <style>
        html{height: 100%;}
        body{height: 100%;}
        .common-right{background: #FFF;}
        .common-wrap{min-height: 100%;}
        .comcontent-wrap{margin: 30px;}
        .select-mode li a{min-width: 100px!important;padding: 6px;}
        .select-mode li{margin-left: 7px;}
        .area-select{padding: 10px 10px 0px 10px;}
        .bottom-wrap{padding-top: 0px;}
        .top-wrap{padding-top: 0px;height: 315px;}
        .bottom-wrap{height: 369px;}
        .content{ width: 100%;padding: 0px 0px 20px 0px;display: flex;justify-content: flex-start ;flex-flow: nowrap;}
        .box{ border-radius: 12px; flex: 1;margin: 0px 10px; display: block; box-shadow: -5px 0px 11px -3px #eeeeee;
            border: 1px solid #eaeaea; }
        .box .article .skt{ width:100%; display: flex;  justify-content: space-between; }
        .box .article{ width: 100%; height: auto; border-bottom: 1px solid #eaeaea; padding: 5px 10px 5px 10px; }
        .box .article:last-child{border: none;}
        .box .bt{ overflow:hidden;  text-overflow:ellipsis;  white-space:nowrap; margin-bottom: 5px!important;    flex: 1; }
        .skt { width: 100%; display: flex; justify-content: space-between;  }
        .col1 { color: #999;}
        .col2 { color: #000;}
        #indexBar{height: 320px; margin-top: 10px;}
        .box .atitle{    height: 38px;font: bold 13px/38px "微软雅黑","Microsoft Yahei";padding-left: 12px;border-bottom: 1px solid #E5E5E5;    color: #FFF;  background: url(html/img/btn-back_img.png);  background-size: 100% 100%;overflow: hidden;border-radius: 12px 12px 0px 0px;}
        .dropdown-menu>li:focus, .dropdown-menu>li:hover {
            color: #262626;
            text-decoration: none;
            background-color: #f5f5f5;
        }
        .btn-group{
            position: absolute;
            right: 100px;
            top:35px;
            z-index:11;
        }
    </style>
    <script type="text/javascript">
        <#include "comTem/comUtil.ftl" />
    </script>
</head>
<body class="index-body">

<!--content begin-->
<div class="common-wrap">
    <!--right begin-->
    <div class="common-right">
        <!--bottom begin-->
        <div class="comcontent-wrap index-wrap">

            <div class="content">
                <div class="box" id="xxzph">
                    <p class="atitle">待举办招聘会</p>
                </div>
                <div class="box" id="xxxjh">
                    <p class="atitle">待举办宣讲会</p>
                </div>
                <div class="box" id="xxqy">
                    <p class="atitle">待审核企业</p>
                </div>
            </div>
            <div class="top-wrap" style="margin-top: 0px;border-radius: 0px;">
                <ul class="pie-list">
                    <li>
                        <div class="pie-list_wrap" >
                            <p><span>企业统计</span></p>
                            <div id="taskcartogram" class="pie-wrap">

                            </div>
                            <div class="piechat-title" >
                                <table id="qytj">

                                </table>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="pie-list_wrap" >
                            <p><span>职位统计</span></p>
                            <div id="taskcartogram2" class="pie-wrap">

                            </div>
                            <div class="piechat-title">
                                <table id="zwtj">

                                </table>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="pie-list_wrap">
                            <p><span>招聘会统计</span></p>
                            <div id="taskcartogram3" class="pie-wrap">

                            </div>
                            <div class="piechat-title">
                                <table id="zphtj">

                                </table>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="pie-list_wrap">
                            <p><span>宣讲会统计</span></p>
                            <div id="taskcartogram4" class="pie-wrap">

                            </div>
                            <div class="piechat-title">
                                <table id="xjhtj">

                                </table>
                            </div>
                        </div>
                    </li>

                </ul>
            </div>

            <div class="bottom-wrap" style="margin-top: 20px;">
                <div  class="point-chart">
                    <p><span>分类汇总</span></p>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span id="year"></span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">

                        </ul>
                    </div>
                    <div id="indexBar">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--content end-->
<script type="text/javascript" src="html/js/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="bootstrap/v3/bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="html/js/echarts.min.js" ></script>
<script type="text/javascript" src="html/js/swiper.min.js"></script>
<script type="text/javascript" src="html/js/common.js"></script>
<script type="text/javascript" src="html/js/leaderIndex.js" ></script>
</body>
</html>
