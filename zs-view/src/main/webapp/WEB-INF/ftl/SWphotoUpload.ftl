<!--
    作者：2515421994@qq.com
    时间：2019-10-26
    描述：拍照上传
-->
<#assign tip="请考生上传：报名表签字和承诺书签字"/>
<#include "com/SWtip.ftl">
<form class="form-horizontal basic-from" id="selectForm" method="" action="" target="baFrame">
    <div class="form-group">
        <label for="qyFrsfz" class="col-sm-2 control-label  col-sm-offset-1">报名表签字上传<span class="red">*</span>：</label>
        <div class="col-sm-4">
            <div class="upimg-wrap">
                <div class="file-btn_wrap">
                    <img src="${base}/img/img-up.png" class="up-btn_img"/>
                    <input type="file" class="file-btn" value="" class="file1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                </div>
                <label class="uploadlabel" for="file">上传签字报名表</label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="qyFrsfz" class="col-sm-2 control-label  col-sm-offset-1">承诺书签字上传<span class="red">*</span>：</label>
        <div class="col-sm-4">
            <div class="upimg-wrap">
                <div class="file-btn_wrap">
                    <img src="${base}/img/img-up.png" class="up-btn_img"/>
                    <input type="file" class="file-btn" value="" class="file1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                </div>
                <label class="uploadlabel" for="file">上传签字承诺书</label>
            </div>
        </div>
    </div>

<#--button  start -->
    <div class="form-group">
        <div class="col-sm-4 text-center col-sm-offset-2">
            <button type="submit" id="saveBasic" class="btn btn-default btn-common yellow">确认上传</button>
        </div>
    </div>
<#--button  end -->
</form>



