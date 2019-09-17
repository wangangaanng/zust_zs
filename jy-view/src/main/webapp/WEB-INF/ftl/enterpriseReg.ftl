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

    .upload-img {
        width: 100%;
        height: 34px;
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
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">填写代码*：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="" autocomplete="off">
                    </div>
                    <div class="col-sm-2">
                        <img src="${base}/img/upload.png" alt="" class="upload-img" />
                    </div>
                    <div class="upload-sm">上传营业执照自动识别统一信用代码</div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">法人身份证号*：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="inputPassword3" placeholder="" autocomplete="off">
                    </div>
                    <div class="col-sm-2">
                        <img src="${base}/img/upload.png" alt="" class="upload-img" />
                    </div>
                    <div class="upload-sm">上传法人身份证正面照，自动识别、填充</div>
                </div>
                <div class="form-group">
                    <label for="input1" class="col-sm-2 control-label">公司名称*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input1" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="input2" class="col-sm-2 control-label">所在省份*：</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="input2">
                            <option>1</option>

                        </select>
                    </div>
                    <label for="input3" class="col-sm-2 control-label">所在市*：</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="input3">
                            <option>1</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="input4" class="col-sm-2 control-label">公司地址*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input4" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="input5" class="col-sm-2 control-label">联系人*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input5" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="input6" class="col-sm-2 control-label">手机*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input6" placeholder="" autocomplete="off">
                    </div>
                </div>

                <div class="form-group">
                    <label for="input7" class="col-sm-2 control-label">邮箱*：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="input7" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="input8" class="col-sm-2 control-label">公司性质：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="input8">
                            <option>1</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="input9" class="col-sm-2 control-label">行业类别：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="input9">
                            <option>1</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="input10" class="col-sm-2 control-label">公司规模：</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="input10">
                            <option>1</option>

                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="input11" class="col-sm-2 control-label">公司介绍：</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="input11" rows="10"></textarea>
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
    <script src="${base}/js/common.js"></script>
</body>

</html>