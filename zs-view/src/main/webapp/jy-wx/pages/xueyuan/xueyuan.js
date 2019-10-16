// pages/xueyuan/xueyuan.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getList(this);
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
var getList = function (that) {
  var data = { "lmbh": "23", "pageNo": "1", "pageSize": "20", "wzzt": "1", "isDetail": "1" };
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      for (var i = 0; i < res.data.bean.records.length; i++) {
        var obj = {};
        var object = res.data.bean.records[i];
        obj.owid = object.owid;
        obj.wzbt = object.wzbt;
        arr.push(obj);
      }
      that.setData({
        list: arr,
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