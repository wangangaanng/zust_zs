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
    xjsjStr: '请选择',
    show: {
      xjsj: false
    },
    form: {
      lxr: '',
      lxdh: '',
      xjsj: '',
      jkr: '',
      jkrjs: '',
      xjhsqly: '',
      memo: '',
    },
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
    const params = e.detail.value
    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      this.showModal(error)
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
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: "宣讲会申请成功，请等待审核",
          success(res) {
            if (res.confirm) {
              wx.navigateBack({
                delta:1
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
      xjsj: {
        required: true
      },
      jkr: {
        required: true
      },
      jkrjs: {
        required: true,
        maxlength: 200,
      },
      xjhsqly: {
        required: true,
        maxlength: 200,
      },
      memo: {
        required: true,
        maxlength: 200,
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
      xjsj: {
        required: '请选择宣讲时间',
      },
      jkr: {
        required: '请填写讲课人',
      },
      jkrjs: {
        required: '请填写讲课人介绍',
        maxlength: '讲课人介绍不得超过200字',
      },
      xjhsqly: {
        required: '请填写申请理由',
        maxlength: '申请理由不得超过200字',
      },
      memo: {
        required: '请填写备注',
        maxlength: '备注不得超过200字',
      }

    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  onConfirm(e) {
    if (e.target.dataset.type == 8) {
      console.log(e)
      var date = util.formatTime2(new Date(e.detail))
      this.setData({
        xjsjStr: date,
        'form.xjsj': date
      })
      this.toggle('xjsj', false);
    }else{
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].str = e.detail.value
      list[parseInt(e.target.dataset.type) - 1].show = false
      list[parseInt(e.target.dataset.type) - 1].val = e.detail.value
      this.setData({
        list: list
      })
    }

  },
  onCancel(e) {
    if (e.target.dataset.type == 8) {
      this.toggle('zwSxsj', false);
    } else {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = false
      this.setData({
        list: list
      })
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
    if (e.target.dataset.type == 8) {
      this.toggle('xjsj', true);
    } else {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = true
      this.setData({
        list: list
      })
    }
    
  },
  hideBottom(e) {
    if (e.target.dataset.type == 8) {
      this.toggle('zwSxsj', false);
    } else {
      var list = this.data.list;
      list[parseInt(e.target.dataset.type) - 1].show = false
      this.setData({
        list: list
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