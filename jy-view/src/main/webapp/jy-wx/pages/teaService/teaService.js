// pages/teaService/teaService.js
var common = require('../../libs/common/common.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: wx.getStorageSync('teaInfo').zjxm || '',
    headImg: '../../static/headImg.png',
    menuList: [
      { text: '学生答疑', icon: '../../../static/index-icon04.png', url: '../teaZx/teaZx' }],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  },
  linkurl: function (e) {
    var url = e.currentTarget.dataset.url;
    if (url) {
      wx.navigateTo({
        url: url,
      })
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  logout: function () {
    wx.removeStorageSync('userType')
    wx.removeStorageSync('yhOwid')
    wx.removeStorageSync('teaInfo')
    wx.reLaunch({
      url: '../index/index',
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.setData({
      name: wx.getStorageSync('teaInfo').zjxm || '',
      headImg: wx.getStorageSync('userInfo').avatarUrl || '',
    })
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