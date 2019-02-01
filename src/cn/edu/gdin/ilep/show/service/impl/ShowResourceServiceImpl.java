package cn.edu.gdin.ilep.show.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.gdin.ilep.manager.dao.CategoryDao;
import cn.edu.gdin.ilep.manager.dao.ResourceDao;
import cn.edu.gdin.ilep.manager.domain.Category;
import cn.edu.gdin.ilep.manager.domain.Resources;
import cn.edu.gdin.ilep.manager.service.CategoryService;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.page.ShowPageBean;
import cn.edu.gdin.ilep.show.service.ShowResourceService;
import cn.edu.gdin.ilep.util.Constant;
import cn.edu.gdin.ilep.util.StringTransferUtils;


@Service("showResourceService")
public class ShowResourceServiceImpl implements ShowResourceService {
	
	@Resource()
	private CategoryDao categoryDao;
	
	@Resource(name="resourceDao")
	private ResourceDao resourceDao;
	
	@Override
	public PageBean<Resources> getResourceList(PageBean<Resources> pageBean,
			String showCategory) {
		ShowPageBean<Resources> show = new ShowPageBean<Resources>();
		show.setPageNum(pageBean.getPageNum());
		show.setPageSize(Constant.SHOW_PAGESIZE);
		show.setCname(showCategory);
		
		List<Resources> list = resourceDao.selectListByCname(show);
		
		int count = resourceDao.selectCountByCname(showCategory);
		
		pageBean.setPageSize(Constant.SHOW_PAGESIZE);
		
		if (null != list && 0 != list.size()){
			//转义特殊字符
			for(Resources res:list)
				res.setTitle(StringTransferUtils.string2html(res.getTitle()));
			pageBean.setBeanList(list);
		}
		else
			pageBean.setBeanList(null);
		// 设置总页数
		pageBean.setPageTotal(count % pageBean.getPageSize() == 0 ? count
				/ pageBean.getPageSize() : 1 + count / pageBean.getPageSize());
		
		return pageBean;
	}

	@Override
	public Resources getResourceInfo(String sid) {
		Resources res = null;
		try {
			List<Resources> list = resourceDao.selectResourceByID(sid);
			if(null!=list&&0!=list.size()){
				res = list.get(0);
				res.setTitle(StringTransferUtils.string2html(res.getTitle()));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public File getFile(Resources res) {
		File file = null;
		try{
			file = new File(res.getPath());
		}catch(Exception e){
			throw new RuntimeException("文件不存在");
		}
		return file;
	}

	@Override
	public List<Category> getResCategoryList() {
		
		return categoryDao.selectResourceCategory();
		
	}

}
