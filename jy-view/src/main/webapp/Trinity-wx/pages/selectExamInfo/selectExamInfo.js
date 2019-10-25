// pages/selectExamInfo/selectExamInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showPop:false,
    subjectList: ["科目一","科目二","科目三","科目四"],
    selectSubject:[],
    result: ['a', 'b']
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
  //确定选择
  confirmSubject(event){
    const { value, index } = event.detail;
    this.data.selectSubject = value;
    this.setData({
      showPop: true,
      ["selectSubject[1]"]: this.data.selectSubject
    });
  },
  //隐藏弹出
  cancelPop(){
    this.setData({
      showPop: false
    });
  },
  //点击弹出
  selectSubject(){
    this.setData({
      showPop: true
    });
  },
  onChange(event) {
    this.setData({
      result: event.detail
    });
  },
  preStep:function(){
    wx.navigateTo({
      url: '../examGrade/examGrade',
    })
  },
  finish:function(){
    wx.navigateTo({
      url: '../Process/Process',
    })
  }
})