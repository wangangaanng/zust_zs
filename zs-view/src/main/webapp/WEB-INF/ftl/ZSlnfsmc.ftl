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
<body>
<#include "com/ZSheader.ftl">
<div class="main">
    <div class="container">
        <div class="content">
            <div class="content-list">
                <div class="fuye_search mt30">
                    <ul>
                        <li>
                            <p class="title">年份：</p>
                            <label class="select-normal">
                                <select id="nf" onchange="mychange(this)">
                                    <option value="" selected="selected">---请选择---</option>
                                    <s:iterator value="#request.nfList" id="item">
                                        <option val="${item.NF}">${item.NF }</option>
                                    </s:iterator>
                                </select>
                                <span>---请选择---</span></label>
                            <span style="background: #FFB300;border:1px solid #FFB300;"><a val="nf" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                        </li>
                        <li>
                            <p class="title">省份：</p>
                            <label class="select-normal">
                                <select id="sf" onchange="mychange(this)">
                                    <option value="" selected="selected">---请选择---</option>
                                    <s:iterator value="#request.sfList" id="item">
                                        <option val="${item.SF}">${item.SF }</option>
                                    </s:iterator>
                                </select>
                                <span>---请选择---</span> </label>
                            <span style="background: #FFB300;border:1px solid #FFB300;"><a val="sf" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                        </li>
                        <li>
                            <p class="title">科类：</p>
                            <label class="select-normal">
                                <select id="kl" onchange="mychange(this)">
                                    <option value="" selected="selected">---请选择---</option>
                                    <s:iterator value="#request.klList" id="item">
                                        <option val="${item.KL}">${item.KL }</option>
                                    </s:iterator>
                                </select>
                                <span>---请选择---</span> </label>
                            <span style="background: #FFB300;border:1px solid #FFB300;"><a val="kl" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                        </li>
                        <li>
                            <p class="title">批次：</p>
                            <label class="select-normal">
                                <select id="pc" onchange="mychange(this)">
                                    <option value="" selected="selected">---请选择---</option>
                                    <s:iterator value="#request.pcList" id="item">
                                        <option val="${item.PC}">${item.PC }</option>
                                    </s:iterator>
                                </select>
                                <span>---请选择---</span> </label>
                            <span style="background: #FFB300;border:1px solid #FFB300;"><a val="pc" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                        </li>
                        <li>
                            <p class="title">专业：</p>
                            <label class="select-normal">
                                <select id="zy" onchange="mychange(this)">
                                    <option value="" selected="selected">---请选择---</option>
                                    <s:iterator value="#request.zyList" id="item">
                                        <option val="${item.ZY}">${item.ZY }</option>
                                    </s:iterator>
                                </select>
                                <span>---请选择---</span> </label>
                            <span style="background: #FFB300;border:1px solid #FFB300;"><a val="zy" onclick="clearVal(this)" style="font-size:12px;margin-bottom:5px;color:#fff">清除</a></span>
                        </li>
                        <li><a id="export_btn" class="fuye_search_btn mr5">导出</a></li>
                    </ul>
                </div>
                <div class="fuye_search_result mt20"> <em class="tip-label"></em>
                    <div class="p10">
                        <table id ="lnfsmc_list" class="tb2 w">
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
                                <th>平均分</th>
                            </tr>
                        </table>
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
