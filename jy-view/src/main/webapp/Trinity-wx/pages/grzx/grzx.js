// pages/grzx/grzx.js
var common = require('../../libs/common/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userName: '',
    userMobile: '',
    imgUrl: '../../static/logo.png',
    hasLogin: false
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var headUrl = wx.getStorageSync('headImg');
    if (headUrl) {
      that.setData({
        'imgUrl': headUrl,
        'userMobile': wx.getStorageSync('mobile')
      })
    }

    if (wx.getStorageSync('userName')) {
      that.setData({
        'userName': that.data.userName
      })
    } else {
      if (wx.getStorageSync('yhRefOwid')) {
        common.getInfoBasic(that);
      }
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () { },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this;
    that.setData({
      'xxbh': wx.getStorageSync('ybmxxbh')
    })
    if (!wx.getStorageSync('yhRefOwid')) { //未登录
      common.toast('请先登录', 'none', 2000)
      that.setData({
        "hasLogin": false,
        "userName": "浙江科技学院三位一体"
      })
    } else { //已经登录
      that.setData({
        "hasLogin": true
      })
    }
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

  },
  loginOut: function () {
    var that = this;
    wx.setStorageSync("yhRefOwid", "");
    that.setData({
      "hasLogin":false
    })
    //wx.setStorageSync("mobile", "");
    wx.navigateTo({
      url: '../login/login'
    })
  },
  swbm() {
    wx.navigateTo({
      url: '../myApply/myApply'
    })
  }
})