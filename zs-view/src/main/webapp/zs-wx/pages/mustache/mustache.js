// pages/mustache/mustache.js
var Mustache = require('../../libs/mustache/mustache');
var WxParse = require('../../libs/wxParse/wxParse.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: [
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表一', des: '这里是描述内容1' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表二', des: '这里是描述内容2' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表三', des: '这里是描述内容3' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表一', des: '这里是描述内容1' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表二', des: '这里是描述内容2' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表三', des: '这里是描述内容3' },
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var data = this.data;
    var tpl = "{{#list}}<div class='media-box'><img class='media-box_thumb' src='{{{img}}}' /><a style='color:#000000;text-decoration:none;'>{{ title }}</a><p style='color:#999999;'>{{ des }}</p></div>{{/ list}}";
    // var tpl = '{{#list}}<a class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><img class="weui-media-box__thumb" src="{{{img}}}" alt=""></div><div class="weui-media-box__bd"><h4 class="index-guess_title">名称名称</h4><h6>年 · 万公里</h6><p>成交价<span class="index-guess_prize"> 万</span></p></div></a>{{/list}}';
    var html = Mustache.render(tpl, data);
    WxParse.wxParse('template', 'html', html, this, 5);
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