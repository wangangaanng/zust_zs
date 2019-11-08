// pages/lianxiwm/lianxiwm.js
var common = require('../../libs/common/common.js')
var WxParse = require('../../libs/wxParse/wxParse.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    result:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getContent(this);
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
var getContent = function (that, owid) {//招聘详情
  var data = { "lmbh": "43", "wzzt": "1", "isDetail": "0" };
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      WxParse.wxParse('template', 'html', res.data.bean.wznr, that, 5);
      that.setData({
        result: res.data.bean,
      })

    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}