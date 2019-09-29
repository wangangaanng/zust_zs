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

</style>

<body>
    <#include "com/header.ftl">
<div class="main" style="margin-top: 20px;">
    <div class="container">
        <div class="content">
            <div class="content-list">
            <div class="news-list">
                <ul>
                            <#if (result??)&&(result.records??)&&(result.records?size>0)>
                                <#assign flag=1>
                                <#list result.records as obj>
                                    <li><a href="${base}/newsDetail/${obj.owid!''}">
                                        <div class="article">${obj.wzbt!''}</div>
                                        <div class="article-time">
                                            <#if obj.fbsj?exists>
                                                ${obj.fbsj?substring(0,10)}
                                            </#if>
                                        </div>

                                    </a></li>
                                </#list>
                            <#else >
                                <#assign flag=0>
                            </#if>
                </ul>
                <div class="text-center">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                        <#--<li>-->
                        <#--<a href="#" aria-label="Previous">-->
                        <#--<span aria-hidden="true">&laquo;</span>-->
                        <#--</a>-->
                        <#--</li>-->
                        <#--<li><a href="#">1</a></li>-->
                        <#--<li><a href="#">2</a></li>-->
                        <#--<li><a href="#">3</a></li>-->
                        <#--<li><a href="#">4</a></li>-->
                        <#--<li><a href="#">5</a></li>-->
                        <#--<li>-->
                        <#--<a href="#" aria-label="Next">-->
                        <#--<span aria-hidden="true">&raquo;</span>-->
                        <#--</a>-->
                        <#--</li>-->
                        </ul>
                    </nav>
                </div>
            </div>
            </div>
        </div>

    </div>
</div>
<input type="hidden" id="key1" value="${key!''}">
        <#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/bootstrap-paginator.min.js" type="text/javascript"></script>
<script>
    var currentPage="${result.currentPage!'1'}"
    $(document).ready(function () {

        $("#gjz22").val($("#key1").val())
        if("${flag}"==0){
            $(".content-list").append(nulltip)
        }else {
            setPage(currentPage, "${result.totalPage!'1'}", function () {
                openUrl('search/'+currentPage+'/?key='+$("#gjz22").val().trim())
            })
        }

    })
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
                currentPage = page
                callback && callback()
            }
        })
    }
</script>
</body>

</html>
