// pages/basicInfo/basicInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    birthPopBool:false,//出生日期
    currentDate: new Date().getTime(),
    sexRadio: '1',//性别
    form:{
      name: '',
      idNum: '',
      sex: '',
      nationality: '',
      address: '',
      contactPhone: '',
      mobile: '',
      mailbox:'',
      qq:'',
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },
  //单选框 选择性别
  sexSelect(event) {
    console.log(event.detail);
    this.setData({
      //sexRadio: event.detail
    });
  }
})