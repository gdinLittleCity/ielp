package cn.edu.gdin.ilep.manager.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import cn.edu.gdin.ilep.manager.domain.Resources;
import cn.edu.gdin.ilep.page.PageBean;

public interface ResourceService {
	/**
	 * 将资源写入磁盘,将资源记录写入数据库
	 * @param file
	 * @param resources
	 * @return
	 * @throws Exception 
	 */
	public String addResource(MultipartFile file,Resources resources,HttpSession session) throws Exception;
	/**
	 * 加载所有资源记录--分页
	 * @param pageBean
	 * @return
	 */
	public PageBean<Resources> getAllResourceInfo(PageBean<Resources> pageBean);
	
	/**
	 * 获取指定的资源信息
	 * @param resources
	 * @return
	 */
	public Resources getResource(Resources resources);
	/**
	 * 更新资源信息
	 * @param resources
	 * @return
	 */
	public int updateResource(Resources resources,HttpSession session);
	
	/**
	 * 删除资源的数据库记录,但不删除资源的磁盘记录
	 * @param ids
	 * @return
	 */
	public int deleteResource(String[] ids);
	

}
