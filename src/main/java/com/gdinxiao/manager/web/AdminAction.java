package com.gdinxiao.manager.web;

import java.security.interfaces.RSAPrivateKey;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gdinxiao.common.util.Constant;
import com.gdinxiao.common.util.RSAUtils;
import com.gdinxiao.manager.domain.Administor;
import com.gdinxiao.manager.service.AdministorService;
import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class AdminAction {
//	private static final Logger log = Logger.getLogger(AdminAction.class);
	@Resource(name="administorService")
	private AdministorService administorService;
	
	@RequestMapping("/tologin")
	public String tologin(HttpSession session) throws Exception{
		
		if(null==session.getAttribute(Constant.RSA_PRIVATE_KEY)||
				null==session.getAttribute(Constant.RSA_PUBLIC_KEY_EXPONET)||
				null==session.getAttribute(Constant.RSA_PUBLIC_KEY_MODULUS))
			administorService.pushKeyIntoSession(session);
//		log.info("公钥发布成功!!跳转到登录页面admin-login.jsp");
		return "/admin/admin-login";
		
	}
	
	/**
	 * 登录
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpSession session) throws Exception{
		String name = request.getParameter("name");
		String psd = request.getParameter("password");
		String verifyCode =  request.getParameter("verifyCode");
		
		if(null==name||"".equals(name.trim())){
			request.setAttribute("name", Constant.ADMIN_LOGIN_NAME_ERROR);
//			log.info("登录错误,用户名为空");
			return "/admin/admin-login";
		}
		
		if(null==psd||StringUtils.isEmpty(psd)){
			request.setAttribute("error", Constant.ADMIN_LOGIN_ERROR);
//			log.info("登录错误,密码为空");
			return "/admin/admin-login";
		}
		
		if(StringUtils.isBlank(verifyCode)||StringUtils.isEmpty(verifyCode)||!verifyCode.equals((String)session.getAttribute("verifyCode"))){
			request.setAttribute("verifyCodeError", "验证码错误(验证码区分大小写)");
//			log.info("登录错误,验证码错误");
			return "/admin/admin-login";
		}
		
		
		//验证密码长度等信息
		RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(Constant.RSA_PRIVATE_KEY);
		String password = RSAUtils.decryptByPrivateKey(psd, privateKey);
		if(password.trim().equals("")||null==password||(password.length()<6||password.length()>20)){
			request.setAttribute("password", Constant.ADMIN_PASSWORD_LENGTH);
//			log.info("登录错误,密码长度错误");
			return "/admin/admin-login";
		}
		
		//用户名,密码校验
		Administor admin =  administorService.login(name,password);
		if(null==admin){
			request.setAttribute("error", Constant.ADMIN_LOGIN_ERROR);
//			log.info("登录错误,用户名或密码错误");
			return "/admin/admin-login";
		}
		
		//将用户保存到session中
		Administor user = new Administor();
		user.setAid(admin.getAid());
		user.setName(admin.getName());
		user.setPower(admin.getPower());
		session.setAttribute(Constant.ADMIN_USER, user);
//		log.info("用户:"+user.getName()+"登录成功");
		return "redirect:/admin/index.action";
	}
	
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		Administor user = (Administor) session.getAttribute(Constant.ADMIN_USER);
		
		if(null==user)
			return  "redirect:/user/tologin.action";
		else{
			session.removeAttribute(Constant.ADMIN_USER);
//			log.info("用户："+user.getName()+"退出登录");
			return  "redirect:/user/tologin.action";
		}
			
	}
	
	/**
	 * 校验验证码
	 * @param session
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping("/ajaxVerfyCode")
	@ResponseBody
	public String ajaxVerfyCode(HttpSession session,String verifyCode){
		String code = (String) session.getAttribute("verifyCode");
		
		if(code.equals(verifyCode))
			return "true";
		else
			return "false";
	}
	
	
}
