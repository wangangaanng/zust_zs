<div class="routes">
    <div class="location">
        <i></i>
        当前位置：
    </div>

    <ol class="breadcrumb">
        <li><a href="/">首页</a></li>
        <#if secondDirName??>
            <li><a href="#">${secondDirName!''}</a></li>
        <#else >
            <li><a href="#">详情</a></li>
        </#if>
        <#if thirdDirName??>
            <li><a href="#">${thirdDirName!''}</a></li>
        </#if>
    </ol>
</div>