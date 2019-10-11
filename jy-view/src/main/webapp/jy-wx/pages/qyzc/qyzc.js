// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
const app = getApp()

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
    getByType(this)
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
  upload(){
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        var tempFilePaths = res.tempFilePaths
        console.log(res)
        var jsonObj={
          "file": tempFilePaths[0],
          "type":1
        }
        wx.uploadFile({
          url: 'http://172.16.13.106:8888/zustcommon/common/picUpload',
          filePath: tempFilePaths[0],
          name: 'file',
          method: 'POST',
          formData: {
            "data": JSON.stringify(jsonObj)
          },
          success: function (res) {
            var data=JSON.parse(res.data)
            console.log(res)
            wx.showModal({
              title: '提示',
              showCancel:false,
              content: data.errorMess,
            })
            
          },
          fail: function () {
            
          }
        })
        
      }
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // wx.showToast({
    //   icon:'none',
    //   title: '123',
    // })
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

var getByType = function (that) {
  var data = { "dicType": "20004"};
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}