<!--
    作者：2515421994@qq.com
    时间：2019-11-05
    描述：三位一体基本信息头部信息
-->
<div class="jf-steps">
    <div class="jf-items">
        <#if (applyOwid=="")&&(page!='0')>
            <div class="jf-item jf-active">1、基本信息
                <div class="jf-box"></div>
                <div class="jf-box1"></div>
                <div class="jf-box2"></div>
            </div>
            <div class="jf-item ">2、联系人
                <div class="jf-box"></div>
                <div class="jf-box1"></div>
                <div class="jf-box2"></div>
            </div>
            <div class="jf-item ">3、学考等第
                <div class="jf-box"></div>
                <div class="jf-box1"></div>
                <div class="jf-box2"></div>
            </div>
            <div class="jf-item ">4、选考信息</div>
            <#else>
            <#if (processState==0)>
                <div class="jf-item jf-active">1、基本信息
                    <div class="jf-box"></div>
                    <div class="jf-box1"></div>
                    <div class="jf-box2"></div>
                </div>
                <div class="jf-item ">2、联系人
                    <div class="jf-box"></div>
                    <div class="jf-box1"></div>
                    <div class="jf-box2"></div>
                </div>
                <div class="jf-item ">3、学考等第
                    <div class="jf-box"></div>
                    <div class="jf-box1"></div>
                    <div class="jf-box2"></div>
                </div>
                <div class="jf-item ">4、选考信息</div>
                <#else>
                    <div class="jf-item jf-active" style="cursor: pointer;" onclick="process(1)">1、基本信息
                        <div class="jf-box"></div>
                        <div class="jf-box1"></div>
                        <div class="jf-box2"></div>
                    </div>
                    <div class="jf-item jf-active" style="cursor: pointer;" onclick="process(2)">2、联系人
                        <div class="jf-box"></div>
                        <div class="jf-box1"></div>
                        <div class="jf-box2"></div>
                    </div>
                    <div class="jf-item jf-active" style="cursor: pointer;" onclick="process(3)">3、学考等第
                        <div class="jf-box"></div>
                        <div class="jf-box1"></div>
                        <div class="jf-box2"></div>
                    </div>
                    <div class="jf-item jf-active" style="cursor: pointer;" onclick="process(4)">4、选考信息</div>
            </#if>
        </#if>
    </div>
</div>