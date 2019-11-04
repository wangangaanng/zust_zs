// pages/enterpriseXjh/enterpriseXjh.js
import Dialog from 'vant-weapp/dialog/dialog';
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({

    /**
     * 页面的初始数据
     */
    data: {
        pageSize: 20,
        pageNo: 1,
        totalPage: '',
        xjhList: [],
        key: ''
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },
    onChange(e) {
        this.setData({
            key: e.detail
        });
    },
    onClear() {
        var that = this;
        refresh(this);
    },
    onSearch: function () {
        var that = this;
        refresh(this);
    },
    loadMore: function () {
        var that = this;
        if ((that.data.pageNo + 1) <= that.data.totalPage) {
            that.setData({
                pageNo: that.data.pageNo + 1,
            })
            myJobList(that);
        }
    },
    shenqin() {
        wx.navigateTo({
            url: '../newZpgg/newZpgg',
        })
    },
    detail(e) {
        wx.navigateTo({
            url: '../zpggXq/zpggXq?owid=' + e.currentTarget.dataset.owid,
        })
    },
    onClose(event) {
        var that = this
        console.log(event)
        const { position, instance } = event.detail;
        switch (position) {
            case 'left':
            case 'cell':
                instance.close();
                break;
            case 'right':
                wx.showModal({
                    title: '提示',
                    content: "确定删除该条记录吗？",
                    success(res) {
                        if (res.confirm) {
                            var data = {
                                "owid": event.currentTarget.dataset.owid
                            };
                            common.ajax('zustjy/bckjBizJob/deleteOneJob', data, function (res) {
                                if (res.data.backCode == 0) {
                                    wx.showToast({
                                        title: '删除成功',
                                        icon: 'none',
                                        duration: 2000
                                    })
                                    that.data.xjhList.splice(event.currentTarget.dataset.index, 1)
                                    that.setData({
                                        xjhList: that.data.xjhList
                                    })
                                    instance.close();
                                } else {
                                    wx.showToast({
                                        title: res.data.errorMess,
                                        icon: 'none',
                                        duration: 2000
                                    })
                                }
                            });


                            console.log('用户点击确定')
                        } else if (res.cancel) {
                            instance.close();
                            console.log('用户点击取消')
                        }
                    }
                })
                break;
        }
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        refresh(this)
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    // onReachBottom: function () {
    //   var that = this;
    //   if ((that.data.pageNo + 1) <= that.data.totalPage) {
    //     that.setData({
    //       pageNo: that.data.pageNo + 1,
    //     })
    //     myJobList(that);
    //   }
    // },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})

function refresh(that) {
    that.setData({
        xjhList: [],
        pageNo: 1,
        totalPage: ""
    })
    myJobList(that);

}

var myJobList = function (that, lx) {
    var data = {
        "zwbt": that.data.key, "qyxxRefOwid": wx.getStorageSync("yhOwid"), "zwlx": 2, "pageNo": that.data.pageNo, "pageSize": that.data.pageSize,
    };
    common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
        if (res.data.backCode == 0) {
            var arr = [];
            // for (var i = 0; i < res.data.bean.records.length; i++) {
            //   var obj = {};
            //   var object = res.data.bean.records[i];
            //   obj.owid = object.owid;
            //   obj.date = object.createtime.substring(5, 7) + "." + object.createtime.substring(8, 10);
            //   obj.year = object.createtime.substring(0, 4);
            //   obj.zwbt = object.zwbt;
            //   obj.qymc = object.exp1;
            //   obj.city = object.zwCity;
            //   obj.gzxz = object.zwGzxzStr;
            //   arr.push(obj);
            // }
            var xjhList;
            if (res.data.bean.records && res.data.bean.records.length > 0) {
                xjhList = that.data.xjhList.concat(res.data.bean.records)
            }
            var totalPage = res.data.bean.totalPage;
            that.setData({
                xjhList: xjhList,
                totalPage: totalPage,
            })
        } else {
            wx.showToast({
                title: res.data.errorMess,
                icon: 'none',
                duration: 2000
            })
        }
    });
}