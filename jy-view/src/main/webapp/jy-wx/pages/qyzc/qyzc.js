// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
import WxValidate from '../../libs/wx-validate/WxValidate'
const app = getApp()
var url = app.globalData.ApiUrl;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    hidden1: false,
    yyzz:'../../static/uploadImg1.png',
    sfz:'../../static/uploadImg2.png',
    column1: ['杭州', '宁波', '温州', '嘉兴', '湖州'],
    gsxzColumns:[],
    hylbColumns:[],
    gsgmColumns:[],
    show: {
      bottom: false,
      gsxz:false,
      hylb:false,
      gsgm:false,
    },
    znlb: '请选择',
    area: '请选择',
    areaList: {},
    loading: true,
    value: 330106,
    gsxzStr: '请选择',
    hylbStr: '请选择',
    gsgmStr: '请选择',
    form:{
      qyTysh: '',
      qyFrsfz: '',
      qyFrdbxm: '',
      qymc: '',
      qyProv: '',
      qyCity: '',
      qyArea: '',
      qydz: '',
      qyLxr: '',
      qyLxrdh: '',
      qyYx: '',
      qyGsxz: '',
      qyHylb: '',
      qyGsgm: '',
      qyGsjs: '',
      qylxfs: '',
    },
    btndisabled: false
  },
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },
  submitForm(e) {
    var that = this
    const params = e.detail.value

    console.log(params)

    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      this.showModal(error)
      return false
    }

    if (params.qyFrsfz.trim().length!=18){
      wx.showModal({
        content: '法人身份证长度有误',
        showCancel: false,
      })
      return false
    }

    wx.showModal({
      title: '提示',
      content: `您的企业税号为${params.qyTysh}，法人身份证号为${params.qyFrsfz}。请确认您的注册信息，点击确认进行注册，点击取消进行修改。`,
      success(res) {
        if (res.confirm) {
          console.log('用户点击确定')
          common.ajax('zustjy/bckjBizQyxx/companyRegister', params, function (res) {
            if (res.data.backCode == 0) {
              that.setData({
                btndisabled: true
              })
              wx.showModal({
                title: '提示',
                showCancel: false,
                content: "注册成功，待后台人员审核通过，便可登录。",
                success(res) {
                  if (res.confirm) {
                    wx.reLaunch({
                      url: '../index/index',
                    })
                    console.log('用户点击确定')
                  } else if (res.cancel) {
                    console.log('用户点击取消')
                  }
                }
              })
            } else {
              wx.showToast({
                title: res.data.errorMess,
                icon: 'none',
                duration: 2000
              })
            }
          });
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
    
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      // qyTysh: {
      //   required: true,
      // },
      // qyFrsfz: {
      //   required: true
      // },
      // qyFrdbxm: {
      //   required: true
      // },
      // qymc: {
      //   required: true
      // },
      // qydz: {
      //   required: true
      // },
      // qyZczj: {
      //   required: true
      // },
      // qyProv: {
      //   required: true
      // },
      // qyCity: {
      //   required: true
      // },
      // qyArea: {
      //   required: true
      // },
      // qyLxr: {
      //   required: true
      // },
      // qyLxrdh: {
      //   required: true,
      //   tel: true,
      // },
      // qyYx: {
      //   required: true,
      //   email: true,
      // },
      // qylxfs: {
      //   required: true
      // },
      // qyGsxz: {
      //   required: true
      // },
      // qyHylb: {
      //   required: true
      // },
      // qyGsgm: {
      //   required: true
      // },
      qyGsjs: {
        required: true,
        minlength:150
      },
      qyYyzzzp: {
        required: true
      },
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      qyTysh: {
        required: '请填写企业统一信用代码'
      },
      qyFrsfz: {
        required: '请填写法人身份证号'
      },
      qyFrdbxm: {
        required: '请填写法人姓名',
      },
      qymc: {
        required: '请填写企业名称',
      },
      qydz: {
        required: '请填写公司地址',
      },
      qyZczj: {
        required: '请填写注册资本',
      },
      qyProv: {
        required: '请选择所在省市区',
      },
      qyCity: {
        required: '请选择所在省市区',
      },
      qyArea: {
        required: '请选择所在省市区',
      },
      qyLxr: {
        required: '请填写联系人',
      },
      qyLxrdh: {
        required: '请填写手机',
        tel: '请填写正确手机号',
      },
      qyYx: {
        required: '请填写邮箱',
        email: '请填写正确邮箱',
      },
      qylxfs: {
        required: '请填写固定电话',
      },
      qyGsxz: {
        required: '请选择公司性质',
      },
      qyHylb: {
        required: '请选择行业类别',
      },
      qyGsgm: {
        required: '请选择公司规模',
      },
      qyGsjs: {
        required: '请填写公司介绍',
        minlength: "公司介绍不得少于150字",
      },
      qyYyzzzp:{
        required: '请上传企业营业执照',
      }
      
    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  onConfirm(e) {
    if (e.target.dataset.type == 1) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gsxzStr: dicVal2,
        'form.qyGsxz': dicVal1
      })
      this.toggle('gsxz', false);
    } else if (e.target.dataset.type == 2) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        hylbStr: dicVal2,
        'form.qyHylb': dicVal1
      })
      this.toggle('hylb', false);
    } else if (e.target.dataset.type == 3) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gsgmStr: dicVal2,
        'form.qyGsgm': dicVal1
      })
      this.toggle('gsgm', false);
    } else if (e.target.dataset.type == 4)  {
      this.setData({
        area: `${e.detail.values[0].name}-${e.detail.values[1].name}-${e.detail.values[2].name}`,
        'form.qyProv': e.detail.values[0].name,
        'form.qyCity': e.detail.values[1].name,
        'form.qyArea': e.detail.values[2].name
      })
      this.toggle('bottom', false);
    }
    
  },
  onCancel(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('gsxz', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('hylb', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('gsgm', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('bottom', false);
    }
  },
  toggle(type, show) {
    this.setData({
      [`show.${type}`]: show,
      hidden1: show
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initValidate()
    getByType1(this)
    getByType2(this)
    getByType3(this)
  },
  showBottom(e) {
    if (e.target.dataset.type==1){
      this.toggle('gsxz', true);
    } else if (e.target.dataset.type == 2) {
      this.toggle('hylb', true);
    } else if (e.target.dataset.type == 3) {
      this.toggle('gsgm', true);
    } else if (e.target.dataset.type == 4) {
      this.toggle('bottom', true);
    }    
  },

  hideBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('gsxz', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('hylb', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('gsgm', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('bottom', false);
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  upload(e){
    var that = this
    var type = e.currentTarget.dataset.type
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        var tempFilePaths = res.tempFilePaths
        console.log(res)
        var jsonObj={
          "file": tempFilePaths[0],
          "type": type
        }
        
        if(type==1){
          that.setData({
            yyzz:tempFilePaths[0]
          })
        } else if(type == 2){
          that.setData({
            sfz: tempFilePaths[0]
          })
        }
        wx.showLoading({
          title: '识别中',
        })
        wx.uploadFile({
          url: url+'zustcommon/common/picUpload',
          filePath: tempFilePaths[0],
          name: 'file',
          method: 'POST',
          formData: {
            "data": JSON.stringify(jsonObj)
          },
          success: function (res) {
            wx.hideLoading();
            var d=JSON.parse(res.data)
            if (d.bean) {
              if (type == 1) {
                if (d.bean["社会信用代码"]) {
                  if (d.bean["社会信用代码"].words) {
                    if (d.bean["社会信用代码"].words != "无") {
                      if (d.bean["社会信用代码"].words.trim().length>18){
                        d.bean["社会信用代码"].words = d.bean["社会信用代码"].words.trim().substring(0, 18)
                      }
                      that.setData({
                        'form.qyTysh': d.bean["社会信用代码"].words
                      })
                    } 
                  } 
                } 
                if (d.bean["单位名称"]) {
                  if (d.bean["单位名称"].words) {
                    if (d.bean["单位名称"].words != "无") {
                      that.setData({
                        'form.qymc': d.bean["单位名称"].words
                      })
                    }
                  }
                }
                if (d.bean["地址"]) {
                  if (d.bean["地址"].words) {
                    if (d.bean["地址"].words != "无") {
                      that.setData({
                        'form.qydz': d.bean["地址"].words
                      })
                    }
                  }
                }

                if (d.bean["注册资本"]) {
                  if (d.bean["注册资本"].words) {
                    if (d.bean["注册资本"].words != "无") {
                      that.setData({
                        'form.qyZczj': d.bean["注册资本"].words
                      })
                    }
                  }
                }
                
                if (d.bean.fileName) {
                  wx.showToast({
                    title: '上传成功',
                    icon: 'none',
                    duration: 2000
                  })
                  that.setData({
                    'form.qyYyzzzp': d.bean.fileName
                  })
                }else{
                  wx.showModal({
                    title: '提示',
                    showCancel: false,
                    content: "上传失败",
                  })
                }
              } else if (type == 2) {
                if (d.bean.image_status) {
                  var image_status = d.bean.image_status;
                  var statusStr='';
                  if (image_status == "reversed_side") {
                    statusStr="身份证正反面颠倒，请重新选择"
                  } else if (image_status == "non_idcard") {
                    statusStr ="上传的图片中不包含身份证，请重新选择"
                  } else if (image_status == "blurred") {
                    statusStr ="身份证模糊，请重新选择"
                  } else if (image_status == "other_type_card") {
                    statusStr ="上传照片为其他类型证照，请重新选择"
                  } else if (image_status == "over_exposure") {
                    statusStr ="身份证关键字段反光或过曝，请重新选择"
                  } else if (image_status == "over_dark") {
                    statusStr ="身份证欠曝（亮度过低），请重新选择"
                  }
                  wx.showModal({
                    title: '提示',
                    showCancel: false,
                    content: statusStr,
                  })
                }

                if (d.bean["姓名"]) {
                  if (d.bean["姓名"].words) {
                    if (d.bean["姓名"].words != "无") {
                      that.setData({
                        'form.qyFrdbxm': d.bean["姓名"].words
                      })
                    }
                  }
                }
                if (d.bean["公民身份号码"]) {
                  if (d.bean["公民身份号码"].words) {
                    if (d.bean["公民身份号码"].words != "无") {
                      that.setData({
                        'form.qyFrsfz': d.bean["公民身份号码"].words
                      })
                    }
                  }
                }
                if (d.bean.fileName) {
                  wx.showToast({
                    title: '上传成功',
                    icon: 'none',
                    duration: 2000
                  })
                  that.setData({
                    'form.qyFrsfzzp': d.bean.fileName
                  })
                } else {
                  wx.showModal({
                    title: '提示',
                    showCancel: false,
                    content: "上传失败",
                  })
                }
              }

            } else {
              wx.showModal({
                title: '提示',
                showCancel: false,
                content: "上传失败",
              })
            }
            
            
          },
          fail: function () {
            wx.hideLoading();
          }
        })
        
      }
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    wx.request({
      url: app.globalData.imgPath +'getAllRegion.json',
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

  }
})

var getByType1 = function (that) {
  var data = { "dicType": "20000"};
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data=res.data;
      if(data.bean&&data.bean.length>0){
        for(var i in data.bean){
          var obj={}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.gsxzColumns.push(obj)
        }
        that.setData({
          gsxzColumns: that.data.gsxzColumns
        })
      }
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}

var getByType2 = function (that) {
  var data = { "dicType": "20001" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.hylbColumns.push(obj)
        }
        that.setData({
          hylbColumns: that.data.hylbColumns
        })
      }
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}

var getByType3 = function (that) {
  var data = { "dicType": "20002" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.gsgmColumns.push(obj)
        }
        that.setData({
          gsgmColumns: that.data.gsgmColumns
        })
      }
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}