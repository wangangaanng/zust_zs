//app.js
App({
  onLaunch: function () {
    try {
      const token = wx.getStorageSync('userInfo');
      if (token === '' || token === undefined || token === null) {
        wx.reLaunch({
          url: '/pages/auth/auth'
        })
      } else { }
    } catch (err) { }
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
    ApiUrl: 'http://172.16.13.106:8888/',
    localimgUrl: '',
    imgPath: 'http://172.16.13.109:8080/zjcFiles/',
    openId: '',
    unionid: ''
  }
})