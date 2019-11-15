// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    hidden1:true,
    years: [],
    show: {
      bottom: false
    },
    year:'',
    yearStr: '请选择',
    list:[]
  },
  onConfirm(e) {
    console.log(e)
    this.setData({
      yearStr: e.detail.value,
      year: e.detail.value
    })
    this.toggle('bottom', false);
    getByType2(this, e.detail.value)

  },
  onCancel(e) {
    this.toggle('bottom', false);
  },
  toggle(type, show) {
    this.setData({
      [`show.${type}`]: show
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getByType2(this)
  },
  showBottom(e) {
    this.toggle('bottom', true);
  },

  hideBottom(e) {
    this.toggle('bottom', false);
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

  }
})

var getByType1 = function (that) {
  var data = {};
  common.ajax('zustjy/bckjBizJypm/getRecentYears', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean) {
        if (data.bean.recentYears && data.bean.recentYears.length > 0) {
          that.setData({
            years: data.bean.recentYears,
            yearStr: data.bean.recentYears[0],
            year: data.bean.recentYears[0]
          })
          getByType2(that, data.bean.recentYears[0])
          // for (var i in data.bean) {
          //   var obj = {}
          //   obj.dicVal1 = data.bean[i].dicVal1
          //   obj.dicVal2 = data.bean[i].dicVal2
          //   that.data.gsxzColumns.push(obj)
          // }
          // that.setData({
          //   gsxzColumns: that.data.gsxzColumns
          // })
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

var getByType2 = function (that) {
  that.setData({
    list: [],
    hidden1: true
  })
  var data = { 
    // "pmnf": year 
    };
  common.ajax('zustjy/bckjBizJypm/jypmList', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        that.setData({
          list: data.bean
        })
      }else{
        that.setData({
          hidden1:false
        })
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
