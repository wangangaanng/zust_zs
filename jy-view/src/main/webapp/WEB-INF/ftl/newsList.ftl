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
        <div class="main">
            <div class="container">
                <#include "com/route.ftl">
                <div class="content">
                   <#include "com/subMenu.ftl">
                    <div class="content-list">
                        <div class="search-bar">
                            <div class="search-label">搜素关键字：</div>
                            <div class="input-group search-input">
                                <input type="text" id="key" onblur="AntiSqlValid(this)" class="form-control" placeholder="输入内容" value="${key!''}">
                                <div class="input-group-btn">
                                    <button onclick="search()" type="button" class="btn btn-default green"><span class="glyphicon glyphicon-search"></span></button>

                                </div>
                            </div>
                        </div>
                        <div class="news-list">
                            <ul>
                                <#if (result??)&&(result.records??)&&(result.records?size>0)>
                                    <#assign flag=1>
                                    <#list result.records as obj>
                                        <li><a href="${base}/newsDetail/${obj.owid!''}" target="_blank">
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
            <script src="${base}/js/bootstrap-paginator.min.js" type="text/javascript"></script>
<script>
    var currentPage="${result.currentPage!'1'}"
    $(document).ready(function () {
        if("${flag}"==0){
            $(".content-list").append(nulltip)
        }else {
            setPage(currentPage, "${result.totalPage!'1'}", function () {
                AntiSqlValidAll(["#key"],function () {
                    if ($("#key").val()) {
                        openUrl('newsList/${secondDir!""}/${thirdDir!""}/' + currentPage + '/?key=' + $("#key").val())
                    } else {
                        openUrl('newsList/${secondDir!""}/${thirdDir!""}/' + currentPage)
                    }
                })

            })
        }

    })
    document.onkeydown = function(e){
        if(e.keyCode==13)
        {
            search();
        }
    }
    function search() {
        AntiSqlValidAll(["#key"],function () {
            var key = $("#key").val();
            if (key) {
                openUrl('newsList/${secondDir!""}/${thirdDir!""}/1?key=' + key)
            } else {
                openUrl('newsList/${secondDir!""}/${thirdDir!""}/1')
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
                currentPage = page
                callback && callback()
            }
        })
    }
</script>
</body>

</html>
