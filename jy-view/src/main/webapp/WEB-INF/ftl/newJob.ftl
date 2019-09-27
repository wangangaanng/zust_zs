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
                <li><a href="#">首页</a></li>
                <li><a href="#">企业服务</a></li>
                <li class="active">新增职位</li>
            </ol>
        </div>
        <div class="content-form">
            <form class="form-horizontal" id="registerForm" method="" action="" target="rfFrame">
                <div class="form-group">
                    <label for="zwbt" class="col-sm-2 control-label">职位名称<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="zwbt" name="zwbt" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwPro" class="col-sm-2 control-label">所在省份<span class="red">*</span>：</label>
                    <div class="col-sm-3">
                        <select class="form-control" onchange="getCity()" name="zwPro" id="zwPro">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="zwCity" class="col-sm-2 control-label">所在市<span class="red">*</span>：</label>
                    <div class="col-sm-3">
                        <select class="form-control" onchange="getArea()" name="zwCity" id="zwCity">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwArea" class="col-sm-2 control-label">所在区<span class="red">*</span>：</label>
                    <div class="col-sm-3">
                        <select class="form-control" name="zwArea" id="zwArea">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGzzn" class="col-sm-2 control-label">职能类别<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGzzn" name="zwGzzn">
                            <option value="">请选择</option>
                        <#list zwGzzn as obj>
                        <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGzxz" class="col-sm-2 control-label">工作性质<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGzxz" name="zwGzxz">
                            <option value="">请选择</option>
                        <#list zwGzxz as obj>
                        <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwXs" class="col-sm-2 control-label">薪水<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="zwXs" name="zwXs" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwLxyx" class="col-sm-2 control-label">邮箱<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="zwLxyx" name="zwLxyx" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwZprs" class="col-sm-2 control-label">招聘人数<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="zwZprs" name="zwZprs" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwNlyq" class="col-sm-2 control-label">年龄要求<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwNlyq" name="zwNlyq">
                            <option value="">请选择</option>
                            <#list zwNlyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwXlyq" class="col-sm-2 control-label">学历要求<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwXlyq" name="zwXlyq">
                            <option value="">请选择</option>
                            <#list zwXlyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGznx" class="col-sm-2 control-label">工作年限<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGznx" name="zwGznx">
                            <option value="">请选择</option>
                            <#list zwGznx as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwYyyq" class="col-sm-2 control-label">语言要求<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwYyyq" name="zwYyyq">
                            <option value="">请选择</option>
                            <#list zwYyyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGwzz" class="col-sm-2 control-label">职位详情<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" id="zwGwzz" name="zwGwzz" rows="10"></textarea>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <button type="submit" class="btn btn-default btn-common green">提交</button>
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

            $("#zwPro").append("<option value='"+e.text+"'>"+e.text+"</option>")
        });

        function getCity() {
            $("#zwCity").html("<option value=''>请选择</option>")
            $("#zwArea").html("<option value=''>请选择</option>")
            pindex=parseInt($("#zwPro option:selected").index())-1;
            if(pindex>-1){
                _cityData=city[pindex]
                _cityData.forEach(function(e) {
                    $("#zwCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
                });

            }

        }

        function getArea() {
            $("#zwArea").html("<option value=''>请选择</option>")
            cindex=parseInt($("#zwCity option:selected").index())-1;
            if(cindex>-1){
                var _areaData=_cityData[cindex].children
                _areaData.forEach(function(e) {
                    $("#zwArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
                });

            }
        }
        $(document).ready(function () {

            jQuery.validator.addMethod("isNum", function(value, element) {
                return this.optional(element) || !isNaN(value) && !testFloat(value);
            }, "请填写整数");
            $("#registerForm").validate({
                rules: {
                    zwbt:"required",
                    zwPro:"required",
                    zwCity:"required",
                    zwArea:"required",
                    zwGzzn:"required",
                    zwGzxz:"required",
                    zwLxyx:{
                        required: true,
                        email: true
                    },
                    zwXs:{
                        required: true,
                        isNum: true
                    },
                    zwZprs:"required",
                    zwNlyq:"required",
                    zwXlyq:"required",
                    zwGznx:"required",
                    zwYyyq:"required",
                    zwGwzz:"required",
                },
                messages: {
                    zwbt: "请填写",
                    zwPro: "请选择",
                    zwCity: "请选择",
                    zwArea: "请选择",
                    zwGzzn: "请选择",
                    zwGzxz: "请选择",
                    zwLxyx: {
                        required: "请填写",
                        email: "请填写正确电子邮箱"
                    },
                    zwZprs: "请填写",
                    zwXs: {
                        required: "请填写",
                        isNum: "请填写整数"
                    },
                    zwNlyq: "请选择",
                    zwXlyq: "请选择",
                    zwGznx: "请选择",
                    zwYyyq: "请选择",
                    zwGwzz: "请填写",
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
                addOneJob()
            }
        });

        function addOneJob() {
            var jsonObj = $("#registerForm").serializeObject()
            jsonObj.qyxxRefOwid=getCookie("qyOwid")
            jsonObj.zwlx=0
            jsonObj.zwXs=parseInt(jsonObj.zwXs)
            console.log(jsonObj)
            ajax("zustjy/bckjBizJob/addOneJob", jsonObj, function (data) {
                if(data.backCode==0){
                    layer.open({
                        title:'提示',
                        content: '职位发布成功',
                        yes: function(index, layero){
                            layer.close(index);
                        }
                    });
                }
            })
        }

    </script>
</body>

</html>