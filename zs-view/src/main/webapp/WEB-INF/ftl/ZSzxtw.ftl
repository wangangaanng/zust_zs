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
    .content-list ul{list-style-type: none}
    #wdListUi  li { margin:20px; border:1px solid #ddd; padding:20px 10px }
    #wdListUi  li .questions{ border-bottom:1px dashed #ddd; margin-bottom:15px;}
    #wdListUi  li .questions h6{ color:#9fa0a0; padding:5px 0; font-size:12px; font-weight:300;}
    #wdListUi  li .questions p{ padding:5px 0; line-height:20px; text-indent:2em;}
    #wdListUi  li .answer{background-color:#f9f9f9; border:1px solid #959595; border-radius:5px; margin-left:10px;}
    #wdListUi  li .answer-content {padding:0 10px;}
    #wdListUi  li .answer .answer-content h6{color:#9fa0a0; padding:5px 0; font-size:12px; font-weight:300;}
    #wdListUi  li .answer .answer-content p{ padding:5px 0; line-height:20px; color:#cc0000;}
    .content-list .zxtw-form{ margin:20px; padding:0 0 20px 0; border-top:1px solid #ddd;}
    .content-list .zxtw-form h5{padding:8px 5px;}
    .content-list .zxtw-form p{ padding:5px;}
    .content-list .zxtw-form textarea{ width:100%; height:100px; margin-bottom:10px;}
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
                    <ul id="wdListUi">
                        <#if (result??)&&(result.records??)&&(result.records?size>0)>
                            <#assign flag=1>
                            <#list result.records as obj>
                                <li class="result">
                                    <div class="questions">
                                        <h6><span class="fr">${obj.TWRQ}</span>${obj_index + 1}、来自${obj.LYIP}的提问</h6>
                                        <p>${obj.WTNR}</p>
                                    </div>
                                    <div class="answer">
                                        <div class="answer-content">
                                            <h6><span class="fr">${obj.HDRQ}</span><strong>管理员</strong>的回复：</h6>
                                            <p>${obj.DANR}</p>
                                        </div>
                                    </div>
                                </li>
                            </#list>
                        <#else >
                            <#assign flag=0>
                        </#if>
                    </ul>
                    <div class="text-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">

                            </ul>
                        </nav>
                    </div>
                    <div class="zxtw-form">
                        <h5>我要留言</h5>
                        <p>
                            <textarea name="textarea" id="wtnr" cols="40" rows="5"></textarea>
                        </p>
                        <p>
                            <button type="button" class="btn btn-default" onclick="submit()"
                                    style="background-color: rgb(85,167,153);color: #ffffff;width: 100px;height: 34px;">提交留言</button>
                        </p>
                    </div>
                </div>
        </div>
    </div>
</div>
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/bootstrap-paginator.min.js" type="text/javascript"></script>
<script>
    var currentPage="${result.currentPage!'1'}";
    $(document).ready(function () {
        if("${flag}"==0){
            $("#wdListUi").append(nulltip)
        }else {
            setPage(currentPage, "${result.totalPage!'1'}", function () {
                openUrl('zxtw/${secondDir!""}/${thirdDir!""}/' + currentPage)
            })
        }
    });
    function submit() {
        if (!$('#wtnr').val()) {
            walert("请填写咨询内容");
            return;
        }
        var data = {
            wtnr: $('#wtnr').val(),
            zxlx: 4
        };
        ajax("zustcommon/bckjBizZxzx/consult", data, function (res) {
            if (res.backCode === 0) {
                $('#wtnr').val("");
                walert("提交成功，请等待回答")
            } else {
                walert("提交失败")
            }
        })
    }
    function setPage(pageCurrent, pageSum, callback) {
        $(".pagination").bootstrapPaginator({
            //设置版本号
            bootstrapMajorVersion: 3,
            // 显示第几页
            currentPage: pageCurrent,
            // 总页数
            totalPages: pageSum,
            //当单击操作按钮的时候, 执行该函数, 调用ajax渲染页面
            onPageClicked: function (event,originalEvent,type,page) {
                // 把当前点击的页码赋值给currentPage, 调用ajax,渲染页面
                currentPage = page;
                callback && callback()
            }
        })
    }
</script>
</body>
</html>
