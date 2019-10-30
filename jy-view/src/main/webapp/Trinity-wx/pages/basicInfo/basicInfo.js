// pages/basicInfo/basicInfo.js
var common = require('../../libs/common/common.js');
import WxValidate from '../../utils/WxValidate';
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentDate: new Date().getTime(),
    name: '',//姓名
    idcard: '',//身份证号
    sex: '1',//性别
    race: '',//名族
    address: '',//通讯地址
    phone: '',//联系电话
    tel: '',//手机
    email: '',//电子邮箱
    qq: '',//qq号
    errorList: '',//错误信息重组
    faceImg: '',//身份证正面
    faceBck: '',//身份证方面
    idType:"1",//1 身份证 2户籍
    houseHold:"",//户籍证明
    showIdError:true,//显示
    showHJError: true,//显示
  },

  //完善基本信息
  basicForm(e) {
    let that = this.data;
    let nullMark = 0; 
    let imgError = '';//图片未传的提示
    const params = e.detail.value;
    //身份证（户籍）必传
    if ((this.data.faceImg == "" || this.data.faceBck == "") && this.data.idType == "1") {
      this.setData({
        showIdError: false,
      });
      imgError= "身份证正反面必传";
      nullMark=1;
    }else{
      this.setData({
        showIdError: true,
      });
    }
    if (this.data.houseHold == "" && this.data.idType=="2") {
      this.setData({
        showHJError: false,
      });
      imgError = "户籍证明必传";
      nullMark = 1;
    }else{
      this.setData({
        showHJError: true,
      });
    }
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0];
      common.errorHash(this.WxValidate.errorList, this);
      return;
    }else{
      this.setData({
        errorList: {}
      });
      common.toast(imgError, 'none', 2000)
      if (nullMark==1){
        return;
      }
    }
    console.log(params);
    var data = {
      "xm": params.name
      , "sfzh": params.name
      , "mz": params.idcard
      , "xb": params.sex
      , "jtzz": params.address 
      , "sfzzm": that.faceImg //身份证正面
      , "sfzfm": that.faceBck //身份证反面
      , "hjzm": that.houseHold//户籍证明
      , "yx": params.email//邮箱
      , "qq": params.qq
      , "lxdh": params.phone//联系电话
      , "yhRefOwid": ''//用户owid
    }
    common.ajax('zustswyt/bckjBizJbxx/finishInfo', data, function (res) {
      if (res.data.backCode == 0) {
        wx.navigateTo({
          url: '../contactors/contactors',
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initValidate();
    //获取基本信息填充
    //getInfoBasic();
  },

  //点击上传图片
  takePhoto: function (event) {
    var that = this;
    var type = event.currentTarget.dataset.type;
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['compressed'], // 可以指定是原图还是压缩图
      sourceType: ['camera', 'album'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths;
        switch (type){
          case 1:

          break;
          case 2:
            that.setData({
              faceImg: tempFilePaths,
            });
            break;
          case 3:
            that.setData({
              faceBck: tempFilePaths,
            });
          break;
        }
        upload(that, tempFilePaths,type);
      }
    })
  },
  //单选框 选择性别
  sexSelect(event) {
    this.setData({
      sex: event.detail
    });
  },
  //选择证件类型
  idTypeSelect(event){
    this.setData({
      idType: event.detail
    });
  },
  //下一步 完善联系人
  nextStep: function () {
    wx.navigateTo({
      url: '../contactors/contactors',
    })
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      name: {
        required: true,
      }
      , idcard: {
        required: true,
        idcard: true
      }
      , sex: {
        required: true
      }
      , race: {
        required: true
      }
      , address: {
        required: true
      }
      , phone: {//联系电话
        required: true
      }
      , tel: {//手机
        required: true,
        tel: true,
      }
      , email: {
        required: true,
        email: true
      }
      , qq: {
        required: true
      }
    }
    this.WxValidate = new WxValidate(rules, '')
  }
})

//图片上传
function upload(that,path,type) {
  wx.showToast({
    icon: "loading",
    title: "正在上传"
  }),
  wx.uploadFile({
    url: app.globalData.ApiUrl + "zustcommon/common/idCardOcr",
    filePath: path[0],
    name: 'file',
    header: { "Content-Type": "multipart/form-data" },
    formData: {
      //和服务器约定的token, 一般也可以放在header中
      'session_token': wx.getStorageSync('session_token')
    },
    success: function (res) {
      // if (res.statusCode != 200) {
      //   wx.showModal({
      //     title: '提示',
      //     content: '上传失败',
      //     showCancel: false
      //   });
      //   that.setData({
      //     src: "../../images/img-hean_green.png",
      //   });
      //   return;
      // } else {
      //   var data = res.data;
      //   common.ajax("zustcommon/common/idCardOcr", { "file": res.data, "type": type }, function (res) {
      //     if (res.backCode == 0) {
      //       wx.showToast({
      //         title: '上传成功',
      //       });
      //     }
      //   })
      // }

    },
    fail: function (e) {
      console.log(e);
      wx.showModal({
        title: '提示',
        content: '上传失败',
        showCancel: false
      });
      that.setData({
        src: "../../images/img-hean_green.png",
      });
    },
    complete: function () {
      wx.hideToast();  //隐藏Toast
    }
  })
}

//获取基本信息
function getInfoBasic(){
  var data = {
    "yhRefOwid": common.unionid
  }
  common.ajax('zustswyt/bckjBizJbxx/getInfo', data, function (res) {
    if (res.data.backCode == 0) {
      
    } else {
      common.toast(res.data.errorMess, 'none', 2000)
    }
  });
}