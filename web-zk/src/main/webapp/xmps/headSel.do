<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="不合格原因"
    width="280px"
    height="400px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.review.HeadSelAction"
	mode="overlapped" position="center,top"
	>
    <custom-attributes caname="/xmps/headSel.do" />
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
<div class="container-fluid" style="padding-bottom:15px;" >
  <div class="row">
      <div class="col-sm-12">
           <div class="btn-group">
           	  <button mold="bs" id="saveBtn" class="btn-info filecloseBtn">保存</button>
           	  <button mold="bs" id="closeBtn" class="btn-info filecloseBtn">关闭</button>
           </div>
      </div>
  </div>
</div>
<div class="container-fluid" >
   <div class="row">
      <div class="col-sm-12">
         <listbox id="items" multiple="true" checkmark="true" height="280px"   />
      </div>
    </div>
</div>


</window>