// pages/wxDetail/wxDetail.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath: common.imgPath,
    owid:'',
    data:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      owid: options.owid
    })
    this.getOne();
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
  // 文章详情
  getOne: function (e) {
    let that = this;
    let data = {
      owid: this.data.owid
    }
    common.ajax('zustcommon/bckjBizArticle/getOne', data, function (res) {
      if (res.data.backCode == 0) {
        that.setData({
          data:res.data.bean
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
})