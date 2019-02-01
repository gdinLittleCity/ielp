<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="graphs">
				
		<div class="row-fluid">
			
			<table id="dataTable" class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th>序号</th>
						<th>ID</th>
						<th>标题</th>
						<th>所属分类</th>
						<th>发布时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.beanList}" var="news" varStatus="status">
					
					<tr class="odd gradeX">
						<td>${status.count }</td>
						<td>${news.nid }</td>
						<td>${news.title }</td>
						<td>
							${news.category.cname }
						</td>
						<td>
							${news.publish_time}
						</td>
						<td class="center">
									<a class="btn btn-danger" href="${pageContext.request.contextPath }/admin/news/updatePre.action?nid=${news.nid}">
										修改
									</a>
									<a class="btn btn-success warning_2" href="javaScript:void(0);" onclick="deleteNews('${pageContext.request.contextPath }/admin/news/deleteNews.action?ids=${news.nid}')">
										删除
									</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>		
			</table>
			</div>
<div class="col-lg-6"
	style="float: none;display: block;margin-left: auto;margin-right: auto;">
	<div class="btn-group" role="group" aria-label="...">
		<nav>
			<ul class="pagination">
				<c:choose>
					<c:when test="${pageBean.pageNum eq 1 }">
						<li class="disabled"><span aria-hidden="true">上一页</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="javaScript:void(0)" onclick="toPage('${pageContext.request.contextPath }/admin/news/getAllNews.action?pageNum=${pageBean.pageNum-1 }')" aria-label="Previous"> <span
								aria-hidden="true">上一页</span>
						</a></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pageBean.pageTotal <=6 }">
						<c:set var="begin" value="1" property="request" />
						<c:set var="end" value="${pageBean.pageTotal-1  }" property="request"/>
					</c:when>
					<c:otherwise>
						<c:set var="begin" value="${pageBean.pageNum }" property="requset" />
						<c:set var="end" value="${pageBean.pageNum+4 }" property="request" />

						<c:if test="${end>=pageBean.pageTotal-1 }">
							<c:set var="begin" value="${pageBean.pageTotal -5}"
								property="requset" />
							<c:set var="end" value="${pageBean.pageTotal-1  }"
								property="request" />
						</c:if>
					</c:otherwise>
				</c:choose>

				<c:forEach begin="${begin}" end="${end}" var="i">
					<c:choose>
						<c:when test="${i==pageBean.pageNum }">
							<li class="active"><a>${i }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javaScript:void(0)" onclick="toPage('${pageContext.request.contextPath }/admin/news/getAllNews.action?pageNum=${i}')">${i }</a></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				
				 <c:if test="${pageBean.pageTotal-end>1 }">
				   <li><a >...</a></li>
				 </c:if>
				 
				 <c:choose>
						<c:when test="${pageBean.pageNum eq pageBean.pageTotal }">
							<li class="active"><a>${pageBean.pageTotal }</a></li>
						</c:when>
						<c:otherwise>
							 <li>
				   				<a href="javaScript:void(0)" onclick="toPage('${pageContext.request.contextPath }/admin/news/getAllNews.action?pageNum=${pageBean.pageTotal}')">${pageBean.pageTotal}</a>
				   			 </li>
						</c:otherwise>
				</c:choose>
				 
				 
				  				
				
				<c:choose>
					<c:when test="${pageBean.pageNum>=pageBean.pageTotal }">
						<li class="disabled"><a aria-label="Next"> <span
								aria-hidden="true">下一页</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javaScript:void(0)" onclick="toPage('${pageContext.request.contextPath }/admin/news/getAllNews.action?pageNum=${pageBean.pageNum+1 }')" aria-label="Next"> <span
								aria-hidden="true">下一页</span>
						</a></li>

					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
</div>
						</div>
				</div>
				
				<div class="copy container" style="position:fixed;bottom:0;">
					<p>Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;</p>
				</div>			
	<!-- /#wrapper -->
	<script type="text/javascript">
	
		
	
	</script>