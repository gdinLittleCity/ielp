package com.gdinxiao.manager.service;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import com.gdinxiao.manager.domain.Administor;
import com.gdinxiao.page.PageBean;
import com.google.gson.JsonObject;


public interface AdministorService {
	/**
	 * 校验管理员名是否存在
	 * @param name
	 * @return
	 */
	boolean ajaxName(String name);
	/**
	 * 获取管理员信息--分页
	 * @param pageBean
	 * @return
	 */
	PageBean<Administor> getAdminInfo(PageBean<Administor> pageBean);
	/**
	 * 获取管理员信息
	 * @param administor
	 * @return
	 */
	Administor getAdministorInfo(Administor administor);
	/**
	 * 修改管理员信息
	 * @param administor
	 * @return
	 */
	int updateAmin(Administor administor);
	/**
	 * 添加管理员信息
	 * @param administor
	 * @return
	 */
	int addAdmin(Administor administor);
	/**
	 * 将密钥对设置到session
	 * @param session
	 * @throws NoSuchAlgorithmException
	 */
	public void pushKeyIntoSession(HttpSession session) throws NoSuchAlgorithmException ;
	/**
	 * 修改信息时,检验管理员名是否可用
	 * @param aid
	 * @param name
	 * @return
	 * 可用返回 true,不可用返回false
	 */
	boolean getAdmin(String aid, String name);
	/**
	 * 登录
	 * @param name
	 * @param password
	 * @return
	 */
	Administor login(String name, String password);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	int deleteResource(String[] ids);
	/**
	 * 修改密码之前的校验
	 * @param administor
	 * @param gson
	 * @return
	 * json对象
	 */
	JsonObject validateAdmin(Administor administor, JsonObject gson, HttpSession session);
	/**
	 * 异步校验密码
	 * @param aid
	 * @param password
	 * @return
	 * 密码正确--返回true,密码错误--返回false
	 */
	boolean ajaxPassword(String aid, String password);
	/**
	 * 异步检验手机号码
	 * @param aid
	 * @param phone
	 * @return
	 * 号码正确--返回true,号码错误--返回false
	 */
	boolean ajaxPhone(String aid, String phone);
	/**
	 * 修改密码的Json处理
	 * @param administor
	 * @param gson
	 * @return
	 */
	JsonObject updatePassword(Administor administor, JsonObject gson);
	
	/**
	 * 修改个人名称,电话号码
	 * @param administor
	 * @return
	 */
	int updateUser(Administor administor);
}
