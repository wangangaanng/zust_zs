// pages/drawGroup/drawGroup.js
var common = require('../../libs/common/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username:'',
    subject:'暂无',
    language:'暂无',
    type:'暂无',
    major: '暂无',
    number: '暂无',
    result: '',
    pageType: '',//group 分组，grade成绩查询

    writeScore:'',//笔试成绩
    faceScore:'',//面试成绩
    finalScore:'' //最终成绩
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      'pageType': options.type
    });

    let pageTitle = '';
    switch (options.type){
      case 'group':
        pageTitle = '面试时间';
        break;
      break;
      case 'grade':
        pageTitle = '成绩查询';
        break;
    }
    wx.setNavigationBarTitle({
      title: pageTitle
    })

    common.getProcssState(function (data){
      //state==8 才有面试时间 避免state==6时错误显示
      if (data.mssj && data.state>7){
        that.data.mssj = data.mssj
      }else{
        //xybnr 面试时间生成中的提示
        if (common.emptyCheck(data.xybnr)){
          that.data.mssj = data.xybnr
        }else{
          that.data.mssj = "生成中请等待"
        }
      }
      that.setData({
        'state': data.state,
        'subject': data.xklb, //学科类别
        'number': data.zkzh, //准考证号
        'language': data.yzmc,//外语语种
        'major': data.xzzymc,//报考专业
        'type': data.bklb,//报考类别
        'result': that.data.mssj,//待确认下一步内容
        'username': data.xm, //姓名
        
        'writeScore': data.bscj,
        'faceScore': data.mscj,
        'finalScore': data.zzcj
      })
    });
    
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

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
