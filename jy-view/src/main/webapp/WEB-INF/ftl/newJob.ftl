<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "com/config.ftl">
    <title>${title!''}</title>
    <link rel="icon" href="${base}/img/zust.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${base}/js/laydate/theme/default/laydate.css" />
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
                <li><a href="${base}/">首页</a></li>
                <li><a href="${base}/enterpriseService/1">企业服务</a></li>
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
                    <div class="col-sm-2">
                        <select class="form-control" onchange="getCity()" name="zwPro" id="zwPro" data-val="${(result.qyProv)!''}">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="zwCity" class="col-sm-1 control-label">所在市<span class="red">*</span>：</label>
                    <div class="col-sm-2">
                        <select class="form-control" onchange="getArea()" name="zwCity" id="zwCity" data-val="${(result.qyCity)!''}">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="zwArea" class="col-sm-1 control-label">所在区<span class="red">*</span>：</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="zwArea" id="zwArea" data-val="${(result.qyArea)!''}">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <#--<div class="form-group">-->
                    <#--<label for="zwArea" class="col-sm-1 control-label">所在区<span class="red">*</span>：</label>-->
                    <#--<div class="col-sm-3">-->
                        <#--<select class="form-control" name="zwArea" id="zwArea">-->
                            <#--<option value="">请选择</option>-->

                        <#--</select>-->
                    <#--</div>-->
                <#--</div>-->
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
                        <#list zwGzxz as obj>
                            <#if (obj.dicVal2)?? && (obj.dicVal2)=='不限'>
                                <option selected value="${obj.dicVal1}">${obj.dicVal2}</option>
                                <#else>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#if>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwXs" class="col-sm-2 control-label">薪水：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="zwXs" name="zwXs" placeholder="面议" autocomplete="off">
                        <span style="position: absolute;right: 25px;top: 7px;">元</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwLxyx" class="col-sm-2 control-label">邮箱<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="zwLxyx" value="${(result.qyYx)!''}" name="zwLxyx" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwZprs" class="col-sm-2 control-label">招聘人数<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="zwZprs" name="zwZprs" value="1" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwNlyq" class="col-sm-2 control-label">年龄要求<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwNlyq" name="zwNlyq">
                            <#list zwNlyq as obj>
                                <#if (obj.dicVal2)?? && (obj.dicVal2)=='不限'>
                                <option selected value="${obj.dicVal1}">${obj.dicVal2}</option>
                                <#else>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwXlyq" class="col-sm-2 control-label">学历要求<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwXlyq" name="zwXlyq">
                            <#list zwXlyq as obj>
                                <#if (obj.dicVal2)?? && (obj.dicVal2)=='不限'>
                                <option selected value="${obj.dicVal1}">${obj.dicVal2}</option>
                                <#else>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGznx" class="col-sm-2 control-label">工作年限<span class="red">*</span>：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGznx" name="zwGznx">
                            <#list zwGznx as obj>
                                <#if (obj.dicVal2)?? && (obj.dicVal2)=='不限'>
                                <option selected value="${obj.dicVal1}">${obj.dicVal2}</option>
                                <#else>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwYyyq" class="col-sm-2 control-label">语言要求：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwYyyq" name="zwYyyq">
                            <option value="">不限</option>
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
                    <label for="zwSxsj" class="col-sm-2 control-label">失效时间：</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="zwSxsj" name="zwSxsj" placeholder="" autocomplete="off">
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
    <script src="${base}/js/laydate/laydate.js" type="text/javascript"></script>
    <script src="${base}/js/city1.js" type="text/javascript"></script>

    <script>
        var provice=[];
        var city=[];
        var pindex=0;
        var cindex=0;
        var _cityData=[];
        // cityData3.forEach(function(e) {
        //     provice.push(e.text)
        //     city.push(e.children)
        //
        //     $("#zwPro").append("<option value='"+e.text+"'>"+e.text+"</option>")
        // });

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

            cityData3.forEach(function(e) {
                provice.push(e.text)
                city.push(e.children)
                if($("#zwPro").attr("data-val")==e.text) {
                    $("#zwPro").append("<option value='" + e.text + "' selected>" + e.text + "</option>")
                }else{
                    $("#zwPro").append("<option value='" + e.text + "' >" + e.text + "</option>")
                }
            });

            pindex=parseInt($("#zwPro option:selected").index())-1;
            if(pindex>-1){
                _cityData=city[pindex]
                _cityData.forEach(function(e) {
                    if($("#zwCity").attr("data-val")==e.text){
                        $("#zwCity").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
                    }else{
                        $("#zwCity").append("<option value='"+e.text+"'>"+e.text+"</option>")
                    }

                });

            }
            cindex=parseInt($("#zwCity option:selected").index())-1;
            if(cindex>-1){
                var _areaData=_cityData[cindex].children
                _areaData.forEach(function(e) {
                    if($("#zwArea").attr("data-val")==e.text){
                        $("#zwArea").append("<option value='"+e.text+"' selected>"+e.text+"</option>")
                    }else{
                        $("#zwArea").append("<option value='"+e.text+"'>"+e.text+"</option>")
                    }
                });

            }

            laydate.render({
                elem: "#zwSxsj" //指定元素
                ,min: 1
            });

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
                        isNum: true
                    },
                    zwZprs:"required",
                    zwNlyq:"required",
                    zwXlyq:"required",
                    zwGznx:"required",
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
                        isNum: "请填写整数"
                    },
                    zwNlyq: "请选择",
                    zwXlyq: "请选择",
                    zwGznx: "请选择",
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
            if(!isTimeOut()) {
                $(".green").attr("disabled","disabled");
                var jsonObj = $("#registerForm").serializeObject()
                jsonObj.qyxxRefOwid = getCookie("qyOwid")
                jsonObj.zwlx = 0
                if(jsonObj.zwXs){
                    jsonObj.zwXs = parseInt(jsonObj.zwXs)
                }
                ajax("zustjy/bckjBizJob/addOneJob", jsonObj, function (data) {
                    if (data.backCode == 0) {
                        layer.open({
                            title: '提示',
                            closeBtn: 0,
                            scrollbar: false,
                            content: '职位发布成功',
                            yes: function (index, layero) {
                                window.location.href='${base}/enterpriseService/1'
                                layer.close(index);
                            }
                        });
                    }else{
                        $(".green").removeAttr("disabled");
                        walert(data.errorMess)
                    }
                })
            }
        }

    </script>
</body>

</html>