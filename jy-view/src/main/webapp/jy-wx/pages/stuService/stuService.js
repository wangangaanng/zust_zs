// pages/stuService/stuService.js
var common = require('../../libs/common/common.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:wx.getStorageSync('stuInfo').xm || '',
    headImg:'../../static/headImg.png',
    menuList: [
      { text: '生源信息', icon: '../../../static/index-icon04.png', url: '../syxx/syxx' },
      { text: '就业方案', icon: '../../../static/qy-icon05.png', url: '../jyfa/jyfa' }, 
      { text: '办事流程', icon: '../../../static/stu-icon01.jpg', url: '../newsList/newsList?lmbh=40'}, 
      { text: '常用下载', icon: '../../../static/stu-icon02.png', url: '../newsList/newsList?lmbh=41'}, 
      { text: '档案查询', icon: '../../../static/stu-icon03.png', url: '../dangan/dangan'  }, 
      { text: '我的预约', icon: '../../../static/stu-icon04.png', url: '../wdyy/wdyy' }, 
      { text: '我的关注', icon: '../../../static/stu-icon05.png', url: '../wdgz/wdgz' }, 
      { text: '就业调查', icon: '../../../static/stu-icon06.png', url: '../inquiry/inquiry?mxdx=1' },
      { text: '专家咨询', icon: '../../../static/stu-icon07.png', url: '../jyzx/jyzx' },
      { text: '签到', icon: '../../../static/stu-icon08.png', url: '../stuQiandao/stuQiandao' }],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    isStudent(this);
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
  logout:function(){
    wx.removeStorageSync('userType')
    wx.removeStorageSync('yhOwid')
    wx.removeStorageSync('stuInfo')
    wx.reLaunch({
      url: '../index/index',
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.setData({
      name: wx.getStorageSync('stuInfo').xm || '',
      headImg: wx.getStorageSync('userInfo').avatarUrl || '',
    })
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
function isStudent(that){
  var data = {
    "xsxh": wx.getStorageSync("stuInfo").xsxh
  }
  common.ajax('zustcommon/bckjBizYhkz/judgeSetPointQualification', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = that.data.menuList;
      arr.push({ text: '采点', icon: '../../../static/stu-icon08.png', url: '../stuCaidian/stuCaidian' });
      that.setData({
        menuList:arr
      })
    }
  });
}