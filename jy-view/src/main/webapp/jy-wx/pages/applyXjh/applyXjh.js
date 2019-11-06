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
    hidden1: false,
    minDate: new Date().getTime(),
    imgPath: imgPath,
    xjsjStr: '请选择',
    show: {
      xjsj: false
    },
    form: {
      lxr: wx.getStorageSync('qyInfo').qyLxr || '',
      lxdh: wx.getStorageSync('qyInfo').qyLxrdh || '',
      xjsj: '',
      jkr: '',
      jkrjs: '',
      memo: '',
    },
    btndisabled: false
  },
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },
  gettjsd(e){
    var xh = parseInt(e.currentTarget.dataset.xh)-1
    var list = this.data.list;
    list[xh].val = e.detail
    this.setData({
      list:list
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
    var d1 = new Date(new Date(Date.parse(params.xjsj.replace(/-/g, "/")))).getDay()
    if (d1 != 4) {
      wx.showModal({
        title: '提示',
        content: '学校宣讲会仅在周四举行，请重新选择。如有特殊情况，请联系学校招就处。',
        showCancel: false
      })
      return false
    }

    var list=this.data.list;
    for (var i = 0; i < list.length;i++){
      var a=i+1;
      params['zdytj' + a] = list[i].zdytj;
      if (!list[i].val){
        if(list[i].isInput){
          wx.showModal({
            content: `请填写${list[i].zdytj}`,
            showCancel: false,
          })
          return false
        }else{
          wx.showModal({
            content: `请选择${list[i].zdytj}`,
            showCancel: false,
          })
          return false
        }
        
      }
      params['tjsd' + a] = list[i].val;
    }
    params.bmlx = 0
    params.bmdx = 1
    params.qyxxRefOwid = wx.getStorageSync('yhOwid')
    common.ajax('zustjy/bckjBizJybm/applyJob', params, function (res) {
      if (res.data.backCode == 0) {
        that.setData({
          btndisabled: true
        })
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: "宣讲会申请成功，请等待审核",
          success(res) {
            if (res.confirm) {
              wx.navigateBack({
                delta: 1
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
      },
      jkr: {
        required: true
      },
      xjsj: {
        required: true
      },
      jkrjs: {
        required: true
      },
      memo: {
        required: true,
        minlength: 50,
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
      },
      jkr: {
        required: '请填写讲课人',
      },
      xjsj: {
        required: '请选择宣讲时间',
      },
      jkrjs: {
        required: '请填写讲课人介绍',
      },
      memo: {
        required: '请填写备注',
        minlength: '公司介绍不得少于50字',
      }

    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  onConfirm(e) {
    if (e.target.dataset.type == 8) {
      var date = util.formatTime1(new Date(e.detail))
      var d1 = new Date(new Date(Date.parse(date.replace(/-/g, "/")))).getDay()
      console.log(d1)
      console.log(date)
      this.setData({
        xjsjStr: date,
        'form.xjsj': date
      })
      this.toggle('xjsj', false);
      if(d1!=4){
        wx.showModal({
          title: '提示',
          content: '学校宣讲会仅在周四举行，请重新选择。如有特殊情况，请联系学校招就处。',
          showCancel:false
        })
      }
    }else{
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].str = e.detail.value
      list[parseInt(e.target.dataset.type) - 1].show = false
      list[parseInt(e.target.dataset.type) - 1].val = e.detail.value
      this.setData({
        list: list,
        hidden1: false
      })
    }

  },
  onCancel(e) {
    if (e.target.dataset.type == 8) {
      this.toggle('xjsj', false);
    } else {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = false
      this.setData({
        list: list,
        hidden1: false
      })
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
    xjhtjList(this)
    this.setData({
      'form.lxr': wx.getStorageSync('qyInfo').qyLxr || '',
      'form.lxdh': wx.getStorageSync('qyInfo').qyLxrdh || '',
    })
  },
  showBottom(e) {
    if (e.target.dataset.type == 8) {
      this.toggle('xjsj', true);
    } else {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = true
      this.setData({
        list: list,
        hidden1: true
      })
    }
    
  },
  hideBottom(e) {
    if (e.target.dataset.type == 8) {
      this.toggle('xjsj', false);
    } else {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = false
      this.setData({
        list: list,
        hidden1: false
      })
    }
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
          // console.log(data.bean[i])
          for (var a in data.bean[i]){
            obj.xh=i+1
            obj.zdytj = a;
            obj.val = '';
            obj.show = false;
            obj.str = "请选择";
            obj.tjsd = data.bean[i][a];
            if (!data.bean[i][a]){
              obj.isInput=true;
            }else{
              obj.isInput = false;
              if (data.bean[i][a].length==0){
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
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}
