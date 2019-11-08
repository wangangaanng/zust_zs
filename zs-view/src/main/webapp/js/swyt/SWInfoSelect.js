var jsfj = [];
var readyNum = 0;
$(document).ready(function () {
    getByType('10020');
    getByType('10024');
    getXkkm();
    var t = setInterval(function () {
        if(readyNum==3){
            getXkcj()
            clearInterval(t)
        }
    },100)
    $('#file3').change(function () {
        console.log($('#file3').prop('files'))
        var str = '<li style="float: left;">' +
            '<div class="file-btn_wrap" style="margin-right: 10px;">' +
            '<img src="'+URL.createObjectURL($('#file3')[0].files[0])+'">' +
            '</div>' +
            '<label class="uploadlabel" onclick="removeJsfj(this)">删除</label>' +
            '</li>';
        $('#fileList').append(str)
        fileUpload(2, $('#file3')[0].files[0], function (res) {
            if (res.backCode == 0) {
                jsfj.push(res.bean.owid);
            } else {
                walert(res.errorMess)
            }
        })
    })
})
function removeJsfj(e) {
    console.log(jsfj)
    var index = $(e).parents('li').index()
    jsfj.splice(index,1)
    console.log(jsfj)
    $(e).parents('li').remove()
}
function getByType(e) {
    var data = {
        dicType: e
    }
    ajax('zustcommon/common/getByType', data, function (res) {
        if (res.backCode == 0) {
            readyNum++;
            if (e == '10020') {
                for (var i in res.bean) {
                    var str = '<option value="' + res.bean[i].dicVal1 + '">' + res.bean[i].dicVal2 + '</option>';
                    $('#wyyz').append(str)
                }
            } else if (e == '10024') {
                for (var i in res.bean) {
                    var str = '<li>' +
                        '<div class="checkbox">' +
                        '<label class="checkbox"><input type="checkbox" name="zxlb" value="' + res.bean[i].dicVal2 + '" /><span>' + res.bean[i].dicVal2 + '</span></label>' +
                        '</div>' +
                        '</li>'
                    $('#zxlb').append(str)
                }
            }
        } else {
            walert(res.errorMess)
        }
    })
}

function getXkkm() {
    var data = {
        dicType: '10022',
        dicVal5: '1'
    }
    ajax('zustcommon/common/getXkkm', data, function (res) {
        readyNum++;
        if (res.backCode == 0) {
            for (var i in res.bean) {
                var str = '<option value="' + res.bean[i].dicval1 + '">' + res.bean[i].dicval2 + '</option>'
                $('#xkkm select').append(str);
            }
        } else {
            walert(res.errorMess)
        }
    })
}

function finishXk() {
    var xkList = [];
    $('#xkkm').find('select').each(function () {
        if(!emptyCheck($(this).find('option:selected').val())){
            return false;
        }
        var obj ={
            kmbh:$(this).find('option:selected').val(),
            kmmc:$(this).find('option:selected').html()
        }
        xkList.push(obj)
    })
    if(xkList.length!=3){
        walert('尚有选考科目未选择')
        return;
    }
    if(xkList[0].kmbh==xkList[1].kmbh||xkList[0].kmbh==xkList[2].kmbh||xkList[1].kmbh==xkList[2].kmbh){
        walert('选考科目重复，请重新选择')
        return
    }
    $('#xkkm').find('input[type=number]').each(function () {
        if(!emptyCheck($(this).val())){
            return false;
        }
        var index = $(this).parents('.form-group').index()
        xkList[index].kmcj = $(this).val()
        xkList[index].xssx = parseInt(index)+1
    })
    for(var i in xkList){
        if(!emptyCheck(xkList[i].kmcj)){
            walert('尚有选考成绩未填写')
            return;
        }
    }
    var wyyz = $('#wyyz option:selected').val()
    if(!emptyCheck(wyyz)){
        walert('请选择外语语种')
        return
    }
    var yzmc = $('#wyyz option:selected').html()
    var wycj = $('#wycj').val()
    if(!emptyCheck(wycj)){
        walert('请填写外语成绩')
        return
    }
    var zxlbMap = [];
    $('input[name=zxlb]:checked').each(function () {
        zxlbMap.push($(this).val())
    })
    var zxlb = '';
    if(zxlbMap.length>0){
        for(var i in zxlbMap){
            if(!!zxlb){
                zxlb= zxlb+','+zxlbMap[i]
            }else {
                zxlb = zxlbMap[i]
            }
        }
    }
    var jsfjstr = '';
    if(jsfj.length>0){
        for(var i in jsfj){
            if(!!jsfjstr){
                jsfjstr= jsfjstr+",'"+jsfj[i]+"'"
            }else {
                jsfjstr = "'"+jsfj[i]+"'"
            }
        }
    }
    var jssm = $('#jssm').val();
    var qtqk = $('#qtqk').val();
    var tcah = $('#tcah').val();
    var data = {
        "yhRefOwid":getCookie('swOwid'),
        "xkList":xkList,
        "wyyz":wyyz,
        "yzmc":yzmc,
        "wycj":wycj,
        "zxlb":zxlb,
        "jssm":jssm,
        "qtqk":qtqk,
        "tcah":tcah,
        "jsfj":jsfjstr
    }
    ajax('zustswyt/bckjBizCjxx/finishXk', data, function (res) {
        if (res.backCode == 0) {

        } else {
            walert(res.errorMess)
        }
    })
}
function getXkcj() {
    var data = {
        "yhRefOwid":getCookie('swOwid'),
    }
    ajax('zustswyt/bckjBizCjxx/getXkcj', data, function (res) {
        if (res.backCode == 0) {
            $('#jssm').val(res.bean.jssm);
            $('#qtqk').val(res.bean.qtqk);
            $('#tcah').val(res.bean.tcah);
            $('#wycj').val(res.bean.wycj);
            var zxlb = res.bean.zxlb.split(',');
            for(var i in zxlb){
               $('input[name=zxlb]').each(function () {
                   if($(this).val()==zxlb[i]){
                       $(this).attr('checked',true)
                   }
               })
            }
            for(var i in res.bean.xkList){
                $('#xkkm').find('.form-group').eq(i).find('select').val(res.bean.xkList[i].kmbh)
                $('#xkkm').find('.form-group').eq(i).find('input').val(res.bean.xkList[i].kmcj)
            }
            $('#wyyz').val(res.bean.wyyz)
            for(var i in res.bean.jsfj){
                var str = '<li style="float: left;">' +
                    '<div class="file-btn_wrap" style="margin-right: 10px;">' +
                    '<img src="'+imagePath+res.bean.jsfj[i].filePath+'">' +
                    '</div>' +
                    '<label class="uploadlabel" onclick="removeJsfj(this)">删除</label>' +
                    '</li>';
                $('#fileList').append(str)
                jsfj.push(res.bean.jsfj[i].owid)
            }
        } else {
            walert(res.errorMess)
        }
    })
}