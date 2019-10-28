// pages/newJob/newJob.js
var common = require('../../libs/common/common.js')
var util = require('../../utils/util.js')
import WxValidate from '../../libs/wx-validate/WxValidate'
const app = getApp()
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;

Page({

  /**
   * 页面的初始数据
   * 
   * zzmmColumns: [],
   * cxsyColumns: [{ dicVal1: '农村', dicVal2: '农村' }, { dicVal1: '城镇', dicVal2: '城镇' }],
   * pyfsColumns: ["在职", "委培", "定向", "自筹", "非定向"],
    knslbColumns: ["家庭困难", "家庭困难和残疾", "就业困难", "就业困难、家庭困难和残疾", "就业困难和家庭困难", "就业困难和残疾", "残疾", "非困难生"],
    sfslbColumns: ["免费师范生", "普通师范生", "非师范生"],
    xlccColumns: ["博士生毕业", "博士生结业", "硕士生毕业", "硕士生结业", "本科生毕业", "本科生结业", "专科生毕业", "专科生毕业"],
   * 
   * 
   */
  data: {
    hidden1: false,
    minDate: new Date("1990-01-01").getTime(),
    minDate1: new Date("2010-01-01").getTime(),
    minDate2: new Date(new Date().getFullYear() + '-01-01').getTime(),
    imgPath: imgPath,
    xbColumns: [{ dicVal1: '1', dicVal2: '男' }, { dicVal1: '2', dicVal2: '女' }],
    mzColumns: [],
    byqxColumns: [],
    sydColumns: [],
    bynfColumns: [],
    yrdwxzColumns:[],
    gzzwlbmcColumns: [],
    dwszdmcColumns: [],
    dwlbmcColumns: [],
    sfzydkColumns: [{ dicVal1: '2', dicVal2: '否' }, { dicVal1: '1', dicVal2: '是' }],
    bdzqflbmcColumns: [],
    bdzszdmcColumns: [],
    sfdydwbdzColumns: [{ dicVal1: '2', dicVal2: '否' }, { dicVal1: '1', dicVal2: '是' }],
    xzColumns: ["1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5"],
    sfdlxyColumns: [{ dicVal1: '2', dicVal2: '否' }, { dicVal1: '1', dicVal2: '是' }],
    sfzzColumns: [{ dicVal1: '2', dicVal2: '否' }, { dicVal1: '1', dicVal2: '是' }],
    sfsfColumns: [{ dicVal1: '2', dicVal2: '否' }, { dicVal1: '1', dicVal2: '是' }],
    show: {
      syd: false,
      xb: false,
      xz: false,
      bynf: false,
      mz: false,
      sfdlxy: false,
      sfzz: false,
      sfsf: false,
      byqx: false,
      yrdwxz: false,
      gzzwlbmc: false,
      dwszdmc: false,
      dwlbmc: false,
      sfzydk: false,
      bdzqflbmc: false,
      bdzszdmc: false,
      bdkssj: false,
      bdjssj: false,
      sfdydwbdz: false,
      
    },
    sydStr: '请选择',
    xbStr: '请选择',
    xzStr: '请选择',
    bynfStr: '请选择',
    mzStr: '请选择',
    sfdlxyStr: '请选择',
    sfzzStr: '请选择',
    sfsfStr: '请选择',
    byqxStr: '请选择',
    yrdwxzStr: '请选择',
    gzzwlbmcStr: '请选择',
    dwszdmcStr: '请选择',
    dwlbmcStr: '请选择',
    sfzydkStr: '请选择',
    bdzqflbmcStr: '请选择',
    bdzszdmcStr: '请选择',
    bdkssjStr: '请选择',
    bdjssjStr: '请选择',
    sfdydwbdzStr: '请选择',
    form: {

    },
    sydList: [],
    sydList2: [],
    showSyd2: true,
    showSyd: true
  },
  getSydItem(e) {
    var that = this
    that.data.form.dwszdmc = e.target.dataset.val
    that.setData({
      form: that.data.form
    })
  },
  getSydItem2(e) {
    var that = this
    that.data.form.bdzqwszdmc = e.target.dataset.val
    that.setData({
      form: that.data.form
    })
  },
  getSyd(e) {
    console.log(e)
    if (e.detail.length > 2) {
      getSmallRoutine(this, e.detail)
    }
  },
  getSyd2(e) {
    console.log(e)
    if (e.detail.length > 2) {
      getSmallRoutine2(this, e.detail)
    }
  },
  close1() {
    this.setData({
      showSyd: true
    })
  },
  close2() {
    this.setData({
      showSyd2: true
    })
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
    params.owid = this.data.owid
    common.ajax('zustcommon/bckjBizJyscheme/saveOneJyschemeXcx', params, function (res) {
      if (res.data.backCode == 0) {
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: "保存成功",
          success(res) {
            if (res.confirm) {
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
      xm: {
        required: true,
      },
      xxmc: {
        required: true,
      },
      byqx: {
        required: true
      },
      bdzqwszdmc: {
        required: true
      },
      bdkssj: {
        required: true
      },
      bdjssj: {
        required: true
      }
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      xm: {
        required: '请填写姓名'
      },
      xxmc: {
        required: '请填写学校名称',
      },
      byqx: {
        required: '请选择毕业去向',
      },
      bdzqwszdmc: {
        required: '请选择报到证签往单位所在地'
      },
      bdkssj: {
        required: '请选择报到开始时间'
      },
      bdjssj: {
        required: '请选择报到结束时间'
      }
      
    }
    this.WxValidate = new WxValidate(rules, messages)
  },
  onConfirm(e) {
    if (e.target.dataset.type == 1) {
      var date = util.formatTime1(new Date(e.detail))
      this.setData({
        csrqStr: date,
        'form.csrq': date
      })
      this.toggle('csrq', false);
    } else if (e.target.dataset.type == 2) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        xbStr: dicVal2,
        'form.xb': dicVal1
      })
      this.toggle('xb', false);
    } else if (e.target.dataset.type == 3) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        mzStr: dicVal2,
        'form.mz': dicVal1
      })
      this.toggle('mz', false);
    } else if (e.target.dataset.type == 4) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        zzmmStr: dicVal2,
        'form.zzmm': dicVal1
      })
      this.toggle('zzmm', false);
    } else if (e.target.dataset.type == 5) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        sydStr: dicVal2,
        'form.syd': dicVal1
      })
      this.toggle('syd', false);
    } else if (e.target.dataset.type == 6) {
      var date = util.formatTime1(new Date(e.detail))
      this.setData({
        rxnfStr: date,
        'form.rxnf': date
      })
      this.toggle('rxnf', false);
    } else if (e.target.dataset.type == 7) {
      this.setData({
        bynfStr: e.detail.value,
        'form.bynf': e.detail.value
      })
      this.toggle('bynf', false);
    } else if (e.target.dataset.type == 8) {
      var date = util.formatTime1(new Date(e.detail))
      this.setData({
        byrqStr: date,
        'form.byrq': date
      })
      this.toggle('byrq', false);
    } else if (e.target.dataset.type == 9) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        cxsyStr: dicVal2,
        'form.cxsy': dicVal1
      })
      this.toggle('cxsy', false);
    } else if (e.target.dataset.type == 10) {
      this.setData({
        xzStr: e.detail.value,
        'form.xz': e.detail.value
      })
      this.toggle('xz', false);
    } else if (e.target.dataset.type == 15) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        sfdlxyStr: dicVal2,
        'form.sfdlxy': dicVal1
      })
      this.toggle('sfdlxy', false);
    } else if (e.target.dataset.type == 16) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        sfzzStr: dicVal2,
        'form.sfzz': dicVal1
      })
      this.toggle('sfzz', false);
    } else if (e.target.dataset.type == 17) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        sfsfStr: dicVal2,
        'form.sfsf': dicVal1
      })
      this.toggle('sfsf', false);
    } else if (e.target.dataset.type == 18) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        byqxStr: dicVal2,
        'form.byqx': dicVal1
      })
      this.toggle('byqx', false);
    } else if (e.target.dataset.type == 19) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        yrdwxzStr: dicVal2,
        'form.yrdwxzmc': dicVal1
      })
      this.toggle('yrdwxz', false);
    } else if (e.target.dataset.type == 20) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        gzzwlbmcStr: dicVal2,
        'form.gzzwlbmc': dicVal1
      })
      this.toggle('gzzwlbmc', false);
    } else if (e.target.dataset.type == 21) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        dwszdmcStr: dicVal2,
        'form.dwszdmc': dicVal1
      })
      this.toggle('dwszdmc', false);
    } else if (e.target.dataset.type == 22) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        dwlbmcStr: dicVal2,
        'form.dwhylbmc': dicVal1
      })
      this.toggle('dwlbmc', false);
    } else if (e.target.dataset.type == 23) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
       sfzydkStr: dicVal2,
        'form.sfzydk': dicVal1
      })
      this.toggle('sfzydk', false);
    } else if (e.target.dataset.type == 24) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        bdzqflbmcStr: dicVal2,
        'form.bdzqflbmc': dicVal1
      })
      this.toggle('bdzqflbmc', false);
    } else if (e.target.dataset.type == 25) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        bdzszdmcStr: dicVal2,
        'form.bdzqwdwmc': dicVal1
      })
      this.toggle('bdzszdmc', false);
    } else if(e.target.dataset.type == 26) {
      var date = util.formatTime1(new Date(e.detail))
      this.setData({
        bdkssjStr: date,
        'form.bdkssj': date
      })
      this.toggle('bdkssj', false);
    } else if (e.target.dataset.type == 27) {
      var date = util.formatTime1(new Date(e.detail))
      this.setData({
        bdjssjStr: date,
        'form.bdjssj': date
      })
      this.toggle('bdjssj', false);
    } else if (e.target.dataset.type == 28) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        sfdydwbdzStr: dicVal2,
        'form.sfdydwbdz': dicVal1
      })
      this.toggle('sfdydwbdz', false);
    }

    console.log(this.data.form)
  },
  onCancel(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('csrq', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('xb', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('mz', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('zzmm', false);
    } else if (e.target.dataset.type == 5) {
      this.toggle('syd', false);
    } else if (e.target.dataset.type == 6) {
      this.toggle('rxnf', false);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bynf', false);
    } else if (e.target.dataset.type == 8) {
      this.toggle('byrq', false);
    } else if (e.target.dataset.type == 9) {
      this.toggle('cxsy', false);
    } else if (e.target.dataset.type == 10) {
      this.toggle('xz', false);
    } else if (e.target.dataset.type == 15) {
      this.toggle('sfdlxy', false);
    } else if (e.target.dataset.type == 16) {
      this.toggle('sfzz', false);
    } else if (e.target.dataset.type == 17) {
      this.toggle('sfsf', false);
    } else if (e.target.dataset.type == 18) {
      this.toggle('byqx', false);
    } else if (e.target.dataset.type == 19) {
      this.toggle('yrdwxz', false);
    } else if (e.target.dataset.type == 20) {
      this.toggle('gzzwlbmc', false);
    } else if (e.target.dataset.type == 21) {
      this.toggle('dwszdmc', false);
    } else if (e.target.dataset.type == 22) {
      this.toggle('dwlbmc', false);
    } else if (e.target.dataset.type == 23) {
      this.toggle('sfzydk', false);
    } else if (e.target.dataset.type == 24) {
      this.toggle('bdzqflbmc', false);
    } else if (e.target.dataset.type == 25) {
      this.toggle('bdzszdmc', false);
    } else if (e.target.dataset.type == 26) {
      this.toggle('bdkssj', false);
    } else if (e.target.dataset.type == 27) {
      this.toggle('bdjssj', false);
    } else if (e.target.dataset.type == 28) {
      this.toggle('sfdydwbdz', false);
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
    this.initValidate()
    getOne(this)

    var curYear = new Date().getFullYear()
    var bynfColumns = []
    for (var i = 0; i < 5; i++) {
      bynfColumns.push(curYear + i)
    }
    this.setData({
      bynfColumns: bynfColumns
    })
  },
  showBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('csrq', true);
    } else if (e.target.dataset.type == 2) {
      this.toggle('xb', true);
    } else if (e.target.dataset.type == 3) {
      this.toggle('mz', true);
    } else if (e.target.dataset.type == 4) {
      this.toggle('zzmm', true);
    } else if (e.target.dataset.type == 5) {
      this.toggle('syd', true);
    } else if (e.target.dataset.type == 6) {
      this.toggle('rxnf', true);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bynf', true);
    } else if (e.target.dataset.type == 8) {
      this.toggle('byrq', true);
    } else if (e.target.dataset.type == 9) {
      this.toggle('cxsy', true);
    } else if (e.target.dataset.type == 10) {
      this.toggle('xz', true);
    } else if (e.target.dataset.type == 15) {
      this.toggle('sfdlxy', true);
    } else if (e.target.dataset.type == 16) {
      this.toggle('sfzz', true);
    } else if (e.target.dataset.type == 17) {
      this.toggle('sfsf', true);
    } else if (e.target.dataset.type == 18) {
      this.toggle('byqx', true);
    } else if (e.target.dataset.type == 19) {
      this.toggle('yrdwxz', true);
    } else if (e.target.dataset.type == 20) {
      this.toggle('gzzwlbmc', true);
    } else if (e.target.dataset.type == 21) {
      this.toggle('dwszdmc', true);
    } else if (e.target.dataset.type == 22) {
      this.toggle('dwlbmc', true);
    } else if (e.target.dataset.type == 23) {
      this.toggle('sfzydk', true);
    } else if (e.target.dataset.type == 24) {
      this.toggle('bdzqflbmc', true);
    } else if (e.target.dataset.type == 25) {
      this.toggle('bdzszdmc', true);
    } else if (e.target.dataset.type == 26) {
      this.toggle('bdkssj', true);
    } else if (e.target.dataset.type == 27) {
      this.toggle('bdjssj', true);
    } else if (e.target.dataset.type == 28) {
      this.toggle('sfdydwbdz', true);
    }
  }, 

  hideBottom(e) {
    if (e.target.dataset.type == 1) {
      this.toggle('csrq', false);
    } else if (e.target.dataset.type == 2) {
      this.toggle('xb', false);
    } else if (e.target.dataset.type == 3) {
      this.toggle('mz', false);
    } else if (e.target.dataset.type == 4) {
      this.toggle('zzmm', false);
    } else if (e.target.dataset.type == 5) {
      this.toggle('syd', false);
    } else if (e.target.dataset.type == 6) {
      this.toggle('rxnf', false);
    } else if (e.target.dataset.type == 7) {
      this.toggle('bynf', false);
    } else if (e.target.dataset.type == 8) {
      this.toggle('byrq', false);
    } else if (e.target.dataset.type == 9) {
      this.toggle('cxsy', false);
    } else if (e.target.dataset.type == 10) {
      this.toggle('xz', false);
    } else if (e.target.dataset.type == 15) {
      this.toggle('sfdlxy', false);
    } else if (e.target.dataset.type == 16) {
      this.toggle('sfzz', false);
    } else if (e.target.dataset.type == 17) {
      this.toggle('sfsf', false);
    } else if (e.target.dataset.type == 18) {
      this.toggle('byqx', false);
    } else if (e.target.dataset.type == 19) {
      this.toggle('yrdwxz', false);
    } else if (e.target.dataset.type == 20) {
      this.toggle('gzzwlbmc', false);
    } else if (e.target.dataset.type == 21) {
      this.toggle('dwszdmc', false);
    } else if (e.target.dataset.type == 22) {
      this.toggle('dwlbmc', false);
    } else if (e.target.dataset.type == 23) {
      this.toggle('sfzydk', false);
    } else if (e.target.dataset.type == 24) {
      this.toggle('bdzqflbmc', false);
    } else if (e.target.dataset.type == 25) {
      this.toggle('bdzszdmc', false);
    } else if (e.target.dataset.type == 26) {
      this.toggle('bdkssj', false);
    } else if (e.target.dataset.type == 27) {
      this.toggle('bdjssj', false);
    } else if (e.target.dataset.type == 28) {
      this.toggle('sfdydwbdz', false);
    }
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

  }
})

