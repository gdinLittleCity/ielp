<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Navigation -->
<!--navbar header -->
<nav class="top1 navbar navbar-default navbar-static-top"
	role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only"></span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${pageContext.request.contextPath }/admin/index.action">信息素质教育在线学习管理后台</a>
	</div>
	<!-- navbar-header -->
	<ul class="nav navbar-nav navbar-right">

		<li class="dropdown">
		<a href="#" class="dropdown-toggle avatar" data-toggle="dropdown">
			<img src="${pageContext.request.contextPath }/images/user.jpg">
			<span class="badge"></span>
		</a>
			<ul class="dropdown-menu">
				<li class="m_2"><a href="${pageContext.request.contextPath }/admin/manager/updateUserPre.action"><i class="fa fa-wrench"></i>
						个人信息设置</a></li>
				<li class="divider"></li>
				<li class="m_2"><a href="${pageContext.request.contextPath }/user/logout.action"><i class="fa fa-lock"></i>
						退出</a></li>
			</ul></li>
	</ul>
	<!--  站内搜索功能 暂未开放
			<form class="navbar-form navbar-right">
              <input type="text" class="form-control" value="Search..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search...';}">
            </form>
            -->
	<div class="navbar-default sidebar" role="navigation" style="margin-bottom: 0">
		<div class="sidebar-nav">
			<ul class="nav" id="side-menu">
				<li><a><i class="fa fa-dashboard fa-fw nav_icon"></i>Dashboard</a></li>
				<li><a href="#"><i class="fa fa-check-square-o nav_icon"></i>
						管理员管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<c:if test="${user.power==1 }">
						<li><a href="${pageContext.request.contextPath }/admin/manager/super/administor.action">添加管理员</a>
						</li>
						<li><a href="javaScript:void(0)" onclick="toPage('${pageContext.request.contextPath }/admin/manager/super/getAdminInfo.action?pageNum=1')">修改/删除管理员</a>
						</li>
						</c:if>
						<li><a href="${pageContext.request.contextPath }/admin/manager/updatePasswordPre.action">修改密码</a>
						</li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa fa-table nav_icon"></i> 分类管理<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath }/admin/category/saveCategoryPre.action?path=parent">添加一级分类</a>
						</li>
						<li><a href="${pageContext.request.contextPath }/admin/category/saveCategoryPre.action">添加二级分类</a>
						</li>
						<li><a href="javaScript:void(0)" onclick="toPage('${pageContext.request.contextPath }/admin/category/getAll.action?pageNum=1')">删除/修改分类</a>
						</li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa fa-indent nav_icon"></i>文章管理<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath }/admin/news/newsInputPre.action">发布文章</a>
						<li><a href="javaScript:void(0)" onclick="toPage('${pageContext.request.contextPath }/admin/news/getAllNews.action?pageNum=1')">修改/删除文章</a>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="#"><i class="fa fa-envelope nav_icon"></i>上传资源管理<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath }/admin/resource/inputResourcePre.action">上传资源</a></li>
						<li><a href="javaScript:void(0)" onclick="toPage('${pageContext.request.contextPath }/admin/resource/getResourceInfo.action?pageNum=1')">修改/删除资源信息</a></li>
					</ul> <!-- /.nav-second-level --></li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>