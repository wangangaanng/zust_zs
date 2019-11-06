<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<#include "com/ZSconfig.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/css/bootstrap-table.min.css" />
    <link rel="stylesheet" href="${base}/css/style.css" />
    <style>
        a:hover
        {
            text-decoration:none;
            out-line: none;
        }
        .fuye_sr_con{
            width:100%;margin: 20px 0px;}

    </style>
</head>
<body>
<#include "com/ZSheader.ftl">
<#include "com/ZStopSlider.ftl">
<!-- S frame-content -->
<div class="frame-wrap">
    <!-- S  a-->
    <div class="index-nav">
        <div class="index-nav_content">
            <div class="index-nav_item">
                <img src="${base}/img/zsdt.png" />
                <a name="zsdt" onclick="changeNews(this)">招生动态</a>
            </div>
            <div class="index-nav_item">
                <img src="${base}/img/2017zszy.png" />
                <a val="按专业招生" name="zszy" onclick="changeNews(this)">2019招生专业</a>
            </div>
            <div class="index-nav_item">
                <img src="${base}/img/zydh.png" />
                <a href="${base}/zszy">专业导航</a>
            </div>
            <div class="index-nav_item">
                <img src="${base}/img/swytbmxt.png" />
                <a href="">三位一体</a>
            </div>
            <div class="index-nav_item">
                <img src="${base}/img/xsybdxt.png" />
                <a href="">预报到</a>
            </div>
            <div class="index-nav_item">
                <img src="${base}/img/zsjd.png" />
                <a name="zsjd" onclick="changeNews(this)">生源基地</a>
            </div>
        </div>
    </div>

    <!-- E a -->
    <!-- S b -->
    <div class="shouYe_div_dynamic">
        <div class="dyn_nav" id="zylbDiv">
            <ul class="zylb" id="zylb">
                <#if (zszyList??)&&(zszyList?size>0)>
                    <#list zszyList as obj>
                        <#if obj_index==0>
                            <li val="${obj.dicVal1!''}" name='zszy' style='background-color: #008784;' onClick='changeNews(this)'><a class='zylb_li_a' style="color: white;">${obj.dicVal2!''}</a></li>
                        <#else >
                            <li val="${obj.dicVal1!''}" name='zszy' style='' onClick='changeNews(this)'><a class='zylb_li_a'>${obj.dicVal2!''}</a></li>
                        </#if>
                    </#list>
                </#if>
            </ul>
        </div>
        <div class="dyn_details">
        <#--<#list list as obj>-->
            <#--<div class="detail">-->
                <#--<img class="detail_img" src=""/>-->
                <#--<strong class="detail_str">浙江科技学院2017艺术类热门专业介绍</strong>-->
                <#--<p class="detail_p">2047年斯洛克斯洛伐克顺路快递附件文件呢副科级还胡歌卡俊女是的发送到广发噶的风格还是规划是否更换地方规划的法规哈地方噶地方噶地方噶分公司体会让他</p>-->
                <#--<span class="glyphicon glyphicon-time"></span><b class="detail_date"> 2016-11-11</b>-->
                <#--<div class="rnum">-->
                    <#--<span class="glyphicon glyphicon-eye-open"></span><b class="detail_num"> 935</b>-->
                <#--</div>-->
            <#--</div>-->
        <#--</#list>-->
        </div>
        <div class="detail_more">查看更多</div>
    </div>
    <!-- E b -->

    <!-- S c -->
    <div class="shouYe_div_chaxun">
        <div class="chaxun_line1"></div>
        <div class="chaxun_line4"></div>
        <b class="chaxun_b">我要查询</b>
        <div class="chaxun_line5"></div>
        <div class="chaxun_line2"></div>
        <div class="chaxun_line6"></div>
        <b class="chaxun_b">通知公告</b>
        <div class="chaxun_line7"></div>
        <div class="chaxun_line3"></div>
        <br /><br />
        <div class="woYaoChaXun">
            <div class="chaXuns">
                <div class="jhcx">
                    <div class="jhcx_icon">
                        <span id="jhcx_icon_span" class="glyphicon glyphicon-list-alt" style="color: white; font-size: 25px;"></span>
                    </div>
                    <p id="jhcx_p" class="chaXuns_p">计划查询</p>
                </div>
                <div class="cjcx">
                    <div class="cjcx_icon">
                        <span id="cjcx_icon_span" class="glyphicon glyphicon-book" style="color: rgb(184, 184, 184); font-size: 25px;"></span>
                    </div>
                    <p class="chaXuns_p">成绩查询</p>
                </div>
                <div class="lqcx">
                    <div class="lqcx_icon">
                        <span id="lqcx_icon_span" class="glyphicon glyphicon-file" style="color: rgb(184, 184, 184); font-size: 25px;"></span>
                    </div>
                    <p class="chaXuns_p">录取查询</p>
                </div>
                <div class="lncx">
                    <div class="lncx_icon">
                        <span id="lncx_icon_span" class="glyphicon glyphicon-calendar" style="color: rgb(184, 184, 184); font-size: 25px;"></span>
                    </div>
                    <p class="chaXuns_p">历年查询</p>
                </div>
            </div>
            <br /><br /><br /><br /><br />
            <div class="chaXunSels">
                <div class="jhcx_form">
                    <select id="jhcx_nf" onchange="jhchang(this)" class="nf" name="nf">
                        <option value="">&nbsp;年份</option>
                        <#if (conditionJh??)&&(conditionJh.nfList??)&&(conditionJh.nfList?size>0)>
                            <#list conditionJh.nfList as obj>
                                <option value="${obj.nf}">${obj.nf}</option>
                            </#list>
                        </#if>
                    </select>
                    <select id="jhcx_sf" onchange="jhchang(this)" class="sf" name="sf">
                        <option value="">&nbsp;省份</option>
                        <#if (conditionJh??)&&(conditionJh.sfList??)&&(conditionJh.sfList?size>0)>
                            <#list conditionJh.sfList as obj>
                                <option value="${obj.sf}">${obj.sf}</option>
                            </#list>
                        </#if>
                    </select>
                    <select id="jhcx_kl" onchange="jhchang(this)" class="kl" name="kl">
                        <option value="">&nbsp;科类</option>
                        <#if (conditionJh??)&&(conditionJh.klList??)&&(conditionJh.klList?size>0)>
                            <#list conditionJh.klList as obj>
                                <option value="${obj.kl}">${obj.kl}</option>
                            </#list>
                        </#if>
                    </select>
                    <select id="jhcx_pc" onchange="jhchang(this)" class="pc" name="pc">
                        <option value="">&nbsp;批次</option>
                        <#if (conditionJh??)&&(conditionJh.pcList??)&&(conditionJh.pcList?size>0)>
                            <#list conditionJh.pcList as obj>
                                <option value="${obj.pc}">${obj.pc}</option>
                            </#list>
                        </#if>
                    </select>
                    <select id="jhcx_zy" onchange="jhchang(this)" class="zy" name="zy">
                        <option value="">&nbsp;专业</option>
                        <#if (conditionJh??)&&(conditionJh.zyList??)&&(conditionJh.zyList?size>0)>
                            <#list conditionJh.zyList as obj>
                                <option value="${obj.zy}">${obj.zy}</option>
                            </#list>
                        </#if>
                    </select>
                    <span id="jhcx_chaXun" class="queryButton" onclick="jhcx_chaXun()">查询</span>
                    <span class="queryButton" onclick="jhcx_init()">重置</span>
                </div>
                <div class="cjcx_form">
                    <input id="cjcx_zkzh" class="input_zkzh" type="text" name="input_zkzh" placeholder="请输入准考证号" value="" />
                    <input id="cjcx_sfzh" class="input_sfzh" type="text" name="input_sfzh" placeholder="请输入身份证号" value="" />
                    <span id="cjcx_chaXun" class="queryButton" onclick="cjcx_chaXun()">查询</span>
                </div>
                <div class="lqcx_form">
                    <input id="lqcx_zkzh" class="input_zkzh" type="text" name="input_zkzh" placeholder="请输入准考证号" value="" />
                    <input id="lqcx_sfzh" class="input_sfzh" type="text" name="input_sfzh" placeholder="请输入身份证号" value="" />
                    <span id="lqcx_chaXun" class="queryButton" onclick="lqcx_chaXun()">查询</span>
                </div>
                <div class="lncx_form">
                    <select id="lncx_nf" onchange="mychange(this)" class="nf" name="">
                        <option value="">&nbsp;年份</option>
                    <#if (conditionLn??)&&(conditionLn.nfList??)&&(conditionLn.nfList?size>0)>
                        <#list conditionLn.nfList as obj>
                            <option value="${obj.nf}">${obj.nf}</option>
                        </#list>
                    </#if>
                    </select>
                    <select id="lncx_sf" onchange="mychange(this)" class="sf" name="">
                        <option value="">&nbsp;省份</option>
                        <#if (conditionLn??)&&(conditionLn.sfList??)&&(conditionLn.sfList?size>0)>
                            <#list conditionLn.sfList as obj>
                                <option value="${obj.sf}">${obj.sf}</option>
                            </#list>
                        </#if>
                    </select>
                    <select id="lncx_kl" onchange="mychange(this)" class="kl" name="">
                        <option value="">&nbsp;科类</option>
                    <#if (conditionLn??)&&(conditionLn.klList??)&&(conditionLn.klList?size>0)>
                        <#list conditionLn.klList as obj>
                            <option value="${obj.kl}">${obj.kl}</option>
                        </#list>
                    </#if>
                    </select>
                    <select id="lncx_pc" onchange="mychange(this)" class="pc" name="">
                        <option value="">&nbsp;批次</option>
                        <#if (conditionLn??)&&(conditionLn.pcList??)&&(conditionLn.pcList?size>0)>
                            <#list conditionLn.pcList as obj>
                                <option value="${obj.pc}">${obj.pc}</option>
                            </#list>
                        </#if>
                    </select>
                    <select id="lncx_zy" onchange="mychange(this)" class="zy" name="">
                        <option value="">&nbsp;专业</option>
                        <#if (conditionLn??)&&(conditionLn.zyList??)&&(conditionLn.zyList?size>0)>
                            <#list conditionLn.zyList as obj>
                                <option value="${obj.zy}">${obj.zy}</option>
                            </#list>
                        </#if>
                    </select>
                    <span id="lncx_chaXun" class="queryButton" onclick="lncx_chaXun()">查询</span>
                    <span class="queryButton" onclick="lncx_init()">重置</span>
                </div>
            </div>
        </div>
        <div class="tongZhiGongGao">
            <ul class="gongGao_ul">
                <#if (tzggList??)&&(tzggList.records??)&&(tzggList.records?size>0)>
                    <#list tzggList.records as obj>
                        <li class="gongGao_ul_li" onclick="linkUrl('wzxq/${obj.owid!''}')">
                            <span class="glyphicon glyphicon-volume-up" style="font-size: 20px;float: left;"></span>
                            <p class="gongGao_title">${obj.wzbt!''}</p>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>
        <div class="shouYe_division"></div>
    </div>
    <!-- E c -->
    <!-- 模态弹出查询结果 -->
    <div class="modal" id="mymodal">
        <div class="modal-dialog" style="width:1200px;">
            <div class="modal-content">
                <div class="modal-body">
                    <div style="min-height: 200px;">
                        <table class="cxRes" border='1'cellspacing="0" cellpadding="0" id="table-zsjh">
                            <!-- <tr><th>Header</th></tr>
                            <tr><td>Data</td></tr> -->
                        </table>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <#--模态弹出2-->
    <div class="modal" id="mymodal2">
        <div class="modal-dialog" style="width:1200px;">
            <div class="modal-content">
                <div class="modal-body">
                    <div style="min-height: 200px;">
                        <div id="lqcx_stuInfo">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th style="width: 150px">准考证号： </th>
                                    <th style="width: 250px" id="zkzh"></th>
                                    <th style="width: 150px" >身份证： </th>
                                    <th style="width: 250px" id="sfzh"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="width: 150px">姓名： </td>
                                    <td style="width: 250px" id="xm"></td>
                                    <td style="width: 150px" >联系电话： </td>
                                    <td style="width: 250px" id="lxdh"></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="fuye_sr_con">
                                <dl class="success" style="display:none;" id="successed">
                                    <dt>
                                        <img src="${base}/img/smile.png" alt="">
                                    </dt>
                                    <dd><h4 class="co_r">恭喜你</h4>
                                        <p>你已经被<strong class="co_r" id="zhuhe">浙江科技学院 -自动化（机电一体化技术） 预录取,最终录取请查询当地考试院。</strong></p>
                                        <p id="lqd"></p>
                                        <p id="ems_dh">EMS单号：1004205824025，请注意查收！！！</p>
                                    </dd>
                                </dl>
                                <dl class="error" style="display:none;" id="failed">
                                    <dt><img src="${base}/img/depress.png" alt=""></dt>
                                    <dd><h4></h4>
                                        <p>很抱歉，目前系统里没有你的录取信息，或者你的录取批次还未开始，请继续关注本网站公告，谢谢！</p>
                                    </dd>
                                </dl>
                            </div>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
<!-- E frame-content -->
<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/swiper.min.js"></script>
<script src="${base}/js/bootstrap-table.min.js"></script>
<script src="${base}/js/bootstrap-table-zh-CN.min.js"></script>
<script src="${base}/js/zs/shouYe.js" type="text/javascript"></script>

</body>
</html>
