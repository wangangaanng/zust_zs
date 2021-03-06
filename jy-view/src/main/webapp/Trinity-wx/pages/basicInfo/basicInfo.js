// pages/basicInfo/basicInfo.js
var common = require('../../libs/common/common.js');
import WxValidate from '../../utils/WxValidate';
const app = getApp();
//确认身份证弹出
import Dialog from '../../miniprogram_npm/vant-weapp/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentDate: new Date().getTime(),
    faceImg: '', //临时页面显示的身份证正面
    faceBck: '', //临时页面显示的身份证方面
    houseHold: "", //临时页面显示的户籍证明

    upFaceImg: "", //上传身份证正面
    upFaceBck: '', //身份证反面
    upHouseHold: "", //户籍证明
    form: {
      xm: '', //姓名
      sfzh: '', //身份证号
      mz: '', //名族
      xb: '1', //性别
      jtzz: '', //通讯地址
      yx: '', //邮箱
      qq: '', //qq
      lxdh: '', //联系电话
    },
    errorList: '', //错误信息重组
    idType: "1", //1 身份证 2户籍
    showIdError: true, //显示
    showHJError: true, //显示
  },

  //完善基本信息
  basicForm(e) {
    let that = this.data;
    let nullMark = 0;
    let imgError = ''; //图片未传的提示
    const params = e.detail.value;
    //身份证（户籍）必传
    if ((this.data.upFaceImg == "" || this.data.upFaceBck == "") && this.data.idType == "1") {
      console.log(this.data.upFaceBck);
      this.setData({
        showIdError: false,
      });
      imgError = "身份证正反面必传";
      nullMark = 1;
    } else {
      this.setData({
        showIdError: true,
      });
    }
    //户籍必传
    if (this.data.upHouseHold == "" && this.data.idType == "2") {
      this.setData({
        showHJError: false,
      });
      imgError = "户籍证明必传";
      nullMark = 1;
    } else {
      this.setData({
        showHJError: true,
      });
    }
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0];
      common.errorHash(this.WxValidate.errorList, this);
      return;
    } else {
      this.setData({
        errorList: {}
      });
      if (nullMark == 1) {
        common.toast(imgError, 'none', 2000)
        return;
      }
    }
    Dialog.confirm({
      title: '确认信息',
      message: '请确认身份证号:' + params.sfzh + '\n请确认手机号:' + params.lxdh
    }).then(() => {
      console.log(params);
      params.yhRefOwid = wx.getStorageSync("yhRefOwid");
      params.sfzzm = that.upFaceImg;
      params.sfzfm = that.upFaceBck;
      params.hjzm = that.upHouseHold;
      delete params['1'];
      delete params['2']
      common.ajax('zustswyt/bckjBizJbxx/finishInfo', params, function (res) {
        if (res.data.backCode == 0) {
          wx.setStorageSync('email', params.yx);
          wx.redirectTo({
            url: '../contactors/contactors',
          })
        } else {
          common.toast(res.data.errorMess, 'none', 2000)
        }
      });
    }).catch(() => {// on cancel
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    this.initValidate();
    //获取基本信息填充
    common.getInfoBasic(this,function(data){
      if (data.xb) {
        data['xb'] = data['xb'].toString();//性别转string
      }
      that.data.form = data;
      if (data.sfzzm) {
        that.setData({
          faceImg: common.imgPath + data.sfzzm, //临时页面显示的身份证正面
          upFaceImg: data.sfzzm,//上传身份证正面
        });
      }
      if (data.sfzfm) {
        that.setData({
          faceBck: common.imgPath + (data.sfzfm.replace("\\", "/")),//临时页面显示的身份证方面
          upFaceBck: data.sfzfm,//身份证反面
        });
      }
      if (data.hjzm) {
        that.setData({
          houseHold: common.imgPath + (data.hjzm.replace("\\", "/")),//临时页面显示的户籍证明
          upHouseHold: data.hjzm,//户籍证明 
        });
      }

      if (data.hjzm) {//如果有户籍证明就默认显示户籍证明 其他情况就都是选身份证默认
        that.setData({
          idType: '2',//户籍证明
        });
      } else {
        that.setData({
          idType: '1',//身份证
        });
      }
      that.setData({
        form: data,
      });
    });
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
        wx.showLoading({
          title: '上传中',
        })
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths;
        if (type == "2") {//身份证正面
          that.setData({
            faceImg: tempFilePaths[0],
          });
          uploadOcr(that, tempFilePaths, type);
        } else {
          common.uploadFile(res.tempFilePaths, 1, function (res) {
            let data = JSON.parse(res.data)
            if (data.backCode == 0) {//3 身份证反面 1 户籍证明]
              switch (type) {
                case "1":
                  that.data.upHouseHold = data.bean.filePath;
                  that.setData({
                    houseHold: tempFilePaths[0],
                    upHouseHold: that.data.upHouseHold
                  });
                  break;
                case "3":
                  that.data.upFaceBck = data.bean.filePath;
                  that.setData({
                    faceBck: tempFilePaths[0],
                    upFaceBck: that.data.upFaceBck
                  });
                  break;
              }
            } else {
              common.toast('上传失败', 'none', 2000);
            }
          });
        }
      }
    })
  },
  //单选框 选择性别
  sexSelect(event) {
    this.setData({
      ['form.xb']: event.detail
    });
  },
  //选择证件类型
  idTypeSelect(event) {
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
      xm: {
        required: true,
      },
      sfzh: {
        required: true,
        idcard: true
      },
      xb: {
        required: true
      },
      mz: {
        required: true
      },
      jtzz: {
        required: true
      },
      lxdh: { //联系电话
        required: true,
        tel: true,
      },
      yx: {
        required: true,
        email: true
      }
    }
    this.WxValidate = new WxValidate(rules, '')
  }
})

