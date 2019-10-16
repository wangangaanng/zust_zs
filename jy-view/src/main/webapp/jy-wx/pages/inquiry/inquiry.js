// pages/inquiry/inquiry.js
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath: imgPath,
    pageSize: 20,
    pageNo: 1,
    totalPage: '',
    list: [],
    mxdx:'',
    key:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(options.mxdx){
      this.setData({
        pageSize: 20,
        pageNo: 1,
        totalPage: '',
        list: [],
        mxdx: options.mxdx
      })
      getList(this, options.mxdx, 1);// pageNo
    }
    
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
  loadMore: function () {
    var that = this;
    if ((that.data.pageNo + 1) <= that.data.totalPage) {
      that.setData({
        pageNo: that.data.pageNo + 1,
      })
      getList(that, that.data.mxdx, that.data.pageNo);
    }
  },
  diaocha:function(e){
    wx.navigateTo({
      url: '../diaocha/diaocha?owid='+e.currentTarget.dataset.owid,
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

  }
})
var getList = function (that, mxdx,pageNo) {
  var data = {
    "wzbh": 1, "wjmc": that.data.key, "mxdx": mxdx, "pageNo": pageNo, "pageSize": that.data.pageSize,
  };
  common.ajax('zustcommon/bckjBizDcwj/dcwjList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
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
      var list = that.data.list.concat(arr)
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
var refresh=function(that){
  that.setData({
    pageSize: 20,
    pageNo: 1,
    totalPage: '',
    list: [],
  })
  getList(that, that.data.mxdx, 1);// pageNo
}