<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>

<body>
    <#include "com/ZSheader.ftl">
    <img class="ejgg" style="width:100%;height:300px;" src="${base}/img/loginbackgrouind2.jpg">
    <div class="clear"></div>
        <div class="main">
            <div class="container">
                <div class="content">
                    <div class="menu-nav">
                        <div class="menu-title">
                            <div class="title-chn">${secondDirName!''} <div class="menu-nav-icon"></div></div>

                        </div>
                        <div class="menu-list">
                            <ul class="list-group">
                            <#if (menuList??)&&(menuList?size>0)>
                                <#list menuList as obj>
                                    <#if obj_index==thirdDir?number>
                                    <li class="list-group-item active1" onclick='openUrl("wzOrTpOrSqLm/${lmbh!''}/${obj_index}")'>
                                    <#else >
                                    <li class="list-group-item" onclick='openUrl("wzOrTpOrSqLm/${lmbh!''}/${obj_index}")'>
                                    </#if>
                                    <span class="ic-menu"></span> ${obj.NAME!''}
                                </li>
                                </#list>
                            </#if>
                            </ul>
                        </div>
                    </div>
                    <div class="content-list">
                        <div class="nav-bar">
                        <#include "com/route.ftl">
                            <div class="search-bar">
                                <div class="input-group search-input">
                                    <input type="text" id="key" class="form-control" placeholder="搜索关键字" value="${key!''}">
                                    <div class="input-group-btn">
                                        <button onclick="search()" type="button" class="btn btn-default green"><span class="glyphicon glyphicon-search"></span></button>

                                    </div>
                                </div>
                            </div>
                            <div style="clear:both;"></div>
                        </div>

                        <div class="news-list">
                            <ul>
                                <#if (result??)&&(result.records??)&&(result.records?size>0)>
                                    <#assign flag=1>
                                    <#list result.records as obj>
                                        <li><a href="${base}/wzxq/${obj.owid!''}" target="_blank">
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
                        openUrl('wzOrTpOrSqLm/${lmbh!""}/${thirdDir!""}/' + currentPage + '/?key=' + encodeURI($("#key").val().trim()))
                    } else {
                        openUrl('wzOrTpOrSqLm/${lmbh!""}/${thirdDir!""}/' + currentPage)
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
            var key = $("#key").val().trim();
            if (key) {
                openUrl('wzOrTpOrSqLm/${lmbh!""}/${thirdDir!""}/1?key=' + encodeURI(key))
            } else {
                openUrl('wzOrTpOrSqLm/${lmbh!""}/${thirdDir!""}/1')
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
