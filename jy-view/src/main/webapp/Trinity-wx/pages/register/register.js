// pages/register/register.js
var utils = require('../../utils/util.js');
import WxValidate from '../../utils/WxValidate'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    loginPswType:'password',
    surePswType: 'password',
    sexRadio: '2',//性别
    areaList: utils.areaList,//全国省市区
    areaShowBool:false,//显示pop
    studyArea:"",//学籍地址
    majorShowBool:false,
    majorList: ['杭州', '宁波', '温州', '嘉兴', '湖州'],//倾向专业
    wantMajor:""
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
    (this.data.loginPswType == 'password') ? this.data.loginPswType="text" : this.data.loginPswType ="password";
    this.setData({
      loginPswType: this.data.loginPswType
    });
  },
  //显示确认密码
  surePswType(){
    (this.data.surePswType == 'password') ? this.data.surePswType = "text" : this.data.surePswType = "password";
    this.setData({
      surePswType: this.data.surePswType
    });
  },
  //获取验证码
  sendCode(){
    console.log('获取验证码');
  },
  //单选框 选择性别
  sexSelect(event) {
    console.log(event.detail);
    this.setData({
      sexRadio: event.detail
    });
  },
  //显示省市区
  showArea() {
    this.setData({ areaShowBool: true });
  },
  //关闭pop
  onClose() {
    this.setData({ 
      areaShowBool: false,
      majorShowBool:false
    });
  },
  //取消地区选择
  cancelArea(){
    this.setData({ areaShowBool: false });
  },
  //确认选择地区
  confirmArea(event) {
    let areaName = "";
    const { picker, values, index } = event.detail;
    for (var i = 0; i < values.length; i++) {
      areaName = areaName + values[i].name + ' '
    }
    this.setData({ 
      areaShowBool: false,
      studyArea: areaName
    });
  },
  //地区选择
  onChangeArea(event) {
    let areaName = "";
    const { picker, values, index } = event.detail;
    for (var i = 0; i < values.length; i++) {
      areaName = areaName + values[i].name + ' '
    }
    this.setData({ studyArea: areaName });
  },
  //显示专业
  showMajor(){
    this.setData({ majorShowBool: true });
  },
  //隐藏专业
  cancelMajor(){
    this.setData({ majorShowBool: false });
  },
  //选择专业
  onChangeMajor(event){
    const { picker, value, index } = event.detail; 
    this.setData({ wantMajor:value});
    //console.log(`当前值：${value}, 当前索引：${index}`);
  },
  //确定选择
  confirmMajor(event){
    const { picker, value, index } = event.detail; 
    this.setData({ majorShowBool: false, wantMajor: value });
  }
})