<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息素质教育在线学习平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/show/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/show/slide.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#find").on('click',function(){
		$("#searchForm").submit();
	});
});
function linkChange(){
		if($("#con1").css("display")==="none")
			$("#moreLink").attr("href","${pageContext.request.contextPath }/show/identifyList.action");
		else
			$("#moreLink").attr("href","${pageContext.request.contextPath }/show/obtainList.action");
	}
</script>

</head>
<body>
<div id="wrap">
	<!--  Header  -->
    <div id="header">
        <img src="${pageContext.request.contextPath }/images/show/title.gif" />
      
        <form id="searchForm" action="${pageContext.request.contextPath }/show/search.action" method="post">
        	<input name="pageNum" value="1" type="hidden"  class="text"/>
        	
        	<dl>
            	<dd><input name="condition" type="text"  class="text" placeholder="站内搜索"/></dd><!--搜索框-->
                
                <dd><input name="" type="button" id="find"  class="btn"/></dd><!--搜索按钮-->
                <dd>
                	<select name="category" id="selector"  placeholder="默认搜索文章资源">
						<option value="1">文章搜索</option>
						<option value="2">下载资源搜索</option>
					</select>
				</dd>
            </dl>
        </form>
    </div>
    <!--  导航  -->
    <div id="nav">
    	<ul>
        	<li><a href="${pageContext.request.contextPath }/show/index.action">首页</a></li>
            <li><a href="${pageContext.request.contextPath }/show/showList.action">新闻动态</a></li>
            <li><a href="${pageContext.request.contextPath }/show/quantityList.action">信息素质</a></li>
            <li><a href="${pageContext.request.contextPath }/show/obtainList.action">信息获取</a></li>
            <li><a href="${pageContext.request.contextPath }/show/identifyList.action">信息甄别</a></li>
            <li><a href="${pageContext.request.contextPath }/show/moralToLaw.action">信息道德</a></li>
           <li><a href="${pageContext.request.contextPath }/show/showAllResource.action">在线资源</a></li>
            <li><a href="#">在线游戏</a></li>
        </ul>
    </div>
    <!--  焦点图  -->
    <div id="container">
        <div id="list" style="left: -720px;">
        	<a href="#"><img src="" alt=""/><span></span></a>
        	<c:forEach items="${activityList }" var="activity" varStatus="index">
        		<a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${activity.nid}"><img src="${activity.image_b }" alt="${index.count}"/><span>${activity.title}</span></a>
        	</c:forEach>
        </div>
        <div id="buttons">
        	<c:forEach items="${activityList }" var="activity" varStatus="index">
        		<c:if test="${index.count==1 }"><span index="${index.count }" class="on">${index.count }</span></c:if>
        		<c:if test="${index.count!=1 }"><span index="${index.count }">${index.count }</span></c:if>
        	</c:forEach>
           
        </div>
        <a href="javascript:;" id="prev" class="arrow">&lt;</a>
        <a href="javascript:;" id="next" class="arrow">&gt;</a>
    </div>
    <!--  新闻动态  -->
    <div id="news" class="box">
    	<div class="title">新闻动态</div>
        <a href="${pageContext.request.contextPath }/show/showList.action" class="more"><img src="${pageContext.request.contextPath }/images/show/more_icon.gif"></a>
        <div class="clear"></div>
        <div class="con">
            <div class="myscroll">
                <div id="area">
                    <ul id="text">
                    <c:forEach items="${newsList }" var="news">
                    	<li><a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${news.nid}">${news.title }</a><span>${news.publish_time }</span></li>
                    </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <!--  中间图片  -->
    <p class="pic"><img src="${pageContext.request.contextPath }/images/show/pic.jpg"></p>
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
                    <li><a href="${pageContext.request.contextPath }/show/criticalMuke.action">信息检索慕课</a></li>
                    <li><a href="${pageContext.request.contextPath }/show/searchMuke.action">批判性思维慕课</a></li>
                </ul>
            </div>
        </div>
        <!--  友情链接  -->
       	<%@include file="links.jsp" %>
    </div>
    <!--  Right  -->
    <div class="right">
    	<!--  信息素质  -->
        <div id="quality" class="box">
        	<div class="title">信息素质</div>
            <a href="${pageContext.request.contextPath }/show/quantityList.action" class="more"><img src="${pageContext.request.contextPath }/images/show/more_icon.gif"></a>
            <div class="clear"></div>
            <div class="con">
                <ul>
                <c:forEach items="${qualityList }" var="quanlity">
                	<li><a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${quanlity.nid}">${quanlity.title }</a><span>${quanlity.publish_time }</span></li>
                </c:forEach>
                <c:forEach begin="1" end="6" var="i">
                	<c:if test="${ fn:length(qualityList)-i<0}">
                		<li><a ></a><span></span></li>
                	</c:if>
                </c:forEach>
                </ul>
            </div>
        </div>
        <!--  信息获取&甄别（Tab）  -->
        <div class="tab box">
        	<ul class="menu">
            	<li class="active">信息获取</li>
                <li>信息甄别</li>
            </ul>
            <!-- 未添加链接 -->
            <a href="javascript:void(0)" onclick="linkChange()" id="moreLink" class="more"><img src="${pageContext.request.contextPath }/images/show/more_icon.gif"></a>
            <div class="con1" id="con1">
                <ul>
	                <c:forEach items="${obtainList }" var="obtain">
	                	<li><a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${obtain.nid}">${obtain.title }</a><span>${obtain.publish_time }</span></li>
	                </c:forEach>
	                <c:forEach begin="1" end="6" var="i">
	                	<c:if test="${ fn:length(obtainList)-i<0}">
	                		<li><a href="#"></a><span></span></li>
	                	</c:if>
                	</c:forEach>
	                
                </ul>
            </div>
            <div class="con2" id="con2">
                <ul>
                  <c:forEach items="${identifyList }" var="identify">
	                	<li><a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${identify.nid}">${identify.title }</a><span>${identify.publish_time }</span></li>
	              </c:forEach>
	              
	               <c:forEach begin="1" end="6" var="i">
	                	<c:if test="${ fn:length(identifyList)-i<0}">
	                		<li><a href="#"></a><span></span></li>
	                	</c:if>
                </c:forEach>
	                
                    
                </ul>
            </div>
        </div>
        <div class="clear"></div>
        <!--  科普活动报道  -->
        <div id="activity" class="box">
        	<div class="title">科普活动报道</div>
            <a href="${pageContext.request.contextPath }/show/activity/showList.action" class="more"><img src="${pageContext.request.contextPath }/images/show/more_icon.gif"></a>
            <div class="clear"></div>
            <div class="photo" id="photo">
            	<table>
                	<tr>
                    	<td id="list1">
                             <table style="border: 0px;" cellpadding="0" cellspacing="0">
                                 <tr id="pic">
                                 <c:forEach items="${activityList }" var="activity" varStatus="index">
        		           			 <td><a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${activity.nid}" target="_blank"><img alt="" src="${activity.image_b }" /><span>${activity.title }</span></a></td>
        						</c:forEach>
        						
        						<c:forEach items="${activityList }" var="activity" varStatus="index">
        		           			 <td><a href="${pageContext.request.contextPath }/show/articleInfo.action?nid=${activity.nid}" target="_blank"><img alt="" src="${activity.image_b }" /><span>${activity.title }</span></a></td>
        						</c:forEach>
                                 </tr>
                             </table>
                         </td>
                         <td id="list2"></td>
                    </tr>
                </table>
            </div>
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
</html>