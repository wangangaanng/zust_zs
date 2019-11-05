// 上传报名表和承诺书
var common = require('../../libs/common/common.js');
import WxValidate from '../../utils/WxValidate';
//确认邮箱弹出框
import Dialog from '../../miniprogram_npm/vant-weapp/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {

    signImg: '', //临时报名表图片
    promiseImg: '', //承诺书图片

    signInfo: '请上传报名表签字',
    promiseInfo: '请上传承诺书签字',

    class1: "",//报名表提示文字颜色
    class2: "",//承诺书提示文字颜色

    state:'',//流程状态

    form: {
      bmbZp: '',//报名表照片
      cnszp: ''//承诺书照片
    }
  },

  //点击提交
  basicForm(e) {
    const params = e.detail.value;
    //验证是否上传
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      common.toast(error.msg, 'none', 2000)
      return false
    }
    Dialog.confirm({
      title: '确认上传',
      message: "请确认上传报名表签字和承诺书签字，确认提交后将不能修改！"
    }).then(() => {
      // 确认图片上传
      upLoadImgs(params);
    }).catch(() => {
      // on cancel
    });

  },

  //选择图片
  choseImageSign(event) {
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
            switch (type) {
              case "1":
                that.setData({
                  ["form.bmbZp"]: imgUrl,
                  signInfo: '报名表签字上传成功',
                  class1:'green'
                })
                break;
              case "2":
                that.setData({
                  ["form.cnszp"]: imgUrl,
                  promiseInfo: '承诺书签字上传成功',
                  class2: 'green'
                })
                break;
            }
          } else {
            switch (type) {
              case "1":
                that.setData({
                  signInfo: '报名表签字上传失败，请重传',
                  class1: 'red'
                })
                break;
              case "2":
                that.setData({
                  promiseInfo: '承诺书签字上传失败，请重传',
                  class2: 'red'
                })
                break;
            }
            common.toast('上传失败', 'none', 2000);
          }
        });

        //图片临时显示
        switch (type) {
          case "1":
            that.setData({
              signImg: tempFilePaths[0]
            });
            break;
          case "2":
            that.setData({
              promiseImg: tempFilePaths[0]
            });
            break;
        }
      }
    })
  },

  previewImage: function (e) {
    var currentSrc = e.target.dataset.src
    console.log(e);
    wx.previewImage({
      current: currentSrc,
      urls: [currentSrc]
    })
  },
  onShareAppMessage: function () {

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    this.initValidate();
    //判断状态
    common.getProcssState(function(res){
      var data = res.data.bean;
      if (data.state>2){
        that.setData({
          'signImg': common.imgPath + data.bmbZp,
          'promiseImg': common.imgPath + data.cnszp,
          'state': data.state,
          'signInfo': '报名表签字已上传成功',
          'promiseInfo': '承诺书签字已上传成功',
          'class1': 'green',
          'class2': 'green'
        })
      }
    });
  },

  initValidate() {
    // 验证字段的规则
    const rules = {
      bmbZp: {
        required: true
      }
      , cnszp: {
        required: true
      }
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      bmbZp: {
        required: '请上传报名表签字',
      }
      , cnszp: {
        required: '请上传承诺书签字',
      }
    }
    this.WxValidate = new WxValidate(rules, messages)
  }
})

//上传图片
function upLoadImgs(params) {
  params.applyOwid = wx.getStorageSync("applyOwid");//申请表owid
  common.ajax('zustswyt/bckjBizBm/promise', params, function (res) {
    if (res.data.backCode == 0) {
      Dialog.alert({
        message: '报名表签字和承诺书签字已成功上传！'
      }).then(() => {
        wx.redirectTo({
          url: '../Process/Process',
        })
      });
    }else{
      common.toast(res.data.errorMess, 'none')
    }
  });
}