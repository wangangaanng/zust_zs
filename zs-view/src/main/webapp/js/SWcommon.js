/**
 * Created by xiayu on 2019/11/04.
 * 三位一体comom
 */
var nulltip="<p style='text-align: center;'>暂无数据</p>";

//ajax请求
function ajax(method, data, successMethod, pageNo, pageSize) {
    beginLoad();
    if(!isTimeOut()) {
        $.ajax({
            url: localUrl,//'http://localhost:8011/webAjax/executeAPI/',//'https://job.zust.edu.cn//gate/'+method,
            data: {
                "method": method,
                "data": JSON.stringify(data),
                "pageNo": pageNo,
                "pageSize": pageSize,
                timestamp: new Date().getTime()
            },
            method: 'POST',
            success: function (res) {
                finishLoad();
                successMethod(res);
            },
            error: function (err) {
                finishLoad();
                walert('系统出错');
            }
        })
    }
}

function keySearch(){
    if (event.keyCode==13){
        searchAll()
    }
}

//判断登录过期
function isTimeOut() {
    addCookie("swOwid","359f287a40bb4fe98b0fd80766a91b4f");
    var url = window.location.href;;
    url = url.split("trinityEnrollment")[0];
    if(!getCookie("swOwid")){
        window.location.href=url+"/SWlogin";
    }else{
        return false;
    }
}

//调用附件上传接口
function fileUpload(type,file,fun) {
    var fd = new FormData();
    fd.append("yhRefOwid",getCookie("swOwid"));
    fd.append("file",file);
    fd.append('data', JSON.stringify({
        "type": type
    }));
    beginLoad();
    console.log(uploadUrl);
    $.ajax({
        url:  uploadUrl,
        type: "POST",
        processData: false,
        contentType: false,
        data: fd,
        success: function(d) {
            // console.log(d);
            finishLoad();
            if(d.backCode==0){
                //d.bean.filePath;
                console.log(d.bean.filePath);
                return fun(d);
            }else{
                walert(d.errorMess)
            }
        },
        fail:function () {
            finishLoad()
        }
    });
}

//身份证识别 以及单张图片上传
function idOcr(imgType,file,fun) {
    var thisType= imgType;
    (thisType=="3")?imgType=2:imgType;
    var fd = new FormData();
    fd.append("file",file);
    fd.append("method","zustcommon/common/picUpload")
    fd.append('data', JSON.stringify({
        "type": imgType
    }));
    beginLoad();
    $.ajax({
        url:  hostUrl+'/webAjax/picUpload',
        type: "POST",
        processData: false,
        contentType: false,
        data: fd,
        success: function(d) {
            finishLoad();
            fun(d);
        },
        fail:function () {
            finishLoad()
        }
    });
}


function keySearch(){
    if (event.keyCode==13){
        searchAll()
    }
}
// Close HTML Tags --------------------------------------------
function closeHTML(str){
    var arrTags=["span","font","b","u","i","h1","h2","h3","h4","h5","h6","p","li","ul","table","div"];
    for(var i=0;i<arrTags.length;i++){
        var intOpen=0;
        var intClose=0;
        var re=new RegExp("\\<"+arrTags[i]+"( [^\\<\\>]+|)\\>","ig");
        var arrMatch=str.match(re);
        if(arrMatch!=null) intOpen=arrMatch.length;
        re=new RegExp("\\<\\/"+arrTags[i]+"\\>","ig");
        arrMatch=str.match(re);
        if(arrMatch!=null) intClose=arrMatch.length;
        for(var j=0;j<intOpen-intClose;j++){
            str+="</"+arrTags[i]+">";
        }
        /*for(var j=(intOpen-intClose-1);j>=0;j--){
         str+="</"+arrTags[i]+">";
         }*/
    }
    return str;
}

function searchAll() {
    if($("#searchAll").val().trim()){
        var key=$("#searchAll").val().trim()
        if(testSql(key,$("#searchAll"))){
            window.location.href=base+'/search?key='+key
        }
    }else{
        layer.tips("请输入关键字！", $("#searchAll"), {tips: 1})
        return
    }
}

