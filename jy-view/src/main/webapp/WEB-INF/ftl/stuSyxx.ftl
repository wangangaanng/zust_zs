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
                <li><a href="/">首页</a></li>
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
                        <li class="list-group-item active1">
                            <span class="ic-menu"></span> 生源信息
                        </li>
                        <li class="list-group-item">
                            <span class="ic-menu"></span> 就业方案
                        </li>
                    </ul>
                </div>
            </div>

            <div class="content-list" style="height: auto;">
                <form class="form-horizontal" style="padding-top: 20px;" id="registerForm" method="" action="" target="rfFrame">
                    <div class="form-group">
                        <label for="xm" class="col-sm-2 control-label">姓名<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xm" name="xm" value="${stuInfo.xm!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xsxh" class="col-sm-2 control-label">学号<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <#if stuInfo.xsxh??>
                                <input type="text" class="form-control" id="xsxh" name="xsxh" value="${stuInfo.xsxh!''}" disabled="disabled" placeholder="" autocomplete="off">
                            <#else>
                               <input type="text" class="form-control" id="xsxh" name="xsxh" value=""  placeholder="" autocomplete="off">
                            </#if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="csrq" class="col-sm-2 control-label">出生日期<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="csrq" name="csrq" value="${stuInfo.csrq!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xb" class="col-sm-2 control-label">性别<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="xb" name="xb" data-val="${stuInfo.xb!''}">
                                <option value="">请选择</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mz" class="col-sm-2 control-label">民族<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="mz" name="mz" data-val="${stuInfo.mz}">
                                <option value="">请选择</option>
                                <#if mzList??>
                                    <#list mzList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                            <#--<input type="text" class="form-control" id="mz" name="mz" value="${stuInfo.mz!''}" placeholder="" autocomplete="off">-->
                        </div>
                        <label for="zzmm" class="col-sm-2 control-label">政治面貌<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="zzmm" name="zzmm" data-val="${stuInfo.zzmm}">
                                <option value="">请选择</option>
                                <#if zzmmList??>
                                    <#list zzmmList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                            <#--<input type="text" class="form-control" id="zzmm" name="zzmm" value="${stuInfo.zzmm!''}" placeholder="" autocomplete="off">-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sfz" class="col-sm-2 control-label">身份证号<span class="red">*</span>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sfz" name="sfz" value="${stuInfo.sfz!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="jtdz" class="col-sm-2 control-label">家庭地址<span class="red">*</span>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="jtdz" name="jtdz" value="${stuInfo.jtdz!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="syd" class="col-sm-2 control-label">生源地<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="syd" name="syd" data-val="${stuInfo.syd}">
                                <option value="">请选择</option>
                                <#if sydList??>
                                    <#list sydList as obj>
                                    <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                    </#list>
                                </#if>
                            </select>
                            <#--<input type="text" class="form-control" id="syd" name="syd" value="${stuInfo.syd!''}" placeholder="" autocomplete="off">-->
                        </div>
                        <label for="rxnf" class="col-sm-2 control-label">入学日期<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="rxnf" name="rxnf" value="${stuInfo.rxnf!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bynf" class="col-sm-2 control-label">毕业年份<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bynf" name="bynf" value="${stuInfo.bynf!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="byrq" class="col-sm-2 control-label">毕业日期<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="byrq" name="byrq" value="${stuInfo.byrq!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cxsy" class="col-sm-2 control-label">城乡生源<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="cxsy" name="cxsy" data-val="${stuInfo.cxsy!''}">
                                <option value="">请选择</option>
                                <option value="农村">农村</option>
                                <option value="城镇">城镇</option>
                            </select>
                            <#--<input type="text" class="form-control" id="cxsy" name="cxsy" value="${stuInfo.cxsy!''}" placeholder="" autocomplete="off">-->
                        </div>
                        <label for="ksh" class="col-sm-2 control-label">考生号<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="ksh" name="ksh" value="${stuInfo.ksh!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="xz" class="col-sm-2 control-label">学制<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="xz" name="xz" data-val="${stuInfo.xz!''}">
                                <option value="">请选择</option>
                                <option value="1.5">1.5</option>
                                <option value="2">2</option>
                                <option value="2.5">2.5</option>
                                <option value="3">3</option>
                                <option value="3.5">3.5</option>
                                <option value="4">4</option>
                                <option value="4.5">4.5</option>
                                <option value="5">5</option>
                                <option value="5.5">5.5</option>
                                <option value="6">6</option>
                                <option value="6.5">6.5</option>
                                <option value="7">7</option>
                                <option value="7.5">7.5</option>
                                <option value="8">8</option>
                                <option value="8.5">8.5</option>
                                <option value="9">9</option>
                                <option value="9.5">9.5</option>
                            </select>
                            <#--<input type="text" class="form-control" id="xz" name="xz" value="${stuInfo.xz!''}" placeholder="" autocomplete="off">-->
                        </div>
                        <label for="xxmc" class="col-sm-2 control-label">所属学校<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xxmc" name="xxmc" value="${stuInfo.xxmc!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ssxy" class="col-sm-2 control-label">所属学院<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="ssxy" name="ssxy" value="${stuInfo.ssxy!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xszy" class="col-sm-2 control-label">学校专业<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xszy" name="xszy" value="${stuInfo.xszy!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="szbj" class="col-sm-2 control-label">所在班级<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="szbj" name="szbj" value="${stuInfo.szbj!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="pyfs" class="col-sm-2 control-label">培养方式<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="pyfs" name="pyfs" data-val="${stuInfo.pyfs!''}">
                                <option value="">请选择</option>
                                <option value="在职">在职</option>
                                <option value="委培">委培</option>
                                <option value="定向">定向</option>
                                <option value="自筹">自筹</option>
                                <option value="非定向">非定向</option>
                            </select>
                            <#--<input type="text" class="form-control" id="pyfs" name="pyfs" value="${stuInfo.pyfs!''}" placeholder="" autocomplete="off">-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="wpdw" class="col-sm-2 control-label">委培单位<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="wpdw" name="wpdw" value="${stuInfo.wpdw!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="knslb" class="col-sm-2 control-label">困难生类别<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="knslb" name="knslb" data-val="${stuInfo.knslb!''}">
                                <option value="">请选择</option>
                                <option value="家庭困难">家庭困难</option>
                                <option value="家庭困难和残疾">家庭困难和残疾</option>
                                <option value="就业困难">就业困难</option>
                                <option value="就业困难、家庭困难和残疾">就业困难、家庭困难和残疾</option>
                                <option value="就业困难和家庭困难">就业困难和家庭困难</option>
                                <option value="就业困难和残疾">就业困难和残疾</option>
                                <option value="残疾">残疾</option>
                                <option value="非困难生">非困难生</option>
                            </select>
                            <#--<input type="text" class="form-control" id="knslb" name="knslb" value="${stuInfo.knslb!''}" placeholder="" autocomplete="off">-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sfslb" class="col-sm-2 control-label">师范生类别<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="sfslb" name="sfslb" data-val="${stuInfo.sfslb!''}">
                                <option value="">请选择</option>
                                <option value="免费师范生">免费师范生</option>
                                <option value="普通师范生">普通师范生</option>
                                <option value="非师范生">非师范生</option>
                            </select>
                            <#--<input type="text" class="form-control" id="sfslb" name="sfslb" value="${stuInfo.sfslb!''}" placeholder="" autocomplete="off">-->
                        </div>
                        <label for="sjh" class="col-sm-2 control-label">手机号码<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="sjh" name="sjh" value="${stuInfo.sjh!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qqhm" class="col-sm-2 control-label">qq号码<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="qqhm" name="qqhm" value="${stuInfo.qqhm!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="jtdh" class="col-sm-2 control-label">家庭电话<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="jtdh" name="jtdh" value="${stuInfo.jtdh!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="jtyb" class="col-sm-2 control-label">家庭邮编<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="jtyb" name="jtyb" value="${stuInfo.jtyb!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xlcc" class="col-sm-2 control-label">学历层次<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="xlcc" name="xlcc" data-val="${stuInfo.xlcc!''}">
                                <option value="">请选择</option>
                                <option value="博士生毕业">博士生毕业</option>
                                <option value="博士生结业">博士生结业</option>
                                <option value="硕士生毕业">硕士生毕业</option>
                                <option value="硕士生结业">硕士生结业</option>
                                <option value="本科生毕业">本科生毕业</option>
                                <option value="本科生结业">本科生结业</option>
                                <option value="专科生毕业">专科生毕业</option>
                                <option value="专科生毕业">专科生毕业</option>
                            </select>
                            <#--<input type="text" class="form-control" id="xlcc" name="xlcc" value="${stuInfo.xlcc!''}" placeholder="" autocomplete="off">-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="yx" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="yx" name="yx" value="${stuInfo.yx!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="zyfx" class="col-sm-2 control-label">专业方向：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zyfx" name="zyfx" value="${stuInfo.zyfx!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="xqda" class="col-sm-2 control-label">入学前档案所在单位：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xqda" name="xqda" value="${stuInfo.xqda!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="sfrx" class="col-sm-2 control-label">档案是否转入学校：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="sfrx" name="sfrx" data-val="${stuInfo.sfrx!''}">
                                <option value="">请选择</option>
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hkrx" class="col-sm-2 control-label">户口是否转入学校：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="hkrx" name="hkrx" data-val="${stuInfo.hkrx!''}">
                                <option value="">请选择</option>
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                        <label for="hkpcs" class="col-sm-2 control-label">入学前户口所在地派出所：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="hkpcs" name="hkpcs" value="${stuInfo.hkpcs!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 text-center">
                            <button type="submit" class="btn btn-default btn-common green">保存</button>
                        </div>
                    </div>
                </form>
                <input type="hidden" value="${stuInfo.owid!''}" id="owid" />
                <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>

            </div>
        </div>
    </div>

</div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${base}/js/laydate/laydate.js" type="text/javascript"></script>
<script src="${base}/js/stuSyxx.js" type="text/javascript"></script>
</body>

</html>