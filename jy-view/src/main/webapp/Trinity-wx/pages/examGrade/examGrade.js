// pages/examGrade/examGrade.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
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
  showSubject(e) {
    console.log(e)
    let that = this;
    let type = e.target.dataset.type;
    let value = e.target.dataset.value;
    let index = Number(e.target.dataset.index);
    // let gradeCategory = [];
    // let defaultIndex = 0;
    if (type == "0") {
      let km = that.data.km
      km[index].value = value
      that.setData({
        km: km
      })
    }else if (type == "1") {
      let xm = that.data.xm
      xm[index].value = value
      that.setData({
        xm: xm
      })
    }
  },
  //科目字典表
  getByType: function(dicType) {
    let that = this;
    let data = {
      dicType: dicType,
    }
    common.ajax('zustcommon/common/getByType', data, function(res) {
      if (res.data.backCode == 0) {
        let map = res.data.bean
        for (let i in map) {
          map[i].map = map[i].dicVal3.split(',')
        }
        
        if (dicType == '10022') {
          that.setData({
            km: map
          })
          that.getHkcj('0')
        } else if (dicType == '10023') {
          that.setData({
            xm: map
          })
          that.getHkcj('2')
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  //完善学考等第
  Step: function (e) {
    let currstep = e.currentTarget.dataset.index
    let that = this;
    let hkList = [], zhList=[];
    for (let i in that.data.km){
      let current = that.data.km[i];
      if (!!current.value){
        let obj = {
          kmbh: current.dicVal1,
          kmdj: current.value,
          xssx: current.dicVal4,
          kmmc: current.dicVal2,
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
          xssx: current.dicVal4,
          kmmc: current.dicVal2,
        }
        zhList.push(obj)
      } else {
        common.toast(current.dicVal2 + '尚未选择', 'none', 2000)
        return
      }
    }
    let data = {
      hkList: hkList,
      yhRefOwid: wx.getStorageSync('yhRefOwid'),
      zhList: zhList
    }
    common.ajax('zustswyt/bckjBizCjxx/finishHk', data, function (res) {
      if (res.data.backCode == 0) {
        if (currstep=='0'){
          wx.redirectTo({
            url: '../contactors/contactors',
          })
        }else{
          wx.redirectTo({
            url: '../selectExamInfo/selectExamInfo',
          })
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  //获取学考等第
  getHkcj: function (lx) {
    let that = this;    
    let data = {
      yhRefOwid: wx.getStorageSync('yhRefOwid'),
      lx: lx
    }
    common.ajax('zustswyt/bckjBizCjxx/getHkcj', data, function (res) {
      if (res.data.backCode == 0) {
        if (lx=='0'){
          let km = that.data.km;
          for(let i in res.data.bean){
            if (km[i].dicVal1 == res.data.bean[i].kmbh)
              km[i].value = res.data.bean[i].kmdj
          }
          that.setData({
            km
          })
        }
        if (lx == '2') {
          let xm = that.data.xm;
          for (let i in res.data.bean) {
            if (xm[i].dicVal1 == res.data.bean[i].kmbh)
              xm[i].value = res.data.bean[i].kmdj
          }
          that.setData({
            xm
          })
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  }  
})