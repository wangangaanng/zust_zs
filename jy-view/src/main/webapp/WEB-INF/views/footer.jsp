<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="footer">
    <div class="container">
        <div class="top1">
            <img src="image/logo22.png" class="hidden-xs" style="width: 62px;height: 62px;">
            <ul>
                <li onclick="gogo('D01')">公司简介</li>
                <li onclick="gogo('D05')">组织机构</li>
                <li onclick="gogo('D06')">联系我们</li>
            </ul>
            <div class="qrcode hidden-xs">
                <img src="image/qrcode.png">
                <p>扫码加关注</p>
            </div>
        </div>
        <div class="bottom">
            <p class="hidden-xs"><a onclick="window.open('http://www.ordos.gov.cn/')" style="color: #fff;cursor: pointer;">鄂尔多斯市政府</a> <a onclick="window.open('http://czj.ordos.gov.cn/')" style="color: #fff;cursor: pointer;">鄂尔多斯市财政局</a></p>
            <p class="hidden-xs bah1">鄂尔多斯市转型发展投资有限责任公司  蒙ICP备19002657号-1</p>
            <div class="bah visible-xs-block">鄂尔多斯市转型发展投资有限责任公司<br />蒙ICP备19002657号-1</div>
        </div>
    </div>
</div>
<script>
    function gogo(xh) {
        localStorage.removeItem("dicVal1");
        window.location.href='aboutUs.htm?dicType=100004&dicVal1='+xh;
    }
</script>