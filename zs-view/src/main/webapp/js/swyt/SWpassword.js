function eye(obj) {
    var val = obj.value
    if(val=="0"){
        $(obj).prev('input').attr("type",'password');
        $(obj).addClass('glyphicon-eye-close').removeClass('glyphicon-eye-open')
        obj.value=1
    }else {
        $(obj).prev('input').attr('type','text');
        $(obj).addClass('glyphicon-eye-open').removeClass('glyphicon-eye-close')
        obj.value=0
    }
}

function sendCode() {
    var swZh = $('#swZh').val();
    if(!(!!swZh)){
        walert('请输入手机号')
        return
    }
    if(swZh.length!=11){
        walert('请输入正确手机号')
        return
    }
    var data = {
        swZh:swZh,
        type:'1'
    };
    ajax('zustcommon/common/sendCode',data,function (res) {
        seconds()
    })
}

var time = 60;
var t;
function seconds() {
    t=setInterval(function () {
        if(time==0){
            clearInterval(t)
            $('.getymz').attr('onclick','sendCode()')
            $('.getymz').html('重新发送')
            time=60
        }else {
            $('.getymz').removeAttr('onclick');
            time--;
            $('.getymz').html('剩余'+time+'秒')
        }
    },1000)
}

function forgetPwd() {
    var swZh = $('#swZh').val();
    var swMm = $('#swMm').val();
    var swMm2 = $('#swMm2').val();
    var yzm = $('#yzm').val();
    if(!(!!swZh)){
        walert('请输入手机号')
        return
    }
    if(swZh.length!=11){
        walert('请输入正确手机号')
        return
    }
    if(!(!!yzm)){
        walert('请输入验证码')
        return
    }
    if(!(!!swMm)){
        walert('请输入密码')
        return
    }
    if(swMm.length>16||swMm.length<6){
        walert('密码长度不正确')
        return
    }
    if(!(!!swMm2)){
        walert('请输入确认密码')
        return
    }
    if(swMm!=swMm2){
        walert('两次密码不同')
        return
    }
    var data = {
        swZh:swZh,
        swMm:swMm,
        yzm:yzm
    }
    ajax('zustcommon/bckjBizYhxx/forgetPwd',data,function (res) {
        if(res.backCode==0){
            window.location.href='trinitylogin'
        }else{
            walert()
        }
    })
}
