// pages/selectExamInfo/selectExamInfo.js
var common = require('../../libs/common/common.js')
const app = getApp()
var url = app.globalData.ApiUrl;
var yhRefOwid = wx.getStorageSync('yhRefOwid');
var num = 0;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showPop: false, //显示选择
    subjectList: [], //显示选项
    selectSubject: [], //显示选项
    files: [], //证书照片
    zx: [], //专项信息
    zxindex: null,
    zxlist: [], //专项信息
    zxtype: [],
    xk: [], //选考信息
    xkindex: [{
      value: null,
      index: null,
    }, {
      value: null,
      index: null,
    }, {
      value: null,
      index: null,
    }],
    xklist: [], //选考信息
    wy: [], //外语
    wyindex: null,
    wylist: [], //外语
    wycj: '', //外语成绩
    jssm: '', //竞赛说明
    qtqk: '', //其他情况
    tcah: '', //特长爱好
    jsfj: [], //附件owid
    res:{},//信息
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;
    this.getByType('10020')
    this.getByType('10024')
    this.getXkkm()
    let t = setInterval(function(){
      if(num==3){
        that.getXkcj()
        clearInterval(t)
      }
    },100)
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  chooseImage() {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        console.log(res)
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        that.setData({
          files: that.data.files.concat(res.tempFilePaths)
        });
        common.uploadFile(res.tempFilePaths, 2, function(res) {
          let data = JSON.parse(res.data)
          if (data.backCode == 0) {
            that.data.jsfj.push(data.bean.owid)
          } else {
            common.toast('上传失败', 'none', 2000);
          }
        });
      }
    })
  },
  previewImage: function(e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.files // 需要预览的图片http链接列表
    })
  },
  xkcj(e) {
    let value = e.detail.value
    let index = e.target.dataset.index
    let xkindex = this.data.xkindex
    xkindex[index].value = value
    this.setData({
      xkindex
    })
  },
  wycj(e) {
    let value = e.detail.value
    this.setData({
      wycj: value
    })
  },
  jssm(e) {
    this.setData({
      jssm: e.detail.value
    })
  },
  qtqk(e) {
    this.setData({
      qtqk: e.detail.value
    })
  },
  tcah(e) {
    this.setData({
      tcah: e.detail.value
    })
  },

  //确定选择
  confirmSubject(e) {
    console.log(e)
    if (this.data.xkeq != null || this.data.xkeq != undefined) {
      let xkindex = this.data.xkindex
      xkindex[this.data.xkeq].index = e.detail.index
      this.setData({
        xkindex,
        showPop: false
      })
    } else {
      let wyindex = e.detail.index
      this.setData({
        wyindex,
        showPop: false
      })
    }
  },
  onChange(event) {
    this.setData({
      zxtype: event.detail
    });
  },
  //隐藏弹出
  cancelPop() {
    this.setData({
      showPop: false
    });
  },
  //点击弹出
  selectSubject(e) {
    console.log(e)
    let type = e.currentTarget.dataset.type;
    let index;
    let subjectList = []
    if (type == "0") {
      index = e.currentTarget.dataset.index;
      subjectList = this.data.xklist
    }
    if (type == "1") {
      subjectList = this.data.wylist
    }
    this.setData({
      showPop: true,
      subjectList,
      xkeq: index
    });
  },
  preStep: function() {
    wx.navigateTo({
      url: '../examGrade/examGrade',
    })
  },
  finish: function() {
    let xkList = [];
    for (let i in this.data.xkindex) {
      let current = this.data.xkindex[i];
      if (current.index == null || current.value == null || current.value == "") {
        common.toast('选考信息未填完整', 'none', 2000)
        return
      } else {
        xkList.push({
          kmbh: this.data.xk[current.index].dicval1,
          kmcj: current.value,
          xssx: parseInt(i) + 1
        })
      }
    }
    if (this.data.wyindex == null) {
      common.toast('外语语种未选择', 'none', 2000)
      return
    }
    if (!(!!this.data.wycj)) {
      common.toast('外语成绩未填完整', 'none', 2000)
      return
    }
    let wyyz = this.data.wy[this.data.wyindex].dicVal1
    let zxlb = '';
    for (let i in this.data.zxtype) {
      if (!!zxlb) {
        zxlb += ',' + this.data.zxtype[i]
      } else {
        zxlb = this.data.zxtype[i]
      }
    }
    let jsfj = '';
    for (let i in this.data.jsfj) {
      if (!!jsfj) {
        jsfj += ',' + this.data.jsfj[i]
      } else {
        jsfj = this.data.jsfj[i]
      }
    }
    let data = {
      xkList: xkList,
      yhRefOwid: yhRefOwid,
      wyyz: wyyz,
      wycj: this.data.wycj,
      zxlb: zxlb,
      jssm: this.data.jssm,
      qtqk: this.data.qtqk,
      tcah: this.data.tcah,
      jsfj: jsfj
    }
    console.log(data)
    common.ajax('zustswyt/bckjBizCjxx/finishXk', data, function(res) {
      if (res.data.backCode == 0) {
        wx.navigateTo({
          url: '../Process/Process',
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  //获取选考列表
  getXkkm: function(e) {
    let that = this;
    let data = {
      dicType: '10022',
      dicVal5: '1'
    }
    common.ajax('zustcommon/common/getXkkm', data, function(res) {
      num++;
      if (res.data.backCode == 0) {
        let xklist = [];
        for (let i in res.data.bean) {
          xklist.push(res.data.bean[i].dicval2)
        }
        that.setData({
          xk: res.data.bean,
          xklist: xklist
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  //获取字典表信息
  getByType: function(e) {
    let that = this;
    let data = {
      dicType: e,
    }
    common.ajax('zustcommon/common/getByType', data, function(res) {
      if (res.data.backCode == 0) {
        num++;
        if (e == '10020') {
          let wylist = [];
          for (let i in res.data.bean) {
            wylist.push(res.data.bean[i].dicVal2)
          }
          that.setData({
            wy: res.data.bean,
            wylist: wylist
          })
        } else if (e == '10024') {
          let zxlist = [];
          for (let i in res.data.bean) {
            zxlist.push(res.data.bean[i].dicVal2)
          }
          that.setData({
            zx: res.data.bean,
            zxlist: zxlist
          })
        }
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  },
  getXkcj() {
    let that = this;
    let data = {
      yhRefOwid: yhRefOwid
    }
    common.ajax('zustswyt/bckjBizCjxx/getXkcj', data, function (res) {
      if (res.data.backCode == 0) {
        let files = [];
        for (let i in res.data.bean.jsfj){
          files.push(app.globalData.imgPath + res.data.bean.jsfj[i].filePath.replace("\\", "/"))
        }
        let wyindex;
        for (let i in that.data.wy){
          if (that.data.wy[i].dicVal1 == res.data.bean.wyyz){
            wyindex=i
          }
        }
        let xkindex = that.data.xkindex;
        for (let i in xkindex){
          xkindex[i].value = res.data.bean.xkList[i].kmcj
          for (let k in that.data.xk){
            if (res.data.bean.xkList[i].kmbh == that.data.xk[k].dicval1){
              xkindex[i].index = k
            }
          }
        }
        console.log(xkindex)
        that.setData({
          res:res.data.bean,
          qtqk: res.data.bean.qtqk,
          tcah: res.data.bean.tcah,
          jssm: res.data.bean.jssm,
          files,
          zxtype: res.data.bean.zxlb.split(','),
          wyindex: wyindex,
          wycj: res.data.bean.wycj,
          xkindex
        })
      } else {
        common.toast(res.data.errorMess, 'none', 2000)
      }
    });
  }
})