// pages/login/login.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/md5.min.js')
const app = getApp()
var url = app.globalData.ApiUrl;
import WxValidate from '../../libs/wx-validate/WxValidate'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isauthorize: false,
    phone:'',
    psw:'',
    pass: true
  },

  initValidate() {
    // 验证字段的规则
    const rules = {
      phone: {
        required: true,
        tel: true,
      },
      psw: {
        required: true
      }
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      phone: {
        required: '请填写手机号/账号',
        tel: '请填写正确手机号',
      },
      psw: {
        required: '请填写密码',
      }
    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  //登录
  formLogin: function (e) {
    var that = this;
    const params = e.detail.value;
    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      common.toast(error.msg,'none',2000)
      return false
    }
    var data = {
      "swZh": params.phone,
      "swMm": params.psw
    }
    common.ajax('zustcommon/bckjBizYhxx/swYtLogin', data, function (res) {
      if (res.data.backCode == 0) {
        wx.reLaunch({
          url: '../shouye/shouye',
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.initValidate();
    var that = this;
    wx.getSetting({
      success(res) {
        if (!res.authSetting['scope.userInfo']) {
          that.setData({
            isauthorize: true,
          })
        } else {
          that.setData({
            isauthorize: false,
          })
        }
      }
    })
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
  }
})