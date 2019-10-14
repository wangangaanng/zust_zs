// pages/qyInfo/qyInfo.js
var Mustache = require('../../libs/mustache/mustache');
var WxParse = require('../../libs/wxParse/wxParse.js');
var common = require('../../libs/common/common.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    result:'',
    old:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.owid) {
      getContent(this, options.owid);
    }
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
})
var getContent = function (that, owid) {//招聘详情
  var data = { "owid": owid, "yhOwid": wx.getStorageSync("stuOwid") };
  common.ajax('zustjy/bckjBizJob/getOneJob', data, function (res) {
    if (res.data.backCode == 0) {
      res.data.bean.createtime = res.data.bean.createtime.substring(0, 10)
      if (res.data.bean.zphKsrq) {
        if (res.data.bean.zwlx == 4) {
          res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 16)
        } else {
          res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 10)
        }
      }
      if (res.data.bean.zphBmjzsj){
        res.data.bean.zphBmjzsj = res.data.bean.zphBmjzsj.substring(0, 10)
      }
      var memo = res.data.bean.memo
      WxParse.wxParse('memo', 'html', memo, that, 5);
      that.setData({
        result: res.data.bean,
      })
      if (res.data.bean.zwSxsj) {
        var thetime = res.data.bean.zwSxsj;
        var d = new Date(Date.parse(thetime.replace(/-/g, "/")));

        var curDate = new Date();
        if (d <= curDate) {
          that.setData({
            old:'1',
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