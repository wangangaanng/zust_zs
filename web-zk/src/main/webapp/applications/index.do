<?page title="浙江科技学院网站后台管理系统"?>
<?link rel="stylesheet" type="text/css"    href="/bootstrap/v3/bootstrap/themes/bootstrap-default.min.css" if="${empty param.theme}"?>
<?link rel="stylesheet" type="text/css"    href="/bootstrap/v3/assets/css/docs.css" ?>
<?link rel="stylesheet" type="text/css"    href="/bootstrap/v3/assets/css/pygments-manni.css" ?>
<?script src="/bootstrap/v3/assets/js/html5shiv.js" if="${zk.ie < 9}" ?>
<?script src="/bootstrap/v3/assets/js/respond.min.js" if="${zk.ie < 9}"?>
<!-- 引入websocket -->
<?script src="/assets/lib/jquery/jquery.min.js" ?>
<?script src="/assets/common/app.js" ?>
<?script src="/assets/common/md5.js" ?>
<?script src="/assets/lib/sockjs/sockjs.js" ?>
<?script src="/assets/common/func.js" ?>
<?script src="/assets/common/SocketClient.js" ?>
<!-- Load fonts from local -->
<?link rel="icon" href="/charisma/img/qzsj.ico" type="image/x-icon"/?>
<?link href="css/fonts.css" rel="stylesheet" type="text/css" ?>
<?link href="css/index.css" rel="stylesheet" type="text/css" if="${empty param.theme || param.theme == 'default'||param.theme == 'blue'|| param.theme == 'black'}"?>
<?link href="css/styleBlue.css" rel="stylesheet" type="text/css" if="${empty param.theme || param.theme == 'blue'|| param.theme == 'default'}"?>
<?link href="css/blackone.css" rel="stylesheet" type="text/css" if="${param.theme == 'black'}"?>
<!--<?link href="css/indexGreen.css" rel="stylesheet" type="text/css" if="${ param.theme == 'green'}"?>-->
<zk xmlns:n="native">
    <style>

    </style>
    <window use="com.ourway.apply.ManageMainAction"
            id="mainWin" width="100%"
            xmlns="http://www.zkoss.org/2005/zul"
            xmlns:h="http://www.w3.org/1999/xhtml"
    >
        <custom-attributes caname="/application/index.do"/>
        <div sclass="navbar">
            <a image="../charisma/img/img_logo2.png" label="" sclass="pull-left navbar-brand"/>
            <label id="systemTitle" sclass="pull-left navbar-title-label" value="欢迎使用浙江科技学院网站后台管理系统 V1.0"/>
            <textbox id="hiddenMessage" visible="false">
                <custom-attributes org.zkoss.zk.ui.updateByClient="true"/>
            </textbox>
            <!--<a id="applyProjectBtn" onClick="mainWin.openFunByPageCa(\'/project/newProjectHt.do\')" sclass="apply-project" >-->
            <!--<label value="项目申报" />-->
            <!--</a>-->
            <a id="commandBtn" visible="false" onClick="mainWin.invokeByJs()"/>
            <hlayout sclass="nav-user pull-right comtop-user" spacing="0">
                <a id="switchBtn" iconSclass="z-icon z-icon-qiehuan1" onClick="mainWin.switchAccount()"
                   style="border-left:none" label="账号切换" visible="false"/>
                <a id="xmzlBtn" iconSclass="z-icon z-icon-home" onClick="mainWin.initFirstPage()"
                   style="border-left:none" label="项目总览">
                </a>
                <a id="anoti" iconSclass="z-icon z-icon-notice" popup="notipp, position=after_end, type=toggle"
                   style="border-left:none">
                    <label value="0" id="anotiNumLabel" sclass="badge badge-important"/>
                </a>
                <menubar sclass="user-menu menu-message">
                    <menu label="" iconSclass="z-icon z-icon-theme">
                        <menupopup>
                            <menuitem label="默认主题" onClick='Executions.sendRedirect("?theme=default")'
                                      iconSclass="z-icon-angle-double-right"/>
                            <!--<menuitem label="怀念绿版" onClick='Executions.sendRedirect("?theme=green")' iconSclass="z-icon-angle-double-right"/>-->
                            <menuitem label="简约科技" onClick='Executions.sendRedirect("?theme=black")'
                                      iconSclass="z-icon-angle-double-right"/>
                        </menupopup>
                    </menu>
                </menubar>
                <menubar sclass="user-menu light-blue">
                    <menu tooltiptext="John's Avatar" label="欢迎您" id="userInfo"
                          image="assets/avatars/user.jpg"><!--  -->
                        <menupopup>
                            <menuitem id="updatePsw" onClick='mainWin.changePsw()'
                                      iconSclass="z-icon-angle-double-right"/>
                            <menuitem id="logoutOut" onClick='mainWin.logout()' iconSclass="z-icon-angle-double-right"/>
                        </menupopup>
                    </menu>
                </menubar>
            </hlayout>
            <popup id="notipp" sclass="menu menu-pink" width="240px">
                <a label="" id="notippLabel" sclass="header" iconSclass="z-icon-exclamation-triangle"/>
                <vlayout id="taskList" spacing="0">

                </vlayout>
            </popup>
        </div>

        <hlayout id="main" onClientInfo="mainWin.onClientInfo(event)" width="100%" spacing="0" sclass="indexContent">
            <tabbox id="menuGroup" orient="right">
                <tabs id="menuGroupTabs"/>
                <tabpanels id="menuGroupTabPanels"/>
            </tabbox>
            <tabbox id="resources" sclass="sourcetabs">
                <tabs id="resourceTabs"></tabs>
                <tabpanels id="resourceTabpanels" sclass="z-tabpanels-index"></tabpanels>
            </tabbox>
        </hlayout>
        <div sclass="indexFooter">
            <label style="font-color:#000000;font-size:12px;text-align:center;width:100%;"
                   value="Copyright © 2019 浙江科技学院. All Rights Reserved  "></label>
        </div>
    </window>
</zk>