<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath }/css/style.css"
	rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href="${pageContext.request.contextPath }/css/lines.css"
	rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath }/css/font-awesome.css"
	rel="stylesheet">
<!-- jQuery -->
<script src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
<!-- Nav CSS -->
<link href="${pageContext.request.contextPath }/css/custom.css"
	rel="stylesheet">
<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath }/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath }/js/custom.js"></script>
<!-- ueditor -->
 <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/editor/ueditor.config.js"></script>
 <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/editor/ueditor.all.min.js"> </script>
  <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/editor/lang/zh-cn/zh-cn.js"></script>




</head>
<body>
	<div id="wrapper">
		<%@include file="../nav.jsp"%>
			<div id="page-wrapper">
			<div class="graphs">
				<div class="tab-content">
					<div class="tab-pane active" id="horizontal-form">
					<c:if test="${status!=0 }">
				
			
						<form class="form-horizontal" id="addForm" method="post" >
							<input type="hidden" name="nid" id="nid" value="${news.nid }"/>
							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">标题
									</label>
								<div class="col-sm-4">
									<input type="text" class="form-control1" id="title"
										placeholder="标题(1~50个汉字)" name="title" value="${news.title }">
								</div>
								  
								<div class="col-sm-5" id="titleMessage">
									<p class="help-block" id="errorTitle"></p>
								</div>
								
							</div>
							
							<div class="form-group">
								<label for="selector1" class="col-sm-2 control-label">所属分类
									</label>
								<div class="col-sm-4">
									<select name="category.cid" id="selector1" class="form-control1" placeholder="所属分类">
											<c:forEach items="${categoryList }" var="category">
												<c:if test="${empty category.child }">
													<option value="${category.cid }">${category.cname}</option>
												</c:if>
											</c:forEach>
									</select>	
								</div>
								<div class="col-sm-5" id="cidMessage">
									<p class="help-block" id="errorCid"></p>
								</div>
							</div>
						
							<div class="form-group">
								 <script id="editor" type="text/plain" name="commontString" style="width:100%;height:360px;">${news.contentString}</script>
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-9 col-sm-offset-3">
										<button class="btn-success btn">修改</button>
									</div>
								</div>
							 </div>
						</form>
						</c:if>
						<c:if test="${status==0 }">
							要修改的文章不存在
						</c:if>
					</div>
				</div>

				<div class="clearfix"></div>
			</div>
	
				
				<div class="copy">
					<p>Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;</p>
				</div>
			</div>
</div>
	<!-- /#wrapper -->
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
$(".help-block").css('color','#EF553A');
window.UEDITOR_HOME_URL="${pageContext.request.contextPath }/js/editor/";
var ue = UE.getEditor("editor");
var runToPage = function(){
toPage('/ilep/admin/news/getAllNews.action?pageNum=1');
};
$("#addForm").validate({
	submitHandler: function(form) {
	$("#cidTitle").text("");
	 $("#errorTitle").text("");
	 if($("#title").val()==''){
	 	$("#errorTitle").text("标题不能为空！");
	 	return false;
	 }
	 if($("#cid").val()==''){
	 	$("#errorCid").text("分类不能为空！");
	 	return false;
	 }
	 var postUrl = "${pageContext.request.contextPath }/admin/news/updateNews.action" ;
	 var nid = $("#nid").val();
	 var title = $("#title").val();
	 var category = $("#selector1").val();
	 var content = ue.getContent();
	 var index = layer.load();
	 var getPage = function(){
			toPage('${pageContext.request.contextPath }/admin/category/getAll.action?pageNum=1');
	};	
		 $.post(postUrl,{title:title,"category.cid":category,contentString:content,nid:nid},function(data){
		 layer.close(index);
		 if(data.status==1){
		 	layer.msg("修改成功");
		 	$("#errorTitle").html("");
		 	setTimeout(runToPage,3000);	 		
	 	}
		 else{
		 	if(!data.contentString){
		 		layer.msg(data.title);
			 	$("#errorTitle").text(data.title);
			 	if(!data.cid){}
			 	else{
			 		$("#cidTitle").text(data.cid);
			 		layer.msg(data.cid);
			 	} 
		 	}else{
		 		layer.msg(data.contentString);
			 	ue.setContent(data.contentString);
		 		
		 	}
		 }	 			 	
		},'json');	
	}
});
});
</script>										