<!--
    作者：2515421994@qq.com
    时间：2019-11-10
    描述：三位一体网站状态判断
-->
<div class="article-detail-text null-txt">
<#switch processState>
    <#case -1>
        <p>已退回，请尽快修改提交。</p>
        <#break>
    <#case 0>
        <p>还未提交报名表</p>
        <#break>
    <#case 1>
        <p>报名表已提交，下一步：“报名表承诺书打印”。</p>
        <#break>
    <#case 2>
        <p>报名表和承诺书邮箱已下载，下一步“拍照上传”。</p>
        <#break>
    <#case 3>
        <p>报名表签字和承诺书签字已经提交，请耐心等待审核。</p>
        <#break>
    <#case 4>
        <#if rePayMess??&&rePayMess!="">
                <p>很遗憾，报名表初审已拒绝！拒绝理由：${rePayMess!""}</p>
            <#else>
                <p>很遗憾，报名表初审已拒绝！</p>
        </#if>
        <#break>
    <#case 5>
        <#if rePayMess??&&rePayMess!="">
                <p>恭喜你，报名表初审通过，下一步：“初审结果/缴费”。缴费已退回，退回理由：${rePayMess!""}</p>
            <#else>
                <p>恭喜你，报名表初审通过，下一步：“初审结果/缴费”</p>
        </#if>
        <#break>
    <#case 6>
        <p>缴费待确认，请耐心等待确认</p>
        <#break>
    <#case 7>
        <p>缴费已确认，请耐心等待分配面试时间</p>
        <#break>
    <#case 8>
        <p>面试时间分配完成，下一步：面试通知单打印</p>
        <#break>
    <#case 9>
        <p>已经下载面试通知单，尚未产生成绩</p>
        <#break>
    <#case 10>
        <p>成绩已经可以查询</p>
        <#break>
</#switch>
</div>