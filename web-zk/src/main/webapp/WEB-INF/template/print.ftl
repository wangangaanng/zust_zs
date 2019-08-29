<#--<!DOCTYPE html>-->
<#--<html>-->
<#--<head>-->
<#--<meta charset="UTF-8">-->
<#--<title>Insert title here</title>-->
<#--</head>-->
<#--<body>-->
<#--${dataBean.hello}-->
<#--</body>-->
<#--</html>-->


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>鄂尔多斯市转型发展投资有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="charisma/css/bootstrap.css" />
    <style>
        *{font-size:12px !important;}
        .p4{width: 15% !important;text-align: center;}
        .p5{width: 5% !important;text-align: center;vertical-align: middle !important;}
        .p6{width: 18% !important;}
        .p8{width: 28% !important;}
        .p15{width: 15% !important;text-align: center;vertical-align: middle !important;}
        .td-input{
            /*padding: 0 8px !important;*/
            font-weight: normal !important;}
        .td-input input{width: 100%;height: 37px;border: none;margin: 0;padding: 0;color: #888;}
        .td-input textarea{width: 100%;border: none;margin: 0;padding: 0;color: #888;resize: none;}
        textarea:focus, input:focus{outline: none !important;}
        .table2 tr:first-child td{border-top: none;}
        .table2 tr td:first-child{border-left: none;}
        .table2 tr td:last-child{border-right: none;}
        .table2 tr:last-child td{border-bottom: none;}
        .table2 tr td{
            /*width: 20% !important;*/
            text-align: center;}
        .keyitem{text-align: center;height: 80px;line-height: 80px !important;}
        .keyitem1{text-align: center;height: 80px;padding-top: 15px !important;}
        /*td{position: inherit;}*/
        em.error{position: absolute;right: 15px;color: red;z-index: 10;top:4px;}
        .error1 em.error{top:-4px;}
        .layui-layer-dialog{top: 30% !important;}
        .bgc{background: #fddfdd;text-align: center;}

        body{ font-size:12px !important; color:#000000;font-family:pingfang sc light;margin: 0 auto;width: 794px;}
        body {font-family: pingfang sc light;font-weight:bold;background:none;margin-left: auto;margin-right: auto;}
        .bt{color: #121212;font-size: 26px; font-weight:bold; line-height:80px;text-align: center;}
        .A4 {margin: 0 auto;width: 794px; }
        .f_12{ font-size:12px;}
        .f_red{ color:#F00}
        .page {
            width: 21cm;
            /*height: 29.7cm;*/
            padding: 1cm;
            margin: 1cm auto;
            border: 1px #D3D3D3 solid;
            border-radius: 5px;
            background: white;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            position: relative;
        }
        .subpage {
            padding: 1cm;
            border: 5px red solid;
            height: 256mm;
            outline: 2cm #FFEAEA solid;
        }
        .water-print{
            background-color:#FFFFFF;
            background-image:url(img/img_logo2.png)  ;
            background-repeat:repeat;
        }
        .noprint h6{margin: 0px;font-style: normal;color: #333;text-align: center}
        .noprint{position: fixed;bottom: 50px;right: 100px;text-decoration: none;z-index: 99999;width: 70px;height: 70px;border-radius: 50%;background: rgba(0,0,0,0.1)}
        .noprint img{width: 39px;margin: 0 auto;display: block;margin-top: 13px;}
        @page {
            size: A4;
            margin: 0;
        }
        @media print {
            .page {
                margin: 0;
                border: initial;
                border-radius: initial;
                width: initial;
                min-height: initial;
                box-shadow: initial;
                background: initial;
                page-break-after: always;
            }
            .noprint{display:none;}
        }
        .table-responsive{overflow-x: hidden;}
        table, tr, td{border-color: #000 !important;border-style: 2px;}
        td{vertical-align:middle !important; position: static;}
        .table2 td{padding: 8px !important;}
        table{table-layout:fixed !important;word-break:break-all !important;}
    </style>
    <script>
        function doPrint() {
            bdhtml=window.document.body.innerHTML;
            sprnstr="<!--startprint-->";
            eprnstr="<!--endprint-->";
            prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
            console.log(prnhtml);
            prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
            console.log(prnhtml);
            window.document.body.innerHTML=prnhtml;
            window.print();
        }
    </script>
<body style="background: #fff;">
<!--startprint-->
<div class="page">
    <a href="javascript:doPrint()" class="noprint">
        <img src="charisma/img/logo/print.png"/>
        <h6>打印</h6>
    </a>
    <div style="margin: 0 auto;color: #000;font-size: 16px !important;width: 100%;text-align: center;">鄂尔多斯引导基金项目库<br>
        企业基本信息填报表</div>
        <div style="width: 100%;text-align: right;font-weight: normal; padding: 5px">填表日期：${dataBean.tbrqTime!''}</div>
        <div class="table-responsive">
                <table class="table table-bordered table1">
                    <tbody>
                    <tr>
                        <td colspan="30" class="bgc fwb">一、企业概况</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="bgc p15">企业全称</td>
                        <td colspan="9" class="td-input">${dataBean.qyqc!''}</td>
                        <td colspan="6" class="bgc p15">总部地址</td>
                        <td colspan="9" class="td-input">${dataBean.bgdz!''}</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="bgc p15">成立日期</td>
                        <td colspan="9" class="td-input">${dataBean.clsj!''}</td>
                        <td colspan="6" class="p15 bgc">所属细分行业</td>
                        <td colspan="9" class="td-input">${dataBean.sshy!''}</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="bgc p15">主管部门</td>
                        <td colspan="9" class="td-input">${dataBean.zgbm!''}</td>
                        <td colspan="6" class="p15 bgc">企业员工数</td>
                        <td colspan="9" class="td-input">${dataBean.zzrs!''}</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="bgc p15">实际控制人姓名</td>
                        <td colspan="9" class="td-input">${dataBean.frdb!''}</td>
                        <td colspan="6" class="p15 bgc">联系电话</td>
                        <td colspan="9" class="td-input">${dataBean.frdbSjh!''}</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="bgc p15">有效联系人及职务</td>
                        <td colspan="9" class="td-input">${dataBean.yxlxr!''}</td>
                        <td colspan="6" class="p15 bgc">联系电话</td>
                        <td colspan="9" class="td-input">${dataBean.lxrDh!''}</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="bgc p15">电子邮箱</td>
                        <td colspan="9" class="td-input">${dataBean.dzyx!''}</td>
                        <td colspan="6" class="p15 bgc"></td>
                        <td colspan="9" class="td-input"></td>
                    </tr>
                    <tr>
                        <td class="bgc p20" rowspan="${listSize}" colspan="6">股权结构现状<br>(只填报占比3%以上股东)</td>
                        <td class="p20 bgc" colspan="6" style="text-align: center;">股东名称</td>
                        <td class="p20 bgc" colspan="6" style="text-align: center;">股东性质</td>
                        <td class="p20 bgc" colspan="6" style="text-align: center;">出资金额(万元)</td>
                        <td class="p20 bgc" colspan="6" style="text-align: center;">出资比例(%)</td>
                    </tr>
                    <#list dataBean.gdList as gdList>
                        <tr>
                            <td colspan="6" class="td-input" style="text-align: center;">
                                ${gdList.gd!''}
                            </td>
                            <td colspan="6" class="td-input" style="text-align: center;">
                                ${gdList.gdxz!''}
                            </td>
                            <td colspan="6" class="td-input" style="text-align: center;">
                                ${gdList.czfs!''}
                            </td>
                            <td colspan="6" class="td-input" style="text-align: center;">
                                ${gdList.gqzb!''}
                            </td>
                        </tr>
                    </#list>

                    <tr>
                        <td colspan="30" class="bgc fwb">二、业务情况</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">主营业务</td>
                        <td colspan="24" class="td-input">
                            ${dataBean.zyywjs!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">商业模式</td>
                        <td colspan="24" class="td-input">
                            ${dataBean.syms!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">市场情况</td>
                        <td colspan="24" class="td-input">
                            ${dataBean.scqk!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">行业地位</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.hydw!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">技术优势<br>(奖项/荣誉/专利/著作权等)</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.jsys!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">企业亮点</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.qyld!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">核心管理团队</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.hxgltd!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">其它需要说明的情况</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.qtxysmqk!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="30" class="bgc fwb">三、股权融资信息</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">历史融资情况</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.lsrzqk!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="td-input bgc" style="vertical-align: middle;">本次拟融资金额（万元）</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.nrzed!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">资金用途</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.rzyt!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem1 bgc">希望获得的其他支持（如行业资源、技术支持、上市辅导等）</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.qtxyzc!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="30" class="bgc fwb">四、财务状况及关键数据</td>
                    </tr>
                    <tr>
                        <td colspan="30" style="padding: 0;">
                            <table class="table-bordered table2" style="width: 100%;border: none;">
                                <tbody>
                                <tr>
                                    <td colspan="6" class="bgc">审计情况</td>
                                    <td colspan="6" class="bgc">审计机构名称</td>
                                    <td colspan="18" class="td-input" style="text-align: left;">${dataBean.sjjg!''}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" rowspan="7" class="bgc" style="padding: 8px;border-bottom: none;">最近三年主要财务数据（万元）</td>
                                    <td colspan="6" class="bgc" style="height: 40px;line-height: 40px;">类   别</td>
                                    <td colspan="6" class="bgc">2018年</td>
                                    <td colspan="6" class="bgc">2017年</td>
                                    <td colspan="6" class="bgc">2016年</td>
                                </tr>
                                <tr>
                                    <td colspan="6" class="bgc">资产总额</td>
                                    <td colspan="6" class="td-input">${dataBean.zzc1!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.zzc2!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.zzc3!''}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" class="bgc">负债总额</td>
                                    <td colspan="6" class="td-input">${dataBean.xxjll1!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.xxjll2!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.xxjll3!''}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" class="bgc">净资产</td>
                                    <td colspan="6" class="td-input">${dataBean.jzc1!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.jzc2!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.jzc3!''}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" class="bgc">营业收入</td>
                                    <td colspan="6" class="td-input">${dataBean.zysr1!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.zysr2!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.zysr3!''}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" class="bgc">净利润</td>
                                    <td colspan="6" class="td-input">${dataBean.jlr1!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.jlr2!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.jlr3!''}</td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">收入构成（产品类别以及占比）</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.srgc!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">其他关键数据</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.qtgjsj!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem bgc">享受税务政策情况</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.xsswzcqk!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="30" class="bgc fwb">五、财务预测</td>
                    </tr>
                    <tr>
                        <td colspan="30" style="padding: 0;">
                            <table class="table-bordered table2" style="width: 100%;border: none;">
                                <tbody>
                                <tr>
                                    <td colspan="6" rowspan="3" class="bgc" style="padding: 8px;border-bottom: none;">未来三年盈利预测（万元）</td>
                                    <td colspan="6" class="bgc" style="height: 40px;line-height: 40px;">类   别</td>
                                    <td colspan="6" class="bgc">2019年</td>
                                    <td colspan="6" class="bgc">2020年</td>
                                    <td colspan="6" class="bgc">2021年</td>
                                </tr>
                                <tr>
                                    <td colspan="6" class="bgc">营业收入</td>
                                    <td colspan="6" class="td-input">${dataBean.yjsc1!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.yjsc2!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.yjsc3!''}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" class="bgc">净利润</td>
                                    <td colspan="6" class="td-input">${dataBean.yjjlr1!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.yjjlr2!''}</td>
                                    <td colspan="6" class="td-input">${dataBean.yjjlr3!''}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" class="keyitem bgc" style="height: 97px;vertical-align: middle;">预测依据</td>
                                    <td colspan="24" class="td-input" style="vertical-align: top;text-align: left;">
                                    ${dataBean.ycyj!''}
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="30" class="bgc fwb">六、客户情况</td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">公司主要客户及所处行业</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.zykhhy!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">公司主要业务区域情况说明</td>
                        <td colspan="24" class="td-input">
                        ${dataBean.zyywqyqk!''}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="30" class="bgc fwb">七、其它关注事项</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc">事项</td>
                        <td colspan="2" class="bgc" style="padding: 0;">是/否</td>
                        <td colspan="14" class="bgc">情况说明</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc" style="text-align: left;">1、历史沿革是否涉及国企或集体企业改制</td>
                        <td colspan="2" class="td-input" style="text-align: center;">${dataBean.sx1!''}</td>
                        <td colspan="14" class="td-input">${dataBean.sxsn1!''}</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc" style="text-align: left;">2、是否与控股股东、实际控制人存在同业竞争</td>
                        <td colspan="2" class="td-input" style="text-align: center;">${dataBean.sx2!''}</td>
                        <td colspan="14" class="td-input">${dataBean.sxsn2!''}</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc" style="text-align: left;">3、是否存在影响独立性的持续性关联交易</td>
                        <td colspan="2" class="td-input" style="text-align: center;">${dataBean.sx3!''}</td>
                        <td colspan="14" class="td-input">${dataBean.sxsn3!''}</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc" style="text-align: left;">4、最近两年内实际控制人或主营业务发生重大变化</td>
                        <td colspan="2" class="td-input" style="text-align: center;">${dataBean.sx4!''}</td>
                        <td colspan="14" class="td-input">${dataBean.sxsn4!''}</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc" style="text-align: left;">5、最近两年内违反工商、税收、土地、环保、海关以及其他法律、行政法规，受到行政处罚，且情节严重</td>
                        <td colspan="2" class="td-input" style="text-align: center;">${dataBean.sx5!''}</td>
                        <td colspan="14" class="td-input">${dataBean.sxsn5!''}</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc" style="text-align: left;">6、最近两年内曾向中国证监会提出发行申请；或有券商介入上市辅导且计划向中国证监会提出发行申请</td>
                        <td colspan="2" class="td-input" style="text-align: center;">${dataBean.sx6!''}</td>
                        <td colspan="14" class="td-input">${dataBean.sxsn6!''}</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc" style="text-align: left;">7、最近两年公司董监高是否有违法违规的行为</td>
                        <td colspan="2" class="td-input" style="text-align: center;">${dataBean.sx7!''}</td>
                        <td colspan="14" class="td-input">${dataBean.sxsn7!''}</td>
                    </tr>
                    <tr>
                        <td colspan="14" class="bgc" style="text-align: left;">8、公司目前是否存在重大涉诉情况</td>
                        <td colspan="2" class="td-input" style="text-align: center;">${dataBean.sx8!''}</td>
                        <td colspan="14" class="td-input">${dataBean.sxsn8!''}</td>
                    </tr>
                    </tbody>
                </table>
        </div>
</div>
<!--endprint-->
</body>
</html>
