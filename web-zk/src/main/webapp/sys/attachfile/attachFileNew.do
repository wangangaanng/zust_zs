<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="附件中心"
    width="800px"
    height="400px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.sys.attachfile.AttachFileNewAction"
	mode="overlapped" position="center,top"
	>
    <custom-attributes caname="/sys/attachfile/attachFileNew.do" />
<style>
.z-button {
       display: inline-block;
       padding: 6px 12px;
       margin-bottom: 0;
       font-size: 14px;
       font-weight: normal;
       line-height: 1.428571429;
       text-align: center;
       white-space: nowrap;
       vertical-align: middle;
       cursor: pointer;
       border: 1px solid transparent;
       border-radius: 4px;
       -webkit-user-select: none;
       -moz-user-select: none;
       -ms-user-select: none;
       -o-user-select: none;
       user-select: none;
       background: -webkit-linear-gradient(top, #5BC0DE 0%, #5BC0DE 100%);
       background-color: #5bc0de;
       border-color: #46b8da;
}
</style>
<style src="/widgets/effects/upload_effect/Customize.css" />
<div class="container-fluid" style="padding-bottom:15px;" >
  <div class="row">
      <div class="col-sm-12">
           <div class="btn-group">
           	  <button mold="bs" id="closeBtn" class="btn-info filecloseBtn">关闭</button>
           </div>
           <vlayout id="flist" hflex="1" sclass="fileList" >
           </vlayout>
      </div>
  </div>
</div>
<div id="fileList" class="container-fluid" >

</div>
<script type="text/javascript" src="/widgets/effects/upload_effect/UPMSamples.js" />

</window>