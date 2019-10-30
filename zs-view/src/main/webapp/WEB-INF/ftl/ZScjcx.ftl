<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/swiper.min.css"/>
    <link rel="stylesheet" href="${base}/css/style.css"/>
</head>
<style>
    .table{ margin: 0 40px; width: 90%; text-align: center;}
    .table th{ text-align: center;}
</style>
<body>
<#include "com/ZSheader.ftl">
<img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
<div class="main">
    <div class="container">
        <div class="content">
            <#include "com/subMenu.ftl">
            <div class="content-list">
                <div class="nav-bar">
                <#include "com/route.ftl">
                </div>
                <div style="display: flex">
                    <div class="input-group" style="width: 300px;margin: 20px 40px">
                        <span class="input-group-addon">身份证号：</span>
                        <input id="input_sfzh" type="number" class="form-control" placeholder="请输入身份证号"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group" style="width: 300px;margin: 20px">
                        <span class="input-group-addon">准考证号：</span>
                        <input id="input_zkzh" type="number" class="form-control" placeholder="请输入准考证号"
                               aria-describedby="basic-addon1">
                    </div>
                    <button type="button" class="btn btn-default" onclick="queryGrade()"
                            style="background-color: rgb(85,167,153);color: #ffffff;width: 100px;height: 34px;margin: 20px 15px">查询</button>
                </div>
                <p style="color: red; margin: 0 40px 10px;">注：身份证和准考证必须都输入正确才可查询</p>
                <table class="table">
                    <tr>
                        <th style="width: 138px">准考证号： </th>
                        <th style="width: 250px" id="zkzh">44182</th>
                        <th style="width: 138px" >身份证号： </th>
                        <th style="width: 250px" id="sfzh">370102199912212528</th>
                    </tr>
                </table>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>姓名</th>
                            <th>科目名称</th>
                            <th>成绩</th>
                            <th>录入时间</th>
                            <th>是否合格</th>
                            <th>备注或名次</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>王柯颖</td>
                            <td>2018年表演类校考</td>
                            <td>79</td>
                            <td>2018-03-07</td>
                            <td>否</td>
                            <td>无</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/zs/cjcx.js"></script>
<script>

</script>
</body>
</html>
