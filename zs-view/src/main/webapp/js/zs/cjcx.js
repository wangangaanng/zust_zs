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
        console.log(res)
    })
}