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
    yhDlzh:'',
    yhDlmm:''
  },
  getyhDlzh(e){
    this.setData({
      yhDlzh: e.detail
    })
  },
  getyhDlmm(e) {
    this.setData({
      yhDlmm: e.detail
    })
  },
  login(){
    if (!this.data.yhDlzh){
      wx.showToast({
        icon:'none',
        title: '请输入用户名',
      })
      return false
    } else if (!this.data.yhDlmm) {
      wx.showToast({
        icon: 'none',
        title: '请输入密码',
      })
      return false
    }
    var data={
      "yhDlzh": this.data.yhDlzh,
      "yhDlmm": util.md5(this.data.yhDlmm)
    }
    common.ajax('zustcommon/bckjBizYhxx/logIn', data, function (res) {
      if (res.data.backCode == 0) {
        wx.clearStorageSync()
        wx.removeStorageSync('userType')
        wx.removeStorageSync('yhOwid')
        wx.removeStorageSync('qyInfo')
        if (res.data.bean){
          wx.setStorageSync('userType', '1')
          wx.setStorageSync('yhOwid', res.data.bean.owid)
          wx.setStorageSync('stuInfo', res.data.bean)
        }
        // wx.reLaunch({
        //   url: '../index/index',
        // })
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