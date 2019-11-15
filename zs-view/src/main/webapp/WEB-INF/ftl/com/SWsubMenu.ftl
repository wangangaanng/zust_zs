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
            <li class="list-group-item ${((0)==(page?number))?string('active1','')} ${(processState>0)?string('active2','')}">
                我的报名表<span class="ic-menu"></span>
            </li>
            <li class="list-group-item ${((1)==(page?number))?string('active1','')} ${(processState>1)?string('active2','')}">
                报名表承诺书打印<span class="ic-menu"></span>
            </li>
            <li class="list-group-item ${((2)==(page?number))?string('active1','')} ${(processState>2)?string('active2','')}">
                拍照上传<span class="ic-menu"></span>
            </li>
            <li class="list-group-item ${((3)==(page?number))?string('active1','')} ${(processState>6)?string('active2','')} ${(processState==4)?string('active3','')}">
                初审结果/缴费<span class="ic-menu"></span>
            </li>
            <li class="list-group-item ${((4)==(page?number))?string('active1','')} ${(processState>7)?string('active2','')} ">
                面试时间<span class="ic-menu"></span>
            </li>
            <li class="list-group-item ${((5)==(page?number))?string('active1','')} ${(processState>8)?string('active2','')} ">
                面试通知单打印<span class="ic-menu"></span>
            </li>
            <li class="list-group-item ${((6)==(page?number))?string('active1','')} ${(processState>9)?string('active2','')} ">
                成绩查询<span class="ic-menu"></span>
            </li>
            <li class="list-group-item ${((7)==(page?number))?string('active1','')} ">
                在线提问<span class="ic-menu"></span>
            </li>
        </ul>
    </div>
</div>

<script>
    $(".list-group li").click(function () {
        var index = $(this).index();
        if(index==7){
            window.location.href="${base}/zxtw/5/1";
        }else{
            window.location.href="${base}/trinityEnrollment/"+index;
            $(this).addClass("active1").siblings().removeClass("active1");
        }
    });
</script>