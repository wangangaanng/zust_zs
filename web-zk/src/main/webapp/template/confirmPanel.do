<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<!-- 单个grid增删改查 -->
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.component.BaseConfirmWindow"
	>
    <custom-attributes caname="/template/confirmPanel.do" />
    <div id="mainTableGrid" class="container-fluid mainTableGrid" >
       <div class="row form-horizontal">
           <div class="col-sm-12" >
                <textbox id="reasonTxt" rows="4" class="form-control input-sm" />
           </div>
       </div>
    </div>
    <div id="operateGrid" class="container-fluid buttonDiv operateGrid"  >
      <div class="col-sm-12" style="text-align:center;width:100%;margin-top:20px;" >
         <button label="确认" mold="bs" id="confirm" sclass="btn-primary " iconSclass="z-icon z-icon-save" style="border:1px solid #ccc"  />
         <button label="取消" mold="bs" id="close" sclass="btn-primary " iconSclass="z-icon z-icon-cancel" style="padding-left:10px;"  />
      </div>
    </div>
</window>