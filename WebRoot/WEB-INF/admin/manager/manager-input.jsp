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

</head>
<body>
	<div id="wrapper">
		<%@include file="../nav.jsp"%>
		<div id="page-wrapper">
			<div class="graphs">
				<div class="tab-content">
					<div class="tab-pane active" id="horizontal-form">
						<form class="form-horizontal" id="addForm" method="post">
							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">管理员名
									</label>
								<div class="col-sm-4">
									<input type="text" class="form-control1" id="name"
										placeholder="管理员名" name="name" value="${administor.name }">
								</div>
								  
								<div class="col-sm-2" id="nameMessage">
									<p class="help-block" id="errorName"></p>
								</div>
								
							</div>
							
							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">密码
									</label>
								<div class="col-sm-4">
									<input type="password" class="form-control1" id="password"
										placeholder="密码" name="password" value="${administor.password }">
								</div>
								  
								<div class="col-sm-2" id="passwordMessage">
									<p class="help-block" id="errorPassword"></p>
								</div>
								
							</div>
							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">确认密码
									</label>
								<div class="col-sm-4">
									<input type="password" class="form-control1" id="repassword"
										placeholder="确认密码" name="repassword" value="${administor.repassword }">
								</div>
								  
								<div class="col-sm-2" id="repasswordMessage">
									<p class="help-block" id="errorRepassword"></p>
								</div>
								
							</div>
							
							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">手机号码
									</label>
								<div class="col-sm-4">
									<input type="text" class="form-control1" id="phone"
										placeholder="手机号码" name="phone" value="${administor.phone }">
								</div>
								  
								<div class="col-sm-2" id="phoneMessage">
									<p class="help-block" id="errorPhone"></p>
								</div>
								
							</div>
							
							<div class="form-group">
									<label class="col-sm-2 control-label">是否是超级管理员</label>
									<div class="col-sm-8" id="wrap">
										<label class="radio-inline">
										  <input type="radio" name="power" value="1" > 是
										</label>
										<label class="radio-inline">
										  <input type="radio" name="power"  value="0" checked class="{required:true}"> 否
										</label>
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
			<div class="col-sm-10" style="position:fixed;bottom:0;">
				<div class="clearfix"></div>
				<div class="copy">
					<p>Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;</p>
				</div>
			</div>
		</div>
	</div>
	<!-- /#wrapper -->
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/security/security.js"></script>
<script type="text/javascript">
$(function(){
$(".help-block").css('color','#EF553A');
jQuery.validator.addMethod("ChineseAndWord",function(value,element){
 var reg = /^([\u4E00-\u9FA5]|\w)*$/;
 return this.optional(element) || (reg.test(value)); 
},"不能包含特殊字符");

jQuery.validator.addMethod("isPhone",function(value,element){
return this.optional(element)||(/^[0-9]{11}/.test(value));
},"此项只能输入数字,且必须是11位");
$("#addForm").validate({
	rules:{
		name:{required:true,
			rangelength:[3,18],
			ChineseAndWord:true,
			remote: {
			    url: "/ilep/admin/manager/super/ajaxName.action",     //后台处理程序
			    type: "post",               //数据发送方式
			    dataType: "json",           //接受数据格式   
			    data: {                     //要传递的数据
			        name: function() {
			            return $("#name").val();
			       	 }
			    	}
				}
		},
		password:{required:true,rangelength:[6,20]},
		repassword:{required:true,equalTo:"#password"},
		phone:{required:true,rangelength:[11,11],
		isPhone:true
		}
	},
	
	messages:{
		name:{required:"用户名不能为空！",
			rangelength: $.format("用户名的最小长度:{0}, 最大长度:{1}(一个汉字为2个字符)"),
			remote:$.format("用户名已经被注册")},
		password:{required:"密码不能为空！",
			rangelength: $.format("密码的最小长度:{0}, 最大长度:{1}")},
		repassword:{
			required:"确认密码不能为空！",
			equalTo:"确认密码和密码不一致！"},
		phone:{required:"手机号码不能为空！",
			rangelength: $.format("号码长度应该是{0}位")
			}
	},
	
	submitHandler: function(form) {
	$("#errorName").text("");
	$("#errorPassword").text("");
	$("#errorRepassword").text("");
	$("#errorPhone").text("");
	var param = {
	 name:function(){return $("#name").val()},
	 password:function(){
	 	var publicKeyExponent = "${publicKeyExponent}";
  		var publicKeyModulus = "${publicKeyModulus}";
  		var password = $("#password").val();   
       	RSAUtils.setMaxDigits(200);  
       	var key = new RSAUtils.getKeyPair(publicKeyExponent, "", publicKeyModulus);  
  	  
      	return  RSAUtils.encryptedString(key,password.split("").reverse().join(""));
  		},
  	repassword:function(){
  		var publicKeyExponent = "${publicKeyExponent}";
  		var publicKeyModulus = "${publicKeyModulus}";
  		var repassword = $("#repassword").val();   
       	RSAUtils.setMaxDigits(200);  
       	var key = new RSAUtils.getKeyPair(publicKeyExponent, "", publicKeyModulus);  
  	  
      	return  RSAUtils.encryptedString(key,repassword.split("").reverse().join(""));
       },
  	phone:function(){return $("#phone").val();},
  	power:function(){return $("#wrap input[name='power']:checked").val();}
	 };
	
	 var postUrl = "${pageContext.request.contextPath }/admin/manager/super/addAdministor.action" ;
	 var index = layer.load();
	 $.post(postUrl,param,function(data){
		 layer.close(index);
		 	if(data.status==1){
		 		layer.msg("添加成功");
		 		setTimeout(function(){
		 			toPage('${pageContext.request.contextPath }/admin/manager/super/getAdminInfo.action?pageNum=1');
		 		},3000); 
		 	}else{
		 		layer.msg("添加失败,请检查所填信息是否按照要求");
		 		$("#errorName").text(data.name);
		 		$("#errorPassword").text(data.password);
		 		$("#errorRepassword").text(data.repassword);
		 		$("#errorPhone").text(data.phone);
		 	}
		 			 			 	
		},'json');		
	}
});

		
	
});

</script>
</body>
</html>
