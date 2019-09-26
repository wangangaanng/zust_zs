//index.js
//获取应用实例
var Mustache = require('../../libs/mustache/mustache');
var WxParse = require('../../libs/wxParse/wxParse.js');
const app = getApp()

Page({
  data: {
    imgUrls: ['https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg','https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg'],
    menuList: [{ text: '学校概况', icon: '../../../static/index-icon01.png', url:'/pages/logs/logs' }, { text: '专业介绍', icon: '../../../static/index-icon02.png' }, { text: '新闻公告', icon: '../../../static/index-icon03.png' }, { text: '招聘信息', icon: '../../../static/index-icon04.png' }, { text: '职业指导', icon: '../../../static/index-icon05.png' }, { text: '就业排行榜', icon: '../../../static/index-icon06.png' }, { text: '学生服务', icon: '../../../static/index-icon07.png' }, { text: '企业服务', icon: '../../../static/index-icon08.png' }],
    list: [
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表一', des: '这里是描述内容1' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表二', des: '这里是描述内容2' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表三', des: '这里是描述内容3' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表一', des: '这里是描述内容1' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表二', des: '这里是描述内容2' },
      { img: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1679456154,2493198454&fm=26&gp=0.jpg', title: '新闻列表三', des: '这里是描述内容3' },
    ]
  },
  onLoad: function () {
    var data = this.data;
    // <a class="weui-media-box weui-media-box_appmsg">
    //   <div class="weui-media-box__hd">
    //     <img class="weui-media-box__thumb" src="defaultImg.png" alt="">
    //       </div>
    //     <div class="weui-media-box__bd">
    //       <h4 class="index-guess_title">名称名称</h4>
    //       <h6>年 · 万公里</h6>
    //       <p>成交价<span class="index-guess_prize"> 万</span>
    //       </p>
    //     </div>
    //   </a>
      var tpl = "{{#list}}<div class='media-box'><img class='media-box_thumb' src='{{{img}}}' /><a style='color:#000000;text-decoration:none;'>{{ title }}</a><p style='color:#999999;'>{{ des }}</p></div>{{/ list}}";
    // var tpl = '{{#list}}<a class="weui-media-box weui-media-box_appmsg"><div class="weui-media-box__hd"><img class="weui-media-box__thumb" src="{{{img}}}" alt=""></div><div class="weui-media-box__bd"><h4 class="index-guess_title">名称名称</h4><h6>年 · 万公里</h6><p>成交价<span class="index-guess_prize"> 万</span></p></div></a>{{/list}}';
      var html = Mustache.render(tpl, data);
      WxParse.wxParse('template', 'html', html, this, 5);
    },
  onReachBottom: function () {
    console.log(1);
  }
  })
