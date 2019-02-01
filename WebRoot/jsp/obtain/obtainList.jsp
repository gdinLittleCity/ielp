<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息获取</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/show/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
</head>
<body>
<div id="wrap">
	<!--  Header  -->
   <%@include file="../header.jsp" %>
    <!--  Left  -->
    <div class="left">
    	<!--  信息道德  -->
        <div id="morals">
        	<div class="left_title">信息道德</div>
            <div class="con">
            	<ul>
                	<li><a href="${pageContext.request.contextPath }/show/moralToLaw.action">相关法律法规</a></li>
                    <li><a href="${pageContext.request.contextPath }/show/moralToCase.action">案例</a></li>
                </ul>
            </div>
        </div>
        <!--  在线资源  -->
        <div id="res">
        	<div class="left_title">在线资源</div>
            <div class="con">
            	<ul>
                	<li><a href="${pageContext.request.contextPath }/show/pptList.action">讲座PPT下载</a></li>
                	<li><a href="${pageContext.request.contextPath }/show/criticalMuke.action">批判性思维慕课</a></li>
                    <li><a href="${pageContext.request.contextPath }/show/searchMuke.action">信息搜索慕课</a></li>
                </ul>
            </div>
        </div>
        <!--  友情链接  -->
        <%@include file="../links.jsp" %>
    </div>
	<!--  Right  -->
    <div class="container">
    	<div class="showlocal">
        	<span>您现在的位置：</span><span><a href="${pageContext.request.contextPath }/show/index.action">首页</a></span><span>&nbsp;>&nbsp;
        	<a href="#">信息获取</a></span>
            <hr>
        </div>
        <!--  列表  -->
        <div class="con">
        	<ul>
        		<c:forEach items="${pageBean.beanList }" var="obtain">
                <li><a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${obtain.nid}">${obtain.title }</a><span>${obtain.publish_time}</span></li>
                </c:forEach>
            </ul>
        </div>
        <!--  页码  -->
        <div class="page">
        <c:choose>
					<c:when test="${pageBean.pageNum<=1 }">
						<a class="page_turn" href="#">上一页</a>
					</c:when>
					<c:otherwise>
						<a class="page_turn" href="${pageContext.request.contextPath }/show/obtainList.action?pageNum=${pageBean.pageNum-1}">上一页</a>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pageBean.pageTotal <=6 }">
						<c:set var="begin" value="1" property="request" />
						<c:set var="end" value="${pageBean.pageTotal  }" />
					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${pageBean.pageNum }" property="requset" />
						<c:set var="end" value="${pageBean.pageNum+4 }" property="request" />

						<c:if test="${end>=pageBean.total }">
							<c:set var="begin" value="${pageBean.pageTotal -4}"
								property="requset" />
							<c:set var="end" value="${pageBean.pageTotal  }"
								property="request" />
						</c:if>
					</c:otherwise>
				</c:choose>

				<c:forEach begin="${begin}" end="${end}" var="i">
					<c:choose>
						<c:when test="${i==pageBean.pageNum }">
							<a class="on" href="#">${i }</a>
						</c:when>
						<c:otherwise>
						 <a href="${pageContext.request.contextPath }/show/obtainList.action?pageNum=${i}">${i }</a>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				 <c:if test="${end < pageBean.pageTotal-1 }">
				    ...
				 </c:if>
				 <c:if test="${pageBean.pageTotal>6 }">
				   <a href="#">${pageBean.pageTotal}</a>
				 </c:if> 
				
				<c:choose>
					<c:when test="${pageBean.pageNum>=pageBean.pageTotal }">
						<a class="page_turn" href="#"> 下一页</a>
					</c:when>
					<c:otherwise>
						<a class="page_turn" href="${pageContext.request.contextPath }/show/obtainList.action?pageNum=${pageBean.pageNum+1}">下一页</a>
					</c:otherwise>
				</c:choose>
            	 跳转到：
            <input id="page_text" type="text" name="page_text">
            <a id="page_btn" class="page_turn" href="javascript:;" onclick="go()">GO</a>
        </div>
    </div>
    <div class="clear"></div>
    
    
    <!--  Footer  -->
    <div id="footer">
    	Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath }/user/tologin.action">>>后台登陆</a>
    </div>
</div>
</body>
<script type="text/javascript">
	function go(){
		var pageNum =parseInt($("#page_text").val());//获取文本框中的当前页码
		var pageTotal = parseInt("${pageBean.pageTotal }");
		//正则校验
		if(!/^[1-9]\d*$/.test(pageNum)) {//对当前页码进行整数校验
			alert('请输入正确的页码！');
			return;
		}
		if(pageNum > pageTotal) {//判断当前页码是否大于最大页
			
			alert('数字太大！请输入正确的页码！');		//
			return;
		}
		location = "${pageContext.request.contextPath }/show/obtainList.action?pageNum="+pageNum;
	
	}
</script>

</html>

