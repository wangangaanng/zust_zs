// pages/search/search.js
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
    menuList: [
      { text: '成绩查询', icon: '../../../static/cx-icon05.png', url: '../search1/search1' },
      { text: '录取查询', icon: '../../../static/cx-icon02.png', url: '../search2/search2' },
      { text: '历年分数/名次', icon: '../../../static/cx-icon03.png', url: '../lnfs/lnfs' },
      { text: '招生计划查询', icon: '../../../static/cx-icon04.png', url: '../jhcx/jhcx' },
      // { text: '录取通知书查询', icon: '../../../static/cx-icon01.png', url: '../cxzx/cxzx' }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
