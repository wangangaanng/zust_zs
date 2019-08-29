<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
    title="请输入评审结论"
    width="580px"
    height="380px"
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.review.ReviewFinalAction"
	mode="overlapped" position="center,middle"
	>
    <custom-attributes caname="/xmps/psjl.do" />
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
       .noFilled{ background:#F00; color:#FFF}
       .noFilled tr td{ background:#F00; color:#FFF}
       .filled{ background:#00FF00; color:#FFF}
       .filled tr td{ background:#F00; color:#FFF}
       .redColor{color:#F00;font-weight:bold;}
       .greenColor{color:#00FF00;}
       .width100{width:80px;}
       .width140{width:140px;}
       .buttonRed{background:#F00;color:#FFF;width:140px;}
       .buttonGreen{background:#00FF00;color:#FFF;width:140px;}
       .buttonYellow{background:#FF892A;color:#FFF;width:140px;}
       .buttonBlue{background:#478FCA;color:#FFF;width:140px;}
       .container-fluid .col-sm-4{text-align:right;}
</style>
<div class="container-fluid" style="padding-bottom:15px;" >
  <div class="row">
      <div class="col-sm-3">

      </div>
      <div class="col-sm-8">
           <div class="btn-group">
           	  <button mold="bs" id="saveBtn" class="btn-info filecloseBtn">保存</button>
           	  <button mold="bs" id="closeBtn" class="btn-info filecloseBtn">关闭</button>
           </div>
      </div>
  </div>
</div>
<div class="container-fluid" >
   <div class="row">
      <div class="col-sm-2">
         <div align="right">
             <label value="评审结果*:" style="color:red" />
         </div>
      </div>
      <div class="col-sm-9">
         <div class="btn-group">
            <button id="passBtn"  >整体规范</button>
            <button id="mostBtn" visible="false"  >比较规范</button>
            <button id="noPassBtn"  >不规范</button>
            <button id="bcPassBtn" >补充材料</button>
          </div>
      </div>
    </div>
   <div class="row" style="margin-top:2px;">
   <div class="col-sm-3"></div>
      <div class="col-sm-8">
          <div align="left">
             <label value="**请输入200字数之内的评审结论**" style="color:red" />
          </div>
      </div>

    </div>
    <div class="row" style="margin-top:8px;">
      <div class="col-sm-2">
          <div align="right">
             <label value="评审结论*:" style="color:red" />
          </div>
      </div>
      <div class="col-sm-9">
         <textbox id="reason" rows="3" style="width:100%;height:200px;" />
      </div>
    </div>
</div>


</window>