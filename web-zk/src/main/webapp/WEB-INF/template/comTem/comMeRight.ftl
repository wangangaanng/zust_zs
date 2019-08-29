<!--
 * @Author: vivian
 * @since: 2019-05-18 11:30:48
 * @description:我的中介机构，固定右侧返回上一个与我的服务单按钮
 -->
<div class="com-right_fix noprint" >
<#if !(myService??)>
    <a href="myService.htm?projectOwid=${projectId}" >
        <div class="right-fix ">
            <p>
                我的服务单
            </p>
        </div>
    </a>
</#if>
<#--页面没有定义first-->
<#if !(first??)>
    <a href="javascript:backFun()">
        <div class="right-fix" id="pre-page">
            <p>
                返回上一页
            </p>
        </div>
    </a>
</#if>
</div>

<script>
    function backFun(){
        if((${myService!"0"}==1) || (${meDetail!"0"}==1)){
            (${meDetail!"0"}==1)?hrefFun("prePageDetail"):hrefFun("prePage");
        }else{
            window.history.go(-1);
        }
    }
    
    function hrefFun(target) {
        window.location.href = localStorage.getItem(target)
    }
</script>
