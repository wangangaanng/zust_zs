<#assign base=request.contextPath />
<#assign imagePath='http://192.168.3.222:8080/zjcFiles/'/>
<link rel="stylesheet" href="${base}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${base}/css/common.css" />
<link rel="stylesheet" href="${base}/js/layer/skin/layer.css" />
<script src="${base}/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.js"></script>
<script src="${base}/js/common.js"></script>
<script>
    var imagePath='${imagePath!""}';
    var base='${base!""}'
    var localUrl = 'http://localhost:8080/webAjax/executeAPI';
    var uploadUrl="http://192.168.3.222:8888/zustcommon/common/picUpload"
</script>