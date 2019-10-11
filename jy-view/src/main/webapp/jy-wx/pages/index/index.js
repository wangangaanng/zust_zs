//index.js
//获取应用实例
var common = require('../../libs/common/common.js')
const app = getApp()

Page({
  data: {
    imgUrls: ['https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg','https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg'],
    menuList: [
      { text: '学校概况', icon: '../../../static/index-icon01.png', url: '../newsDetail/newsDetail' }, 
      { text: '学院介绍', icon: '../../../static/index-icon02.png', url: '../xueyuan/xueyuan' }, 
      { text: '新闻公告', icon: '../../../static/index-icon03.png', url: '../newsList/newsList' }, 
      { text: '招聘信息', icon: '../../../static/index-icon04.png', url: '../recruitment/recruitment' }, 
      { text: '职业指导', icon: '../../../static/index-icon05.png', url: '../newsList/newsList' }, 
      { text: '就业排行榜', icon: '../../../static/index-icon06.png' }, 
      { text: '学生服务', icon: '../../../static/index-icon07.png', url: '../stuService/stuService' }, 
      { text: '企业服务', icon: '../../../static/index-icon08.png', url: '../qyService/qyService' }],
    historyList:[],
  },
  onLoad: function () {
    getList(this);

  },
  onReachBottom: function () {

  },
  url1:function(){
    wx.navigateTo({
      url: '../lianxiwm/lianxiwm',
    })
  },
  linkurl:function(e){
    var url = e.currentTarget.dataset.url;
    if(url){
      wx.navigateTo({
        url: url,
      })
    }
  }
  })
var getList = function (that){
  var data = { "zwgjz": "", "zwQymc": "", "zwPro": "", "zwCity": "", "zwlx": "0", "pageSize": 20, "pageNo": 1 };
  common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
    if (res.data.backCode == 0) {
      var historyList = that.data.historyList.concat(res.data.bean.records)
      var totalPage = res.data.bean.totalPage;
      that.setData({
        historyList: historyList,
        totalPage: totalPage,
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