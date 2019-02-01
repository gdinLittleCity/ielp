package cn.edu.gdin.ilep.show.service;

import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.page.PageBean;

public interface ShowActivityService {
	
	/**
	 * 获取科普活动列表
	 * @param pageBean
	 * @return
	 */
	PageBean<News> getActivityList(PageBean<News> pageBean);

}
