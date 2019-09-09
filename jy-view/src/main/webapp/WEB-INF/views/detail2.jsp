<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="commonProperty.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
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
				</ul>
			</div>
			<div class="table" style="display: block;">
				<div class="table_detail_title"></div>
				<div class="table_detail_info"></div>
				<div class="table_detail_content" id="content">
				</div>
				<div id="fj" class="table_detail_content" style="display: none;">附件：</div>
				<%--<div style="text-align: center;margin: 130px 0 45px;">--%>
					<%--<button type="button" class="table_detail_back" onclick="history.back()">返回</button>--%>
				<%--</div>--%>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</body>
	<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/js/detail2.js" type="text/javascript" charset="utf-8"></script>
</html>
