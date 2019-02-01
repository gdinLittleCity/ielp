<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="graphs">
				<div class="tab-content">
					<div class="tab-pane active" id="horizontal-form">
						<form class="form-horizontal" id="updateForm" action="" method="post">
							<input type="hidden" name="cid" id="cid" value="${category.cid }">
							<div class="form-group">
								<label for="focusedinput" class="col-sm-2 control-label">分类名
									</label>
								<div class="col-sm-4">
									<input type="text" class="form-control1" id="cname"
										placeholder="分类名" name="cname" value="${category.cname }">
								</div>
								  
								<div class="col-sm-2">
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
								<div class="col-sm-2">
									<p class="help-block" id="errorDesc"></p>
								</div>
								
							</div>
							<div class="form-group">
									<label class="col-sm-2 control-label">是否是资源分类</label>
									<div class="col-sm-8">
										<label class="radio-inline">
										  <input type="radio" name="isResource" value="1"> 是
										</label>
										<label class="radio-inline">
										  <input type="radio" name="isResource" value="0" checked> 否
										</label>
									</div>
							</div>
							
							<c:if test="${not empty category.parent }">
							<div class="form-group">
								<label for="selector1" class="col-sm-2 control-label">所属上级分类
									</label>
								<div class="col-sm-4">
									<select name="parent.cid" id="selector1" class="form-control1">
										<c:forEach items="${parents}" var="parent">
											<option value="${parent.cid }">${parent.cname }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							</c:if>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-9 col-sm-offset-3">
										<button class="btn-success btn">修改</button>
									</div>
								</div>
							 </div>
						</form>
					</div>
				</div>
				
			</div>
			<div class="copy container" style="position:fixed;bottom:0;">
					<p>Copyright&copy;2016 信息素质教育在线学习平台 All Right Reserved<br>
        技术支持：计算机网络中心&nbsp;&nbsp;</p>
				</div>
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
$("#updateForm").validate({
rules:{
		cname:{ required:true,
			    rangelength:[3,50],
			    ChineseAndWord:true,
				remote: {
				    url: "/ilep/admin/category/ajaxUpdateCname.action",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        cname: function() {
				            return $("#cname").val();
				       	 },
				       	 cid:function(){return $("#cid").val();}
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
	 var postUrl = "${pageContext.request.contextPath }/admin/category/updateCategory.action" ;
	 var param = $("#updateForm").serialize();
	 var index = layer.load();
		 $.post(postUrl,param,function(data){
		 	$("#errorCname").text("");
		 	$("#errorDesc").text("");
		 	layer.close(index);
		 	$("#errorCname").text(data.cname);
		 	$("#errorDesc").text(data.desc);
		 	if(data.successMsg!=null&&data.successMsg!=""){
		 		layer.msg(data.successMsg);
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
						