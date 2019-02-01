package cn.edu.gdin.ilep.show.service;

import java.util.List;

import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.page.PageBean;

public interface ShowNewsService {
	/**
	 * 新闻动态列表,时间排序
	 * @return
	 */
	List<News> newsList();
	/**
	 * 信息素质列表,时间排序
	 * @return
	 */
	List<News> qualityList();
	/**
	 * 信息获取列表,时间排序
	 * @return
	 */
	List<News> obtainList();
	/**
	 * 信息甄别列表,时间排序
	 * @return
	 */
	List<News> identifyList();
	/**
	 * 科普活动列表,时间排序
	 * @return
	 */
	List<News> activityList();
	/**
	 * 获取文章详情
	 * @param nid
	 * @return
	 */
	News getArticlInfo(String nid);
	
	/**
	 * 获取文章列表
	 * @param pageBean
	 * @param showCategoryNews
	 * @return
	 */
	PageBean<News> getNewsList(PageBean<News> pageBean, String showCategory);

}
