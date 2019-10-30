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
    .fuye_search{border-width:1px 0; padding: 18px 0 0 10px;; background-color:#f9f9f9; overflow:hidden;margin-right: 22px;}
    .fuye_search ul li{ float:left; padding:0 15px; margin-bottom:5px;}
    .fuye_search ul li p.title{ line-height:36px; float:left;}
    .fuye_search ul li a.fuye_search_btn, a.fuye_search_btn{ display: inline-block; background-color:rgb(85,167,153); height:34px; width:120px; text-align:center; line-height:34px; color:#fff; font-size:14px;}
    .form-control{width: 150px;display: inline-block}
    .table-bordered{width: 78%}
    .table-bordered th{text-align: center}
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
            <div class="content-form">
                <form class="form-horizontal" id="queryForm"  action="" target="queryFrame">
                    <div class="fuye_search">
                        <div class="form-group">
                            <label for="nf" class="col-sm-1">年份：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="nf" id="nf">
                                    <option value="">---请选择---</option>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a onclick="" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                            <label for="sf" class="col-sm-1">省份：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="sf" id="sf">
                                    <option value="">---请选择---</option>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a onclick="" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                            <label for="kl" class="col-sm-1">科类：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="sf" id="kl">
                                    <option value="">---请选择---</option>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a onclick="" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pc" class="col-sm-1">批次：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="pc" id="pc">
                                    <option value="">---请选择---</option>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a onclick="" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                            <label for="zy" class="col-sm-1">专业：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="zy" id="zy">
                                    <option value="">---请选择---</option>
                                </select>
                                <span style="background: #FFB300;border:1px solid #FFB300;"><a onclick="" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                            </div>
                            <button type="button" class="btn btn-default"
                                    style="background-color: rgb(85,167,153);color: #ffffff;width: 150px;height: 34px;margin-left: 90px">导出</button>
                        </div>
                    </div>
                </form>
                <div class="fuye_search_result" style="margin-top: 20px">
                    <div class="p10" style="padding: 10px">
                        <table class="table table-bordered">
                            <tr>
                                <th> 年份 </th>
                                <th> 省份 </th>
                                <th> 科类 </th>
                                <th> 批次 </th>
                                <th> 专业 </th>
                                <th> 学制 </th>
                                <th> 录取数</th>
                                <th> 最高分</th>
                                <th> 最低分</th>
                                <th> 平均分</th>
                            </tr>
                        </table>
                        <div class="text-center">
                            <nav aria-label="Page navigation">
                                <ul class="pagination">

                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
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
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script>

</script>
</body>
</html>
