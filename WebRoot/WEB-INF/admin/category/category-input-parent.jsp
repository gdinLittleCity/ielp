<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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


</head>
<body>
	<div id="wrapper">
		<%@include file="../nav.jsp"%>
		<div id="page-wrapper">
			<div class="graphs">
				<div class="tab-content">
					<div class="tab-pane active" id="horizontal-form">
						<form class="form-horizontal" id="addForm" action="${pageContext.request.contextPath }/CategoryAction" method="post">
							<input type="hidden" name="method" value="addOne"/>
							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">一级分类名
									</label>
								<div class="col-sm-4">
									<input type="text" class="form-control1" id="cname"
										placeholder="一级分类名" name="cname" value="${category.cname }">
								</div>
								  
								<div class="col-sm-6">
									<p class="help-block" id="errorCname"></p>
								</div>
								
							</div>
							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">备注
									</label>
								<div class="col-sm-4">
									<input type="text" class="form-control1" id="note"
										placeholder="备注" name="desc" value="${category.desc }">
								</div>
								<div class="col-sm-6">
									<p class="help-block" id="errorDesc"></p>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-9 col-sm-offset-3">
										<button class="btn-success btn">添加</button>
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
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
<script type="text/javascript">
$(function(){
$(".help-block").css('color','#EF553A');
jQuery.validator.addMethod("ChineseAndWord",function(value,element){
 var reg = /^([\u4E00-\u9FA5]|\w)*$/;
 return this.optional(element) || (reg.test(value)); 
},"不能包含特殊字符");

$("#addForm").validate({
	rules:{
		cname:{ required:true,
			    rangelength:[3,50],
			   ChineseAndWord:true,
				remote: {
				    url: "/ilep/admin/category/ajaxCname.action",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        cname: function() {
				            return $("#cname").val();
				       	 }
				    	}
					}
		},
		//desc:{ ChineseAndWord:true}
	},
	
	messages:{
		cname:{required:"分类名不能为空！",
			rangelength: $.format("分类名的最小长度:{0}, 最大长度:{1}"),
			remote:$.format("该分类名已经存在")}
	},

	submitHandler: function(form) {
	 
	 var postUrl = "${pageContext.request.contextPath }/admin/category/saveCategory.action" ;
	 var param = $("#addForm").serialize();
	 var index = layer.load();
	 $.post(postUrl,param,function(data){
		 layer.close(index);
		 	$("#errorCname").text(data.cname);
		 	$("#errorDesc").text(data.desc);
		 	if(data.status==0)
		 		layer.msg("添加失败");
		 	if(data.status==1){
		 		layer.msg("添加成功");
		 		setTimeout(runToPage,3000); 
		 	}		 			 	
		},'json');		
	}
});
//延迟函数使用的包装函数
		function runToPage(){
			toPage('${pageContext.request.contextPath }/admin/category/getAll.action?pageNum=1');
		}	
	
});

</script>
</body>
</html>
