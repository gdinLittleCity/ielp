<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>登录</title>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
 <!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- jQuery -->
<script src="js/jquery-1.10.2.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
</head>
<body id="login">
  <div class="login-logo">
    
  </div>
  <h2 class="form-heading">登录</h2>
  <div class="app-cam">
  	 <div class="col-sm-8">
		<p class="help-block" id="error">${error }</p>
	</div>
	  <form id="loginForm" action="user/login.action" method="post">
		<input type="text" class="text" name="name" id="name" value="account" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'account';}">
		<label id="errorName">${name }</label>
		<input type="password" name="password" id="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">
		<label id="errorPassword">${password }</label>
		<div>
		<a id="aCode" href="javascript:void(0)" onclick="_change()">换一张</a>
		<img id="verifyCode" src="${pageContext.request.contextPath }/show/getVerifyCode.action"/>
        
        
		<input type="text" name="verifyCode" id="inputCode" style="width:30%">
		<label id="errorPassword">${verifyCodeError }</label>
		</div>
		<div class="submit"><input type="submit" value="登录"></div>
		<div class="login-social-link">
          
        </div>
		<ul class="new">
			<li class="new_left"><p class="sign">没有账号?忘记密码 ?请向管理员申请</p></li>
			<div class="clearfix"></div>
		</ul>
	</form>
  </div>
   <div class="copy_layout login">
      <p>Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;</p>
   </div>
   <script type="text/javascript" src="js/security/security.js"></script>
   <script type="text/javascript" src="js/jquery.validate.js"></script>
   <script type="text/javascript" src="js/layer.js"></script>
   <script type="text/javascript" >
   function _change(){
   		$("#verifyCode").attr("src", "${pageContext.request.contextPath }/show/getVerifyCode.action?" + new Date().getTime());
   
   }
   
   
   	$(document).ready(function (){
   	
	  $("#loginForm").validate({
	   	rules:{
		name:{required:true,
			rangelength:[3,18],
		},
		password:{required:true,rangelength:[6,20]},
		verifyCode:{required:true,rangelength:[4,4],
			remote: {
			    url: "/ilep/user/ajaxVerfyCode.action",     //后台处理程序
			    type: "post",               //数据发送方式
			    dataType: "json",           //接受数据格式   
			    data: {                     //要传递的数据
			        verifyCode: function() {
			            return $("#inputCode").val();
			       	 }
			    	}
				}
		
		}
		
	},
	
	messages:{
		name:{required:"用户名不能为空！",
			rangelength: $.format("用户名的最小长度:{0}, 最大长度:{1}(一个汉字为2个字符)"),
			},
		password:{required:"密码不能为空！",
			rangelength: $.format("密码的最小长度:{0}, 最大长度:{1}")},
			
		verifyCode:{required:"验证码不能为空！",
			rangelength:"验证码长度为4位",
			remote:"验证码输入错误"}
	},
	
	submitHandler: function(form) {
	
			$("#password").val(function(){
			var publicKeyExponent = "${publicKeyExponent}";
	  		var publicKeyModulus = "${publicKeyModulus}";
	  		var password = $("#password").val();   
	       	RSAUtils.setMaxDigits(200);  
	       	var key = new RSAUtils.getKeyPair(publicKeyExponent, "", publicKeyModulus);  
	  	  
	      	return  RSAUtils.encryptedString(key,password.split("").reverse().join(""));
			});
		
		   	form.submit();
		 }
	  });
   	
   	
   	});
   </script>
</body>
</html>
