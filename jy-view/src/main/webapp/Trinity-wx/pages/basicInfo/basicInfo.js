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
      , "yhRefOwid": wx.getSystemInfoSync('yhRefOwid')//用户owid
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
    getInfoBasic();
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
          case 2: //身份证正面
            that.setData({
              faceImg: tempFilePaths,
            });
            break;
          case 3: //身份证反面
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
  var jsonObj = {
    "file": path[0],
    "type": type
  }
  wx.showLoading({
    title: '识别中',
  })
  wx.uploadFile({
    url: app.globalData.ApiUrl + "zustcommon/common/picUpload",
    filePath: path[0],
    name: 'file',
    header: { "Content-Type": "multipart/form-data" },
    formData: {
      "data": JSON.stringify(jsonObj)
    },
    success: function (res) {
      if (res.statusCode != 200) {
        wx.showModal({
          title: '提示',
          content: '上传失败',
          showCancel: false
        });
        that.setData({
          src: "../../images/img-hean_green.png",
        });
        return;
      } else {
        let d = JSON.parse(res.data);
        console.log(d);
        let statusStr = "";
        switch (d.bean.image_status){
          case "reversed_side":
            statusStr = "身份证正反面颠倒，请重新选择"
            break;
          case "non_idcard":
            statusStr = "上传的图片中不包含身份证，请重新选择"
            break;
          case "blurred":
            statusStr = "身份证模糊，请重新选择"
            break;
          case "other_type_card":
            statusStr = "上传照片为其他类型证照，请重新选择"
            break;
          case "over_exposure":
            statusStr = "身份证关键字段反光或过曝，请重新选择"
            break; 
          case "over_dark":
            statusStr = "身份证欠曝（亮度过低），请重新选择"
            break;
          default:
            statusStr = d.bean.image_status;
            break;          
        }
        common.toast(statusStr, 'none', 2000);

        if (d.bean["姓名"] && d.bean["姓名"].words != "无") {
            that.setData({
              'name': d.bean["姓名"].words
            })
        }
        if (d.bean["公民身份号码"] && d.bean["公民身份号码"].words != "无") {
          that.setData({
            'idcard': d.bean["公民身份号码"].words
          })
        }
        if (d.bean["住址"] && d.bean["住址"].words != "无") {
          that.setData({
            'address': d.bean["住址"].words
          })
        }
        if (d.bean["民族"] && d.bean["民族"].words != "无") {
          that.setData({
            'race': d.bean["民族"].words
          })
        }
        if (d.bean["性别"] && d.bean["性别"].words != "无") {
          switch (d.bean["性别"].words){
            case "女":
              that.bean.sex = "2"
              break;
            case "男":
              that.bean.sex = "1"
              break;  
          }

          that.setData({
            'sex': that.bean.sex
          })
        }
        
        
      }
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
    }
  })
}

//获取基本信息
function getInfoBasic(){
  var data = {
    "yhRefOwid": wx.getStorageSync('yhRefOwid')
  }
  common.ajax('zustswyt/bckjBizJbxx/getInfo', data, function (res) {
    if (res.data.backCode == 0) {
      
    } else {
      common.toast(res.data.errorMess, 'none', 2000)
    }
  });
}