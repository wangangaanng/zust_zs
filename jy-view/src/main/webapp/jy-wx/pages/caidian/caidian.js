// pages/caidian/caidian.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    headImgUrl: '',
    name: '',
    xh: '',
    result: '',
    owid:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.getLocation({
      type: 'gcj02',// 默认wgs84
      success: function (res) {
        that.setData({
          latitude: res.latitude,
          longitude: res.longitude
        })
      },
      fail: function (res) { }
    });
    var curDate = common.formatTime(new Date());
    that.setData({
      xq: common.convertWKtwo(curDate),
      date: curDate.substring(0, 10),
      time: curDate.substring(11, 19)
    })
    var Interval = setInterval(function () {
      var curDate1 = common.formatTime(new Date());
      that.setData({
        time: curDate1.substring(11, 19)
      })
    }, 1000)
    var userInfo = wx.getStorageSync('userInfo');
    var stuInfo = wx.getStorageSync('stuInfo');
    this.setData({
      "headImgUrl": userInfo.avatarUrl,
      "name": stuInfo.xm,
      "xh": stuInfo.xsxh
    })
    if (options.owid) {
      this.setData({
        owid: options.owid
      })
      getContent(this, options.owid);
    }

  },
  caidian(){
    var that = this;
    wx.getLocation({
      type: 'gcj02',// 默认wgs84
      success: function (res2) {
        var data = { "owid": that.data.owid, "zphGpsjd": res2.longitude, "zphGpswd": res2.latitude };
        common.ajax('zustjy/bckjBizJob/setCdPoint', data, function (res) {
          if (res.data.backCode == 0) {
            wx.showModal({
              title: '采集成功',
              showCancel: false,
              content: '经度：' + res2.longitude + '\n纬度：' + res2.latitude,
              success(res3) {
                if (res3.confirm) {
                  wx.navigateBack();
                }
              }
            })
          } else {
            wx.showToast({
              title: res.data.errorMess,
              icon: 'none',
              duration: 2000
            })
          }
        });
      },
      fail: function (res) {
        wx.showToast({
          title: '获取位置失败',
        })
       }
    });
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
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
var getContent = function (that, owid) {//招聘详情
  var data = { "owid": owid, "yhOwid": wx.getStorageSync("yhOwid") };
  common.ajax('zustjy/bckjBizJob/getOneJob', data, function (res) {
    if (res.data.backCode == 0) {
      res.data.bean.createtime = res.data.bean.createtime.substring(0, 10)
      if (res.data.bean.zphKsrq) {
        res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 10)
      }
      that.setData({
        result: res.data.bean,
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