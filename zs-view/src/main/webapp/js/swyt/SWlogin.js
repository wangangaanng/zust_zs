$(document).ready(function () {
    $("#swMm,#swZh").keyup(function(event){
        if(event.keyCode == 13){
            swYtLogin()
        }
    });
});

function swYtLogin() {
    var swZh = $('#swZh').val();
    var swMm = $('#swMm').val();
    if(!(!!swZh)){
        walert('请输入手机号')
        return
    }
    if(swZh.length!=11){
        walert('请输入正确手机号')
        return
    }
    if(!(!!swMm)){
        walert('请输入密码')
        return
    }
    var data = {
        swZh:swZh,
        swMm:swMm
    }
    $.ajax({
        url: localUrl,//'http://localhost:8011/webAjax/executeAPI/',//'https://job.zust.edu.cn//gate/'+method,
        data: {
            "method": 'zustcommon/bckjBizYhxx/swYtLogin',
            "data": JSON.stringify(data),
            timestamp: new Date().getTime()
        },
        method: 'POST',
        success: function (res) {
            finishLoad();
            if(res.backCode==0){
                addCookie('swOwid',res.bean.owid);
                window.location.href=base+'/trinityEnrollment/0';
            }else{
                walert(res.errorMess)
            }
        },
        error: function (err) {
            finishLoad();
            walert('系统出错');
        }
    })
   /* ajax('zustcommon/bckjBizYhxx/swYtLogin',data,function (res) {
        if(res.backCode==0){
            addCookie('swOwid',res.bean.owid);
            window.location.href=base+'/trinityEnrollment/0';
        }else {
            walert(res.errorMess)
        }
    })*/
}