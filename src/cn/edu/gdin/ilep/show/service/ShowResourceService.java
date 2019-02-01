package cn.edu.gdin.ilep.show.service;

import java.io.File;
import java.util.List;

import cn.edu.gdin.ilep.manager.domain.Category;
import cn.edu.gdin.ilep.manager.domain.Resources;
import cn.edu.gdin.ilep.page.PageBean;

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
