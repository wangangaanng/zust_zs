// pages/xw/xw.js
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
    focus:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

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
  focus:function(e){
    this.setData({
      focus:true
    })
  },
  blur:function(e){
    this.setData({
      focus: false
    })
  }
})