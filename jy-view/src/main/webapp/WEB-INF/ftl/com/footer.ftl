<div class="bottom">
    <div class="websites-nav">
        <div class="container">
            <#if (footer??)&&(footer?size>0)>
                <#list footer as obj>
                    <div class="website">
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                ${obj.NAME!''}
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <#if (obj.chirdMenu??)&&(obj.chirdMenu?size>0)>
                                    <#list obj.chirdMenu as ob>
                                        <li><a href="${ob.TZLJ!''}" target="_blank">${ob.NAME!''}</a></li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <div class="contact">
        <div class="container text-center">
            <div class="contact-info text-center">
                <div>浙江科技学院招生就业处</div>
                <div>电话：0571-85124573、85121710 &nbsp;&nbsp;&nbsp;传真：0571-85124573 &nbsp;&nbsp;&nbsp;电子邮件：zustjob@zust.edu.cn</div>
                <div>Copyright © job.zust.edu.cn All Right reserved 沪ICP备10005749号-3</div>
                <div>技术支持：杭州步长科技有限公司</div>
            </div>
            <div class="ewm">
                <div class="ewmxq">
                    <div class="ewm-border">
                        <img src="${base}/img/ewm-jiuye.png">
                    </div>
                    <div class="ewmsm">
                        科院微就业
                    </div>
                </div>
                <div class="ewmxq">
                    <div class="ewm-border">
                        <img src="${base}/img/ewm-qq.png">
                    </div>
                    <div class="ewmsm">
                        企业QQ群
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>