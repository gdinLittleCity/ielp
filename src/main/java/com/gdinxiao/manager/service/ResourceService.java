package com.gdinxiao.manager.service;

import com.gdinxiao.manager.domain.Resources;
import com.gdinxiao.page.PageBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


public interface ResourceService {
	/**
	 * 将资源写入磁盘,将资源记录写入数据库
	 * @param file
	 * @param resources
	 * @return
	 * @throws Exception 
	 */
	String addResource(MultipartFile file, Resources resources, HttpSession session) throws Exception;
	/**
	 * 加载所有资源记录--分页
	 * @param pageBean
	 * @return
	 */
	PageBean<Resources> getAllResourceInfo(PageBean<Resources> pageBean);
	
	/**
	 * 获取指定的资源信息
	 * @param resources
	 * @return
	 */
	Resources getResource(Resources resources);
	/**
	 * 更新资源信息
	 * @param resources
	 * @return
	 */
	int updateResource(Resources resources, HttpSession session);
	
	/**
	 * 删除资源的数据库记录,但不删除资源的磁盘记录
	 * @param ids
	 * @return
	 */
	int deleteResource(String[] ids);
	

}
