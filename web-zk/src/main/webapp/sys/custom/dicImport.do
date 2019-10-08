<?xml version="1.0" encoding="UTF-8"?>
<?page title="浙江科技学院招就处一体化管理平台"?>
<?meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /?>
<?meta name="renderer" content="webkit" /?>
<?link rel="icon" href="/charisma/img/zust.ico" type="image/x-icon"/?>
<?link href="/charisma/css/common.css" rel="stylesheet" type="text/css"?>
<?link href="/charisma/css/login.css" rel="stylesheet" type="text/css"?>
<?script src="/charisma/js/jquery-3.2.1.min.js" ?>
<window
        xmlns="http://www.zkoss.org/2005/zul"
        use="com.ourway.syszk.selfDefine.DicTreeImportAction"
        id="projectInforWin">
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
    <custom-attributes caname="/dicImport.do"/>
    <grid onClientInfo="projectInforWin.onClientInfo(event)" style="height:50px !important;">
        <rows>
            <row spans="1,2,3">
                <hbox>
                    <button id="uploadBtn" label="上传" width="150px" upload="true" />
                </hbox>
            </row>
        </rows>
    </grid>

</window>