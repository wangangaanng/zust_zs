// pages/drawGroup/drawGroup.js
var common = require('../../libs/common/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username:'',
    subject:'暂无',
    language:'暂无',
    type:'暂无',
    major: '暂无',
    number: '暂无',
    result: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    common.getProcssState(function(res){
      var data = res.data.bean;
      that.setData({
        'state': data.state,
        'subject': data.xklb, //学科类别
        'number': data.zkzh, //准考证号
        'language': data.wyyz,//外语语种
        'major': data.xzzymc,//报考专业
        'type': data.bklb,//报考类别
        'result': data.xybnr,//待确认下一步内容
        'username': data.xm //姓名
      })
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
    common.getProcssState(function(res){

    });
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
