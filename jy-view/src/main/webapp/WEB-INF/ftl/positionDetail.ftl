<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
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
                                <h1>${result.zwbt!''}</h1>
                                <dl class="info">
                                    <dt><a href="">${result.zphCbf!''}</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo">
                                <li>具体时间：<span>${result.zphKsrq?substring(0,10)}</span></li>
                                <li>具体城市：<span>${result.zwPro!''} - ${result.zwCity} - ${result.zwArea!''}</span></li>
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
                                <div><p>${result.memo!''}</p></div>
                            </div>
                        </#if>
                        <#if (result??)&&(result.zwlx==2)>
                            <div class="position-head">
                                <h1>${result.zwbt!''}</h1>
                                <dl class="info">
                                    <dt><a href="">企业招聘公告</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo xInfo-2 cl">
                                <li>工作城市：<span>${result.zwPro!''} - ${result.zwCity} - ${result.zwArea!''}</span></li>
                                <li>发布日期：<span>${result.createtime?substring(0,16)}</span></li>
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
                                <div><p>${result.memo!''}</p></div>
                            </div>
                        </#if>
                        <#if (result??)&&(result.zwlx==3)>
                            <div class="position-head">
                                <h1>${result.zwbt!''}</h1>
                                <dl class="info">
                                    <dt><a href="">社会招聘会</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo">
                                <li>具体时间：<span>${result.zphKsrq?substring(0,10)}</span></li>
                                <li>具体城市：<span>${result.zwPro!''} - ${result.zwCity} - ${result.zwArea!''}</span></li>
                                <li>举办地点：<span>${result.zphJbdd!''}</span></li>
                            </ul>
                            <div class="tools cl"> <a class="btn_1" onclick="applyJob()">学生报名参加</a>
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
                                <div><p>${result.memo!''}</p></div>
                            </div>
                        </#if>
                        <#if (result??)&&(result.zwlx==4)>
                            <div class="position-head">
                                <h1>${result.zwbt!''}</h1>
                                <dl class="info">
                                    <dt><a href="">宣讲会</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo">
                                <li>具体时间：<span>${result.zphKsrq?substring(0,10)}</span></li>
                                <li>具体城市：<span>${result.zwPro!''} - ${result.zwCity} - ${result.zwArea!''}</span></li>
                                <li>举办地点：<span>${result.zphJbdd!''}</span></li>
                            </ul>
                            <div class="tools cl"> <a class="btn_1" onclick="applyJob()">学生报名参加</a>
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
                                <div><p>${result.memo!''}</p></div>
                            </div>
                        </#if>
                        <#if (result??)&&(result.zwlx==0)>
                            <div class="position-head">
                                <h1>${result.zwbt!''}</h1>
                                <#assign qy=result.qyxx>
                                <dl class="info">
                                    <dt><a href="">${qy.qymc!''}</a></dt>
                                    <dd>
                                        <div class="vieCount">浏览：${result.zwYds!'0'}次 </div>
                                    </dd>
                                </dl>
                            </div>
                            <ul class="xInfo">
                                <li>公司性质：<span>${qy.qyGsxzStr!''}</span></li>
                                <li>公司行业：<span>${qy.qyHylbStr!''}</span></li>
                                <li>公司规模：<span>${qy.qyGsgmStr}</span></li>
                            </ul>
                            <ul class="xInfo xInfo-2 cl">
                                <li>工作性质：<span>${result.zwGzxzStr!''}</span></li>
                                <li>发布日期：<span>${result.createtime?substring(0,10)}</span></li>
                                <li>工作年限：<span>${result.zwGznxStr!''}</span></li>
                                <li>学历要求：<span>${result.zwXlyqStr!''}</span></li>
                                <li>年龄要求：<span>${result.zwNlyqStr!''}</span></li>
                                <li>招聘人数：<span>${result.zwZprs!'0'}人</span></li>
                                <li>语言能力：<span>${result.zwYyyqStr!''}</span></li>
                                <li>工作地点：<span>${result.zwPro!''} - ${result.zwCity} - ${result.zwArea!''}</span></li>
                                <li>薪资待遇：<span>${result.zwXs!''}</span></li>
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

                    </div>
                </div>
            </div>

        </div>
    </div>
    <#include "com/footer.ftl">
    <script>
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
                    layer.msg('申请成功', {icon: 1});
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