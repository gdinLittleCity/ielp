<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>详情</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/show/style.css"/>
</head>

<body>
<div id="wrap">
	<!--  Header  -->
    <%@include file="header.jsp" %>
    <!--  内容  -->
    <div id="article">
		<div class="essay_title">${article.title }</div>
        <div class="info">发布人:${article.user.name}<span>发布时间：${article.publish_time }</span></div>
        <div class="essay">
			${article.contentString }
        </div>
    </div>
    <!--  Footer  -->
    <div id="footer">
    	Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;
       <a href="${pageContext.request.contextPath }/user/tologin.action">>>后台登陆</a>
    </div>
</div>
</body>
</html>
