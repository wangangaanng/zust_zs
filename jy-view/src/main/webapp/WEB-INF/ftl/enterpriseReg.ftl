<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
</head>
<style>

    .form-group {
        margin-right: 280px !important;
        position: relative;
    }
</style>

<body>
<#include "com/header.ftl">
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i>
                当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a href="${base}/">首页</a></li>
                <li class="active">企业注册</li>
            </ol>
        </div>
        <div class="content-form">
            <form class="form-horizontal" id="registerForm" method="" action="" target="rfFrame">
                <div class="form-group">
                    <label for="qyTysh" class="col-sm-2 control-label">填写代码<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="qyTysh" name="qyTysh" placeholder="" autocomplete="off">
                    </div>
                    <div class="col-sm-2 upload-img">
                        <input type="file" value="" class="file1" data-type="1" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                        <img src="${base}/img/upload.png" id="yyzz" alt="" />
                    </div>
                    <div class="upload-sm">上传营业执照自动识别统一信用代码<span class="red">*</span></div>
                </div>
                <div class="form-group">
                    <label for="qyFrsfz" class="col-sm-2 control-label">法人身份证号<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="qyFrsfz" name="qyFrsfz" placeholder="" autocomplete="off">
                    </div>
                    <div class="col-sm-2 upload-img">
                        <input type="file" value="" class="file1" data-type="2" name="file1" id="file1" accept="image/jpeg,image/jpg,image/png,image/svg" />
                        <img src="${base}/img/upload.png" id="sfz" alt="" />
                    </div>
                    <div class="upload-sm">上传法人身份证正面照，自动识别、填充</div>
                </div>
                <div class="form-group">
                    <label for="qyFrdbxm" class="col-sm-2 control-label">法人姓名<span class="red">*</span>：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qyFrdbxm" name="qyFrdbxm" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qymc" class="col-sm-2 control-label">公司名称<span class="red">*</span>：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qymc" name="qymc" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyZczj" class="col-sm-2 control-label">注册资本<span class="red">*</span>：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qyZczj" name="qyZczj" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyProv" class="col-sm-2 control-label">所在省份<span class="red">*</span>：</label>
                    <div class="col-sm-2">
                        <select class="form-control" onchange="getCity()" name="qyProv" id="qyProv">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="qyCity" class="col-sm-2 control-label">所在市<span class="red">*</span>：</label>
                    <div class="col-sm-2">
                        <select class="form-control" onchange="getArea()" name="qyCity" id="qyCity">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="qyArea" class="col-sm-2 control-label">所在区<span class="red">*</span>：</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="qyArea" id="qyArea">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qydz" class="col-sm-2 control-label">公司地址<span class="red">*</span>：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qydz" name="qydz" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyLxr" class="col-sm-2 control-label">联系人<span class="red">*</span>：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="qyLxr" name="qyLxr" placeholder="" autocomplete="off">
                    </div>
                    <label for="qyLxrdh" class="col-sm-2 control-label">手机<span class="red">*</span>：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="qyLxrdh" name="qyLxrdh" placeholder="" autocomplete="off">
                    </div>
                </div>

                <div class="form-group">
                    <label for="qyYx" class="col-sm-2 control-label">邮箱<span class="red">*</span>：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="qyYx" name="qyYx" placeholder="" autocomplete="off">
                    </div>
                    <label for="qylxfs" class="col-sm-2 control-label">固定电话：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="qylxfs" name="qylxfs" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyGsxz" class="col-sm-2 control-label">公司性质<span class="red">*</span>：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyGsxz" name="qyGsxz">
                            <option value="">请选择</option>
                            <#if qyGsxz??>
                            <#list qyGsxz as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyHylb" class="col-sm-2 control-label">行业类别<span class="red">*</span>：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyHylb" name="qyHylb">
                            <option value="">请选择</option>
                            <#if qyHylb??>
                            <#list qyHylb as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyGsgm" class="col-sm-2 control-label">公司规模<span class="red">*</span>：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyGsgm" name="qyGsgm">
                            <option value="">请选择</option>
                            <#if qyGsgm??>
                            <#list qyGsgm as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                            </#if>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="qyGsjs" class="col-sm-2 control-label">公司介绍<span class="red">*</span>：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="qyGsjs" name="qyGsjs" rows="10"></textarea>
                    </div>
                </div>

                <input type="hidden" id="qyYyzzzp" name="qyYyzzzp" />
                <input type="hidden" id="qyFrsfzzp" name="qyFrsfzzp" />
                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <button type="submit" class="btn btn-default btn-common green">注册</button>
                    </div>
                </div>
            </form>

            <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>
        </div>
    </div>

