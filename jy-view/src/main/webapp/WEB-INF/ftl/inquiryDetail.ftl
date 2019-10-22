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
    .article-detail-text{
        font-size: 16px;
        color: #666;
        text-indent: 25px;
        line-height: 24px;
        margin: 16px 20px;
        border-bottom: 1px dashed #ededed;
    }
    dl{margin: 0px 10px 0px 10px;}
    dl dt{color: #212121;padding: 0px 15px;font-weight: bold;font-size: 15px;}
    dl dd ul{list-style: none;padding: 10px;margin-bottom: 0px;padding-left: 25px;}
    dl dd ul li label{font-weight: normal;font-size: 15px;margin: 15px 0px !important;}
    dl dd ul li input{margin: 15px 0px;}
    dl dd ul li input[type='radio']{margin-right: 10px;}
    .radio input[type="radio"]+span:before{
        width: 20px;height: 20px;
    }
    .checkbox input[type="checkbox"]+span:before{
        width: 18px;height: 18px;
    }
    dl dt a{
        font-weight: normal;
        text-decoration: underline;
        margin-left: 10px;
    }
</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
    <#include "com/route.ftl">
        <div class="content">
            <#if result??>
                <div class="article-detail-title">
                    <div class="h3">${result.wjmc!''}</div>
                </div>
                <#if result.wjjj??>
                    <div class="article-detail-text">
                        <p>${result.wjjj!''}</p>
                    </div>
                </#if>
                <div>
                    <dl class="wjcontent">
                        <#if (result.questionList??)&&(result.questionList?size>0)>
                            <#list result.questionList?sort_by("tmsx") as obj>
                                <#if obj.tmlx?number==1>
                                    <dt data-owid="${obj.owid!''}" data-lx="${obj.tmlx}">${obj_index+1}.${obj.tmmc}<#if obj.tmsm??><a href="${obj.tmsm!''}" target="_blank">题目说明</a></#if></dt>
                                    <dd>
                                        <ul>
                                            <li><input type="text" name="input${obj_index}" class="form-control" /></li>
                                        </ul>
                                    </dd>
                                </#if>
                                <#if obj.tmlx?number==2>
                                    <dt data-owid="${obj.owid!''}" data-lx="${obj.tmlx}">${obj_index+1}.${obj.tmmc}<#if obj.tmsm??><a href="${obj.tmsm!''}" target="_blank">题目说明</a></#if></dt>
                                    <dd>
                                        <ul>
                                            <#if (obj.xxList??)&&(obj.xxList?size>0)>
                                                <#list obj.xxList as xx>
                                                    <li><label class="radio"><input type="radio" name="radio${obj_index}" value="${xx.bh!''}" /><span>${xx.ms!''}</span></label></li>
                                                </#list>
                                            </#if>
                                        </ul>
                                    </dd>
                                </#if>
                                <#if obj.tmlx?number==3>
                                    <dt data-owid="${obj.owid!''}" data-lx="${obj.tmlx}">${obj_index+1}.${obj.tmmc}<#if obj.tmsm??><a href="${obj.tmsm!''}" target="_blank">题目说明</a></#if></dt>
                                    <dd>
                                        <ul>
                                            <#if (obj.xxList??)&&(obj.xxList?size>0)>
                                                <#list obj.xxList as xx>
                                                    <li><label class="checkbox"><input type="checkbox" name="checkbox${obj_index}" value="${xx.bh!''}" /><span>${xx.ms!''}</span></label></li>
                                                </#list>
                                            </#if>
                                        </ul>
                                    </dd>
                                </#if>

                            </#list>
                        </#if>

                    </dl>

                </div>
                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <#if result.tips??>
                            <button type="submit" class="btn btn-default btn-common" style="background-color: #e6e6e6;color: #333;">不可作答</button>
                        <#else >
                            <button type="submit" class="btn btn-default btn-common green" onclick="commitWj()">提交</button>
                        </#if>

                    </div>
                </div>
            </#if>
        </div>

    </div>
</div>

<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script>
    var answerList=[];
    var tips="${result.tips!''}"
    var ksdt="";
    var jsdt="";
    $(document).ready(function () {
        ksdt=new Date();
        if(tips){
            layer.open({
                title:'提示',
                content: tips,
                yes: function(index, layero){
                    layer.close(index);
                }
            });
        }

        $(".wjcontent dt").each(function (k, p) {
            var obj={};
            obj.dcwjtmRefOwid=$(this).data("owid");
            obj.wjda="";
            obj.tmlx=$(this).data("lx");
            answerList.push(obj);
        })
    })
    function commitWj() {
        $.each(answerList,function (k, p) {
            if(p.tmlx=="1"){//输入
                answerList[k].wjda=$('input[name="input'+k+'"]').val();
            }else if(p.tmlx=="2"){//单选
                if($('input[name="radio'+k+'"]:checked').val()){
                    answerList[k].wjda=$('input[name="radio'+k+'"]:checked').val();
                }
            }else if(p.tmlx=="3"){//多选
                //获取复选框值
                var chk_value =[];//定义一个数组
                $('input[name="checkbox'+k+'"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
                    chk_value.push($(this).val());//将选中的值添加到数组chk_value中
                });
                if(chk_value.length>0){
                    answerList[k].wjda=chk_value.join(",");
                }
            }
            if(answerList[k].wjda==""){
                $(".wjcontent").find("dt").eq(k).addClass('warn');
                $(".wjcontent").find("dd").eq(k).addClass('warn');
            }else {
                $(".wjcontent").find("dt").eq(k).removeClass('warn');
                $(".wjcontent").find("dd").eq(k).removeClass('warn');
            }
        })
        if($(".wjcontent").find("dt").hasClass("warn")){
            layer.msg('请填写完整', {icon: 2});
            return false;
        }
        jsdt=new Date();
        if(getCookie("stuOwid")){
            var jsonObj ={
                "dcwjRefOwid":"${owid!''}",
                "answerList":answerList,
                "ksdt":formatTime(ksdt),
                "jsdt":formatTime(jsdt),
                "dtrId":convertStr(getCookie("stuOwid"),""),
            }
        }else {
            var jsonObj ={
                "dcwjRefOwid":"${owid!''}",
                "answerList":answerList,
                "ksdt":formatTime(ksdt),
                "jsdt":formatTime(jsdt),
            }
        }
        ajax("zustcommon/bckjBizDcwj/submit", jsonObj, function (data) {
            if(data.backCode==0){
                layer.msg('提交成功', {icon: 1});
            }else if(data.backCode==2){
                layer.msg("请先登录", {icon: 2});
                setTimeout('window.location.href=base+"/"',1500);
            }else{
                layer.msg(data.errorMess, {icon: 2});
            }
        })
    }
</script>
</body>

</html>