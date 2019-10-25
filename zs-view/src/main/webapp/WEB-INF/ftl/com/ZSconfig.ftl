<#assign base=request.contextPath />
<#assign title="浙江科技学院招生网" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="js/layer/skin/layer.css" />
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script src="js/common.js"></script>
<script>
    var imagePath='${imagePath!""}';
    var base='${base!""}'
    var localUrl = '${base!""}'+'/webAjax/executeAPI';
    var uploadUrl='${uploadUrl!""}';
</script>