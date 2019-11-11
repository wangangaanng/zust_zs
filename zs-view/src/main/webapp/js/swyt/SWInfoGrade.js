var xklist;//选考列表
var zhlist;//综合列表
// $(document).ready(function () {
//     //选考信息保存
//     // $("#saveGrade").click(function () {
//         // $("#basicForm").hide();
//         // $("#contactForm").hide();
//         // $("#gradeForm").hide();
//         // $("#selectForm").show();
//         // $(".jf-items .jf-item").eq(3).addClass("jf-active");
//     // });
//     getByType111('10022');
//     getByType111('10023');
// });

function getByType111(e) {
    var data = {
        dicType: e
    }
    ajax('zustcommon/common/getByType', data, function (res) {
        if (res.backCode == 0) {
            if (e == '10022') {
                getHkcj1(0)
                xklist = res.bean;
                for (var i in res.bean) {
                    var dicVal3 = res.bean[i].dicVal3.split(',');
                    var str = '<div class="form-group">' +
                        '<label for="qyGsxz" class="col-sm-2 control-label col-sm-offset-1">' + res.bean[i].dicVal2 + '<span class="red">*</span>：</label>' +
                        '<div class="col-sm-8">' +
                        '<select class="form-control" name="xk' + res.bean[i].dicVal1 + '"><option>请选择</option>';
                    for (var k in dicVal3) {
                        str += '<option value="' + dicVal3[k] + '">' + dicVal3[k] + '</option>';
                    }
                    str += '</select></div></div>';
                    $('#xk10022').append(str)
                }
            } else if (e == '10023') {
                getHkcj1(2)
                zhlist = res.bean;
                for (var i in res.bean) {
                    var dicVal3 = res.bean[i].dicVal3.split(',');
                    var str = '<div class="form-group">' +
                        '<label for="qyGsxz" class="col-sm-2 control-label col-sm-offset-1">' + res.bean[i].dicVal2 + '<span class="red">*</span>：</label>' +
                        '<div class="col-sm-8">' +
                        '<select class="form-control" name="zh' + res.bean[i].dicVal1 + '"><option>请选择</option>';
                    for (var k in dicVal3) {
                        str += '<option value="' + dicVal3[k] + '">' + dicVal3[k] + '</option>';
                    }
                    str += '</select></div></div>';
                    $('#zh10023').append(str)
                }
            }
        }else {
            walert(res.errorMess)
        }
    })
}

function finishHk(e) {
    var hkList = [];
    for (var i in xklist) {
        var value = $('#xk10022').find('select[name=xk' + xklist[i].dicVal1 + '] option:selected').attr('value')
        if (!!value) {
            var obj = {
                kmmc: xklist[i].dicVal2,
                kmbh: xklist[i].dicVal1,
                kmdj: value,
                xssx: parseInt(i) + 1,
            }
            hkList.push(obj)
        }else {
            walert(xklist[i].dicVal2+'成绩尚未选择')
            return
        }
    }
    var zhList = [];
    for (var i in zhlist) {
        var value = $('#zh10023').find('select[name=zh' + zhlist[i].dicVal1 + '] option:selected').attr('value')
        if (!!value) {
            var obj = {
                kmmc: zhlist[i].dicVal2,
                kmbh: zhlist[i].dicVal1,
                kmdj: value,
                xssx: parseInt(i) + 1,
            }
            zhList.push(obj)
        }else {
            walert(zhlist[i].dicVal2+'成绩尚未选择')
            return
        }
    }

    var data = {
        yhRefOwid: getCookie('swOwid'),
        zhList: zhList,
        hkList: hkList
    }
    ajax('zustswyt/bckjBizCjxx/finishHk', data, function (res) {
        if(res.backCode==0){
            if(e==0){
                $("#basicForm").hide();
                $("#contactForm").show();
                $("#gradeForm").hide();
                $("#selectForm").hide();
                $(".jf-items .jf-item").eq(2).removeClass("jf-active");
            }else {
                $("#basicForm").hide();
                $("#contactForm").hide();
                $("#gradeForm").hide();
                $("#selectForm").show();
                $(".jf-items .jf-item").eq(3).addClass("jf-active");
            }
        }else{
            walert(res.errorMess)
        }
    })
}

function getHkcj1(e) {
    ajax('zustswyt/bckjBizCjxx/getHkcj', {yhRefOwid: getCookie('swOwid'),lx:e}, function (res) {
        if(res.backCode == 0){
            if(e=='0'){
                for(var i in res.bean){
                    $('#xk10022').find('select[name=xk'+res.bean[i].kmbh+']').val(res.bean[i].kmdj)
                }
            }else if(e=='2'){
                for(var i in res.bean){
                    $('#zh10023').find('select[name=zh'+res.bean[i].kmbh+']').val(res.bean[i].kmdj)
                }
            }
        }else {
            walert(res.errorMess)
        }
    })
}