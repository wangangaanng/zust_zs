// pages/school/school.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
import WxValidate from '../../libs/wx-validate/WxValidate'
const app = getApp()
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;

Page({
  data: {
    currentTab: 0,
    key: '',
    list1: [],
    list2: [],
    pageSize: 20,
    pageNo1: 1,
    pageNo2: 1,
    totalPage1: '',
    totalPage2: '',
    minDate: new Date().getTime(),
    imgPath: imgPath,
    zwGzznColumns: [],
    zwGzxzColumns: [],
    zwNlyqColumns: [],
    zwXlyqColumns: [],
    zwGznxColumns: [],
    zwYyyqColumns: [],
    show: {
      bottom: false,
      gzzn: false,
      gzxz: false,
      nlyq: false,
      xlyq: false,
      gznx: false,
      yyyq: false,
      zwSxsj: false,
    },
    area: '请选择',
    areaList: {},
    loading: true,
    value: 330106,
    gzznStr: '请选择',
    gzxzStr: '请选择',
    nlyqStr: '请选择',
    xlyqStr: '请选择',
    gznxStr: '请选择',
    yyyqStr: '不限',
    zwSxsjStr: '请选择',
    form: {
      zwbt: '',
      zwPro: '',
      zwCity: '',
      zwArea: '',
      zwGzzn: '',
      zwGzxz: '',
      zwLxyx: '',
      zwXs: '',
      zwZprs: '',
      zwNlyq: '',
      zwXlyq: '',
      zwGznx: '',
      zwYyyq: '',
      zwGwzz: '',
      zwSxsj: '',
    },
  },
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },
  submitForm(e) {
    const params = e.detail.value

    console.log(params)

    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      this.showModal(error)
      return false
    }
    params.zwlx = 0
    params.zwXs = parseInt(params.zwXs)
    params.owid = this.data.owid
    params.qyxxRefOwid = wx.getStorageSync('yhOwid')
    common.ajax('zustjy/bckjBizJob/fixJob', params, function (res) {
      if (res.data.backCode == 0) {
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: "修改成功",
          success(res) {
            if (res.confirm) {
              // wx.navigateBack({
              //   delta:1
              // })
              console.log('用户点击确定')
            } else if (res.cancel) {
              console.log('用户点击取消')
            }
          }
        })
      } else {
        wx.showToast({
          title: res.data.errorMess,
          icon: 'none',
          duration: 2000
        })
      }
    });
  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      zwbt: {
        required: true,
      },
      zwPro: {
        required: true,
      },
      zwCity: {
        required: true
      },
      zwArea: {
        required: true
      },
      zwGzzn: {
        required: true
      },
      zwGzxz: {
        required: true
      },
      zwXs: {
        required: true
      },
      zwZprs: {
        required: true
      },
      zwLxyx: {
        required: true,
        email: true,
      },
      zwNlyq: {
        required: true
      },
      zwXlyq: {
        required: true
      },
      zwGznx: {
        required: true
      },
      zwYyyq: {
        required: true
      },
      zwGwzz: {
        required: true,
        maxlength: 200,
      },
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      zwbt: {
        required: '请填写职位名称'
      },
      zwPro: {
        required: '请选择所在省市区',
      },
      zwCity: {
        required: '请选择所在省市区',
      },
      zwArea: {
        required: '请选择所在省市区',
      },
      zwGzzn: {
        required: '请填写职能类别',
      },
      zwGzxz: {
        required: '请填写工作性质',
      },
      zwLxyx: {
        required: '请填写邮箱',
        email: '请填写正确邮箱',
      },
      zwXs: {
        required: '请填写薪水',
      },
      zwZprs: {
        required: '请填写招聘人数',
      },
      zwNlyq: {
        required: '请填写年龄要求',
      },
      zwXlyq: {
        required: '请选择学历要求',
      },
      zwGznx: {
        required: '请选择工作年限',
      },
      zwYyyq: {
        required: '请选择语言要求',
      },
      zwGwzz: {
        required: '请填写职位详情',
        maxlength: "职位详情不得超过200字",
      }

    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  onConfirm(e) {
    if (e.target.dataset.type == 1) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gzznStr: dicVal2,
        'form.zwGzzn': dicVal1
      })
      this.toggle('gzzn', false);
    } else if (e.target.dataset.type == 2) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gzxzStr: dicVal2,
        'form.zwGzxz': dicVal1
      })
      this.toggle('gzxz', false);
    } else if (e.target.dataset.type == 3) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        nlyqStr: dicVal2,
        'form.zwNlyq': dicVal1
      })
      this.toggle('nlyq', false);
    } else if (e.target.dataset.type == 4) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        xlyqStr: dicVal2,
        'form.zwXlyq': dicVal1
      })
      this.toggle('xlyq', false);
    } else if (e.target.dataset.type == 5) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gznxStr: dicVal2,
        'form.zwGznx': dicVal1
      })
      this.toggle('gznx', false);
    } else if (e.target.dataset.type == 6) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        yyyqStr: dicVal2,
        'form.zwYyyq': dicVal1
      })
      this.toggle('yyyq', false);
    } else if (e.target.dataset.type == 7) {
      this.setData({
        area: `${e.detail.values[0].name}-${e.detail.values[1].name}-${e.detail.values[2].name}`,
        'form.zwPro': e.detail.values[0].name,
        'form.zwCity': e.detail.values[1].name,
        'form.zwArea': e.detail.values[2].name
      })
      this.toggle('bottom', false);
    } else if (e.target.dataset.type == 8) {
      console.log(e)
      var date = util.formatTime1(new Date(e.detail))
      this.setData({
        zwSxsjStr: date,
        'form.zwSxsj': date
      })
      this.toggle('zwSxsj', false);
    }

  },
  onCancel(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('gzzn', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('gzxz', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('nlyq', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('xlyq', false);
    } else if (e.target.dataset.type == 5) {
      this.toggle('gznx', false);
    } else if (e.target.dataset.type == 6) {
      this.toggle('yyyq', false);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bottom', false);
    } else if (e.target.dataset.type == 8) {
      this.toggle('zwSxsj', false);
    }
  },
  toggle(type, show) {
    this.setData({
      [`show.${type}`]: show
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(options.owid){
      this.setData({
        owid: options.owid
      })
      getOneJob(this, options.owid)
    }
    this.initValidate()
    getByType1(this)
    getByType2(this)
    getByType3(this)
    getByType4(this)
    getByType5(this)
    getByType6(this)
    zwSubcribeList(this)
    myBmList(this)
  },
  showBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('gzzn', true);
    } else if (e.target.dataset.type == 2) {
      this.toggle('gzxz', true);
    } else if (e.target.dataset.type == 3) {
      this.toggle('nlyq', true);
    } else if (e.target.dataset.type == 4) {
      this.toggle('xlyq', true);
    } else if (e.target.dataset.type == 5) {
      this.toggle('gznx', true);
    } else if (e.target.dataset.type == 6) {
      this.toggle('yyyq', true);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bottom', true);
    } else if (e.target.dataset.type == 8) {
      this.toggle('zwSxsj', true);
    }
  },

  hideBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('gzzn', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('gzxz', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('nlyq', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('xlyq', false);
    } else if (e.target.dataset.type == 5) {
      this.toggle('gznx', false);
    } else if (e.target.dataset.type == 6) {
      this.toggle('yyyq', false);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bottom', false);
    } else if (e.target.dataset.type == 8) {
      this.toggle('zwSxsj', false);
    }
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    wx.request({
      url: app.globalData.imgPath + 'getAllRegion.json',
      success: response => {
        this.setData({
          loading: false,
          areaList: response.data.data
        });
      }
    });

  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var that = this;
    if (that.data.currentTab == 1) {
      if ((that.data.pageNo1 + 1) <= that.data.totalPage1) {
        that.setData({
          pageNo1: that.data.pageNo1 + 1,
        })
        zwSubcribeList(that);
      }
    } else if (that.data.currentTab == 2) {
      if ((that.data.pageNo2 + 1) <= that.data.totalPage2) {
        that.setData({
          pageNo2: that.data.pageNo2 + 1,
        })
        myBmList(that);
      }
    }
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  onChange(e) {
    this.setData({
      key: e.detail
    });
  },

  clickTab: function (e) {
    this.setData({
      currentTab: e.detail.index
    })
  },
  onClear() {
    var that = this;
    refresh(this);
  },
  onSearch: function () {
    var that = this;
    refresh(this);
  },

})

var getOneJob = function (that, owid) {
  var data = { 
    "yhOwid": wx.getStorageSync('yhOwid'),
    "owid": owid
  };
  common.ajax('zustjy/bckjBizJob/getOneJob', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean) {
        that.setData({
          form: data.bean,
          area: `${data.bean.zwPro}-${data.bean.zwCity}-${data.bean.zwArea}`,
          gzznStr: data.bean.zwGzznStr || '',
          gzxzStr: data.bean.zwGzxzStr || '',
          nlyqStr: data.bean.zwNlyqStr || '',
          xlyqStr: data.bean.zwXlyqStr || '',
          gznxStr: data.bean.zwGznxStr || '',
          yyyqStr: data.bean.zwYyyqStr || '',
          zwSxsjStr: data.bean.zwSxsj?data.bean.zwSxsj.substring(0,10):'暂无',
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

var getByType1 = function (that) {
  var data = { "dicType": "20005" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.zwNlyqColumns.push(obj)
        }
        that.setData({
          zwNlyqColumns: that.data.zwNlyqColumns
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

var getByType2 = function (that) {
  var data = { "dicType": "20006" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.zwXlyqColumns.push(obj)
        }
        that.setData({
          zwXlyqColumns: that.data.zwXlyqColumns
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

var getByType3 = function (that) {
  var data = { "dicType": "20007" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.zwYyyqColumns.push(obj)
        }
        that.setData({
          zwYyyqColumns: that.data.zwYyyqColumns
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

var getByType4 = function (that) {
  var data = { "dicType": "20008" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.zwGznxColumns.push(obj)
        }
        that.setData({
          zwGznxColumns: that.data.zwGznxColumns
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

var getByType5 = function (that) {
  var data = { "dicType": "20003" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.zwGzznColumns.push(obj)
        }
        that.setData({
          zwGzznColumns: that.data.zwGzznColumns
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

var getByType6 = function (that) {
  var data = { "dicType": "20004" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.zwGzxzColumns.push(obj)
        }
        that.setData({
          zwGzxzColumns: that.data.zwGzxzColumns
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

var zwSubcribeList = function (that) {
  var data = { 
    "jobRefOwid": that.data.owid,
    "pageSize": that.data.pageSize,
    "pageNo": that.data.pageNo1
  };
  common.ajax('zustjy/bckjBizXsgz/zwSubcribeList', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      var list;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        list = that.data.list1.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list1: list,
        totalPage11: totalPage,
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


var myBmList = function (that) {
  var data = {
    "jobRefOwid": that.data.owid,
    "pageSize": that.data.pageSize,
    "bmlx": 1,
    "bmdx": 2,
    "pageNo": that.data.pageNo2
  };
  common.ajax('zustjy/bckjBizJybm/myBmList', data, function (res) {
    if (res.data.backCode == 0) {
      var list;
      if (res.data.bean.records && res.data.bean.records.length > 0) {
        list = that.data.list2.concat(res.data.bean.records)
      }
      var totalPage = res.data.bean.totalPage;
      that.setData({
        list2: list,
        totalPage2: totalPage,
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