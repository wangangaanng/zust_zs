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
                        <label for="qyTysh" class="col-sm-2 control-label">学号<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <#if stuInfo.xh??>
                                <input type="text" class="form-control" id="xh" name="xh" value="${stuInfo.xh!''}" disabled="disabled" placeholder="" autocomplete="off">
                            <#else>
                               <input type="text" class="form-control" id="xh" name="xh" value=""  placeholder="" autocomplete="off">
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
                            <input type="text" class="form-control" id="mz" name="mz" value="${stuInfo.mz!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="zzmm" class="col-sm-2 control-label">政治面貌<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zzmm" name="zzmm" value="${stuInfo.zzmm!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sfzh" class="col-sm-2 control-label">身份证号<span class="red">*</span>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sfzh" name="sfzh" value="${stuInfo.sfzh!''}" placeholder="" autocomplete="off">
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
                            <input type="text" class="form-control" id="syd" name="syd" value="${stuInfo.syd!''}" placeholder="" autocomplete="off">
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
                            <input type="text" class="form-control" id="cxsy" name="cxsy" value="${stuInfo.cxsy!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xqda" class="col-sm-2 control-label">入学前档案所在单位：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xqda" name="xqda" value="${stuInfo.xqda!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sfrx" class="col-sm-2 control-label">档案是否转入学校：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="sfrx" name="sfrx" data-val="${stuInfo.sfrx!''}">
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
                        <label for="ksh" class="col-sm-2 control-label">考生号<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="ksh" name="ksh" value="${stuInfo.ksh!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="hkrx" class="col-sm-2 control-label">户口是否转入学校：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="hkrx" name="hkrx" data-val="${stuInfo.hkrx!''}">
                                <option value="">请选择</option>
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="xz" class="col-sm-2 control-label">学制<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xz" name="xz" value="${stuInfo.xz!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="ssxx" class="col-sm-2 control-label">所属学校<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="ssxx" name="ssxx" value="${stuInfo.ssxx!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ssxy" class="col-sm-2 control-label">所属学院<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="ssxy" name="ssxy" value="${stuInfo.ssxy!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xxzy" class="col-sm-2 control-label">学校专业<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xxzy" name="xxzy" value="${stuInfo.xxzy!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="zyfx" class="col-sm-2 control-label">专业方向：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zyfx" name="zyfx" value="${stuInfo.zyfx!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="szbj" class="col-sm-2 control-label">所在班级<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="szbj" name="szbj" value="${stuInfo.szbj!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pyfs" class="col-sm-2 control-label">培养方式<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="pyfs" name="pyfs" value="${stuInfo.pyfs!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="wpdw" class="col-sm-2 control-label">委培单位<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="wpdw" name="wpdw" value="${stuInfo.wpdw!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="knslb" class="col-sm-2 control-label">困难生类别<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="knslb" name="knslb" value="${stuInfo.knslb!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="sfslb" class="col-sm-2 control-label">师范生类别<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="sfslb" name="sfslb" value="${stuInfo.sfslb!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sjhm" class="col-sm-2 control-label">手机号码<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="sjhm" name="sjhm" value="${stuInfo.sjhm!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="dzyx" class="col-sm-2 control-label">电子邮箱<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="dzyx" name="dzyx" value="${stuInfo.dzyx!''}" placeholder="" autocomplete="off">
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
                            <input type="text" class="form-control" id="xlcc" name="xlcc" value="${stuInfo.xlcc!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 text-center">
                            <button type="submit" class="btn btn-default btn-common green">保存</button>
                        </div>
                    </div>
                </form>
                <input type="hidden" value="${stuInfo.xb!''}" id="sel1" />
                <input type="hidden" value="${stuInfo.sfrx!''}" id="sel2" />
                <input type="hidden" value="${stuInfo.hkrx!''}" id="sel3" />
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