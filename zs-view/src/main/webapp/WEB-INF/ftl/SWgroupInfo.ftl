<!--
    作者：2515421994@qq.com
    时间：2019-10-26
    描述：分组信息
-->
<#assign groupList=[
    {"label":"考生姓名","value":"张小凡"}
    ,{"label":"学科类别","value":"普通类"}
    ,{"label":"外语语种","value":"英语"}
    ,{"label":"招生专业","value":"计算机科学与技术"}
    ,{"label":"准考证号","value":"20191028"}
    ,{"label":"抽签结果","value":"等待结果"}]
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



