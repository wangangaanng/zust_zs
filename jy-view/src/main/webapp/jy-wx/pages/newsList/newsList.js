// pages/newsList/newsList.js
var common = require('../../libs/common/common.js')
const app = getApp()
var wzList=[];
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab:0,
    key:'',
    navList:[],
    wzList:[

    ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(options.fid){
      getLm(this, options.fid)
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
    setTimeout(function () {
      console.log(this.data.wzList)
    }, 2000)
  },

 
  clickTab:function(e){
    this.setData({
      currentTab: e.detail.index
    })
  },
  newsDetail(e){
    wx.navigateTo({
      url: '../newsDetail/newsDetail?owid='+e.currentTarget.dataset.owid,
    })
  },
  onChange(e) {
    this.setData({
      key: e.detail
    });
  },

  onClear() {
    var that = this;
    refresh(this);
  },
  onSearch: function () {
    var that = this;
    refresh(this);
  },
  loadMore:function(){
    var index = this.data.currentTab
    // console.log(this.data.wzList[index].pageNo);
    // console.log(this.data.wzList[index].totalPage)
    var page = "wzList[" + index + "].pageNo";
    if ((this.data.wzList[index].pageNo + 1) <= this.data.wzList[index].totalPage) {
      this.setData({
        [page]: this.data.wzList[index].pageNo + 1,
      })
      getList(this, this.data.navList[index].owid, this.data.navList[index].isDetail, index, this.data.wzList[index].pageNo);//index, pageNo
    }
  }
})
var getLm = function(that,bh){
  wzList=[];
  var data = { "fid": bh, "wzbh": "1" };
  common.ajax('zustcommon/bckjDicMenu/getLmMenu', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      for (var i = 0; i < res.data.bean.length; i++) {
        var obj = {};
        var object = res.data.bean[i];
        obj.owid = object.CODE;
        obj.name = object.NAME;
        obj.isDetail = object.BXLX;
        arr.push(obj);
        
        var wzListObj = {};
        wzListObj.pageSize = 20;
        wzListObj.pageNo = 1;
        wzListObj.totalPage = "";
        wzListObj.list = [];
        wzList.push(wzListObj);
        that.setData({
          wzList: wzList
        })


        getList(that, obj.owid, obj.isDetail,i,1);//index, pageNo
      }
      that.setData({
        navList: arr,
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
var getList = function (that, owid, isDetail,index,pageNo){
  var data = { "lmbh": owid, "pageNo": pageNo, "pageSize": "20", "wzzt": "1", "isDetail": isDetail, "gjz": that.data.key };//isDetail:1列表
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      if (res.data.bean.records){
        for (var i = 0; i < res.data.bean.records.length; i++) {
          var obj = {};
          var object = res.data.bean.records[i];
          if (object.fbsj) {
            object.date = object.fbsj.substring(5, 7) + "." + object.fbsj.substring(8, 10);
            object.year = object.fbsj.substring(0, 4);
          }
          arr.push(object);
        }
      }
      
      var page = "wzList[" + index + "].pageNo";
      var totalPage = "wzList[" + index + "].totalPage";
      var list = "wzList[" + index + "].list";
      that.setData({
        [page]: pageNo,
        [totalPage]: res.data.bean.totalPage,
        [list]: that.data.wzList[index].list.concat(arr),
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
function refresh(that) {
  wzList = [];
  var navList = that.data.navList;
  for (var i = 0; i < navList.length; i++) {
    var wzListObj = {};
    wzListObj.pageSize = 20;
    wzListObj.pageNo = 1;
    wzListObj.totalPage = "";
    wzListObj.list = [];
    wzList.push(wzListObj);
    that.setData({
      wzList: wzList
    })
    getList(that, navList[i].owid, navList[i].isDetail, i, 1);//index, pageNo
  }
}