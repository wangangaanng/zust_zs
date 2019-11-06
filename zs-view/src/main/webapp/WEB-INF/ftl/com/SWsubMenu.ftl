<!--
    作者：2515421994@qq.com
    时间：2019-10-28
    描述：三位一体右边菜单
-->
<div class="menu-nav">
    <div class="menu-title" style="min-height: 70px;">
        <div class="title-chn">三位一体</div>
        <div class="title-en">PROCESS
            <div class="menu-nav-icon"></div>
        </div>

    </div>
    <div class="menu-list">
        <ul class="list-group">
            <#list menuList as data>
                <li class="list-group-item ${((data_index?number)==(page?number))?string('active1','')}">${data}<span class="ic-menu"></span></li>
            </#list>
        </ul>
    </div>
</div>

<script>
    $(".list-group li").click(function () {
        var index = $(this).index();
        window.location.href="${base}/trinityEnrollment/"+index;
        $(this).addClass("active1").siblings().removeClass("active1");
    });
</script>