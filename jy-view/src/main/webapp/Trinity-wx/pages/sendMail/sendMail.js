// 预览发送到邮箱
var common = require('../../libs/common/common.js');
var utils = require('../../utils/util.js');
import WxValidate from '../../utils/WxValidate';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mailAddress:"",
    imgPath: common.imgPath,
    filePath:''
  },
  //发送邮箱
  sendMailForm(e){
    const params = e.detail.value;
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      common.toast(error.msg, 'none', 2000)
      return false
    }
  },
  //预览pdf:报名表
  openFile: function (e) {
    console.log(e.currentTarget.dataset.src);
    if (e.currentTarget.dataset.src) {
      wx.downloadFile({
        // 示例 url，并非真实存在
        url: e.currentTarget.dataset.src,
        success: function (res) {
          const filePath = res.tempFilePath
          wx.openDocument({
            filePath: filePath,
            success: function (res) {
              console.log('打开文档成功')
            }
          })
        },
        fail: function () {
          console.log('打开失败')
        }
      })
    }
  },
  onLoad:function(options){
    var eamil = wx.getStorageSync("eamil");
    this.setData({
      mailAddress: eamil,
    });
    this.getFileUrl();
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //获取pdf文件路径
  getFileUrl:function(){
    var data = {
      "applyOwid": wx.getStorageSync("yhRefOwid")
    }
    common.ajax('zustswyt/bckjBizBm/getApply', data, function (res) {
      if (res.data.backCode == 0) {
        this.setData({
          filePath: res.data.bean,
        });
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  }
})