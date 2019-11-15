// pages/majorExam/majorExam.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
import Dialog from '../../miniprogram_npm/vant-weapp/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    open: false,
    index1: null, //学科
    index2: null, //类别
    index3: null, //专业
    subjectCategory: [], //学科类别
    projectType: [], //报考类别
    enrollmentMajor: [], //招生专业
    list: [], //当前显示
    subject: [],
    project: [],
    Major: [],
    url: '', //pdf地址
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.indexState(-1);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  //取消显示
  cancelPop() {
    this.setData({
      open: false,
    });
  },
  //显示
  show(e) {
    console.log(e)
    let type = e.target.dataset.type
    if (type == "2" && this.data.index1 == null) {
      common.toast('请先选择学科类别', 'none', 2000)
      return
    } else if (type == "3" && this.data.index2 == null) {
      common.toast('请先选择报考类别', 'none', 2000)
      return
    }
    let list = [];
    if (type == "1") {
      list = this.data.subject
    } else if (type == "2") {
      list = this.data.project
    } else if (type == "3") {
      list = this.data.Major
    }
    this.setData({
      open: true,
      type: e.target.dataset.type,
      list
    });
  },
  confirm(e) {
    console.log(e);
    let that = this;
    if (this.data.type == '1') {
      this.setData({
        open: false,
        index1: e.detail.index,
        index2: null,
        index3: null,
        bklb: null,
        xklb: null,
        xzzymc: null
      });
      this.indexState(this.data.subjectCategory[e.detail.index].owid)
    } else if (this.data.type == '2') {
      this.setData({
        open: false,
        index2: e.detail.index,
        index3: null,
        xklb: null,
        xzzymc: null
      });
      this.indexState(this.data.projectType[e.detail.index].owid)
    } else if (this.data.type == '3') {
      this.setData({
        open: false,
        index3: e.detail.index,
        xzzymc: null
      });
      that.submit()
    }
  },
  indexState: function(e) {
    let that = this;
    let xxbh = wx.getStorageSync('xxbh');
    let data = {
      xxbh: xxbh,
      fid: e
    }
    common.ajax('zustswyt/bckjBizBkzy/getMajors', data, function(res) {
      if (res.data.backCode == 0) {
        if (e == -1) {
          let subject = [];
          for (let i in res.data.bean) {
            subject.push(res.data.bean[i].name)
          }
          that.setData({
            subject: subject,
            subjectCategory: res.data.bean
          })
          if (wx.getStorageSync('applyOwid')) {
            that.getResult()
          }
        } else if (that.data.type == '1') {
          let project = [];
          for (let i in res.data.bean) {
            project.push(res.data.bean[i].name)
            if (that.data.bklb == res.data.bean[i].name) {
              that.setData({
                index2: i,
                type: 2
              })
            }
          }
          that.setData({
            project: project,
            projectType: res.data.bean
          })
          if (that.data.bklb) {
            that.indexState(that.data.projectType[that.data.index2].owid)
          }
        } else if (that.data.type == '2') {
          let Major = [];
          for (let i in res.data.bean) {
            Major.push(res.data.bean[i].name)
            if (that.data.xzzymc == res.data.bean[i].name) {
              that.setData({
                index3: i
              })
            }
          }
          that.setData({
            Major: Major,
            enrollmentMajor: res.data.bean
          })
          if (!!that.data.xzzymc) {
            that.submit()
          }
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  //申请表预览
  getApply: function(applyOwid) {
    let that = this;
    let data = {
      applyOwid: applyOwid,
    }
    common.ajax('zustswyt/bckjBizBm/getApply', data, function(res) {
      if (res.data.backCode == 0) {
        that.setData({
          url: res.data.bean
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  //预览pdf:报名表
  openFile: function(e) {
    let that = this;
    wx.downloadFile({
      // 示例 url，并非真实存在
      url: common.imgPath + that.data.url,
      success: function(res) {
        const filePath = res.tempFilePath
        wx.openDocument({
          filePath: filePath,
          success: function(res) {
            console.log('打开文档成功')
          }
        })
      },
      fail: function() {
        console.log('打开失败')
      }
    })
  },
  // 报考专业提交

  submit() {
    let that = this;
    let xxbh = wx.getStorageSync('xxbh');
    if (that.data.index1 == null || that.data.index2 == null || that.data.index3 == null) {
      common.toast("尚有未选择项", 'none', 2000)
      return
    }
    let xklb = that.data.subjectCategory[that.data.index1].name; //学科类别
    let xklbOwid = that.data.subjectCategory[that.data.index1].owid; //学科类别
    let bklb = that.data.projectType[that.data.index2].name; //报考类别
    let bklbOwid = that.data.projectType[that.data.index2].owid; //报考类别
    let zyOwid = that.data.enrollmentMajor[that.data.index3].owid; //专业owid
    let data = {
      xxbh: xxbh,
      xklb: xklb,
      bklb: bklb,
      zyOwid: zyOwid,
      userRefOwid: wx.getStorageSync('yhRefOwid'),
      xklbOwid: xklbOwid,
      bklbOwid: bklbOwid,
    }
    common.ajax('zustswyt/bckjBizBm/submit', data, function(res) {
      if (res.data.backCode == 0) {
        wx.setStorageSync('sqbOwid', res.data.bean)
        that.getApply(res.data.bean)
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  openDialog() {
    if (this.data.index1 == null || this.data.index2 == null || this.data.index3 == null) {
      common.toast('尚未选择', 'none', 2000)
      return
    }
    Dialog.confirm({
      title: '报名表确认',
      message: '请确认报名表。（预览报名表点击页面上方按钮）'
    }).then(() => {
      let data = {
        applyOwid: wx.getStorageSync('sqbOwid'),
      }
      common.ajax('zustswyt/bckjBizBm/confirmApply', data, function(res) {
        if (res.data.backCode == 0) {
          wx.navigateBack({
            delta: 1
          })
        } else {
          common.toast(res.data.errorMess, 'none', 2000)
        }
      })
    }).catch(() => { // on cancel
    });
  },
  getResult() {
    var that = this;
    let data = {
      applyOwid: wx.getStorageSync('applyOwid'),
    }
    common.ajax('zustswyt/bckjBizBm/getResult', data, function(res) {
      if (res.data.backCode == 0) {
        for (var i in that.data.subject) {
          if (that.data.subject[i] == res.data.bean.xklb) {
            that.setData({
              bklb: res.data.bean.bklb,
              xklb: res.data.bean.xklb,
              xzzymc: res.data.bean.xzzymc,
              index1: i,
              type: 1
            })
            that.indexState(that.data.subjectCategory[i].owid)
          }
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    })
  }
})