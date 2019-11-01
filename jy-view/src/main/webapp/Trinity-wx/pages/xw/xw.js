// pages/xw/xw.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid = wx.getStorageSync('yhRefOwid');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    value: '',
    array: [
      '招生报名',
      '大学生活',
      '校友圈',
      '专业知识',
      'dengdeng'
    ],
    index1: 0,
    ff: true,
    focus: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getLmMenu()
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
  onSearch: function(e) {
    console.log(e.detail)
  },
  click: function(e) {
    this.setData({
      index1: e.currentTarget.dataset.index1
    })
  },
  scroll: function(e) {
    this.setData({
      ff: false
    })
  },
  swiper: function(e) {
    console.log(e)
    this.setData({
      index1: e.detail.current
    })
  },
  focus: function(e) {
    this.setData({
      focus: true
    })
  },
  blur: function(e) {
    this.setData({
      focus: false
    })
  },
  // 栏目获取（单级）
  getLmMenu: function(e) {
    let that = this;
    let data = {
      wzbh: 0,
      fid: '119'
    }
    common.ajax('zustcommon/bckjDicMenu/getLmMenu', data, function(res) {
      if (res.data.backCode == 0) {
        that.setData({
          array: res.data.bean
        })
        that.getMuArticle(res.data.bean[0].CODE,0)
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  // 文章列表获取
  getMuArticle: function(e,index) {
    let that = this;
    let data = {
      pageNo: 1,
      pageSize: 8,
      isDetail: '1',
      wzzt: 1,
      lmbh: e
    }
    common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function(res) {
      if (res.data.backCode == 0) {
        let array = that.data.array;
        array[index].list = res.data.bean.records;
        that.setData({
          array
        }) 
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
})