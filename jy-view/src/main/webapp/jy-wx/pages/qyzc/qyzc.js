// pages/newJob/newJob.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    column1: ['杭州', '宁波', '温州', '嘉兴', '湖州'],
    username:'123',
    show: {
      bottom: false
    },
    znlb: '请选择',
    area: '请选择',
    areaList: {},
    loading: true,
    value: 330106
  },
  onConfirm1(e) {
    console.log(e)
    this.setData({
      area: `${e.detail.values[0].name}-${e.detail.values[1].name}-${e.detail.values[2].name}`
    })
    this.toggle('bottom', false);
  },
  onCancel1(e) {
    this.toggle('bottom', false);
  },
  onConfirm(event) {
    const { value, index } = event.detail;
    this.setData({
      znlb: value
    })
    this.toggle('bottom', false);
  },
  onCancel() {
    this.toggle('bottom', false);
  },
  onChange1(event) {
    const { value, index } = event.detail;
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

  },
  showBottom() {
    this.toggle('bottom', true);
  },

  hideBottom() {
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
    wx.request({
      url: 'https://cashier.youzan.com/wsctrade/uic/address/getAllRegion.json',
      success: response => {
        this.setData({
          loading: false,
          areaList: response.data.data
        });
      }
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