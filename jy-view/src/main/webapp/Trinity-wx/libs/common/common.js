const app = getApp()
var lj = app.globalData.lj;
var url = app.globalData.ApiUrl;
var imgPath = app.globalData.imgPath;
var localImgPath = app.globalData.localimgUrl;
var openId = app.globalData.openId;
var unionid = app.globalData.unionid;
function ajax(method, data, successMethod,hideload) {
  if (hideload==1){

  }else{
    wx.showLoading({
      title: '加载中',
    })
  }
  
  wx.request({
    url: url + method,
    data: {
      //openId: wx.getStorageSync('openId'),
      "data": JSON.stringify(data),
      timestamp: new Date().getTime()
    },
    header: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    method: 'POST',
    success: function (res) {
      if (hideload == 1) {

      }else{
        wx.hideLoading();
      }
      
      return typeof successMethod == "function" && successMethod(res)
    },
    fail: function (err) {
      wx.showToast({
        title: '请求失败，请联系管理员',
        icon: 'none',
        duration: 2000
      })
    }
  })
}

function uploadFile(tempFilePaths, type,fun){
  var jsonObj = {
    "file": tempFilePaths[0],
    "type": type,
    "yhRefOwid": wx.getStorageSync('yhRefOwid')
  }
  wx.showLoading({
    title: '上传中',
  })
  wx.uploadFile({
    url: app.globalData.ApiUrl + "zustcommon/common/fileUpload",
    filePath: tempFilePaths[0],
    name: 'file',
    method: 'POST',
    formData: {
      "data": JSON.stringify(jsonObj)
    },
    success: function (res) {
      wx.hideLoading()
      fun(res)
     },
    fail: function (e) {
      wx.showModal({
        title: '提示',
        content: '上传失败',
        showCancel: false
      });
    }
  })
}
// author:2515421994@qq.com,time:2019.10.29++++++++++++++++++++++++++++++++++++++++++++++++++++
//获取验证码
function getCode(mobile, type, that) {//that 小程序中的this 【type 0：注册 1：忘记密码 2：网站注册】
  if (!(/^1[3456789]\d{9}$/.test(mobile))) {
    toast('请填写正确的手机号', 'none', 2000);
    return false;
  } 
  //验证码发送ajax
  var data = {
    "swZh": mobile
    , "type": type //0表示小程序用户注册
    , "unionid": wx.getStorageSync('unionid'),
  }
  ajax('zustcommon/common/sendCode', data, function (res) {
    if (res.data.backCode == 0) {
      countDown(that);//倒计时
    } else {
      toast(res.data.errorMess, 'none', 2000)
    }
  });

}

//倒计时显示
let interval= null;
function countDown(that){
  var currentTime = 60;
  interval = setInterval(function () {
    currentTime--;
    that.setData({
      time: currentTime + '秒',
      disabled:true
    })
    if (currentTime <= 0) {
      clearInterval(interval)
      that.setData({
        time: '重新获取',
        currentTime: 60,
        disabled: false
      })
    }
  }, 1000)
}

//重组errorList
function errorHash(err,that){
  var obj2 = {};
  for (var i = 0; i < err.length; i++) {
    var obj1 = {};
    obj1.msg = err[i].msg;
    obj2[err[i].param] = obj1;
  }
  that.setData({
    errorList: obj2
  });
}

//初始化获取缴费地址
function getPayUrl(that) {
  var data = {
    "dicType": '10025',//字典表缴费说明
  }
  ajax('zustcommon/common/getByType', data, function (res) {
    if (res.data.backCode == 0) {
      that.setData({
        "payUrl": res.data.bean[0].dicVal3,
        "payDetail": res.data.bean[0].dicVal2
      })
    } else {
      toast(res.data.errorMess, 'none', 2000)
    }
  });
}

//报名表所有查询 
function getProcssState(fun) {
  var data = { "applyOwid": wx.getStorageSync('applyOwid') }
  ajax('zustswyt/bckjBizBm/getResult', data, function (res) {
    if (res.data.backCode == 0) {
      fun(res);
    } else {
      toast(res.data.errorMess, 'none', 2000)
    }
  });
}

//获取基本信息 用于个人中心 和process用户名称显示
function getInfoBasic(that) {
  var data = {
    "yhRefOwid": wx.getStorageSync('yhRefOwid')
  }
  ajax('zustswyt/bckjBizJbxx/getInfo', data, function (res) {
    if (res.data.backCode == 0) {
      var data = res.data.bean;
      that.setData({
        'userName': data.xm
      })
    } else {
      toast("获取用户基本信息报错" + res.data.errorMess, 'none', 2000)
    }
  })
}
//author:2515421994@qq.com,time:2019.10.29 end++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//获取数组中字符串的位置
function indexOf(arr, str) {
  // 如果可以的话，调用原生方法
  if (arr && arr.indexOf) {
    return arr.indexOf(str);
  }

  var len = arr.length;
  for (var i = 0; i < len; i++) {
    // 定位该元素位置
    if (arr[i] == str) {
      return i;
    }
  }

  // 数组中不存在该元素
  return -1;
}
// 判断空

function emptyCheck(val) {
  if (val !== null && val !== undefined && val !== '') {
    return true;
  } else
    return false;
}
function toast(title,icon,duration,mask){
  if (!duration) { duration=2000}
  wx.showToast({
    title: title,
    icon: icon,
    duration:duration,
    mask:mask
  })
}
function convertStr(val, val1) {
  if ("" == val || null == val || 'null' == val || 'undefined' == val) {
    return val1;
  } else
    return val;
}
const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

exports.formatTime= formatTime
exports.toast = toast
exports.emptyCheck = emptyCheck
exports.indexOf = indexOf
exports.ajax = ajax
exports.convertStr = convertStr
exports.uploadFile = uploadFile

//author:2515421994@qq.com,time:2019.10.29 获取验证码 重组errorList
exports.getCode = getCode 
exports.errorHash = errorHash
exports.getPayUrl = getPayUrl
exports.getProcssState = getProcssState
exports.getInfoBasic = getInfoBasic
exports.imgPath = imgPath