//身份证正面上传识别
function uploadOcr(that, path, type) {
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
    method: 'POST',
    formData: {
      "data": JSON.stringify(jsonObj)
    },
    success: function (res) {
      wx.hideLoading();
      let d = JSON.parse(res.data);
      if (d.backCode == 0) {
        //是被信息回显  
        ocrImgStatus(d, d.bean.image_status, that);
      } else {
        wx.showModal({
          title: '提示',
          content: '上传失败,请重新上传',
          showCancel: false
        });
        return;
      }
    },
    fail: function (e) {
      wx.hideLoading();
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


function ocrImgStatus(d, status, that) {
  let statusStr = "";
  //身份证识别返回
  if (status != "normal") {
    that.setData({
      'upFaceImg': '',
    })
    switch (status) {
      case "reversed_side":
        statusStr = "身份证正反面颠倒，请重新选择";
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
        statusStr = status;
        that.setData({
          'upFaceImg': d.bean.fileName,
        })
        break;
    }
    common.toast(statusStr, 'none', 2000);
  } else {
    that.setData({
      'upFaceImg': d.bean.fileName,
    })
  }

  //身份证信息填充
  if (d.bean["姓名"] && d.bean["姓名"].words != "无") {
    that.setData({
      ['form.xm']: d.bean["姓名"].words
    })
  }
  if (d.bean["公民身份号码"] && d.bean["公民身份号码"].words != "无") {
    that.setData({
      ['form.sfzh']: d.bean["公民身份号码"].words
    })
  }
  if (d.bean["住址"] && d.bean["住址"].words != "无") {
    that.setData({
      ['form.jtzz']: d.bean["住址"].words
    })
  }
  if (d.bean["民族"] && d.bean["民族"].words != "无") {
    that.setData({
      ['form.mz']: d.bean["民族"].words
    })
  }
  if (d.bean["性别"] && d.bean["性别"].words != "无") {
    switch (d.bean["性别"].words) {
      case "女":
        that.data.form.xb = "2"
        break;
      case "男":
        that.data.form.xb = "1"
        break;
    }
    that.setData({
      ['form.xb']: that.data.form.xb
    })
  }
}