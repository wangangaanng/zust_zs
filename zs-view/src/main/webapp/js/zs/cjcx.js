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
        return;
    }
    if (!(!!(ksh))) {
        walert("请输入准考证号");
        return;
    }
    var data = {
        ksh: ksh,
        sfzh: sfzh
    };
    ajax('zustzs/bckjBizCjcx/cjcx', data, function (res) {
        clearTable();
        if (res.backCode === 0) {
            $('#zkzh').html(res.bean.ksh);
            $('#sfzh').html(res.bean.sfzh);
            var str = '';
            str='<tr>\n' +
                '<td>'+res.bean.xm+'</td>\n' +
                '<td>'+res.bean.jtdz+'</td>\n' +
                '<td>'+res.bean.yw+'</td>\n' +
                '<td>'+res.bean.jcsj.substr(0, 10)+'</td>\n' +
                '<td>'+getValue(res.bean.mzdm)+'</td>\n' +
                '<td>'+getValue(res.bean.memo)+'</td>\n' +
                '</tr>';
            $('#cx-table tbody').append(str);
        } else {
            walert("未查到成绩，请检查身份证号或准考证号是否输入正确")
        }
    })
}

function clearTable() {
    $('#zkzh').html("");
    $('#sfzh').html("");
    $('#cx-table tbody').html("");
}

function getValue(val) {
    if (null != val) {
        return val
    } else {
        return "无"
    }
}