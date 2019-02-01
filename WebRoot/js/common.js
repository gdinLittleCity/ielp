
/*
 * @author gdinxiao
 * 前置条件：1.JQuery 1.10.2.js
 * 2.layer.js
 */
//上一页,下一页处理函数
function next_pre_Page(url,pageNum,pageTotal){
	if(pageNum<=0)
		{
		alert("这是第一页,没有上一页了");
		return false;
		}
	if(pageTotal<pageNum){
		alert("这是最后,没有下一页了");
		return false;
		}
		var index = layer.load();
		$.ajax({
				url: url,
				cache: false,
				data:{pageNum:pageNum},
				success: function(html){
				layer.close(index);
					$("#page-wrapper").empty();
					$("#page-wrapper").html(html);
				},
				 dataType: 'text'
			});
	}
//异步加载内容到内容页	
function toPage(url){
	var index = layer.load();
			$.ajax({
				url: url,
				cache: false,
				success: function(html){
				layer.close(index);
					$("#page-wrapper").empty();
					$("#page-wrapper").html(html);
				},
				 dataType: 'text'
			});
		}
//删除的异步处理函数	
	function deleteCategory(url){
		var index = layer.load();
	 $.post(url,function(data){
	 	layer.close(index);
	 	
	 	if(data.status==9999){
	 		layer.alert('该目录下还有二级目录,不能直接删除', {
	 			  skin: 'layui-layer-molv' //样式类名
	 			  ,closeBtn: 0
	 			}, function(){
	 			  layer.alert('先删除该目录下的二级目录吧', {
	 			    skin: 'layui-layer-lan'
	 			    ,closeBtn: 0
	 			    ,shift: 4 //动画类型
	 			  });
	 			});
	 	}
	 	if(data.successMsg!=null&&data.successMsg!=""){
	 		layer.msg(data.successMsg);
	 		setTimeout(runToPage,3000);
	 	}
	 	if(data.failMsg!=null&&data.failMsg!=""){
	 		layer.msg(data.failMsg);
	 	}	 			 	 	
	 },'json');	
	}
//删除文章函数
	function deleteNews(url){
		var index = layer.load();
		var runToPage =function(){
			toPage('/ilep/admin/news/getAllNews.action?pageNum=1');
		};	
		 $.post(url,function(data){
		 	layer.close(index);
		 	
		 	if(data.status==1){
		 		layer.msg("删除文章成功");
		 		setTimeout(runToPage,3000);
		 	}
		 	if(data.status==0){
		 		layer.msg("删除失败,稍后再来试试");
		 	}	 			 	 	
		 },'json');	
		
	}
	
	
	
	