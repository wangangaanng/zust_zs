// pages/myApply/myApply.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageNo: 1,
    list: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    yhRefOwid = wx.getStorageSync('yhRefOwid')
    this.getXxxx()
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
    if (this.data.totalPage != this.data.pageNo) {
      this.data.pageNo++;
      this.getXxxx()
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  //学校信息获取
  getXxxx: function(e) {
    let that = this;
    let data = {
      pageNo: that.data.pageNo,
      pageSize: 10,
      yhRefOwid: yhRefOwid,
    }
    common.ajax('zustswyt/bckjBizXxpz/getXxxx', data, function(res) {
      if (res.data.backCode == 0) {
        let list = that.data.list;
        let list2 = res.data.bean.list
        for (let i in res.data.bean.list) {
          if (!!res.data.bean.list[i].bmzy) {
            let obj = res.data.bean.list[i]
            let kssj = '';
            let jzsj = '';
            let xxbq = '';
            if (obj.xxbq) {
              obj.xxbq = list2[i].xxbq.split('，')
            }
            if (obj.kssj && obj.jzsj) {
              obj.kssj = obj.kssj.substring(5, 10).replace("-", ".")
              obj.jzsj = obj.jzsj.substring(5, 10).replace("-", ".")
            }
            list.push(obj)
          }
        }
        that.setData({
          res: list,
          totalPage: res.data.bean.totalPage
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  setxxbh(e) {
    wx.setStorageSync('xxbh', e.currentTarget.dataset.url);
    if (!!yhRefOwid) {
      wx.navigateTo({
        url: '../Process/Process',
      })
    } else {
      wx.redirectTo({
        url: '../login/login',
      })
    }
  },
})