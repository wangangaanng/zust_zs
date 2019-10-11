//index.js
//获取应用实例
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
  },
  onLoad: function () {
    var data = this.data;

  },
  onReachBottom: function () {
    console.log(1);
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
