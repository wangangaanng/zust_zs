// pages/jyzx/jyzx.js
var common = require('../../libs/common/common.js')
import Dialog from 'vant-weapp/dialog/dialog';
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({
  data: {
    imgPath: imgPath,
    list: [],
    pageNo: 1,
    pageSize: 15,
    totalPage: '',
    list1: [],
    pageNo1: 1,
    totalPage1: '',
    show:false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    supervisorList(this)
    historyConsult(this)
  },

  cancel1(event) {
    console.log(event)
    this.setData({
      show: false
    });
  },
  detail(e){
    wx.navigateTo({
      url: '../zjxq/zjxq?owid=' + e.currentTarget.dataset.owid,
    })
  },
  confirm1(e){
    
    setTimeout(function(){
      console.log('333')
    },1500)
  },
  ask(e){
    // e.currentTarget.dataset.
    var that = this
    that.setData({
      show:true
    })
  },
  loadMore: function () {
    var that = this;
    if ((that.data.pageNo + 1) <= that.data.totalPage) {
      that.setData({
        pageNo: that.data.pageNo + 1,
      })
      supervisorList(that);
    }
  },
  loadMore1: function () {
    var that = this;
    if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
      that.setData({
        pageNo1: that.data.pageNo1 + 1,
      })
      historyConsult(that);
    }
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

var supervisorList = function (that) {//新闻快递轮播图
  var data = { "pageNo": that.data.pageNo, "pageSize": that.data.pageSize };
  common.ajax('zustjy/bckjBizZjzx/supervisorList', data, function (res) {
    if (res.data.backCode == 0) {
      var list;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        list = that.data.list.concat(res.data.bean.records)
      }
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

var historyConsult = function (that) {//新闻快递轮播图
  var data = { "twOwid":wx.getStorageSync('yhOwid'),"zxlx":'2', "pageNo": that.data.pageNo1, "pageSize": that.data.pageSize };
  common.ajax('zustcommon/bckjBizZxzx/historyConsult', data, function (res) {
    if (res.data.backCode == 0) {
      var list;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        list = that.data.list1.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list1: list,
        totalPage1: totalPage,
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

var consult = function (that,o) {//新闻快递轮播图
  var data = { 
    "wtnr": that.data.wtnr,
    "owid": o,
    "zxlx": 2,
    "studentOwid": wx.getStorageSync('yhOwid')
   };
  common.ajax('zustcommon/bckjBizZxzx/consult', data, function (res) {
    if (res.data.backCode == 0) {
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: "咨询成功",
        success(res) {
          if (res.confirm) {
            console.log('用户点击确定')
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
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