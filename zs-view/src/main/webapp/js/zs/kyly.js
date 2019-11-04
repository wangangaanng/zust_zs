$(document).ready(function () {
    initPicPath();
});

//初始化图片
function initPicPath() {
    var data = {
        lmbh: "",
        lx: 0
    };
    ajax("zustcommon/bckjBizPicvid/getPicList", data, function (res) {
        if (res.backCode === 0) {
            if (res.bean && res.bean.length > 0) {
                var top = '';
                var bottom = '';
                $.each(res.bean, function (k, p) {
                    top = '';
                    bottom = '';
                    top = '<div class="swiper-slide">' +
                                '<img src="k.xsbt">' +
                              '</div>';
                    bottom = '<div class="swiper-slide">' +
                                '<img src="k.xsbt">' +
                              '</div>';
                    $('#top-wrapper').append(top);
                    $('#bottom-wrapper').append(bottom);
                });
            }
        } else {
            walert(res.errorMess);
        }
    })
}