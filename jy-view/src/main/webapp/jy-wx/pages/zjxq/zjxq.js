// pages/zjxq/zjxq.js
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath: imgPath,
    modal1: false,
    wtnr: '',
  },
  cancel: function () {
    this.setData({
      modal1: false,
      wtnr: ''
    });
  },
  ask: function (e) {
    this.setData({
      tOwid: e.currentTarget.dataset.owid,
      modal1: true
    });
  },
  getWtnr(e) {
    this.setData({
      wtnr: e.detail.value
    })
  },
  //确认
  confirm: function () {
    var that = this
    if (!that.data.wtnr.trim()) {
      wx.showToast({
        icon: 'none',
        title: '请填写内容',
      })
      return false
    }
    var data = {
      "wtnr": that.data.wtnr.trim(),
      "owid": that.data.tOwid,
      "zxlx": 2,
      "studentOwid": wx.getStorageSync("yhOwid")
    };
    common.ajax('zustcommon/bckjBizZxzx/consult', data, function (res) {
      if (res.data.backCode == 0) {
        var tip="";
        if(res.data.bean){
          tip=res.data.bean;
        }else{
          tip ="咨询已提交，请等待回复。"
        }
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: tip,
          success(res) {
            if (res.confirm) {
              console.log('用户点击确定')
              that.setData({
                modal1: false,
                wtnr: ''
              })
            } else if (res.cancel) {
              console.log('用户点击取消')
            }
          }
        })
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
    if(options.owid){
      details(this, options.owid)
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

var details = function (that,owid) {//新闻快递轮播图
  var data = { "owid": owid };
  common.ajax('zustjy/bckjBizZjzx/getConsultsOne', data, function (res) {
    if (res.data.backCode == 0) {
      if(res.data.bean){
        if (res.data.bean.xb){
          if (res.data.bean.xb==1) {
            res.data.bean.xbStr='男'
          } else if(res.data.bean.xb == 1) {
            res.data.bean.xbStr = '女'
          }
        }
        
        that.setData({
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