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
<script
	src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
<!-- Nav CSS -->
<link href="${pageContext.request.contextPath }/css/custom.css"
	rel="stylesheet">
<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath }/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath }/js/custom.js"></script>

</head>
<body>
	<div id="wrapper">
		<%@include file="../nav.jsp"%>
		<div id="page-wrapper">
			<div class="graphs">
				<div class="tab-content">
					<div class="tab-pane active" id="horizontal-form">
						<form class="form-horizontal"
							action="${pageContext.request.contextPath }/admin/resource/uploadFile/do.action"
							id="addForm" enctype="multipart/form-data" method="post">
							<div class="form-group">
								<c:if test="${not empty message }">
								<label class="col-sm-5 control-label" style="color:red">
									${message } </label>
								</c:if>
								<c:if test="${not empty fileType }">
								<label class="col-sm-5 control-label" style="color:red">
									${fileType } </label>
								</c:if>
								<c:if test="${not empty fileNone }"> 
									<label class="col-sm-5 control-label"
									style="color:red"> ${fileNone } </label>
								</c:if>
								<c:if test="${not empty fileSize }"> 
									<label class="col-sm-5 control-label" style="color:red">
									${fileSize } </label>
								</c:if>
							</div>


							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">资源标题
								</label>
								<div class="col-sm-4">
									<input type="text" class="form-control1" id="title"
										placeholder="资源标题(1~50个汉字)" name="title"
										value="${resource.title }">
								</div>

								<div class="col-sm-5" id="titleMessage">
									<p class="help-block" id="errorTitle">${title.DefaultMessage}</p>
								</div>

							</div>

							<div class="form-group">
								<label for="selector1" class="col-sm-2 control-label">所属分类
								</label>
								<div class="col-sm-4">
									<select name="category.cid" id="selector1"
										class="form-control1" placeholder="所属分类">
										<c:forEach items="${categoryList }" var="category">
											<option value="${category.cid }">${category.cname}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-5" id="CIDMessage">
									<p class="help-block" id="errorCID">${CIDnone}</p>
								</div>
							</div>

							<div class="form-group">

								<label for="inputFile" class="col-sm-2 control-label">上传文件</label>
								<div class="col-sm-6">
									<input type="file" id="inputFile" name="file" onchange="checkfile(this)">
								</div>
								<script type="text/javascript">
									/*校验文件大小及类型*/
								function checkfile(param){
								        var maxMb = 110;//最大为110M
								        if(typeof(param.files[0])=='undefined'){
								        	layer.alert("您还没选中文件,请重新上传");
								        	return false;
								        }  
								        var fileSize = param.files[0].size/1024/1024;//获取文件大小，单位是M  
								        if(fileSize>maxMb){
								        	layer.alert("上传的文件大小为："+Math.round(fileSize)+"M.大于110M,请重新上传");
								        	return false;
								        }  
								        var file = $("#inputFile").val();//获取文件路径  
								        var sp = file.lastIndexOf(".");//获取文件路径中.的位置  
								        var sufix = file.substring(sp+1).toLowerCase();//获取文件后缀并将它大写  
								        var type = "doc,docx,xls,xlsx,txt,pdf,ppt,pptx,zip,rar";  
								        if(type.search(sufix)<0){//设定格式判断  
								            layer.alert("格式不正确，请选择doc,docx,xls,xlsx,ppt,pptx,txt,pdf,zip,rar中的一种");
								            return false;  
								        }  
								        return true;  
								    }  
								</script>
							</div>

							
							<div class="form-group">
							<label for="inputFile" class="col-sm-2 control-label"></label>
								<div class="progress col-sm-6" id="progressPra">
									<div class="progress-bar progress-bar-primary" id="progress"
										style="width: 0%"></div>
								</div>

							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-9 col-sm-offset-3">
										<button type="button" id="sub" class="btn btn-default">上传</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>

				<div class="clearfix"></div>
			</div>


			<div class="copy container" style="position:fixed;bottom:0;">
				<p>Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;</p>
			</div>

		</div>
	</div>
	<!-- /#wrapper -->
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/layer.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/common.js" charset="UTF-8"></script>

<script type="text/javascript">  
$(document).ready(function() {


/*ajax请求进度百分比*/
function setProgress() {
$.ajax({
        type: "post",
        dataType: "text",
        url: "/ilep/admin/resource/getProgress.action",
        data: "",
        success: function (data) {          
            $("#progress").css("width",data+"%").html(data+"%");
        },
        error: function (err) {
          	
        }
    });
}
/*进度条控制*/
function progressLoop(){
	 	var x = $("#progress").width();
	    var y = $("#progressPra").width();
	 	if (x<y) {
	    setTimeout(function(){setProgress();}, 500);
	    setTimeout(function(){progressLoop();}, 500);
	    }
}   


	$("#sub").on("click",function(){
	if(checkfile($("#inputFile").get(0))){
		progressLoop();
		$("#addForm").submit();
		}
	}); 


 

    
});   
</script>
</body>
</html>
