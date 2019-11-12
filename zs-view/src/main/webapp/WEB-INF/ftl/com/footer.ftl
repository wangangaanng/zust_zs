<style>
    .shouYe_div_foot ul{
        margin: 0;
        padding: 0;
    }
    .shouYe_div_foot
    {
        width: 1200px;
        height: 340px;
        font-family: "微软雅黑";
        margin-left: auto;
        margin-right: auto;
    }
    .first_link
    {
        width: 1000px;
        height: 60px;
        line-height: 60px;
        /*border-top: 1px solid #DCDCDC;*/
    }
    .linktxt
    {
        position: relative;
        top:-22px;
        width: 100px;
        height: 100%;
    }
    .linkspan
    {
        position: absolute;
        top: 20px;
        left: 0px;
        font-weight: 500;
        font-size: 20px;
        font-family: "微软雅黑";
    }
    .linkweb
    {
        width: 920px;
        height: 100%;
        margin-left: 11%;
        float: left;
        margin-top: -60px;
    }
    .linkweb>ul
    {
        width: 100% ;
        height: 100%;
    }
    .linkweb>ul>li
    {
        font-size: 14px;
        margin-left: 30px;
        float: left;
    }
    .linkweb>ul>li>a
    {
        color: #999999;
        font-family: "微软雅黑";
        font-weight: 500;
    }
    .second_link
    {
        width: 100%;
        height: 230px;
        background: #4D4D57;
        padding-top: 35px;
        padding-bottom: 35px;
    }
    .second_container
    {
        width: 65%;
        height: 100%;
        margin: 0 auto;
    }
    .webPagination
    {
        width: 452px;
        height: 100%;
        border-right: 1px solid #7D7D7D;
        position: relative;
        left: -26.5%;
    }
    .first_Pagination
    {
        margin-top: 0px;
        /*left: -46%;*/
    }
    .first_Paginationtxtdiv
    {
        margin-bottom: 20px;
        color: #DBDBDD;
        font-weight: 500;
        font-size: 14px;
    }
    .first_Paginationtxtcont
    {
        font-size: 14px;
    }
    .first_Paginationtxtcont>ul>li,.second_Pagination>ul>li,.third_Pagination>ul>li{
        margin-bottom: 8px;
        width: 60px;
    }
    .first_Paginationtxtcont>ul>li>a,.second_Pagination>ul>li>a,.third_Pagination>ul>li>a
    {
        font-size: 14px;
        color: #7D7D7D;
    }
    .second_Pagination
    {
        font-size: 14px;
        width: 70px;
        position:relative;
        left: 5%;
        margin-top: -111px;
        margin-left: 70px;
    }
    .third_Pagination
    {
        font-size: 14px;
        width: 70px;
        position:relative;
        left: 40.7%;
        margin-top: -112px;
    }
    .gzwxdiv
    {
        width: 100px;
        position: relative;
        left: -20%;
    }
    .gzwximg_wx
    {
        margin-top: -200px;
        position: relative;
        left: 490%;
    }
    .gzwxtxt
    {
        position: relative;
        left: 490%;
        top: -155px;
        color: white;
    }
    .gzwxtxtdiv
    {
        color: #DBDBDD;
        font-weight: 500;
        width: 100%;
        height: 20px;
        line-height: 20px;
        text-align: center;
        font-size: 14px;
    }
    .lxfsdiv
    {
        margin-left: 0px;
        border-left: 1px solid #7D7D7D;
        width: 450px;
        height: 158px;
        margin-top: -178px;
        position: relative;
        left: 70%;
    }
    .lxfstitle
    {
        height: 20px;
        margin-left: 90px;
        color: #DBDBDD;
        font-weight: 500;
        margin-bottom: 20px;
    }
    .meslist
    {
        margin-left: 90px;
        font-size: 12px;
    }
    .mesitem
    {
        margin-bottom: 10px;
        color:  #999999;;
    }
    .third_link
    {
        width: 100%;
        height: 70px;
        line-height: 60px;
        background: #1F1A17;
        font-size: 18px;
        font-family: "微软雅黑";
        font-weight: 500;
        color: white;
        margin-top: -10px;
    }
    .foot_back1
    {
        width: 100%;
        height: 230px;
        background: #4D4D57;
        margin-top: -280px;
    }
    .foot_back2
    {
        width: 100%;
        height: 70px;
        background: #1F1A17;
        margin-top: -10px;
    }
</style>
<div class="shouYe_div_foot">
    <div class="first_link">
        <div class="linktxt">
            <span class="linkspan">友情链接</span>
        </div>
        <div class="linkweb">
            <ul style="list-style-type: none;">
                <#if (footer??)&&(footer?size>0)>
                    <#list footer as obj>
                        <li><a href="${obj.tzlj!''}" target="_blank">${obj.bt!''}</a></li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>
    <div class="second_link">
        <div class="second_container">
            <div class="webPagination">
                <div class="first_Pagination">
                    <div class="first_Paginationtxtdiv">
                        <span class="first_Paginationtxt">网站导航</span>
                    </div>
                    <div class="first_Paginationtxtcont">
                        <ul style="list-style-type: none;">
                            <li><a href="${base}/wzOrTpOrSqLm/119/0">招生动态</a></li>
                            <li><a href="${base}/cjcx/1/0">查询服务</a></li>
                            <li><a href="${base}/wzOrTpOrSqLm/130/0">奖助学金</a></li>
                            <li><a href="${base}/wzOrTpOrSq/2/0">合作办学</a></li>
                        </ul>
                    </div>
                </div>
                <div class="second_Pagination">
                    <ul style="list-style-type: none;">
                        <li><a href="${base}/wzOrTpOrSqnd/66/0">招生计划</a></li>
                        <li><a href="${base}/wzOrTpOrSq/4/0">招生政策</a></li>
                        <li><a href="${base}/wzOrTpOrSq/5/0">在线咨询</a></li>
                        <li><a href="${base}/wzOrTpOrSqnd/131/0">招生简章</a></li>
                    </ul>
                </div>
                <div class="third_Pagination">
                    <ul style="list-style-type: none;">
                        <li><a href="${base}/wzOrTpOrSq/6/0">学院专业</a></li>
                        <li><a href="${base}/kyly/1/0">科院掠影</a></li>
                        <li><a href="${base}/wzOrTpOrSq/7/0">学在科院</a></li>
                    </ul>
                </div>
            </div>
            <div class="gzwxdiv">
                <div class="gzwxtxtdiv">
                    <span class="gzwxtxt">关注招生微信</span>
                    <img class="gzwximg_wx"  src="${base}/img/wx.png"/>
                </div>
            </div>
            <div class="lxfsdiv">
                <div class="lxfstitle">
                    <span>联系我们&nbsp;<img src="${base}/img/wb.png" /> </span>
                </div>
                <div class="meslist">
                    <p class="mesitem">招生办电话:<span style="color:#67B687 ;">0571-85070165</span></a>&nbsp;&nbsp;&nbsp;Email:<span style="color:#5B9DB7 ;">zsb165@zust.edu.cn</span></p>
                    <p class="mesitem">传&nbsp;&nbsp;&nbsp;真:85071165&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮 编:310023</p>
                    <p class="mesitem">QQ&nbsp;&nbsp;&nbsp;群:158252915&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地 址:杭州市小和山高教园区</p>
                </div>
            </div>
        </div>
    </div>
    <div class="third_link">
        <p class="third_linktxt">版权所有 © 2019浙江科技学院招生办公室 浙ICP备05014576号<p>
    </div>
</div>
<div class="foot_back1"></div>
<div class="foot_back2"></div>