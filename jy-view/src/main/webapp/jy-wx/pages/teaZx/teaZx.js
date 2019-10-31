// pages/teaZx/teaZx.js
var common = require('../../libs/common/common.js')
import Dialog from 'vant-weapp/dialog/dialog';
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({
  data: {
    currentTab:0,
    modal1: false,
    wtnr: '',
    imgPath: imgPath,
    list1: [],
    pageNo1: 1,
    totalPage1: '',
    list2: [],
    pageNo2: 1,
    totalPage2: '',
    pageSize:20,
  },
  clickTab(e) {
    this.setData({
      currentTab: e.detail.index
    })
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
      "danr": that.data.wtnr.trim(),
      "owid": that.data.tOwid,
      "zxlx": 2,
      "yhid": wx.getStorageSync("yhOwid")
    };
    common.ajax('zustjy/bckjBizZjzx/replyConsult', data, function (res) {
      if (res.data.backCode == 0) {
        that.setData({
          modal1: false,
          wtnr: ''
        })
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: '回复成功',
          success(res) {
            if (res.confirm) {
              refresh(that);
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
    
  },
  // detail(e) {
  //   wx.navigateTo({
  //     url: '../stuXq/stuXq?owid=' + e.currentTarget.dataset.owid,
  //   })
  // },
  getWtnr(e) {
    this.setData({
      wtnr: e.detail.value
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
    historyConsult(this);
    historyConsult2(this);
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
  loadMore: function () {
    var that = this;
    if(that.data.currentTab==0){
      if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
        that.setData({
          pageNo1: that.data.pageNo1 + 1,
        })
        historyConsult(that);
      }
    } else if (that.data.currentTab == 1){
      if ((that.data.pageNo2 + 1) <= that.data.totalPage2) {
        that.setData({
          pageNo2: that.data.pageNo2 + 1,
        })
        historyConsult2(that);
      }
    }
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})



var historyConsult = function (that) {
  var data = { "yhid": wx.getStorageSync('yhOwid'), "zxlx": '2', "pageNo": that.data.pageNo1, "pageSize": that.data.pageSize,"state":'1' };
  common.ajax('zustjy/bckjBizZjzx/showStudentReplyList', data, function (res) {
    if (res.data.backCode == 0) {
      var list=[];
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        list = that.data.list1.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list1: list,
        totalPage1: totalPage,
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
var historyConsult2 = function (that) {
  var data = { "yhid": wx.getStorageSync('yhOwid'), "zxlx": '2', "pageNo": that.data.pageNo2, "pageSize": that.data.pageSize, "state": '2' };
  common.ajax('zustjy/bckjBizZjzx/showStudentReplyList', data, function (res) {
    if (res.data.backCode == 0) {
      var list=[];
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        list = that.data.list2.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list2: list,
        totalPage2: totalPage,
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
var refresh=function(that){
  that.setData({
    list1: [],
    pageNo1: 1,
    totalPage1: '',
    list2: [],
    pageNo2: 1,
    totalPage2: '',
  })
  historyConsult(that);
  historyConsult2(that)
}