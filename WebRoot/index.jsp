<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息素质教育在线学习平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/show/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>

</head>

<body>
  
    
  </body>
  <script type="text/javascript">
  $(document).ready(function(){
	  window.location.href="${pageContext.request.contextPath }/show/index.action";
  });
  
  </script>
</html>
