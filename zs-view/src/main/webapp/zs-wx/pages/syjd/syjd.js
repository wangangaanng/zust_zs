// pages/syjd/syjd.js
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath: imgPath,
    pageSize: 20,
    pageNo1: 1,
    totalPage1: '',
    newsList1: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getList1(this)
  },
  detail(e) {
    wx.navigateTo({
      url: '../newsDetail/newsDetail?owid=' + e.currentTarget.dataset.owid,
    })
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
    var that = this;
    if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
      that.setData({
        pageNo1: that.data.pageNo1 + 1,
      })
      getList1(that);
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})


var getList1 = function (that) {
  var data = { "lmbh": '126', "pageNo": that.data.pageNo1, "pageSize": "20", "wzzt": "1", "isDetail": '1' };//isDetail:1列表
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    
    if (res.data.backCode == 0) {
      var totalPage1 = res.data.bean.totalPage;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        var newsList1 = that.data.newsList1.concat(res.data.bean.records)
        that.setData({
          newsList1: newsList1,
          totalPage1: totalPage1,
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