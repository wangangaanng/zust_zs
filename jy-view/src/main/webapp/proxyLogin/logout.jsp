<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	session.invalidate();
	// ids的退出地址
	String casLogoutURL = request.getHeader("cas_logout_url");
	String redirectURL=casLogoutURL+URLEncoder.encode("https://job.zust.edu.cn/"); // 地址需要根据环境修改（域名+上下文）
	response.sendRedirect(redirectURL);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title></title>
</head>
<body>
</body>
</html>
