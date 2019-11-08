// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
import WxValidate from '../../libs/wx-validate/WxValidate'
const app = getApp()
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;

Page({

  data: {
    floorstatus: false,
    imgPath: imgPath,
    pageSize: 20,
    pageNo: 1,
    totalPage: 2,
    list: [],
    sfColumns: [],
    zyColumns: [],
    pcColumns: [],
    klColumns: [],
    nfColumns: [],
    show: {
      sf: false,
      zy: false,
      pc: false,
      kl: false,
      nf: false
    },
    sfStr: '请选择',
    zyStr: '请选择',
    pcStr: '请选择',
    klStr: '请选择',
    nfStr: '请选择',
    form: {
      sf: '',
      zy: '',
      pc: '',
      kl: '',
      nf: '',
    },
    btndisabled: false,
    parentId: ''
  },
  onConfirm(e) {
    if (e.target.dataset.type == 1) {
      const { nf } = e.detail.value;
      this.setData({
        nfStr: nf,
        'form.nf': nf
      })
      this.toggle('nf', false);
    } else if (e.target.dataset.type == 2) {
      const { sf } = e.detail.value;
      this.setData({
        sfStr: sf,
        'form.sf': sf
      })
      this.toggle('sf', false);
    } else if (e.target.dataset.type == 3) {
      const { kl } = e.detail.value;
      this.setData({
        klStr: kl,
        'form.kl': kl
      })
      this.toggle('kl', false);
    } else if (e.target.dataset.type == 4) {
      const { pc } = e.detail.value;
      this.setData({
        pcStr: pc,
        'form.pc': pc
      })
      this.toggle('pc', false);
    } else if (e.target.dataset.type == 5) {
      const { zy } = e.detail.value;
      this.setData({
        zyStr: zy,
        'form.zy': zy
      })
      this.toggle('zy', false);
    }
    getChanges(this)
    this.setData({
      pageNo: 1,
      totalPage: '',
      list: [],
    })
    getResult(this)
  },
  onCancel(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('nf', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('sf', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('kl', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('pc', false);
    } else if (e.target.dataset.type == 5) {
      this.toggle('zy', false);
    }
  },
  toggle(type, show) {
    this.setData({
      [`show.${type}`]: show,
      hidden1: show
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // getOneCompany(this)
    getChanges(this)
  },
  showBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('nf', true);
    } else if (e.target.dataset.type == 2) {
      this.toggle('sf', true);
    } else if (e.target.dataset.type == 3) {
      this.toggle('kl', true);
    } else if (e.target.dataset.type == 4) {
      this.toggle('pc', true);
    } else if (e.target.dataset.type == 5) {
      if (!this.data.form.nf || !this.data.form.sf || !this.data.form.kl || !this.data.form.pc) {
        wx.showToast({
          title: '请先选择其他条件',
          icon: 'none'
        })
        return false;
      }
      this.toggle('zy', true);
    }
  },

  hideBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('nf', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('sf', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('kl', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('pc', false);
    } else if (e.target.dataset.type == 5) {
      this.toggle('zy', false);
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
  reset: function () {
    this.setData({
      imgPath: imgPath,
      pageSize: 20,
      pageNo: 1,
      totalPage: 2,
      list: [],
      sfColumns: [],
      zyColumns: [],
      pcColumns: [],
      klColumns: [],
      nfColumns: [],
      sfStr: '请选择',
      zyStr: '请选择',
      pcStr: '请选择',
      klStr: '请选择',
      nfStr: '请选择',
      form: {
        sf: '',
        zy: '',
        pc: '',
        kl: '',
        nf: '',
      },
    })
    getChanges(this)
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
  onReachBottom: function () {
    var that = this;
    if ((that.data.pageNo + 1) <= that.data.totalPage) {
      that.setData({
        pageNo: that.data.pageNo + 1,
      })
      getResult(that);
    }
  },
  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  }
})

var getChanges = function (that) {
  var data = { "nf": that.data.form.nf, "pc": that.data.form.pc, "kl": that.data.form.kl, "sf": that.data.form.sf, "zy": that.data.form.zy };
  common.ajax('zustzs/bckjBizZsjh/getChanges', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean) {
        that.setData({
          klColumns: data.bean.klList ? data.bean.klList : [],
          nfColumns: data.bean.nfList ? data.bean.nfList : [],
          pcColumns: data.bean.pcList ? data.bean.pcList : [],
          sfColumns: data.bean.sfList ? data.bean.sfList : [],
          zyColumns: data.bean.zyList ? data.bean.zyList : []
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

var getResult = function (that) {
  var data = {
    "nf": that.data.form.nf,
    "sf": that.data.form.sf,
    "pc": that.data.form.pc,
    "kl": that.data.form.kl,
    "zy": that.data.form.zy,
    "pageNo": that.data.pageNo,
    "pageSize": that.data.pageSize
  };
  common.ajax('zustzs/bckjBizZsjh/getResult', data, function (res) {
    if (res.data.backCode == 0) {
      var list = []
      var totalPage = res.data.bean.totalPage;

      if (res.data.bean && res.data.bean.records && res.data.bean.records.length > 0) {
        var list = res.data.bean.records
      }
      list = that.data.list.concat(list)
      that.setData({
        list: list,
        totalPage: totalPage
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