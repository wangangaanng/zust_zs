/**
 * Created by Xia Yu on 2019/7/30.
 */
$(function () {

});

wx.ready(function () {
    // 5 图片接口
    // 5.1 拍照、本地选图
    var images = {
        localId: [],
        serverId: []
    };
    var localid = "";
    if(emptyCheck($("#imgFace"))){//详情页 没有这个节点id 用于更换照片点击
        //证件正面
        document.querySelector('#imgFace').onclick = function () {
            wx.chooseImage({
                count: 1,
                success: function (res) {
                    images.localId = res.localIds;
                    if (res.localIds.length > 1) {
                        $.toast("一次只能选择一张图片", "text");
                        return;
                    }
                    localid = res.localIds[0];
                    // $("#imgFace").attr("src",res.localIds);
                    $("#imgFace").html("<img src='" + res.localIds + "' class='upImg'/>");
                    wx.uploadImage({
                        localId: localid,
                        isShowProgressTips: 1, // 默认为1，显示进度提示
                        success: function (res) {
                            serverId = res.serverId;
                            imgDown(serverId, 1);
                        },
                        fail: function (res) {
                            alert(JSON.stringify(res));
                        }
                    });
                }
            });
        };
    }
});
wx.error(function (res) {
    alert(res.errMsg);
});

