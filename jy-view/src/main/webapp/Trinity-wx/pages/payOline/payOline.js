// pages/payOline/payOline.js
var common = require('../../libs/common/common.js');
var util = require('../../utils/util.js')
//确认邮箱弹出框
import Dialog from '../../miniprogram_npm/vant-weapp/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    jfInfo: '缴费证明图片上传',
    class1: '',
    proveImg: '',
    upProveImg: '',
    payDetail: '',//缴费说明
    payUrl: '',
    state: '',//流程进行带的状态
    class1: '',
    payTime: '', //缴费时间
    returnPayReason:'', //缴费缴费理由
    showPop: false,
    currentDate: new Date().getTime(),
    minDate: new Date().getTime() - 86400000 * 200,
    maxDate: new Date().getTime() + 86400000 * 365,
  },

  //选择图片
  choseImageProve(event) {
    var that = this;
    var type = event.currentTarget.dataset.type;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success(res) {
        const tempFilePaths = res.tempFilePaths;

        //上传图片
        common.uploadFile(tempFilePaths, 1, function (res) {
          let data = JSON.parse(res.data)
          if (data.backCode == 0) {
            let imgUrl = data.bean.filePath;
            that.setData({
              upProveImg: imgUrl,
              jfInfo: '缴费证明图片上传成功',
              class1: 'green'
            })
          } else {
            that.setData({
              jfInfo: '缴费证明图片上传失败，请重传',
              class1: 'red'
            })
            common.toast('上传失败', 'none', 2000);
          }
        });

        //图片临时显示
        that.setData({
          proveImg: tempFilePaths[0]
        });

      }
    })
  },

  //上传缴费证明
  upPayImg: function () {
    var that = this;
    if (this.data.payTime == "") {
      common.toast('请先选择缴费时间', 'none')
      return;
    }
    if (this.data.upProveImg == "") {
      common.toast('请先上传缴费证明图片', 'none')
      return;
    }
    var data = {
      "applyOwid": wx.getStorageSync("applyOwid"),
      "jfpzZp": that.data.upProveImg,
      "jfsj": that.data.payTime
    }
    common.ajax('zustswyt/bckjBizBm/submitJft', data, function (res) {
      console.log(console.log(res.data.backCode));
      if (res.data.backCode == 0) {
        console.log(res.data.backCode);
        Dialog.alert({
          message: '缴费证明图片已成功提交，请等待审核！'
        }).then(() => {
          wx.redirectTo({
            url: '../Process/Process',
          })
        });
      } else {
        common.toast(res.data.errorMess, 'none')
      }
    });
  },

  //点击图片放大
  previewImage: function (event) {
    let src = event.currentTarget.dataset.src;//获取data-src
    let imgArr = [src]
    wx.previewImage({
      current: src,     //当前图片地址
      urls: imgArr,               //所有要预览的图片的地址集合 数组形式
      success: function (res) { },
      fail: function (res) { },
      complete: function (res) { },
    })
  },

  //跳转到外部链接支付页面
  goPayOline: function () {
    wx.navigateTo({
      url: '../payLink/payLink',
    })
  },

  //复制链接地址
  copyUrl: function (e) {
    wx.setClipboardData({
      data: e.currentTarget.dataset.text,
      success: function (res) {
        wx.getClipboardData({
          success: function (res) {
            common.toast("复制成功 黏贴到浏览器打开", 'none')
          }
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(this.data.currentDate)
    let that = this;
    common.getPayUrl(this);//获取缴费初始化地址
    that.setData({
      'state': options.state
    });

    common.getProcssState(function (data) {
      if (data.jfpzZp) {
        that.setData({
          proveImg: common.imgPath + data.jfpzZp,
          jfInfo: '缴费证明图片已上传',
          upProveImg: data.jfpzZp,
          class1: 'green',
          payTime: (data.jfsj).substring(0, 10),
          returnPayReason: data.jjly,
        })
      }
    })
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
    var that = this;
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //取消pop
  cancelPop() {
    this.setData({
      showPop: false
    });
  },
  //显示文化程度弹出
  showTime(e) {
    let that = this;
    that.setData({
      showPop: true,
    });
  },
  confirmTime(e) {
    let that = this;
    var date = util.formatTime1(new Date(e.detail));
    that.setData({
      showPop: false,
      payTime: date
    });
  }
})
