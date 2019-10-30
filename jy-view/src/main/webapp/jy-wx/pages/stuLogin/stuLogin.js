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
    yhDlmm:'',
    isauthorize: false,
    userType: ''
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
        title: '请输入身份证或学号',
      })
      return false
    } else if (!this.data.yhDlmm) {
      wx.showToast({
        icon: 'none',
        title: '请输入身份证后六位',
      })
      return false
    }
    var data={
      "openId": wx.getStorageSync('openId'),
      "nickname":wx.getStorageSync('userInfo').nickName,
      "gender": wx.getStorageSync('openId').gender,
      "city": wx.getStorageSync('openId').city,
      "province": wx.getStorageSync('openId').province,
      "country": wx.getStorageSync('openId').country,
      "avatarUrl": wx.getStorageSync('openId').avatarUrl,
      "unionid": wx.getStorageSync('unionid'),
      "yhDlzh": this.data.yhDlzh,
      "yhDlmm": util.md5(this.data.yhDlmm)
    }
    common.ajax('zustcommon/bckjBizYhxx/logIn', data, function (res) {
      if (res.data.backCode == 0) {
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
        var pages = getCurrentPages();
        var beforePage = pages[pages.length - 2];
        if (JSON.stringify(beforePage.options) != "{}"){
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