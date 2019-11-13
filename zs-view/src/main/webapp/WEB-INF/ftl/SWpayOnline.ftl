<!--
    作者：2515421994@qq.com
    时间：2019-10-26
    描述：初审结果/在线缴费
-->
<link rel="stylesheet" href="${base}/js/laydate/theme/default/laydate.css" />
<#if (processState==6) >
    <#assign tip="恭喜你，报名表初审已通过。缴费证明图片已提交，请耐心等耐审核！"/>
</#if>
<#if (processState>6) >
    <#assign tip="恭喜你，报名表初审已通过，缴费审核已通过。"/>
</#if>
<#if (processState<6) >
    <#if (processState ==5 && rePayMess?? && rePayMess !="")>
            <#assign tip="恭喜你，报名表初审通过。缴费已退回，退回理由："+rePayMess />
        <#else>
            <#assign tip="恭喜你，报名表初审通过。缴费完成后将分配面试时间。缴费成功后，请上传缴费成功证明图片。"/>
    </#if>
</#if>
<#include "com/SWtip.ftl">
<div class="pay-online row mt20">
    <ul class="col-sm-offset-1">
        <li>
            <p class="contact-wrap_title active">缴费说明</p>
            <div class="pay-online_ctxt">
                <h5>${payMess!""}</h5>
                <h5>请点击前往缴费地址：
                    <a class="link-url" href="${payUrl!""}" target="_blank"><span class="glyphicon glyphicon-hand-up"></span>${payUrl!""}</a>
                </h5>
            </div>
        </li>

        <li>
            <p class="contact-wrap_title">上传缴费证明</p>
            <div class="pay-online_ctxt">
                <h5 style="margin-bottom: 20px;">如已完成在线缴费，请在此处选择缴费时间，并上传缴费成功证明图片</h5>
                <div class="form-horizontal">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label label-select_wrap">
                            缴费时间
                            <span class="red">*</span>
                        </label>
                        <div class="col-sm-10">
                            <#if payTime??>
                                <input type="text" class="form-control" id="payTime" name="payTime" ${(processState??&&processState>5)?string('disabled','')} value="${payTime?substring(0,10)!""}" placeholder="请选择时间" autocomplete="off">
                                <#else>
                                    <input type="text" class="form-control" id="payTime" name="payTime" ${(processState??&&processState>5)?string('disabled','')} value="" placeholder="请选择时间" autocomplete="off">
                            </#if>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label label-select_wrap">
                            缴费证明图片
                            <span class="red">*</span>
                        </label>
                        <div class="col-sm-10">
                            <div class="upimg-wrap" typeNum="1">
                                <div class="file-btn_wrap">
                                    <#if payProveImg??>
                                            <img src="${imagePath}${payProveImg}" class="up-btn_img img-full"/>
                                        <#else>
                                            <img src="${base}/img/img-up.png" class="up-btn_img"/>
                                    </#if>
                                    <input type="file" class="file-btn" value="" class="file1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                                </div>
                                <label class="uploadlabel" for="file">上传缴费成功证明图片</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </li>

        <#if (processState>5)>
            <li>
                <p class="contact-wrap_title ${(processState>6)?string('active','')}">${(processState==6)?string('等待缴费确认','已缴费')}</p>
            </li>
        </#if>
    </ul>
    <#--button  start -->
    <#if (processState==5)>
        <div class="form-group">
            <div class="col-sm-12 text-center mt20">
                <button id="saveProveImg" class="btn btn-default btn-common yellow">确认上传</button>
            </div>
        </div>
    </#if>
    <#--button  end -->
</div>
<script src="${base}/js/laydate/laydate.js" type="text/javascript"></script>
<script>
    var payProveImg = "${payProveImg!""}";//缴费证明图片
    var payTime = "";//缴费时间
    //init选择支付年月日 5待缴费
    <#if processState??&&processState==5>
        laydate.render({
            elem: '#payTime'
        });
        <#elseif (processState??&&processState>5)>
            $(".file-btn").hide();
            $(".uploadlabel").attr("for","").html("已上传");
            $(".pay-online li").eq(1).find(".contact-wrap_title").addClass("active");
    </#if>

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
                    payProveImg = d.bean.fileName;
                    $(".pay-online li").eq(1).find(".contact-wrap_title").addClass("active");
                    walert("缴费证明图片上传成功,请等待审核")
                });
            }
            reader.readAsDataURL(file);
        }
    });

    //点击按钮提交 缴费证明图片和缴费时间
    $("#saveProveImg").click(function () {
        payTime = $("#payTime").val();
        if(payTime==""){
            walert("请先选择缴费时间");
            return;
        }
        if(payProveImg==""){
            walert("请上传缴费证明图片");
            return;
        }
        var index = layer.confirm('确认提交后将不可再修改！', {
            btn: ['确认','取消'] //按钮
            ,title:"确认提交"
        }, function(){
            layer.close(index);
            imgAjax();
        }, function(){
        });

    })

    function imgAjax() {
        var data = {
            "applyOwid":formOwid,//申请表owid在trinityEnrollment取得
            "jfpzZp":payProveImg,
            "jfsj":payTime
        }
        ajax("zustswyt/bckjBizBm/submitJft", data, function (data) {
            if(data.backCode==0){
                location.reload();
            }else{
                walert(data.errorMess)
            }
        })
    }
</script>



