#zust_website

##doc
存放数据库，接口文档等

##common-module
公共模块，即公用都放在这个模块

##zs-module
招生模块

##jy-module
就业模块

##swyt-module
三位一体模块

##zust-center
注册中心

##zust-gateway
服务网关   

##zust-utils
所有的公用的方法都写在这个模块
             
##web-zk
ftl页面都写在 /WEB-INF/template 目录下，页面路径 com.ourway.syszk.controll该文件夹下新增controller

####接口调用
通过网关调用, 统一使用 webzk中的Comm

后台配置页面接口路径需要加前缀 如下 \*\*为具体的方法路径
公用模块 : /center/zustcommon/**
招生模块 : /center/zustzs/**
就业模块 : /center/zustjy/**
三位一体模块 : /center/zustswyt/**




**企业服务**
    enterpriseService.html

**咨询列表**
    askList.html
    
**联系我们**
    contactUs.html
  
**企业指南**
  enterpriseGuide.html

**企业注册**
    enterpriseReg.html
  
**招聘会申请**
  jobFair.html
    
**新闻详情**
  newsDetail.html
    
**新闻列表**
  newsList.html
  
**学生服务**
    studentService.html
  
**导师详情**
    teacherDetail.html