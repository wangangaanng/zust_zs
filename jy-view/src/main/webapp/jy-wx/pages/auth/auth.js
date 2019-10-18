// pages/auth/auth.js
const app = getApp()
var openId;
var unionid;
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  getInfo(e) {
    var that = this;
    app.globalData.userInfo = e.detail.userInfo;
    wx.setStorageSync('userInfo', e.detail.userInfo);
    var encryptedData = e.detail.encryptedData
    var iv = e.detail.iv
    wx.login({
      success: function (res) {
        if (res.code) {
          var jsonObj = {
            code: res.code,
            encryptedData: encryptedData,
            iv: iv,
            wxid:'wx01'
          };
          wx.request({
            url: app.globalData.ApiUrl + 'zustcommon/bckjBizYhgl/getYhInfoByOpenid',
            data: {
              "data": JSON.stringify(jsonObj),
              timestamp: new Date().getTime()
            },
            header: {
              "Content-Type": "application/x-www-form-urlencoded"
            },
            method: 'POST',
            success: function (res) {
              if (res.data.bean) {
                openId = res.data.bean.openId;
                unionid = res.data.bean.unionid;
                app.globalData.openId = res.data.bean.openId;
                app.globalData.unionid = res.data.bean.unionid;
                wx.setStorageSync('openId', openId);
                wx.setStorageSync('unionid', unionid);
                // wx.setStorageSync('openId', 'oislo5CDg1Ot6nn6agupD9xpAYek');
                // wx.setStorageSync('unionid', 'oP6Dw029bsnywzPxvaxXNhYmbBj8');
              }
              wx.reLaunch({
                url: '/pages/index/index'
              })
            }

          })
        }
      }
    })
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