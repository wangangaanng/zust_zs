var compTaxno="";
var compName="";
var compAddr="";
var userMobile="";
var userName="";
var userCard="";
var userValidCode="";
var userPsd="";
var compTel1="";
var compLegalPerson="";
var compLegalPersonTel="";
// var openId="123456";
var lj="http://www.qzsjcloud.com/webApi/";
// var lj="http://127.0.0.1:8082/webApi/";

$(document).ready(function(){
	userMobile=getRequests()[0];
	userName=getRequests()[4];
	userCard=getRequests()[2];
	userValidCode=getRequests()[1];
	userPsd=getRequests()[3];
    mui("body").on("tap",".confirm",function(){
    	compTaxno=$("#compTaxno").val();
    	compName=$("#compName").val();
    	compAddr=$("#compAddr").val();
        compTel1=$("#compTel1").val();
        compLegalPerson=$("#compLegalPerson").val();
        compLegalPersonTel=$("#compLegalPersonTel").val();
    	if(!emptyCheck(compTaxno)){
    		walert("请输入机构代码");
    		return;
    	}
    	if(!emptyCheck(compName)){
    		walert("请输入企业名称");
    		return;
    	}
    	if(!emptyCheck(compAddr)){
    		walert("请输入企业地址");
    		return;
    	}
        if(!emptyCheck(compTel1)){
            walert("请输入企业联系电话");
            return;
        }
        if(!emptyCheck(compLegalPerson)){
            walert("请输入企业法人");
            return;
        }
        if(!emptyCheck(compLegalPersonTel)){
            walert("请输入企业法人联系电话");
            return;
        }
    	register();
    });
    mui("body").on("tap",".qd",function(){
        $("#iosDialog2").hide();
    });
});

function register() {
    beginLoad();
    url=lj+"wxCompanyUserApi/register.do";
    var jsonObj = {
        "openId":openId,
        "userMobile":userMobile,
        "userName":userMobile,
        "userPsd":userPsd,
        "userCard":userCard,
        "userValidCode":userValidCode,
        "compTaxno":compTaxno,
        "compName":compName,
        "compAddr":compAddr,
        "compTaxImg":"",
        "compTel1":compTel1,
        "compLegalPerson":compLegalPerson,
        "compLegalPersonTel":compLegalPersonTel,
    };
    ajax("wxCompanyUserApi/register",jsonObj).done(function(data){
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

