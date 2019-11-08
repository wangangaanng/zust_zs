var sex;
$(document).ready(function () {
    showProv()
    $(".dropdown-menu a").click(function () {
        var val = $(this).html()
        $('#sex').html(val);
        sex = $(this).parents('li').index()+1;
        console.log(sex)
    })
});

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
        type:'2'
    };
    ajax('zustcommon/common/sendCode',data,function (res) {
        seconds()
    })
}

function swYtzc() {
    var swZh = $('#swZh').val();
    var xm = $('#xm').val();
    var yzm = $('#yzm').val();
    var swMm = $('#swMm').val();
    var swMm2 = $('#swMm2').val();
    var qxzy = $('#qxzy').val();
    if(!(!!swZh)){
        walert('请输入手机号')
        return
    }
    if(swZh.length!=11){
        walert('请输入正确手机号')
        return
    }
    if(!(!!xm)){
        walert('请输入真实姓名')
        return
    }
    if(!(!!sex)){
        walert('请选择性别')
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
    if(!(!!current.prov)){
        walert('请选择学籍省')
        return
    }
    if(!(!!current.city)){
        walert('请选择学籍市')
        return
    }
    if(!(!!current.country)){
        walert('请选择学籍区')
        return
    }
    if(!(!!qxzy)){
        walert('请输入倾向专业')
        return
    }
    var data = {
        swZh:swZh,
        xm:xm,
        xb:sex,
        yzm:yzm,
        swMm:swMm,
        prov:$('#prov option:selected').html(),
        city:$('#city option:selected').html(),
        area:$('#country option:selected').html(),
        qxzy:qxzy
    };
    console.log(data)
    ajax('zustcommon/bckjBizYhxx/swYtzc',data,function () {
        window.location.href='SWYTlogin'
    })
}