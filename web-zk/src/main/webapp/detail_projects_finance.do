<?xml version="1.0" encoding="UTF-8"?>
<?page title="项目投资详情"?>
<window
        style="height:100%;overflow-y:auto;"
        xmlns="http://www.zkoss.org/2005/zul"
        xmlns:h="http://www.w3.org/1999/xhtml"
        use="com.ourway.apply.ProjectFinanceAction">
    <custom-attributes caname="/detail_projects_finance.do"/>
    <!--<include src="../newProjectInfo.do"/>-->
    <tabbox id="projectTabbox" sclass="sourcetabs">
        <tabs id="projectTabs">
            <tab id="step1Tab" label="项目投资详情"/>
            <!--<tab id="step2Tab" label="第二步：项目流程定义" />-->
            <!--<tab id="step3Tab" label="第三步：项目基本信息" />-->
        </tabs>
        <tabpanels id="projectTabpanels" sclass="z-tabpanels-index">
            <tabpanel id="step1Content">
                <grid>
                    <rows>
                        <row align="1,2,1,2">
                            <div align="right" style="color:red">
                                <label value="投资期限*：" style="width:150px"/>
                            </div>
                            <div>
                                <datebox id="financeYearStart"/>--
                                <datebox id="financeYearEnd"/>
                            </div>
                            <div align="right" style="color:red">
                                <label value="经费管理部门：" style="width:150px"/>
                            </div>
                            <bandbox width="150px" id="deptBandbox" />
                        </row>
                        <row align="1,5">
                            <div align="right">
                                <label value="投资总金额：" style="width:150px" />
                            </div>
                            <textbox width="150px" readonly="true" />
                        </row>
                        <row align="1,2,1,2">
                            <div align="right">
                                <label value="中央投资："/>
                            </div>
                            <doublebox width="150px" />
                            <div align="right">
                                <label value="地方投资："/>
                            </div>
                            <doublebox width="150px" />
                        </row>
                        <row align="1,2,1,2">
                            <div align="right">
                                <label value="政府投资" />
                            </div>
                            <combobox />
                        </row>
                    </rows>
                </grid>
            </tabpanel>
        </tabpanels>
    </tabbox>

</window>