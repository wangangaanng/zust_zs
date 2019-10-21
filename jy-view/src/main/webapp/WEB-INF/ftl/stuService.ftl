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

<select style="display: none;" id="mz">
        <#if mzList??>
            <#list mzList as obj>
            <option value="${obj.dicVal1}">${obj.dicVal2}</option>
            </#list>
        </#if>
</select>
<select style="display: none;" id="syd">
        <#if sydList??>
            <#list sydList as obj>
            <option value="${obj.dicVal1}">${obj.dicVal2}</option>
            </#list>
        </#if>
</select>
<select style="display: none;" id="bdzszdmc">
        <#if sydList??>
            <#list sydList as obj>
            <option value="${obj.dicVal1}">${obj.dicVal2}</option>
            </#list>
        </#if>
</select>
<select style="display: none;" id="byqx">
        <#if byqxList??>
            <#list byqxList as obj>
            <option value="${obj.dicVal1}">${obj.dicVal2}</option>
            </#list>
        </#if>
</select>
<select style="display: none;" id="bdzqflbmc">
        <#if bdzqflbList??>
            <#list bdzqflbList as obj>
            <option value="${obj.dicVal1}">${obj.dicVal2}</option>
            </#list>
        </#if>
</select>

<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>

<script>
    var index;
    $(document).ready(function () {
        $(".ohidden").parents().find('.layui-layer-content').addClass("oyhidden")
    })

    function inquiryArchives(){
        if(getCookie('stuOwid')) {
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
                        if(data.bean.xm){
                            str+='<div class="da-item"><div>学生姓名：</div><p>'+p.xm+'</p></div>'
                        }
                        if(data.bean.xsxh){
                            str+='<div class="da-item"><div>学生学号：</div><p>'+p.xsxh+'</p></div>'
                        }
                        if(data.bean.sfz){
                            str+='<div class="da-item"><div>身份证号码：</div><p>'+p.sfz+'</p></div>'
                        }
                        if(data.bean.xb){
                            if(data.bean.xb==1){
                                str+='<div class="da-item"><div>性别：</div><p>男</p></div>'
                            }else if(data.bean.xb==2){
                                str+='<div class="da-item"><div>性别：</div><p>女</p></div>'
                            }
                        }
                        if(data.bean.mz){
                            $("#mz").val(data.bean.mz);
                            str+='<div class="da-item"><div>民族：</div><p>'+$("#mz option:selected").html()+'</p></div>'
                        }
                        if(data.bean.xxmc){
                            str+='<div class="da-item"><div>学校名称：</div><p>'+p.xxmc+'</p></div>'
                        }
                        if(data.bean.xsxy){
                            str+='<div class="da-item"><div>学生学院：</div><p>'+p.xsxy+'</p></div>'
                        }
                        if(data.bean.xszy){
                            str+='<div class="da-item"><div>学生专业：</div><p>'+p.xszy+'</p></div>'
                        }
                        if(data.bean.xsbj){
                            str+='<div class="da-item"><div>学生班级：</div><p>'+p.xsbj+'</p></div>'
                        }
                        if(data.bean.bynf){
                            str+='<div class="da-item"><div>毕业年份：</div><p>'+p.bynf+'</p></div>'
                        }
                        if(data.bean.byxl){
                            str+='<div class="da-item"><div>毕业学历：</div><p>'+p.byxl+'</p></div>'
                        }
                        if(data.bean.xz){
                            str+='<div class="da-item"><div>学制：</div><p>'+p.xz+'</p></div>'
                        }
                        if(data.bean.syddm){
                            str+='<div class="da-item"><div>生源地代码：</div><p>'+p.syddm+'</p></div>'
                        }
                        if(data.bean.syd){
                            $("#syd").val(data.bean.syd);
                            str+='<div class="da-item"><div>生源地：</div><p>'+$("#syd option:selected").html()+'</p></div>'
                        }
                        if(data.bean.sjh){
                            str+='<div class="da-item"><div>手机号：</div><p>'+p.sjh+'</p></div>'
                        }
                        if(data.bean.yx){
                            str+='<div class="da-item"><div>邮箱：</div><p>'+p.yx+'</p></div>'
                        }
                        if(data.bean.dwszddm){
                            str+='<div class="da-item"><div>报到地区代码：</div><p>'+p.dwszddm+'</p></div>'
                        }
                        if(data.bean.byqx){
                            $("#byqx").val(data.bean.byqx);
                            str+='<div class="da-item"><div>毕业去向：</div><p>'+$("#byqx option:selected").html()+'</p></div>'
                        }
                        if(data.bean.bdzszdmc){
                            $("#bdzszdmc").val(data.bean.bdzszdmc);
                            str+='<div class="da-item"><div>报到证签往单位所在地：</div><p>'+$("#bdzszdmc option:selected").html()+'</p></div>'
                        }
                        if(data.bean.bdzqflbmc){
                            $("#bdzqflbmc").val(data.bean.bdzqflbmc);
                            str+='<div class="da-item"><div>报到证签发类别名称：</div><p>'+$("#bdzqflbmc option:selected").html()+'</p></div>'
                        }
                        if(data.bean.bdkssj){
                            str+='<div class="da-item"><div>报到开始时间：</div><p>'+p.bdkssj+'</p></div>'
                        }
                        if(data.bean.bdjssj){
                            str+='<div class="da-item"><div>报到结束时间：</div><p>'+p.bdjssj+'</p></div>'
                        }
                        if(data.bean.bdzbh){
                            str+='<div class="da-item"><div>报到证编号：</div><p>'+p.bdzbh+'</p></div>'
                        }
                        if(data.bean.datdxxdz){
                            str+='<div class="da-item"><div>档案投递详细地址：</div><p>'+p.datdxxdz+'</p></div>'
                        }
                        if(data.bean.datddw){
                            str+='<div class="da-item"><div>档案投递单位：</div><p>'+p.datddw+'</p></div>'
                        }
                        if(data.bean.hkqydz){
                            str+='<div class="da-item"><div>户口迁移地址：</div><p>'+p.hkqydz+'</p></div>'
                        }
                        if(data.bean.bdzlsh){
                            str+='<div class="da-item"><div>报到证流水号：</div><p>'+p.bdzlsh+'</p></div>'
                        }
                        if(data.bean.bdzbz){
                            str+='<div class="da-item"><div>报到证备注：</div><p>'+p.bdzbz+'</p></div>'
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
        }else{
            login();
        }
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