// pages/enterpriseXjh/enterpriseXjh.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
const app = getApp()
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageSize: 20,
    pageNo: 1,
    totalPage: '',
    key: '',
    xjhList: [],
    show:{
      bottom: false
    },
    lx:'',
    lxStr:'请选择',
    lxColumns: [{ dicVal1: '0', dicVal2: '职位' }, { dicVal1: '3', dicVal2: '职来职往' }, { dicVal1: '4', dicVal2: '宣讲会' }],
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    myJobList(this);
  },
  onConfirm(e) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        lxStr: dicVal2,
        lx: dicVal1,
        xjhList: [],
        pageNo: 1,
        totalPage: ""
      })
    this.toggle('bottom', false);
    myJobList(this)
  },
  onCancel(e) {
    this.toggle('bottom', false);
  },
  toggle(type, show) {
    this.setData({
      [`show.${type}`]: show
    });
  },
  showBottom(e) {
    this.toggle('bottom', true);
  },

  hideBottom(e) {
    this.toggle('bottom', false);
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

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

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
    var that = this;
    if ((that.data.pageNo + 1) <= that.data.totalPage) {
      that.setData({
        pageNo: that.data.pageNo + 1,
      })
      myJobList(that);
    }
  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
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
})


function refresh(that) {
  that.setData({
    xjhList: [],
    pageNo: 1,
    totalPage: ""
  })
  myJobList(that);

}

var myJobList = function (that) {
  var data = {
    "zwbt": that.data.key, 
    "zwlx": that.data.lx,
    "zphSfbm": 1, "yhRefOwid":wx.getStorageSync('yhOwid'), 
    "pageNo": that.data.pageNo,
    "pageSize": that.data.pageSize,
  };
  common.ajax('zustjy/bckjBizJob/myJobList', data, function (res) {
    if (res.data.backCode == 0) {
      var xjhList;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        for (var i = 0; i < res.data.bean.records.length; i++) {
          res.data.bean.records[i].zwlxStr = '';
          if (res.data.bean.records[i].zwlx==0){
            res.data.bean.records[i].zwlxStr = '职位';
          } else if (res.data.bean.records[i].zwlx == 3) {
            res.data.bean.records[i].zwlxStr = '职来职往';
          } else if (res.data.bean.records[i].zwlx == 4) {
            res.data.bean.records[i].zwlxStr = '宣讲会';
          }
          res.data.bean.records[i].str = '';
          res.data.bean.records[i].color = '';
          if (res.data.bean.records[i].state == 2) {
            if (res.data.bean.records[i].sfbm == 1) {
              res.data.bean.records[i].str = '未报名';
              res.data.bean.records[i].color = 'color:#888;';
            } else if (res.data.bean.records[i].sfbm == 2) {
              res.data.bean.records[i].str = '已报名';
              res.data.bean.records[i].color = 'color:#008784;';
            }

          } else {
            res.data.bean.records[i].str = '';
          }
          if (res.data.bean.records[i].zphBmjzsj && util.compareToday(res.data.bean.records[i].zphBmjzsj)) {
            res.data.bean.records[i].str = '已截止报名';
            res.data.bean.records[i].color = 'color:red;';
          }
          if (res.data.bean.records[i].zphKsrq && util.compareToday(res.data.bean.records[i].zphKsrq)) {
            res.data.bean.records[i].str = '已结束';
            res.data.bean.records[i].color = 'color:red;';
          }
        }
        xjhList = that.data.xjhList.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        xjhList: xjhList,
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