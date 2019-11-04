// pages/contactors/contactors.js
var common = require('../../libs/common/common.js');
import WxValidate from '../../utils/WxValidate'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    teacher:{
      sex:"1"
    },
    detailList:[],//联系人信息
    errorList: '', //错误信息重组
  },
  //点击想下一步保存联系人
  contactForm: function(e) {
    const params = e.detail.value;
    console.log(params);
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      common.errorHash(this.WxValidate.errorList, this);
      return false
    } else {this.setData({errorList: {}});}
    var dataArr  = [];
    dataArr[0] = {//父亲
      "cylb":0
      ,"xm": params.faName
      ,"xb":1
      , "whcd": params.faEdu
      , "gzdw": params.faCom
      , "gzzw": params.faJob
      , "lxsj": params.faTel
      , "dwdh": params.faComTel
    }
    dataArr[1] = {//母亲
      "cylb": 1
      , "xm": params.moName
      , "xb": 2
      , "whcd": params.moEdu
      , "gzdw": params.moCom
      , "gzzw": params.moJob
      , "lxsj": params.moTel
      , "dwdh": params.moComTel
    }
    dataArr[2] = {//高中老师
      "cylb": 2
      , "xm": params.teName
      , "xb": params.teSex
      , "whcd": params.teEdu
      , "gzdw": params.teCom
      , "gzzw": params.teJob
      , "lxsj": params.teTel
      , "dwdh": params.teComTel
    }
    var data = {
      "dataList": dataArr,
      "yhRefOwid": wx.getStorageSync('yhRefOwid')
    } 
    common.ajax('zustswyt/bckjBizJtcyxx/finish', data, function(res) {
      if (res.data.backCode == 0) {
        wx.navigateTo({
          url: '../examGrade/examGrade',
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.initValidate();
    getContactor(this);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  //上一步基本信息
  preStep: function() {
    wx.navigateTo({
      url: '../basicInfo/basicInfo',
    })
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      faName: {
        required: true,
      },
      faTel: {
        required: true,
        tel: true,
      },
      moName: {
        required: true,
      },
      moTel: {
        required: true,
        tel: true,
      },
      teName: {
        required: true,
      },
      teTel: {
        required: true,
        tel: true,
      }
    }
    this.WxValidate = new WxValidate(rules, '')
  },
  //单选框 选择性别
  sexSelect(event) {
    this.setData({
      ['teacher.xb']: event.detail
    });
  },
})

//获取联系人信息
function getContactor(that) {
  var data = {
    "yhRefOwid": wx.getStorageSync('yhRefOwid')
  }
  common.ajax('zustswyt/bckjBizJtcyxx/getInfo', data, function (res) {
    if (res.data.backCode == 0) {
      var list = res.data.bean;
      if (list&&list.length>0){
        for (var i = 0; i < list.length; i++) {
          switch (list[i].cylb) {
            case 0:
              that.data.father = list[i];
              break;
            case 1:
              that.data.mother = list[i];
              break;
            case 2:
              list[i].xb = list[i].xb.toString();
              that.data.teacher = list[i];
              break;
          }
        }
        that.setData({
          father: that.data.father,
          mother: that.data.mother,
          teacher: that.data.teacher
        });
      }
    } else {
      common.toast(res.data.errorMess, 'none', 2000)
    }
  })
}