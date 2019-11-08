// pages/newsList/newsList.js
var common = require('../../libs/common/common.js')
const app = getApp()
var wzList = [];
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab: 0,
    key: '',
    navList: [],
    wzList: [

    ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wzList = [];
    this.setData({
      currentTab: 0,
      key: '',
      navList: [],
      wzList: [

      ],
    })

    getByType(this)
    // if (options.fid) {//tab列表
    //   getLm(this, options.fid)
    // } else if (options.lmbh) {//单列表
    //   var that = this;
    //   that.setData({
    //     navList: [{ owid: options.lmbh, name: '', isDetail: '1' }],
    //   })

    //   var wzListObj = {};
    //   wzListObj.pageSize = 20;
    //   wzListObj.pageNo = 1;
    //   wzListObj.totalPage = "";
    //   wzListObj.list = [];
    //   wzList.push(wzListObj);
    //   that.setData({
    //     wzList: wzList
    //   })
    //   getList(that, options.lmbh, 1, 0, 1);//index, pageNo
    // }

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
    wzList = []
  },


  clickTab: function (e) {
    this.setData({
      currentTab: e.detail.name
    })
  },
  newsDetail(e) {
    wx.navigateTo({
      url: '../newsDetail/newsDetail?owid=' + e.currentTarget.dataset.owid,
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
  loadMore: function () {
    var index = this.data.currentTab
    // console.log(this.data.wzList[index].pageNo);
    // console.log(this.data.wzList[index].totalPage)
    var page = "wzList[" + index + "].pageNo";
    if ((this.data.wzList[index].pageNo + 1) <= this.data.wzList[index].totalPage) {
      this.setData({
        [page]: this.data.wzList[index].pageNo + 1,
      })
      getList(this, this.data.navList[index].dicVal1, index, this.data.wzList[index].pageNo);//index, pageNo
    }
  }
})
var getLm = function (that, bh) {
  wzList = [];
  var data = { "fid": bh, "wzbh": "0" };
  common.ajax('zustcommon/bckjDicMenu/getLmMenu', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      if (res.data.bean && res.data.bean.length > 0) {
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


          getList(that, obj.owid, obj.isDetail, i, 1);//index, pageNo
        }
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
var getList = function (that, nf, index, pageNo) {
  var data = { "lmbh": "66", "pageNo": pageNo, "nf": nf, "pageSize": "20", "wzzt": "1", "isDetail": '1', "gjz": that.data.key };//isDetail:1列表
  common.ajax('zustcommon/bckjBizArticle/getMuArticle', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      if (res.data.bean.records) {
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
    getList(that, navList[i].dicVal1, i, 1);//index, pageNo
  }
}

var getByType = function (that) {
  wzList = [];
  var data = { "dicType": '10027', "orderBy":"b.dicVal1 desc" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var arr = [];
      if (res.data.bean && res.data.bean.length > 0) {
        for (var i = 0; i < res.data.bean.length; i++) {
          var obj = {};
          var object = res.data.bean[i];
          obj.dicVal1 = object.dicVal1;
          obj.dicVal2 = object.dicVal2;
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


          getList(that, obj.dicVal1, i, 1);//index, pageNo
        }
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