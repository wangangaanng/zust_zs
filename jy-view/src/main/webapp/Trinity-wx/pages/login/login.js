// pages/login/login.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/md5.min.js')
const app = getApp()
var url = app.globalData.ApiUrl;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phone:'',
    psw:'',
    pass: true
  },

  //登录
  loginBtn: function (e) {
    var data = {
      "swZh": this.data.phone,
      "swMm": this.data.psw
    }
    common.ajax('zustcommon/bckjBizYhxx/swYtLogin', data, function (res) {
      if (res.data.backCode == 0) {
        wx.reLaunch({
          url: '../shouye/shouye',
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  //密码是否可见
  clickPass: function(e) {
    let pass = !this.data.pass
    console.log(pass)
    this.setData({
      pass
    })
  },
  //注册
  register: function (e) {
    wx.navigateTo({
      url: '../register/register',
    })
  },
  //忘记密码
  forgetPsw: function (e) {
    wx.navigateTo({
      url: '../forgetPsw/forgetPsw',
    })
  },
})