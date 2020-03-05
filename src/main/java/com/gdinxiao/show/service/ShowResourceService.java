package com.gdinxiao.show.service;

import com.gdinxiao.manager.domain.Category;
import com.gdinxiao.manager.domain.Resources;
import com.gdinxiao.page.PageBean;

import java.io.File;
import java.util.List;


public interface ShowResourceService {
	/**
	 * 获取资源列表
	 * @param pageBean
	 * @param showCategory
	 * @return
	 */
	PageBean<Resources> getResourceList(PageBean<Resources> pageBean,
										String showCategory);
	/**
	 * 获取资源信息
	 * @param sid
	 * @return
	 */
	Resources getResourceInfo(String sid);
	
	/**
	 * 获取文件
	 * @param res
	 * @return
	 */
	File getFile(Resources res);
	
	/**
	 * 获取所有资源分类
	 * @return
	 */
	List<Category> getResCategoryList();

}
