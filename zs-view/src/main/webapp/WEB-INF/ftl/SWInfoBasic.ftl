<!--
    作者：2515421994@qq.com
    时间：2019-10-25
    描述：基本信息1
-->
<#--步骤条1 start-->
<div class="jf-steps">
    <div class="jf-items">
        <div class="jf-item jf-active">1、基本信息
            <div class="jf-box"></div>
            <div class="jf-box1"></div>
            <div class="jf-box2"></div>
        </div>
        <div class="jf-item ">2、联系人
            <div class="jf-box"></div>
            <div class="jf-box1"></div>
            <div class="jf-box2"></div>
        </div>
        <div class="jf-item ">3、学考等第
            <div class="jf-box"></div>
            <div class="jf-box1"></div>
            <div class="jf-box2"></div>
        </div>
        <div class="jf-item ">4、选考信息</div>
    </div>
</div>
<#--步骤条 end-->

<#--步骤基本信息1 start-->
<form class="form-horizontal" id="basicForm" method="" action="" target="bcFrame" >
    <div class="form-group">
        <label for="qymc" class="col-sm-2 control-label  col-sm-offset-1">姓名<span class="red">*</span>：</label>
        <div class="col-sm-8">
        <#if (cInfo.qymc)??>
            <input type="text" class="form-control" id="qymc"  value="${cInfo.qymc}" placeholder="" autocomplete="off">
        <#else>
            <input type="text" class="form-control" id="qymc"  value="张小凡" placeholder="" autocomplete="off">
        </#if>
        </div>
    </div>
    <div class="form-group">
        <label for="qyTysh" class="col-sm-2 control-label  col-sm-offset-1">性别<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <select class="form-control">
                <option selected>男</option>
                <option>女</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="qyFrsfz" class="col-sm-2 control-label  col-sm-offset-1">身份证号<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="sfzh"  value="" placeholder="请输入身份证号" autocomplete="off">
        </div>
    </div>
    <div class="form-group">
        <label for="qyFrsfz" class="col-sm-2 control-label  col-sm-offset-1">名族<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="mz"  value="" placeholder="请输入名族" autocomplete="off">
        </div>
    </div>
    <div class="form-group">
        <label for="qydz" class="col-sm-2 control-label  col-sm-offset-1">通讯地址<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="txdx" name="qydz" placeholder="请输入通讯地址" autocomplete="off" value="">
        </div>
    </div>
    <div class="form-group">
        <label for="qyLxr" class="col-sm-2 control-label  col-sm-offset-1">联系电话<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="qyLxr" name="qyLxr" placeholder="请输入联系电话" autocomplete="off" value="">
        </div>
    </div>
    <div class="form-group">
        <label for="qyLxrdh" class="col-sm-2 control-label  col-sm-offset-1">手机<span class="red">*</span>：</label>
        <div class="col-sm-8">
        <#if (cInfo.qyLxrdh)??>
            <input type="text" class="form-control" id="qyLxrdh" name="qyLxrdh" placeholder="" autocomplete="off" value="${cInfo.qyLxrdh}">
        <#else>
            <input type="text" class="form-control" id="qyLxrdh" name="qyLxrdh" placeholder="请输入手机号" autocomplete="off" value="">
        </#if>
        </div>
    </div>

    <div class="form-group">
        <label for="qyYx" class="col-sm-2 control-label  col-sm-offset-1">电子邮箱<span class="red">*</span>：</label>
        <div class="col-sm-8">
        <#if (cInfo.qyYx)??>
            <input type="text" class="form-control" id="qyYx" name="qyYx" placeholder="" autocomplete="off" value="${cInfo.qyYx}">
        <#else>
            <input type="text" class="form-control" id="qyYx" name="qyYx" placeholder="请输入电子邮箱" autocomplete="off" value="">
        </#if>
        </div>
    </div>
    <div class="form-group">
        <label for="qyGsxz" class="col-sm-2 control-label  col-sm-offset-1">QQ号<span class="red">*</span>：</label>
        <div class="col-sm-8">
            <input type="text" class="form-control" id="qyYx" name="qyYx" placeholder="请输入QQ号" autocomplete="off" value="">
        </div>
    </div>

    <div class="form-group pr">
        <label for="qyGsjs" class="col-sm-2 control-label label-select_wrap  col-sm-offset-1">
            <select class="form-control label-select">
                <option value="1" selected>身份证照片</option>
                <option value="2">户籍证明</option>
            </select>
            <span class="red">*</span>
        </label>
        <div class="col-sm-8" style="padding: 0px;">
            <div style="text-align: center;" class="col-sm-4">
                <img src="${base}/img/upload.png" id="yyzz"  style="max-height: 180px;" />
                <label class="uploadlabel" for="file">点击上传身份证正面</label>
                <input type="file" style="display: none;" value="" class="file1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
            </div>
            <div style="text-align: center;" class="col-sm-4">
                <img src="${base}/img/upload.png" id="yyzz"  style="max-height: 180px;" />
                <label class="uploadlabel" for="file">点击上传身份证反面</label>
                <input type="file" style="display: none;" value="" class="file1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-12 text-center">
            <button type="submit" id="saveBasic" class="btn btn-default btn-common yellow">下一步，完善联系人</button>
        </div>
    </div>
</form>
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

<script>
    //基本信息保存
    $("#saveBasic").click(function () {
        $("#basicForm").hide();
        $("#contactForm").show();
        $(".jf-items .jf-item").eq(1).addClass("jf-active");
    });
</script>


