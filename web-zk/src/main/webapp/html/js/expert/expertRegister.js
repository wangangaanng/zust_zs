/**
 * Created by Xia Yu on 2019/4/16.
 */

$(function () {
});

var formInput = $('.center-form input');
$(".exregister-btn").click(function () {
    phoneNum = $("#phoneNum").val();
    phoneCode = $("#phoneCode").val();
    idNum = $("#idNum").val();
    exName = $("#exName").val();
    var mark = "";
    var allFull = 0;
    $.each(formInput,function(k,p){
        mark = $("input:focus").length;
        if(!($(p).val())){
            console.log(k+"222");
            allFull = 1;
            if(mark==0){
                $(p).focus();
            }
            nullInput(p,1);
            layer.msg("请确认信息填写完整");
            return;
        }else if(k==1&&$(p).val().length !=  11){
            layer.msg("手机号长度不正确");
            nullInput(p);
            return;
        }else if(k==3&&$(p).val().length !=  18){
            layer.msg("身份证号码长度不正确");
            nullInput(p);
            return;
        }else{
            $(p).parents(".weui-cell").css("border","1px solid #eee");
        }
        if(allFull==0&&(k==formInput.length-1)){
            var paramThis = {
                "empName": exName,
                "empMobile1": phoneNum,//手机号码
                "empPic": empPic,//微信头像
                "empWeixin": weixinId,//微信号ID
                "yzm": phoneCode,//验证码
                "empCard":idNum,//身份证号码
                "testRefOwid": testOwid,//评价打分表owid
                timestamp: new Date().getTime(),
            };
            ajaxPost("bckjBizMidQuest/saveExpertEmploy",paramThis,2,"","","expertMark");
        }
    })
});

function nullInput(p,type) {
    if(type!=1){
        $(p).focus();
    }
    $(p).parents(".weui-cell").css("border","1px solid #ffa801");
}


// 获取验证码 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
var timeleft=60;//验证码等待时间
var registerBtn=$(".registerCode");
var phoneDom = $("#phoneNum");
//获取验证码
registerBtn.click(function(){
    phoneNum= phoneDom.val();
    exName = $("#exName").val();
    phoneCode = $("#phoneCode").val();
    idNum = $("#idNum").val();
    var phoneParent = phoneDom.parents(".weui-cell");
    if(!phoneNum){
        layer.msg("请先输入手机号");
        phoneDom.focus();
        phoneParent.css("border","1px solid #ffa801");
        return;
    }
    if(phoneNum.length!=11){
        layer.msg("手机号长度不正确");
        phoneDom.focus();
        phoneParent.css("border","1px solid #ffa801");
        return;
    }
    phoneParent.css("border","1px solid #eee");
    $(this).attr("disabled",true); //防止多次点击
    mobile = phoneDom.val();
    ajaxPost("bckjBizMidQuest/getYzm",{"empMobile1":phoneNum},1,"","","expertMark");
});
//计时函数
function timeCount(){
    timeleft-=1
    if (timeleft>0){
        registerBtn.html(timeleft+"S");
        setTimeout(timeCount,1000)
    }
    else {
        registerBtn.html("重新发送");
        timeleft=60 ;  //重置等待时间
        registerBtn.removeAttr("disabled");
    }
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//统一数据处理
function handleData(data,index,typeId) {
    if(data.backCode == 0){
        switch (index){
            case 1://获取验证码
                timeCount();
                 layer.msg("发送成功请注意查收", {icon: 1});
                 break;
            case 2://注册
                layer.msg("注册成功", {icon: 1});
                window.setTimeout(pageHref,1000);
                break;
        }
    }else{
        layer.msg(data.errorMess);
    }
}

function pageHref() {
    window.location.href = "faceMatch.htm";
}



