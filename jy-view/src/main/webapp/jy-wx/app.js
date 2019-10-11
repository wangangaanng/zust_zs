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
  globalData: {
    userInfo: '',
    lj: '',
    ApiUrl: 'http://172.16.13.106:8888/',
    localimgUrl: '',
    imgUrl: 'http://172.16.13.109:8080/zjcFiles/',
    openId: '',
    unionid: ''
  }
})