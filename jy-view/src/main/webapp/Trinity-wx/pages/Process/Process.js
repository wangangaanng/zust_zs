// pages/Process/Process.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid = wx.getStorageSync('yhRefOwid');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userName: '',
    bmState: '',
    // eqindex: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // this.data.eqindex = options.index
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this;
    that.getXxxx();
    that.indexState();

    if (wx.getStorageSync('userName')) {
      that.setData({
        'userName': that.data.userName
      })
    } else {
      common.getInfoBasic(that);
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  //学校信息获取
  getXxxx: function(e) {
    let that = this;
    let data = {
      pageNo: 1,
      pageSize: 1,
      yhRefOwid: wx.getStorageSync('yhRefOwid'),
    }
    common.ajax('zustswyt/bckjBizXxpz/getXxxx', data, function(res) {
      if (res.data.backCode == 0) {
        // let eqindex = that.data.eqindex
        for (let i in res.data.bean.list) {
          if (wx.getStorageSync('xxbh') == res.data.bean.list[i].xxbh) {
            wx.setStorageSync('xxbh', res.data.bean.list[i].xxbh)
            wx.setStorageSync('applyOwid', res.data.bean.list[i].applyOwid)
            that.setData({
              bmState: res.data.bean.list[i].bmState,
              res: res.data.bean.list[i]
            })
          }
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  // 首页判断基本信息、会考等填写状态
  indexState: function(e) {
    let that = this;
    let data = {
      yhRefOwid: wx.getStorageSync('yhRefOwid')
    }
    common.ajax('zustswyt/bckjBizJbxx/indexState', data, function(res) {
      if (res.data.backCode == 0) {
        let state = 0;
        let bean = res.data.bean;
        if (bean.state == 1 && bean.xkState == 1 && bean.hkState == 1 && bean.jtcyState == 1) {
          state = 1
        }
        that.setData({
          state: state
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
})