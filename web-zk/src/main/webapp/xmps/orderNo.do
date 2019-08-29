<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window
        title="设轮次"
        width="450px"
        height="300px"
        xmlns="http://www.zkoss.org/2005/zul"
        use="com.ourway.review.OrderNoAction"
        mode="overlapped" position="center,top"
>
    <custom-attributes caname="/xmps/orderNo.do"/>
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
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <label value="轮次：" style="margin: 10px;" />
                <intbox id="orderNo" style="width:60%;margin:5px auto;"/>
                <!--<textbox id="reason" rows="3" style="width:100%;height:110px;" />-->
            </div>
        </div>
    </div>


</window>