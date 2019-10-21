// pages/register/register.js
var utils = require('../../utils/util.js');
import WxValidate from '../../utils/WxValidate'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    loginPswType:true,
    surePswType: true,
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

  },
  //显示登陆密码
  showPswType(){
    (this.data.loginPswType) ? this.data.loginPswType=false : this.data.loginPswType =true;
    this.setData({
      loginPswType: this.data.loginPswType
    });
  },
  //显示确认密码
  surePswType(){
    (this.data.surePswType) ? this.data.surePswType = false : this.data.surePswType = true;
    this.setData({
      surePswType: this.data.surePswType
    });
  },
  //获取验证码
  sendCode(){
    console.log('获取验证码');
  }
})