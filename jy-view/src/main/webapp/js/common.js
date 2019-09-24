/**
 * Created by zhang on 2019/9/11.
 */
// var prx='../'//html路径
// document.write('<link rel="stylesheet" href="'+prx+'css/common.css" />');
// document.write('<script language=javascript src="'+prx+'js/mustache.js"></script>');
// $(document).ready(function () {
//     mustache_init();
// })
// var data={nav:[
//     {url:'index.html',title:'首页'},
//     {url:'',title:'学院概况',sub:[{url:'articleTpl.html',title:'学校简介'},{url:'',title:'学院专业'},{url:'',title:'中心介绍'}]},
//     {url:'',title:'新闻公告',sub:[{url:'newsList.html',title:'通知公告'},{url:'',title:'新闻快递'},{url:'',title:'校内公示'}]},
//     {url:'',title:'招聘信息',sub:[{url:'',title:'浙科院·职来职往'},{url:'',title:'社会招聘会'},{url:'',title:'企业招聘信息'},{url:'',title:'职位招聘信息'},{url:'',title:'招考公告'}]},
//     {url:'',title:'职业指导',sub:[{url:'',title:'政策法规'},{url:'',title:'就业指导'},{url:'',title:'创业指导'},{url:'',title:'生涯规划'},{url:'',title:'技能培训'}]},
//     {url:'',title:'企业指南',sub:[{url:'',title:'招聘指南'},{url:'',title:'生源速览'}]},
//     {url:'',title:'学生服务',sub:[{url:'',title:'办事流程'},{url:'',title:'常用下载'},{url:'',title:'档案查询'}]},
//     {url:'',title:'联系我们'},
// ]};
// var headerTpl='<header><div class="top"><div class="container"><div class="top-logo"><img class="logo" src="'+prx+'img/logo-zust.png"><div class="title">就业信息网</div> </div>'+
//     '<div class="nav"><ul>{{>navList}}</ul></div> </div> </div></header>'
// var headerPart={navList:"{{#nav}}<li onclick='openUrl(\"{{url}}\")'>{{title}}<ul class='subnav-list'>{{>subnavList}}</ul></li>{{/nav}}",subnavList:"{{#sub}}<li onclick='openUrl(\"{{url}}\")'><a><span>{{title}}</span></a></li>{{/sub}}"}
// function mustache_init() {
//     var header=Mustache.render(headerTpl, data,headerPart);
//     $("body").prepend(header);
// }
// function openUrl(url) {
//     if(url){
//         window.location.href=url
//         // window.open(url);
//     }
// }
var nulltip="<p style='text-align: center;'>暂无数据</p>"
$(document).ready(function () {
    if(getCookie("qyOwid")){
        $("#qyInfo").show();
        $("#qyName").html(JSON.parse(getCookie("qyInfo")).qymc);
    }
    if(getCookie("stuOwid")){
        $("#stuInfo").show();
        $("#stuName").html(getCookie("stuSjh").substring(0,3)+"****"+getCookie("stuSjh").substring(7,11));
    }
})
//登录
function login(url) {
    url=convertStr(url,'');
    var layer1;
    layer1=layer.open({
        type: 1,
        title:'登录信息',
        skin: 'layui-layer-rim', //加上边框
        area: ['420px', '240px'], //宽高
        content: '<div class="lxr-modal"><div class="row">\n' +
        '                            <div class="form-group">\n' +
        '                                <label for="lxr" class="col-sm-3 col-sm-offset-1 control-label text-right" style="line-height: 34px;">账号：</label>\n' +
        '                                <div class="col-sm-6">\n' +
        '                                    <input type="text" class="form-control" id="username" name="lxr" placeholder="" autocomplete="off">\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div>\n' +
        '                        <div class="row">\n' +
        '                            <div class="form-group">\n' +
        '                                <label for="lxdh" class="col-sm-3 col-sm-offset-1 control-label text-right" style="line-height: 34px;">密码：</label>\n' +
        '                                <div class="col-sm-6">\n' +
        '                                    <input type="password" class="form-control" id="psd" name="lxdh" placeholder="" autocomplete="off">\n' +
        '                                </div>\n' +
        '                            </div>\n' +
        '                        </div><div class="row btn-yd">\n' +
        '                            <div class="col-md-9 col-sm-offset-1 text-center">\n' +
        '                                <button class="btn green" onclick="confirmDl(\''+url+'\')">确定</button>\n' +
        '                            </div>\n' +
        '                        </div></div>'
    });
}
function confirmDl(url) {
    if(!$("#username").val().trim()){
        walert("请填写账号")
        return
    }else if(!$("#psd").val().trim()){
        walert("请填写密码")
        return
    }
    var jsonObj={
        "yhDlzh":$("#username").val().trim(),
        "yhDlmm":$("#psd").val().trim().MD5(),
    }
    ajax("zustcommon/bckjBizYhxx/logIn", jsonObj, function (data) {
        if(data.backCode==0){
            addCookie("stuOwid",data.bean.owid)
            addCookie("stuSjh",data.bean.sjh)
            if(url){
                window.location.href=base+url
            }else {
                location.reload();
            }

        }
    })

}
function loginout() {
    // delCookie("qyInfo");
    // delCookie("qyOwid");
    // delCookie("stuSjh");
    // delCookie("stuOwid");
    document.cookie  = "qyInfo=;path=/";
    document.cookie  = "qyOwid=;path=/";
    document.cookie  = "stuSjh=;path=/";
    document.cookie  = "stuOwid=;path=/";

    window.location.href='/'
    // location.reload();
}

// var localUrl = 'http://www.hwhautomall.com/ajax/executeAPI';
var localUrl = 'http://localhost:8080/webAjax/executeAPI';
var userKey = '';
function ajax(method, data, successMethod, pageNo, pageSize) {
        $.ajax({
            url: localUrl,
            data: {
                "method": method,
                "data": JSON.stringify(data),
                "pageNo": pageNo,
                "pageSize": pageSize,
                timestamp: new Date().getTime()
            },
            method: 'POST',
            success: successMethod,
            error: function (err) {
                // walert('系统出错');
            }
        })

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


//添加Cookie 时间以小时计
function addCookie(name, value, expires, path, domain) {
    var str = name + "=" + escape(value);
    if (expires !== "" && expires !== null && expires !== undefined) {
        var date = new Date();
        date.setTime(date.getTime() + expires * 3600 * 1000);// expires单位为小时
        str += ";expires=" + date.toGMTString();
    } else {
        var date = new Date();
        date.setTime(date.getTime() + 365 * 24 * 3600 * 1000);//若不输入时间，默认保存1年
        str += ";expires=" + date.toGMTString();
    }
    if (path !== "" && path !== null && path !== undefined) {
        str += ";path=" + path;// 指定可访问cookie的目录
    }else {
        str += ";path=/";// 指定可访问cookie的目录
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
