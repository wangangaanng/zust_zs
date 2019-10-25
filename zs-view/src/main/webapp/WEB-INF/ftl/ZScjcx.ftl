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
    .table{
        margin: 0 40px;
        width: 90%;
    }
</style>
<body>
<#include "com/ZSheader.ftl">
<div class="main">
    <div class="container">
        <#include "com/route.ftl">
        <div class="content">
            <#include "com/subMenu.ftl">
            <div class="content-list">
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
                    <button type="button" class="btn btn-default"
                            style="background-color: rgb(85,167,153);color: #ffffff;width: 100px;height: 34px;margin: 20px">查询</button>
                </div>
                <p style="color: red; margin: 0 40px 10px;">注：身份证和准考证必须都输入正确才可查询</p>
                <table class="table">
                    <tr>
                        <th style="width: 150px">准考证号： </th>
                        <th style="width: 250px" id="zkzh">44182</th>
                        <th style="width: 150px" >身份证号： </th>
                        <th style="width: 250px" id="sfzh">370102199912212528</th>
                    </tr>
                </table>
                <table class="table table-bordered" style="text-align: center">
                    <tr>
                        <td>姓名</td>
                        <td>科目名称</td>
                        <td>成绩</td>
                        <td>录入时间</td>
                        <td>是否合格</td>
                        <td>备注或名次</td>
                    </tr>
                    <tr>
                        <td>王柯颖</td>
                        <td>2018年表演类校考</td>
                        <td>79</td>
                        <td>2018-03-07</td>
                        <td>否</td>
                        <td>无</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script>

</script>
</body>
</html>
