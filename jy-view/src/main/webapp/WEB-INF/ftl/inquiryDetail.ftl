<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
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
</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
    <#include "com/route.ftl">
        <div class="content">
            <div class="article-detail-title">
                <div class="h3">${result.wjmc!''}</div>
            </div>
            <#if result.wjjj??>
                <div class="article-detail-text">
                <p>${result.wjjj!''}</p>
                </div>
            </#if>
            <div>
                <dl>
                    <#if (result.questionList??)&&(result.questionList?size>0)>
                        <#list result.questionList?sort_by("tmsx") as obj>
                            <#if obj.tmlx?number==1>
                                <dt>${obj_index+1}.${obj.tmmc}</dt>
                                <dd>
                                    <ul>
                                    <#--<#if (typeList??)&&(typeList?size>0)>-->
                                    <#--<#list typeList as obj>-->
                                        <li><input type="text" class="form-control" /></li>
                                    <#--</#list>-->
                                    <#--</#if>-->
                                    </ul>
                                </dd>
                            </#if>
                            <#if obj.tmlx?number==2>
                            <dt>${obj_index+1}.${obj.tmmc}</dt>
                            <dd>
                                <ul>
                                    <#--<#if (typeList??)&&(typeList?size>0)>-->
                                        <#--<#list typeList as obj>-->
                                            <li><label class="radio"><input type="radio" name="gzzn-radio" code="a" /><span>名字</span></label></li>
                                        <li><label class="radio"><input type="radio" name="gzzn-radio" code="a" /><span>名字</span></label></li>
                                    <#--</#list>-->
                                    <#--</#if>-->
                                </ul>
                            </dd>
                        </#if>
                        <#if obj.tmlx?number==3>
                            <dt>${obj_index+1}.${obj.tmmc}</dt>
                            <dd>
                                <ul>
                                <#--<#if (typeList??)&&(typeList?size>0)>-->
                                <#--<#list typeList as obj>-->
                                    <li><label class="checkbox"><input type="checkbox" name="gzzn-radio" code="a" /><span>名字</span></label></li>
                                    <li><label class="checkbox"><input type="checkbox" name="gzzn-radio" code="a" /><span>名字</span></label></li>
                                <#--</#list>-->
                                <#--</#if>-->
                                </ul>
                            </dd>
                        </#if>

                        </#list>
                    </#if>

                </dl>

                <#--<div class="div_question">-->
                    <#--<div class="div_title_question_all">-->
                        <#--<div class="div_topic_question">2.</div>-->
                        <#--<div id="divTitle2" class="div_title_question">性别<span class="req">*</span></div>-->
                        <#--<div style="clear:both;"></div>-->
                    <#--</div>-->
                    <#--<div class="div_table_radio_question" id="divquestion2">-->
                        <#--<div class="div_table_clear_top"></div>-->
                        <#--<ul class="ulradiocheck">-->
                            <#--<li style="width: 99%;">-->
                                <#--<a href="javascript:" class="jqRadio jqChecked" rel="q2_1"></a>-->
                                <#--<input style="display:none;" type="radio" name="q2" id="q2_1" value="1">-->
                                <#--<label for="q2_1">A.男</label>-->
                            <#--</li>-->
                            <#--<li style="width: 99%;">-->
                                <#--<a href="javascript:" class="jqRadio" rel="q2_2"></a>-->
                                <#--<input style="display:none;" type="radio" name="q2" id="q2_2" value="2">-->
                                <#--<label for="q2_2">B.女</label>-->
                            <#--</li>-->
                            <#--<div style="clear:both;"></div>-->
                        <#--</ul>-->
                        <#--<div style="clear:both;"></div>-->
                        <#--<div class="div_table_clear_bottom"></div>-->
                    <#--</div>-->
                    <#--<div class="errorMessage"></div>-->
                <#--</div>-->
            </div>
        </div>

    </div>
</div>

<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>