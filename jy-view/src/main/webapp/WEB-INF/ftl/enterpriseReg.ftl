<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>就业网</title>
    <#include "com/config.ftl">
    <link rel="stylesheet" href="${base}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${base}/css/common.css" />
</head>
<style>
    .form-group {
        margin-right: 280px !important;
        position: relative;
    }

    .upload-sm {
        position: absolute;
        line-height: 34px;
        width: 280px;
        padding-right: 15px;
        right: -280px;
    }

    .content-form {
        border: solid 1px #e0e0e0;
        padding: 30px 0;
    }

    .btn-common {
        width: 260px;
        height: 40px;
        background-color: #008784;
        color: #fff;
        border-radius: 4px;
    }
    .upload-img{
        position: relative;
    }
    .upload-img #file{
        opacity: 0;
        width: 100%;
        height: 34px;
        z-index: 99;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        padding-right: 15px;
        cursor: pointer;
    }
    .upload-img img{
        width: 100%;
        height: 34px;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        z-index: 1;
        padding-right: 15px;
    }
</style>

<body>
<#include "com/header.ftl">
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
            <form class="form-horizontal" id="registerForm">
                <div class="form-group">
                    <label for="qyTysh" class="col-sm-2 control-label">填写代码*：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="qyTysh" name="qyTysh" placeholder="" autocomplete="off">
                    </div>
                    <div class="col-sm-2 upload-img">
                        <input type="file" value="" name="file" id="file" accept="image/jpeg,image/jpg,image/png,image/svg" />
                        <img src="${base}/img/upload.png" id="yyzz" alt="" />
                    </div>
                    <div class="upload-sm">上传营业执照自动识别统一信用代码</div>
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
                    <label for="qyGsxz" class="col-sm-2 control-label">公司性质：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyGsxz" name="qyGsxz">
                            <option>请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyHylb" class="col-sm-2 control-label">行业类别：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyHylb" name="qyHylb">
                            <option>请选择</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qyGsgm" class="col-sm-2 control-label">公司规模：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="qyGsgm" name="qyGsgm">
                            <option>请选择</option>

                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="qyGsjs" class="col-sm-2 control-label">公司介绍：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="qyGsjs" name="qyGsjs" rows="10"></textarea>
                    </div>
                </div>



                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <button type="submit" class="btn btn-default btn-common">注册</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

<#include "com/footer.ftl">
    <script src="${base}/js/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/common.js" type="text/javascript"></script>
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



            $("#registerForm").submit(function (envent){
                envent.preventDefault();

                var form = $(this);
                console.log(form.serialize())
            });

            var jsonObj = {
                "dicType": 20000
            }
            ajax("zustcommon/common/getByType", jsonObj, function (data) {
                if(data.backCode==0){
                }
            })


            $("#file").change(function (e) {
                var file = e.target.files[0] || e.dataTransfer.files[0];
                $('#photoCover').val(document.getElementById("file").files[0].name);
                if (file) {
                    var reader = new FileReader();
                    reader.onload = function () {
                        $("#yyzz").attr("src",this.result)
                    }

                    reader.readAsDataURL(file);
                }
            });
        })

        function imgPreview(fileDom, i) {
            console.log('fileDom',fileDom)
            //判断是否支持FileReader
            if (window.FileReader) {
                var reader = new FileReader();
            } else {
                alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
            }
            //获取文件
            var file = fileDom.files[0];
            var imageType = /^image\//;
            //是否是图片
            if (!imageType.test(file.type)) {
                alert("请选择图片！");
                return;
            }
            //读取完成
            reader.onload = function(e) {
                //图片路径设置为读取的图片
                // img.src = e.target.result;
                // console.log(document.getElementsByClassName('file-box'));
                $("#yyzz").attr("src",e.target.result);
                // document.getElementsByClassName('file-box')[i].style.background = "url(" + e.target.result + ")no-repeat"; //回显图片
                // document.getElementsByClassName('file-box')[i].style.backgroundSize = '200px 160px';
                // console.log('reader', reader)
            };
            reader.readAsDataURL(file);
        }

    </script>
</body>

</html>