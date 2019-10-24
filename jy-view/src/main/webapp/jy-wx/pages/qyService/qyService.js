// pages/qyService/qyService.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    qymc:'',
    menuList: [
      { text: '企业信息', icon: '../../../static/qy-icon01.png', url: '../qyxx/qyxx' }, 
      { text: '新增职位', icon: '../../../static/qy-icon02.png', url: '../newJob/newJob' }, 
      { text: '宣讲会申请', icon: '../../../static/qy-icon03.png', url: '../enterpriseXjh/enterpriseXjh' }, 
      { text: '招聘会申请', icon: '../../../static/qy-icon04.png', url: '../enterpriseZph/enterpriseZph' }, 
      { text: '职位列表', icon: '../../../static/qy-icon05.png', url: '../enterpriseZw/enterpriseZw' }, 
      { text: '企业指南', icon: '../../../static/qy-icon06.png', url: '../qyzn/qyzn' },
      { text: '就业调查', icon: '../../../static/stu-icon04.png', url: '../inquiry/inquiry?mxdx=0' },
    ],
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (wx.getStorageSync('qyInfo').qymc){
      this.setData({
        qymc: wx.getStorageSync('qyInfo').qymc
      })
    }
    
  },
  linkurl: function (e) {
    var url = e.currentTarget.dataset.url;
    if (url) {
      wx.navigateTo({
        url: url,
      })
    }
  },
  logout: function () {
    wx.removeStorageSync('userType')
    wx.removeStorageSync('yhOwid')
    wx.removeStorageSync('qyInfo')
    wx.reLaunch({
      url: '../index/index',
    })
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