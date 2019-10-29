// pages/examGrade/examGrade.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid = app.globalData.yhRefOwid;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showPop: false,
    km: [],
    xm: [],
    gradeCategory: [],
    type: '',
    index: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getByType('10022'); //获取科目字典表内容
    this.getByType('10023'); //获取综合测评项目字典表内容
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  confirmGrade(e) {
    console.log(e)
    if (this.data.type == "0") {
      let km = this.data.km;
      km[this.data.index].value = e.detail.value
      this.setData({
        km,
        showPop: false
      })
    } else if (this.data.type == "1") {
      let xm = this.data.xm;
      xm[this.data.index].value = e.detail.value
      this.setData({
        xm,
        showPop: false
      })
    }
  },
  cancelPop() {
    this.setData({
      showPop: false
    });
  },
  showSubject(e) {
    let that = this;
    let type = e.target.dataset.type;
    let index = Number(e.target.dataset.index);
    let gradeCategory = [];
    if (type == "0") {
      gradeCategory = this.data.km[index].dicVal3.split(',')
    }else if (type == "1") {
      gradeCategory = this.data.xm[index].dicVal3.split(',')
    }
    this.setData({
      showPop: true,
      gradeCategory,
      type,
      index
    });
  },
  //上一步基本信息
  preStep: function() {
    wx.navigateTo({
      url: '../basicInfo/basicInfo',
    })
  },
  //科目字典表
  getByType: function(dicType) {
    let that = this;
    let data = {
      dicType: dicType,
    }
    common.ajax('zustcommon/common/getByType', data, function(res) {
      if (res.data.backCode == 0) {
        if (dicType == '10022') {
          that.setData({
            km: res.data.bean
          })
        } else if (dicType == '10023') {
          that.setData({
            xm: res.data.bean
          })
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  //完善学考等第
  nextStep: function () {
    let that = this;
    let hkList = [], zhList=[];
    for (let i in that.data.km){
      let current = that.data.km[i];
      if (!!current.value){
        let obj = {
          kmbh: current.dicVal1,
          kmdj: current.value,
          kmbh: current.dicVal4,
        }
        hkList.push(obj)
      }else{
        common.toast(current.dicVal2+'尚未选择', 'none', 2000)
        return
      }
    }
    for (let i in that.data.xm) {
      let current = that.data.xm[i];
      if (!!current.value) {
        let obj = {
          kmbh: current.dicVal1,
          kmdj: current.value,
          kmbh: current.dicVal4,
        }
        zhList.push(obj)
      } else {
        common.toast(current.dicVal2 + '尚未选择', 'none', 2000)
        return
      }
    }
    let data = {
      hkList: hkList,
      yhRefOwid: yhRefOwid,
      zhList: zhList
    }
    common.ajax('zustswyt/bckjBizCjxx/finishHk', data, function (res) {
      if (res.data.backCode == 0) {
        wx.navigateTo({
          url: '../selectExamInfo/selectExamInfo',
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
})