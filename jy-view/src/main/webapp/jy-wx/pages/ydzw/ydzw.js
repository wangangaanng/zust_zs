// pages/qyInfo/qyInfo.js
var Mustache = require('../../libs/mustache/mustache');
var WxParse = require('../../libs/wxParse/wxParse.js');
var common = require('../../libs/common/common.js')
import WxValidate from '../../libs/wx-validate/WxValidate'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    result: '',
    old: '',
    zphOwid:'',
    form: {
      lxr: wx.getStorageSync('qyInfo').qyLxr || '',
      lxdh: wx.getStorageSync('qyInfo').qyLxrdh || '',
      zw1:'',
      zw2: '',
      zw3: '',
      zw4: '',
      zw5: '',
      rs1: '',
      rs2: '',
      rs3: '',
      rs4: '',
      rs5: '',
    },
    list:[],
    btndisabled: false
  },
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },
  gettjsd(e) {
    var xh = parseInt(e.currentTarget.dataset.xh) - 1
    var list = this.data.list;
    list[xh].val = e.detail
    this.setData({
      list: list
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
    var list = this.data.list;
    for (var i = 0; i < list.length; i++) {
      var a = i + 1;
      params['zdytj' + a] = list[i].zdytj;
      if (!list[i].val) {
        if (list[i].isInput) {
          wx.showModal({
            content: `请填写${list[i].zdytj}`,
            showCancel: false,
          })
          return false
        } else {
          wx.showModal({
            content: `请选择${list[i].zdytj}`,
            showCancel: false,
          })
          return false
        }

      }
      params['tjsd' + a] = list[i].val;
    }
    for (var i = 1; i < 6; i++) {
      if (params['zw' + i] && !params['rs' + i]){
        wx.showModal({
          content: '请填写请填写岗位及相应招聘人数',
          showCancel: false,
        })
        return false
      }
      if (!params['zw' + i] && params['rs' + i]) {
        wx.showModal({
          content: '请填写请填写岗位及相应招聘人数',
          showCancel: false,
        })
        return false
      }
    }
    params.bmlx = 0
    params.bmdx = 0
    params.qyxxRefOwid = wx.getStorageSync('yhOwid')
    params.jobRefOwid = that.data.zphOwid
    common.ajax('zustjy/bckjBizJybm/applyJob', params, function (res) {
      if (res.data.backCode == 0) {
        that.setData({
          btndisabled: true
        })
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: "招聘会申请成功，请等待审核",
          success(res) {
            if (res.confirm) {
              wx.navigateBack({
                delta: 2
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
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      lxr: {
        required: true,
      },
      lxdh: {
        required: true,
        tel: true,
      }
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      lxr: {
        required: '请填写联系人'
      },
      lxdh: {
        required: '请填写联系人手机',
        tel: '请填写正确手机号',
      }
    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  onConfirm(e) {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].str = e.detail.value
      list[parseInt(e.target.dataset.type) - 1].show = false
      list[parseInt(e.target.dataset.type) - 1].val = e.detail.value
      this.setData({
        list: list
      })
  },
  onCancel(e) {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = false
      this.setData({
        list: list
      })
  },
  toggle(type, show) {
    this.setData({
      [`show.${type}`]: show
    });
  },
  showBottom(e) {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = true
      this.setData({
        list: list
      })
  },
  hideBottom(e) {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = false
      this.setData({
        list: list
      })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initValidate()
    if (options.owid) {
      this.setData({
        zphOwid: options.owid
      })
      getContent(this, options.owid);
      xjhtjList(this, options.owid)
    }
    this.setData({
      'form.lxr': wx.getStorageSync('qyInfo').qyLxr || '',
      'form.lxdh': wx.getStorageSync('qyInfo').qyLxrdh || '',
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

  },
})

var xjhtjList = function (that,owid) {
  var data = {
    "owid": owid
  };
  common.ajax('zustjy/bckjBizJob/zphtjList', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      var arr = [];
      if (data.bean && data.bean.length > 0) {
        for (var i = 0; i < data.bean.length; i++) {
          var obj = {};
          // console.log(data.bean[i])
          for (var a in data.bean[i]) {
            obj.xh = i + 1
            obj.zdytj = a;
            obj.val = '';
            obj.show = false;
            obj.str = "请选择";
            obj.tjsd = data.bean[i][a];
            if (!data.bean[i][a]) {
              obj.isInput = true;
            } else {
              obj.isInput = false;
              if (data.bean[i][a].length == 0) {
                obj.isInput = true;
              }
            }
          }
          arr.push(obj)
        }
        that.setData({
          list: arr
        })
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

var getContent = function (that, owid) {//招聘详情
  var data = { "owid": owid, "yhOwid": wx.getStorageSync("yhOwid") };
  common.ajax('zustjy/bckjBizJob/getMiniJob', data, function (res) {
    if (res.data.backCode == 0) {
      res.data.bean.createtime = res.data.bean.createtime.substring(0, 10)
      if (res.data.bean.zphKsrq) {
        // if (res.data.bean.zwlx == 4) {
        //   res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 16)
        // } else {
          res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 10)
        // }
      }
      if (res.data.bean.zphBmjzsj) {
        res.data.bean.zphBmjzsj = res.data.bean.zphBmjzsj.substring(0, 10)
      }
      if (res.data.bean.qyxx) {
        if (res.data.bean.qyxx.qyLxrdh) {
          res.data.bean.qyxx.qyLxrdh = common.base64Decode(res.data.bean.qyxx.qyLxrdh)
        }
        if (res.data.bean.qyxx.qylxfs) {
          res.data.bean.qyxx.qylxfs = common.base64Decode(res.data.bean.qyxx.qylxfs)
        }
        if (res.data.bean.qyxx.qyYx) {
          res.data.bean.qyxx.qyYx = common.base64Decode(res.data.bean.qyxx.qyYx)
        }
      }
      that.setData({
        result: res.data.bean,
      })
      if (res.data.bean.zphKsrq) {
        var thetime = res.data.bean.zphKsrq;
        var d = new Date(Date.parse(thetime.replace(/-/g, "/")));

        var curDate = new Date();
        if (d <= curDate) {
          that.setData({
            old: '1',
          })
        }
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