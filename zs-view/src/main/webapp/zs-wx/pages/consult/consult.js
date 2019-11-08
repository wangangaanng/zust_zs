// pages/consult/consult.js
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab:0,
    imgPath: imgPath,
    pageSize: 20,
    pageNo3: 1,
    totalPage3: '',
    list3: [],
    mxdx: '',
    key: '',
    modal1: false,
    wtnr: '',
    list2: [],
    pageNo2: 1,
    pageSize: 15,
    totalPage2: '',
    list1: [],
    pageNo1: 1,
    totalPage1: ''
  },
  clicktab: function (e) {
    console.log(e)
    if (e.detail.name == 1) {
      this.setData({
        modal1: false,
        wtnr: '',
        totalPage: '',
        list2: [],
        pageNo2: 1,
        totalPage2: ''
      })
      getList2(this)
    }
   
    this.setData({
      modal1: false,
      wtnr: '',
      currentTab: e.detail.name
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getList1(this);
    getList3(this)
  },
  onChange(e) {
    this.setData({
      key: e.detail
    });
  },
  onClear() {
    var that = this;
    refresh(this);
  },
  onSearch: function () {
    var that = this;
    refresh(this);
  },
  diaocha: function (e) {
    wx.navigateTo({
      url: '../diaocha/diaocha?owid=' + e.currentTarget.dataset.owid,
    })
  },
  cancel: function () {
    this.setData({
      modal1: false,
      wtnr: ''
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
    console.log('that.data.tOwid', that.data.tOwid)
    var data = {
      "wtnr": that.data.wtnr.trim(),
      "zxlx": 4
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
                wtnr: '',
                totalPage: '',
                list2: [],
                pageNo2: 1,
                totalPage2: ''
              })
              getList2(that)
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

  ask: function (e) {
    this.setData({
      modal1: true
    });
  },
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
      "zxlx": 4,
    };
    common.ajax('zustcommon/bckjBizZxzx/consult', data, function (res) {
      if (res.data.backCode == 0) {
        var tip = "";
        if (res.data.bean) {
          tip = res.data.bean;
        } else {
          tip = "咨询已提交，请等待回复。"
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
  newsDetail(e) {
    wx.navigateTo({
      url: '../newsDetail/newsDetail?owid=' + e.currentTarget.dataset.owid,
    })
  },
  loadMore1: function () {
    var that = this;
    if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
      that.setData({
        pageNo1: that.data.pageNo1 + 1,
      })
      getList1(that);
    }
  },
  loadMore2: function () {
    var that = this;
    if ((that.data.pageNo2 + 1) <= that.data.totalPage2) {
      that.setData({
        pageNo2: that.data.pageNo2 + 1,
      })
      getList2(that);
    }
  },
  loadMore3: function () {
    var that = this;
    if ((that.data.pageNo3 + 1) <= that.data.totalPage3) {
      that.setData({
        pageNo3: that.data.pageNo3 + 1,
      })
      getList3(that);
    }
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

var getList1 = function (that) {
  var data = { "lmbh": '94', "pageNo": that.data.pageNo1, "pageSize": "20", "wzzt": "1", "isDetail": '1' };//isDetail:1列表
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      var curDate = new Date();
      if (res.data.bean && res.data.bean.records && res.data.bean.records.length > 0) {
        for (var i = 0; i < res.data.bean.records.length; i++) {
          arr.push(res.data.bean.records[i]);
        }
      }

      var list1 = that.data.list1.concat(arr)
      var totalPage1 = res.data.bean.totalPage;
      // console.log
      that.setData({
        list1: list1,
        totalPage1: totalPage1,
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


var getList3 = function (that) {
  var data = {
    "wzbh": '0', "pageNo": that.data.pageNo3, "pageSize": that.data.pageSize,
  };
  common.ajax('zustcommon/bckjBizDcwj/dcwjList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      if (res.data.bean.records && res.data.bean.records.length>0){
        for (var i = 0; i < res.data.bean.records.length; i++) {
          var obj = {};
          var object = res.data.bean.records[i];
          obj.wjmc = object.wjmc;
          obj.kssj = object.kssj.substring(0, 10);
          obj.jssj = object.jssj.substring(0, 10);
          obj.sfyx = object.sfyx;
          obj.owid = object.owid;
          arr.push(obj);
        }
      }
      var list3 = that.data.list3.concat(arr)
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list3: list3,
        totalPage3: totalPage,
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
var refresh = function (that) {
  that.setData({
    pageSize: 20,
    pageNo: 1,
    totalPage: '',
    list: [],
  })
  getList(that, that.data.mxdx, 1);// pageNo
}


var getList2 = function (that) {//
  var data = { "zxlx": '4', "pageNo": that.data.pageNo2, "pageSize": that.data.pageSize };
  common.ajax('zustcommon/bckjBizZxzx/historyMessage', data, function (res) {
    if (res.data.backCode == 0) {
      var list2;
      if (res.data.bean && res.data.bean.records && res.data.bean.records.length > 0) {
        for (var i in res.data.bean.records){
          res.data.bean.records[i].LYIP = res.data.bean.records[i].LYIP ? res.data.bean.records[i].LYIP:''
          res.data.bean.records[i].TWRQ = res.data.bean.records[i].TWRQ ? res.data.bean.records[i].TWRQ : ''
          res.data.bean.records[i].WTNR = res.data.bean.records[i].WTNR ? res.data.bean.records[i].WTNR : ''
          res.data.bean.records[i].DANR = res.data.bean.records[i].DANR ? res.data.bean.records[i].DANR : ''
          res.data.bean.records[i].HDRQ = res.data.bean.records[i].HDRQ ? res.data.bean.records[i].HDRQ : ''
        }
        list2 = that.data.list2.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list2: list2,
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
