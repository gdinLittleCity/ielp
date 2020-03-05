package com.gdinxiao.show.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.gdinxiao.common.util.Constant;
import com.gdinxiao.common.util.StringTransferUtils;
import com.gdinxiao.manager.dao.NewsDao;
import com.gdinxiao.manager.domain.News;
import com.gdinxiao.page.PageBean;
import com.gdinxiao.page.ShowPageBean;
import com.gdinxiao.show.service.ShowActivityService;
import org.springframework.stereotype.Service;


@Service("showActivityService")
public class ShowActivityServiceImpl implements ShowActivityService {
	
	@Resource(name="newsDao")
	private NewsDao newsDao;
	
	@Override
	public PageBean<News> getActivityList(PageBean<News> pageBean) {
		ShowPageBean<News> show = new ShowPageBean<News>();
		show.setPageNum(pageBean.getPageNum());
		show.setPageSize(Constant.SHOW_ACTIVITY_PAGESIZE);
		show.setCname(Constant.SHOW_CATEGORY_ACTIVITY);
		
		List<News> list = newsDao.selectListByCname(show);
		//转义特殊字符
		for(News news:list){
			news.setTitle(StringTransferUtils.string2html(news.getTitle()));
		}
		int count = newsDao.selectCountByCname(Constant.SHOW_CATEGORY_ACTIVITY);
		pageBean.setPageSize(Constant.SHOW_ACTIVITY_PAGESIZE);
		
		if (null != list && 0 != list.size())
			pageBean.setBeanList(list);
		else
			pageBean.setBeanList(null);
		// 设置总页数
		pageBean.setPageTotal(count % pageBean.getPageSize() == 0 ? count
				/ pageBean.getPageSize() : 1 + count / pageBean.getPageSize());
		
		return pageBean;
	}

}
