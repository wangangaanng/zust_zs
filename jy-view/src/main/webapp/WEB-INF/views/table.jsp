<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="commonProperty.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>鄂尔多斯市转型发展投资有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/swiper.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/layer/skin/layer.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/com.css" />
    <style>
        .table1 tbody, .table1 tbody tr{width: 1200px;}
        .table1{padding: 30px 150px;
            border:none;}
        .p4{width: 15% !important;text-align: center;}
        .p5{width: 5% !important;text-align: center;vertical-align: middle !important;}
        .p6{width: 18% !important;}
        .p8{width: 28% !important;}
        .p20{width: 20% !important;text-align: center;vertical-align: middle !important;}
        .p15{width: 15% !important;text-align: center;vertical-align: middle !important;}
        .td-input{padding: 0 8px !important;}
        .td-input input{width: 100%;height: 37px;border: none;margin: 0;padding: 0;color: #888;}
        .td-input textarea{width: 100%;border: none;margin: 0;padding: 0;color: #888;resize: none;}
        textarea:focus, input:focus{outline: none !important;}
        .table2 tr:first-child td{border-top: none;}
        .table2 tr td:first-child{border-left: none;}
        .table2 tr td:last-child{border-right: none;}
        .table2 tr:last-child td{border-bottom: none;}
        .table2 tr td{width: 20% !important;text-align: center;}
        .keyitem{text-align: center;height: 80px;line-height: 80px !important;}
        .keyitem1{text-align: center;height: 80px;padding-top: 15px !important;}
        td{position: relative;}
        em.error{position: absolute;right: 15px;color: red;z-index: 10;top:4px;}
        .error1 em.error{top:-4px;}
        .layui-layer-dialog{top: 30% !important;}
        .bgc{background: #fddfdd;text-align: center}
        table, td{border-color: #000 !important;}
        .fwb{font-weight: bold;}
    </style>
<body style="background: #fff;">
<div class="top">
    <div class="container">
        <div class="pull-left ptinfo">专业的融资信息服务平台</div>
        <div class="pull-right ptinfo">客服热线：<span>0447-8581241</span></div>
    </div>
</div>
<div class="sub_body">
    <div class="container">
        <div class="col-md-5 col-sm-5" style="padding-left: 0;">
            <div class="compy">
                <img src="image/logo22.png" />
                <div class="compy_name">
                    <p>鄂尔多斯市转型发展</p>
                    <p class="ls3">投资有限责任公司</p>
                </div>
                <div class="web_name">
                    官方网站
                </div>
            </div>
        </div>
        <div class="col-md-7 col-sm-7" style="padding-right: 0;">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="indexPage.htm">首页</a></li>
                <%--<li role="presentation"><a href="aboutUs.htm?dicType=100001">新闻资讯</a></li>--%>
                <%--<li role="presentation"><a href="aboutUs.htm?dicType=100002">党建园地</a></li>--%>
                <%--<li role="presentation"><a href="aboutUs.htm?dicType=100003">客户服务</a></li>--%>
                <%--<li role="presentation"><a href="aboutUs.htm?dicType=100004">关于我们</a></li>--%>
                <%--<li role="presentation"><a href="aboutUs.htm?dicType=100005">热门推荐</a></li>--%>
            </ul>
        </div>
    </div>
</div>

<div style="margin: 0 auto;padding-top: 20px;color: #000;font-size: 22px;width: 100%;text-align: center;border-top: 1px solid #ddd">鄂尔多斯引导基金项目库<br>
    企业基本信息填报表</div>
<div class="container">
    <div class="table-responsive">
        <form id="signupForm" method="" action="" target="rfFrame">
            <table class="table table-bordered table1">
                <tbody>
                <tr>
                    <td colspan="30" class="bgc fwb">一、企业概况</td>
                </tr>
                <tr>
                    <td colspan="6" class="bgc p15">企业全称</td>
                    <td colspan="9" class="td-input"><input id="qyqc" name="qyqc" type="text" /></td>
                    <td colspan="6" class="bgc p15">总部地址</td>
                    <td colspan="9" class="td-input"><input id="bgdz" name="bgdz" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="bgc p15">成立日期</td>
                    <td colspan="9" class="td-input"><input id="clsj" name="clsj" type="text" /></td>
                    <td colspan="6" class="p15 bgc">所属细分行业</td>
                    <td colspan="9" class="td-input"><input id="sshy" name="sshy" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="bgc p15">主管部门</td>
                    <td colspan="9" class="td-input"><input id="zgbm" name="zgbm" type="text" /></td>
                    <td colspan="6" class="p15 bgc">企业员工数</td>
                    <td colspan="9" class="td-input"><input id="zzrs" name="zzrs" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="bgc p15">实际控制人姓名</td>
                    <td colspan="9" class="td-input"><input id="frdb" name="frdb" type="text" /></td>
                    <td colspan="6" class="p15 bgc">联系电话</td>
                    <td colspan="9" class="td-input"><input id="frdbSjh" name="frdbSjh" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="bgc p15">有效联系人及职务</td>
                    <td colspan="9" class="td-input"><input id="yxlxr" name="yxlxr" type="text" /></td>
                    <td colspan="6" class="p15 bgc">联系电话</td>
                    <td colspan="9" class="td-input"><input id="lxrDh" name="lxrDh" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="bgc p15">电子邮箱</td>
                    <td colspan="9" class="td-input"><input id="dzyx" name="dzyx" type="text" /></td>
                    <td colspan="6" class="p15 bgc"></td>
                    <td colspan="9" class="td-input"></td>
                </tr>
                <tr>
                    <td class="bgc p20" rowspan="7" colspan="6">股权结构现状<br>(只填报占比3%以上股东)</td>
                    <td class="p20 bgc" colspan="6" style="text-align: center;">股东名称</td>
                    <td class="p20 bgc" colspan="6" style="text-align: center;">股东性质</td>
                    <td class="p20 bgc" colspan="6" style="text-align: center;">出资金额(万元)</td>
                    <td class="p20 bgc" colspan="6" style="text-align: center;">出资比例(%)</td>
                </tr>
                <tr>
                    <td colspan="6" class="td-input"><input id="gd1" name="gd1" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="xz1" name="xz1" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="cz1" name="cz1" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="bl1" name="bl1" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="td-input"><input id="gd2" name="gd2" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="xz2" name="xz2" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="cz2" name="cz2" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="bl2" name="bl2" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="td-input"><input id="gd3" name="gd3" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="xz3" name="xz3" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="cz3" name="cz3" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="bl3" name="bl3" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="td-input"><input id="gd4" name="gd4" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="xz4" name="xz4" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="cz4" name="cz4" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="bl4" name="bl4" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="td-input"><input id="gd5" name="gd5" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="xz5" name="xz5" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="cz5" name="cz5" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="bl5" name="bl5" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="td-input"><input id="gd6" name="gd6" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="xz6" name="xz6" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="cz6" name="cz6" type="text" /></td>
                    <td colspan="6" class="td-input"><input id="bl6" name="bl6" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="30" class="bgc fwb">二、业务情况</td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">主营业务</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="zyywjs" placeholder="" name="zyywjs"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">商业模式</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="syms" placeholder="" name="syms"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">市场情况</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="scqk" placeholder="" name="scqk"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">行业地位</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="hydw" placeholder="" name="hydw"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">技术优势<br>(奖项/荣誉/专利/著作权等)</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="jsys" placeholder="" name="jsys"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">企业亮点</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="qyld" placeholder="" name="qyld"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">核心管理团队</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="hxgltd" placeholder="" name="hxgltd"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">其它需要说明的情况</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="qtxysmqk" placeholder="" name="qtxysmqk"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="30" class="bgc fwb">三、股权融资信息</td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">历史融资情况</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="lsrzqk" placeholder="" name="lsrzqk"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="td-input bgc" style="vertical-align: middle;">本次拟融资金额（万元）</td>
                    <td colspan="24" class="td-input">
                        <input type="text" id="nrzed" name="nrzed" />
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">资金用途</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="rzyt" placeholder="" name="rzyt"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem1 bgc">希望获得的其他支持（如行业资源、技术支持、上市辅导等）</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="qtxyzc" placeholder="" name="qtxyzc"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="30" class="bgc fwb">四、财务状况及关键数据</td>
                </tr>
                <tr>
                    <%--<td class="p15 bgc" colspan="6" >最近三年主要财务数据（万元）</td>--%>
                    <td colspan="30" style="padding: 0;">
                        <table class="table-bordered table2" style="width: 100%;border: none;">
                            <tbody>
                            <tr>
                                <td colspan="6" class="bgc">审计情况</td>
                                <td colspan="6" class="bgc">审计机构名称</td>
                                <td colspan="18" class="td-input"><input id="sjjg" name="sjjg" type="text" /></td>
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
                                <td colspan="6" class="td-input"><input id="zzc1" name="zzc1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="zzc2" name="zzc2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="zzc3" name="zzc3" type="text" /></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="bgc">负债总额</td>
                                <td colspan="6" class="td-input"><input id="xxjll1" name="xxjll1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="xxjll2" name="xxjll2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="xxjll3" name="xxjll3" type="text" /></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="bgc">净资产</td>
                                <td colspan="6" class="td-input"><input id="jzc1" name="jzc1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="jzc2" name="jzc2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="jzc3" name="jzc3" type="text" /></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="bgc">营业收入</td>
                                <td colspan="6" class="td-input"><input id="zysr1" name="zysr1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="zysr2" name="zysr2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="zysr3" name="zysr3" type="text" /></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="bgc">净利润</td>
                                <td colspan="6" class="td-input"><input id="jlr1" name="jlr1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="jlr2" name="jlr2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="jlr3" name="jlr3" type="text" /></td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">收入构成（产品类别以及占比）</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="srgc" placeholder="" name="srgc"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">其他关键数据</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="qtgjsj" placeholder="" name="qtgjsj"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">享受税务政策情况</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="xsswzcqk" placeholder="" name="xsswzcqk"></textarea>
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
                                <td colspan="6" class="td-input"><input id="yjsc1" name="yjsc1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="yjsc2" name="yjsc2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="yjsc3" name="yjsc3" type="text" /></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="bgc">净利润</td>
                                <td colspan="6" class="td-input"><input id="yjjlr1" name="yjjlr1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="yjjlr2" name="yjjlr2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="yjjlr3" name="yjjlr3" type="text" /></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="keyitem bgc" style="height: 97px;vertical-align: middle;">预测依据</td>
                                <td colspan="24" class="td-input" style="vertical-align: top;">
                                    <textarea rows="4" id="ycyj" placeholder="" name="ycyj"></textarea>
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
                    <td colspan="6" class="keyitem bgc">公司主要客户及所处行业</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="zykhhy" placeholder="" name="zykhhy"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem1 bgc" style="height: 97px;vertical-align: middle;">公司主要业务区域情况说明</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="zyywqyqk" placeholder="" name="zyywqyqk"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="30" class="bgc fwb">七、其它关注事项</td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc">事项</td>
                    <td colspan="1" class="bgc">是/否</td>
                    <td colspan="15" class="bgc">情况说明</td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc" style="text-align: left;">1、历史沿革是否涉及国企或集体企业改制</td>
                    <td colspan="1" class="td-input"><input id="sx1" name="sx1" type="text" /></td>
                    <td colspan="15" class="td-input"><input id="sxsn1" name="sxsn1" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc" style="text-align: left;">2、是否与控股股东、实际控制人存在同业竞争</td>
                    <td colspan="1" class="td-input"><input id="sx2" name="sx2" type="text" /></td>
                    <td colspan="15" class="td-input"><input id="sxsn2" name="sxsn2" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc" style="text-align: left;">3、是否存在影响独立性的持续性关联交易</td>
                    <td colspan="1" class="td-input"><input id="sx3" name="sx3" type="text" /></td>
                    <td colspan="15" class="td-input"><input id="sxsn3" name="sxsn3" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc" style="text-align: left;">4、最近两年内实际控制人或主营业务发生重大变化</td>
                    <td colspan="1" class="td-input"><input id="sx4" name="sx4" type="text" /></td>
                    <td colspan="15" class="td-input"><input id="sxsn4" name="sxsn4" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc" style="text-align: left;">5、最近两年内违反工商、税收、土地、环保、海关以及其他法律、行政法规，受到行政处罚，且情节严重</td>
                    <td colspan="1" class="td-input"><input id="sx5" name="sx5" type="text" /></td>
                    <td colspan="15" class="td-input"><input id="sxsn5" name="sxsn5" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc" style="text-align: left;">6、最近两年内曾向中国证监会提出发行申请；或有券商介入上市辅导且计划向中国证监会提出发行申请</td>
                    <td colspan="1" class="td-input"><input id="sx6" name="sx6" type="text" /></td>
                    <td colspan="15" class="td-input"><input id="sxsn6" name="sxsn6" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc" style="text-align: left;">7、最近两年公司董监高是否有违法违规的行为</td>
                    <td colspan="1" class="td-input"><input id="sx7" name="sx7" type="text" /></td>
                    <td colspan="15" class="td-input"><input id="sxsn7" name="sxsn7" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="14" class="bgc" style="text-align: left;">8、公司目前是否存在重大涉诉情况</td>
                    <td colspan="1" class="td-input"><input id="sx8" name="sx8" type="text" /></td>
                    <td colspan="15" class="td-input"><input id="sxsn8" name="sxsn8" type="text" /></td>
                </tr>
                </tbody>
            </table>
            <div class="container" style="text-align: center;">
                <button type="submit" class="btn btn-primary tjxm">提交</button>
            </div>
        </form>
        <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>
    </div>
    <div style="margin: 0 auto;padding: 20px 0;color: #000;font-size: 20px;width: 100%;text-align: center;border-top: 1px solid #ddd">
        填报说明
    </div>
    <p style="padding-left: 20%;padding-bottom: 30px;">
        1.企业信息须与营业执照上完全一致；<br>
        2.所属细分行业：填写到具体细分行业，按国家统计局《国民经济行业分类》(GB/T 4754—2017)行业分类；<br>
        3.股东性质：国有、集体、民营、中外合资、外商独资等；<br>
        4.主营业务：企业主要的业务范围、业务板块、产品简介等；<br>
        5.商业模式：如非传统行业，请用简明语言清晰表达商业模式<br>
        6.市场情况：行业壁垒、政策限制等，产品或服务市场规模与增长速度，核心竞争力构成，行业平均利润水平，主要竞争对手等；<br>
        7.企业亮点：除了行业地位、技术优势以外的企业竞争优势；<br>
        8.核心管理团队:核心管理团队及技术团队简介；<br>
        9.历史融资情况：投资人/投资机构名称，融资金额，融资时间；<br>
        10. 资金用途：股权融资获得资金的使用方向、前景等；<br>
        11. 其它关键数据：如制造企业的产能、成品率；互联网企业的活跃用户数、平台交易额；连锁企业的门店数量、地域分布等
    </p>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/table.js"></script>
</body>
</html>
