<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/js/laydate/theme/default/laydate.css" />
    <link rel="stylesheet" href="${base}/css/chosen.css" />

</head>
<style>
    .col-sm-2{padding-left: 0 !important;padding-right: 0 !important;}
    @media (min-width: 768px){
        .col-sm-3 {
            /*width: 27% !important;*/
        }
        .col-sm-8 {
            width: 70% !important;
        }
        .col-sm-2 {
            width: 20% !important;
        }
    }

</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i> 当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a href="${base}/">首页</a></li>
                <li><a href="#">个人中心</a></li>
                <li class="active">生源信息</li>
            </ol>
        </div>
        <div class="content">
            <div class="menu-nav">
                <div class="menu-title">
                    <div class="title-chn">个人中心</div>
                    <div class="title-en">INFORMATION
                        <div class="menu-nav-icon"></div>
                    </div>

                </div>
                <div class="menu-list">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 导师咨询
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 咨询列表
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 报名预约
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 我的收藏
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 生源信息
                        </li>
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 就业方案
                        </li>
                    </ul>
                </div>
            </div>

            <div class="content-list" style="height: auto;">
                <form class="form-horizontal" style="padding-top: 20px;" id="registerForm" method="" action="" target="rfFrame">
                    <div class="form-group">
                        <label class="col-sm-5 col-sm-offset-1 text-left f-title">学生信息</label>
                    </div>
                    <div class="form-group">
                        <label for="xm" class="col-sm-2 control-label">姓名<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xm" name="xm" value="${(result.xm)!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xsxh" class="col-sm-2 control-label">学号<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xsxh" name="xsxh" value="${(result.xsxh)!''}" disabled="disabled" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="xxmc" class="col-sm-2 control-label">学校名称<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xxmc" name="xxmc" value="${(result.xxmc)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-5 col-sm-offset-1 text-left f-title">毕业去向信息</label>
                    </div>
                    <div class="form-group">
                        <label for="byqx" class="col-sm-2 control-label">毕业去向<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="byqx" name="byqx" data-val="${(result.byqx)!''}">
                                <option value="">请选择</option>
                                <#if byqxList??>
                                    <#list byqxList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <label for="sfzydk" class="col-sm-2 control-label">专业是否对口：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="sfzydk" name="sfzydk" data-val="${(result.sfzydk)!''}">
                                <option value="">请选择</option>
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="yrdwdm" class="col-sm-2 control-label">用人单位代码：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="yrdwdm" name="yrdwdm" value="${(result.yrdwdm)!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="yrdwmc" class="col-sm-2 control-label">用人单位名称：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="yrdwmc" name="yrdwmc" value="${(result.yrdwmc)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="yrdwxzmc" class="col-sm-2 control-label">用人单位性质名称：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="yrdwxzmc" name="yrdwxzmc" data-val="${(result.yrdwxzmc)!''}">
                                <option value="">请选择</option>
                                <#if yrdwxzList??>
                                    <#list yrdwxzList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <label for="dwhylbmc" class="col-sm-2 control-label">单位行业类别名称：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="dwhylbmc" name="dwhylbmc" data-val="${(result.dwhylbmc)!''}">
                                <option value="">请选择</option>
                                <#if dwhylbList??>
                                    <#list dwhylbList as obj>
                                        <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                       <#-- <label for="yrdwlsmc" class="col-sm-2 control-label">用人单位隶属部门：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="yrdwlsmc" name="yrdwlsmc" value="${(result.yrdwlsmc)!''}" placeholder="" autocomplete="off">
                        </div>-->
                        <label for="dwszdmc" class="col-sm-2 control-label">单位所在地名称：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="dwszdmc" name="dwszdmc" data-placeholder="请选择" data-val="${(result.dwszdmc)!''}">
                                <option value="-1"></option>
                                <#if sydList??>
                                    <#list sydList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                            <select class="form-control" id="dwszdmc1" style="display: none;" data-val="${(result.dwszdmc)!''}">
                                <#if sydList??>
                                    <#list sydList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <label for="gzzwlbmc" class="col-sm-2 control-label">工作职位类别名称：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="gzzwlbmc" name="gzzwlbmc" data-val="${(result.gzzwlbmc)!''}">
                                <option value="">请选择</option>
                                <#if gzzwlbList??>
                                    <#list gzzwlbList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dwlxr" class="col-sm-2 control-label">单位联系人：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="dwlxr" name="dwlxr" value="${(result.dwlxr)!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="dwdh" class="col-sm-2 control-label">单位联系电话：</label>
                        <div class="col-sm-3">
                            <input class="form-control" id="dwdh" name="dwdh" value="${(result.dwlxr)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <#--<div class="form-group">
                        <label for="zgdwdm" class="col-sm-2 control-label">主管单位代码：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zgdwdm" name="zgdwdm" value="${(result.zgdwdm)!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="zgdwmc" class="col-sm-2 control-label">主管单位名称：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zgdwmc" name="zgdwmc" value="${(result.zgdwmc)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>-->
                    <#--<div class="form-group">
                        <label for="yrdwlsmc" class="col-sm-2 control-label">用人单位隶属部门：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="yrdwlsmc" name="yrdwlsmc" value="${(result.yrdwlsmc)!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="dwszdmc" class="col-sm-2 control-label">单位所在地名称：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="dwszdmc" name="dwszdmc" data-placeholder="请选择" data-val="${(result.dwszdmc)!''}">
                                <option value="-1"></option>
                                <#if sydList??>
                                    <#list sydList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                                </select>
                                <select class="form-control" id="dwszdmc1" style="display: none;" data-val="${(result.dwszdmc)!''}">
                                <#if sydList??>
                                    <#list sydList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>-->
                   <#-- <div class="form-group">
                        <label for="dwszddm" class="col-sm-2 control-label">单位所在地代码：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="dwszddm" name="dwszddm" value="${(result.dwszddm)!''}" placeholder="" autocomplete="off">
                        </div>
                        &lt;#&ndash;<label for="dwlbmc" class="col-sm-2 control-label">单位行业类别名称：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="dwlbmc" name="dwlbmc" data-val="${(result.dwlbmc)!''}">
                                <option value="">请选择</option>
                                <#if dwhylbList??>
                                    <#list dwhylbList as obj>
                                        <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>&ndash;&gt;
                    </div>-->

                    <div class="form-group">
                        <label class="col-sm-5 col-sm-offset-1 text-left f-title">报到证信息</label>
                    </div>
                    <div class="form-group">
                        <label for="bdzqflbmc" class="col-sm-2 control-label">报到证签发类别名称：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="bdzqflbmc" name="bdzqflbmc" data-val="${(result.bdzqflbmc)!''}">
                                <option value="">请选择</option>
                                <#if bdzqflbList??>
                                    <#list bdzqflbList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <label for="bdzqwdwmc" class="col-sm-2 control-label">报到证签往单位名称：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bdzqwdwmc" name="bdzqwdwmc" value="${(result.bdzqwdwmc)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bdzqwszdmc" class="col-sm-2 control-label">报到证签往单位所在地<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="bdzqwszdmc" name="bdzqwszdmc" data-placeholder="请选择" data-val="${(result.bdzqwszdmc)!''}">
                                <option value="-1"></option>
                                <#if sydList??>
                                    <#list sydList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                            <select class="form-control" id="bdzqwszdmc1" style="display: none;" data-val="${(result.bdzqwszdmc)!''}">
                                <#if sydList??>
                                    <#list sydList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                        <label for="bdkssj" class="col-sm-2 control-label">报到开始时间<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <#if (result.bdkssj)??>
                                <input type="text" class="form-control" id="bdkssj" name="bdkssj" value="${(result.bdkssj)?substring(0,10)}" placeholder="" autocomplete="off">
                            <#else>
                                <input type="text" class="form-control" id="bdkssj" name="bdkssj" value="" placeholder="" autocomplete="off">
                            </#if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bdjssj" class="col-sm-2 control-label">报到结束时间<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <#if (result.bdjssj)??>
                                <input type="text" class="form-control" id="bdjssj" name="bdjssj" value="${(result.bdjssj)?substring(0,10)}" placeholder="" autocomplete="off">
                            <#else>
                                <input type="text" class="form-control" id="bdjssj" name="bdjssj" value="" placeholder="" autocomplete="off">
                            </#if>
                        </div>
                        <label for="bdzbz" class="col-sm-2 control-label">报到证备注：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bdzbz" name="bdzbz" value="${(result.bdzbz)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sfdydwbdz" class="col-sm-2 control-label">&nbsp;&nbsp;&nbsp;&nbsp;是否打印单位到报到证备注：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="sfdydwbdz" name="sfdydwbdz" data-val="${(result.sfdydwbdz)!''}">
                                <option value="">请选择</option>
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 col-sm-offset-1 text-left f-title">档案信息</label>
                    </div>
                    <div class="form-group">
                        <label for="datdxxdz" class="col-sm-2 control-label">档案投递详细地址：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="datdxxdz" name="datdxxdz" value="${(result.datdxxdz)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hkqydz" class="col-sm-2 control-label">户口迁移地址：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="hkqydz" name="hkqydz" value="${(result.hkqydz)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="datddw" class="col-sm-2 control-label">档案投递单位：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="datddw" name="datddw" value="${(result.datddw)!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="datddh" class="col-sm-2 control-label">档案投递电话：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="datddh" name="datddh" value="${(result.datddh)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="xjcqk" class="col-sm-2 control-label">下基层情况：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="xjcqk" name="xjcqk" value="${(result.xjcqk)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bzone" class="col-sm-2 control-label">备注一：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bzone" name="bzone" value="${(result.bzone)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bztwo" class="col-sm-2 control-label">备注二：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bztwo" name="bztwo" value="${(result.bztwo)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bzthree" class="col-sm-2 control-label">备注三：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bzthree" name="bzthree" value="${(result.bzthree)!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 text-center">
                            <button type="submit" class="btn btn-default btn-common green">保存</button>
                        </div>
                    </div>
                </form>
                <input type="hidden" value="${(result.owid)!''}" id="owid" />

                <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>

            </div>
        </div>
    </div>

</div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/chosen.jquery.js" type="text/javascript"></script>
<script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${base}/js/laydate/laydate.js" type="text/javascript"></script>
<script src="${base}/js/stuJyfa.js" type="text/javascript"></script>
</body>

</html>