<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="graphs">
				
		<div class="row-fluid">
			
			<table id="dataTable" class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>序号</th>
						<th>ID</th>
						<th>分类名</th>
						<th>备注</th>
						<th>上级目录ID</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.beanList}" var="category" varStatus="status">
					
					<tr class="odd gradeX">
						<td>${status.count }</td>
						<td>${category.cid }</td>
						<td>
							${category.cname }
						</td>
						<td>
							${category.desc }
						</td>
						<c:if test="${not empty category.parent }">
							<td>${category.parent.cid }</td>
						</c:if>
						<c:if test="${empty category.parent }">
							<td>我是一级目录,没有上级目录</td>
						</c:if>
						<td class="center">
									<a class="btn btn-danger" href="javaScript:void(0);" onclick="toPage('${pageContext.request.contextPath }/admin/category/getOneByCid.action?cid=${category.cid}')">
										修改
									</a>
									<a class="btn btn-success warning_2" href="javaScript:void(0);" onclick="deleteCategory('${pageContext.request.contextPath }/admin/category/deleteCategory.action?cid=${category.cid}')">
										删除
									</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>		
				
			</table>
						
					</div>
					
					<div class="col-lg-3" style="float: none;display: block;margin-left: auto;margin-right: auto;">
							<div class="btn-group" role="group" aria-label="...">
							  <button type="button" class="btn btn-default" onclick="next_pre_Page('${pageContext.request.contextPath }/admin/category/getAll.action','${page.pageNum-1}','${page.pageTotal }')">上一页</button>
							  <button type="button" class="btn btn-default" onclick="next_pre_Page('${pageContext.request.contextPath }/admin/category/getAll.action','${page.pageNum+1}','${page.pageTotal }')">下一页</button>
							</div>
						</div>
					
				</div>

				
				<div class="copy container" >
					<p>Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;</p>
				</div>			
	<!-- /#wrapper -->
	<script type="text/javascript">
		//延迟函数使用的包装函数
		function runToPage(){
			toPage('${pageContext.request.contextPath }/admin/category/getAll.action?pageNum=1');
		}	
	
	</script>