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
      url: '../zphList/zphList',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    myBmList(this);
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
      myBmList(that);
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})

var myBmList = function (that, lx) {
  var data = {
    "qyxxRefOwid": wx.getStorageSync("yhOwid"), "bmdx": 0, "bmlx": 0, "pageNo": that.data.pageNo, "pageSize": that.data.pageSize,
  };
  common.ajax('zustjy/bckjBizJybm/myBmList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      for (var i = 0; i < res.data.bean.records.length; i++) {
        var obj = {};
        var object = res.data.bean.records[i];
        obj.owid = object.owid;
        obj.date = object.createtime.substring(5, 7) + "." + object.createtime.substring(8, 10);
        obj.year = object.createtime.substring(0, 4);
        obj.zwbt = object.zwbt;
        obj.qymc = object.exp1;
        obj.city = object.zwCity;
        obj.gzxz = object.zwGzxzStr;
        arr.push(obj);
      }
      var xjhList = that.data.xjhList.concat(res.data.bean.records)
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