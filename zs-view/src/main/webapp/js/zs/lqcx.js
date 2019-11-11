$(document).ready(function () {
    $('#input_sfzh, #input_zkzh').keyup(function (event) {
        if (event.keyCode === 13) {
            queryGrade();
        }
    })
});

//查询录取
function queryIn() {
    //准考证号
    var ksh = $('#input_zkzh').val();
    //身份证号
    var sfzh = $('#input_sfzh').val();
    if (!(!!(sfzh))) {
        walert("请输入身份证号");
    }
    if (!(!!(ksh))) {
        walert("请输入准考证号");
    }
    if (sfzh.length !== 18) {
        walert("请输入正确的身份证号")
    }
    var data = {
        ksh: ksh,
        sfzh: sfzh
    };
    ajax('zustzs/bckjBizLqxs/lqcx', data, function (res) {
        clearTable();
        if (res.backCode === 0) {
            $('#zkzh').html(res.bean.ksh);
            $('#sfzh').html(res.bean.sfzh);
            $('#lxdh').html(res.bean.lxdh ? res.bean.lxdh : "无");
            $('#xm').html(res.bean.xm);
            $('#zhuhe').html("浙江科技学院-" + res.bean.lqzy + "预录取，最终录取请查询当地考试院。");
            if(null!=res.bean.jcsj) {
                $("#lqd").html("录取通知单已经于<b>"+res.bean.jcsj+"</b>寄出");
            }
            if(null!=res.bean.ems) {
                $("#ems_dh").html("EMS单号：<b style='cursor: pointer'  class='emsUrl' ems='"+res.bean.ems+"'>"+res.bean.ems+"</b>，请注意查收！！！");
            }
            $("#successed").show();
            $(".emsUrl").click(function () {
                var emsUrl=$(this).attr("ems");
                window.open("http://www.ems.com.cn/?mailNum="+emsUrl,"_blank");
            })
        } else {
            ksh = '';
            sfzh = '';
            $('.error').show();
        }
    })
}

function clearTable() {
    $('#zkzh').html("");
    $('#sfzh').html("");
    $('#xm').html("");
    $('#lxdh').html("");
    $('.success').hide();
    $('.error').hide();
}