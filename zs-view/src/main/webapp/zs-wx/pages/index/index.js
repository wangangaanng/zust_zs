//index.js
//获取应用实例
var common = require('../../libs/common/common.js')
const app = getApp()
var imgPath = app.globalData.imgPath;
Page({
  data: {
    imgPath:imgPath,
    imgUrls: [],
    menuList: [
      { text: '招生计划', icon: '../../../static/index-icon01.png', url: '../plan/plan' }, 
      { text: '招生专业', icon: '../../../static/index-icon02.png', url: '../newsList/newsList?lmbh=129' }, 
      { text: '专业导航', icon: '../../../static/index-icon03.png', url: '../xueyuan/xueyuan' }, 
      { text: '考生报名', icon: '../../../static/index-icon04.png', url: '../ksbm/ksbm' }, 
      { text: '查询中心', icon: '../../../static/index-icon05.png', url: '../search/search' }, 
      { text: '招生政策', icon: '../../../static/index-icon06.png', url: '../newsList/newsList?fid=67' }, 
      { text: '合作办学', icon: '../../../static/index-icon07.png', url: '../newsList/newsList?fid=65' }, 
      { text: '生源基地', icon: '../../../static/index-icon08.png', url: '../syjd/syjd' }],
    pageSize: 20,
    pageNo1: 1,
    totalPage1: '',
    newsList1:[],
    floorstatus:false,
  },
  newsDetail(e){
    wx.navigateTo({
      url: '../newsDetail/newsDetail?owid=' + e.currentTarget.dataset.owid,
    })
  },
  positionDetail(e) {
    if (e.currentTarget.dataset.lx == 5) {
      wx.navigateTo({
        url: '../newsDetail/newsDetail?owid=' + e.currentTarget.dataset.owid,
      })
    } else {
      wx.navigateTo({
        url: '../qyInfo/qyInfo?owid=' + e.currentTarget.dataset.owid,
      })
    }

  },
  onPageScroll: function (e) {
    if (e.scrollTop > 700) {
      this.setData({
        floorstatus: true
      });
    } else {
      this.setData({
        floorstatus: false
      });
    }
  },
  //回到顶部
  goTop: function (e) { 
    if (wx.pageScrollTo) {
      wx.pageScrollTo({
        scrollTop: 0
      })
    } else {
      wx.showModal({
        title: '提示',
        content: '当前微信版本过低，无法使用该功能，请升级到最新微信版本后重试。'
      })
    }
  },
  onLoad: function () {
    // 登录
    var that=this;
    getPicList(this);//轮播
    getLm(this);
    getList1(this);

  },
  onPullDownRefresh: function () {
    var that = this;
    this.setData({
      pageNo1: 1,
      totalPage1: '',
      newsList1: [],
    })
    getPicList(this);//轮播
    getLm(this);
    getList1(this);
    //当逻辑执行完后关闭刷新    
    wx.stopPullDownRefresh()
  },
  onReachBottom: function () {
    var that = this;
    if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
      that.setData({
        pageNo1: that.data.pageNo1 + 1,
      })
      getList1(that, '0');//职位
    }
  },
  url1:function(){
    wx.navigateTo({
      url: '../consult/consult',
    })
  },
  clicktab:function(e){
    this.setData({
      currentTab: e.detail.index
    })
  },
  // linkurl:function(e){
  //   var url = e.currentTarget.dataset.url;
  //   var index = e.currentTarget.dataset.index;
  //   if(url){
      
  //       wx.navigateTo({
  //         url: url,
  //       })
      
      
  //   }
  // }
  })

var getPicList = function (that) {//首页轮播:
  var data = { "lmbh": "127", "lx": "0", "zszd": "0" };
  common.ajax('zustcommon/bckjBizPicvid/getPicList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      var count = 0;
      if (res.data.bean && res.data.bean.length > 0) {
        for (var i = 0; i < res.data.bean.length; i++) {
          var obj = {};
          var object = res.data.bean[i];
          if (object.xsbt) {
            // if ((object.xsbt) && (count < 5)) {
            obj.owid = object.owid;
            obj.xsbt = object.xsbt;
            arr.push(obj);
            count += 1;
          }
        }
        that.setData({
          imgUrls: arr,
        })
      }
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}

var getLm = function (that) {
  var data = { "lmbh": "129", "wzbh": "0" };
  common.ajax('zustcommon/bckjDicMenu/getLmmc', data, function (res) {
    if (res.data.backCode == 0) {
      if (res.data.bean && res.data.bean.NAME) {
        that.data.menuList[1].text = res.data.bean.NAME
        that.setData({
          menuList: that.data.menuList
        })
        
      }
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}

var getList1 = function (that){
  var data = { "lmbh": '119', "pageNo": that.data.pageNo1, "pageSize": "20"};
  common.ajax('zustcommon/bckjBizArticle/searchByYjlm', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      if (res.data.bean.records && res.data.bean.records.length>0){
        for (var i = 0; i < res.data.bean.records.length; i++) {
          var obj = {};
          var object = res.data.bean.records[i];
          obj.owid = object.owid;
          obj.wzbt = object.wzbt;
          obj.tpjj = object.tpjj;
          obj.fbsj = object.fbsj ? object.fbsj.substring(0,10):'';
          obj.ydcs = object.ydcs;
          arr.push(obj);
        }
      }
      var newsList1 = that.data.newsList1.concat(arr)
      var totalPage1 = res.data.bean.totalPage;
      that.setData({
        newsList1: newsList1,
        totalPage1: totalPage1,
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
