<?xml version="1.0" encoding="UTF-8"?>
<?page title="项目评审中心-结果确认"?>
<?meta http-equiv="X-UA-Compatible" content="IE=edge"?>
<?meta name="renderer"  content="ie-comp" ?>
<window xmlns="http://www.zkoss.org/2005/zul"
        xmlns:h="http://www.w3.org/1999/xhtml"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        use="com.ourway.review.SchemaReviewLeaderComfirmAction2">
    <custom-attributes caname="/review/projectPsList.do"/>
    <style>
       .noFilled{ background:#F00; color:#FFF}
       .noFilled tr td{ background:#F00; color:#FFF}
       .filled{ background:#00FF00; color:#FFF}
       .filled tr td{ background:#F00; color:#FFF}
       .redColor{color:#F00;font-weight:bold;}
       .greenColor{color:#00FF00;}
       .width100{width:80px;}
       .width140{width:140px;}
       .buttonRed{background:#F00;color:#FFF;border:none;}
       .buttonGreen{background:#00FF00;color:#FFF;text-align: center;}
       .buttonYellow{background:#FF892A;color:#FFF;width:140px;}
       .buttonBlue{background:#478FCA;color:#FFF;width:140px;}
        .container-fluid .col-sm-4{text-align:right;}
    </style>
    <div class="container-fluid" style="border:1px dashed #CCC;padding:10px 10px 10px 10px;" >
        <div class="row form-horizontal" >
            <div class="col-sm-8">
               <div class="btn-group">
                  <button id="submitBtn" mold="bs"  sclass="btn-primary" label="提 交" />
                  <button id="refreshBtn" mold="bs"  sclass="btn-primary" label="刷 新" />
                  <button id="clsBtn" mold="bs"  sclass="btn-primary" label="关 闭" />
                  <button id="orderNoBtn" mold="bs"  sclass="btn-primary" label="重新打分" />
               </div>
            </div>
             <div class="col-sm-4">
               <div class="btn-group">
                  <button id="firstBtn" mold="bs" sclass="btn-primary" label="首页"  />
                  <button id="preBtn" mold="bs"  sclass="btn-primary" label="上一页" />
                  <button id="nextBtn" mold="bs"  sclass="btn-primary" label="下一页" />
                  <button id="lastBtn" mold="bs"  sclass="btn-primary" label="尾页" />
               </div>
             </div>
        </div>
    </div>

    <grid id="dataList" sclass="grid-spreadsheet" >
     <frozen columns="5">
         <div style="padding: 0 10px;" ></div>
      </frozen>
    </grid>



</window>