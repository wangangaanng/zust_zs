<!--
    作者：2515421994@qq.com
    时间：2019-10-25
    描述：基本信息1
-->
<#--步骤条1 start-->
<#include "com/SWsteps.ftl">
<#--步骤条 end-->

<#--步骤基本信息1 start-->
<form class="form-horizontal" id="basicForm" method="" action="" target="bcFrame" >

    <div class="form-group pr">
        <label for="qyGsjs" class="col-sm-2 control-label label-select_wrap  col-sm-offset-1">
            <select class="form-control label-select" id="confrimType">
                <option value="1" selected>身份证照片</option>
                <option value="2">户籍证明</option>
            </select>
            <span class="red">*</span>
        </label>
        <div class="col-sm-8" style="padding: 0px;">
            <div id="idCard">
                <div style="text-align: center;" class="col-sm-4">
                    <div class="upimg-wrap" typeNum="2">
                        <div class="file-btn_wrap">
                            <img src="${base}/img/img-up.png" class="up-btn_img" id="sfzzmImg">
                        </div>
                        <input type="file"   class="file-btn" data-type="2"  value="" name="file" accept="image/jpeg,image/jpg,image/png,image/svg">
                        <label class="uploadlabel"  for="file">上传身份证正面</label>
                    </div>
                </div>
                <div style="text-align: center;" class="col-sm-4">
                    <div class="upimg-wrap"  typeNum="3">
                        <div class="file-btn_wrap">
                            <img src="${base}/img/img-up.png" class="up-btn_img" id="sfzfmImg">
                        </div>
                        <input type="file" class="file-btn" value="" name="file" accept="image/jpeg,image/jpg,image/png,image/svg">
                        <label class="uploadlabel" for="file">上传身份证反面</label>
                    </div>
                </div>
                <div class="col-sm-4 has-error">
                    <p class="help-block error-tip"></p>
                </div>
            </div>
            <div id="household" style="display: none">
                <div style="text-align: center;" class="col-sm-4">
                    <div class="upimg-wrap" typeNum="1">
                        <div class="file-btn_wrap">
                            <img src="${base}/img/img-up.png" class="up-btn_img" id="hjzmImg">
                        </div>
                        <input type="file"   class="file-btn" data-type="1"  value="" name="file" accept="image/jpeg,image/jpg,image/png,image/svg">
                        <label class="uploadlabel"  for="file">户籍证明</label>
                    </div>
                </div>
                <div class="col-sm-4 has-error">
                    <p class="help-block error-tip"></p>
                </div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="xm" class="col-sm-2 control-label  col-sm-offset-1">姓名<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="xm" id="xm"  value="" placeholder="" >
        </div>
    </div>
    <div class="form-group">
        <label for="xb" class="col-sm-2 control-label  col-sm-offset-1">性别<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <select class="form-control" name="xb" id="xb">
                <option value="1" selected>男</option>
                <option value="2" >女</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="sfzh" class="col-sm-2 control-label  col-sm-offset-1">身份证号<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="sfzh" id="sfzh"  value="" placeholder="请输入身份证号" >
        </div>
    </div>
    <div class="form-group">
        <label for="mz" class="col-sm-2 control-label  col-sm-offset-1">名族<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="mz"  name="mz" value="" placeholder="请输入名族" >
        </div>
    </div>
    <div class="form-group">
        <label for="jtzz" class="col-sm-2 control-label  col-sm-offset-1">通讯地址<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="jtzz" name="jtzz" placeholder="请输入通讯地址"  value="">
        </div>
    </div>
    <div class="form-group">
        <label for="lxdh" class="col-sm-2 control-label  col-sm-offset-1">联系电话<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="lxdh" name="lxdh" placeholder="请输入联系电话"  value="">
        </div>
    </div>

    <div class="form-group">
        <label for="yx" class="col-sm-2 control-label  col-sm-offset-1">电子邮箱<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="yx" name="yx" placeholder="请输入电子邮箱" autocomplete="off" value="">
        </div>
    </div>
    <div class="form-group">
        <label for="qyGsxz" class="col-sm-2 control-label  col-sm-offset-1">QQ号<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="qq" name="qq" placeholder="请输入QQ号"  value="">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-12 text-center">
            <button type="submit" id="saveBasic" class="btn btn-default btn-common yellow">下一步，完善联系人</button>
        </div>
    </div>

    <#--身份证正反面和户籍证明-->
    <input name="sfzzm" value="" id="sfzzm" type="text" style="display: none;"/>
    <input name="sfzfm" value="" id="sfzfm" type="text" style="display: none;"/>
    <input name="hjzm" value="" id="hjzm" type="text" style="display: none;"/>
</form>
<#--<iframe id="bcFrame" name="bcFrame" src="about:blank" style="display:none;"></iframe>-->
<#--步骤基本信息1 end-->

<#--联系人2 start-->
<#include "SWinfoContactor.ftl">
<#--联系人2 end-->

<#--学考等第3 start-->
<#include "SWInfoGrade.ftl">
<#--学考等第3 end-->

<#--选考信息4 start-->
<#include "SWInfoSelect.ftl">
<#--选考信息4 end-->
<script src="${base}/js/swyt/SWInfoBasic.js"></script>

