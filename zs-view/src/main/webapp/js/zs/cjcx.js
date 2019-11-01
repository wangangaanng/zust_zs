$(document).ready(function () {
    $('#input_sfzh, #input_zkzh').keyup(function (event) {
        if (event.keyCode === 13) {
            queryGrade();
        }
    })
});

//查询成绩
function queryGrade() {
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
    ajax('zustzs/bckjBizCjcx/cjcx', data, function (res) {
        console.log(res);
        if (res.backCode === 0) {
            $('#zkzh').html(res.bean.ksh);
            $('#sfzh').html(res.bean.sfzh);
            var str = '';
            str = '<tr>' +
                    '<td>res.bean.xm ? res.bean.xm : "无"</td>' +
                    '<td>res.bean.jtdz ? res.bean.jtdz : "无"</td>' +
                    '<td>res.bean.fs ? res.bean.fs : "无"</td>' +
                    '<td>res.bean.jcsj ? res.bean.jcsj : "无"</td>' +
                    '<td>res.bean.mzdm ? res.bean.mzdm : "无"</td>' +
                    '<td>res.bean.memo ? res.bean.memo : "无"</td>' +
                '</tr>';
            $('.table tbody').append(str);
        } else {
            walert("未查到成绩，请检查身份证号或准考证号是否输入正确")
        }
    })
}