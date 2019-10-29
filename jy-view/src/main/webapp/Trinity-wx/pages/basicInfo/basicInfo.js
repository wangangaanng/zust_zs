// pages/basicInfo/basicInfo.js
var common = require('../../libs/common/common.js');
import WxValidate from '../../utils/WxValidate'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentDate: new Date().getTime(),
    name:'',//姓名
    idcard:'',//身份证号
    sex: '1',//性别
    race:'',//名族
    address:'',//通讯地址
    phone:'',//联系电话
    tel:'',//手机
    email:'',//电子邮箱
    qq:'',//qq号
    errorList:[]
  },

  //完善基本信息
  basicForm(e){
    const params = e.detail.value;
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      common.errorHash(this.WxValidate.errorList,this);
      return false
    }
    var data = {
      "xm": params.tel
      ,"sfzh": params.name
      ,"mz": params.sex
      ,"xb": params.code
      , "jtzz": params.surePsw //家庭住址
      , "sfzzm": params.prov //身份证正面
      , "sfzfm": params.city//身份证反面
      , "hjzm": params.area//户籍地址
      , "yx": params.major//邮箱
      , "qq": params.major
      , "lxdh": params.major//联系电话
      , "yhRefOwid": params.major//用户owid
    }
    common.ajax('zustswyt/bckjBizJbxx/finishInfo', data, function (res) {
      if (res.data.backCode == 0) {
        wx.navigateTo({
          url: '../contactors/contactors',
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initValidate();
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
  //单选框 选择性别
  sexSelect(event) {
    this.setData({
      sex: event.detail
    });
  },
  //下一步 完善联系人
  nextStep:function(){
    wx.navigateTo({
      url: '../contactors/contactors',
    })
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      name: {
        required: true,
      }
      , idcard: {
        required: true,
        idcard: true
      }
      , sex: {
        required: true
      }
      , race: {
        required: true
      }
      , address: {
        required: true
      }
      , phone: {//联系电话
        required: true
      }
      , tel: {//手机
        required: true,
        tel: true,
      }
      , email: {
        required: true,
        email: true
      }
      , qq: {
        required: true
      }
    }
    this.WxValidate = new WxValidate(rules, '')
  }
})