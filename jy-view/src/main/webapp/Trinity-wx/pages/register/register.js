// pages/register/register.js
var common = require('../../libs/common/common.js');
var utils = require('../../utils/util.js');
import WxValidate from '../../utils/WxValidate';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    form:{
      tel:"",//手机号
      code:"",//验证码
      name:"",//真实姓名
      sex:"1",//性别
      psw:"",//登录密码
      surePsw:"",//确认密码
      studyArea:"",//学籍地址
      major:"",//倾向专业
      prov:'',//学籍省
      city: '',//学籍市
      area: ''//学籍区
    },
    loginPswType:true,//密码
    surePswType: true,//确认密码
    areaList: utils.areaList,//全国省市区初始化数据
    areaShowBool:false,//显示pop
    time: '获取验证码', //倒计时
    disabled: false,//获取验证码disabled
  },

  //注册表单提交
  formRegister:function(e){
    const params = e.detail.value;
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      common.toast(error.msg, 'none', 2000)
      return false
    }
    var data = {
      "swZh": params.tel
      , "xm": params.name
      , "xb": params.sex
      , "yzm": params.code
      , "swMm": params.surePsw
      , "prov": params.prov
      , "city": params.city
      , "area": params.area
      , "qxzy": params.major
    }
    common.ajax('zustcommon/bckjBizYhxx/swYtzc', data, function (res) {
      if (res.data.backCode == 0) {
        wx.setStorageSync('mobile', params.tel);
        wx.reLaunch({
          url: '../login/login',
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },


  onLoad: function (options) {
    this.initValidate();
  },

  //获取验证码
  sendCode(){
    common.getCode(this.data.form['tel'], 0, this);
  },

  //单选框 选择性别
  sexSelect(event) {
    console.log(event.detail);
    this.setData({
      ["form.sex"]: event.detail
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
    var that = this;
    const { picker, values, index } = event.detail;
    for (var i = 0; i < values.length; i++) {
      console.log(i);
      switch (i){
        case 0: //省
          that.data.form['prov'] = values[i].name;
          break;
        case 1: //市
          that.data.form['city'] = values[i].name;
          break; 
        case 2: //区
          that.data.form['area'] = values[i].name;
          break;    
      }
      areaName = areaName + values[i].name + ' '
    }
    this.setData({ 
      areaShowBool: false,
      ["form.studyArea"]: areaName,
      ["form.prov"]: that.data.form['prov'],
      ["form.city"]: that.data.form['city'],
      ["form.area"]: that.data.form['area']
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
      , name: {
        required: true
      }
      , sex: {
        required: true
      }
      , psw: {
        required: true,
        rangelength:[6,16],
        numLetter: true
      }
      , surePsw: {
        required: true,
        equalTo: 'psw',
        numLetter:true
      }
      , studyArea: {
        required: true
      }
      // , major: {
      //   required: true
      // }
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
      , name: {
        required: '请填写真实姓名',
      }
      , sex: {
        required: '请选择性别',
      }
      , psw: {
        required: '请设置登录密码',
        numLetter:'请输入字母和数字的组合'
      }
      , surePsw: {
        required: '请填写确认密码',
        equalTo: '两次密码输入不一致',
        numLetter: '请输入字母和数字的组合'
      }
      , studyArea: {
        required: '请选择学籍地址',
      }
      // , major: {
      //   required: '请填写倾向专业',
      // }
    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  //显示登陆密码
  showPswType() {
    (this.data.loginPswType) ? this.data.loginPswType = false : this.data.loginPswType = true;
    this.setData({
      loginPswType: this.data.loginPswType
    });
  },
  //显示确认密码
  surePswType() {
    (this.data.surePswType) ? this.data.surePswType = false : this.data.surePswType = true;
    this.setData({
      surePswType: this.data.surePswType
    });
  },
  //获取手机号码
  telInput(e){
    this.setData({
      ["form.tel"]: e.detail
    });
  }
})