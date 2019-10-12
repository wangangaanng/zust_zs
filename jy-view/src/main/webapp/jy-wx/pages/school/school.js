// pages/school/school.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content1:'',
    content2:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getContent(this,'22');
    getContent(this,'24');
  },
  clicktab: function (e) {
    // wx.pageScrollTo({
    //   scrollTop: 0
    // })
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

  }
})
var getContent = function (that,lm) {//学校简介
  var data = { "lmbh": lm, "wzzt": "1", "isDetail": "0" };
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      if(lm=='22'){
        that.setData({
          content1: res.data.bean.wznr,
        })
      }
      if (lm == '24') {
        that.setData({
          content2: res.data.bean.wznr,
        })
      }
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}