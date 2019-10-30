// pages/shouye/shouye.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid = wx.getStorageSync('yhRefOwid');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    res: {},
    state: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.historyMessage()
    this.getXxxx()
    this.indexState()
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
      yhRefOwid: yhRefOwid,
    }
    common.ajax('zustswyt/bckjBizXxpz/getXxxx', data, function(res) {
      if (res.data.backCode == 0) {
        that.setData({
          res: res.data.bean.list[0]
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  //学校信息获取
  getXxxx: function(e) {
    let that = this;
    let data = {
      pageNo: 1,
      pageSize: 1,
      yhRefOwid: yhRefOwid,
    }
    common.ajax('zustswyt/bckjBizXxpz/getXxxx', data, function(res) {
      if (res.data.backCode == 0) {
        that.setData({
          res: res.data.bean.list[0]
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  // 底部问答列表
  historyMessage: function(e) {
    let that = this;
    let data = {
      pageNo: 1,
      pageSize: 8,
      zxlx: '1'
    }
    common.ajax('zustcommon/bckjBizZxzx/historyMessage', data, function(res) {
      if (res.data.backCode == 0) {
        // that.setData({
        //   historyMessage: res.data.bean.list[0]
        // })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  // 首页判断基本信息、会考等填写状态
  indexState: function(e) {
    let that = this;
    let data = {
      yhRefOwid: yhRefOwid
    }
    common.ajax('zustswyt/bckjBizJbxx/indexState', data, function(res) {
      if (res.data.backCode == 0) {
        that.setData({
          state: res.data.bean
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
})