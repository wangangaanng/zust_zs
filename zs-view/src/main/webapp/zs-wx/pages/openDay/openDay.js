// pages/qyInfo/qyInfo.js
var Mustache = require('../../libs/mustache/mustache');
var WxParse = require('../../libs/wxParse/wxParse.js');
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
const app = getApp()
var imgPath = app.globalData.imgPath;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    modal1: false,
    isauthorize: false,
    imgPath: imgPath,
    result: '',
    yzmStr1: '发送验证码',
    seconds1: 60,
    disabled1: false,
    xm: '',
    exp9: '',
    qxzy: '',
    sjh1: '',
    yzm1: '',
  },
  cancel: function () {
    this.setData({
      modal1: false,
      sjh1: '',
      yzm1: '',
      yzmStr1: '发送验证码',
      seconds1: 60,
      disabled2: false,
      rq: ''
    });
  },
  confirm() {
    var that = this
    if (!this.data.xm.trim()) {
      wx.showToast({
        title: '请输入姓名',
        icon: 'none'
      })
      return false
    }
    if (this.data.sjh1.trim() && this.data.sjh1.trim().length == 11) {
      if (/^1[34578]\d{9}$/.test(this.data.sjh1.trim())) {

      } else {
        wx.showToast({
          title: '手机号有误',
          icon: 'none'
        })
        return false
      }
    } else {
      wx.showToast({
        title: '请输入11位手机号',
        icon: 'none'
      })
      return false
    }
    if (!this.data.yzm1.trim()) {
      wx.showToast({
        title: '请输入验证码',
        icon: 'none'
      })
      return false
    }
    if (!this.data.exp9.trim()) {
      wx.showToast({
        title: '请输入家庭地址',
        icon: 'none'
      })
      return false
    }
    if (!this.data.qxzy.trim()) {
      wx.showToast({
        title: '请输入意向专业',
        icon: 'none'
      })
      return false
    }

    var data = {
      "openid": wx.getStorageSync('openId'),
      "unionid": wx.getStorageSync('unionid'),
      "wxid": '02',
      "type": '2',
      "nickname": wx.getStorageSync('userInfo').nickName,
      "gender": wx.getStorageSync('userInfo').gender,
      "city": wx.getStorageSync('userInfo').city,
      "province": wx.getStorageSync('userInfo').province,
      "country": wx.getStorageSync('userInfo').country,
      "avatarUrl": wx.getStorageSync('userInfo').avatarUrl,
      "xm": that.data.xm,
      "exp9": that.data.exp9,
      "qxzy": that.data.qxzy,
      "sjh": that.data.sjh1,
      "yzm": that.data.yzm1,
      "owid": that.data.option.owid
    };
    common.ajax('zustcommon/bckjBizYhxx/candidatesRegistration', data, function (res) {
      if (res.data.backCode == 0) {
        wx.showToast({
          title: '预约成功',
          icon: 'none'
        })
        that.setData({
          modal1: false,
          sjh1: '',
          yzm1: '',
          yzmStr1: '发送验证码',
          seconds1: 60,
          disabled2: false,
          rq: ''
        });
      } else {
        wx.showToast({
          title: res.data.errorMess,
          icon: 'none',
          duration: 2000
        })
      }
    });

  },
  getxm(e) {
    this.setData({
      xm: e.detail
    })
  },
  getsjh1(e) {
    this.setData({
      sjh1: e.detail
    })
  },
  getyzm1(e) {
    this.setData({
      yzm1: e.detail
    })
  },
  getexp(e) {
    this.setData({
      exp9: e.detail
    })
  },
  getqxzy(e) {
    this.setData({
      qxzy: e.detail
    })
  },
  send1(e) {
    if (this.data.sjh1.trim() && this.data.sjh1.trim().length == 11) {
      if (/^1[34578]\d{9}$/.test(this.data.sjh1.trim())) {
        if (!this.data.disabled2) {
          sendYzm(this, '2')
        }
      } else {
        wx.showToast({
          title: '手机号有误',
          icon: 'none'
        })
        return false
      }
    } else {
      wx.showToast({
        title: '请输入11位手机号',
        icon: 'none'
      })
      return false
    }

  },
  applyDay(){
    this.setData({
      modal1: true,
      yzmStr1: '发送验证码',
      seconds1: 60,
      disabled1: false,
      xm: '',
      exp9: '',
      qxzy: '',
      sjh1: '',
      yzm1: '',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    if (options.owid) {//列表进入
      that.setData({
        option: options
      })
    } else if (options.scene) {//扫码签到
      that.setData({
        option: options
      })
    }

    // if (!wx.getStorageSync('unionid')) {
    //   this.setData({
    //     isauthorize: true,
    //   })
    // } else {
    //   this.setData({
    //     isauthorize: false,
    //   })
    // }

    if (options.owid) {
      getCaOpDetail(this, options.owid);
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  makecall(){
    wx.makePhoneCall({
      phoneNumber: '0571-85070165'
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },
  wxParseTagATap: function (e) {
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
  }
})

var getCaOpDetail = function (that, owid) {//招聘详情
  var data = { "owid": owid };
  common.ajax('zustcommon/bckjBizYhxx/getCaOpDetail', data, function (res) {
    if (res.data.backCode == 0) {
      
      if (res.data.bean) {
        var obj={}
        obj.dicVal1 = res.data.bean.dicVal1 ? res.data.bean.dicVal1 : ''
        obj.dicVal2 = res.data.bean.dicVal2 ? res.data.bean.dicVal2 : ''
        obj.dicVal3 = res.data.bean.dicVal3 ? res.data.bean.dicVal3 : ''
        obj.dicVal4 = res.data.bean.dicVal4 ? res.data.bean.dicVal4 : ''
        obj.dicVal5 = res.data.bean.dicVal5 ? res.data.bean.dicVal5 : ''
        obj.dicVal7 = res.data.bean.dicVal7 ? res.data.bean.dicVal7 : ''


        if (res.data.bean.dicVal5) {
          var dicVal5 = res.data.bean.dicVal5
          WxParse.wxParse('dicVal5', 'html', dicVal5, that, 5);
        }
        that.setData({
          result: obj,
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


var count = function (that, type) {
  if (type == 1) {
    that.setData({
      disabled1: true
    })
    djs(that)
  } else if (type == 2) {
    that.setData({
      disabled2: true
    })
    djs1(that)
  }
}

var djs = function (that) {
  if (that.data.seconds > 0) {
    that.data.seconds--
    setTimeout(function () {
      that.setData({
        seconds: that.data.seconds,
        yzmStr: `剩余${that.data.seconds}秒`
      })
      djs(that)
    }, 1000)
  } else {
    that.setData({
      disabled1: false,
      seconds: 60,
      yzmStr: '发送验证码'
    })
  }
}

var djs1 = function (that) {
  if (that.data.seconds1 > 0 && that.data.disabled2) {
    that.data.seconds1--
    setTimeout(function () {
      that.setData({
        seconds1: that.data.seconds1,
        yzmStr1: `剩余${that.data.seconds1}秒`
      })
      djs1(that)
    }, 1000)
  } else {
    that.setData({
      disabled2: false,
      seconds1: 60,
      yzmStr1: '发送验证码'
    })
  }
}

var sendYzm = function (that, type) {
  var data;
  if (type == 1) {
    that.setData({
      disabled1: true
    })
    data = { "sjh": that.data.sjh };
  } else if (type == 2) {
    that.setData({
      disabled2: true
    })
    data = { "sjh": that.data.sjh1 };
  }
  common.ajax('zustcommon/bckjBizYhxx/sendYzm/' + type, data, function (res) {
    if (res.data.backCode == 0) {
      wx.showToast({
        title: '验证码已发送',
        icon: 'none'
      })
      count(that, type)
    } else {
      if (type == 1) {
        that.setData({
          disabled1: false
        })
      } else if (type == 2) {
        that.setData({
          disabled2: false
        })
      }
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}