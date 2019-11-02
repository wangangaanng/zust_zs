// pages/register/register.js
var common = require('../../libs/common/common.js');
import WxValidate from '../../utils/WxValidate';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //显示隐藏密码
    loginPswType:true,
    surePswType: true,
    time: '获取验证码', //倒计时
    tel:'',
    disabled:false
  },
  //点击获取验证码
  sendCode() {
    common.getCode(this.data.tel,1,this);
  },
  changePswForm(e){
    const params = e.detail.value;
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      common.toast(error.msg, 'none', 2000)
      return false
    }
    var data = {
      "swMm": params.surePsw
      , "swZh": params.tel
      , "yzm": params.code
    }
    common.ajax('zustcommon/bckjBizYhxx/forgetPwd', data, function (res) {
      if (res.data.backCode == 0) {
        wx.setStorageSync('account', params.tel);
        wx.setStorageSync('hasLogin', '0');
        common.toast('密码设置成功', 'none', 1500);
        setTimeout(function () {
          wx.navigateTo({
            url: '../login/login',
          });
        }, 2000);
      } else {
        common.toast(res.data.errorMess, 'none', 1500)
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initValidate();
    var currAccount = wx.getStorageSync("mobile");
    this.setData({
      tel: currAccount,
    });
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
  //获取手机号码
  telBlur(e) {
    this.setData({
      tel: e.detail.value
    });
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      tel: {
        required: true,
        tel: true,
      }
      , code: {
        required: true
      }
      , psw: {
        required: true,
        rangelength: [6, 16],
        numLetter: true
      }
      , surePsw: {
        required: true,
        equalTo: 'psw',
        numLetter: true
      }
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      tel: {
        required: '请填写手机号',
        tel: '请填写正确手机号',
      }
      , code: {
        required: '请填写验证码',
      }
      , psw: {
        required: '请设置登录密码',
        numLetter: '请输入字母和数字的组合'
      }
      , surePsw: {
        required: '请填写确认密码',
        equalTo: '两次密码输入不一致',
        numLetter: '请输入字母和数字的组合'
      }
    }
    this.WxValidate = new WxValidate(rules, messages)
  }
})