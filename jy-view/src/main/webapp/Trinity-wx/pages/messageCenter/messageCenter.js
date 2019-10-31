// pages/messageCenter/messageCenter.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid = wx.getStorageSync('yhRefOwid');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    applyOwid:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.applyOwid = options.applyOwid
    this.getResult()
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

  },
  //报名表所有查询
  getResult: function (e) {
    let that = this;
    let data = {
      applyOwid: that.data.applyOwid
    }
    common.ajax('zustswyt/bckjBizBm/getResult', data, function (res) {
      if (res.data.backCode == 0) {
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
})