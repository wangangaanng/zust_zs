<!--
    作者：2515421994@qq.com
    时间：2019-10-28
    描述：三位一体头部
-->
<style>
    .search-bar{display: block;float: right;margin-right: 0px;width: 195px;height: 40px;padding-left: 15px;padding-right: 45px;background-color: #fff;border-radius: 20px;overflow: hidden;position: relative;padding: 0px 30px 0px 20px;margin-top: 58px;margin-bottom: 40px;}
    .search-bar input{height: 100%;outline: none;}
    .search-btn{ position: absolute;right: 11px;top:10px;}
    .search-ipt{width:100%;background-color:#fff;border: none;line-height: 40px;}
    header .top{height: auto}
    header .top-logo{margin-left: 0px;margin-top: 0px;}
    header .logo{margin: 40px 0px;}
    header .top-logo .user-info{position: relative; right: 0px; top: 0px;  color: #fff;  margin-top: 73px;  float: right;}
</style>
<header>
    <div class="top">
        <div class="container">
            <div class="top-logo">
                <img class="logo" src="${base}/img/logo-zust.png">
                <div class="title">
                    三位一体招生网
                </div>
                <#if nameStu??>
                    <div class="user-info" id="stuInfo">
                        欢迎您<span id="stuName">${nameStu!""}</span>, <a href="#" id="outBtn">退出</a>
                    </div>
                </#if>
                <#--<span class="search-bar">-->
                    <#--<input class="search-btn" style="width:21px;height:21px;" type="image" src="${base}/img/search.png" >-->
                    <#--<input name="showkeycode" class="search-ipt" id="searchAll" placeholder="请输入">-->
                <#--</span>-->
            </div>
        </div>
    </div>
</header>
<script>
    $("#outBtn").click(function () {
        addCookie("swOwid","")
        window.location.href = "${base}/trinitylogin";
    });
</script>