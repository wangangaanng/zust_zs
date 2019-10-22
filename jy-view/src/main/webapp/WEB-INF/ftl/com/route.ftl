<div class="routes">
    <div class="location">
        <i></i>
        当前位置：
    </div>

    <ol class="breadcrumb">
        <li><a href="${base}/">首页</a></li>
        <#if secondDirName??>
            <li><a href="#">${secondDirName!''}</a></li>
        <#else >
            <li><a>详情</a></li>
        </#if>
        <#if thirdDirName??>
            <li><a>${thirdDirName!''}</a></li>
        </#if>
    </ol>
</div>