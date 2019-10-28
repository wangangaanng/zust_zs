<!--
    作者：2515421994@qq.com
    时间：2019-10-28
    描述：三位一体考生成绩查询
-->
<#assign groupList=[
    {"label":"考生姓名","value":"张小凡"}
    ,{"label":"学科类别","value":"普通类"}
    ,{"label":"外语语种","value":"英语"}
    ,{"label":"招生专业","value":"计算机科学与技术"}
    ,{"label":"准考证号","value":"20191028"}]
/>
<div class="group-wrap">
    <ul class="list-group row">
        <div class="grade-result row">
            <div class="col-sm-2 tc  col-sm-offset-4">
                <label class="search-score_label">成绩 </label>
                <label class="search-score">99</label>
            </div>
            <div class="col-sm-2 tc">
                <label class="search-score_label">排名 </label>
                <label class="search-score">1</label>
            </div>
        </div>
        <#list groupList as list>
            <li class="list-group-item ">
                <label class="group-label">${list.label}:</label>
                <label class="group-value">${list.value}</label>
            </li>
        </#list>
    </ul>
</div>



