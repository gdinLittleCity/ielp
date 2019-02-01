package cn.edu.gdin.ilep.util;

public class Constant {
	//默认分页的单页大小
	public static final int PAGE_SIZE=5;
	//默认分页的当前页码
	public static final int PAGE_NUM=1;
	
	public static final int PAGE_SIZE_EIGHT=8;
	
	public static final String CATEGORY_DELETE_FAILED="删除失败，再试试吧";
	
	public static final String CATEGORY_DELETE_SUCCESS="删除成功";
	
	public static final int CATEGORY_SAVE_PARENT_NULL=3;
	
	public static final int CATEGORY_DELETE_CHILD_NOT_NULL=9999;
	
	
	public static final int SUCCESS=1;
	
	public static final int FAIL=0;
	
	public static final  int CATEGORY_ISRESOURCE_DEFAULT_NOT=0;
	
	public static final String RESOURCE_INSERT_ERROR="添加失败";
	
	public static final String RESOURCE_INSERT_SUCCESS="添加成功";
	
	public static final String RESOURCE_ID_NONE = "您所要删除的信息的ID错误或不存在";
	
	public static final String UPLOAD_PATH= "E:/upload";
	
	public static final String UPLOAD_FILE_TYPE="doc,docx,xls,xlsx,txt,pdf,ppt,pptx,zip,rar";
	
	public static final long  UPLOAD_FILE_SIZE=110;
	
	public static final String UPLOAD_TYPE_ERRORMESSAGE="文件类型错误,不允许的文件类型";
	
	public static final String UPLOAD_REQUEST_ERROR="请求类型错误或未选择文件";
	
	public static final String UPLOAD_NONE_FILE="未选中文件上传,请重新选择文件上传";
	
	public static final String UPLOAD_FILE_SIZE_ERRORMESSAGE="文件大小不应该超过110M";
	
	public static final String UPLOAD_WRITE_ERROR="资源写入失败";
	
	public static final String UPLOAD_CID_NONE="分类不能为空";
	
	public static final String ADMIN_EQUAL="确认密码与密码不一致";
	public static final String ADMIN_PASSWORD_LENGTH="密码长度应该在6~20位之间";
	public static final int ADMIN_POWER_DEFAULT_NOTSUPER=0;
	//私钥
	public static final String RSA_PRIVATE_KEY = "privateKey";
	//公钥指数
	public static final String RSA_PUBLIC_KEY_EXPONET = "publicKeyExponent";
	//公钥模
	public static final String RSA_PUBLIC_KEY_MODULUS = "publicKeyModulus";
	
	public static final String ADMIN_LOGIN_NAME_ERROR = "用户名不能为空";
	
	public static final String ADMIN_LOGIN_ERROR = "用户名或者密码错误";
	
	public static final String ADMIN_USER = "user";
	
	public static final String ADMIN_ID_NONE = "您所要删除的管理员的ID错误或不存在";
	
	public static final String ADMIN_PHONE_LENGTH = "号码长度应该为11位";
	
	public static final int ADMIN_SUPER = 1;
	
	public static final String SHOW_CATEGORY_NEWS = "新闻动态";
	
	public static final String SHOW_CATEGORY_QUALITY = "信息素质";
	
	public static final String SHOW_CATEGORY_OBTAIN = "信息获取";
	
	public static final String SHOW_CATEGORY_IDENTIFY = "信息甄别";
	
	public static final String SHOW_CATEGORY_ACTIVITY = "科普活动报道";
	
	public static final String SHOW_CATEGORY_LAW = "相关法律法规";
	
	public static final String SHOW_CATEGORY_CASE = "案例";
	
	public static final String SHOW_CATEGORY_PPT = "讲座PPT下载";
	
	public static final String SHOW_CATEGORY_CRITICAL = "批判性思维慕课";
	
	public static final String SHOW_CATEGORY_SEARCH = "信息检索慕课";
	
	public static final int SHOW_ACTIVITY_PAGESIZE = 12;
	
	public static final int SHOW_PAGESIZE = 15;
	
	public static final String NEWS_INDEXURL = "E:/index/news";
	
	public static final String RESOURCE_INDEXURL = "E:/index/resource";
	
	public static final  int SEARCHER_PAGESIZE = 20;
	
	public static final  int SEARCHER_TOPSIZE = 50;
	
}
