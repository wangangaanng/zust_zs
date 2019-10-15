// pages/stuLogin/stuLogin.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/md5.min.js')
const app = getApp()
var url = app.globalData.ApiUrl;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    qyTysh: '',
    qyFrsfz: ''
  },
  getqyTysh(e) {
    this.setData({
      qyTysh: e.detail
    })
  },
  getqyFrsfz(e) {
    this.setData({
      qyFrsfz: e.detail
    })
  },
  login() {
    if (!this.data.qyTysh) {
      wx.showToast({
        icon: 'none',
        title: '请输入社会统一信用码',
      })
      return false
    } else if (!this.data.qyFrsfz) {
      wx.showToast({
        icon: 'none',
        title: '请输入法人身份证后六位',
      })
      return false
    }
    var data = {
      "qyTysh": this.data.qyTysh,
      "qyFrsfz": this.data.qyFrsfz
    }
    common.ajax('zustjy/bckjBizQyxx/login', data, function (res) {
      if (res.data.backCode == 0) {
        wx.clearStorageSync()
        wx.removeStorageSync('userType')
        wx.removeStorageSync('yhOwid')
        wx.removeStorageSync('stuInfo')
        if (res.data.bean) {
          wx.setStorageSync('userType', '0')
          wx.setStorageSync('yhOwid', res.data.bean.owid)
          wx.setStorageSync('qyInfo', res.data.bean)
        }
        wx.reLaunch({
          url: '../index/index',
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
  register(){
    wx.navigateTo({
      url: '../qyzc/qyzc',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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