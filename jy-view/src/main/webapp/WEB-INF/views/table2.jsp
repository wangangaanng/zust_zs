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
        .p15{width: 15% !important;text-align: center;vertical-align: middle !important;}
        .td-input{padding: 0 8px !important;}
        .td-input input{width: 100%;height: 37px;border: none;margin: 0;padding: 0;color: #888;}
        .td-input textarea{width: 100%;border: none;margin: 0;padding: 0;color: #888;resize: none;}
        textarea:focus, input:focus{outline: none !important;}
        .table2 tr:first-child td{border-top: none;}
        .table2 tr td:first-child{border-left: none;}
        .table2 tr td:last-child{border-right: none;}
        .table2 tr:last-child td{border-bottom: none;}
        .table2 tr td{width: 25% !important;text-align: center;}
        .keyitem{text-align: center;height: 80px;line-height: 80px !important;}
        .keyitem1{text-align: center;height: 80px;padding-top: 15px !important;}
        td{position: relative;}
        em.error{position: absolute;right: 15px;color: red;z-index: 10;top:4px;}
        .error1 em.error{top:-4px;}
        .layui-layer-dialog{top: 30% !important;}
        .bgc{background: #fddfdd;}
        table, td{border-color: #000 !important;}
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

<div style="margin: 0 auto;padding-top: 20px;color: #000;font-size: 22px;width: 100%;text-align: center;border-top: 1px solid #ddd">企业申报表</div>
<div class="container">
    <div class="table-responsive">
        <form id="signupForm" method="" action="" target="rfFrame">
            <table class="table table-bordered table1">
                <tbody>
                <tr>
                    <td class="p5 bgc" rowspan="7" style="position: inherit;" colspan="2">企<br>业<br>基<br>本<br>信<br>息</td>
                    <td colspan="4" class="bgc">企业名称</td>
                    <td colspan="24" class="td-input"><input id="qyqc" name="qyqc" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="4" class="bgc">注册地址</td>
                    <td colspan="24" class="td-input"><input id="zcdz" name="zcdz" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="4" class="bgc">办公地址</td>
                    <td colspan="24" class="td-input"><input id="bgdz" name="bgdz" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="4" class="bgc">法定代表人</td>
                    <td colspan="10" class="td-input"><input id="frdb" name="frdb" type="text" /></td>
                    <td colspan="4" class="p4 bgc">企业成立时间</td>
                    <td colspan="10" class="td-input"><input id="clsj" name="clsj" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="4" class="bgc">在职人数</td>
                    <td colspan="10" class="td-input"><input id="zzrs" name="zzrs" type="text" /></td>
                    <td colspan="4" class="p4 bgc">所属行业</td>
                    <td colspan="10" class="td-input"><input id="sshy" name="sshy" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="p6 bgc">法定代表人身份证号码</td>
                    <td colspan="22" class="td-input"><input id="frdbSfz" name="frdbSfz" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="4" class="bgc">注册资本（万元）</td>
                    <td colspan="10" class="td-input"><input id="zczb" name="zczb" type="text" /></td>
                    <td colspan="4" class="p4 bgc">营业执照号</td>
                    <td colspan="10" class="td-input"><input id="yyzzh" name="yyzzh" type="text" /></td>
                </tr>
                <tr>
                    <td class="p5 bgc" rowspan="4" style="position: inherit;" colspan="2">联<br>系<br>人<br>信<br>息</td>
                    <td colspan="4" class="bgc">联系人</td>
                    <td colspan="10" class="td-input"><input id="lxr" name="lxr" type="text" /></td>
                    <td colspan="4" class="p4 bgc">身份证号码</td>
                    <td colspan="10" class="td-input"><input id="lxrSfz" name="lxrSfz" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="4" class="bgc">联系电话</td>
                    <td colspan="10" class="td-input"><input id="lxrDh" name="lxrDh" type="text" /></td>
                    <td colspan="4" class="p4 bgc">手机</td>
                    <td colspan="10" class="td-input"><input id="lxrMobile" name="lxrMobile" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="4" class="bgc">通讯地址</td>
                    <td colspan="24" class="td-input"><input id="txdz" name="txdz" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="4" class="bgc">传真号码</td>
                    <td colspan="10" class="td-input"><input id="czhm" name="czhm" type="text" /></td>
                    <td colspan="4" class="p4 bgc">电子邮箱</td>
                    <td colspan="10" class="td-input"><input id="dzyx" name="dzyx" type="text" /></td>
                </tr>
                <tr>
                    <td class="p15 bgc" rowspan="6" colspan="6">股权结构</td>
                    <td class="p8 bgc" colspan="8" style="text-align: center;">主要股东名称</td>
                    <td class="p8 bgc" colspan="8" style="text-align: center;">出资方式及金额</td>
                    <td class="p8 bgc" colspan="8" style="text-align: center;">股权比例</td>
                </tr>
                <tr>
                    <td colspan="8" class="td-input"><input id="gd1" name="gd1" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="cz1" name="cz1" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="bl1" name="bl1" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="8" class="td-input"><input id="gd2" name="gd2" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="cz2" name="cz2" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="bl2" name="bl2" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="8" class="td-input"><input id="gd3" name="gd3" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="cz3" name="cz3" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="bl3" name="bl3" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="8" class="td-input"><input id="gd4" name="gd4" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="cz4" name="cz4" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="bl4" name="bl4" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="8" class="td-input"><input id="gd5" name="gd5" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="cz5" name="cz5" type="text" /></td>
                    <td colspan="8" class="td-input"><input id="bl5" name="bl5" type="text" /></td>
                </tr>

                <tr>
                <tr>
                    <%--<td class="p15"--%>
                    <td class="p15 bgc" colspan="6" >前两个年度和最近一期财务数据
                        （单位：万元）</td>
                    <td colspan="24" style="padding: 0;">
                        <table class="table-bordered table2" style="width: 100%;border: none;">
                            <tbody>
                            <tr>
                                <td colspan="6" class="bgc" style="height: 40px;line-height: 40px;">年   度</td>
                                <td colspan="6" class="bgc">2016年</td>
                                <td colspan="6" class="bgc">2017年</td>
                                <td colspan="6" class="bgc">2018年</td>
                            </tr>
                            <tr>
                                <td colspan="6" class="bgc">总资产</td>
                                <td colspan="6" class="td-input"><input id="zzc1" name="zzc1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="zzc2" name="zzc2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="zzc3" name="zzc3" type="text" /></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="bgc">净资产</td>
                                <td colspan="6" class="td-input"><input id="jzc1" name="jzc1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="jzc2" name="jzc2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="jzc3" name="jzc3" type="text" /></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="bgc">主营业务收入</td>
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
                            <tr>
                                <td colspan="6" class="bgc">经营现金净流量</td>
                                <td colspan="6" class="td-input"><input id="xxjll1" name="xxjll1" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="xxjll2" name="xxjll2" type="text" /></td>
                                <td colspan="6" class="td-input"><input id="xxjll3" name="xxjll3" type="text" /></td>
                            </tr>
                            </tbody>
                        </table>
                    </td>


                <tr>
                    <td colspan="6" class="keyitem bgc">主营业务介绍</td>
                    <td colspan="24" class="td-input">
                        <textarea rows="4" id="zyywjs" placeholder="（需说明所属行业，现有行业地位，如涉及多个行业，需说明各行业收入占比）" name="zyywjs"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">历史沿革</td>
                    <td colspan="24" class="td-input"><textarea rows="4" placeholder="（说明目标公司及其前身成立时间、分子公司设立过程、股权变更、重大资产重组等）" id="lsyg" name="lsyg"></textarea></td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem1 bgc">行业主要竞争对手（含国内外）</td>
                    <td colspan="24" class="td-input"><textarea rows="4" id="jzds" name="jzds"></textarea></td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem1 bgc">主要竞争<br>
                        优势分析</td>
                    <td colspan="24" class="td-input"><textarea rows="4" id="ysfx" placeholder="（说明目标公司在盈利模式、技术、研发、市场、管理团队等方面特有的竞争优势）" name="ysfx"></textarea></td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem1 bgc">未来三年发展规划及预计主要投资项目介绍</td>
                    <td colspan="24" class="td-input"><textarea rows="4" id="fzgh" name="fzgh"></textarea></td>
                </tr>
                <tr>
                    <td rowspan="2" colspan="6" class="keyitem bgc" style="position: inherit;">贷款及借款情况</td>
                    <td colspan="24" class="td-input error1" style="height: 40px;line-height: 40px;">银行贷款情况：<input id="yhdk" name="yhdk" style="width: 560px;position: absolute;left: 100px;top:0;" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="24" class="td-input error1" style="height: 40px;line-height: 40px;">其他借款：<input id="qtjk" name="qtjk" style="width: 560px;position: absolute;left: 73px;top:0;" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">已获得投资说明</td>
                    <td colspan="24" class="td-input"><textarea rows="4" id="ydtzsm" name="ydtzsm"></textarea></td>
                </tr>
                <tr>
                    <td rowspan="4" colspan="6" class="keyitem bgc">融资意向</td>
                    <td colspan="12" class="td-input error1" style="border-right: none;height: 40px;line-height: 40px;">拟融资额度(万元)：<input id="nrzed" name="nrzed" style="width: 180px;position: absolute;left: 125px;top:0;" type="text" /></td>
                    <td colspan="12" class="td-input error1" style="border-left: none;height: 40px;line-height: 40px;">拟融资期限：<input id="nrzqx" name="nrzqx" style="width: 220px;position: absolute;left: 90px;top:0;" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="24" class="td-input error1" style="height: 40px;line-height: 40px;">融资用途：<input id="rzyt" name="rzyt" style="width: 560px;position: absolute;left: 73px;top:0;" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="24" class="td-input error1" style="border-bottom: none;height: 40px;line-height: 40px;">融资方式意向：<input id="rzfxyx" name="rzfxyx" style="width: 560px;position: absolute;left: 100px;top:0;" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="24" class="td-input error1" style="border-top: none;height: 40px;line-height: 40px;">还款来源：<input id="hkly" name="hkly" style="width: 560px;position: absolute;left: 73px;top:0;" type="text" /></td>
                </tr>
                <tr>
                    <td colspan="6" class="keyitem bgc">服务需求</td>
                    <td colspan="24" class="td-input"><textarea rows="4" placeholder="（中心可提供路演展示、培育孵化、投融资对接、咨询服务、资本商学院课程、教练陪跑等服务内容）" id="fwxq" name="fwxq"></textarea></td>
                </tr>
                </tbody>
            </table>
            <div class="container" style="text-align: center;">
                <button type="submit" class="btn btn-primary tjxm">提交</button>
            </div>
        </form>
        <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>
    </div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/table2.js"></script>
</body>
</html>
