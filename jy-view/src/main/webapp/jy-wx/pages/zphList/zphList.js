// pages/enterpriseXjh/enterpriseXjh.js
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageSize: 20,
    pageNo: 1,
    totalPage: '',
    xjhList: []
  },
  shenqin() {
    wx.navigateTo({
      url: '../applyXjh/applyXjh',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    myJobList(this);
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
    if ((that.data.pageNo + 1) <= that.data.totalPage) {
      that.setData({
        pageNo: that.data.pageNo + 1,
      })
      myJobList(that);
    }
  },
  order(e) {
    wx.navigateTo({
      url: '../ydzw/ydzw?owid='+e.currentTarget.dataset.owid,
    })
  }
})

var myJobList = function (that, lx) {
  var data = {
    "zwlx": 3, "zphSfbm": 1, "pageNo": that.data.pageNo, "pageSize": that.data.pageSize,
  };
  common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
    if (res.data.backCode == 0) {
      var xjhList;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        xjhList = that.data.xjhList.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        xjhList: xjhList,
        totalPage: totalPage,
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