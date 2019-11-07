<!--
    作者：2515421994@qq.com
    时间：2019-10-26
    描述：拍照上传
-->
<#assign tip="请考生上传：报名表签字和承诺书签字"/>
<#include "com/SWtip.ftl">
<div class="form-horizontal basic-from" id="picForm" method="" action="" target="baFrame">
    <div class="form-group">
        <label for="qyFrsfz" class="col-sm-2 control-label  col-sm-offset-1">报名表签字上传<span class="red">*</span>：</label>
        <div class="col-sm-4">
            <div class="upimg-wrap" typeNum="1">
                <div class="file-btn_wrap">
                    <img src="${base}/img/img-up.png" class="up-btn_img"/>
                    <input type="file" class="file-btn" value="" class="file1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                </div>
                <label class="uploadlabel" for="file">上传签字报名表</label>
            </div>
        </div>
        <div class="col-sm-4 has-error">
            <p class="help-block error-tip"></p>
        </div>
    </div>

    <div class="form-group">
        <label for="qyFrsfz" class="col-sm-2 control-label  col-sm-offset-1">承诺书签字上传<span class="red">*</span>：</label>
        <div class="col-sm-4">
            <div class="upimg-wrap" typeNum="2">
                <div class="file-btn_wrap">
                    <img src="${base}/img/img-up.png" class="up-btn_img"/>
                    <input type="file" class="file-btn" value="" class="file1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                </div>
                <label class="uploadlabel" for="file">上传签字承诺书</label>
            </div>
        </div>
        <div class="col-sm-4 has-error">
            <p class="help-block error-tip"></p>
        </div>
    </div>

<#--button  start -->
    <div class="form-group">
        <div class="col-sm-4 text-center col-sm-offset-2">
            <button  id="saveBasic" class="btn btn-default btn-common yellow">确认上传</button>
        </div>
    </div>
<#--button  end -->
</div>

<script>
    var bmbZp = "";//报名表照片
    var cnszp = "";//承诺书照片
    //提交报名表承诺书 表单验证触发保存基本信息
    $("#saveBasic").click(function () {
        if(!emptyCheck(bmbZp)){
            walert("请上传报名表")
            return;
        }
        if(!emptyCheck(cnszp)){
            walert("请上传承诺书照片")
            return;
        }
       var index = layer.confirm('确认提交，确认提交后将不可再修改！', {
            btn: ['确认','取消'] //按钮
        }, function(){
            layer.close(index);
            savePic();
        }, function(){
        });
    });

    function savePic() {
        var data = {
            "applyOwid":formOwid,//申请表owid在trinityEnrollment取得
            "cnszp":cnszp,
            "bmbZp":bmbZp
        }
        ajax("zustswyt/bckjBizBm/promise", data, function (data) {
            if(data.backCode==0){
                var data = data.bean;
                walert("提交成功");
                $(".file-btn").hide();
                $(".uploadlabel").html("已上传成功").attr("for"," ");
                $("#saveBasic").hide();
            }else{
                walert(data.errorMess)
            }
        })
    }
    //报名表签字上传
    $(".file-btn").change(function (e) {
        var $parents = $(this).parents(".upimg-wrap");
        imgType =  $parents.attr("typeNum");
        var file = e.target.files[0] || e.dataTransfer.files[0];
        if (file) {
            if(file.size>2000000){
                walert("图片过大，请选择2M以下的图片")
                return
            }
            var reader = new FileReader();
            reader.onload = function () {
                //图片临时显示
                $parents.find(".up-btn_img").attr("src",this.result);
                $parents.find(".up-btn_img").addClass("fullImg");
                //调用上传接口
                idOcr(imgType,file,function (d) {
                    switch(imgType){
                        case "1"://报名表
                            walert("报名表签字上传成功");
                            bmbZp = d.bean.fileName;
                            break;
                        case "2"://承诺书
                            walert("承诺书签字上传成功");
                            cnszp = d.bean.fileName;
                            break;
                    }
                });
            }
            reader.readAsDataURL(file);
        }
    });
</script>



