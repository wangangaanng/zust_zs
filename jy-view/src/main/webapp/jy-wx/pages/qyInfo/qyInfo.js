// pages/qyInfo/qyInfo.js
var Mustache = require('../../libs/mustache/mustache');
var WxParse = require('../../libs/wxParse/wxParse.js');
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
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
  applyJob2: function () {
    var that = this;
    if ((wx.getStorageSync('yhOwid'))&&(wx.getStorageSync('userType') == 0)) {//企业登录
      wx.navigateTo({
        url: '../ydzw/ydzw?owid=' + that.data.result.owid,
      })
    } else {
      wx.navigateTo({
        url: '../qyLogin/qyLogin',
      })
    }
  },
  applyJob: function () {
    var that = this;
    if (wx.getStorageSync('userType')!= 1) {//学生登录
      wx.navigateTo({
        url: '../stuLogin/stuLogin',
      })
    } else {
      var bmdx = that.data.result.zwlx;
      if (bmdx == '0') {
        bmdx = 2
      } else if (bmdx == '3') {
        bmdx = 0
      } else if (bmdx == '4') {
        bmdx = 1
      }
      var data = {
        "jobRefOwid": that.data.result.owid, "bmlx": '1', "bmdx": bmdx, "yhRefOwid": wx.getStorageSync("yhOwid")}
      common.ajax("zustjy/bckjBizJybm/applyJob", data, function (res) {
        if (res.data.backCode == 0) {
          wx.showToast({
            icon: 'success',
            title: '申请成功',
          });
        } else {
          wx.showToast({
              title: res.data.errorMess,
              icon: 'none',
              duration: 2000
          });
        }

      })
    }
  },
  saveJob:function(){
    var that=this;
    if (wx.getStorageSync('userType')!=1){//学生登录
      wx.navigateTo({
        url: '../stuLogin/stuLogin',
      })
    }else{
      var data = {"jobRefOwid": that.data.result.owid,"xxlb": '0', "yhRefOwid": wx.getStorageSync("yhOwid")}
      common.ajax("zustjy/bckjBizXsgz/signInOrScribe", data, function (res) {
        if (res.data.backCode == 0) {
          that.setData({
            jlowid: res.data.bean[0].owid,
            isSc:true,
          })
          wx.showToast({
            icon: 'success',
            title: '收藏成功',
          });
        } else {
          wx.showToast({
              title: res.data.errorMess,
              icon: 'none',
              duration: 2000
          });
        }

      })
    }
  },
  cancelJob:function(){
    var that = this;
    if (wx.getStorageSync('userType') != 1) {//学生登录
      wx.navigateTo({
        url: '../stuLogin/stuLogin',
      })
    } else {
      var data = { "owid": that.data.jlowid }
      common.ajax("zustjy/bckjBizXsgz/cancelSubcribe", data, function (res) {
        if (res.data.backCode == 0) {
          that.setData({
            jlowid: "",
            isSc: false,
          })
          wx.showToast({
            icon: 'success',
            title: '取消成功',
          });
        } else {
          wx.showToast({
              title: res.data.errorMess,
              icon: 'none',
              duration: 2000
          });
        }

      })
    }
  }
})
var getContent = function (that, owid) {//招聘详情
  var data = { "owid": owid, "yhOwid": wx.getStorageSync("yhOwid") };
  common.ajax('zustjy/bckjBizJob/getOneJob', data, function (res) {
    if (res.data.backCode == 0) {
      res.data.bean.sfkbm = false;
      if (res.data.bean.state == 2) {
        if (res.data.bean.zphSfbm == 0) {
          res.data.bean.sfkbm = false;
        } else if (res.data.bean.zphSfbm == 1) {
          res.data.bean.sfkbm = true;
        }

      } else if (res.data.bean.state == 6) {
        res.data.bean.sfkbm = false;
      } else {
        res.data.bean.sfkbm = false;
      }
      if (res.data.bean.zphBmjzsj && util.compareToday(res.data.bean.zphBmjzsj)) {
        res.data.bean.sfkbm = false;
      }
      if (util.compareToday(res.data.bean.zphKsrq)) {
        res.data.bean.sfkbm = false;
      }

      res.data.bean.createtime = res.data.bean.createtime.substring(0, 16)
      if (res.data.bean.zphKsrq) {
        // if (res.data.bean.zwlx == 4) {
        //   res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 16)
        // } else {
          res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 10)
        // }
      }
      if (res.data.bean.zphBmjzsj){
        res.data.bean.zphBmjzsj = res.data.bean.zphBmjzsj.substring(0, 10)
      }
      var memo = res.data.bean.memo
      WxParse.wxParse('memo', 'html', memo, that, 5);
      if (res.data.bean.zwGwzz){
        var zwGwzz = res.data.bean.zwGwzz
        WxParse.wxParse('zwGwzz', 'html', zwGwzz, that, 5);
      }
      if ((res.data.bean.qyxx) && (res.data.bean.qyxx.qyGsjs)){
        var qyGsjs = res.data.bean.qyxx.qyGsjs
        WxParse.wxParse('qyGsjs', 'html', qyGsjs, that, 5);
      }
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
      if ((res.data.bean.exp2) && (res.data.bean.exp2 != "0")){
        that.setData({
          jlowid: res.data.bean.exp2,
          isSc:true
        })
      }else{
        that.setData({
          isSc: false
        })
      }
      if ((res.data.bean.zwlx == 3) || (res.data.bean.zwlx == 4)){
        if (res.data.bean.zphSfbm == 1){
          that.setData({
            sfbm:true
          })
        }else{
          that.setData({
            sfbm: false
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