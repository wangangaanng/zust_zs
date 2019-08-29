var flag=0;
var empPsw="";
var empId="";
// var openId="123456";
var lj="https://www.qzsjcloud.com/webApi/";
// var lj="http://192.168.3.62:8082/webApi/";

$(document).ready(function(){
	mui("body").on("tap",".confirm",function(){
		empId=$("#empId").val();
    	empPsw=$("#empPsw").val();
    	if(!emptyCheck(empId)){
    		walert("请输入登录账号");
    		return;
    	}
    	if(!emptyCheck(empPsw)){
    		walert("请输入登录密码");
    		return;
    	}
        bind1();

    });
    // mui("body").on("tap",".register",function(){
    //
    //     window.location.href="xzcyh.htm";
    // });
    mui("body").on("tap",".mui-icon-eye",function(){
    	if(flag%2==0){
    		$(".mui-icon-eye").addClass("mui-active");
			$("#empPsw").attr("type","text");
    	}else{
    		$(".mui-icon-eye").removeClass("mui-active");
			$("#empPsw").attr("type","password");
    	}
		
		flag++;
    });
    mui("body").on("tap",".qd",function(){
        $("#iosDialog2").hide();
    });
});

function bind() {
    beginLoad();
    url=lj+"wxCompanyUserApi/wxBindUser.do";
    var jsonObj = {
        "openId":openId,
		"empId":empId,
		"empPsw":empPsw
    };
    ajax("wxCompanyUserApi/wxBindUser",jsonObj).done(function(data){
        finishLoad();
        if (data.backCode == 0) {
            window.location.href="zccg.htm";
        }else{
            if(emptyCheck(data.errorMess)){
                walert(data.errorMess);
            }
        }
    }).fail(function(data){
        finishLoad();
        walert("调用失败");
    });

}

function bind1() {
    beginLoad();
    url=lj+"wxCompanyUserApi/wxBindUser.do";
    var jsonObj = {
        "openId":openId,
        "empId":empId,
        "empPsw":empPsw,
        "avatarUrl":avatarUrl,
        "nickName":nickName,
        "unionId":unionId
    };
    ajax("wxCompanyUserApi/wxBindUser",jsonObj).done(function(data){
        finishLoad();
        if (data.backCode == 0) {
            window.location.href="zccg.htm";
        }else{
            if(emptyCheck(data.errorMess)){
                walert(data.errorMess);
            }
        }
    }).fail(function(data){
        finishLoad();
        walert("调用失败");
    });

}

