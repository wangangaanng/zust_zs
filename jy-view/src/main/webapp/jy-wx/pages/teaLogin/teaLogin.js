// pages/teaLogin/teaLogin.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/md5.min.js')
const app = getApp()
var url = app.globalData.ApiUrl;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    yhDlzh: '',
    yhDlmm: '',
    isauthorize: false,
    userType: ''
  },
  getyhDlzh(e) {
    this.setData({
      yhDlzh: e.detail
    })
  },
  getyhDlmm(e) {
    this.setData({
      yhDlmm: e.detail
    })
  },
  login() {
    if (!this.data.yhDlzh) {
      wx.showToast({
        icon: 'none',
        title: '请输入账号',
      })
      return false
    } else if (!this.data.yhDlmm) {
      wx.showToast({
        icon: 'none',
        title: '请输入密码',
      })
      return false
    }
    var data = {
      "openid": wx.getStorageSync('openId'),
      "nickname": wx.getStorageSync('userInfo').nickName,
      "gender": wx.getStorageSync('userInfo').gender,
      "city": wx.getStorageSync('userInfo').city,
      "province": wx.getStorageSync('userInfo').province,
      "country": wx.getStorageSync('userInfo').country,
      "avatarUrl": wx.getStorageSync('userInfo').avatarUrl,
      "unionid": wx.getStorageSync('unionid'),
      "yhDlzh": this.data.yhDlzh,
      "yhDlmm": util.md5(this.data.yhDlmm),
      "type":"teatch",
      "wxid":'wx01',
    }
    common.ajax('zustcommon/bckjBizYhxx/appletLogin', data, function (res) {
      if (res.data.backCode == 0) {
        wx.removeStorageSync('userType')
        wx.removeStorageSync('yhOwid')
        wx.removeStorageSync('qyInfo')
        wx.removeStorageSync('stuInfo')
        if (res.data.bean) {
          wx.setStorageSync('userType', '2')//教师
          wx.setStorageSync('yhOwid', res.data.bean.owid)
          wx.setStorageSync('teaInfo', res.data.bean)
        }
        var pages = getCurrentPages();
        var beforePage = pages[pages.length - 2];
        if (JSON.stringify(beforePage.options) != "{}") {
          beforePage.onLoad(beforePage.options);
        }
        wx.navigateBack()

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
  onLoad: function (options) {
    if (!wx.getStorageSync('unionid')) {
      this.setData({
        userType: '1',
        isauthorize: true,
      })
    } else {
      this.setData({
        isauthorize: false,
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

  }
})