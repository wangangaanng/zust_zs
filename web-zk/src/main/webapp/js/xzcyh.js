var userMobile="";
var userName="";
var userCard="";
var userValidCode="";
var userPsd="";

$(document).ready(function(){
    mui("body").on("tap",".qyxx",function(){
    	userMobile=$("#userMobile").val();
    	userValidCode=$("#userValidCode").val();
    	userName=$("#userName").val();
    	userCard=$("#userCard").val();
    	userPsd=$("#userPsd").val();
    	if(!emptyCheck(userMobile)){
    		walert("请输入手机号");
    		return;
    	}
    	if(!emptyCheck(userValidCode)){
    		walert("请输入动态码");
    		return;
    	}
    	if(!emptyCheck(userName)){
    		walert("请输入姓名");
    		return;
    	}
    	if(!emptyCheck(userCard)){
    		walert("请输入身份证号码");
    		return;
    	}
    	if(!emptyCheck(userPsd)){
    		walert("请输入登录密码");
    		return;
    	}
        window.location.href="qyxx.htm?userMobile="+userMobile+"&userValidCode="+userValidCode+"&userCard="+userCard+"&userPsd="+userPsd+"&userName="+userName;
    });
});

function bind() {
    beginLoad();
    var jsonObj = {
         
    };
    ajax("userAjax!bind",jsonObj).done(function(data){
        finishLoad();
        if (data.backCode == 1) {
             
        }else{
             
        }
    }).fail(function(data){
        finishLoad();
        walert("调用失败");
    });
}

function sendMessageCode() {
	timedCount();
//  beginLoad();
//  var jsonObj = {
//      "openId":openId,
//      "mobile":mobile
//  };
//  ajax("/message/sendMessageCode",jsonObj).done(function(data){
//      finishLoad();
//      var str="";
//      if (data.backCode == 0) {
//          vaue=data.bean;
//          timedCount();
//      }else{
//          walert(data.errorMess);
//      }
//  }).fail(function(data){
//      finishLoad();
//      walert("调用失败");
//  });
}
var loop = 60; // 等待120秒
var t;
function timedCount(){
    loop = loop - 1;
    var str = loop+"秒";
    var rzBtn = $(".yanzhengmabutton");
    rzBtn.removeAttr("onclick");
    rzBtn.text("剩余" + str);
    t = setTimeout("timedCount()", 1000)
    if (loop <= 0) {
        loop = 60;
        clearTimeout(t);
        rzBtn.text("获取动态码");
        rzBtn.attr("onclick", "sendMessageCode()");
    }
}
