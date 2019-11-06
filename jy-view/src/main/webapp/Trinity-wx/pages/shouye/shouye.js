// pages/shouye/shouye.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    res: {},
    state: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    
    this.historyMessage()
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
    yhRefOwid = wx.getStorageSync('yhRefOwid')
    if (!!yhRefOwid){
      this.getXxxx()
      wx.setStorageSync('href', 0)
      this.indexState()
    }else{
      this.getXxsInfo()
    }
    
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
  //学校信息获取
  getXxxx: function(e) {
    let that = this;
    let data = {
      pageNo: 1,
      pageSize: 10,
      yhRefOwid: yhRefOwid,
    }
    common.ajax('zustswyt/bckjBizXxpz/getXxxx', data, function(res) {
      if (res.data.backCode == 0) {
        let list = res.data.bean.list
        for (let i in res.data.bean.list) {
          let kssj = '';
          let jzsj = '';
          let xxbq = '';
          if (list[i].xxbq) {
            list[i].xxbq = res.data.bean.list[i].xxbq.split('，')
          }
          if (list[i].kssj && list[i].jzsj) {
            list[i].kssj = list[i].kssj.substring(5, 10).replace("-", ".")
            list[i].jzsj = list[i].jzsj.substring(5, 10).replace("-", ".")
          }
        }
        // 目前阶段先确定一个学校
        if (res.data.bean.list[0].bmState!=0) {
          wx.setStorageSync('ybmxxbh', res.data.bean.list[0].xxbh);
        }
        that.setData({
          res: list
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  // 底部问答列表
  historyMessage: function(e) {
    let that = this;
    let data = {
      pageNo: 1,
      pageSize: 8,
      zxlx: '4'
    }
    common.ajax('zustcommon/bckjBizZxzx/historyMessage', data, function(res) {
      if (res.data.backCode == 0) {
        that.setData({
          historyMessage: res.data.bean.records
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  // 首页判断基本信息、会考等填写状态
  indexState: function(e) {
    let that = this;
    let data = {
      yhRefOwid: yhRefOwid
    }
    common.ajax('zustswyt/bckjBizJbxx/indexState', data, function(res) {
      if (res.data.backCode == 0) {
        that.setData({
          state: res.data.bean
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  openUrl: function(e) {
    if (!!yhRefOwid){
      let href = '';
      wx.setStorageSync('href', 1) //通过缓存href判断选考信息保存后跳转的页面
      switch (e.currentTarget.dataset.index) {
        case '0':
          href = '../basicInfo/basicInfo'
          break;
        case '1':
          href = '../contactors/contactors'
          break;
        case '2':
          href = '../examGrade/examGrade'
          break;
        case '3':
          href = '../selectExamInfo/selectExamInfo'
          break;
      }
      wx.navigateTo({
        url: href
      })
    }else{
      wx.redirectTo({
        url: '../login/login',
      })
    }
  },
  setxxbh(e){
    wx.setStorageSync('xxbh', e.currentTarget.dataset.url);
    if (!!yhRefOwid){
      wx.navigateTo({
        url: '../Process/Process',
      })
    }else{
      wx.redirectTo({
        url: '../login/login',
      })
    }
  },
  // 首页判断基本信息、会考等填写状态
  getXxsInfo: function (e) {
    let that = this;
    let data = {
      pageNo: 1,
      pageSize: 10
    }
    common.ajax('zustswyt/bckjBizXxpz/getXxsInfo', data, function (res) {
      if (res.data.backCode == 0) {
        let list = res.data.bean.list
        for (let i in res.data.bean.list) {
          let kssj = '';
          let jzsj = '';
          let xxbq = '';
          if (list[i].xxbq) {
            list[i].xxbq = res.data.bean.list[i].xxbq.split('，')
          }
          if (list[i].kssj && list[i].jzsj) {
            list[i].kssj = list[i].kssj.substring(5, 10).replace("-", ".")
            list[i].jzsj = list[i].jzsj.substring(5, 10).replace("-", ".")
          }
        }
        that.setData({
          res: list
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
})