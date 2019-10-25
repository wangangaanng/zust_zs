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
                <div>
                    <label for="input_sfzh">身份证号：</label>
                    <input type="number" id="input_sfzh" placeholder="输入身份证号" >
                    <label for="input_zkzh">准考证号：</label>
                    <input type="number" id="input_zkzh" placeholder="输入准考证号" >
                    <button>查 询</button>
                </div>
                <div>
                    <table>
                        <tr>
                            <th>准考证号： </th>
                            <td id="zkzh"></td>
                            <th>身份证： </th>
                            <td id="sfzh"></td>
                        </tr>
                        <tr style="display:none">
                            <th>姓名： </th>
                            <td id="xm"></td>
                            <th> </th>
                            <td id="lxdh"></td>
                        </tr>
                    </table>
                    <div class="line pb5 m10"></div>
                    <div class="p10">
                        <table class="tb2 w" id="cjtab">
                            <tr>
                                <th> 姓名 </th>
                                <th> 科目名称 </th>
                                <th> 成绩</th>
                                <th> 录入时间 </th>
                                <th> 是否合格</th>
                                <th> 备注或名次 </th>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="fuye_sr_con">
                    <dl class="success" style="display:none;" id="successed">
                        <dt></dt>
                        <dd><h4 class="co_r">恭喜你</h4>
                            <p>你已经被<strong class="co_r" id="zhuhe"></strong></p>
                            <p id="lqd"></p>
                            <p id="ems_dh"></p>
                        </dd>
                    </dl>
                    <dl class="error" style="display:none;" id="failed">
                        <dt></dt>
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
<script>

</script>
</body>
</html>
