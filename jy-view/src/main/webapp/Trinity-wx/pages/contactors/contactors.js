// pages/contactors/contactors.js
var common = require('../../libs/common/common.js');
import WxValidate from '../../utils/WxValidate'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    teacher: {
      sex: "1",
    },
    detailList: [],//联系人信息
    errorList: '', //错误信息重组
    eduCategory: [],//文化程度
    showPop: false,
    contactType: '',//判断当前点击是谁
    defaultIndex: 0,//弹出框默认显示第几个

    fatherWhCode: '', //文化程度的码 传给后台
    motherWhCode: '',
    teacherWhCode: '',
  },
  //点击想下一步保存联系人
  contactForm: function (e) {
    let that = this;
    let curId = e.detail.target.id;
    const params = e.detail.value;
    //传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      common.errorHash(this.WxValidate.errorList, this);
      return false
    } else { this.setData({ errorList: {} }); }
    var dataArr = [];
    console.log(params);
    dataArr[0] = {//父亲
      "cylb": params.faCylb
      , "xm": params.faName
      , "xb": params.faSex
      , "whcd": that.data.fatherWhCode
      , "gzdw": params.faCom
      , "gzzw": params.faJob
      , "lxsj": params.faTel
      , "dwdh": params.faComTel
    }
    dataArr[1] = {//高中老师
      "cylb": 2
      , "xm": params.teName
      , "xb": params.teSex
      , "whcd": that.data.teacherWhCode
      , "gzdw": params.teCom
      , "gzzw": params.teJob
      , "lxsj": params.teTel
      , "dwdh": params.teComTel
    }
    var data = {
      "dataList": dataArr,
      "yhRefOwid": wx.getStorageSync('yhRefOwid')
    }
    common.ajax('zustswyt/bckjBizJtcyxx/finish', data, function (res) {
      if (res.data.backCode == 0) {
        switch (curId) {
          case "pre":
            wx.redirectTo({
              url: '../basicInfo/basicInfo',
            })
            break;
          case "next":
            wx.redirectTo({
              url: '../examGrade/examGrade',
            })
            break;
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initValidate();
    getEdu(this);//文化程度
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  initValidate() {
    // 验证字段的规则
    const rules = {
      faName: {
        required: true,
      },
      faTel: {
        required: true,
        tel: true,
      },
      faCylb: {
        required: true,
      },
      faSex: {
        required: true,
      },
      teName: {
        required: true,
      },
      teTel: {
        required: true,
        tel: true,
      },
      teSex: {
        required: true,
      }
    }
    this.WxValidate = new WxValidate(rules, '')
  },
  //单选框 选择性别
  sexSelect(event) {
    switch (event.currentTarget.dataset.id) {
      case "1":
        this.setData({
          ['father.xb']: event.detail,
        });
        break;
      case "2":
        this.setData({
          ['mother.xb']: event.detail
        });
        break;
      case "3":
        this.setData({
          ['teacher.xb']: event.detail
        });
        break;
    }
  },
  //单选框 监护人关系
  contactorSelect(event) {
    this.setData({
      ['father.cylb']: event.detail,
    });
  },
  //取消pop
  cancelPop() {
    this.setData({
      showPop: false
    });
  },
  //显示文化程度弹出
  showEduList(e) {
    let that = this;
    let type = e.target.dataset.type;
    let code = e.target.dataset.code;
    let eduArr = that.data.eduCategory;
    let index = (code != '') ? code : 0;
    //打开默认当前选中项目
    for (let i in eduArr) {
      if (eduArr[i].value == code) {
        index = i
      }
    }
    console.log(index);
    that.setData({
      showPop: true,
      contactType: type,
      defaultIndex: index
    });
  },
  //确认选择文化程度
  confirmEdu(event) {
    var that = this;
    const { value, index } = event.detail;
    //console.log(value);
    switch (that.data.contactType) {
      case "1": //父亲
        that.setData({
          ['father.whcd']: value.text,
          fatherWhCode: value.value
        });
        break;
      case "2": //母亲
        that.setData({
          ['mother.whcd']: value.text,
          motherWhCode: value.value
        });
        break;
      case "3": //老师
        that.setData({
          ['teacher.whcd']: value.text,
          teacherWhCode: value.value
        });
        break;
    }
    this.setData({
      showPop: false,
    });
  },

})

//获取联系人信息
function getContactor(that) {
  var data = {
    "yhRefOwid": wx.getStorageSync('yhRefOwid')
  }
  common.ajax('zustswyt/bckjBizJtcyxx/getInfo', data, function (res) {
    if (res.data.backCode == 0) {
      var list = res.data.bean;
      var whArr = that.data.eduCategory;
      if (list && list.length > 0) {
        for (var i = 0; i < list.length; i++) {
          
          if (list[i].cylb == 0 || list[i].cylb == 1) {
            // 0是父亲/母亲 其他监护人
            if (list[i].xb) { list[i].xb = list[i].xb.toString(); }

            //文化程度处理
            if (list[i].whcd) {
              let thisWh = list[i].whcd;
              for (let j in whArr) {
                if (list[i].whcd == whArr[j].value) {
                  that.data.fatherWhCode = list[i].whcd;
                  list[i].whcd = whArr[j].text;
                }
              }
            }
            
            //关系处理
            list[i].cylb = (list[i].cylb).toString();
            that.data.father = list[i];
          } else {
            //==2 高中联系人
            if (list[i].xb) { list[i].xb = list[i].xb.toString(); }

            if (list[i].whcd) {
              let thisWh = list[i].whcd;
              for (let j in whArr) {
                if (list[i].whcd == whArr[j].value) {
                  that.data.teacherWhCode = list[i].whcd;
                  list[i].whcd = whArr[j].text;
                }
              }
            }
            that.data.teacher = list[i];
          }
        }
        that.setData({
          father: that.data.father,
          teacher: that.data.teacher,
          fatherWhCode: that.data.fatherWhCode, //文化程度传给接口
          motherWhCode: that.data.motherWhCode,
          teacherWhCode: that.data.teacherWhCode
        });
      }
    } else {
      common.toast(res.data.errorMess, 'none', 2000)
    }
  })
}

//获取文化程度
function getEdu(that) {
  var data = {
    "dicType": '10012',//字典表缴费说明
  }
  common.ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      let data = res.data.bean;
      var cloArr = [];
      for (var i = 0; i < data.length; i++) {
        cloArr.push({ "text": data[i].dicVal2, "value": data[i].dicVal1 })
      }
      that.setData({
        "eduCategory": cloArr,
      });

      //获取联系人 文化程度需要进行匹配
      getContactor(that);

    } else {
      common.toast(res.data.errorMess, 'none', 2000)
    }
  });
}