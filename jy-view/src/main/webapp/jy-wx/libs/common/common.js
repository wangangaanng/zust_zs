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
function convertWKtwo(dt) {
  var weekDay = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
  var myDate = new Date(Date.parse(dt.replace(/-/g, "/")));
  return weekDay[myDate.getDay()];
}
const convertName = function (val, list) {
  if (val.split(",").length > 1) {
    var valArr = val.split(",");
    var nameArr = [];
    for (var j = 0; j < valArr.length; j++) {
      for (var i = 0; i < list.length; i++) {
        if (valArr[j] == list[i].dicVal1) {
          nameArr.push(list[i].dicVal2)
        }
      }
    }
    return nameArr.join();
  } else {
    for (var i = 0; i < list.length; i++) {
      if (val == list[i].dicVal1) {
        return list[i].dicVal2
      }
    }
  }

}
function getDistance(lat1, lng1, lat2, lng2) {
  var radLat1 = lat1 * Math.PI / 180.0;
  var radLat2 = lat2 * Math.PI / 180.0;
  var a = radLat1 - radLat2;
  var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
  var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
    Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
  s = s * 6378.137;// EARTH_RADIUS;
  s = Math.round(s * 10000) / 10000;
  return s;
}

exports.formatTime= formatTime
exports.toast = toast
exports.emptyCheck = emptyCheck
exports.indexOf = indexOf
exports.ajax = ajax
exports.convertStr = convertStr
exports.convertWKtwo = convertWKtwo
exports.convertName = convertName
exports.getDistance = getDistance

