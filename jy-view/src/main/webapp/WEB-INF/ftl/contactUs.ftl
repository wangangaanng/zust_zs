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
        <#include "com/route.ftl">
        <div class="content">
            <#include "com/subMenu.ftl">
            <div class="content-list">
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">${thirdDirName!''}
                            <button class="btn green pull-right" onclick="message()">留言</button>
                        </div>

                    </div>
                    <div class="message">
                    <#if (result??)&&(result.records??)&&(result.records?size>0)>
                        <#assign flag=1>
                        <#list result.records as obj>
                    <div class="al-item "><#--active2-->
                        <div class="al-question">
                            <i></i> 来自${obj.LYIP!''}的提问：${obj.WTNR!''}
                            <div class="pull-right twrq">${obj.TWRQ!''}</div>
                        </div>
                    <#--<#if obj.danr??>-->
                        <div class="al-answer">
                            <i></i> 管理员的回复：${obj.DANR!"暂无回复"}
                            <div class="pull-right twrq">${obj.HDRQ!''}</div>
                        </div>
                    </div>
                        </#list>
                    <#else >
                        <#assign flag=0>
                    </#if>

                        <div class="text-center">
                            <nav aria-label="Page navigation">
                                <ul class="pagination">

                                </ul>
                            </nav>
                        </div>
                        <#--<div class="message-content">
                            <div class="message-input">
                                <label>
                                    <i class="icon ic-name"></i>
                                </label>
                                <input class="" type="text" id="xm" placeholder="姓名" />
                            </div>
                            <div class="message-input">
                                <label>
                                    <i class="icon ic-tel"></i>
                                </label>
                                <input class="" type="text" id="sjh" placeholder="手机号" />
                            </div>
                        </div>
                        <div class="message-content1">
                            <div class="message-textarea">
                                <label>
                                    <i class="icon ic-message"></i>
                                </label>
                                <textarea class="" type="text" id="wtnr" placeholder="留言" /></textarea>
                            </div>
                        </div>
                        <div class="message-btn">
                            <button class="btn" onclick="consult()">发送</button>
                        </div>-->
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
            $(".message").append(nulltip)
        }else {
            setPage(currentPage, "${result.totalPage!'1'}", function () {
                openUrl('contactUs/${secondDir!""}/${thirdDir!""}/' + currentPage)
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
    var layer1;
    function message() {
        layer1=layer.open({
            type: 1,
            title: '留言',
            skin: 'layui-layer-rim', //加上边框
            area: ['750px', '480px'], //宽高
            content: '<div class="message-content" style="padding: 15px;height: 80px;">\n' +
            '                            <div class="message-input">\n' +
            '                                <label>\n' +
            '                                    <i class="icon ic-name"></i>\n' +
            '                                </label>\n' +
            '                                <input class="" type="text" id="xm" placeholder="姓名" />\n' +
            '                            </div>\n' +
            '                            <div class="message-input">\n' +
            '                                <label>\n' +
            '                                    <i class="icon ic-tel"></i>\n' +
            '                                </label>\n' +
            '                                <input class="" type="text" id="sjh" placeholder="手机号" />\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="message-content1" style="margin: 0;padding: 0 15px;height:250px;">\n' +
            '                            <div class="message-textarea" >\n' +
            '                                <label>\n' +
            '                                    <i class="icon ic-message"></i>\n' +
            '                                </label>\n' +
            '                                <textarea class="" type="text" id="wtnr" placeholder="留言" /></textarea>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="message-btn" style="padding: 0 15px;margin-top:30px;">\n' +
            '                            <button class="btn" onclick="consult()">发送</button>\n' +
            '                        </div>'
        });
    }
    function consult() {
        if(!$("#xm").val().trim()){
            walert("请填写姓名");
            return
        }
        if(!$("#sjh").val().trim()){
            walert("请填写手机号");
            return
        }else{

            var length = $("#sjh").val().trim().length;
            var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
            if(length!=11||!mobile.test($("#sjh").val().trim())){
                walert("请填写11位正确手机号");
                return
            }
        }
        if(!$("#wtnr").val().trim()){
            walert("请填写留言内容");
            return
        }
        var jsonObj={
            "wtnr":$("#wtnr").val(),
            "zxlx": 5,
            "sjh": $("#sjh").val().trim(),
            "xm": $("#xm").val().trim(),
            "studentOwid": getCookie("stuOwid")
        }
        ajax("zustcommon/bckjBizZxzx/consult", jsonObj, function (data) {
            if(data.backCode==0){
               walert("留言成功")
                layer.close(layer1)
            }else{
                walert(data.errorMess)
            }
        })
    }
</script>
</body>

</html>