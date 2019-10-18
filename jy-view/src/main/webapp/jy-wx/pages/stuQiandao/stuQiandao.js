// pages/qiandaolb/qiandaolb.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageSize: 20,
    pageNo: 1,
    totalPage: '',
    list: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getList(this, 1);// pageNo
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

  loadMore: function () {
    var that = this;
    if ((that.data.pageNo + 1) <= that.data.totalPage) {
      that.setData({
        pageNo: that.data.pageNo + 1,
      })
      getList(that, that.data.mxdx, that.data.pageNo);
    }
  },
  qiandao: function (e) {
    wx.navigateTo({
      url: '../qiandao/qiandao?owid=' + e.currentTarget.dataset.owid,
    })
  },
})
var getList = function (that, pageNo) {
  var data = {
     "pageNo": pageNo, "pageSize": that.data.pageSize,
  };
  common.ajax('zustjy/bckjBizJob/getQdList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      for (var i = 0; i < res.data.bean.records.length; i++) {
        var obj = {};
        var object = res.data.bean.records[i];
        obj.owid = object.owid;
        obj.zwbt = object.zwbt;
        obj.zphJbdd = object.zphJbdd;
        if (object.zphKsrq) {
          obj.zphKsrq = object.zphKsrq.substring(0, 10)
        }
        obj.zphJtsj = object.zphJtsj;
        obj.zwlx = object.zwlx;
        arr.push(obj);
      }
      var list = that.data.list.concat(arr)
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list: list,
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