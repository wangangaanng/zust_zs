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
      { text: '学校概况', icon: '../../../static/index-icon01.png', url: '../school/school' }, 
      { text: '学院介绍', icon: '../../../static/index-icon02.png', url: '../xueyuan/xueyuan' }, 
      { text: '新闻公告', icon: '../../../static/index-icon03.png', url: '../newsList/newsList?fid=16' }, 
      { text: '招聘信息', icon: '../../../static/index-icon04.png', url: '../recruitment/recruitment?fid=17' }, 
      { text: '职业指导', icon: '../../../static/index-icon05.png', url: '../newsList/newsList?fid=18' }, 
      { text: '就业排行榜', icon: '../../../static/index-icon06.png',url: '' }, 
      { text: '学生服务', icon: '../../../static/index-icon07.png', url: '../stuService/stuService' }, 
      { text: '企业服务', icon: '../../../static/index-icon08.png', url: '../qyService/qyService' }],
    pageSize: 20,
    pageNo1: 1,
    totalPage1: '',
    newsList1:[],
    pageNo2: 1,
    totalPage2: '',
    newsList2: [],
    pageNo3: 1,
    totalPage3: '',
    newsList3: [],
    pageNo4: 1,
    totalPage4: '',
    newsList4: [],
    currentTab:'0',
    floorstatus:false,
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
    getAdv(this);
    getList1(this,'0');//职位
    getList2(this, '2');//企业招聘公告
    getList3(this, '3');//招聘会
    getList4(this, '4');//宣讲会

  },
  onReachBottom: function () {
    var that = this;
    if (this.data.currentTab==0){
      if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
        that.setData({
          pageNo1: that.data.pageNo1 + 1,
        })
        getList1(that, '0');//职位
      }
    }
    if (this.data.currentTab == 1) {
      if ((that.data.pageNo2 + 1) <= that.data.totalPage2) {
        that.setData({
          pageNo2: that.data.pageNo2 + 1,
        })
        getList2(that, '2');//企业招聘公告
      }
    }
    if (this.data.currentTab == 2) {
      if ((that.data.pageNo3 + 1) <= that.data.totalPage3) {
        that.setData({
          pageNo3: that.data.pageNo3 + 1,
        })
        getList3(that, '3');//招聘会
      }
    }
    if (this.data.currentTab == 3) {
      if ((that.data.pageNo4 + 1) <= that.data.totalPage4) {
        that.setData({
          pageNo4: that.data.pageNo4 + 1,
        })
        getList4(that, '4');//宣讲会
      }
    }
  },
  url1:function(){
    wx.navigateTo({
      url: '../lianxiwm/lianxiwm',
    })
  },
  clicktab:function(e){
    console.log(e);
    this.setData({
      currentTab: e.detail.index
    })
  },
  linkurl:function(e){
    var url = e.currentTarget.dataset.url;
    var index = e.currentTarget.dataset.index;
    if(url){
      if (index == 6) {
        if (wx.getStorageSync('yhOwid')){
          if (wx.getStorageSync('userType') == 1){
            wx.navigateTo({
              url: url,
            })
          } else if (wx.getStorageSync('userType') == 0) {
            wx.showModal({
              title: '提示',
              content: "当前登录的是企业账户，是否要登录学生账户？",
              success(res) {
                if (res.confirm) {
                  wx.navigateTo({
                    url: '../stuLogin/stuLogin',
                  })
                } else if (res.cancel) {
                  console.log('用户点击取消')
                }
              }
            })
          }
        }else{
          wx.navigateTo({
            url: '../stuLogin/stuLogin',
          })
        }
      } else if (index == 7) {
        if (wx.getStorageSync('yhOwid')) {
          if (wx.getStorageSync('userType') == 1) {
            wx.showModal({
              title: '提示',
              content: "当前登录的是学生账户，是否要登录企业账户？",
              success(res) {
                if (res.confirm) {
                  wx.navigateTo({
                    url: '../qyLogin/qyLogin',
                  })
                } else if (res.cancel) {
                  console.log('用户点击取消')
                }
              }
            })
          } else if (wx.getStorageSync('userType') == 0) {
            wx.navigateTo({
              url: url,
            })
          }
        } else {
          wx.navigateTo({
            url: '../qyLogin/qyLogin',
          })
        }
      } else {
        wx.navigateTo({
          url: url,
        })
      }
      
    }
  }
  })
