// pages/majorExam/majorExam.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    subjectShowBool:false,
    languageShowBool:false,
    typeShowBool:false,
    majorShowBool:false,
    subject:"",//学科
    language:"",//语种
    type:"",//类别
    major:"",//专业
    subjectCategory: ['学科类别1', '宁波', '温州', '嘉兴', '湖州'],//学科类别
    foreignLanguages: ['外语语种1', '宁波', '温州', '嘉兴', '湖州'],//外语语种
    projectType: ['报考类别1', '宁波', '温州', '嘉兴', '湖州'],//报考类别
    enrollmentMajor: ['招生专业1', '宁波', '温州', '嘉兴', '湖州'],//招生专业
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },
  //取消显示
  cancelPop(){
    this.setData({ 
      subjectShowBool: false,
      languageShowBool: false,
      typeShowBool: false,
      majorShowBool: false
    });
  },
  //显示学科
  showSubject(){
    this.setData({subjectShowBool:true });
  },
  //确定学科
  confirmSubject(event){
    const { index, value} = event.detail;
    console.log(value);
    this.setData({ 
      subject: value, 
      subjectShowBool: false
    });
  },
  //外语语种
  showLanguage(){
      this.setData({ languageShowBool: true });
  },
  //确定语种
  confirmLanguages(event) {
    const { index, value } = event.detail;
    console.log(value);
    this.setData({
      language: value,
      languageShowBool: false
    });
  },
  //显示类别
  showType(){
    this.setData({ typeShowBool: true });
  },
  confirmType(event){
    const { index, value } = event.detail;
    this.setData({
      type: value,
      typeShowBool: false
    });
  },
  showMajor(){
    this.setData({ majorShowBool: true });
  },
  confirmMajor(event){
    const { index, value } = event.detail;
    this.setData({
      major: value,
      majorShowBool: false
    });
  }
})