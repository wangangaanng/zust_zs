// pages/drawGroup/drawGroup.js
var common = require('../../libs/common/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username:'张小凡',
    subject:'普通类',
    language:'英语',
    type:'综合类',
    major: '计算机科学与技术',
    number: '20191019',
    result: ''
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
    common.getProcssState(function(res){

    });
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


// function getProcssState(){
//   var data = {
//     "applyOwid": wx.getStorageSync('sqbOwid'),
//   }
//   ajax('zustswyt/bckjBizBm/getResult', data, function (res) {
//     if (res.data.backCode == 0) {

//     } else {
//       common.toast(res.data.errorMess, 'none', 2000)
//     }
//   });
// }