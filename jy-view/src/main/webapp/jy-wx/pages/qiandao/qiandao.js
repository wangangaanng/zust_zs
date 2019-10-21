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
    owid:'',
    latitude:'',
    longitude:'',
    isqd:false,
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
        this.setData({
          owid: options.owid,
        })
        getContent(this, options.owid);
      }
    }else{
      wx.navigateTo({
        url: '../stuLogin/stuLogin',
      })
    }
    
  },
  qiandao:function(){
    wx.showModal({
      title: '提示',
      content: '签到后该微信号将与该账号绑定，绑定后不可更改，是否确认是本人签到',
      success(res) {
        if (res.confirm) {
          qd(this);
        } else if (res.cancel) {
          
        }
      }
    })
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
var qd = function(that){
  var data = { "xxlb": 1, "gpsJd": that.data.longitude, "gpsWd": that.data.latitude, "jobRefOwid": that.data.owid, "yhRefOwid": wx.getStorageSync("yhOwid") };
  common.ajax('zustjy/bckjBizXsgz/signInOrScribe', data, function (res) {
    if (res.data.backCode == 0) {
      wx.showToast({
        title: '签到成功',
      })
      that.setData({
        isqd: true,
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