var getAdv = function(that){//新闻快递轮播图
  var data = { "lmbh": "26", "pageNo": "1", "pageSize": "20", "wzzt": "1", "isDetail": "1" };
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      var count=0;
      for (var i = 0; i < res.data.bean.records.length; i++) {
        var obj = {};
        var object = res.data.bean.records[i];
        if ((object.tpjj) && (count < 5)){
          obj.owid = object.owid;
          obj.tpjj = object.tpjj;
          arr.push(obj);
          count += 1;
        }
      }
      that.setData({
        imgUrls: arr,
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
var getList1 = function (that,lx){
  var data = {
    "zwgjz": "", "zwQymc": "", "zwPro": "", "zwCity": "", "zwlx": lx, "pageNo": that.data.pageNo1,"pageSize": that.data.pageSize, };
  common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      for (var i = 0; i < res.data.bean.records.length; i++) {
        var obj = {};
        var object = res.data.bean.records[i];
        obj.owid = object.owid;
        obj.date = object.createtime.substring(5, 7) + "." + object.createtime.substring(8, 10);
        obj.year = object.createtime.substring(0, 4);
        obj.zwbt = object.zwbt;
        obj.qymc = object.exp1;
        obj.city = object.zwCity;
        obj.gzxz = object.zwGzxzStr;
        arr.push(obj);
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
var getList2 = function (that, lx) {
  var data = {
    "zwgjz": "", "zwQymc": "", "zwPro": "", "zwCity": "", "zwlx": lx, "pageNo": that.data.pageNo2, "pageSize": that.data.pageSize,
  };
  common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      for (var i = 0; i < res.data.bean.records.length; i++) {
        var obj = {};
        var object = res.data.bean.records[i];
        obj.owid = object.owid;
        obj.date = object.createtime.substring(5, 7) + "." + object.createtime.substring(8, 10);
        obj.year = object.createtime.substring(0, 4);
        obj.zwbt = object.zwbt;
        obj.city = object.zwCity;
        arr.push(obj);
      }
      var newsList2 = that.data.newsList2.concat(arr)
      var totalPage2 = res.data.bean.totalPage;
      that.setData({
        newsList2: newsList2,
        totalPage2: totalPage2,
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
var getList3 = function (that, lx) {
  var data = {
    "zwgjz": "", "zwQymc": "", "zwPro": "", "zwCity": "", "zwlx": lx, "pageNo": that.data.pageNo3, "pageSize": that.data.pageSize,
  };
  common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      for (var i = 0; i < res.data.bean.records.length; i++) {
        var obj = {};
        var object = res.data.bean.records[i];
        obj.owid = object.owid;
        obj.date = object.createtime.substring(5, 7) + "." + object.createtime.substring(8, 10);
        obj.year = object.createtime.substring(0, 4);
        obj.zwbt = object.zwbt;
        obj.zphJbdd = object.zphJbdd;
        obj.zphKsrq = object.zphKsrq.substring(0, 10);
        obj.zphJtsj = object.zphJtsj;
        obj.city = object.zwCity;
        arr.push(obj);
      }
      var newsList3 = that.data.newsList3.concat(arr)
      var totalPage3 = res.data.bean.totalPage;
      that.setData({
        newsList3: newsList3,
        totalPage3: totalPage3,
      })
    } else {
      wx.showToast({
        title: '系统错误',
        icon: 'none',
        duration: 2000
      })
    }
  });
}
var getList4 = function (that, lx) {
  var data = {
    "zwgjz": "", "zwQymc": "", "zwPro": "", "zwCity": "", "zwlx": lx, "pageNo": that.data.pageNo4, "pageSize": that.data.pageSize,
  };
  common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      if (res.data.bean.records){
        for (var i = 0; i < res.data.bean.records.length; i++) {
          var obj = {};
          var object = res.data.bean.records[i];
          obj.owid = object.owid;
          obj.date = object.createtime.substring(5, 7) + "." + object.createtime.substring(8, 10);
          obj.year = object.createtime.substring(0, 4);
          obj.zwbt = object.zwbt;
          obj.zphJbdd = object.zphJbdd;
          obj.zphKsrq = object.zphKsrq.substring(0, 10);
          obj.zphJtsj = object.zphJtsj;
          obj.city = object.zwCity;
          arr.push(obj);
        }
        var newsList4 = that.data.newsList4.concat(arr)
        var totalPage4 = res.data.bean.totalPage;
        that.setData({
          newsList4: newsList4,
          totalPage4: totalPage4,
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