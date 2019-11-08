<!--
    作者：2515421994@qq.com
    时间：2019-10-26
    描述：分组信息
-->
<#assign groupList=[
    {"label":"考生姓名","value":nameStu!""}
    ,{"label":"学科类别","value":subjectType!""}
    ,{"label":"外语语种","value":languageType!""}
    ,{"label":"报考类别","value":examType!""}
    ,{"label":"招生专业","value":major!""}
    ,{"label":"准考证号","value":examNum!"暂无"}
    ,{"label":"面试时间","value":faceTime!"暂无"}]
/>
<div class="group-wrap">
    <ul class="list-group row">
        <#list groupList as list>
            <#if list_has_next>
                    <li class="list-group-item ">
                        <label class="group-label">${list.label}:</label>
                        <label class="group-value">${list.value}</label>
                    </li>
                <#else>
                    <li class="list-group-item ">
                        <label class="group-label">${list.label}:</label>
                        <label class="group-value wait">${list.value}</label>
                    </li>
            </#if>
        </#list>
    </ul>
</div>



