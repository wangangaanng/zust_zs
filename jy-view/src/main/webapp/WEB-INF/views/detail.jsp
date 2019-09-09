<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="commonProperty.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>鄂尔多斯市转型发展投资有限公司</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/com.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css" />
	</head>
	<body>
	<%@include file="top.jsp"%>
		<div class="bodyContent container">
			<div class="route">
				<img src="image/route.png">
				<ul>
					<li>首页</li>
					<li id="secondmenu"></li>
					<li id="thirdmenu"></li>
				</ul>
			</div>
			<div class="table" style="display: block;">
				<div class="table_detail_title"></div>
				<div class="table_detail_info"><span id="time"></span> 文章来源：<span id="wzly"></span> 作者：<span id="author"></span></div>
				<div class="table_detail_content" id="content">

				</div>
				<div id="fj" class="table_detail_content" style="display: none;">附件：</div>
				<%--<div style="text-align: center;margin: 130px 0 45px;">--%>
					<%--<button type="button" class="table_detail_back" onclick="close1()">返回</button>--%>
				<%--</div>--%>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</body>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/detail.js"></script>
	<script type="text/javascript">
	</script>
</html>
