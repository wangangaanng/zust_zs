<#assign base=request.contextPath />
<#assign swyt_title="浙江科技学院三位一体招生网" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="${base}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${base}/css/common.css" />
<link rel="stylesheet" href="${base}/js/layer/skin/layer.css" />
<script src="${base}/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.js"></script>
<script src="${base}/js/SWcommon.js"></script>
<#--表单验证-->
<script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
<#--表单验证汉化-->
<script src="${base}/js/messages_zh.min.js" type="text/javascript"></script>
<script>
    var imagePath='${imagePath!""}';
    var base='${base!""}'
    var localUrl = '${base!""}'+'/webAjax/executeAPI';
    var uploadUrl='${uploadUrl!""}';
    var hostUrl = '${base!""}'; //身份证识别用就业模块的 OCr需要调这个地址
</script>