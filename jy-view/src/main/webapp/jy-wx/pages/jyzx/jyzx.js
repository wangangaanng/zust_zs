// pages/jyzx/jyzx.js
var common = require('../../libs/common/common.js')
import Dialog from 'vant-weapp/dialog/dialog';
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({
  data: {
    currentTab: 0,
    modal1: false,
    wtnr:'',
    imgPath: imgPath,
    list: [],
    pageNo: 1,
    pageSize: 15,
    totalPage: '',
    list1: [],
    pageNo1: 1,
    totalPage1: '',
    show:false,
  },
  cancel: function () {
    this.setData({
      modal1: false,
      wtnr: ''
    });
  },
  ask:function(e){
    console.log(e.currentTarget.dataset.owid)
    this.setData({
      tOwid: e.currentTarget.dataset.owid,
      modal1: true
    });
  },
  //确认
  confirm: function () {
    var that=this
    if (!that.data.wtnr.trim()) {
      wx.showToast({
        icon: 'none',
        title: '请填写内容',
      })
      return false
    }
    console.log('that.data.tOwid', that.data.tOwid)
    var data = { 
      "wtnr": that.data.wtnr.trim(),
      "owid": that.data.tOwid,
      "zxlx": 2,
      "studentOwid": wx.getStorageSync("yhOwid")
     };
    common.ajax('zustcommon/bckjBizZxzx/consult', data, function (res) {
      if (res.data.backCode == 0) {
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: '咨询已提交，请等待回复。',
          success(res) {
            if (res.confirm) {
              console.log('用户点击确定')
              that.setData({
                modal1: false,
                wtnr:''
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
    supervisorList(this)
  },
  clickTab(e){
    if(e.detail.index==1){
      this.setData({
        modal1: true,
        wtnr: '',
        totalPage: '',
        list1: [],
        pageNo1: 1,
        totalPage1: ''
      })
      historyConsult(this)
    }
    this.setData({
      currentTab: e.detail.index
    })
  },
  detail(e){
    wx.navigateTo({
      url: '../zjxq/zjxq?owid=' + e.currentTarget.dataset.owid,
    })
  },
  getWtnr(e){
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
    var that = this;
    if (that.data.currentTab == 0) {
      if ((that.data.pageNo + 1) <= that.data.totalPage) {
        that.setData({
          pageNo: that.data.pageNo + 1,
        })
        supervisorList(that);
      }
    } else if (that.data.currentTab == 1) {
      if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
        that.setData({
          pageNo1: that.data.pageNo1 + 1,
        })
        historyConsult(that);
      }
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})

// function beforeclose(action, done) {
//   // done(false)
//   console.log(action)
//   if (action === 'confirm') {
//     setTimeout(done, 1000)
//     console.log(1111)
//   } else if (action === 'cancel') {
//     done() //关闭
//   }
// }

var supervisorList = function (that) {//新闻快递轮播图
  var data = { "pageNo": that.data.pageNo, "pageSize": that.data.pageSize };
  common.ajax('zustjy/bckjBizZjzx/supervisorList', data, function (res) {
    if (res.data.backCode == 0) {
      var list;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        list = that.data.list.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list: list,
        totalPage: totalPage,
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

var historyConsult = function (that) {//新闻快递轮播图
  var data = { "twOwid":wx.getStorageSync('yhOwid'),"zxlx":'2', "pageNo": that.data.pageNo1, "pageSize": that.data.pageSize };
  common.ajax('zustcommon/bckjBizZxzx/historyConsult', data, function (res) {
    if (res.data.backCode == 0) {
      var list;
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
