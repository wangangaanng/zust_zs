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
    xjhList: [],
    key: '',
    type: ["招聘会","宣讲会",'职位']
  },
  positionDetail(e) {
    wx.navigateTo({
      url: '../qyInfo/qyInfo?owid=' + e.currentTarget.dataset.owid,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    myBmList(this);
  },
  onChange(e) {
    this.setData({
      key: e.detail
    });
  },
  onClear() {
    var that = this;
    refresh(this);
  },
  onSearch: function () {
    var that = this;
    refresh(this);
  },
  loadMore: function () {
    var that = this;
    if ((that.data.pageNo + 1) <= that.data.totalPage) {
      that.setData({
        pageNo: that.data.pageNo + 1,
      })
      myBmList(that);
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
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})

function refresh(that) {
  that.setData({
    xjhList: [],
    pageNo: 1,
    totalPage: ""
  })
  myBmList(that);

}

var myBmList = function (that, lx) {
  var data = {
    "zwbt": that.data.key, "yhRefOwid": wx.getStorageSync("yhOwid"), "bmlx": 1, "pageNo": that.data.pageNo, "pageSize": that.data.pageSize,
  };
  common.ajax('zustjy/bckjBizJybm/myBmList', data, function (res) {
    if (res.data.backCode == 0) {
      var xjhList;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        xjhList = that.data.xjhList.concat(res.data.bean.records)
      }

      var totalPage = res.data.bean.totalPage;
      that.setData({
        xjhList: xjhList,
        totalPage: totalPage
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