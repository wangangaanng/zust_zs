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
    .form-group div{line-height: 34px !important;}
    .layui-layer-content{overflow-y:auto !important;}
    .da-item{float:left;width:50%;}
    .da-item div,.da-item p{width: 50%; float: left;white-space: normal;word-break:break-all;}
    .da-item div:first-child{text-align: right;}
    .oyhidden{overflow-y:hidden !important;}
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
                                    <input class="" id="xsxm" type="text" placeholder="请输入姓名" autocomplete="off" />
                                </div>
                                <div class="archives-input">
                                    <label>
                                        <i class="icon ic-sfz"></i>
                                    </label>
                                    <input class="" id="sfzh" type="text" placeholder="请输入身份证号码" autocomplete="off" />
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
        $(".ohidden").parents().find('.layui-layer-content').addClass("oyhidden")
    })

    function inquiryArchives(){
        // if(getCookie('stuOwid')) {
            if (!$("#xsxm").val().trim()) {
                walert("请输入姓名")
                return;
            }
            if (!$("#sfzh").val().trim()) {
                walert("请输入身份证号码")
                return;
            }
            var jsonObj = {
                "xsxm": $("#xsxm").val().trim(),
                "sfz": $("#sfzh").val().trim()
            }
            ajax("zustcommon/bckjBizJyscheme/queryDocument", jsonObj, function (data) {
                if (data.backCode == 0) {
                    if(data.bean){
                        var str='';
                        var p=data.bean
                        if(p.xm){
                            str+='<div class="da-item"><div>学生姓名：</div><p>'+p.xm+'</p></div>'
                        }
                        if(p.xsxh){
                            str+='<div class="da-item"><div>学生学号：</div><p>'+p.xsxh+'</p></div>'
                        }
                        if(p.xxmc){
                            str+='<div class="da-item"><div>学校名称：</div><p>'+p.xxmc+'</p></div>'
                        }
                        if(p.sfzydk){
                            if(p.sfzydk==1){
                                str+='<div class="da-item"><div>是否专业对口：</div><p>是</p></div>'
                            }else if(p.sfzydk==2){
                                str+='<div class="da-item"><div>是否专业对口：</div><p>否</p></div>'
                            }
                        }
                        if(p.byqx){
                            // $("#byqx").val(p.byqx);
                            // str+='<div class="da-item"><div>毕业去向：</div><p>'+$("#byqx option:selected").html()+'</p></div>'
                            str+='<div class="da-item"><div>毕业去向：</div><p>'+p.byqx+'</p></div>'
                        }
                        if(p.yrdwmc){
                            str+='<div class="da-item"><div>用人单位名称：</div><p>'+p.yrdwmc+'</p></div>'
                        }
                        if(p.yrdwdm){
                            str+='<div class="da-item"><div>用人单位代码：</div><p>'+p.yrdwdm+'</p></div>'
                        }
                        if(p.yrdwxzmc){
                            str+='<div class="da-item"><div>用人单位性质：</div><p>'+p.yrdwxzmc+'</p></div>'
                        }
                        if(p.dwhylbmc){
                            str+='<div class="da-item"><div>用人单位行业类别：</div><p>'+p.dwhylbmc+'</p></div>'
                        }
                        if(p.dwszdmc){
                            str+='<div class="da-item"><div>单位所在地：</div><p>'+p.dwszdmc+'</p></div>'
                        }


                        if(p.dwlxr){
                            str+='<div class="da-item"><div>单位联系人：</div><p>'+p.dwlxr+'</p></div>'
                        }
                        if(p.dwdh){
                            str+='<div class="da-item"><div>单位电话：</div><p>'+p.dwdh+'</p></div>'
                        }
                        if(p.gzzwlbmc){
                            str+='<div class="da-item"><div>工作职位类别：</div><p>'+p.gzzwlbmc+'</p></div>'
                        }


                        if(p.bdzqflbmc){
                            // $("#bdzqflbmc").val(p.bdzqflbmc);
                            // str+='<div class="da-item"><div>报到证签发类别名称：</div><p>'+$("#bdzqflbmc option:selected").html()+'</p></div>'
                            str+='<div class="da-item"><div>报到证签发类别：</div><p>'+p.bdzqflbmc+'</p></div>'
                        }
                        if(p.bdzqwdwmc){
                            str+='<div class="da-item"><div>报到证签往单位：</div><p>'+p.bdzqwdwmc+'</p></div>'
                        }
                        if(p.bdzqwszdmc){
                            str+='<div class="da-item"><div>报到证签往所在地：</div><p>'+p.bdzqwszdmc+'</p></div>'
                        }
                        if(p.bdkssj){
                            str+='<div class="da-item"><div>报到开始时间：</div><p>'+p.bdkssj.substring(0,10)+'</p></div>'
                        }
                        if(p.bdjssj){
                            str+='<div class="da-item"><div>报到结束时间：</div><p>'+p.bdjssj.substring(0,10)+'</p></div>'
                        }
                        if(p.datddh){
                            str+='<div class="da-item"><div>档案投递电话：</div><p>'+p.datddh+'</p></div>'
                        }
                        if(p.datddw){
                            str+='<div class="da-item"><div>档案投递单位：</div><p>'+p.datddw+'</p></div>'
                        }
                        if(p.hkqydz){
                            str+='<div class="da-item"><div>户口迁移地址：</div><p>'+p.hkqydz+'</p></div>'
                        }
                        if(p.bdzbz){
                            str+='<div class="da-item"><div>报到证备注：</div><p>'+p.bdzbz+'</p></div>'
                        }
                        if(p.bzone){
                            str+='<div class="da-item"><div>备注一：</div><p>'+p.bzone+'</p></div>'
                        }
                        if(p.bztwo){
                            str+='<div class="da-item"><div>备注二：</div><p>'+p.bztwo+'</p></div>'
                        }
                        if(p.bzthree){
                            str+='<div class="da-item"><div>备注三：</div><p>'+p.bzthree+'</p></div>'
                        }

                        index = layer.open({
                            type: 1,
                            title: '查询结果',
                            skin: 'layui-layer-rim', //加上边框
                            area: ['750px', '450px'], //宽高
                            content: '<div class="lxr-modal1">\n' +str+
                            '                        <div class="row btn-yd">\n' +
                            '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
                            '                                <button class="btn green" style="width: 120px;margin-bottom:20px;" onclick="closeLayer()">确定</button>\n' +
                            '                            </div>\n' +
                            '                        </div></div>'
                        });

                    }
                } else {
                    walert(data.errorMess)
                }
            })
        // }else{
        //     login();
        // }
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