function testSql(val,obj) {
    re = /select|update|delete|exec|count|'|"|=|;|>|<|%/i;
    if (re.test(val)) {
        layer.tips("请您不要在参数中输入特殊字符和SQL关键字！", obj, {tips: 1})
        return false
    }else{
        return true
    }
}

function testSjh(sjh) {
    var length = sjh.length;
    var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
    return (length == 11 && mobile.test(sjh));
}

function testFloat(f) {
    return f%1>0;
}

//清除数字千分位
function clearComma(s) {
    if ($.trim(s) == "") {
        return s;
    } else {
        return (s + "").replace(/[,]/g, "");
    }
}

var loading; // 加载中

// 从url中获取参数值
function GetRequest() {
    var url = location.search; // 获取url中"?"符后的字串
    if (url.indexOf("?") != -1) { // 判断是否有参数
        var str = url.substr(1); // 从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
        strs = str.split("="); // 用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔
        return strs[1]; // 返回第一个参数 （如果有多个参数 还要进行循环的）
    }
}

// 错误提示，alert框

function walert(msg) {
    layer.msg(msg);
}

// 错误tip提示
function tip(msg, obj) {
    layer.tips(msg, obj, {
        tips: [1, '#2db4d3'],
        time: 4000
    });
}


// 取消null显示
function convertStr(val) {
    if ("" == val || null == val || 'null' == val || 'undefined' == val) {
        return "";
    } else
        return val;
}

// 取消null显示
function convertStr(val, val1) {
    if ("" == val || null == val || 'null' == val || 'undefined' == val) {
        return val1;
    } else
        return val;
}


// 开始加载
function beginLoad() {
    loading = layer.load(2, {shade: false});
}
// 停止加载

function finishLoad() {
    if (loading != null)
        layer.close(loading);
}

// 从url中分离所有的参数，返回数组
function getRequests() {
    var url = location.search;
    var params = new Array();
    var strs;
    var _strs;
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        if (str.indexOf("&") != -1) {
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                _strs = strs[i].split("=");
                params[i] = _strs[1];
            }

        } else {
            strs = str.split("=");
            params[0] = strs[1];
        }
    }
    return params;
}

// js产生uuid
function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the
    // clock_seq_hi_and_reserved
    // to 01
    s[8] = s[13] = s[18] = s[23] = "-";
    var uuid = s.join("");
    uuid = uuid.replace(/-/g, "");
    return uuid;
}

// 判断空
function emptyCheck(val) {
    if (val !== null && val !== undefined && val !== '') {
        return true;
    } else
        return false;
}


function convertDate(lng) {
    if (null == lng || lng == "" || lng == 0)
        return "";
    var dt = new Date(lng);
    var _sj = "";
    _sj = dt.getFullYear() + "-" + fixZero(dt.getMonth() + 1, 2) + "-" + fixZero(dt.getDate(), 2) + " " + fixZero(dt.getHours(), 2) + ":" + fixZero(dt.getMinutes(), 2) + ":" + fixZero(dt.getSeconds(), 2);
    return _sj;
}

function fixZero(num, length) {
    var str = "" + num;
    var len = str.length;
    var s = "";
    for (var i = length; i-- > len;) {
        s += "0";
    }
    return s + str;
}

// 以下是base64加密
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1,
    63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1,
    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
    20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31,
    32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
    50, 51, -1, -1, -1, -1, -1);

