// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
import WxValidate from '../../libs/wx-validate/WxValidate'
const app = getApp()
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    minDate: new Date().getTime(),
    imgPath: imgPath,
    zwGzznColumns: [],
    zwGzxzColumns: [],
    zwNlyqColumns: [],
    zwXlyqColumns: [],
    zwGznxColumns: [],
    zwYyyqColumns: [],

    show: {
      bottom: false,
      gzzn: false,
      gzxz: false,
      nlyq: false,
      xlyq: false,
      gznx: false,
      yyyq: false,
      zwSxsj: false,
    },
    area: '请选择',
    areaList: {},
    loading: true,
    value: 330106,
    gzznStr: '请选择',
    gzxzStr: '请选择',
    nlyqStr: '请选择',
    xlyqStr: '请选择',
    gznxStr: '请选择',
    yyyqStr: '请选择',
    zwSxsjStr: '请选择',
    form: {
      zwbt: '',
      zwPro: '',
      zwCity: '',
      zwArea: '',
      zwGzzn: '',
      zwGzxz: '',
      zwLxyx: '',
      zwXs: '',
      zwZprs: '',
      zwNlyq: '',
      zwXlyq: '',
      zwGznx: '',
      zwYyyq: '',
      zwGwzz: '',
      zwSxsj: '',
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
    params.zwlx = 0
    params.zwXs = parseInt(params.zwXs)
    params.qyxxRefOwid = wx.getStorageSync('yhOwid')
    common.ajax('zustjy/bckjBizJob/addOneJob', params, function (res) {
      if (res.data.backCode == 0) {
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: "职位发布成功",
          success(res) {
            if (res.confirm) {
              // wx.navigateBack({
              //   delta:1
              // })
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
      zwbt: {
        required: true,
      },
      zwPro: {
        required: true,
      },
      zwCity: {
        required: true
      },
      zwArea: {
        required: true
      },
      zwGzzn: {
        required: true
      },
      zwGzxz: {
        required: true
      },
      zwXs: {
        required: true
      },
      zwZprs: {
        required: true
      },
      zwLxyx: {
        required: true,
        email: true,
      },
      zwNlyq: {
        required: true
      },
      zwXlyq: {
        required: true
      },
      zwGznx: {
        required: true
      },
      zwYyyq: {
        required: true
      },
      zwGwzz: {
        required: true,
        maxlength: 200,
      },
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      zwbt: {
        required: '请填写职位名称'
      },
      zwPro: {
        required: '请选择所在省市区',
      },
      zwCity: {
        required: '请选择所在省市区',
      },
      zwArea: {
        required: '请选择所在省市区',
      },
      zwGzzn: {
        required: '请填写职能类别',
      },
      zwGzxz: {
        required: '请填写工作性质',
      },
      zwLxyx: {
        required: '请填写邮箱',
        email: '请填写正确邮箱',
      },
      zwXs: {
        required: '请填写薪水',
      },
      zwZprs: {
        required: '请填写招聘人数',
      },
      zwNlyq: {
        required: '请填写年龄要求',
      },
      zwXlyq: {
        required: '请选择学历要求',
      },
      zwGznx: {
        required: '请选择工作年限',
      },
      zwYyyq: {
        required: '请选择语言要求',
      },
      zwGwzz: {
        required: '请填写职位详情',
        maxlength: 200,
      }

    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  onConfirm(e) {
    if (e.target.dataset.type == 1) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gzznStr: dicVal2,
        'form.zwGzzn': dicVal1
      })
      this.toggle('gzzn', false);
    } else if (e.target.dataset.type == 2) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gzxzStr: dicVal2,
        'form.zwGzxz': dicVal1
      })
      this.toggle('gzxz', false);
    } else if (e.target.dataset.type == 3) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        nlyqStr: dicVal2,
        'form.zwNlyq': dicVal1
      })
      this.toggle('nlyq', false);
    } else if (e.target.dataset.type == 4) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        xlyqStr: dicVal2,
        'form.zwXlyq': dicVal1
      })
      this.toggle('xlyq', false);
    } else if (e.target.dataset.type == 5) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gznxStr: dicVal2,
        'form.zwGznx': dicVal1
      })
      this.toggle('gznx', false);
    } else if (e.target.dataset.type == 6) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        yyyqStr: dicVal2,
        'form.zwYyyq': dicVal1
      })
      this.toggle('yyyq', false);
    } else if (e.target.dataset.type == 7) {
      this.setData({
        area: `${e.detail.values[0].name}-${e.detail.values[1].name}-${e.detail.values[2].name}`,
        'form.zwPro': e.detail.values[0].name,
        'form.zwCity': e.detail.values[1].name,
        'form.zwArea': e.detail.values[2].name
      })
      this.toggle('bottom', false);
    } else if (e.target.dataset.type == 8) {
      console.log(e)
      var date = util.formatTime1(new Date(e.detail))
      this.setData({
        zwSxsjStr: date,
        'form.zwSxsj': date
      })
      this.toggle('zwSxsj', false);
    }

  },
  onCancel(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('gzzn', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('gzxz', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('nlyq', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('xlyq', false);
    } else if (e.target.dataset.type == 5) {
      this.toggle('gznx', false);
    } else if (e.target.dataset.type == 6) {
      this.toggle('yyyq', false);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bottom', false);
    } else if (e.target.dataset.type == 8) {
      this.toggle('zwSxsj', false);
    }
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
    this.initValidate()
    xjhtjList(this)
  },
  showBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('gzzn', true);
    } else if (e.target.dataset.type == 2) {
      this.toggle('gzxz', true);
    } else if (e.target.dataset.type == 3) {
      this.toggle('nlyq', true);
    } else if (e.target.dataset.type == 4) {
      this.toggle('xlyq', true);
    } else if (e.target.dataset.type == 5) {
      this.toggle('gznx', true);
    } else if (e.target.dataset.type == 6) {
      this.toggle('yyyq', true);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bottom', true);
    } else if (e.target.dataset.type == 8) {
      this.toggle('zwSxsj', true);
    }
  },

  hideBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('gzzn', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('gzxz', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('nlyq', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('xlyq', false);
    } else if (e.target.dataset.type == 5) {
      this.toggle('gznx', false);
    } else if (e.target.dataset.type == 6) {
      this.toggle('yyyq', false);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bottom', false);
    } else if (e.target.dataset.type == 8) {
      this.toggle('zwSxsj', false);
    }
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },
  preview() {
    var previewImageList = [];
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

var xjhtjList = function (that) {
  var data = {};
  common.ajax('zustjy/bckjBizJob/xjhtjList', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      var arr=[];
      if (data.bean && data.bean.length > 0) {
        for (var i = 0; i < data.bean.length;i++){
          var obj={};
          console.log(data.bean[i])
          for (var a in data.bean[i]){
            obj.zdytj = a;
            obj.tjsd = data.bean[i][a];
          }
          arr.push(obj)
        }
        that.setData({
          list: data.bean
        })
        // for (var i in data.bean) {
        //   var obj = {}
        //   obj.dicVal1 = data.bean[i].dicVal1
        //   obj.dicVal2 = data.bean[i].dicVal2
        //   that.data.zwNlyqColumns.push(obj)
        // }
        // that.setData({
        //   zwNlyqColumns: that.data.zwNlyqColumns
        // })
      }
      console.log(arr)
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}
