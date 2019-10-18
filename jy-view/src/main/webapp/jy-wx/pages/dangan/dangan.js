// pages/dangan/dangan.js
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    xsxm:'',
    sfzh:'',
    isSearch:false,
    result:''
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
  nameInput:function(e){
    this.setData({
      xsxm: e.detail,
    })
  },
  numberInput:function(e){
    this.setData({
      sfzh: e.detail,
    })
  },
  search:function(){
    if (!this.data.xsxm) {
      wx.showToast({
        icon: 'none',
        title: '请输入学生姓名',
      })
      return false
    } else if (!this.data.sfzh) {
      wx.showToast({
        icon: 'none',
        title: '请输入身份证号码',
      })
      return false
    }
    var data = {
      "xsxm": this.data.xsxm,
      "sfz": this.data.sfzh
    }
    var that=this;
    common.ajax('zustcommon/bckjBizJyscheme/queryDocument', data, function (res) {
      if (res.data.backCode == 0) {
        if (res.data.bean){
          res.data.bean.csrq = res.data.bean.sfz.substring(6, 10) + '年' + res.data.bean.sfz.substring(10, 12) + '月' + res.data.bean.sfz.substring(12, 14) + '日'
          that.setData({
            isSearch: true,
            result: res.data.bean
          })
        }

      } else {
        wx.showToast({
          title: res.data.errorMess,
          icon: 'none',
          duration: 2000
        })
      }
    });
  }
})