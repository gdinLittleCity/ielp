<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$(document).ready(function(){
	$("#find").on('click',function(){
		$("#searchForm").submit();
	});

});

</script>

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
