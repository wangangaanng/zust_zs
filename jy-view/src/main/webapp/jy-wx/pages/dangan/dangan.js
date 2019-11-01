// pages/dangan/dangan.js
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    xsxm:'',
    sfzh:'',
    isSearch:false,
    result: '',
    mzList:[],
    sydList:[],
    byqxList:[],
    qflbList:[]
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getByType(this,'50009');
    getByType(this, '50005');
    getByType(this, '50001');
    getByType(this, '50007');
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
  nameInput:function(e){
    this.setData({
      xsxm: e.detail,
    })
  },
  numberInput:function(e){
    this.setData({
      sfzh: e.detail,
    })
  },
  search:function(){
    if (!this.data.xsxm) {
      wx.showToast({
        icon: 'none',
        title: '请输入学生姓名',
      })
      return false
    } else if (!this.data.sfzh) {
      wx.showToast({
        icon: 'none',
        title: '请输入身份证号码',
      })
      return false
    }
    var data = {
      "xsxm": this.data.xsxm,
      "sfz": this.data.sfzh
    }
    var that=this;
    common.ajax('zustcommon/bckjBizJyscheme/queryDocument', data, function (res) {
      if (res.data.backCode == 0) {
        if (res.data.bean){
          if (res.data.bean.sfz){
            res.data.bean.csrq = res.data.bean.sfz.substring(6, 10) + '年' + res.data.bean.sfz.substring(10, 12) + '月' + res.data.bean.sfz.substring(12, 14) + '日'
          }
          var mzName = common.convertName(res.data.bean.mz,that.data.mzList)
          var sydName = common.convertName(res.data.bean.syd, that.data.sydList)
          var byqxName = common.convertName(res.data.bean.byqx, that.data.byqxList)
          var bdzszdName = common.convertName(res.data.bean.bdzszdmc, that.data.sydList)
          var qflbName = common.convertName(res.data.bean.bdzqflbmc, that.data.qflbList)
          that.setData({
            isSearch: true,
            mzName:mzName,
            sydName: sydName,
            byqxName: byqxName,
            bdzszdName: bdzszdName,
            qflbName: qflbName,
            result: res.data.bean
          })
        }else{
          wx.showToast({
            title: '未查询到您的档案数据',
            icon: 'none',
            duration: 2000
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
})
var getByType = function (that,lb) {
  var data = { "dicType": lb };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          if (lb =='50009'){//民族
            that.data.mzList.push(obj)
          }
          if (lb == '50005') {//生源地
            that.data.sydList.push(obj)
          }
          if (lb == '50001') {//毕业去向
            that.data.byqxList.push(obj)
          }
          if (lb == '50007') {//签发类别
            that.data.qflbList.push(obj)
          }
         
        }
        that.setData({
          mzList: that.data.mzList,
          sydList: that.data.sydList,
          byqxList: that.data.byqxList,
          qflbList: that.data.qflbList
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