<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
        title="项目打分"
        width="450px"
        height="300px"
        xmlns="http://www.zkoss.org/2005/zul"
        use="com.ourway.review.YjAction"
        mode="overlapped" position="center,top"
>
    <custom-attributes caname="/xmps/yj.do"/>
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
        .borderRadius{
        border-radius: 5px;
        margin-left: 9px;
        padding: 3px;
        }
    </style>
    <div class="container-fluid" style="padding-bottom:15px;">
        <div class="row">
            <div class="col-sm-12">
                <div class="btn-group" align="right">
                    <button mold="bs" id="saveBtn" class="btn-info filecloseBtn">保存</button>
                    <button mold="bs" id="closeBtn" class="btn-info filecloseBtn">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--<div class="col-sm-12 " style="display:flex; justify-content: flex-start;flex-wrap:wrap;">
    </div>-->

    <div id="tipDiv" class="col-sm-12">

    </div>

    <div class="col-sm-12">
        <label value="组长结果：" style="margin: 10px;" />
        <listbox id="scores" mold="select" style="width:60%;margin:5px auto;"/>
        <!--<textbox id="reason" rows="3" style="width:100%;height:110px;" />-->
    </div>
    <div class="container-fluid">
        <div class="row" style="margin-top:2px;">
            <div class="col-sm-12">
                <div align="center">
                    <label value="**组长最终意见**" style="color:red"/>
                </div>
            </div>
        </div>
        <div sclass="row">
            <div class="col-sm-12">
                <textbox id="reason" rows="3" style="width:90%;height:110px;margin-left:10px;"/>
            </div>
        </div>
    </div>


</window>