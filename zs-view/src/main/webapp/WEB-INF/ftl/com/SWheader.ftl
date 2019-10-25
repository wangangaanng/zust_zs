<#--<#assign nav=-->
<#--[{'url':'','title':'首页'},-->
<#--{'url':'','title':'学院概况','sub':[{'url':'articleTpl/college/0','title':'学校简介'},{'url':'articleTpl/1','title':'学院专业'},{'url':'articleTpl/2','title':'中心介绍'}]},-->
<#--{'url':'','title':'新闻公告','sub':[{'url':'newsList','title':'通知公告'},{'url':'/newsList','title':'新闻快递'},{'url':'/newsList','title':'校内公示'}]},-->
<#--{'url':'','title':'招聘信息','sub':[{'url':'recruitment','title':'浙科院·职来职往'},{'url':'recruitment','title':'社会招聘会'},{'url':'recruitment','title':'企业招聘信息'},{'url':'recruitment','title':'职位招聘信息'},{'url':'announcement','title':'招考公告'}]},-->
<#--{'url':'','title':'职业指导','sub':[{'url':'newsList','title':'政策法规'},{'url':'newsList','title':'就业指导'},{'url':'newsList','title':'创业指导'},{'url':'newsList','title':'生涯规划'},{'url':'newsList','title':'技能培训'}]},-->
<#--{'url':'','title':'企业指南','sub':[{'url':'enterpriseGuide','title':'招聘指南'},{'url':'enterpriseGuide','title':'生源速览'}]},-->
<#--{'url':'','title':'学生服务','sub':[{'url':'studentService','title':'办事流程'},{'url':'studentService','title':'常用下载'},{'url':'studentService','title':'档案查询'}]},-->
<#--{'url':'contactUs','title':'联系我们'}]-->
<#-->-->
<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="${base}/img/logo-zust.png">
                <div class="title">就业信息网</div>
                <div class="user-info" id="qyInfo" style="display: none;">
                    <a href="/enterpriseService/0">欢迎您<span id="qyName"></span></a>,<a href="#" onclick="loginout()">退出</a>
                </div>
                <div class="user-info" id="stuInfo" style="display: none;">
                    <a href="/stuCenter/0">欢迎您<span id="stuName"></span></a>,<a href="#" onclick="loginout()">退出</a>
                </div>
                <div class="search-bar" style="float: right;width: 350px;margin-top: 50px;margin-right:12px;border-bottom: none;">
                    <div class="input-group">
                        <input type="text" class="form-control" onkeydown="keySearch()" id="gjz22" placeholder="请输入">
                        <span class="input-group-btn">
                            <button class="btn btn-default header-search" type="button" onclick="searchAll()">搜索</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
