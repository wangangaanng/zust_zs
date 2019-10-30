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
        console.log(res);

    })
}