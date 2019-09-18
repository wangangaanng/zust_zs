<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
</head>
<style>

    .form-group {
        margin-right: 280px !important;
        position: relative;
    }
</style>

<body>
<#--<#include "com/header.ftl">-->
<div class="main">
    <div class="container">
        <div class="routes">
            <div class="location">
                <i></i>
                <!-- <img src="img/ic-home.png"> -->
                当前位置：
            </div>

            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li class="active">企业注册</li>
            </ol>
        </div>
        <div class="content-form">
            <form class="form-horizontal" id="registerForm" method="" action="" target="rfFrame">
                <div class="form-group">
                    <label for="qyTysh" class="col-sm-2 control-label">填写代码*：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="qyTysh" name="qyTysh" placeholder="" autocomplete="off">
                    </div>
                    <div class="col-sm-2 upload-img">
                        <input type="file" value="" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                        <img src="${base}/img/upload.png" id="yyzz" alt="" />
                    </div>
                    <div class="upload-sm">上传营业执照自动识别统一信用代码*</div>
                </div>
                <div class="form-group">
                    <label for="qyFrsfz" class="col-sm-2 control-label">法人身份证号*：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="qyFrsfz" name="qyFrsfz" placeholder="" autocomplete="off">
                    </div>
                    <div class="col-sm-2 upload-img">
                        <img src="${base}/img/upload.png" alt="" class="" />
                    </div>
                    <div class="upload-sm">上传法人身份证正面照，自动识别、填充</div>
                </div>
                <div class="form-group">
                    <label for="qymc" class="col-sm-2 control-label">公司名称*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qymc" name="qymc" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyProv" class="col-sm-2 control-label">所在省份*：</label>
                    <div class="col-sm-4">
                        <select class="form-control" onchange="getCity()" name="qyProv" id="qyProv">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="qyCity" class="col-sm-2 control-label">所在市*：</label>
                    <div class="col-sm-4">
                        <select class="form-control" onchange="getArea()" name="qyCity" id="qyCity">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyArea" class="col-sm-2 control-label">所在区*：</label>
                    <div class="col-sm-4">
                        <select class="form-control" name="qyArea" id="qyArea">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qydz" class="col-sm-2 control-label">公司地址*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qydz" name="qydz" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyLxr" class="col-sm-2 control-label">联系人*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qyLxr" name="qyLxr" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyLxrdh" class="col-sm-2 control-label">手机*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qyLxrdh" name="qyLxrdh" placeholder="" autocomplete="off">
                    </div>
                </div>

                <div class="form-group">
                    <label for="qyYx" class="col-sm-2 control-label">邮箱*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="qyYx" name="qyYx" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyGsxz" class="col-sm-2 control-label">公司性质*：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyGsxz" name="qyGsxz">
                            <option value="">请选择</option>
                            <#list qyGsxz as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyHylb" class="col-sm-2 control-label">行业类别*：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyHylb" name="qyHylb">
                            <option value="">请选择</option>
                            <#list qyHylb as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyGsgm" class="col-sm-2 control-label">公司规模*：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyGsgm" name="qyGsgm">
                            <option value="">请选择</option>
                            <#list qyGsgm as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="qyGsjs" class="col-sm-2 control-label">公司介绍*：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="qyGsjs" name="qyGsjs" rows="10"></textarea>
                    </div>
                </div>

                <input type="hidden" id="qyYyzzzp" name="qyYyzzzp" />

                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <button type="submit" class="btn btn-default btn-common">注册</button>
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
            var jsonObj={
                "dicType":20000
            }
            ajax("zustcommon/common/getByType", jsonObj, function (data) {
                if(data.backCode==0){
                }
            })

            $("#file").change(function (e) {
                var file = e.target.files[0] || e.dataTransfer.files[0];

                // $('#photoCover').val(document.getElementById("file").files[0].name);
                if (file) {
                    console.log("file",file.name)
                    var reader = new FileReader();
                    reader.onload = function () {
                        // console.log("file",file)
                        $("#yyzz").attr("src",this.result)
                        var fd = new FormData();
                        fd.append("file",file);
                        fd.append('data', JSON.stringify({
                            "type": 1
                        }));
                        // fd.append("method", "zustcommon/common/picUpload");
                        $.ajax({
                            url: "http://192.168.3.222:8888/zustcommon/common/picUpload",//localUrl,//
                            type: "POST",
                            processData: false,
                            contentType: false,
                            data: fd,
                            success: function(d) {
                                // console.log(d);
                                if(d.bean){
                                    if(d.bean["社会信用代码"]){
                                        $("#qyTysh").val(d.bean["社会信用代码"].words)
                                    }
                                    if(d.bean.fileName){
                                        $("#qyYyzzzp").val(d.bean.fileName)
                                    }
                                }
                                console.log(d.bean["社会信用代码"])
                            }
                        });
                    }

                    reader.readAsDataURL(file);
                }
            });

            $("#registerForm").validate({
                rules: {
                    qyTysh:"required",
                },
                messages: {
                    qyTysh: "请填写"
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

            var jsonObj = $("#registerForm").serializeObject()
            console.log(jsonObj)
            ajax("zustjy/bckjBizQyxx/companyRegister", jsonObj, function (data) {
                if(data.backCode==0){
                }
            })
        }

        $.fn.serializeObject = function() {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [ o[this.name] ];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
    </script>
</body>

</html>