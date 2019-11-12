// pages/search1/search1.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    sfzh:'',
    ksh: ''
  },
  getsfzh(e) {
    this.setData({
      sfzh:e.detail
    })
  },
  getksh(e) {
    this.setData({
      ksh: e.detail
    })
  },
  submit1() { 
    var that = this
    if (!that.data.sfzh.trim()){
      wx.showToast({
        title: '请输入身份证号',
        icon: 'none'
      })
      return false
    }

    if (!that.data.ksh.trim()) {
      wx.showToast({
        title: '请输入准考证号',
        icon: 'none'
      })
      return false
    }

    var data = { 
      "sfzh": that.data.sfzh.trim(), 
      "ksh": that.data.ksh.trim() 
    };
    common.ajax('zustzs/bckjBizCjcx/cjcx', data, function (res) {
      if (res.data.backCode == 0) {
        var data = res.data;
        if (data.bean) {
          if (data.bean.jcsj){
            data.bean.jcsj = data.bean.jcsj.substring(0,10)
          }
          that.setData({
            result: data.bean
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

  }
})