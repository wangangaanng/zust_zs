// pages/school/school.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;

Page({
  data: {
    currentTab: 0,
    key: '',
    list1: [],
    list2: [],
    pageSize: 20,
    pageNo1: 1,
    pageNo2: 1,
    totalPage1: '',
    totalPage2: '',
    minDate: new Date().getTime(),
    imgPath: imgPath,
    old: ''
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.owid) {
      this.setData({
        owid1: options.owid,
        owid2: options.owid1
      })
      getOne(this, options.owid)
    }
    showStudentInfo1(this)
    // showStudentInfo2(this)
  },
  onShow: function () {

  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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
    if (that.data.currentTab == 1) {
      if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
        that.setData({
          pageNo1: that.data.pageNo1 + 1,
        })
        showStudentInfo1(that);
      }
    } else if (that.data.currentTab == 2) {
      if ((that.data.pageNo2 + 1) <= that.data.totalPage2) {
        that.setData({
          pageNo2: that.data.pageNo2 + 1,
        })
        showStudentInfo2(that);
      }
    }
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  clickTab: function (e) {
    this.setData({
      currentTab: e.detail.index
    })
  },

})

var getOne = function (that, owid) {
  var data = {
    "owid": owid
  };
  common.ajax('zustjy/bckjBizJybm/getOne', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean) {
        that.setData({
          result:data.bean
        })
        if (data.bean.zphSfbm && data.bean.zphSfbm==1){
          showStudentInfo2(that)
        }
      }
      if (res.data.bean.xjsj) {
        var thetime = res.data.bean.xjsj;
        var d = new Date(Date.parse(thetime.replace(/-/g, "/")));

        var curDate = new Date();
        if (d <= curDate) {
          that.setData({
            old: '1',
          })
        }
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

var showStudentInfo1 = function (that) {
  var data = {
    "type": "2", //1：报名 2：关注
    "owid":wx.getStorageSync("yhOwid"),
    "jobOwid": that.data.owid2,
    "pageSize": that.data.pageSize,
    "pageNo": that.data.pageNo1
  };
  common.ajax('zustjy/bckjBizQyxx/showStudentInfo', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      var list;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        list = that.data.list1.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list1: list,
        totalPage11: totalPage,
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


var showStudentInfo2 = function (that) {
  var data = {
    "type": "1",//1：报名 2：关注
    "owid": wx.getStorageSync("yhOwid"),
    "jobOwid": that.data.owid2,
    "pageSize": that.data.pageSize,
    "pageNo": that.data.pageNo1
  };
  common.ajax('zustjy/bckjBizQyxx/showStudentInfo', data, function (res) {
    if (res.data.backCode == 0) {
      var list;
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