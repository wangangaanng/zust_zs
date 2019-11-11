<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/style.css"/>
</head>
<style>
    .table{ margin: 0 40px; width: 90%; text-align: center;}
    .table th{ text-align: center;}
    .fuye_sr_con{ margin:20px 40px; width: 90%}
    .fuye_sr_con dl{ border:1px solid #e0e0e0; background-color:#fff; padding:20px 0 20px 30px; overflow:hidden;}
    .fuye_sr_con dl dt, .fuye_sr_con dl dd{float:left;}
    .fuye_sr_con dl dt{ margin-right:20px; width:105px; height:105px;}
    .fuye_sr_con dl.success dt{ background-position:0 -250px;}
    .fuye_sr_con dl.error dt{ background-position:-143px -250px;}
    .fuye_sr_con dl dd h4{ height:30px; line-height:30px;}
    .fuye_sr_con dl dd p{ line-height:25px; font-size:14px;}
    .co_r{ color:#ff0000;}
</style>
<body>
<#include "com/ZSheader.ftl">
<img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
<div class="main">
    <div class="container">
        <div class="content">
            <#include "com/subMenu.ftl">
            <div class="nav-bar">
            <#include "com/route.ftl">
            </div>
            <div class="content-list">
                <div style="display: flex">
                    <div class="input-group" style="width: 300px;margin: 20px 40px">
                        <span class="input-group-addon">身份证号：</span>
                        <input id="input_sfzh" type="text" class="form-control" placeholder="请输入身份证号"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group" style="width: 300px;margin: 20px">
                        <span class="input-group-addon">准考证号：</span>
                        <input id="input_zkzh" type="text" class="form-control" placeholder="请输入准考证号"
                               aria-describedby="basic-addon1">
                    </div>
                    <button type="button" class="btn btn-default" onclick="queryIn()"
                            style="background-color: rgb(85,167,153);color: #ffffff;width: 100px;height: 34px;margin: 20px 15px">查询</button>
                </div>
                <p style="color: red; margin: 0 40px 10px;">注：身份证和准考证必须都输入正确才可查询</p>
                <div>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 150px">准考证号： </th>
                                <th style="width: 250px" id="zkzh"></th>
                                <th style="width: 150px" >身份证： </th>
                                <th style="width: 250px" id="sfzh"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 150px">姓名： </td>
                                <td style="width: 250px" id="xm"></td>
                                <td style="width: 150px" >联系电话： </td>
                                <td style="width: 250px" id="lxdh"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="fuye_sr_con">
                    <dl class="success" style="display:none;" id="successed">
                        <dt>
                            <img src="../../img/smile.png" alt="">
                        </dt>
                        <dd><h4 class="co_r">恭喜你</h4>
                            <p>你已经被<strong class="co_r" id="zhuhe">浙江科技学院 -自动化（机电一体化技术） 预录取,最终录取请查询当地考试院。</strong></p>
                            <p id="lqd"></p>
                            <p id="ems_dh">EMS单号：1004205824025，请注意查收！！！</p>
                        </dd>
                    </dl>
                    <dl class="error" style="display:none;" id="failed">
                        <dt><img src="../../img/depress.png" alt=""></dt>
                        <dd><h4></h4>
                            <p>很抱歉，目前系统里没有你的录取信息，或者你的录取批次还未开始，请继续关注本网站公告，谢谢！</p>
                            <p><a href="#">返回</a></p>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/zs/lqcx.js"></script>
<script>

</script>
</body>
</html>
