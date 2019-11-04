// pages/xw/xw.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    value: '',
    array: [
      '招生报名',
      '大学生活',
      '校友圈',
      '专业知识',
      'dengdeng'
    ],
    list: [],
    index1: 0,
    ff: true,
    focus: false,
    height: 0,
    imgPath: common.imgPath,
    top: 0,
    pageNo: 1,
    zwsj: false,
    current: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getLmMenu()
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
  onSearch: function(e) {
    console.log(e.detail)
  },
  click: function(e) {
    this.setData({
      top: 0,
      index1: e.currentTarget.dataset.index1,
      current: 0,
      pageNo: 1,
      list: []
    })
    this.getMuArticle(this.data.array[e.currentTarget.dataset.index1].CODE)
  },
  scroll: function(e) {
    this.setData({
      ff: false
    })
  },
  swiper: function(e) {
    console.log(e)
    this.setData({
      index1: e.detail.current
    })
  },
  focus: function(e) {
    this.setData({
      focus: true
    })
  },
  blur: function(e) {
    this.setData({
      focus: false
    })
  },
  // 栏目获取（单级）
  getLmMenu: function(e) {
    let that = this;
    let data = {
      wzbh: 0,
      fid: '119'
    }
    common.ajax('zustcommon/bckjDicMenu/getLmMenu', data, function(res) {
      if (res.data.backCode == 0) {
        that.setData({
          array: res.data.bean
        })
        that.getMuArticle(res.data.bean[0].CODE)
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  // 文章列表获取
  getMuArticle: function(e) {
    let that = this;
    let data = {
      pageNo: that.data.pageNo,
      pageSize: 8,
      isDetail: '1',
      wzzt: 1,
      lmbh: e
    }
    common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function(res) {
      if (res.data.backCode == 0) {
        let zwsj = true
        if (!!res.data.bean.records) {
          zwsj = false
        }
        let list = that.data.list;
        if (res.data.bean.records){
          list = list.concat(res.data.bean.records)
        }
        that.setData({
          list: list,
          zwsj: zwsj,
          totalPage: res.data.bean.totalPage
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  searchAll: function(e) {
    if(!e.detail){
      common.toast('请输入关键字', 'none', 2000)
      return
    }
    let that = this;
    let data = {
      gjz: e.detail,
      wzbh: 0,
      pageSize: 8,
      pageNo: 1,
    }
    common.ajax('zustcommon/bckjBizArticle/searchAll', data, function(res) {
      if (res.data.backCode == 0) {
        let zwsj = true
        if (!!res.data.bean.records) {
          zwsj = false
        }
        that.setData({
          list: res.data.bean.records || [],
          zwsj: zwsj,
          current: 1
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  pagination(e) {
    let pageNo = this.data.pageNo + 1
    if (this.data.current == 0 && this.data.totalPage > this.data.pageNo) {
      this.setData({
        pageNo: pageNo
      })
      this.getMuArticle(this.data.array[this.data.index1].CODE)
    }
  },
})