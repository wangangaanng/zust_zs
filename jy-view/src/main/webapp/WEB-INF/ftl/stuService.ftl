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
    .control-label{
        color: #999 !important;
    }
    .form-group div{line-height: 34px !important;}
</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
       <#include "com/route.ftl">
        <div class="content">
            <#include "com/subMenu.ftl">
            <div class="content-list" >
                <div class="article-detail" style="border: none;">
                    <div class="article-column-title">
                        <div class="h3">${thirdDirName!''}</div>
                    </div>
                    <div class="article-detail-text text-center">
                        <div class="archives">
                            <div class="archives-title">按姓名和身份证查询</div>
                            <div class="archives-content">
                                <div class="archives-input">
                                    <label>
                                        <i class="icon ic-name"></i>
                                    </label>
                                    <input class="" id="xsxm" type="text" placeholder="请输入姓名" />
                                </div>
                                <div class="archives-input">
                                    <label>
                                        <i class="icon ic-sfz"></i>
                                    </label>
                                    <input class="" id="sfzh" type="text" placeholder="请输入身份证号码" />
                                </div>
                                <div class="archives-input">
                                    <button class="btn green" onclick="inquiryArchives()">查询</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>

        </div>

    </div>
</div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>

<script>
    var index;
    $(document).ready(function () {

    })

    function inquiryArchives(){
        if(!$("#xsxm").val().trim()){
            walert("请输入姓名")
            return;
        }
        if(!$("#sfzh").val().trim()){
            walert("请输入身份证号码")
            return;
        }
        var jsonObj ={
            "xsxm":$("#xsxm").val().trim(),
            "sfzh":$("#sfzh").val().trim()
        }
        ajax("zustjy/bckjBizDacx/inquiryArchives", jsonObj, function (data) {
            if(data.backCode==0){
                var index;
                index=layer.open({
                    type: 1,
                    title:'查询结果',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['750px', '450px'], //宽高
                    content: '<div class="lxr-modal"><div class="row">\n' +
                    '                            <div class="form-group">\n' +
                    '                                <label for="lxr" class="col-sm-3 control-label text-right" style="line-height: 34px;">姓名：</label>\n' +
                    '                                <div class="col-sm-3">\n' +
                    '                                    \n' + data.bean.xsxm +
                    '                                </div>\n' +
                    '                                <label for="lxdh" class="col-sm-3 control-label text-right" style="line-height: 34px;">学号：</label>\n' +
                    '                                <div class="col-sm-3">\n' +
                    '                                    \n' + data.bean.xsxh +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <div class="row">\n' +
                    '                            <div class="form-group">\n' +
                    '                                <label for="jkr" class="col-sm-3 control-label text-right" style="line-height: 34px;">身份证号：</label>\n' +
                    '                                <div class="col-sm-3">\n' +
                    '                                    \n' + data.bean.sfzh +
                    '                                </div>\n' +
                    '                                <label for="xjsj" class="col-sm-3 control-label text-right" style="line-height: 34px;">毕业时间：</label>\n' +
                    '                                <div class="col-sm-3">\n' +
                    '                                    \n' + data.bean.bysj.substring(0,10) +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                            </div>\n' +
                    '                        <div class="row">\n' +
                    '                            <div class="form-group">\n' +
                    '                                <label for="jkr" class="col-sm-3 control-label text-right" style="line-height: 34px;">所在院系：</label>\n' +
                    '                                <div class="col-sm-3">\n' +
                    '                                    \n' + data.bean.szxy +
                    '                                </div>\n' +
                    '                                <label for="xjsj" class="col-sm-3 control-label text-right" style="line-height: 34px;">所在班级：</label>\n' +
                    '                                <div class="col-sm-3">\n' +
                    '                                    \n' + data.bean.szbj.substring(0,10) +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                            </div>\n' +
                    '                        <div class="row">\n' +
                    '                            <div class="form-group">\n' +
                    '                                <label for="jkr" class="col-sm-3 control-label text-right" style="line-height: 34px;">报到证签往单位名称：</label>\n' +
                    '                                <div class="col-sm-3">\n' +
                    '                                    \n' + data.bean.bdzDwmc +
                    '                                </div>\n' +
                    '                                <label for="xjsj" class="col-sm-3 control-label text-right" style="line-height: 34px;">档案转寄单位名称：</label>\n' +
                    '                                <div class="col-sm-3">\n' +
                    '                                    \n' + data.bean.dazjDwmc +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                            </div>\n' +
                    '                        <div class="row">\n' +
                    '                            <div class="form-group">\n' +
                    '                                <label for="jkr" class="col-sm-3 control-label text-right" style="line-height: 34px;">档案转寄单位地址：</label>\n' +
                    '                                <div class="col-sm-8">\n' +
                    '                                    \n' + data.bean.dazjDwdz +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                            </div>\n' +
                    '                        <div class="row btn-yd">\n' +
                    '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
                    '                                <button class="btn green" onclick="closeLayer()">确定</button>\n' +
                    '                            </div>\n' +
                    '                        </div></div>'
                });
            }else{
                walert(data.errorMess)
            }
        })
    }

    function closeLayer(){
        layer.close(index)
        index=null;
    }

    $(".list-group-item").click(function(e) {
        $(this).siblings().removeClass("active1")
        $(this).addClass("active1")
        $(".content-list").hide();
        $(".content-list").eq($(this).index()).show();
    })
</script>
</body>

</html>