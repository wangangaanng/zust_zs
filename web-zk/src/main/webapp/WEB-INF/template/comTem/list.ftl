
    <#list titleList as item>
        <li owid="${item.owid}">
            <a title="${item.newsTitle!""}">${item.newsTitle!""}
                <#if item.createtime??>
                        <span>${(item.createtime?replace("-","."))?substring(0,10)}</span>
                    <#else>
                        <span>暂无时间</span>
                </#if>
            </a>
        </li>
    </#list>


