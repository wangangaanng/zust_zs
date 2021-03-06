// pages/qiandao/qiandao.js
var common = require('../../libs/common/common.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    headImgUrl:'',
    name:'',
    xh:'',
    zwbt:'',
    zphJbdd:'',
    owid:'',
    latitude:'',
    longitude:'',
    isqd:false,
    show:false,
    isauthorize: false,
    jl: false,
    // markers: [{
    //   iconPath: "/static/location.png",
    //   id: 0,
    //   latitude: '',
    //   longitude: '',
    //   width: 50,
    //   height: 50
    // }],
    userType: '3'
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
    wx.getSetting({
      success(res) {
        if (!res.authSetting['scope.userInfo']) {
          that.setData({
            isauthorize: true,
          })
        } else {
          that.setData({
            isauthorize: false,
          })
          // if (wx.getStorageSync("yhOwid")) {
          if (wx.getStorageSync('userType') == 1) {//学生登录
            that.setData({
              show: true
            })
            wx.getLocation({
              type: 'gcj02',// 默认wgs84
              isHighAccuracy:true,
              success: function (res) {
                // var latitude = "markers[0].latitude"
                // var longitude = "markers[0].longitude"
                that.setData({
                  // [latitude]: res.latitude,
                  // [longitude]: res.longitude,
                  latitude: res.latitude,
                  longitude: res.longitude
                })
                if (options.owid) {//列表进入
                  getContent(that, options.owid);
                } else if (options.scene) {//扫码签到
                  getContent(that, options.scene);
                }
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
            that.setData({
              "headImgUrl": userInfo.avatarUrl,
              "name": stuInfo.xm,
              "xh": stuInfo.xsxh
            })
          } else {
            that.setData({
              show: false
            })
            wx.navigateTo({
              url: '../stuLogin/stuLogin',
            })
          }
        }
      }
    })
    
  },
  refreshGPS:function(){
    var that = this
    wx.getLocation({
      type: 'gcj02',// 默认wgs84
      isHighAccuracy: true,
      success: function (res) {
        that.setData({
          latitude: res.latitude,
          longitude: res.longitude
        })
        getDistance(that); 
        wx.showToast({
          title: '刷新成功',
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
  qiandao:function(){
    var that = this
    if (that.data.latitude){//获取到本人位置
      wx.showModal({
        title: '提示',
        content: '签到后该微信号将与该账号绑定，绑定后不可更改，是否确认是本人签到',
        confirmColor: '#008783',
        success(res) {
          if (res.confirm) {
            qd(that);
          } else if (res.cancel) {

          }
        }
      })
    }else{
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
      that.setData({
        owid: res.data.bean.owid,//真实owid
      })
      res.data.bean.createtime = res.data.bean.createtime.substring(0, 10)
      if (res.data.bean.zphKsrq) {
        // if (res.data.bean.zwlx == 4) {
        //   res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 16)
        // } else {
        res.data.bean.zphKsrq = res.data.bean.zphKsrq.substring(0, 10)
        // }
      }
      if (res.data.bean.zphGpsjd){
        that.setData({
          zphGpsjd: res.data.bean.zphGpsjd,
          zphGpswd: res.data.bean.zphGpswd,
        })
        getDistance(that);
      }else{
        that.setData({
          jl: false,
        })
      }
      
      that.setData({
        zwbt: res.data.bean.zwbt,
        zphJbdd: res.data.bean.zphJbdd
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
var getDistance=function(that){
  var jl = common.getDistance(that.data.zphGpswd, that.data.zphGpsjd, that.data.latitude, that.data.longitude);
  if (jl < 1) {
    jl = (jl * 1000).toFixed(0) + 'm'
  } else {
    jl = jl.toFixed(0) + 'km'
  }
  that.setData({
    jl: jl
  })
}

var qd = function(that){
  var data = { "xxlb": 1, "gpsJd": that.data.longitude, "gpsWd": that.data.latitude,"distance":that.data.jl, "jobRefOwid": that.data.owid, "yhRefOwid": wx.getStorageSync("yhOwid") };
  common.ajax('zustjy/bckjBizXsgz/signInOrScribe', data, function (res) {
    if (res.data.backCode == 0) {
      wx.showToast({
        title: '签到成功',
      })
      that.setData({
        isqd: true,
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