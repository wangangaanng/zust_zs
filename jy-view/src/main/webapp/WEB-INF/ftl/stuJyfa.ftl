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
                        <label for="xsxh" class="col-sm-2 control-label">学号：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xsxh" name="xsxh" value="${result.xsxh!''}" disabled="disabled" placeholder="" autocomplete="off">
                        </div>
                        <label for="xm" class="col-sm-2 control-label">姓名<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xm" name="xm" value="${result.xm!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sfz" class="col-sm-2 control-label">身份证号<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="sfz" name="sfz" value="${result.sfz!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xb" class="col-sm-2 control-label">性别<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="xb" name="xb" data-val="${result.xb!''}">
                                <option value="">请选择</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="xxmc" class="col-sm-2 control-label">学校<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xxmc" name="xxmc" value="${result.xxmc!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="xsxy" class="col-sm-2 control-label">学院<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xsxy" name="xsxy" value="${result.xsxy!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="xszy" class="col-sm-2 control-label">专业<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="xszy" name="xszy" value="${result.xszy!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="zyfx" class="col-sm-2 control-label">专业方向<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="zyfx" name="zyfx" value="${result.zyfx!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pyfs" class="col-sm-2 control-label">培养方式<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="pyfs" name="pyfs" value="${result.pyfs!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="syd" class="col-sm-2 control-label">生源地<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="syd" name="syd" value="${result.syd!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sjh" class="col-sm-2 control-label">手机号码<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="sjh" name="sjh" value="${result.sjh!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="byxl" class="col-sm-2 control-label">学历：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="byxl" name="byxl" value="${result.byxl!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 col-sm-offset-1 text-left f-title">毕业去向信息</label>
                    </div>
                    <div class="form-group">
                        <label for="byqx" class="col-sm-2 control-label">毕业去向<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="byqx" name="byqx" value="${result.byqx!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="yrdwmc" class="col-sm-2 control-label">用人单位名称<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="yrdwmc" name="yrdwmc" value="${result.yrdwmc!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="yrdwdm" class="col-sm-2 control-label">用人单位代码<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="yrdwdm" name="yrdwdm" value="${result.yrdwdm!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="yrdwxz" class="col-sm-2 control-label">用人单位性质<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="yrdwxz" name="yrdwxz" value="${result.yrdwxz!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dwlbmc" class="col-sm-2 control-label">单位行业<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="dwlbmc" name="dwlbmc" value="${result.dwlbmc!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="dwszdmc" class="col-sm-2 control-label">用人单位所在地<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="dwszdmc" name="dwszdmc" value="${result.dwszdmc!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dwlxr" class="col-sm-2 control-label">单位联系人：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="dwlxr" name="dwlxr" value="${result.dwlxr!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="dwdh" class="col-sm-2 control-label">单位联系电话：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="dwdh" name="dwdh" value="${result.dwdh!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="gzzwlbmc" class="col-sm-2 control-label">工作职位类别<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="gzzwlbmc" name="gzzwlbmc" value="${result.gzzwlbmc!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="sfzydk" class="col-sm-2 control-label">专业是否对口<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="sfzydk" name="sfzydk" data-val="${result.sfzydk!''}">
                                <option value="">请选择</option>
                                <option value="1">对口</option>
                                <option value="2">不对口</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 col-sm-offset-1 text-left f-title">报到证信息</label>
                    </div>
                    <div class="form-group">
                        <label for="bdzqflbmc" class="col-sm-2 control-label">报到证签发类别：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bdzqflbmc" name="bdzqflbmc" value="${result.bdzqflbmc!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="bdzdwmc" class="col-sm-2 control-label">报到证签往单位：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bdzdwmc" name="bdzdwmc" value="${result.bdzdwmc!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bdzszdmc" class="col-sm-2 control-label">签往单位所在地：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bdzszdmc" name="bdzszdmc" value="${result.bdzszdmc!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="bdkssj" class="col-sm-2 control-label">报到开始时间：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bdkssj" name="bdkssj" value="${result.bdkssj!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bdjssj" class="col-sm-2 control-label">报到结束时间：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bdjssj" name="bdjssj" value="${result.bdjssj!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="sfdydwbdz" class="col-sm-2 control-label">是否打印备注：</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="sfdydwbdz" name="sfdydwbdz" data-val="${result.sfdydwbdz!''}">
                                <option value="">请选择</option>
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bdzbz" class="col-sm-2 control-label">报到证备注：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="bdzbz" name="bdzbz" value="${result.bdzbz!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 col-sm-offset-1 text-left f-title">档案信息</label>
                    </div>
                    <div class="form-group">
                        <label for="datddw" class="col-sm-2 control-label">档案投递单位<span class="red">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="datddw" name="datddw" value="${result.datddw!''}" placeholder="" autocomplete="off">
                        </div>
                        <label for="datddh" class="col-sm-2 control-label">档案投递电话：</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="datddh" name="datddh" value="${result.datddh!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="datdxxdz" class="col-sm-2 control-label">档案投递详细地址：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="datdxxdz" name="datdxxdz" value="${result.datdxxdz!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hkqydz" class="col-sm-2 control-label">户口迁移地址：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="hkqydz" name="hkqydz" value="${result.hkqydz!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bzone" class="col-sm-2 control-label">备注一：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bzone" name="bzone" value="${result.bzone!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bztwo" class="col-sm-2 control-label">备注二：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bztwo" name="bztwo" value="${result.bztwo!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bzthree" class="col-sm-2 control-label">备注三：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bzthree" name="bzthree" value="${result.bzthree!''}" placeholder="" autocomplete="off">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12 text-center">
                            <button type="submit" class="btn btn-default btn-common green">保存</button>
                        </div>
                    </div>
                </form>
                <input type="hidden" value="${result.sfzydk!''}" id="sel1" />
                <input type="hidden" value="${result.sfdydwbdz!''}" id="sel2" />
                <input type="hidden" value="${result.xb!''}" id="sel3" />
                <#--<input type="hidden" value="${result.owid!''}" id="owid" />-->

                <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>

            </div>
        </div>
    </div>

</div>


<#include "com/footer.ftl">
<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${base}/js/laydate/laydate.js" type="text/javascript"></script>
<script src="${base}/js/stuJyfa.js" type="text/javascript"></script>
</body>

</html>