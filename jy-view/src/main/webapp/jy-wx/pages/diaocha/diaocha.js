// pages/diaocha/diaocha.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tips:'',
    wjmx:'',
    wjjj:'',
    list:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      ksdt: new Date()
    })
    if(options.owid){
      this.setData({
        owid: options.owid,
      })
      getContent(this, options.owid);
    }
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
  checkboxchange(e){
    for(var i=0;i<this.data.list.length;i++){
      if (this.data.list[i].owid==e.currentTarget.id){
        var da ="list["+i+"].da"
        this.setData({
          [da]: e.detail.value.join()
        })
      }
    }
  },
  inputchange(e){
    for (var i = 0; i < this.data.list.length; i++) {
      if (this.data.list[i].owid == e.currentTarget.id) {
        var da = "list[" + i + "].da"
        this.setData({
          [da]: e.detail.value
        })
      }
    }
  },
  radiochange(e){
    for (var i = 0; i < this.data.list.length;i++){
      if (this.data.list[i].owid==e.currentTarget.id){
        var da = "list[" + i + "].da"
        this.setData({
          [da]: e.detail.value
        })
      }
    }
  },
  submit() {
    var that = this;
    var arr = []
    for (var i = 0; i < this.data.list.length; i++) {
      this.data.list[i].ischeck = false
      var obj = {}
      obj.dcwjtmRefOwid = this.data.list[i].owid
      obj.wjda = this.data.list[i].da
      var ischeck = "list[" + i + "].ischeck"
      if (this.data.list[i].da == "") {
        this.setData({
          [ischeck]: true
        })
      } else {
        this.setData({
          [ischeck]: false
        })
      }
      arr.push(obj)
    }
    for (var i = 0; i < this.data.list.length; i++) {
      if (this.data.list[i].ischeck == true){
        wx.showToast({
          title: '请填写完整',
        })
        return false;
      }
      
    }
    var data = {
      dcwjRefOwid: this.data.owid,
      answerList: arr,
      "ksdt": common.formatTime(this.data.ksdt),
      "jsdt": common.formatTime(new Date()),
      "dtrId": wx.getStorageSync("yhOwid"),
    };
    ajax('zustcommon/bckjBizDcwj/submit', data, function (res) {
      if (res.data.backCode == 0) {
        wx.showToast({
          title: '提交成功',
        })

      } else {
        wx.showToast({
          title: res.data.errorMess,
          icon: 'none',
          duration: 2000
        })
      }
    })

  }
})
var getContent = function (that, owid) {
  var data = { "dcwjRefOwid": owid, "yhOwid": wx.getStorageSync("yhOwid"), "wzbh": "1" };
  common.ajax('zustcommon/bckjBizDcwj/dcwjDetail', data, function (res) {
    if (res.data.backCode == 0) {
      var list = res.data.bean.questionList
      
      var arr = [];
      for (var i = 0; i < list.length; i++) {
        var obj = {}
        obj.owid = list[i].owid
        obj.xxList = list[i].xxList
        obj.tmlx = list[i].tmlx
        obj.tmmc = list[i].tmmc
        obj.tmsm = list[i].tmsm
        obj.da = ""
        obj.ischeck = false
        arr.push(obj)
      }
      if (res.data.bean.tips){
        wx.showModal({
          title: '提示',
          content: res.data.bean.tips,
          showCancel:false,
          success(res) {
            
          }
        })
      }
      that.setData({
        tips: res.data.bean.tips,
        wjmc: res.data.bean.wjmc,
        wjjj: res.data.bean.wjjj,
        list: arr
      })
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}