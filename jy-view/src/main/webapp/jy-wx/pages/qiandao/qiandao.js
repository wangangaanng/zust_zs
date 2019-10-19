// pages/qiandao/qiandao.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    headImgUrl:'',
    name:'',
    xh:'',
    result:'',
    markers: [{
      iconPath: "/static/location.png",
      id: 0,
      latitude: '',
      longitude: '',
      width: 50,
      height: 50
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    if (wx.getStorageSync("yhOwid")){
      wx.getLocation({
        type: 'gcj02',// 默认wgs84
        success: function (res) { 
          var latitude ="markers[0].latitude"
          var longitude = "markers[0].longitude"
          that.setData({
            [latitude]: res.latitude,
            [longitude]: res.longitude,
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
        getContent(this, options.owid);
      }
    }else{
      wx.navigateTo({
        url: '../stuLogin/stuLogin',
      })
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
        // if (res.data.bean.zwlx == 4) {
        //   res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 16)
        // } else {
        res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 10)
        // }
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