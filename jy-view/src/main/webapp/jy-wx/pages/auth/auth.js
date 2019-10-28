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
      console.log(e)
      // if (e.detail.iv)
      var that = this;
      app.globalData.userInfo = e.detail.userInfo;
      wx.setStorageSync('userInfo', e.detail.userInfo);
      var encryptedData = e.detail.encryptedData
      var iv = e.detail.iv
      wx.login({
        success: function (res) {
          if (res.code) {
            var jsonObj = {
              code: res.code,
              encryptedData: encryptedData,
              iv: iv,
              wxid:'wx01'
            };
            wx.showLoading({
              title: '加载中',
            })
            wx.request({
              url: app.globalData.ApiUrl + 'zustcommon/bckjBizYhgl/getYhInfoByOpenid',
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
                if (res.data.bean) {
                  openId = res.data.bean.openId;
                  unionid = res.data.bean.unionid;
                  app.globalData.openId = res.data.bean.openId;
                  app.globalData.unionid = res.data.bean.unionid;
                  wx.setStorageSync('openId', openId);
                  wx.setStorageSync('unionid', unionid);
                }
                that.setData({ show: false });
                // that.triggerEvent('myevent');
                if (that.data.type==1){
                  wx.navigateTo({
                    url: '../stuLogin/stuLogin',
                  })
                } else if (that.data.type == 2) {
                  wx.navigateTo({
                    url: '../qyLogin/qyLogin',
                  })
                }
                
                // if (wx.getStorageSync("fxid")) {
                //   wx.reLaunch({
                //     url: '../index/index?scene=' + wx.getStorageSync("fxid")
                //   })
                // } else {
                //   wx.reLaunch({
                //     url: '../index/index'
                //   })
                // }
              },
              fail:function(){
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
        fail:function(res){
          console.log(res)
        }
      })
    }
  }
})
