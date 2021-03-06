/**
 * Created by win on 2015/6/25.
 * 全局注册js。比如需要把socket传递给js中
 */
var commonPath = "http://localhost:8080/webApi/";
var publicDataVO = new Object();
function initData(){
    publicDataVO.openId = "";
    publicDataVO.randNum = "";
    publicDataVO.privateKey = "";
    publicDataVO.appKey = "";
    publicDataVO.method = "";
    publicDataVO.wxid = "";
    publicDataVO.sessionId = "";
    publicDataVO.version = "";
    publicDataVO.timestamp = "";
    publicDataVO.info = "";
    publicDataVO.data = "";
    publicDataVO.pageNo = 0;
    publicDataVO.pageSize = 20;
    publicDataVO.empId = "";
    publicDataVO.empLanguage = "chn";
    publicDataVO.empName = "";
    publicDataVO.appSecrect = "";
    publicDataVO.zkCookie = "";
}
getLogIn();
function zkSetCookie(baseUrl,empId,empName,empLanguage,appKey,cookie){
    //addCookie("commonPath",baseUrl);
    addCookie("empId",empId);
    //addCookie("empName",empName);
    addCookie("empLanguage",empLanguage);
    addCookie("appKey",appKey);
    addCookie("zkCookie",cookie);
    getLogIn();
}

function getLogIn(){
    //commonPath = getCookie("commonPath");
    publicDataVO.appKey=getCookie("appKey");
    publicDataVO.appSecrect=getCookie("appSecrect");
    publicDataVO.empId=getCookie("empId");
    //publicDataVO.empName= getCookie("empName");
    publicDataVO.empLanguage=getCookie("empLanguage");
    publicDataVO.zkCookie=getCookie("zkCookie");
}

function ajax(method,data,successMethod){
    var baseUrl = "../ajax/executeAPI.do";
    getLogIn();
    var params = {
        "method":method,
        "data":JSON.stringify(data),
        "empId":publicDataVO.empId,
        "empLanguage":publicDataVO.empLanguage,
        "zkCookie":publicDataVO.zkCookie,
        timestamp: new Date().getTime(),
    }
    $.post(baseUrl,params,successMethod);
}
function base64_decode(input) { // 敏感信息解码，配合decodeURIComponent使用
    var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var output = "";
    var chr1, chr2, chr3;
    var enc1, enc2, enc3, enc4;
    var i = 0;
    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
    while (i < input.length) {
        enc1 = base64EncodeChars.indexOf(input.charAt(i++));
        enc2 = base64EncodeChars.indexOf(input.charAt(i++));
        enc3 = base64EncodeChars.indexOf(input.charAt(i++));
        enc4 = base64EncodeChars.indexOf(input.charAt(i++));
        chr1 = (enc1 << 2) | (enc2 >> 4);
        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
        chr3 = ((enc3 & 3) << 6) | enc4;
        output = output + String.fromCharCode(chr1);
        if (enc3 != 64) {
            output = output + String.fromCharCode(chr2);
        }
        if (enc4 != 64) {
            output = output + String.fromCharCode(chr3);
        }
    }
    return utf8_decode(output);
}

function utf8_decode(utftext) { // utf-8解码
    var string = '';
    var i = 0;
    var c = 0;
    var c1 = 0;
    var c2 = 0;
    while (i < utftext.length) {
        c = utftext.charCodeAt(i);
        if (c < 128) {
            string += String.fromCharCode(c);
            i++;
        } else if ((c > 191) && (c < 224)) {
            c1 = utftext.charCodeAt(i + 1);
            string += String.fromCharCode(((c & 31) << 6) | (c1 & 63));
            i += 2;
        } else {
            c1 = utftext.charCodeAt(i + 1);
            c2 = utftext.charCodeAt(i + 2);
            string += String.fromCharCode(((c & 15) << 12) | ((c1 & 63) << 6) | (c2 & 63));
            i += 3;
        }
    }
    return string;
}

//通过ajax方法调用api，获取多语言
function getRealLanguage(labelKey,backFunc){
    var method = "/sysLanguageApi/realLan.do";
    var params = {
        "labelKey":labelKey,
        timestamp : new Date().getTime()
    }
    ajax(method,params,backFunc);
}
//产生随机字符
function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}
//添加Cookie 时间以小时计
function addCookie(name, value, expires, path, domain) {
    var str = name + "=" + escape(value);
    if (expires !== "" && expires !== null && expires !== undefined ) {
        var date = new Date();
        date.setTime(date.getTime() + expires * 3600 * 1000);// expires单位为小时
        str += ";expires=" + date.toGMTString();
    } else {
        var date = new Date();
        date.setTime(date.getTime() + 365 * 24 * 3600 * 1000);//若不输入时间，默认保存1年
        str += ";expires=" + date.toGMTString();
    }
    if (path !== "" && path !== null && path !== undefined ) {
        str += ";path=" + path;// 指定可访问cookie的目录
    }
    if (domain !== "" && domain !== null && domain !== undefined ) {
        str += ";domain=" + domain;// 指定可访问cookie的域
    }
    document.cookie = str;
}
// 获取Cookie
function getCookie(cookie_name) {
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookie_name);

    // 如果找到了索引，就代表cookie存在，返回value值。
    // 反之，就说明不存在，返回null值。
    var value = null;
    if (cookie_pos != -1) {
        // 把cookie_pos放在值的开始，只要给值加1即可。
        cookie_pos += cookie_name.length + 1;
        var cookie_end = allcookies.indexOf(";", cookie_pos);
        if (cookie_end == -1) {
            cookie_end = allcookies.length;
        }
        value = unescape(allcookies.substring(cookie_pos, cookie_end));
    }
    return value;
}
// 删除Cookie
function delCookie(name) {// 为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
    var date = new Date();
    date.setTime(date.getTime() - 10000);
    document.cookie = name + "=a; expires=" + date.toGMTString();
}
//删除所有Cookie
function clearCookie() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if (keys) {
        for (var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString();
    }
}

function requestFullScreen(element) {
    // 判断各种浏览器，找到正确的方法
    var requestMethod = element.requestFullScreen || //W3C
        element.webkitRequestFullScreen ||    //Chrome等
        element.mozRequestFullScreen || //FireFox
        element.msRequestFullScreen; //IE11
    if (requestMethod) {
        requestMethod.call(element);
    }else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript !== null) {
            wscript.SendKeys("{F11}");
        }
    }
}

//退出全屏 判断浏览器种类
function exitFull() {
    // 判断各种浏览器，找到正确的方法
    var exitMethod = document.exitFullscreen || //W3C
        document.mozCancelFullScreen ||    //Chrome等
        document.webkitExitFullscreen || //FireFox
        document.webkitExitFullscreen; //IE11
    if (exitMethod) {
        exitMethod.call(document);
    }else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript !== null) {
            wscript.SendKeys("{F11}");
        }
    }
}

function openUrl(url){
     window.location.href = url;
    // window.parent.location.href = url;
    // window.opener.location.href = url;
}
//强制打开窗口
function windowOpen(url){
    var a = document.createElement("a");
    a.setAttribute("href", url);
    a.setAttribute("target", "_blank");
    document.body.appendChild(a);
    if(a.click){
        a.click();
    }else{
        try{
            var evt = document.createEvent('Event');
            a.initEvent('click', true, true);
            a.dispatchEvent(evt);
        }catch(e){
            window.open(url);
        }
    }
    document.body.removeChild(a);
}

