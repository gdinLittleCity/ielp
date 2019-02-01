<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/show/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
<title>文章搜索结果</title>

<script>
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
		location = "${pageContext.request.contextPath }/show/moralToLaw.action?pageNum="+pageNum;
	
	}
</script>
</head>

<body>
<div id="wrap">
	<!--  Header  -->
    <%@include file="header.jsp" %>
    <!--  Left  -->
    <div class="left">
    	<!--  信息道德  -->
        <div id="select">
        	<div class="left_title">信息道德</div>
            <div class="con">
            	<ul>
                	<li><a href="${pageContext.request.contextPath }/show/moralToLaw.action">相关法律法规</a></li>
                    <li><a href="${pageContext.request.contextPath }/show/moralToCase.action">案例</a></li>
                </ul>
            </div>
        </div>
        <!--  最新动态  -->
        <div id="last_news">
        	<div class="left_title">最新动态</div>
            <div class="con">
            	<ul>
            	<c:forEach items="${newsPage.beanList }" var="news">
                	<li><a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${news.nid}">${news.title }</a></li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
	<!--  Right  -->
    <div class="container ctn" id="ctn1" style="display:block;">
        <div class="showlocal">
            <span>您现在的位置：</span><span><a href="${pageContext.request.contextPath }/show/index.action">首页</a></span>
            <span>&nbsp;>&nbsp;<a>搜索结果</a></span>
            <hr>
        </div>
        <!--  列表  -->
        <div class="con">
            <ul>
            	<c:forEach items="${pageBean.beanList }" var="res">
                	<li><a href="${pageContext.request.contextPath }/show/downResource.action?sid=${res.sid}">${res.title }</a>
                	
                	</li>
                
                </c:forEach>
                               
            </ul>
        </div>
        <!--  页码  -->
        <div class="page">
        <c:choose>
					<c:when test="${pageBean.pageNum<=1 }">
						<a class="page_turn">上一页</a>
					</c:when>
					<c:otherwise>
						<a class="page_turn" href="${pageContext.request.contextPath }/show/search.action?pageNum=${pageBean.pageNum-1}&condition=${condition}&category=${category}">上一页</a>
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
						 <a href="${pageContext.request.contextPath }/show/search.action?pageNum=${i}&condition=${condition}&category=${category}">${i }</a>
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
						<a class="page_turn" href="${pageContext.request.contextPath }/show/search.action?pageNum=${pageBean.pageNum+1}&condition=${condition}&category=${category}">下一页</a>
					</c:otherwise>
				</c:choose>
            	 跳转到：
            <input id="page_text" type="text" name="page_text"/>
            <a id="page_btn" class="page_turn" href="javascript:;" onclick="go()">GO</a>
        </div>
    </div>   
        <div class="clear"></div>
    <!--  Footer  -->
    <div id="footer">
    	Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br/>
        技术支持：计算机网络中心&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath }/user/tologin.action">>>后台登陆</a>
    </div>
</div>
</body>
</html>