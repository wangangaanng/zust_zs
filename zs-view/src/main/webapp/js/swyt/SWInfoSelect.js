$(document).ready(function () {
    getByType('10020');
    getByType('10024');
    getXkkm();

    $('#file3').change(function () {
        console.log($('#file3').prop('files'))
            var str = '<li class="file-btn_wrap" style="float: left;margin-right: 10px;">' +
            '<img src="'+URL.createObjectURL($('#file3')[0].files[0])+'">\n' +
            '</li>';
        $('#fileList').append(str)
        fileUpload(2, $('#file3')[0].files[0], function (res) {
            if (res.bakcCode == 0) {

            } else {
                walert(res.errorMess)
            }
        })
    })
})

function getByType(e) {
    var data = {
        dicType: e
    }
    ajax('zustcommon/common/getByType', data, function (res) {
        if (res.backCode == 0) {
            if (e == '10020') {
                for (var i in res.bean) {
                    var str = '<option value="' + res.bean[i].dicVal1 + '">' + res.bean[i].dicVal2 + '</option>';
                    $('#wyyz').append(str)
                }
            } else if (e == '10024') {
                for (var i in res.bean) {
                    var str = '<li>' +
                        '<div class="checkbox">' +
                        '<label class="checkbox"><input type="checkbox" name="checkbox1" value="' + res.bean[i].dicVal1 + '" /><span>' + res.bean[i].dicVal2 + '</span></label>' +
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