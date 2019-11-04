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
   */
  data: {
    hidden1: false,
    minDate: new Date("1990-01-01").getTime(),
    minDate1: new Date("1990-01-01").getTime(),
    minDate2: new Date("1990-01-01").getTime(),
    imgPath: imgPath,
    xbColumns: [{ dicVal1: '1', dicVal2: '男' }, { dicVal1: '2', dicVal2: '女' }],
    mzColumns: [],
    sydColumns: [],
    zzmmColumns: [],
    bynfColumns: [],
    cxsyColumns: [{ dicVal1: '农村', dicVal2: '农村' }, { dicVal1: '城镇', dicVal2: '城镇' }],
    xzColumns: ["1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5"],
    pyfsColumns: ["在职", "委培", "定向", "自筹", "非定向"],
    knslbColumns: ["家庭困难", "家庭困难和残疾", "就业困难", "就业困难、家庭困难和残疾", "就业困难和家庭困难", "就业困难和残疾", "残疾", "非困难生" ],
    sfslbColumns: ["免费师范生", "普通师范生", "非师范生"],
    xlccColumns: ["博士生毕业", "博士生结业", "硕士生毕业", "硕士生结业", "本科生毕业", "本科生结业", "专科生毕业", "专科生毕业"],
    sfrxColumns: [{ dicVal1: '0', dicVal2: '否' }, { dicVal1: '1', dicVal2: '是' }],
    hkrxColumns: [{ dicVal1: '0', dicVal2: '否' }, { dicVal1: '1', dicVal2: '是' }],
    show: {
      bottom: false,
      syd: false,
      csrq: false,
      xb: false,
      mz: false,
      zzmm: false,
      rxnf: false,
      bynf: false,
      byrq: false,
      cxsy: false,
      xz: false,
      pyfs: false,
      knslb: false,
      sfslb: false,
      xlcc: false,
      sfrx: false,
      hkrx: false,
    },
    csrqStr: '请选择',
    sydStr: '请选择',
    xbStr: '请选择',
    mzStr: '请选择',
    zzmmStr: '请选择',
    rxnfStr: '请选择',
    bynfStr: '请选择',
    byrqStr: '请选择',
    cxsyStr: '请选择',
    xzStr: '请选择',
    pyfsStr: '请选择',
    knslbStr: '请选择',
    sfslbStr: '请选择',
    xlccStr: '请选择',
    sfrxStr: '请选择',
    hkrxStr: '请选择',
    form: {
      xm: '',
      xsxh: '',
      csrq: '',
      xb: '',
      mz: '',
      zzmm: '',
      sfz: '',
      jtdz: '',
      syd: '',
      rxnf: '',
      bynf: '',
      byrq: '',
      cxsy: '',
      ksh: '',
      xz: '',
      xxmc: '',
      xsxy: '',
      xszy: '',
      xsbj: '',
      pyfs: '',
      wpdw: '',
      knslb: '',
      sfslb: '',
      sjh: '',
      qqhm: '',
      jtdh: '',
      jtyb: '',
      xlcc: '',
      yx: '',
      zyfx: '',
      xqda: '',
      sfrx: '',
      hkrx: '',
      hkpcs: ''
    },
    sydList:[],
    showSyd: true,
    owid:''
  },
  getSydItem(e){
    var that = this
    that.data.form.syd = e.target.dataset.val
    that.setData({
      showSyd: true,
      'form.syd': e.target.dataset.val
    })
  },
  getSyd(e){
    console.log(e)
    if(e.detail.length>2){
      getSmallRoutine(this, e.detail)
    }
    this.setData({
      'form.syd': e.detail
    })
  },
  close1(){
    this.setData({
      showSyd: true
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
    params.csrq = params.csrq ? params.csrq.substring(0, 10) : ''
    params.rxnf = params.rxnf ? params.rxnf.substring(0, 10) : ''
    params.byrq = params.byrq ? params.byrq.substring(0, 10) : ''
   
    common.ajax('zustcommon/bckjBizSyb/savaOneXcx', params, function (res) {
      if (res.data.backCode == 0) {
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: "修改成功",
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
      xsxh: {
        required: true
      },
      csrq: {
        required: true
      },
      xb: {
        required: true
      },
      mz: {
        required: true
      },
      zzmm: {
        required: true
      },
      jtdz: {
        required: true,
      },
      syd: {
        required: true
      },
      rxnf: {
        required: true
      },
      bynf: {
        required: true
      },
      byrq: {
        required: true
      },
      cxsy: {
        required: true
      },
      ksh: {
        required: true
      },
      xz: {
        required: true
      },
      xxmc: {
        required: true
      },
      xsxy: {
        required: true
      },
      xszy: {
        required: true,
      },
      xsbj: {
        required: true
      },
      pyfs: {
        required: true
      },
      wpdw: {
        required: true
      },
      knslb: {
        required: true
      },
      sfslb: {
        required: true
      },
      sjh: {
        required: true,
        tel: true,
      },
      qqhm: {
        required: true
      },
      jtdh: {
        required: true
      },
      jtyb: {
        required: true
      },
      xlcc: {
        required: true
      },
      yx: {
        email: true,
      }

    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      xm: {
        required: '请填写姓名'
      },
      xsxh: {
        required: '请填写学号'
      },
      csrq: {
        required: '请选择出生日期'
      },
      xb: {
        required: '请选择性别'
      },
      mz: {
        required: '请选择民族'
      },
      zzmm: {
        required: '请选择政治面貌'
      },
      jtdz: {
        required: '请填写家庭地址',
      },
      syd: {
        required: '请填写生源地'
      },
      rxnf: {
        required: '请选择入学日期'
      },
      bynf: {
        required: '请选择毕业年份'
      },
      byrq: {
        required: '请选择毕业日期'
      },
      cxsy: {
        required: '请选择城乡生源'
      },
      ksh: {
        required: '请填写考生号'
      },
      xz: {
        required: '请选择学制'
      },
      xxmc: {
        required: '请填写所属学校'
      },
      xsxy: {
        required: '请填写所属学院'
      },
      xszy: {
        required: '请填写学校专业',
      },
      xsbj: {
        required: '请填写学生班级'
      },
      pyfs: {
        required: '请选择培养方式'
      },
      wpdw: {
        required: '请填写委培单位'
      },
      knslb: {
        required: '请选择困难生类别'
      },
      sfslb: {
        required: '请选择困难生类别'
      },
      sjh: {
        required: '请填写手机号码',
        tel: '请填写正确手机号码',
      },
      qqhm: {
        required: '请填写qq号码'
      },
      jtdh: {
        required: '请填写家庭电话'
      },
      jtyb: {
        required: '请填写邮政编码'
      },
      xlcc: {
        required: '请选择学历层次'
      },
      yx: {
        email: '请填写正确邮箱',
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
    } else if (e.target.dataset.type == 11) {
      this.setData({
        pyfsStr: e.detail.value,
        'form.pyfs': e.detail.value
      })
      this.toggle('pyfs', false);
    } else if (e.target.dataset.type == 12) {
      this.setData({
        knslbStr: e.detail.value,
        'form.knslb': e.detail.value
      })
      this.toggle('knslb', false);
    } else if (e.target.dataset.type == 13) {
      this.setData({
        sfslbStr: e.detail.value,
        'form.sfslb': e.detail.value
      })
      this.toggle('sfslb', false);
    } else if (e.target.dataset.type == 14) {
      this.setData({
        xlccStr: e.detail.value,
        'form.xlcc': e.detail.value
      })
      this.toggle('xlcc', false);
    } else if (e.target.dataset.type == 15) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        sfrxStr: dicVal2,
        'form.sfrx': dicVal1
      })
      this.toggle('sfrx', false);
    } else if (e.target.dataset.type == 16) {
      const { dicVal1, dicVal2 } = e.detail.value;
      this.setData({
        hkrxStr: dicVal2,
        'form.hkrx': dicVal1
      })
      this.toggle('hkrx', false);
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
    } else if (e.target.dataset.type == 11) {
      this.toggle('pyfs', false);
    } else if (e.target.dataset.type == 12) {
      this.toggle('knslb', false);
    } else if (e.target.dataset.type == 13) {
      this.toggle('sfslb', false);
    } else if (e.target.dataset.type == 14) {
      this.toggle('xlcc', false);
    } else if (e.target.dataset.type == 15) {
      this.toggle('sfrx', false);
    } else if (e.target.dataset.type == 16) {
      this.toggle('hkrx', false);
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
    // getByType2(this)
    // getByType1(this)//民族
    // getByType2(this)//政治面貌



    var curYear = new Date().getFullYear()
    var bynfColumns=[]
    for(var i=0;i<5;i++){
      bynfColumns.push(curYear+i)
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
    } else if (e.target.dataset.type == 11) {
      this.toggle('pyfs', true);
    } else if (e.target.dataset.type == 12) {
      this.toggle('knslb', true);
    } else if (e.target.dataset.type == 13) {
      this.toggle('sfslb', true);
    } else if (e.target.dataset.type == 14) {
      this.toggle('xlcc', true);
    } else if (e.target.dataset.type == 15) {
      this.toggle('sfrx', true);
    } else if (e.target.dataset.type == 16) {
      this.toggle('hkrx', true);
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
    } else if (e.target.dataset.type == 11) {
      this.toggle('pyfs', false);
    } else if (e.target.dataset.type == 12) {
      this.toggle('knslb', false);
    } else if (e.target.dataset.type == 13) {
      this.toggle('sfslb', false);
    } else if (e.target.dataset.type == 14) {
      this.toggle('xlcc', false);
    } else if (e.target.dataset.type == 15) {
      this.toggle('sfrx', false);
    } else if (e.target.dataset.type == 16) {
      this.toggle('hkrx', false);
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
  common.ajax('zustcommon/bckjBizSyb/getOneXcx', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if(data.bean){
        data.bean.csrq = data.bean.csrq ? data.bean.csrq.substring(0, 10) : ''
        data.bean.rxnf = data.bean.rxnf ? data.bean.rxnf.substring(0, 10) : ''
        data.bean.byrq = data.bean.byrq ? data.bean.byrq.substring(0, 10) : ''
        that.setData({
          form: data.bean,
          owid: data.bean.owid,
          csrqStr: data.bean.csrq ? data.bean.csrq.substring(0, 10) : '请选择',
          syd: data.bean.syd || '',
          rxnfStr: data.bean.rxnf ? data.bean.rxnf.substring(0, 10) : '请选择',
          bynfStr: data.bean.bynf || '请选择',
          byrqStr: data.bean.byrq ? data.bean.byrq.substring(0, 10) : '请选择',
          cxsyStr: data.bean.cxsy || '请选择',
          xzStr: data.bean.xz || '请选择',
          pyfsStr: data.bean.pyfs || '请选择',
          knslbStr: data.bean.knslb || '请选择',
          sfslbStr: data.bean.sfslb || '请选择',
          xlccStr: data.bean.xlcc || '请选择',
        })
        if (data.bean.xb) {
          that.setData({
            xbStr: util.getVal(data.bean.xb, that.data.xbColumns),
          })
        }
        if (data.bean.sfrx == 0) {
          that.setData({
            sfrxStr: '否'
          })
        } else if (data.bean.sfrx == 1) {
          that.setData({
            sfrxStr: '是'
          })
        }
        if (data.bean.hkrx == 0) {
          that.setData({
            hkrxStr: '否'
          })
        } else if (data.bean.hkrx == 1) {
          that.setData({
            hkrxStr: '是'
          })
        }
        if (data.bean.mz) {
          getByType1(that, data.bean.mz)//民族
        } else {
          getByType1(that)//民族
        }
        if (data.bean.zzmm) {
          getByType2(that, data.bean.zzmm)//政治面貌
        } else {
          getByType2(that)//政治面貌
        }
      }else{
        getByType1(that)//民族
        getByType2(that)//政治面貌
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
    "dicType": "50009" ,
    "text":val  
  };
  common.ajax('zustcommon/common/getSmallRoutine', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        that.setData({
          sydList: data.bean,
          showSyd: false
        })
      }else{
        that.setData({
          showSyd:true
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

var getByType1 = function (that,mz) {
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
        if(mz){
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

var getByType2 = function (that, zzmm) {
  var data = { "dicType": "50008" };
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].dicVal1
          obj.dicVal2 = data.bean[i].dicVal2
          that.data.zzmmColumns.push(obj)
        }
        that.setData({
          zzmmColumns: that.data.zzmmColumns
        })
        if (zzmm) {
          that.setData({
            zzmmStr: util.getVal(zzmm, that.data.zzmmColumns)
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

var getByType3 = function (that,syd) {
  var data = { "dicType": "50005" };
  common.ajax('zustcommon/common/getByTypeSort', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data;
      if (data.bean && data.bean.length > 0) {
        for (var i in data.bean) {
          var obj = {}
          obj.dicVal1 = data.bean[i].DIC_VAL1
          obj.dicVal2 = data.bean[i].DIC_VAL2
          that.data.sydColumns.push(obj)
        }
        that.setData({
          sydColumns: that.data.sydColumns
        })
        if (syd) {
          console.log(util.getVal(syd, that.data.sydColumns))
          that.setData({
            sydStr: util.getVal(syd, that.data.sydColumns)
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