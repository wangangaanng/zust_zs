var comTip = '<div class="null-tips"><img src="img/nulltip.png" /><h6>暂无信息</h6></div>';
var comload = {color: '#d8f3fe',text : "正在加载...",};
var nullTip = "<div class = 'null-tip'><img src = 'img/index_nulltip.png' style='margin-top: 60px'/><p style='margin-top: 10px'>查询结果暂为空</p></div>";
var axisLine = {lineStyle: {type: 'solid', color: '#666',width:'2'}};
var projectTopDeptPath = "";
var projectReginCode = "";

//简化ajax请求 url参数写在comUtil.ftl中
function ajaxPost(url,paradata,index,charttype,typeId,isExpertMark){//路径、参数、请求顺序、是否是图标、typeId、是否是专家评分expertMark请求路径不同
    var urlAll = "";
    if(!paradata){paradata = {}}
    var params = {
        "data":JSON.stringify(paradata),
        "currLanguage":"chn",
        timestamp : new Date().getTime(),
    };
    (isExpertMark=="expertMark")?(index==4?(urlAll=jsUploadImg):(urlAll = expertMarkApi,params.method="center/projecttarget/"+url)):(urlAll = urlApi+url);
    $.post(urlAll, params, function(data) {
        if(charttype){
            charttype.hideLoading();
        }
        handleData(data,index,typeId);
    });
}

//防止xss攻击
function htmlEscape(text) {
    return text.replace(/[<>"&;\\]|javascript|base64|onerror/g, function (match) {
        switch (match) {
            case '<':
                return '&lt;'
                break;
            case 'onerror':
                return ';'
                break;
            case '\\':
                return ''
                break;
            case '>':
                return '&gt;'
                break;
            case '\"':
                return '&quot;'
                break;
            case 'base64':
                return ''
                break;
            case 'javascript':
                return ''
                break;
            case ';':
                return ''
                break;
            case '&':
                return '&amp;'
                break;
        }
    })
}

//生成6位随机数
function num(){
    var Num="";
    for(var i=0;i<6;i++)
    {
        Num+=Math.floor(Math.random()*10);
    }
    return Num;
}
//判空
function emptyCheck(val) {
    if (val !== null && val !== undefined && val !== '') {
        return true;
    } else
        return false;
}

//获取时间yy-mm-ddd
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}

// 从url中获取参数值
function GetRequest() {
    var url = location.search; // 获取url中"?"符后的字串
    if (url.indexOf("?") != -1) { // 判断是否有参数
        var str = url.substr(1); // 从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
        strs = str.split("="); // 用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔
        return strs[1]; // 返回第一个参数 （如果有多个参数 还要进行循环的）
    }
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
/*年月日*/
function getDateYMD(date) {
    var day = date;
    var Year = 0;
    var Month = 0;
    var Day = 0;
    var CurrentDate = "";
    // 初始化时间
    Year = day.getFullYear();
    Month = day.getMonth() + 1;
    Day = day.getDate();

    CurrentDate = Year + "-";
    if (Month >= 10) {
        CurrentDate = CurrentDate + Month + "-";
    } else {
        CurrentDate = CurrentDate + "0" + Month + "-";
    }
    if (Day >= 10) {
        CurrentDate = CurrentDate + Day;
    } else {
        CurrentDate = CurrentDate + "0" + Day;
    }
    return CurrentDate;
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

//倒计时
var second = 60;
var timeOut = "";
function countdown(a) {
    if (second == 0) {
        $("#getCode").removeAttr("disabled");
        $("#getCode").html("重新发送");
        second = 60;
        clearTimeout(timeOut);
        return;
    }

    timeOut = setTimeout(function () {
        second -= 1;
        $("#getCode").attr("disabled", "true");
        $("#getCode").html(second + "s");
        $("#getCode").html(second + "s");
        countdown(a);
    }, 1000);
}


//显示查询
$("#top-project_list a").click(function () {
    $(".overlay-mask").addClass("active");
    topScroll = $(document).scrollTop();
    $("body").css({"position":"fixed","top":-topScroll});
    ajaxPost("dicTreeApi/listDeptTree.do","",3);//主管部门
    ajaxPost("dicTreeApi/listAddress.do","",4);//所在地区
    ajaxPost("sysDicApi/listDic/10010.do","",5);//建设模式
    ajaxPost("sysDicApi/listDic/10011.do",{"param":"px"},6);//项目级次
    ajaxPost("newProjectApi/policyListInPie.do","",7);//根据部门获取政策
    $(".sec-select").html("暂无");
    $(".sec-select").attr({"owid":"","path":""});
    $(".sec-select").next(".select-option").html("");
    $("#partlist18 a").html("请选择");
    $("#partlist18 a").attr({"owid":""});
    $(".select-input").val("");
    $("#part-value").html("");
    $("#area-value").html("");
});

//查询
$(".search-btn").click(function () {
    projectTitle = $(".select-input").val();//标题
    var policys = $($(".select-overlay ul.select-mode")[5]).children("li").children("a");
    if(!$(policys).attr("path")){
        projectPolicyPath = "";
    }else{
        $.each(policys,function (k,p) {
            if($(p).attr("path")){
                projectPolicyPath = $(p).attr("path");
            }
        });
    }
    projectFinancMode = $("#partlist15 a").attr("owid");//建设模式
    projectLevel = $("#partlist16 a").attr("owid");//项目级次
    projectIndustry = $("#partlist18 a").attr("owid");//项目工程类别
    $(".search-btn").val("查询中，请稍后...");
    ajaxPost("projectApi/listAllProject.do",{"projectTitle":projectTitle,"projectTopDeptPath":projectTopDeptPath,"projectReginCode":projectReginCode,"projectFinancMode":projectFinancMode,"projectLevel":projectLevel,"projectPolicyPath":projectPolicyPath,"projectIndustry":projectIndustry},8);
});

//收起树
function closeTree(a) {
    var tree = a;
    //获取 zTree 的全部节点数据将节点数据转换为简单 Array 格式
    var nodes = tree.transformToArray(tree.getNodes());
    for(var i=0;i<nodes.length;i++){
        //if(nodes[i].level == 0){
           // console.log(nodes[i].name);
            //根节点展开
          //  tree.expandNode(nodes[i],true,true,false)
       // }else{
            tree.expandNode(nodes[i],false,true,false)
       // }
    }
}

