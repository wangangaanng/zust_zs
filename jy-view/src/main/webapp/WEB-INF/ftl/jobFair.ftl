<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
    <link rel="stylesheet" href="${base}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${base}/css/common.css" />
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
                <li><a href="#">企业服务</a></li>
                <li class="active">招聘会申请</li>
            </ol>
        </div>
        <div class="content">
            <div class="jf-content">
                <div class="jf-steps">
                    <div class="jf-items">
                        <div class="jf-item jf-active">1、招聘会列表

                            <div class="jf-box"></div>
                            <div class="jf-box1"></div>
                            <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item jf-active">2、预定展位
                            <div class="jf-box"></div>
                            <div class="jf-box1"></div>
                            <div class="jf-box2"></div>
                        </div>
                        <div class="jf-item jf-active">3、完成</div>
                    </div>
                </div>
                <div class="jf-result">
                    <div class="jf-tips">
                        <div class="jf-tip-icon">
                            <i></i>
                        </div>
                        <div class="jf-tip-text">
                            <div>认证资料提交成功，您的注册认证一般在24小时内审核完成，</div>
                            <div>将以邮件的方式通知审核结果</div>
                            <div>注册遇到问题请咨询浙江科技学院学生处就业指导中心:0571-85121710</div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>

<#include "com/footer.ftl">
<script src="${base}/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="${base}/js/common.js"></script>
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>