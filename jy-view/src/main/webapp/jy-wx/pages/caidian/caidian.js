// pages/caidian/caidian.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    headImgUrl: '',
    name: '',
    xh: '',
    zwbt: '',
    zphJbdd:'',
    owid:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.getLocation({
      type: 'gcj02',// 默认wgs84
      isHighAccuracy: true,
      success: function (res) {
        that.setData({
          latitude: res.latitude.toFixed(5),
          longitude: res.longitude.toFixed(5)
        })
      },
      fail: function (res) { }
    });
    var curDate = common.formatTime(new Date());
    that.setData({
      xq: common.convertWKtwo(curDate),
      date: curDate.substring(0, 10),
      time: curDate.substring(11, 19)
    })
    var Interval = setInterval(function () {
      var curDate1 = common.formatTime(new Date());
      that.setData({
        time: curDate1.substring(11, 19)
      })
    }, 1000)
    var userInfo = wx.getStorageSync('userInfo');
    var stuInfo = wx.getStorageSync('stuInfo');
    this.setData({
      "headImgUrl": userInfo.avatarUrl,
      "name": stuInfo.xm,
      "xh": stuInfo.xsxh
    })
    if (options.owid) {
      this.setData({
        owid: options.owid,
        option: options
      })
      getContent(this, options.owid);
    }

  },
  chooseGPS(){//地图采点
    var that = this;
    wx.chooseLocation({
      success:function(res){
        that.setData({
          latitude: res.latitude.toFixed(5),
          longitude: res.longitude.toFixed(5)
        })
        wx.showModal({
          title: '提示',
          content: '您已选择采点位置，是否确认提交',
          confirmColor: '#008783',
          success(res) {
            if (res.confirm) {
              cd(that);
            } else if (res.cancel) {

            }
          }
        })
      },fail: function (res) {
        wx.showModal({
          title: '提示',
          content: '未获取到您的位置，请打开设置后重试',
          confirmColor: '#008783',
          success(res) {
            if (res.confirm) {
              wx.openSetting({
                success: function (osrs) {
                  // 出发条件是返回的时候
                  wx.getLocation({
                    success: function (locationinfo) {
                      that.onLoad(that.data.option);
                    },
                    fail: function (fres) {

                    }
                  })
                }
              })
            } else if (res.cancel) {

            }
          }
        })
      }
    })
  },
  caidian(){
    var that = this;
    wx.getLocation({
      type: 'gcj02',// 默认wgs84
      isHighAccuracy: true,
      success: function (res2) {
        that.setData({
          latitude: res2.latitude.toFixed(5),
          longitude: res2.longitude.toFixed(5)
        })
        wx.showModal({
          title: '提示',
          content: '是否确认在当前位置采点',
          confirmColor: '#008783',
          success(res) {
            if (res.confirm) {
              cd(that);
            } else if (res.cancel) {

            }
          }
        })
      },
      fail: function (res) {
        wx.showModal({
          title: '提示',
          content: '未获取到您的位置，请打开设置后重试',
          confirmColor: '#008783',
          success(res) {
            if (res.confirm) {
              wx.openSetting({
                success: function (osrs) {
                  // 出发条件是返回的时候
                  wx.getLocation({
                    success: function (locationinfo) {
                      that.onLoad(that.data.option);
                    },
                    fail: function (fres) {

                    }
                  })
                }
              })
            } else if (res.cancel) {

            }
          }
        })
      }
    });
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
var getContent = function (that, owid) {//招聘详情
  var data = { "owid": owid, "yhOwid": wx.getStorageSync("yhOwid") };
  common.ajax('zustjy/bckjBizJob/getMiniJob', data, function (res) {
    if (res.data.backCode == 0) {
      res.data.bean.createtime = res.data.bean.createtime.substring(0, 10)
      if (res.data.bean.zphKsrq) {
        res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 10)
      }
      that.setData({
        zwbt: res.data.bean.zwbt,
        zphJbdd: res.data.bean.zphJbdd,
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

var cd = function (that) {
  var data = { "owid": that.data.owid, "zphGpsjd": that.data.longitude, "zphGpswd": that.data.latitude };
  common.ajax('zustjy/bckjBizJob/setCdPoint', data, function (res) {
    if (res.data.backCode == 0) {
      wx.showModal({
        title: '采集成功',
        showCancel: false,
        content: '经度：' + that.data.longitude + '\n纬度：' + that.data.latitude,
        confirmColor: '#008783',
        success(res3) {
          if (res3.confirm) {
            wx.navigateBack();
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
}