<#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${base}/js/city1.js" type="text/javascript"></script>

    <script>
        var provice=[];
        var city=[];
        var pindex=0;
        var cindex=0;
        var _cityData=[];
        cityData3.forEach(function(e) {
            provice.push(e.text)
            city.push(e.children)

            $("#qyProv").append("<option value='"+e.text+"'>"+e.text+"</option>")
        });

        function getCity() {
            $("#qyCity").html("<option value=''>请选择</option>")
            $("#qyArea").html("<option value=''>请选择</option>")
            pindex=parseInt($("#qyProv option:selected").index())-1;
            if(pindex>-1){
                _cityData=city[pindex]
                _cityData.forEach(function(e) {
                    $("#qyCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
                });

            }

        }

        function getArea() {
            $("#qyArea").html("<option value=''>请选择</option>")
            cindex=parseInt($("#qyCity option:selected").index())-1;
            if(cindex>-1){
                var _areaData=_cityData[cindex].children
                _areaData.forEach(function(e) {
                    $("#qyArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
                });

            }
        }

        $(document).ready(function () {

            $(".file1").change(function (e) {
                var file = e.target.files[0] || e.dataTransfer.files[0];
                console.log(this.getAttribute("data-type"))
                var type=this.getAttribute("data-type")
                // $('#photoCover').val(document.getElementById("file").files[0].name);
                if (file) {
                    console.log("file.size",file.size)
                    if(file.size>2000000){
                        walert("图片过大，请选择2M以下的图片")
                        return
                    }
                    var reader = new FileReader();
                    reader.onload = function () {
                        console.log("file",file)

                        var fd = new FormData();
                        fd.append("file",file);
                        fd.append("method","zustcommon/common/picUpload")
                        if(type==1){
                            $("#yyzz").attr("src",this.result)
                            fd.append('data', JSON.stringify({
                                "type": 1
                            }));
                        }else if(type==2){
                            $("#sfz").attr("src",this.result)
                            fd.append('data', JSON.stringify({
                                "type": 2
                            }));
                        }

                        beginLoad()
                        $.ajax({
                            url: base+'/webAjax/picUpload',//uploadUrl,
                            type: "POST",
                            processData: false,
                            contentType: false,
                            data: fd,
                            success: function(d) {
                                // console.log(d);
                                finishLoad()
                                if(d.bean){
                                    if(type==1){
                                        if(d.bean["社会信用代码"]){
                                            if(d.bean["社会信用代码"].words){
                                                if(d.bean["社会信用代码"].words!="无"){
                                                    $("#qyTysh").val(d.bean["社会信用代码"].words)
                                                }
                                            }
                                        }
                                        if(d.bean["单位名称"]){
                                            if(d.bean["单位名称"].words){
                                                if(d.bean["单位名称"].words!="无"){
                                                    $("#qymc").val(d.bean["单位名称"].words)
                                                }
                                            }
                                        }
                                        if(d.bean["地址"]){
                                            if(d.bean["地址"].words){
                                                if(d.bean["地址"].words!="无"){
                                                    $("#qydz").val(d.bean["地址"].words)
                                                }
                                            }
                                        }
                                        if(d.bean["注册资本"]){
                                            if(d.bean["注册资本"].words){
                                                if(d.bean["注册资本"].words!="无"){
                                                    $("#qyZczj").val(d.bean["注册资本"].words)
                                                }
                                            }
                                        }
                                        if(d.bean.fileName){
                                            walert("上传成功")
                                            $("#qyYyzzzp").val(d.bean.fileName)
                                        }
                                    }else if(type==2){
                                        if(d.bean.image_status){
                                            var image_status=d.bean.image_status;
                                            if(image_status=="reversed_side"){
                                                walert("身份证正反面颠倒，请重新选择")
                                            }else if(image_status=="non_idcard"){
                                                walert("上传的图片中不包含身份证，请重新选择")
                                            }else if(image_status=="blurred"){
                                                walert("身份证模糊，请重新选择")
                                            }else if(image_status=="other_type_card"){
                                                walert("上传照片为其他类型证照，请重新选择")
                                            }else if(image_status=="over_exposure"){
                                                walert("身份证关键字段反光或过曝，请重新选择")
                                            }else if(image_status=="over_dark"){
                                                walert("身份证欠曝（亮度过低），请重新选择")
                                            }
                                        }

                                        if(d.bean["姓名"]){
                                            if(d.bean["姓名"].words){
                                                if(d.bean["姓名"].words!="无"){
                                                    $("#qyFrdbxm").val(d.bean["姓名"].words)
                                                }
                                            }
                                        }
                                        if(d.bean["公民身份号码"]){
                                            if(d.bean["公民身份号码"].words){
                                                if(d.bean["公民身份号码"].words!="无"){
                                                    $("#qyFrsfz").val(d.bean["公民身份号码"].words)
                                                }
                                            }
                                        }
                                        if(d.bean.fileName){
                                            walert("上传成功")
                                            $("#qyFrsfzzp").val(d.bean.fileName)
                                        }
                                    }

                                }else{
                                    walert("上传失败")
                                }
                            },
                            fail:function () {
                                finishLoad()
                            }
                        });
                    }

                    reader.readAsDataURL(file);
                }
            });

            jQuery.validator.addMethod("isMobile", function(value, element) {
                var length = value.length;
                var mobile = /^1[345789]\d{9}$/;/*/^1(3|4|5|7|8)\d{9}$/*/
                return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请正确填写您的手机号码");

            jQuery.validator.addMethod("isIdCardNo", function (value, element){
                return this.optional(element) || isIdCardNo(value);
            },"请正确输入您的身份证号码");

            $("#registerForm").validate({
                rules: {
                    qyTysh:"required",
                    qyFrsfz:{
                        required: true,
                        isIdCardNo: true
                    },
                    qyFrdbxm:"required",
                    qymc:"required",
                    qyZczj:"required",
                    qyProv:"required",
                    qyCity:"required",
                    qyArea:"required",
                    qydz:"required",
                    qyLxr:"required",
                    qyLxrdh:{
                        required: true,
                        isMobile: true
                    },
                    qyYx:{
                        required: true,
                        email: true
                    },
                    qyGsxz:"required",
                    qyHylb:"required",
                    qyGsgm:"required",
                    qyGsjs:"required",
                },
                messages: {
                    qyTysh: "请填写",
                    qyFrsfz:  {
                        required: "请填写",
                        isIdCardNo: "请填写正确身份证号码"
                    },
                    qyFrdbxm: "请填写",
                    qymc: "请填写",
                    qyZczj:"请填写",
                    qyProv: "请选择",
                    qyCity: "请选择",
                    qyArea: "请选择",
                    qydz: "请填写",
                    qyLxr: "请填写",
                    qyLxrdh:  {
                        required: "请填写",
                        email: "请填写正确的11位手机号码"
                    },
                    qyYx: {
                        required: "请填写",
                        email: "请填写正确电子邮箱"
                    },
                    qyGsxz: "请选择",
                    qyHylb: "请选择",
                    qyGsgm: "请选择",
                    qyGsjs: "请填写",
                },
                errorElement: "em",
                errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( "help-block" );

                    if ( element.prop( "type" ) === "checkbox" ) {
                        error.insertAfter( element.parent( "label" ) );
                    } else {
                        error.insertAfter( element );
                    }
                },
                highlight: function ( element, errorClass, validClass ) {
                    $( element ).parent().addClass( "has-error" ).removeClass( "has-success" );
                },
                unhighlight: function (element, errorClass, validClass) {
                    $( element ).parent().removeClass( "has-error" );
                }
            });


        })

        $.validator.setDefaults({
            submitHandler: function () {
                companyRegister()
            }
        });

        function companyRegister() {
            if(!$("#qyYyzzzp").val()){
                walert("请上传营业执照");
                return;
            }
            $(".green").attr("disabled","disabled");
            var jsonObj = $("#registerForm").serializeObject()
            // console.log(jsonObj)
            ajax("zustjy/bckjBizQyxx/companyRegister", jsonObj, function (data) {
                if(data.backCode==0){
                    layer.open({
                        title:'提示',
                        closeBtn: 0,
                        content: '注册成功，待后台人员审核通过，便可登录。',
                        yes: function(index, layero){
                            window.location.href="${base}/";
                            layer.close(index);
                        }
                    });
                }else{
                    $(".green").removeAttr("disabled");
                    walert(data.errorMess)
                }
            })
        }

    </script>
</body>

</html>