//app.js
App({
  onLaunch: function () {
    // wx.getSetting({
    //   success(res) {
    //     if (!res.authSetting['scope.userInfo']) {
    //       wx.reLaunch({
    //         url: '/pages/auth/auth'
    //       })
    //       // wx.authorize({
    //       //   scope: 'scope.userInfo',
    //       //   success() {
    //       //     wx.getUserInfo({
    //       //       success: function (res) {
    //       //         wx.setStorageSync('userInfo', res.userInfo);
    //       //       }
    //       //     })
    //       //   }
    //       // })
    //     }
    //   }
    // })
    // try {
    //   const token = wx.getStorageSync('userInfo');
    //   if (token === '' || token === undefined || token === null) {
    //     wx.reLaunch({
    //       url: '/pages/auth/auth'
    //     })
    //   } else { }
    // } catch (err) { }
  },
  onShow: function () {
    // var token1 = wx.getStorageSync('openId');
    // var token2 = wx.getStorageSync('unionid');
    // if (token1 === '' || token1 === undefined || token1 === null || token2 === '' || token2 === undefined || token2 === null){
    //   wx.reLaunch({
    //     url: '/pages/auth/auth'
    //   })
    // }
  },
  globalData: {
    userInfo: '',
    lj: '',
    ApiUrl: 'https://job.zust.edu.cn/gate/',//'http://172.16.13.106:8888/',//'http://192.168.3.222:8888/',//
    localimgUrl: '',
    imgPath: 'https://job.zust.edu.cn/zjcFiles/',
    openId: '',
    unionid: ''
  }
})