var comTip = '<div class="null-tips"><img src="img/nulltip.png" /><h6>暂无信息</h6></div>';
var comload = {color: '#d8f3fe',text : "正在加载...",};
//简化ajax请求
function ajaxPost(url,paradata,index,charttype){
	if(!paradata){
		paradata = {}
	}
	var url = "https://www.qzsjcloud.com/webApi/"+url;
	// var url = "http://localhost:8081/webApi/"+url;
    var params = {
        "data":JSON.stringify(paradata),
        "currLanguage":"chn",
        timestamp : new Date().getTime(), 
    };
    $.post(url, params, function(data) {
    	 if(charttype){
            	charttype.hideLoading();
            }
     	 handleData(data,index);
    });
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

