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
    qyFrsfz: '',
    isauthorize: false,
    userType: ''
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
        title: '请输入法人身份证后六位或统一信用码后六位',
      })
      return false
    }
    var data = {
      "qyTysh": this.data.qyTysh,
      "qyFrsfz": this.data.qyFrsfz
    }
    common.ajax('zustjy/bckjBizQyxx/login', data, function (res) {
      if (res.data.backCode == 0) {
        wx.removeStorageSync('userType')
        wx.removeStorageSync('yhOwid')
        wx.removeStorageSync('stuInfo')
        if (res.data.bean) {
          wx.setStorageSync('userType', '0')
          wx.setStorageSync('yhOwid', res.data.bean.owid)
          var obj={}
          obj.qymc = res.data.bean.qymc;
          obj.qyLxr = res.data.bean.qyLxr;
          obj.qyLxrdh = res.data.bean.qyLxrdh;
          obj.qyYx = res.data.bean.qyYx;
          wx.setStorageSync('qyInfo', obj)
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
  register(){
    wx.navigateTo({
      url: '../qyzc/qyzc',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (!wx.getStorageSync('unionid')) {
      this.setData({
        userType: '2',
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