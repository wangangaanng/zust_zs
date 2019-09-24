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
                <li class="active">修改职位</li>
            </ol>
        </div>
        <div class="content-form">
            <form class="form-horizontal" id="registerForm" method="" action="" target="rfFrame">
                <div class="form-group">
                    <label for="zwbt" class="col-sm-2 control-label">职位名称*：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="zwbt" name="zwbt" value="${jobDetail.zwbt!''}" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwPro" class="col-sm-2 control-label">所在省份*：</label>
                    <div class="col-sm-3">
                        <select class="form-control" onchange="getCity()" data-val="${jobDetail.zwPro!''}" name="zwPro" id="zwPro">
                            <option value="">请选择</option>

                        </select>
                    </div>
                    <label for="zwCity" class="col-sm-2 control-label">所在市*：</label>
                    <div class="col-sm-3">
                        <select class="form-control" onchange="getArea()" data-val="${jobDetail.zwCity!''}" name="zwCity" id="zwCity">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwArea" class="col-sm-2 control-label">所在区*：</label>
                    <div class="col-sm-3">
                        <select class="form-control" name="zwArea" id="zwArea" data-val="${jobDetail.zwArea!''}">
                            <option value="">请选择</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGzzn" class="col-sm-2 control-label">职能类别*：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGzzn" name="zwGzzn" data-val="${jobDetail.zwGzzn!''}">
                            <option value="">请选择</option>
                        <#list zwGzzn as obj>
                        <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGzxz" class="col-sm-2 control-label">工作性质*：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGzxz" name="zwGzxz" data-val="${jobDetail.zwGzxz!''}">
                            <option value="">请选择</option>
                        <#list zwGzxz as obj>
                        <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwXs" class="col-sm-2 control-label">薪水*：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="zwXs" name="zwXs" value="${jobDetail.zwXs!''}" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwLxyx" class="col-sm-2 control-label">邮箱*：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="zwLxyx" name="zwLxyx" value="${jobDetail.zwLxyx!''}" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwZprs" class="col-sm-2 control-label">招聘人数*：</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="zwZprs" name="zwZprs" value="${jobDetail.zwZprs!''}" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwNlyq" class="col-sm-2 control-label">年龄要求*：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwNlyq" name="zwNlyq" data-val="${jobDetail.zwNlyq!''}">
                            <option value="">请选择</option>
                            <#list zwNlyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwXlyq" class="col-sm-2 control-label">学历要求*：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwXlyq" name="zwXlyq" data-val="${jobDetail.zwXlyq!''}">
                            <option value="">请选择</option>
                            <#list zwXlyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGznx" class="col-sm-2 control-label">工作年限*：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwGznx" name="zwGznx" data-val="${jobDetail.zwGznx!''}">
                            <option value="">请选择</option>
                            <#list zwGznx as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwYyyq" class="col-sm-2 control-label">语言要求*：</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="zwYyyq" name="zwYyyq" data-val="${jobDetail.zwYyyq!''}">
                            <option value="">请选择</option>
                            <#list zwYyyq as obj>
                                <option value="${obj.dicVal1}">${obj.dicVal2}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="zwGwzz" class="col-sm-2 control-label">职位详情*：</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" id="zwGwzz" name="zwGwzz" rows="10" data-val="${jobDetail.zwGwzz!''}"></textarea>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <button type="submit" class="btn btn-default btn-common green">提交</button>
                    </div>
                </div>
            </form>
            <input type="hidden" id="zwOwid" value="${jobDetail.owid!''}">
            <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>
        </div>
    </div>

<#include "com/footer.ftl">
    <script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${base}/js/city1.js" type="text/javascript"></script>
    <script src="${base}/js/fixJob.js" type="text/javascript"></script>
</body>

</html>