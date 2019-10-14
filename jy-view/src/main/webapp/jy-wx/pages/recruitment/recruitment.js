// pages/recruitment/recruitment.js
var common = require('../../libs/common/common.js')
const app = getApp()
var wzList = [];
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab: 0,
    key:"",
    navList: [],
    wzList: [

    ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.fid) {
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

  },


  clickTab: function (e) {
    this.setData({
      currentTab: e.detail.index
    })
  },
  positionDetail(e){
    wx.navigateTo({
      url: '../qyInfo/qyInfo?owid=' + e.currentTarget.dataset.owid,
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
  onSearch:function(){
    var that=this;
    refresh(this);
  },
  loadMore: function () {
    var index = this.data.currentTab
    var page = "wzList[" + index + "].pageNo";
    if ((this.data.wzList[index].pageNo + 1) <= this.data.wzList[index].totalPage) {
      this.setData({
        [page]: this.data.wzList[index].pageNo + 1,
      })
      getList(this, this.data.navList[index].owid, this.data.navList[index].isDetail, this.data.navList[index].SJHQDX, index, this.data.wzList[index].pageNo);//index, pageNo
    }
  }
})
var getLm = function (that, bh) {
  wzList = [];
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
        obj.SJHQDX = object.SJHQDX;
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


        getList(that, obj.owid, obj.isDetail, obj.SJHQDX, i, 1);//index, pageNo
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
var getList = function (that, owid, isDetail, SJHQDX, index, pageNo) {
  if (SJHQDX==5){//公告
    var data = { "lmbh": owid, "pageNo": pageNo, "pageSize": "20", "wzzt": "1", "isDetail": isDetail,"gjz":that.data.key };//isDetail:1列表
    common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
      if (res.data.backCode == 0) {
        var arr = [];
        if (res.data.bean.records) {
          for (var i = 0; i < res.data.bean.records.length; i++) {
            var obj = {};
            var object = res.data.bean.records[i];
            obj.owid =object.owid;
            obj.wzbt = object.wzbt;
            if (object.fbsj) {
              obj.date = object.fbsj.substring(5, 7) + "." + object.fbsj.substring(8, 10);
              obj.year = object.fbsj.substring(0, 4);
            }
            obj.zwlx=5;
            arr.push(obj);
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
  }else{
    var data={
      "zwgjz": that.data.key, "zwlx": SJHQDX, "pageNo": pageNo, "pageSize": "20",
    }
    common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
      if (res.data.backCode == 0) {
        var arr = [];
        if (res.data.bean.records) {
          for (var i = 0; i < res.data.bean.records.length; i++) {
            var obj = {};
            var object = res.data.bean.records[i];
            if (object.createtime) {
              obj.date = object.createtime.substring(5, 7) + "." + object.createtime.substring(8, 10);
              obj.year = object.createtime.substring(0, 4);
            }
            if (object.zphKsrq){
              if (object.zwlx == 4) {
                obj.zphKsrq = object.zphKsrq.substring(0, 16)
              } else {
                obj.zphKsrq = object.zphKsrq.substring(0, 10)
              }
            }
            obj.owid = object.owid;
            obj.zwbt = object.zwbt;
            obj.zphJbdd = object.zphJbdd;
            obj.zphJtsj = object.zphJtsj;
            obj.zwCity = object.zwCity;
            obj.zwlx = object.zwlx;
            if (object.zwlx == 0){
              obj.qymc = object.qymc;
              obj.zwGzxzStr = object.zwGzxzStr;
            }
            
            arr.push(obj);
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
}
function refresh(that){
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
    getList(that, navList[i].owid, navList[i].isDetail, navList[i].SJHQDX, i, 1);//index, pageNo
  }
}