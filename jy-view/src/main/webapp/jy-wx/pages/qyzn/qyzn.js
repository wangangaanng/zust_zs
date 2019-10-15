// pages/school/school.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab:0,
    content1: '',
    key: '',
    wzList: [],
    pageSize: 20,
    pageNo: 1,
    totalPage: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getContent(this, '38');
    getList(this, '39');
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
    if (this.data.currentTab == 1) {
      if ((that.data.pageNo + 1) <= that.data.totalPage) {
        that.setData({
          pageNo: that.data.pageNo + 1,
        })
        getList(that, '39');
      }
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }, 
  newsDetail(e) {
    wx.navigateTo({
      url: '../newsDetail/newsDetail?owid=' + e.currentTarget.dataset.owid,
    })
  },
  onChange(e) {
    this.setData({
      key: e.detail
    });
  },

  clickTab: function (e) {
    this.setData({
      currentTab: e.detail.index
    })
  },
  onClear() {
    var that = this;
    refresh(this);
  },
  onSearch: function () {
    var that = this;
    refresh(this);
  },
  
})
var getContent = function (that, lm) {//学校简介
  var data = { "lmbh": lm, "wzzt": "1", "isDetail": "0" };
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      if (lm == 38) {
        that.setData({
          content1: res.data.bean.wznr,
        })
      }
      if (lm == '29') {
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

var getList = function (that,owid) {
  var data = { "lmbh": owid, "pageNo": that.data.pageNo, "pageSize": that.data.pageSize, "wzzt": "1", "isDetail": '1', "gjz": that.data.key };//isDetail:1列表
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      if (res.data.bean.records) {
        for (var i = 0; i < res.data.bean.records.length; i++) {
          var obj = {};
          var object = res.data.bean.records[i];
          if (object.fbsj) {
            object.date = object.fbsj.substring(5, 7) + "." + object.fbsj.substring(8, 10);
            object.year = object.fbsj.substring(0, 4);
          }
          arr.push(object);
        }
      }
      var wzList = that.data.wzList.concat(arr)
      var totalPage = res.data.bean.totalPage;
      that.setData({
        wzList: wzList,
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
function refresh(that) {
  that.setData({
    wzList: [],
    pageNo: 1, 
    totalPage : ""
  })
  getList(that, '39');
  
}