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
      openId: wx.getStorageSync('openId'),
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
        title: '系统错误，请联系管理员',
        icon: 'none',
        duration: 2000
      })
    }
  })
}


//获取验证码
function getCode(mobile, type, that) {//that 小程序中的this 【type 0：注册 1：忘记密码 2：网站注册】
  if (!mobile) {
    toast('请填写手机号', 'none', 2000);
    return;
  }
  if (mobile.length != '11') {
    toast('请填写正确的手机号', 'none', 2000);
    return;
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
      time: currentTime + '秒'
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
exports.getCode = getCode

