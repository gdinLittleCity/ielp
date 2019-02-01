package cn.edu.gdin.ilep.manager.web;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.gdin.ilep.manager.domain.Administor;
import cn.edu.gdin.ilep.manager.service.AdministorService;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.util.Constant;
import cn.edu.gdin.ilep.util.RSAUtils;

import com.google.gson.JsonObject;

@Controller
@RequestMapping("/admin/manager")
public class AdministorManageAction {
	@Resource(name="administorService")
	private AdministorService administorService;
	/**
	 * 跳转到添加管理员页面
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/super/administor")
	public String administor(HttpSession session) throws Exception{
		if(null==session.getAttribute(Constant.RSA_PRIVATE_KEY)||
				null==session.getAttribute(Constant.RSA_PUBLIC_KEY_EXPONET)||
				null==session.getAttribute(Constant.RSA_PUBLIC_KEY_MODULUS))
			administorService.pushKeyIntoSession(session);
		
		
		return "admin/manager/manager-input";
	}
	
	/**
	 * 修改密码预处理
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatePasswordPre")
	public String updatePasswordPre(HttpSession session) throws Exception{
		if(null==session.getAttribute(Constant.RSA_PRIVATE_KEY)||
				null==session.getAttribute(Constant.RSA_PUBLIC_KEY_EXPONET)||
				null==session.getAttribute(Constant.RSA_PUBLIC_KEY_MODULUS))
			administorService.pushKeyIntoSession(session);
		return "admin/manager/manager-update-password";
		
	}
	/**
	 * 异步校验管理员名是否可用
	 * @param request
	 * @return
	 */
	@RequestMapping("/super/ajaxName")
	@ResponseBody
	public String ajaxName(HttpServletRequest request){
		String name = request.getParameter("name");
		
		boolean valite = administorService.ajaxName(name);
		
		if(valite)
			return "false";
		return "true";
	}
	/**
	 * 修改管理员信息时,异步校验管理名是否可用
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajaxUpdateName")
	@ResponseBody
	public String ajaxUpdateName(HttpServletRequest request){
		String name = request.getParameter("name");
		String aid = request.getParameter("aid");
		boolean valite = administorService.getAdmin(aid,name);
		
		if(valite)
			return "true";
		return "false";
	}
	/**
	 * 个人修改密码时校验原始密码是否正确
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajaxPassword")
	@ResponseBody
	public String ajaxPassword(HttpServletRequest request,HttpSession session) throws Exception{
		String psd = request.getParameter("password");
		String aid = request.getParameter("aid");
		
		RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(Constant.RSA_PRIVATE_KEY);
		
		String password = RSAUtils.decryptByPrivateKey(psd, privateKey);
		
		boolean valite = administorService.ajaxPassword(aid,password);
		
		if(valite)
			return "true";
		return "false"; 
	}
	/**
	 * 个人修改密码时校验个人手机号码是否正确
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajaxPhone")
	@ResponseBody
	public String ajaxPhone(HttpServletRequest request){
		String phone = request.getParameter("phone");
		String aid = request.getParameter("aid");
		
		boolean valite = administorService.ajaxPhone(aid,phone);
		
		if(valite)
			return "true";
		return "false"; 
		
	}
	
	/**
	 * 添加管理员
	 * @param administor
	 * @param result
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/super/addAdministor")
	@ResponseBody
	public String addAdministor(@Validated Administor administor,BindingResult result,HttpSession session) throws Exception{
		JsonObject gson = new JsonObject();
		
		List<FieldError> errors = result.getFieldErrors();
		if(null!=errors&&0!=errors.size()){
			for(FieldError error:errors)
				gson.addProperty(error.getField(), error.getDefaultMessage());
			return gson.toString();
		}
		if(!administor.getRepassword().equals(administor.getPassword())){
			gson.addProperty("repassword",Constant.ADMIN_EQUAL);
			return gson.toString();
		}
		RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(Constant.RSA_PRIVATE_KEY);
		
		String password = RSAUtils.decryptByPrivateKey(administor.getPassword(), privateKey);
		if(password.length()<6||password.length()>30){
			gson.addProperty("password",Constant.ADMIN_PASSWORD_LENGTH);
			return gson.toString();
		}
			
		administor.setPassword(password);
		
		
		int count = administorService.addAdmin(administor);
		
		if(count<=0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson.toString();
	}
	/**
	 * 获取管理员信息列表
	 * @param pageBean
	 * @return
	 */
	@RequestMapping("/super/getAdminInfo")
	public String getAdminInfo(PageBean<Administor> pageBean){
		if(pageBean==null)
			pageBean = new PageBean<Administor>();
		pageBean.setPageSize(4);
		
		pageBean =  administorService.getAdminInfo(pageBean);
		
		return "admin/manager/manager-item";
	}
	/**
	 * 跳转到修改页面
	 * @param administor
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/super/updateAdministorPre")
	public String updateAdministorPre(Administor administor,Model model,HttpSession session) throws Exception{
		if(null==administor.getAid()||"".equals(administor.getAid())){
			model.addAttribute("status", 0);
			return "admin/manager/manage-update";
		}
		
		administor = administorService.getAdministorInfo(administor);
		if(null==administor){
			model.addAttribute("status", 0);
			return "admin/manager/manager-update";
		}
		
		if(null==session.getAttribute(Constant.RSA_PRIVATE_KEY)||
				null==session.getAttribute(Constant.RSA_PUBLIC_KEY_EXPONET)||
				null==session.getAttribute(Constant.RSA_PUBLIC_KEY_MODULUS))
			administorService.pushKeyIntoSession(session);
		
		model.addAttribute("admin", administor);
		
		return "admin/manager/manager-update";
	}
	/**
	 * 异步请求修改管理员信息
	 * @param administor
	 * @param result
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/super/updateAdministor")
	@ResponseBody
	public String updateAdministor(@Validated Administor administor,BindingResult result,HttpSession session) throws Exception{
		JsonObject gson = new JsonObject();
		
		List<FieldError> errors = result.getFieldErrors();
		if(null!=errors&&0!=errors.size()){
			for(FieldError error:errors)
				gson.addProperty(error.getField(), error.getDefaultMessage());
			return gson.toString();
		}
		if(!administor.getRepassword().equals(administor.getPassword())){
			gson.addProperty("equal",Constant.ADMIN_EQUAL);
			return gson.toString();
		}
		
		RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(Constant.RSA_PRIVATE_KEY);
		
		String password = RSAUtils.decryptByPrivateKey(administor.getPassword(), privateKey);
		if(password.length()<6||password.length()>30){
			gson.addProperty("password",Constant.ADMIN_PASSWORD_LENGTH);
			return gson.toString();
		}
			
		administor.setPassword(password);
		
		int count = administorService.updateAmin(administor);
		
		if(count<=0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson.toString();
		
	}
	/**
	 * 删除管理员信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("/super/deleteAdmin")
	@ResponseBody
	public String deleteAdmin(String[] ids){
		JsonObject gson =new JsonObject();
		if(0==ids.length||null==ids){
			gson.addProperty("IDNone", Constant.ADMIN_ID_NONE);
			return gson.toString();
		}
			
		
		int count = administorService.deleteResource(ids);
		
		if(count<0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson.toString();
		
		
	}
	/**
	 * 异步请求修改个人密码
	 * @param request
	 * @param administor
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public String updatePassword(HttpServletRequest request,Administor administor,HttpSession session) throws Exception{
		JsonObject gson = new JsonObject();
		
		RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(Constant.RSA_PRIVATE_KEY);
		
		
		
		String newpassword = request.getParameter("newpassword");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		if(null!=newpassword&&null!=password&&null!=repassword){
			newpassword = RSAUtils.decryptByPrivateKey(newpassword, privateKey);
			password = RSAUtils.decryptByPrivateKey(password, privateKey);
			repassword = RSAUtils.decryptByPrivateKey(repassword, privateKey);
		}else{
			gson.addProperty("password", "原始密码、新密码、确认密码不能为空！");
			return gson.toString();
		}
		
		if(!administorService.ajaxPassword(administor.getAid(), password)){
			gson.addProperty("password", "原始密码错误");
			return gson.toString();
		}
		
		if(newpassword.trim().equals("")||(6>newpassword.length()||20<newpassword.length())){
			gson.addProperty("newpassword", Constant.ADMIN_PASSWORD_LENGTH);
			return gson.toString();
		}
		
		if(!newpassword.equals(repassword)){
			gson.addProperty("repassword",Constant.ADMIN_EQUAL);
			return gson.toString();
		}
		administor.setPassword(newpassword);
		administor.setRepassword(repassword);
		gson = administorService.validateAdmin(administor,gson,session);
		
		
		return gson.toString();
		
	}
	
	@RequestMapping("/updateUserPre")
	public String updateUserPre(){
		
		return "admin/manager/manager-setting";
	}
	
	
	/**
	 * 修改管理员信息
	 * @param administor
	 * @return
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(Administor administor){
		JsonObject gson = new JsonObject();
		String name = administor.getName(),phone = administor.getPhone();
		if(name!=null&&phone!=null){
			if(name.trim().equals("")||3>name.length()||20<name.length()){
				gson.addProperty("name", Constant.ADMIN_LOGIN_NAME_ERROR);
				return gson.toString();
			}
				
			if(phone.trim().equals("")||phone.length()!=11){
				gson.addProperty("phone", Constant.ADMIN_PHONE_LENGTH);
				return gson.toString();
			}
			
		}else{
			gson.addProperty("name",Constant.ADMIN_LOGIN_NAME_ERROR+","+Constant.ADMIN_PHONE_LENGTH);
			return gson.toString();
		}
			
		
		int count = administorService.updateUser(administor);
			
		if(count<=0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson.toString();
	}
	
	
}
