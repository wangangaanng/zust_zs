// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
import WxValidate from '../../libs/wx-validate/WxValidate'
const app = getApp()
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    hidden1: false,
    imgPath: imgPath,
    gsxzColumns: [],
    hylbColumns: [],
    gsgmColumns: [],
    show: {
      bottom: false,
      gsxz: false,
      hylb: false,
      gsgm: false,
    },
    znlb: '请选择',
    area: '请选择',
    areaList: {},
    loading: true,
    value: 330106,
    gsxzStr: '请选择',
    hylbStr: '请选择',
    gsgmStr: '请选择',
    form: {
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
      qyZczj: '',
      qylxfs:''
    },
  },
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },
  submitForm(e) {
    const params = e.detail.value

    console.log(params)

    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      this.showModal(error)
      return false
    }
    params.owid=wx.getStorageSync('yhOwid')
    common.ajax('zustjy/bckjBizQyxx/fixCompany', params, function (res) {
      if (res.data.backCode == 0) {
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: "修改成功",
          success(res) {
            if (res.confirm) {
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
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      qyTysh: {
        required: true,
      },
      qyFrsfz: {
        required: true,
      },
      qyFrdbxm: {
        required: true
      },
      qymc: {
        required: true
      },
      qyProv: {
        required: true
      },
      qyCity: {
        required: true
      },
      qyArea: {
        required: true
      },
      qydz: {
        required: true
      },
      qyZczj: {
        required: true
      },
      qyLxr: {
        required: true
      },
      qyLxrdh: {
        required: true,
        tel: true,
      },
      qyYx: {
        required: true,
        email: true,
      },
      qylxfs: {
        required: true
      },
      qyGsxz: {
        required: true
      },
      qyHylb: {
        required: true
      },
      qyGsgm: {
        required: true
      },
      qyGsjs: {
        required: true,
        maxlength: 200,
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
        required: '请填写法人身份证号',
      },
      qyFrdbxm: {
        required: '请填写法人姓名',
      },
      qymc: {
        required: '请填写企业名称',
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
      qydz: {
        required: '请填写公司地址',
      },
      qyZczj: {
        required: '请填写注册资本',
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
        maxlength: 200,
      },
      qyYyzzzp: {
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
    } else if (e.target.dataset.type == 4) {
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
    getOneCompany(this)
    getByType1(this)
    getByType2(this)
    getByType3(this)
  },
  showBottom(e) {
    if (e.target.dataset.type == 1) {
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
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    wx.request({
      url: app.globalData.imgPath + 'getAllRegion.json',
      success: response => {
        this.setData({
          loading: false,
          areaList: response.data.data
        });
      }
    });

  },
  preview(){
    var previewImageList=[];
    var image = this.data.imgPath + this.data.form.qyYyzzzp;
    previewImageList.push(image)
    wx.previewImage({
      urls: previewImageList // 需要预览的图片http链接列表
    })
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

var getOneCompany = function (that) {
  var data = { "owid": wx.getStorageSync('yhOwid') };
  common.ajax('zustjy/bckjBizQyxx/getOneCompany', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      that.setData({
        form:data.bean,
        gsxzStr: data.bean.qyGsxzStr,
        hylbStr: data.bean.qyHylbStr,
        gsgmStr: data.bean.qyGsgmStr,
        area: `${data.bean.qyProv}-${data.bean.qyCity}-${data.bean.qyArea}`
      })
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}

var getByType1 = function (that) {
  var data = { "dicType": "20000" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
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