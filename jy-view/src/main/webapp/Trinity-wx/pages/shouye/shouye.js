// pages/shouye/shouye.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid = app.globalData.yhRefOwid;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    res:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getXxxx()
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
  //学校信息获取
  getXxxx: function (e) {
    let that = this;
    let data = {
      pageNo:1,
      pageSize:1,
      yhRefOwid: yhRefOwid,
    }
    common.ajax('zustswyt/bckjBizXxpz/getXxxx', data, function (res) {
      if (res.data.backCode == 0) {
        that.setData({
          res: res.data.bean.list[0]
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
})