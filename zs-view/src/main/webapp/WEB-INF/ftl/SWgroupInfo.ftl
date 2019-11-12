<!--
    作者：2515421994@qq.com
    时间：2019-10-26
    描述：分组信息page==4 和成绩查询page==6
-->
<#if page=="4">
    <#assign groupList=[
    {"label":"考生姓名","value":nameStu!""}
    ,{"label":"学科类别","value":subjectType!""}
    ,{"label":"外语语种","value":languageType!""}
    ,{"label":"报考类别","value":examType!""}
    ,{"label":"招生专业","value":major!""}
    ,{"label":"准考证号","value":examNum!"暂无"}
    ,{"label":"面试时间","value":faceTime!"暂无"}
    ]
    />
<#elseif page=="6">
    <#assign groupList=[
    {"label":"考生姓名","value":nameStu!""}
    ,{"label":"学科类别","value":subjectType!""}
    ,{"label":"外语语种","value":languageType!""}
    ,{"label":"报考类别","value":examType!""}
    ,{"label":"招生专业","value":major!""}
    ,{"label":"准考证号","value":examNum!"暂无"}
    ]
    />
</#if>

<div class="group-wrap">
    <ul class="list-group row">
     <#--成绩查询头部特有的展示成绩   start-->
    <#if page="6">
        <div class="grade-result row">
            <div class="col-sm-3 tc  col-sm-offset-2">
                <label class="search-score_label">笔试成绩</label>
                <label class="search-score">${writeScore!"暂无"}</label>
            </div>
            <div class="col-sm-3 tc">
                <label class="search-score_label">面试成绩 </label>
                <label class="search-score">${faceScore!"暂无"}</label>
            </div>
            <div class="col-sm-3 tc">
                <label class="search-score_label">最终成绩 </label>
                <label class="search-score">${faceScore!"暂无"}</label>
            </div>
        </div>
    </#if>
     <#--成绩查询头部特有的展示成绩   end-->

     <#--共用列表部分  start-->
    <#list groupList as list>
        <#if list_has_next>
            <li class="list-group-item ">
                <label class="group-label">${list.label}:</label>
                <label class="group-value">${list.value}</label>
            </li>
        <#else>
            <li class="list-group-item ">
                <label class="group-label">${list.label}:</label>
                <label class="group-value ${(page=="4")?string("wait","")}">${list.value}</label>
            </li>
        </#if>
    </#list>
     <#--共用列表部分  end-->
    </ul>
</div>