var getOne = function (that) {
  var data = { "owid": wx.getStorageSync('yhOwid') };
  common.ajax('zustcommon/bckjBizJyscheme/getOneJyschemeXcx', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean) {
        
        if (!data.bean.xm) {
          data.bean.xm = wx.getStorageSync('stuInfo').xm || ''
        }
        if (!data.bean.xsxh) {
          data.bean.xsxh = wx.getStorageSync('stuInfo').xsxh || ''
        }
        that.setData({
          form: data.bean,
          owid: data.bean.owid
        })

        if (data.bean.xb) {
          that.setData({
            xbStr: util.getVal(data.bean.xb, that.data.xbColumns),
          })
        }
        if (data.bean.sfdlxy == 2) {
          that.setData({
            sfdlxyStr: '否'
          })
        } else if (data.bean.sfdlxy == 1) {
          that.setData({
            sfdlxyStr: '是'
          })
        }

        if (data.bean.sfzz == 2) {
          that.setData({
            sfzzStr: '否'
          })
        } else if (data.bean.sfzz == 1) {
          that.setData({
            sfzzStr: '是'
          })
        }

        if (data.bean.sfsf == 2) {
          that.setData({
            sfsfStr: '否'
          })
        } else if (data.bean.sfsf == 1) {
          that.setData({
            sfsfStr: '是'
          })
        }

        if (data.bean.sfzydk == 2) {
          that.setData({
            sfzydkStr: '否'
          })
        } else if (data.bean.sfzydk == 1) {
          that.setData({
            sfzydkStr: '是'
          })
        }

        if (data.bean.sfdydwbdz == 2) {
          that.setData({
            sfdydwbdzStr: '否'
          })
        } else if (data.bean.sfdydwbdz == 1) {
          that.setData({
            sfdydwbdzStr: '是'
          })
        }

        if (data.bean.byqx) {
          getByType2(that, data.bean.byqx)//毕业去向
        } else {
          getByType2(that)//毕业去向
        }
        if (data.bean.yrdwxzmc) {
          getByType3(that, data.bean.yrdwxzmc)
        } else {
          getByType3(that)
        }
        if (data.bean.gzzwlbmc) {
          getByType4(that, data.bean.gzzwlbmc)
        } else {
          getByType4(that)
        }
        if (data.bean.dwhylbmc) {
          getByType5(that, data.bean.dwhylbmc)
        } else {
          getByType5(that)
        }
        if (data.bean.bdzqflbmc) {
          getByType6(that, data.bean.bdzqflbmc)
        } else {
          getByType6(that)
        }
      }else{

        that.data.form.xm = wx.getStorageSync('stuInfo').xm || ''
        that.data.form.xsxh = wx.getStorageSync('stuInfo').xsxh || ''

        that.setData({
          form: that.data.form
        })

        getByType2(that)//毕业去向
        getByType3(that)
        getByType4(that)
        getByType5(that)
        getByType6(that)
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

var getSmallRoutine = function (that, val) {
  var data = {
    "dicType": "50009",
    "text": val
  };
  common.ajax('zustcommon/common/getSmallRoutine', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        that.setData({
          sydList: data.bean,
          showSyd: false
        })
      } else {
        that.setData({
          showSyd: true
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

var getSmallRoutine2 = function (that, val) {
  var data = {
    "dicType": "50009",
    "text": val
  };
  common.ajax('zustcommon/common/getSmallRoutine', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        that.setData({
          sydList2: data.bean,
          showSyd2: false
        })
      } else {
        that.setData({
          showSyd2: true
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

var getByType1 = function (that, mz) {
  var data = { "dicType": "50009" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.mzColumns.push(obj)
        }

        that.setData({
          mzColumns: that.data.mzColumns          
        })
        if (mz) {
          that.setData({
            mzStr: util.getVal(mz, that.data.mzColumns)
          })
        }
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

var getByType2 = function (that, byqx) {
  var data = { "dicType": "50001" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.byqxColumns.push(obj)
        }
        that.setData({
          byqxColumns: that.data.byqxColumns,
          // byqxStr: util.getVal(byqx, that.data.byqxColumns)
        })
        if (byqx){
          that.setData({
            byqxStr: util.getVal(byqx, that.data.byqxColumns)
          })
        }
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

var getByType3 = function (that, yrdwxz) {
  var data = { "dicType": "50002" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.yrdwxzColumns.push(obj)
        }
        that.setData({
          yrdwxzColumns: that.data.yrdwxzColumns
        })
        if (yrdwxz) {
          that.setData({
            yrdwxzStr: util.getVal(yrdwxz, that.data.yrdwxzColumns)
          })
        }
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

var getByType4 = function (that, gzzwlbmc) {
  var data = { "dicType": "50004" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.gzzwlbmcColumns.push(obj)
        }
        that.setData({
          gzzwlbmcColumns: that.data.gzzwlbmcColumns
        })
        if (gzzwlbmc) {
          that.setData({
            gzzwlbmcStr: util.getVal(gzzwlbmc, that.data.gzzwlbmcColumns)
          })
        }
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

var getByType5 = function (that, dwlbmc) {
  var data = { "dicType": "50003" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.dwlbmcColumns.push(obj)
        }
        that.setData({
          dwlbmcColumns: that.data.dwlbmcColumns
        })
        if (dwlbmc) {
          that.setData({
            dwlbmcStr: util.getVal(dwlbmc, that.data.dwlbmcColumns)
          })
        }
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

var getByType6 = function (that, bdzqflbmc) {
  var data = { "dicType": "50007" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.bdzqflbmcColumns.push(obj)
        }
        that.setData({
          bdzqflbmcColumns: that.data.bdzqflbmcColumns         
        })
        if (bdzqflbmc) {
          that.setData({
            bdzqflbmcStr: util.getVal(bdzqflbmc, that.data.bdzqflbmcColumns)
          })
        }
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

var getByType33 = function (that) {
  var data = { "dicType": "20002" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      // if (data.bean && data.bean.length > 0) {
      //   for (var i in data.bean) {
      //     var obj = {}
      //     obj.dicVal1 = data.bean[i].dicVal1
      //     obj.dicVal2 = data.bean[i].dicVal2
      //     that.data.gsgmColumns.push(obj)
      //   }
      //   that.setData({
      //     gsgmColumns: that.data.gsgmColumns
      //   })
      // }
    } else {
      wx.showToast({
        title: res.data.errorMess,
        icon: 'none',
        duration: 2000
      })
    }
  });
}