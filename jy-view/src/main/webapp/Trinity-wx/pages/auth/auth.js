// pages/auth/auth.js
const app = getApp()
var openId;
var unionid;
Component({

  /**
   * 组件的属性列表
   */
  properties: {
    //是否显示modal
    show: {
      type: Boolean,
      value: false
    },
    //modal的高度
    height: {
      type: String,
      value: '300rpx'
    },
    type: {
      type: String,
      value: '0'
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    type:''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    // clickMask() {
    //   this.setData({show: false})
    // },

    // cancel() {
    //   this.setData({ show: false })
    //   this.triggerEvent('cancel')
    // },

    // confirm() {
    //   this.setData({ show: false })
    //   this.triggerEvent('confirm')
    // }
    getInfo: function (e) {
      var that = this;
      if (e.detail.iv && e.detail.encryptedData){
        app.globalData.userInfo = e.detail.userInfo;
        wx.setStorageSync('headImg', e.detail.userInfo.avatarUrl);
        wx.setStorageSync('nickName', e.detail.userInfo.nickName);
        var encryptedData = e.detail.encryptedData
        var iv = e.detail.iv
        wx.login({
          success: function (res) {
            if (res.code) {
              var jsonObj = {
                code: res.code,
                encryptedData: encryptedData,
                iv: iv,
                wxid: 'wx03'
              };
              wx.showLoading({
                title: '加载中',
              })
              wx.request({
                url: app.globalData.ApiUrl + 'zustcommon/bckjBizYhxx/getSwWxInfo',
                data: {
                  "data": JSON.stringify(jsonObj),
                  timestamp: new Date().getTime()
                },
                header: {
                  "Content-Type": "application/x-www-form-urlencoded"
                },
                method: 'POST',
                success: function (res) {
                  wx.hideLoading();
                  //console.log(res);
                  if (res.data.bean) {
                    //console.log(res.data.bean.openId);
                    if (res.data.bean.unionid){
                      openId = res.data.bean.openId;
                      unionid = res.data.bean.unionid;
                      app.globalData.openId = res.data.bean.openId;
                      app.globalData.unionid = res.data.bean.unionid;
                      wx.setStorageSync('openId', openId);
                      wx.setStorageSync('unionid', unionid);
                      that.setData({ show: false });
                    }else{
                      wx.showToast({
                        title: '获取用户信息失败，请重新授权',
                        icon: 'none'
                      })
                    }
                  }
                },
                fail: function () {
                  wx.hideLoading();
                  wx.showToast({
                    title: '网络出错，请稍后再试',
                    icon: 'none',
                    duration: 2000
                  })
                }

              })
            }
          },
          fail: function (res) {
            console.log(res)
          }
        })
      }else{
        wx.showModal({
          title: '提示',
          content: "用户登录需要授权，请重新授权",
          showCancel:false,
          success(res) {
            if (res.confirm) {
              
            } else if (res.cancel) {
              console.log('用户点击取消')
            }
          }
        })
        return false;
      }
      
      
    }
  }
})