function base64encode(str) {
    var out, i, len;
    var c1, c2, c3;

    len = str.length;
    i = 0;
    out = "";
    while (i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if (i == len) {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if (i == len) {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += base64EncodeChars.charAt(c1 >> 2);
        out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
        out += base64EncodeChars.charAt(c3 & 0x3F);
    }
    return out;
}

function base64decode(str) {
    var c1, c2, c3, c4;
    var i, len, out;

    len = str.length;
    i = 0;
    out = "";
    while (i < len) {
        /* c1 */
        do {
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while (i < len && c1 == -1);
        if (c1 == -1)
            break;

        /* c2 */
        do {
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while (i < len && c2 == -1);
        if (c2 == -1)
            break;

        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

        /* c3 */
        do {
            c3 = str.charCodeAt(i++) & 0xff;
            if (c3 == 61)
                return out;
            c3 = base64DecodeChars[c3];
        } while (i < len && c3 == -1);
        if (c3 == -1)
            break;

        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

        /* c4 */
        do {
            c4 = str.charCodeAt(i++) & 0xff;
            if (c4 == 61)
                return out;
            c4 = base64DecodeChars[c4];
        } while (i < len && c4 == -1);
        if (c4 == -1)
            break;
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
    }
    return out;
}


function utf8to16(str) {
    var out, i, len, c;
    var char2, char3;

    out = "";
    len = str.length;
    i = 0;
    while (i < len) {
        c = str.charCodeAt(i++);
        switch (c >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                // 0xxxxxxx
                out += str.charAt(i - 1);
                break;
            case 12:
            case 13:
                // 110x xxxx 10xx xxxx
                char2 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
                break;
            case 14:
                // 1110 xxxx 10xx xxxx 10xx xxxx
                char2 = str.charCodeAt(i++);
                char3 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
                break;
        }
    }

    return out;
}

function CharToHex(str) {
    var out, i, len, c, h;

    out = "";
    len = str.length;
    i = 0;
    while (i < len) {
        c = str.charCodeAt(i++);
        h = c.toString(16);
        if (h.length < 2)
            h = "0" + h;

        out += "\\x" + h + " ";
        if (i > 0 && i % 8 == 0)
            out += "\r\n";
    }

    return out;
}

function doEncode() {
    var src = document.getElementById('src').value;
    document.getElementById('dest').value = base64encode(utf16to8(src));
}
function AntiSqlValid(oField ) {
    re = /select|update|delete|exec|count|'|"|=|;|>|<|%/i;
    if (re.test(oField.value)) {
        layer.tips("请您不要在参数中输入特殊字符和SQL关键字！", oField, {tips: 1})
        oField.value = "";
        $(oField).addClass("errInfo");
        oField.focus();
        return false;
    }
}
function AntiSqlValidAll(val,successMethod ) {
    re = /select|update|delete|exec|count|'|"|=|;|>|<|%/i;
    var allright=true;
    for(var i=0;i<val.length;i++){
        if (re.test($(val[i]).val())) {
            layer.tips("请您不要在参数中输入特殊字符和SQL关键字！", $(val[i]),{tips: 1})
            // alert("请您不要在参数中输入特殊字符和SQL关键字！"); //注意中文乱码
            $(val[i]).value = "";
            $(val[i]).addClass("errInfo");
            $(val[i]).focus();
            allright=false;
            break
        }
    }
    if(allright){
        return typeof successMethod == "function" && successMethod()
    }

}

// 添加Cookie 时间以小时计
function addCookie(name, value, expires, path, domain) {
    var str = name + "=" + escape(value);
    if (expires !== "" && expires !== null && expires !== undefined) {
        var date = new Date();
        date.setTime(date.getTime() + expires * 3600 * 1000);// expires单位为小时
        str += ";expires=" + date.toGMTString();
    } else {
        var date = new Date();
        date.setTime(date.getTime() + 1 * 1 * 3600 * 1000);//若不输入时间，默认保存1小时
        str += ";expires=" + date.toGMTString();
    }
    if (path !== "" && path !== null && path !== undefined) {
        str += ";path=" + path;// 指定可访问cookie的目录
    }else {
        if(base){
            str += ";path="+base;// 指定可访问cookie的目录
        }else{
            str += ";path=/";// 指定可访问cookie的目录
        }

    }
    if (domain !== "" && domain !== null && domain !== undefined) {
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


//模拟map
function HashMap() {
    //定义长度
    var length = 0;
    //创建一个对象
    var obj = new Object();
    /**
     * 判断Map是否为空
     */
    this.isEmpty = function () {
        return length == 0;
    };
    /**
     * 判断对象中是否包含给定Key
     */
    this.containsKey = function (key) {
        return (key in obj);
    };
    /**
     * 判断对象中是否包含给定的Value
     */
    this.containsValue = function (value) {
        for (var key in obj) {
            if (obj[key] == value) {
                return true;
            }
        }
        return false;
    };
    /**
     *向map中添加数据
     */
    this.put = function (key, value) {
        if (!this.containsKey(key)) {
            length++;
        }
        obj[key] = value;
    };
    /**
     * 根据给定的Key获得Value
     */
    this.get = function (key) {
        return this.containsKey(key) ? obj[key] : null;
    };
    /**
     * 根据给定的Key删除一个值
     */
    this.remove = function (key) {
        if (this.containsKey(key) && (delete obj[key])) {
            length--;
        }
    };
    /**
     * 获得Map中的所有Value
     */
    this.values = function () {
        var _values = new Array();
        for (var key in obj) {
            _values.push(obj[key]);
        }
        return _values;
    };
    /**
     * 获得Map中的所有Key
     */
    this.keySet = function () {
        var _keys = new Array();
        for (var key in obj) {
            _keys.push(key);
        }
        return _keys;
    };
    /**
     * 获得Map的长度
     */
    this.size = function () {
        return length;
    };
    /**
     * 清空Map
     */
    this.clear = function () {
        length = 0;
        obj = new Object();
    };
}


//把日期转换为星期
function convertWK(dt) {
    var weekDay = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"];
    var myDate = new Date(Date.parse(dt.replace(/-/g, "/")));
    return weekDay[myDate.getDay()];
}

//获取当前日期的下一天
function getNextDay(dt) {
    var myDate = new Date(Date.parse(dt.replace(/-/g, "/")));
    myDate.setDate(myDate.getDate() + 1);
    var nextdate = convertDate(myDate).substring(0, 10);
    return nextdate;
}
//统一调整到周日
function ToSun(dt) {
    //如果是周六显示的是下周日
    if (convertWK(dt) == "周六")
        return getNextDay(dt);
    //否则显示的是这周日
    else {
        var myDate = new Date(Date.parse(dt.replace(/-/g, "/")));
        myDate.setDate(myDate.getDate() - parseInt(myDate.getDay(), 10));
        var ToSundate = convertDate(myDate).substring(0, 10);
        return ToSundate;
    }
}


//获取url中的值，并处理中文乱码问题
function getRequest() {
    var url = window.location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            //就是这句的问题
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
            //之前用了unescape()
            //才会出现乱码
        }
    }
    return theRequest;
}
function getQueryString(name) {
    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
function formatTime(date) {
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    const hour = date.getHours()
    const minute = date.getMinutes()
    const second = date.getSeconds()

    return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}
function formatNumber(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}

function compareToday(d) {
    var d1=new Date(d).getTime()
    var td=new Date(new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+new Date().getDate()+' 00:00:00').getTime()

    if(d1<td) {
        return true
    }else{
        return false
    }
}

//用于表单 身份证格式
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4","3", "2");
    var varArray= new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber= num;
    //initialize
    if((intStrLen != 15) && (intStrLen !=18)) {
        return false;
    }
    // check andset value
    for (i = 0;i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i]> '9') && (i != 17)){
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if(intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (var i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else{       //length is 15
                //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}
function isDate6(sDate) {
    if(!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year,month, day;
    year =sDate.substring(0, 4);
    month =sDate.substring(4, 6);
    if (year< 1700 || year > 2500) {
        return false
    }
    if (month< 1 || month > 12){
        return false
    }
    return true
}

function isDate8(sDate) {
    if(!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year,month, day;
    year =sDate.substring(0, 4);
    month =sDate.substring(4, 6);
    day =sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,31]
    if (year< 1700 || year > 2500){
        return false
    }
    if (((year %4 == 0) && (year % 100 != 0)) ||(year % 400 == 0)){
        iaMonthDays[1] = 29;
    }
    if (month< 1 || month > 12){
        return false
    }
    if (day< 1 || day > iaMonthDays[month - 1]){
        return false
    }
    return true
}