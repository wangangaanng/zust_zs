var mark=1;
var newpsd = "";
var surepsd = "";
var code = "";//验证码
var mobile = "";
var cityId = "";
$(document).ready(function(){
    cityId = GetRequest();
    var name = "浙江科技学院网站后台管理系统";
    var area = "";
    if(cityId == 484){
        area = "满洲里";
    }
    if(cityId == 470){
        area = "呼伦贝尔市";
    }
    if(cityId == 482){
        area = "兴安盟";
    }
    if(cityId == 485){
        area = "二连浩特";
    }
    if(cityId == 475){
        area = "通辽市";
    }
    if(cityId == 476){
        area = "赤峰市";
    }
    if(cityId == 479){
        area = "锡林郭勒盟";
    }
    if(cityId == 474){
        area = "乌兰察布市";
    }
    if(cityId == 472){
        area = "包头市";
    }
    if(cityId == '015'){
        area = "内蒙古自治区";
    }
    if(cityId == 471){
        area = "呼和浩特市";
    }
    if(cityId == 478){
        area = "巴彦淖尔市";
    }
    if(cityId == 477){
        area = "鄂尔多斯市";
    }
    if(cityId == 473){
        area = "乌海市";
    }
    if(cityId == 483){
        area = "阿拉善盟";
    }
    if(!emptyCheck(cityId)){
        area = "浙江科技学院网站后台管理系统";
    }
    $(".login-header_content p").html(area+name);

	$(".login-btn").click(function(k,p){
		$("input.psdinput").each(function(k,p){
			var a = $(this).val();
			var b = $(this).attr("thisval");
			mark=1;
			if(!a){
				mark=0;
				$(this).parent("li").find("span").removeClass("hide").addClass("show").html(b);
				return;
			}else{
				$(this).parent("li").find("span").removeClass("show").addClass("hide").html("");
			}
			if(k==0){
				mobile = $(this).val();
			}
			if(k==1){
				code = $(this).val();
			}
			if(k==2){
		    	newpsd = $(this).val();
		   }
			if(k==3){
		    	surepsd = $(this).val();
		    	if(newpsd != surepsd){
		    		$(this).parent("li").find("span").removeClass("hide").addClass("show").html("两次密码输入不一样");
		    		mark=0;
		    	}
		    }
			
		});
		if(mark==1){
		    ajaxPost("companyIndexApi/forgetPsw.do",{"code":code,"mobile":mobile,"newPsw":newpsd,"rePsw":surepsd},0);
		}
	});
	
	var ordertime=60   //设置再次发送验证码等待时间
    var timeleft=ordertime
    var btn=$(".idenCode")
    var phone=$("#phoneNum")
    var reg = /^1[0-9]{10}$/;  //电话号码的正则匹配式

    phone.keyup(function(){
      if (reg.test(phone.val())){
        btn.removeAttr("disabled")  //当号码符合规则后发送验证码按钮可点击
        $(this).parent("li").find("span").removeClass("show").addClass("hide").html("");
        }
      else{
        btn.attr("disabled",true);
        $(this).parent("li").find("span").removeClass("hide").addClass("show").html("请输入正确的手机号码");
      }
    })
	$(".idenCode").click(function(){
		$(this).attr("disabled",true); //防止多次点击
		mobile = $("#phoneNum").val();
		ajaxPost("companyIndexApi/mobileCode.do",{"mobile":mobile},1);
        timeCount(this);
	});

    $(".success-tip .login-btn").click(function () {
        window.location.href= loginUrl+"/login.htm?cityId="+cityId;
    });
	
	//计时函数
	function timeCount(){
	   timeleft-=1
	   if (timeleft>0){
	   btn.val(timeleft+"S");
	   setTimeout(timeCount,1000)
	   }
	   else {
	    btn.val("重新发送");
	    timeleft=ordertime   //重置等待时间
	    btn.removeAttr("disabled");
	   }
	 }
});
function handleData(data,index,typeId) {
	if(index==0){
		if(data.backCode == 0){
			$(".success-tip").show();
            $(".forgetForm").hide();
		}else{
			$(".login-btn").parent("li").find("span").removeClass("hide").addClass("show").html(data.errorMess);
		}
	}
}

