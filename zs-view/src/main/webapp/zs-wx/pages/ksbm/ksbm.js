// pages/ksbm/ksbm.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    hidden1: 'block',
    modal1:false,
    yzmStr1: '发送验证码',
    seconds1: 60,
    isauthorize: false,
    currentTab: 0,
    disabled1:false,
    disabled2: false,
    yzmStr:'发送验证码',
    seconds:60,
    date:"",
    sjh:'',
    xm:'',
    yzm:'',
    exp9:'',
    qxzy:'',
    sjh1: '',
    yzm1: '',
    list:[],
    rq:''
  },
  yy(e){
    this.setData({
      modal1: true,
      rq: e.currentTarget.dataset.rq
    });
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
  confirm(){
    var that = this
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
    var data = {
      "sjh": that.data.sjh1,
      "yzm": that.data.yzm1,
      "val2": that.data.rq
    };
    common.ajax('zustcommon/bckjBizYhxx/apOfCaOpDay', data, function (res) {
      if (res.data.backCode == 0) {
        wx.showToast({
          title: '预约成功',
          icon: 'none'
        })
        this.setData({
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
  getsjh(e){
    this.setData({
      sjh:e.detail
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
  getyzm(e) {
    this.setData({
      yzm: e.detail
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
  send(e) {
    if (this.data.sjh.trim() && this.data.sjh.trim().length==11){
      if (/^1[34578]\d{9}$/.test(this.data.sjh.trim())){
          if (!this.data.disabled1) {
            sendYzm(this, '1')
          }
      } else {
        wx.showToast({
          title: '手机号有误',
          icon: 'none'
        })
        return false
      }
    }else{
      wx.showToast({
        title: '请输入11位手机号',
        icon:'none'
      })
      return false
    }
   
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
  clickTab(e) {
    console.log(e)
    if (e.detail.name==1){
      this.setData({
        list:[]
      })
      getShowCaOpDayDate(this)
    }
    this.setData({
      currentTab: e.detail.name
    })
  },
  submit(){
    if(!this.data.xm.trim()){
      wx.showToast({
        title: '请输入姓名',
        icon: 'none'
      })
      return false
    }
    if (this.data.sjh.trim() && this.data.sjh.trim().length == 11) {
      if (/^1[34578]\d{9}$/.test(this.data.sjh.trim())) {
        
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
    if (!this.data.yzm.trim()) {
      wx.showToast({
        title: '请输入验证码',
        icon: 'none'
      })
      return false
    }
    if (!this.data.exp9.trim()) {
      wx.showToast({
        title: '请输入地区',
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
    var that = this

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
      "sjh": that.data.sjh, 
      "yzm": that.data.yzm, 
      "exp9": that.data.exp9, 
      "qxzy": that.data.qxzy
    };
    common.ajax('zustcommon/bckjBizYhxx/candidatesRegistration', data, function (res) {
      if (res.data.backCode == 0) {
        wx.showToast({
          title: '报名成功',
          icon: 'none'
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (!wx.getStorageSync('unionid')) {
      this.setData({
        isauthorize: true,
      })
    } else {
      this.setData({
        isauthorize: false,
      })
    }

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

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})

var count = function (that, type) {
  if(type==1){
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

var djs = function (that){
  if (that.data.seconds>0){
    that.data.seconds--
    setTimeout(function () {
      that.setData({
        seconds: that.data.seconds,
        yzmStr: `剩余${that.data.seconds}秒`
      })
      djs(that)
    }, 1000)
  }else{
    that.setData({
      disabled1: false,
      seconds:60,
      yzmStr:'发送验证码'
    })
  }
}

var djs1 = function (that) {
  if (that.data.seconds1 > 0) {
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

var getShowCaOpDayDate = function (that) {
  var data = {};
  common.ajax('zustcommon/bckjBizYhxx/getShowCaOpDayDate', data, function (res) {
    if (res.data.backCode == 0) {
      
      if(res.data.bean && res.data.bean.length>0){
        for(var i in res.data.bean){
          if (res.data.bean[i]['校园开放日']){
            res.data.bean[i].rq = res.data.bean[i]['校园开放日']
          }
        }
        that.setData({
          list: res.data.bean,
          hidden1:'none'
        })
      }else{
        that.setData({
          hidden1: 'block'
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

var sendYzm = function (that, type) {
  var data;
  if(type==1){
    data = { "sjh": that.data.sjh };
  } else if (type == 2) {
    data = { "sjh": that.data.sjh1 };
  }
  common.ajax('zustcommon/bckjBizYhxx/sendYzm/' + type, data, function (res) {
    if (res.data.backCode == 0) {
      wx.showToast({
        title: '验证码已发送',
        icon:'none'
      })
      count(that, type)
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}

var getContent = function (that,) {
  var data = { "owid": owid, "yhOwid": wx.getStorageSync("yhOwid") };
  common.ajax('zustjy/bckjBizJob/getOneJob', data, function (res) {
    if (res.data.backCode == 0) {


    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}