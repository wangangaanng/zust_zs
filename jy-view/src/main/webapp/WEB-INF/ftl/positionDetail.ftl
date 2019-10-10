<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <style>
        .tag-grey{
            position: relative;
            top: -2px;
        }
    </style>
</head>

<body>
<#include "com/header.ftl">
    <div class="main">
        <div class="container">
            <#include "com/route.ftl">
            <div class="content">
                <div class="article-detail">
                    <div class="position-detail">
                        <#if (result??)&&(result.zwlx==1)>
                            <div class="position-head">
                                <#if (result.zwSxsj?exists)&&((result.zwSxsj)?date("yyyy-MM-dd HH:mm:ss") lt (.now)?date)>
                                    <h1><span class="tag-grey">过期</span>${result.zwbt!''}</h1>
                                <#else >
                                    <h1>${result.zwbt!''}</h1>
                                </#if>
                                <dl class="info">
                                    <dt><a href="">${result.zphCbf!''}</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo">
                                <#if result.zphKsrq?exists>
                                    <li>具体时间：<span>${result.zphKsrq?substring(0,16)}</span></li>
                                </#if>
                                <#if result.zphJtsj?exists>
                                    <li>举办时长：<span> ${result.zphJtsj!''}</span></li>
                                </#if>
                                <li>具体城市：<span> ${result.zwCity!''}</span></li>
                                <li>举办地点：<span>${result.zphJbdd!''}</span></li>
                            </ul>
                            <div class="tools cl">
                                <#if (result.exp2??)&&(result.exp2!="0")>
                                    <a class="btn_1 shoucang" style="display: none;" onclick="saveJob()">收藏</a><a class="btn_1 quxiao" onclick="cancelJob()">取消收藏</a>
                                <#else >
                                    <a class="btn_1 shoucang" onclick="saveJob()">收藏</a><a class="btn_1 quxiao" style="display: none;" onclick="cancelJob()">取消收藏</a>
                                </#if>
                            </div>
                            <div class="vTools">
                                <div class="warn">
                                    <span>信息来源：<img src="../img/icon-zz.png" class="shield">浙江科技学院就业信息网</span>
                                    温馨提示：求职需提高谨慎，辨别信息真伪，勿上当受骗。
                                </div>
                            </div>
                            <div class="position-tabbar"><ul><li class="active"><a>详情</a></li></ul></div>
                            <div class="frame-body tabbar-frame_content">
                                <div><p id="memo"></p></div>
                            </div>
                        </#if>
                        <#if (result??)&&(result.zwlx==2)>
                            <div class="position-head">
                                <#if (result.zwSxsj?exists)&&((result.zwSxsj)?date("yyyy-MM-dd HH:mm:ss") lt (.now)?date)>
                                    <h1><span class="tag-grey">过期</span>${result.zwbt!''}</h1>
                                <#else >
                                    <h1>${result.zwbt!''}</h1>
                                </#if>
                                <dl class="info">
                                    <dt><a href="">企业招聘公告</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo xInfo-2 cl">
                                <li>工作城市：<span>${result.zwCity!''}</span></li>
                                <#if result.createtime?exists>
                                    <li>发布时间：<span>${result.createtime?substring(0,16)}</span></li>
                                </#if>
                            </ul>
                            <div class="tools cl">
                                <#if (result.exp2??)&&(result.exp2!="0")>
                                    <a class="btn_1 shoucang" style="display: none;" onclick="saveJob()">收藏</a><a class="btn_1 quxiao" onclick="cancelJob()">取消收藏</a>
                                <#else >
                                    <a class="btn_1 shoucang" onclick="saveJob()">收藏</a><a class="btn_1 quxiao" style="display: none;" onclick="cancelJob()">取消收藏</a>
                                </#if>
                            </div>
                            <div class="vTools">
                                <div class="warn">
                                    <span>信息来源：<img src="../img/icon-zz.png" class="shield">浙江科技学院就业信息网</span>
                                    温馨提示：求职需提高谨慎，辨别信息真伪，勿上当受骗。
                                </div>
                            </div>
                            <div class="position-tabbar"><ul><li class="active"><a>详情</a></li></ul></div>
                            <div class="frame-body tabbar-frame_content">
                                <div><p id="memo"></p></div>
                            </div>
                        </#if>
                        <#if (result??)&&(result.zwlx==3)>
                            <div class="position-head">
                                <#if (result.zwSxsj?exists)&&((result.zwSxsj)?date("yyyy-MM-dd HH:mm:ss") lt (.now)?date)>
                                    <h1><span class="tag-grey">过期</span>${result.zwbt!''}</h1>
                                <#else >
                                    <h1>${result.zwbt!''}</h1>
                                </#if>
                                <dl class="info">
                                    <dt><a href="">社会招聘会</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo">
                                <#if result.zphKsrq?exists>
                                    <li>具体时间：<span>${result.zphKsrq?substring(0,16)}</span></li>
                                </#if>
                                <#if result.zphJtsj?exists>
                                    <li>举办时长：<span> ${result.zphJtsj!''}</span></li>
                                </#if>
                                <li>具体城市：<span>${result.zwCity!''}</span></li>
                                <li>举办地点：<span>${result.zphJbdd!''}</span></li>
                            </ul>
                            <div class="tools cl"> <a class="btn_1" onclick="applyJob()">我要报名</a>
                                <#if (result.exp2??)&&(result.exp2!="0")>
                                    <a class="link_1 shoucang" style="display: none;" onclick="saveJob()">收藏</a><a class="link_1 quxiao" onclick="cancelJob()">取消收藏</a>
                                <#else >
                                    <a class="link_1 shoucang" onclick="saveJob()">收藏</a><a class="link_1 quxiao" style="display: none;" onclick="cancelJob()">取消收藏</a>
                                </#if>
                            </div>
                            <div class="vTools">
                                <div class="warn">
                                    <span>信息来源：<img src="../img/icon-zz.png" class="shield">浙江科技学院就业信息网</span>
                                    温馨提示：求职需提高谨慎，辨别信息真伪，勿上当受骗。
                                </div>
                            </div>
                            <div class="position-tabbar"><ul><li class="active"><a>详情</a></li></ul></div>
                            <div class="frame-body tabbar-frame_content">

                                <div><p id="memo"></p></div>
                            </div>
                        </#if>
                        <#if (result??)&&(result.zwlx==4)>
                            <div class="position-head">
                                <#if (result.zwSxsj?exists)&&((result.zwSxsj)?date("yyyy-MM-dd HH:mm:ss") lt (.now)?date)>
                                    <h1><span class="tag-grey">过期</span>${result.zwbt!''}</h1>
                                <#else >
                                    <h1>${result.zwbt!''}</h1>
                                </#if>
                                <dl class="info">
                                    <dt><a href="">宣讲会</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo">
                                <#if result.zphKsrq?exists>
                                    <li>具体时间：<span>${result.zphKsrq?substring(0,16)}</span></li>
                                </#if>
                                <#if result.zphJtsj?exists>
                                    <li>举办时长：<span> ${result.zphJtsj!''}</span></li>
                                </#if>
                                <li>具体城市：<span>${result.zwCity!''}</span></li>
                                <li>举办地点：<span>${result.zphJbdd!''}</span></li>
                            </ul>
                            <div class="tools cl"> <a class="btn_1" onclick="applyJob()">我要报名</a>
                                <#if (result.exp2??)&&(result.exp2!="0")>
                                    <a class="link_1 shoucang" style="display: none;" onclick="saveJob()">收藏</a><a class="link_1 quxiao" onclick="cancelJob()">取消收藏</a>
                                <#else >
                                    <a class="link_1 shoucang" onclick="saveJob()">收藏</a><a class="link_1 quxiao" style="display: none;" onclick="cancelJob()">取消收藏</a>
                                </#if>
                            </div>
                            <div class="vTools">
                                <div class="warn">
                                    <span>信息来源：<img src="../img/icon-zz.png" class="shield">浙江科技学院就业信息网</span>
                                    温馨提示：求职需提高谨慎，辨别信息真伪，勿上当受骗。
                                </div>
                            </div>
                            <div class="position-tabbar"><ul><li class="active"><a>详情</a></li></ul></div>
                            <div class="frame-body tabbar-frame_content">
                                <div><p id="memo"></p></div>
                            </div>
                        </#if>
                        <#if (result??)&&(result.zwlx==0)>
                            <div class="position-head">
                                <#if (result.zwSxsj?exists)&&((result.zwSxsj)?date("yyyy-MM-dd HH:mm:ss") lt (.now)?date)>
                                    <h1><span class="tag-grey">过期</span>${result.zwbt!''}</h1>
                                <#else >
                                    <h1>${result.zwbt!''}</h1>
                                </#if>
                                <dl class="info">
                                    <#if result.qyxx??>
                                        <#assign qy=result.qyxx>
                                        <dt><a href="">${qy.qymc!''}</a></dt>
                                    <#else >
                                        <dt><a href="">未知企业</a></dt>
                                    </#if>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo">
                            <#if result.qyxx??>
                                <#assign qy=result.qyxx>
                                <li>公司性质：<span>${qy.qyGsxzStr!''}</span></li>
                                <li>公司行业：<span>${qy.qyHylbStr!''}</span></li>
                                <li>公司规模：<span>${qy.qyGsgmStr!'未知'}</span></li>
                            <#else >
                                <li>公司性质：<span>未知</span></li>
                                <li>公司行业：<span>未知</span></li>
                                <li>公司规模：<span>未知</span></li>
                            </#if>
                            </ul>
                            <ul class="xInfo xInfo-2 cl">
                                <li>工作性质：<span>${result.zwGzxzStr!''}</span></li>
                            <#if result.createtime?exists>
                                <li>发布日期：<span>${result.createtime?substring(0,16)}</span></li>
                            </#if>
                                <li>工作年限：<span>${result.zwGznxStr!''}</span></li>
                                <li>学历要求：<span>${result.zwXlyqStr!''}</span></li>
                                <li>年龄要求：<span>${result.zwNlyqStr!''}</span></li>
                                <li>招聘人数：<span>${result.zwZprs!'0'}人</span></li>
                                <li>语言能力：<span>${result.zwYyyqStr!''}</span></li>
                                <li>工作地点：<span> ${result.zwCity!''}</span></li>
                                <#if result.zwXs?exists>
                                    <li>薪资待遇：<span>${result.zwXs!''}元</span></li>
                                <#else >
                                    <li>薪资待遇：</li>
                                </#if>
                                <li>职位类别：<span>${result.zwGzznStr!''}</span></li>
                            </ul>
                            <div class="tools cl"> <a class="btn_1" onclick="applyJob()">申请职位</a>
                                <#if (result.exp2??)&&(result.exp2!="0")>
                                    <a class="link_1 shoucang" style="display: none;" onclick="saveJob()">收藏</a><a class="link_1 quxiao" onclick="cancelJob()">取消收藏</a>
                                <#else >
                                    <a class="link_1 shoucang" onclick="saveJob()">收藏</a><a class="link_1 quxiao" style="display: none;" onclick="cancelJob()">取消收藏</a>
                                </#if>
                            </div>

                            <div class="vTools">
                                <div class="warn">
                                    <span>信息来源：<img src="../img/icon-zz.png" class="shield">浙江科技学院就业信息网</span>
                                    温馨提示：求职需提高谨慎，辨别信息真伪，勿上当受骗。
                                </div>
                            </div>

                            <div class="position-tabbar"><ul><li class="active"><a>岗位说明</a></li></ul></div>
                            <div class="frame-body tabbar-frame_content">
                                <div><p>${result.zwGwzz!''}</p></div>
                            </div>
                            <#if result.qyxx??>
                                <#assign qy=result.qyxx>
                            <div class="position-tabcontent">
                                <div class="position-tabbar"><ul><li class="active"><a>公司简介</a></li><li><a>联系方式</a></li></ul></div>
                                <div class="frame-body tabbar-frame_content">
                                    <div class="tabcontent position-company">
                                        <b>${qy.qymc!''}</b>
                                        <p>${qy.qyGsjs!''}</p>
                                    </div>
                                    <div class="tabcontent" style="display: none;">
                                        <ul class="xInfo">
                                            <li>联系人：<span>${qy.qyLxr!''}</span></li>
                                            <li>联系电话：<span>${qy.qyLxrdh!''}</span></li>
                                            <li>企业邮箱：<span>${qy.qyYx!''}</span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            </#if>
                        </#if>

                    </div>
                </div>
            </div>

        </div>
    </div>
    <#include "com/footer.ftl">
    <script>
        var string='${result.memo!''}'
        var memo=closeHTML(string);
        $("#memo").html(memo);
        var jlowid="${result.exp2!'0'}"
        $(".position-tabbar ul li").hover(function () {
            $(this).addClass('active').siblings().removeClass('active');
            $(this).parents(".position-tabcontent").find(".tabcontent").eq($(this).index()).show().siblings().hide();
        })
        function applyJob() {
            var bmdx="${result.zwlx!''}";
            if(bmdx=='0'){
                bmdx=2
            }else if(bmdx=='3'){
                bmdx=0
            }else if(bmdx=='4'){
                bmdx=1
            }
            if(getCookie('stuOwid')){
                var jsonObj={
                    "jobRefOwid":"${result.owid!''}",
                    "bmlx":'1',
                    "bmdx":bmdx,
                    "yhRefOwid":getCookie('stuOwid'),
                }
                ajax("zustjy/bckjBizJybm/applyJob", jsonObj, function (data) {
                    if(data.backCode==0){
                        layer.msg('申请成功', {icon: 1});
                    }else if(data.backCode==2){
                        layer.msg("请先登录", {icon: 2});
                        setTimeout('window.location.href="/"',1500);
                    }else{
                        layer.msg(data.errorMess, {icon: 2});
                    }

                })
            }else{
                login();

            }
        }
        function saveJob() {
            if(getCookie('stuOwid')){
                var jsonObj={
                    "jobRefOwid":"${result.owid!''}",
                    "xxlb":'0',
                    "yhRefOwid":getCookie('stuOwid'),
                }
                ajax("zustjy/bckjBizXsgz/signInOrScribe", jsonObj, function (data) {
                    if(data.backCode==0){
                        jlowid=data.bean[0].owid;
                        layer.msg('收藏成功', {icon: 1});
                        $(".shoucang").hide();
                        $(".quxiao").show();
                    }else if(data.backCode==2){
                        layer.msg("请先登录", {icon: 2});
                        setTimeout('window.location.href="/"',1500);
                    }else {
                        layer.msg('收藏失败', {icon: 2});
                    }

                })
            }else{
                login();

            }
        }
        function cancelJob() {
            if(getCookie('stuOwid')){
                var jsonObj={
                    "owid":jlowid,
                }
                ajax("zustjy/bckjBizXsgz/cancelSubcribe", jsonObj, function (data) {
                    if(data.backCode==0){
                        layer.msg('取消成功', {icon: 1});
                        $(".shoucang").show();
                        $(".quxiao").hide();
                    }else {
                        layer.msg('取消失败', {icon: 2});
                    }

                })
            }else{
                login();

            }
        }
    </script>
</body>

</html>