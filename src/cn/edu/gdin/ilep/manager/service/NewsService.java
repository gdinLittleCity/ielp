package cn.edu.gdin.ilep.manager.service;

import javax.servlet.http.HttpSession;

import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.page.PageBean;

public interface NewsService {
	/**
	 * 增加文章
	 * @param news
	 * @return
	 */
	int addNews(News news,HttpSession session);
	/**
	 * 获取文章列表--分页
	 * @param pageBean
	 * @return
	 */
	PageBean<News> getAllNews(PageBean<News> pageBean);
	/**
	 * 批量删除
	 * @param ids 文章的ID数组
	 * @return
	 */
	int deleteNews(String[] ids);
	/**
	 * 修改文章
	 * @param news
	 * @return
	 */
	int updateNews(News news,HttpSession session);
	/**
	 * 获取文章通过ID
	 * @param nid
	 * @return
	 */
	News selectNewsByID(String nid);

}
