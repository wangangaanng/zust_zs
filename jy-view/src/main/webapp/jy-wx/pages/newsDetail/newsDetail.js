// pages/newsDetail/newsDetail.js
var Mustache = require('../../libs/mustache/mustache');
var WxParse = require('../../libs/wxParse/wxParse.js');
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(options.owid){
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

  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
var getContent = function (that, owid) {//学校简介
  var data = { "owid": owid };
  common.ajax('zustcommon/bckjBizArticle/getOne', data, function (res) {
    if (res.data.backCode == 0) {
      res.data.bean.createtime = res.data.bean.createtime.substring(0, 4) + '年' + res.data.bean.createtime.substring(5, 7) + '月' + res.data.bean.createtime.substring(8, 10) + '日 ' + res.data.bean.createtime.substring(11, 16);
      var memo = res.data.bean.wznr
      // const regex1 = new RegExp('<h[1-6]', 'gi');
      // const regex2 = new RegExp('</h[1-6]', 'gi');
      // const regex3 = new RegExp('<img', 'gi');
      // const regex4 = new RegExp('<p', 'gi');
      // memo = memo.replace(regex1, `<h1 style="font-size:16px"`);
      // memo = memo.replace(regex2, `</h1`);
      // memo = memo.replace(regex3, `<img class="memoimg" onclick="prebiew()" style="max-width:100% !important;height: auto !important;border:0px !important;" `);
      // memo = memo.replace(regex4, `<p class="memop"`);
      // res.data.bean.wznr=memo;
      WxParse.wxParse('template', 'html', memo, that, 5);
        that.setData({
          content: res.data.bean,
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