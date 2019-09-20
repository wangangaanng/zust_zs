<div class="menu-nav">
    <div class="menu-title">
        <div class="title-chn">${secondDirName!''}</div>
        <div class="title-en">INFORMATION
            <div class="menu-nav-icon"></div>
        </div>

    </div>
    <div class="menu-list">
        <ul class="list-group">
        <#if (menuList??)&&(menuList?size>0)>
            <#list menuList as obj>
                <#if obj.NAME==thirdDirName>
                <li class="list-group-item active1" onclick='openUrl("${obj.TZLJ!''}")'>
                <#else >
                <li class="list-group-item" onclick='openUrl("${obj.TZLJ!''}")'>
                </#if>
                <span class="ic-menu"></span> ${obj.NAME!''}
            </li>
            </#list>
        </#if>
        </ul>
    </div>
</div>