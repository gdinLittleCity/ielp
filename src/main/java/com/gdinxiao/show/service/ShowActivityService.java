package com.gdinxiao.show.service;

import com.gdinxiao.manager.domain.News;
import com.gdinxiao.page.PageBean;

public interface ShowActivityService {
	
	/**
	 * 获取科普活动列表
	 * @param pageBean
	 * @return
	 */
	PageBean<News> getActivityList(PageBean<News> pageBean);

}
