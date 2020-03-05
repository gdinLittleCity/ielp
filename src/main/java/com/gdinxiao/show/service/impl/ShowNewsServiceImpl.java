package com.gdinxiao.show.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import com.gdinxiao.common.util.Constant;
import com.gdinxiao.common.util.StringTransferUtils;
import com.gdinxiao.manager.dao.NewsDao;
import com.gdinxiao.manager.domain.News;
import com.gdinxiao.page.PageBean;
import com.gdinxiao.page.ShowPageBean;
import com.gdinxiao.show.service.ShowNewsService;
import org.springframework.stereotype.Service;


@Service("showNewsService")
public class ShowNewsServiceImpl implements ShowNewsService {
	@Resource(name="newsDao")
	private NewsDao newsDao;
	
	@Override
	public List<News> newsList() {
		
		List<News> list = newsDao.selectByCname(Constant.SHOW_CATEGORY_NEWS);
		
		if(null==list||0==list.size())
			return null;
		
		else{
			//转义特殊字符
			for(News news:list){
				news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			}
			return list;
		}
		
	}

	@Override
	public List<News> qualityList() {
		List<News> list = newsDao.selectByCname(Constant.SHOW_CATEGORY_QUALITY);
		
		if(null==list||0==list.size())
			return null;
		else{
			
			//转义特殊字符
			for(News news:list){
				news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			}
			return list;
		}
	}

	@Override
	public List<News> obtainList() {
		List<News> list = newsDao.selectByCname(Constant.SHOW_CATEGORY_OBTAIN);
		
		if(null==list||0==list.size())
			return null;
		else{
			//转义特殊字符
			for(News news:list){
				news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			}
			return list;
		}
	}

	@Override
	public List<News> identifyList() {
		List<News> list = newsDao.selectByCname(Constant.SHOW_CATEGORY_IDENTIFY);
		
		if(null==list||0==list.size())
			return null;
		else{
			//转义特殊字符
			for(News news:list){
				news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			}
			return list;
		}
	}

	@Override
	public List<News> activityList() {
		List<News> list = newsDao.selectByCname(Constant.SHOW_CATEGORY_ACTIVITY);
		
		if(null==list||0==list.size())
			return null;
		else{
			//转义特殊字符
			for(News news:list){
				news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			}
			return list;
		}
	}
	
	@Override
	public News getArticlInfo(String nid) {
		News news = null;
		try {
			List<News> list = newsDao.selectById(nid);
			if(null!=list&&0!=list.size()){
				news = list.get(0);
				//转义特殊字符
				news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(news==null)
			return null;
		return news;
	}

	@Override
	public PageBean<News> getNewsList(PageBean<News> pageBean,
									  String showCategory) {
		ShowPageBean<News> show = new ShowPageBean<News>();
		show.setPageNum(pageBean.getPageNum());
		show.setPageSize(Constant.SHOW_PAGESIZE);
		show.setCname(showCategory);
		
		List<News> list = newsDao.selectListByCname(show);
		//转义特殊字符
		for(News news:list){
			news.setTitle(StringTransferUtils.string2html(news.getTitle()));
		}
		int count = newsDao.selectCountByCname(showCategory);
		pageBean.setPageSize(Constant.SHOW_PAGESIZE);
		
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
