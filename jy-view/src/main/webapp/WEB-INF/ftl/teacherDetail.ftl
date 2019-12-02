<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
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
                <li><a href="${base}/">首页</a></li>
                <li><a href="${base}/stuCenter/0">个人中心</a></li>
                <li class="active">导师简介</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav" style="border: none;">
                <div class="td-item">
                    <div class="t-bg" style="background: url(${imagePath!""}${(detail.zjtx)!''});background-size: 100% 100%;"></div>
                    <div class="td-detail">
                        <div class="t-name">${(detail.zjxm)!''}</div>
                        <div class="t-xhx"><span></span></div>
                        <div class="t-sm">${(detail.zjxx)!'暂无简介'}</div>

                    </div>
                </div>
            </div>

            <div class="content-list" style="min-height: 707px;height:auto;">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">导师简介</div>
                    </div>
                    <div class="teacher-list">
                        <p class="td-sm" style="padding: 0 20px;">
                            <#if (detail.zjxm)??>
                            <span>姓名：${(detail.zjxm)!''}</span>
                            </#if>
                            <#if (detail.xb)??>
                            <span style="margin-left: 40px;">性别：
                                <#if detail.xb==1>
                                    男
                                <#elseif detail.xb==2>
                                     女
                                <#else>
                                </#if>
                            </span>
                            </#if>
                            <#if (detail.exp4)??>
                            <span style="margin-left: 40px;">咨询方向：${(detail.exp4)!''} </span>
                            </#if>
                            <#if (detail.exp3)??>
                            <span style="margin-left: 40px;">所在部门：${(detail.exp3)!''} </span>
                            </#if>
                        </p>
                        <p class="td-sm">
                            ${(detail.zjxx)!'暂无简介'}
                        </p>
                        <div class="td-ask">
                            <button class="btn" onclick="question()">我要咨询</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<input type="hidden" id="teacherOwid" value="${towid}" />
<input type="hidden" id="owid" value="${(detail.owid)!''}" />
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script>

    $(document).ready(function () {

    })

    function question() {
        if(!isTimeOut()) {
            layer.open({
                type: 1,
                scrollbar: false,
                title: '填写咨询内容',
                btn: ["提交", "取消"],
                skin: 'layui-layer-rim', //加上边框
                area: ['450px', '320px'], //宽高
                content: '<div class="zx-textarea"><textarea id="wtnr"></textarea></div>',
                yes: function (index) {
                    ask(index);
                }
            })
        }
    }

    function ask(index) {
        if(!isTimeOut()) {
            if (!$("#wtnr").val()) {
                walert("请填写咨询内容");
                return;
            }
            var jsonObj = {
                "wtnr": $("#wtnr").val(),
                "owid": $("#owid").val(),
                "zxlx": 2,
                "studentOwid": getCookie("stuOwid")
            }
            ajax("zustcommon/bckjBizZxzx/consult", jsonObj, function (data) {
                if (data.backCode == 0) {
                    layer.close(index)
                    layer.open({
                        title: '提示',
                        scrollbar: false,
                        content: '咨询已提交，请等待回复。',
                        yes: function (index1, layero) {
                            layer.close(index1);
                        }
                    });
                }else{
                    walert(data.errorMess)
                }
            })
        }
    }

</script>
</body>

</html>