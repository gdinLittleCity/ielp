package cn.edu.gdin.ilep.show.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.gdin.ilep.manager.dao.NewsDao;
import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.page.ShowPageBean;
import cn.edu.gdin.ilep.show.service.ShowActivityService;
import cn.edu.gdin.ilep.util.Constant;
import cn.edu.gdin.ilep.util.StringTransferUtils;

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
