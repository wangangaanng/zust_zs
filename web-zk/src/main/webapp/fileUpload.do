<?xml version="1.0" encoding="UTF-8"?>
<?page title="浙江科技学院招就处一体化"?>
<?meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /?>
<?meta name="renderer" content="webkit" /?>
<?link rel="icon" href="/charisma/img/zust.ico" type="image/x-icon"/?>
<?link href="/charisma/css/common.css" rel="stylesheet" type="text/css"?>
<?link href="/charisma/css/login.css" rel="stylesheet" type="text/css"?>
<?script src="/charisma/js/jquery-3.2.1.min.js" ?>
<window
        xmlns="http://www.zkoss.org/2005/zul"
        xmlns:h="http://www.w3.org/1999/xhtml"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        use="com.ourway.syszk.selfDefine.ProjectInforImport"
        id="projectInforWin"
        class="login-body"
        onOk="projectInforWin.check()">
    <style>
        .listBoxClass {
        padding: 6px;
        margin-right: 20px;
        width: 100px;
        border: 1px solid #d8d7d8;
        border-radius: 5px;
        color: #a78d91;
        }
    </style>
    <custom-attributes caname="/fileUpload.do"/>
    <grid onClientInfo="projectInforWin.onClientInfo(event)" style="height:50px !important;">
        <rows>
            <row spans="1,2,3">
                <div align="right" >
                    <label value="文件名：" style="color:red" />
                </div>
                <textbox id="attachFileName"  style="width:200px" />
                <hbox>
                    <button id="uploadBtn" label="上传" width="150px" upload="true" />
                    <button label="关闭" style="width:150px" onClick="projectInforWin.detch()" />
                </hbox>
            </row>
            <!--<row spans="6">-->
                <!--<hbox>-->
                    <!--<button id="uploadFileBtn" label="上传附件" width="150px" />-->

                <!--</hbox>-->
            <!--</row>-->

        </rows>
    </grid>

</window>