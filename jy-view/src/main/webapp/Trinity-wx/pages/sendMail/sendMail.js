// 预览发送到邮箱
var common = require('../../libs/common/common.js');
var utils = require('../../utils/util.js');
import WxValidate from '../../utils/WxValidate';
//确认邮箱弹出框
import Dialog from '../../miniprogram_npm/vant-weapp/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mailAddress: "",
    imgPath: common.imgPath,
    filePath: '',
    sendMailType:'',//发送邮箱还是发送面试通知到
    stringLable:'',//报名表还是面试通知单
    tipString:'',//头部提示
  },
  
  //发送邮箱
  sendMailForm(e) {
    let that = this;
    const params = e.detail.value;
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      common.toast(error.msg, 'none', 2000)
      return false
    }
    Dialog.confirm({
      title: '确认邮箱地址',
      message: this.data.stringLable+'将发送到：'+this.data.mailAddress
    }).then(() => {
      that.setData({
        "mailAddress": params.email
      })
      wx.setStorageSync('email', params.email)
      // 确认发送邮箱
      that.ajaxSend(params.email);
    }).catch(() => {// on cancel
    });

  },

  ajaxSend: function (email) {
    var that = this;
    var data = {
      "applyOwid": wx.getStorageSync("applyOwid"),//申请表owid
      "yx": email
    }
    common.ajax('zustswyt/bckjBizBm/sendView', data, function (res) {
      if (res.data.backCode == 0) {
        common.toast(that.data.stringLable +'已成功发送到' + email+',请注意查收', 'none', 3000)
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
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

  onLoad: function (options) {
    var that = this;
    that.initValidate();

    //判断状态
    common.getProcssState(function(res){
      console.log(res);
    })

    //显示默认邮箱地址
    let email = wx.getStorageSync("email");
    that.setData({
      mailAddress: email,
    });

    //获取判断页面type sign offer
    let pageType = options.type;
    that.setData({
      sendMailType: options.type
    });

    switch (pageType) {
      case "'sign'": //报名表
        that.setData({
          stringLable: '报名表',
          tipString:'你的报名表已成功提交'
        });
        break;
      case "'offer'": //面试通知单
        that.setData({
          stringLable:'面试通知单',
          tipString: '恭喜你已成功通过初审'
        });
        break;
    }

    that.getFileUrl(pageType);
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //获取pdf文件路径
  getFileUrl: function (type) {
    let that = this;
    let urlArr = ['zustswyt/bckjBizBm/getApply','zustswyt/bckjBizBm/notice'];
    let curUrl = "";
    switch (type){
      case "'sign'":
        curUrl = urlArr[0]
      break;
      case "'offer'":
        curUrl = urlArr[1]
      break;
    }
    var data = {
      "applyOwid": wx.getStorageSync("applyOwid"),//申请表owid
    }
    common.ajax(curUrl, data, function (res) {
      if (res.data.backCode == 0) {
        that.setData({
          filePath: res.data.bean,
        });
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      email: {
        required: true,
        email: true,
      }
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      email: {
        required: '请输入邮箱地址'
      }
    }
    this.WxValidate = new WxValidate(rules, messages)
  }
})