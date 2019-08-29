<?xml version="1.0" encoding="UTF-8"?>
<?page title=""?>
<window   
    xmlns="http://www.zkoss.org/2005/zul"
	xmlns:h="http://www.w3.org/1999/xhtml" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	use="com.ourway.base.zk.template.MainTemplateAction">
    <custom-attributes caname="/template/MainTemplate.do" />
    <hlayout id="main" width="100%" spacing="0" sclass="indexAllContent">
    	<div id="sidebar" sclass="subsidebar">
    	   <!-- Navigation List -->
    	    <navbar id="mainNavbar" mold="default" sclass="nav-list" orient="vertical" style="overflow-y:auto" >

    	    </navbar>

        </div>
    	<tabbox id="mainResources" sclass="sourcetabs">
    	    <tabs id="mainResourceTabs"></tabs>
    	    <tabpanels id="mainResourceTabpanels" sclass="z-tabpanels-index"></tabpanels>
        </tabbox>
    </hlayout>
</window>