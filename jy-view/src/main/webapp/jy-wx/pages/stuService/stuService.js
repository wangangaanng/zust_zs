// pages/stuService/stuService.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    menuList: [
      { text: '办事流程', icon: '../../../static/stu-icon01.png' }, 
      { text: '常用下载', icon: '../../../static/stu-icon02.png' }, 
      { text: '档案查询', icon: '../../../static/stu-icon03.png', url: '../dangan/dangan'  }, 
      { text: '我的预约', icon: '../../../static/stu-icon04.png' }, 
      { text: '我的关注', icon: '../../../static/stu-icon05.png' }, 
      { text: '就业调查', icon: '../../../static/stu-icon06.png', url: '../diaocha/diaocha' }, 
      { text: '专家咨询', icon: '../../../static/stu-icon07.png' }, 
      { text: '签到', icon: '../../../static/stu-icon08.png', url: '../stuQiandao/stuQiandao' }],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  linkurl: function (e) {
    var url = e.currentTarget.dataset.url;
    if (url) {
      wx.navigateTo({
        url: url,
      })
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