// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
import WxValidate from '../../libs/wx-validate/WxValidate'
const app = getApp()
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;

Page({

  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.owid){
      getOne(this, options.owid)
    }

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


var getOne = function (that, owid) {
  var data = { "yhOwid": wx.getStorageSync('yhOwid') ,"owid":owid};
  common.ajax('zustjy/bckjBizJob/getMiniJob', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean) {
        if (data.bean.qyxx) {
          if (data.bean.qyxx.qyLxrdh) {
            data.bean.qyxx.qyLxrdh = common.base64Decode(data.bean.qyxx.qyLxrdh)
          }
          if (data.bean.qyxx.qylxfs) {
            data.bean.qyxx.qylxfs = common.base64Decode(data.bean.qyxx.qylxfs)
          }
          if (data.bean.qyxx.qyYx) {
            data.bean.qyxx.qyYx = common.base64Decode(data.bean.qyxx.qyYx)
          }
        }
        that.setData({
          form: data.bean
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